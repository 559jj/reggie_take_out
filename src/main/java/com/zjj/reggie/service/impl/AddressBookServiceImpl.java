package com.zjj.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zjj.reggie.entity.AddressBook;
import com.zjj.reggie.mapper.AddressBookMapper;
import com.zjj.reggie.service.AddressBookService;
import org.springframework.stereotype.Service;

/**
 * 地址管理(AddressBook)表服务实现类
 *
 * @author makejava
 * @since 2022-10-07 09:24:02
 */
@Service("addressBookService")
public class AddressBookServiceImpl extends ServiceImpl<AddressBookMapper, AddressBook> implements AddressBookService {

}

