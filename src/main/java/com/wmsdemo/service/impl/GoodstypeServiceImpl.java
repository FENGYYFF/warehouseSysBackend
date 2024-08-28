package com.wmsdemo.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wmsdemo.entity.Goodstype;
import com.wmsdemo.mapper.GoodstypeMapper;
import com.wmsdemo.service.GoodstypeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class GoodstypeServiceImpl extends ServiceImpl<GoodstypeMapper, Goodstype> implements GoodstypeService {

    @Resource
    private GoodstypeMapper goodstypeMapper;

    @Override
    public IPage pageCC(IPage<Goodstype> page, Wrapper wrapper) {
        return goodstypeMapper.pageCC(page, wrapper);
    }
}
