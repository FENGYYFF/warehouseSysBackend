package com.wmsdemo.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wmsdemo.entity.Record;
import com.wmsdemo.mapper.RecordMapper;
import com.wmsdemo.service.RecordService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class RecordServiceImpl extends ServiceImpl<RecordMapper, Record> implements RecordService {

    @Resource
    private RecordMapper recordMapper;

    @Override
    public IPage pageCC(IPage<Record> page, Wrapper wrapper) {
        return recordMapper.pageCC(page, wrapper);
    }
}
