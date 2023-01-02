package site.levyro.takeout.mapper;

import site.levyro.takeout.entity.Employee;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface MyMapper {
    Employee selectByUsername(@Param("username") String username);
}
