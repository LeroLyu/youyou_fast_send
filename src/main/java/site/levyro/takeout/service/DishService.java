package site.levyro.takeout.service;

import com.baomidou.mybatisplus.extension.service.IService;
import site.levyro.takeout.dto.DishDto;
import site.levyro.takeout.entity.Dish;

public interface DishService extends IService<Dish> {
    // 新增菜品和对应的菜品口味
    void saveWithFlavor(DishDto dishDto);
    DishDto getByIdWithFlavor(Long id);
    void updateWithFlavor(DishDto dishDto);
}
