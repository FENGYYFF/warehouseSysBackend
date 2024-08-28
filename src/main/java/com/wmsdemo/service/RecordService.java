package com.wmsdemo.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wmsdemo.entity.Record;
import com.baomidou.mybatisplus.extension.service.IService;

public interface RecordService extends IService<Record> {

    IPage pageCC(IPage<Record> page, Wrapper wrapper);
}
