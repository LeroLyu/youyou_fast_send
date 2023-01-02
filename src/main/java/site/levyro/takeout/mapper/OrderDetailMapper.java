package site.levyro.takeout.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import site.levyro.takeout.entity.OrderDetail;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderDetailMapper extends BaseMapper<OrderDetail> {

}