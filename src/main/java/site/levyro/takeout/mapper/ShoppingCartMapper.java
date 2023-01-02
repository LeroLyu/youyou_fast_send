package site.levyro.takeout.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import site.levyro.takeout.entity.ShoppingCart;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ShoppingCartMapper extends BaseMapper<ShoppingCart> {
}
