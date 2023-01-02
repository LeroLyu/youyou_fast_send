package site.levyro.takeout.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import site.levyro.takeout.entity.AddressBook;
import site.levyro.takeout.mapper.AddressBookMapper;
import site.levyro.takeout.service.AddressBookService;
import org.springframework.stereotype.Service;

@Service
public class AddressBookServiceImpl extends ServiceImpl<AddressBookMapper, AddressBook> implements AddressBookService {

}
