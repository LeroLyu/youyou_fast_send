package site.levyro.takeout.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import site.levyro.takeout.entity.OrderDetail;
import site.levyro.takeout.mapper.OrderDetailMapper;
import site.levyro.takeout.service.OrderDetailService;
import org.springframework.stereotype.Service;

@Service
public class OrderDetailServiceImpl extends ServiceImpl<OrderDetailMapper, OrderDetail> implements OrderDetailService {

}