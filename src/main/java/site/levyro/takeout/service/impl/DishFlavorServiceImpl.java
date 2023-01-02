package site.levyro.takeout.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import site.levyro.takeout.entity.DishFlavor;
import site.levyro.takeout.mapper.DishFlavorMapper;
import site.levyro.takeout.service.DishFlavorService;
import org.springframework.stereotype.Service;

@Service
public class DishFlavorServiceImpl extends ServiceImpl<DishFlavorMapper, DishFlavor> implements DishFlavorService {
}
