package site.levyro.takeout.service;

import com.baomidou.mybatisplus.extension.service.IService;
import site.levyro.takeout.dto.SetmealDto;
import site.levyro.takeout.entity.Setmeal;

public interface SetmealService extends IService<Setmeal> {
    void saveWithDish(SetmealDto setmealDto);
    SetmealDto getByIdWithDish(Long id);
    void updateWithDish(SetmealDto setmealDto);
}
