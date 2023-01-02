package site.levyro.takeout.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import site.levyro.takeout.entity.Dish;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DishMapper extends BaseMapper<Dish> {
}
