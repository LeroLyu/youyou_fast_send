package site.levyro.takeout.dto;

import site.levyro.takeout.entity.Setmeal;
import site.levyro.takeout.entity.SetmealDish;
import lombok.Data;
import java.util.List;

@Data
public class SetmealDto extends Setmeal {

    private List<SetmealDish> setmealDishes;

    private String categoryName;
}
