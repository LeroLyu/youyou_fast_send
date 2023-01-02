package site.levyro.takeout.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import site.levyro.takeout.entity.Orders;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderMapper extends BaseMapper<Orders> {

}