package site.levyro.takeout.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import site.levyro.takeout.entity.ShoppingCart;
import site.levyro.takeout.mapper.ShoppingCartMapper;
import site.levyro.takeout.service.ShoppingCartService;
import org.springframework.stereotype.Service;

@Service
public class ShoppingCartServiceImpl extends ServiceImpl<ShoppingCartMapper, ShoppingCart> implements ShoppingCartService {
}
