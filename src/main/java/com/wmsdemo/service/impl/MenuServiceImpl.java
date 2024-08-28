package com.wmsdemo.service.impl;

import com.wmsdemo.entity.Menu;
import com.wmsdemo.mapper.MenuMapper;
import com.wmsdemo.service.MenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;


@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {

}
