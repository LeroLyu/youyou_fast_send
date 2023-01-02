package site.levyro.takeout.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import site.levyro.takeout.entity.Employee;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EmployeeMapper extends BaseMapper<Employee>{
}
