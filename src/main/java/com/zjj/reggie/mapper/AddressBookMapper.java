package com.zjj.reggie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zjj.reggie.entity.AddressBook;
import org.apache.ibatis.annotations.Mapper;


/**
 * 地址管理(AddressBook)表数据库访问层
 *
 * @author makejava
 * @since 2022-10-07 09:23:58
 */
@Mapper
public interface AddressBookMapper extends BaseMapper<AddressBook> {

}


