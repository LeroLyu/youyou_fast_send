package site.levyro.takeout.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import site.levyro.takeout.entity.Employee;
import site.levyro.takeout.mapper.EmployeeMapper;
import site.levyro.takeout.service.EmployeeService;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper,Employee> implements EmployeeService {
}
