package site.levyro.takeout.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import site.levyro.takeout.common.CustomException;
import site.levyro.takeout.common.R;
import site.levyro.takeout.dto.SetmealDto;
import site.levyro.takeout.entity.Category;
import site.levyro.takeout.entity.Dish;
import site.levyro.takeout.entity.Setmeal;
import site.levyro.takeout.entity.SetmealDish;
import site.levyro.takeout.service.CategoryService;
import site.levyro.takeout.service.DishService;
import site.levyro.takeout.service.SetmealDishService;
import site.levyro.takeout.service.SetmealService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/setmeal")
@Slf4j
public class SetmealController {
    @Autowired
    private SetmealService setmealService;

    @Autowired
    private SetmealDishService setmealDishService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private DishService dishService;

    @GetMapping("/{id}")
    public R<SetmealDto> getById(@PathVariable Long id) {
        SetmealDto setmealDto = setmealService.getByIdWithDish(id);
        if(setmealDto != null){
            return R.success(setmealDto);
        }
        return R.error("没有查询到对应菜品信息");
    }

    @PostMapping
    private R<String> save(@RequestBody SetmealDto setmealDto) {
        setmealService.saveWithDish(setmealDto);
        return R.success("添加成功");
    }

    @PutMapping
    public R<String> update(@RequestBody SetmealDto setmealDto) {
        setmealService.updateWithDish(setmealDto);
        return R.success("修改套餐成功");
    }

    @GetMapping("/page")
    public R<Page> page(int page, int pageSize, String name) {
        log.info("page = {},pageSize = {},name = {}" ,page,pageSize,name);
        //构造分页构造器
        Page<Setmeal> pageInfo = new Page<>(page, pageSize);
        Page<SetmealDto> setmealDtoPage = new Page<>();
        //构造条件构造器
        LambdaQueryWrapper<Setmeal> queryWrapper = new LambdaQueryWrapper();
        //添加过滤条件
        queryWrapper.like(StringUtils.isNotEmpty(name), Setmeal::getName, name);
        //添加排序条件
        queryWrapper.orderByDesc(Setmeal::getUpdateTime);
        //执行查询
        setmealService.page(pageInfo,queryWrapper);
        // 对象拷贝
        BeanUtils.copyProperties(pageInfo, setmealDtoPage, "records");
        List<Setmeal> records = pageInfo.getRecords();
        List<SetmealDto> list = new ArrayList<>();
        for (Setmeal record : records) {
            SetmealDto setmealDto = new SetmealDto();
            BeanUtils.copyProperties(record, setmealDto);
            Long categoryId = record.getCategoryId();
            Category category = categoryService.getById(categoryId);
            if (category != null) {
                setmealDto.setCategoryName(category.getName());
            }
            list.add(setmealDto);
        }
        setmealDtoPage.setRecords(list);

        return R.success(setmealDtoPage);
    }

    @PostMapping("/status/{status}")
    public R<String> changeBatchStatus(@PathVariable Integer status, String ids) {
        String[] strings = ids.split(",");
        List<Long> id = new ArrayList<>();
        for (String string : strings) {
            id.add(Long.valueOf(string));
        }
        for (Long i : id) {
            Setmeal setmeal = setmealService.getById(i);
            setmeal.setStatus(status);
            setmealService.updateById(setmeal);
        }
        return R.success("停/起售修改成功");
    }

    @DeleteMapping
    @Transactional
    public R<String> delete(String ids) {
        String[] strings = ids.split(",");
        List<Long> id = new ArrayList<>();
        for (String string : strings) {
            id.add(Long.valueOf(string));
        }
        for (Long i : id) {
            LambdaQueryWrapper<Setmeal> queryWrapper1 = new LambdaQueryWrapper<>();
            queryWrapper1.eq(Setmeal::getStatus, 0);
            queryWrapper1.eq(Setmeal::getId, i);
            Setmeal setmeal = setmealService.getOne(queryWrapper1);
            if (setmeal != null) {
                LambdaQueryWrapper<SetmealDish> queryWrapper2 = new LambdaQueryWrapper<>();
                queryWrapper2.eq(SetmealDish::getSetmealId, i);
                setmealDishService.remove(queryWrapper2);
                setmealService.removeById(i);
            }
            else {
                throw new CustomException("套餐正在售卖中不能删除");
            }

        }
        return R.success("删除成功");
    }

    @GetMapping("/list")
    public R<List<SetmealDto>> getDishList(Long categoryId) {
        LambdaQueryWrapper<Setmeal> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Setmeal::getCategoryId, categoryId);
        queryWrapper.eq(Setmeal::getStatus, 1);
        queryWrapper.orderByDesc(Setmeal::getUpdateTime);
        List<Setmeal> list = setmealService.list(queryWrapper);
        List<SetmealDto> res = new ArrayList<>();
        for (Setmeal record : list) {
            SetmealDto setmealDto = new SetmealDto();
            BeanUtils.copyProperties(record, setmealDto);
            Long setmealId = record.getId();
            LambdaQueryWrapper<SetmealDish> queryWrapper1 = new LambdaQueryWrapper<>();
            queryWrapper1.eq(SetmealDish::getSetmealId, setmealId);
            List<SetmealDish> setmealDishList = setmealDishService.list(queryWrapper1);
            setmealDto.setSetmealDishes(setmealDishList);
            res.add(setmealDto);
        }
        return R.success(res);
    }

    @GetMapping("/dish/{setmealId}")
    public R<List<Dish>> getDish(@PathVariable Long setmealId) {
        LambdaQueryWrapper<SetmealDish> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SetmealDish::getSetmealId, setmealId);
        List<SetmealDish> dishList = setmealDishService.list(queryWrapper);
        List<Dish> list = new ArrayList<>();
        for (SetmealDish setmealDish : dishList) {
            LambdaQueryWrapper<Dish> lambdaQueryWrapper = new LambdaQueryWrapper<>();
            lambdaQueryWrapper.eq(Dish::getId, setmealDish.getDishId());
            Dish one = dishService.getOne(lambdaQueryWrapper);
            list.add(one);
        }
        return R.success(list);
    }
}
