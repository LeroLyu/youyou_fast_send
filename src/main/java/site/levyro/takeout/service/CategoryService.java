package site.levyro.takeout.service;

import com.baomidou.mybatisplus.extension.service.IService;
import site.levyro.takeout.entity.Category;

public interface CategoryService extends IService<Category> {
    void remove(Long id);
}
