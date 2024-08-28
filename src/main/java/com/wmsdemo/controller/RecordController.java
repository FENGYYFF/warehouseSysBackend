package com.wmsdemo.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wmsdemo.common.QueryPageParam;
import com.wmsdemo.common.Result;
import com.wmsdemo.entity.Goods;
import com.wmsdemo.entity.Record;
import com.wmsdemo.service.GoodsService;
import com.wmsdemo.service.RecordService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/record")
public class RecordController {

    private final RecordService recordService;
    private final GoodsService goodsService;

    public RecordController(RecordService recordService, GoodsService goodsService) {
        this.recordService = recordService;
        this.goodsService = goodsService;
    }

    @PostMapping("/listPage")
    public Result listPage(@RequestBody QueryPageParam query) {
        Map<String, Object> param = query.getParam();
        Page<Record> page = new Page<>(query.getPageNum(), query.getPageSize());

        QueryWrapper<Record> queryWrapper = new QueryWrapper<>();
        queryWrapper.apply("a.goods=b.id and b.storage=c.id and b.goodsType=d.id");

        if ("2".equals(param.get("roleId"))) {
            queryWrapper.apply("a.userId = {0}", param.get("userId"));
        }

        if (StringUtils.isNotBlank((String) param.get("name")) && !"null".equals(param.get("name"))) {
            queryWrapper.like("b.name", param.get("name"));
        }
        if (StringUtils.isNotBlank((String) param.get("goodstype")) && !"null".equals(param.get("goodstype"))) {
            queryWrapper.eq("d.id", param.get("goodstype"));
        }
        if (StringUtils.isNotBlank((String) param.get("storage")) && !"null".equals(param.get("storage"))) {
            queryWrapper.eq("c.id", param.get("storage"));
        }

        IPage<Record> result = recordService.pageCC(page, queryWrapper);
        return Result.success(result.getRecords(), result.getTotal());
    }

    @PostMapping("/save")
    public Result save(@RequestBody Record record) {
        Goods goods = goodsService.getById(record.getGoods());
        int n = "2".equals(record.getAction()) ? -record.getCount() : record.getCount();
        goods.setCount(goods.getCount() + n);
        record.setCount(n);

        goodsService.updateById(goods);
        return recordService.save(record) ? Result.success() : Result.failed();
    }
}
