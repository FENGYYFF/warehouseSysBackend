package com.wmsdemo.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wmsdemo.common.QueryPageParam;
import com.wmsdemo.common.Result;
import com.wmsdemo.entity.Goods;
import com.wmsdemo.service.GoodsService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/goods")
public class GoodsController {

    private final GoodsService goodsService;

    public GoodsController(GoodsService goodsService) {
        this.goodsService = goodsService;
    }

    @PostMapping("/save")
    public Result save(@RequestBody Goods goods) {
        return goodsService.save(goods) ? Result.success() : Result.failed();
    }

    @PostMapping("/update")
    public Result update(@RequestBody Goods goods) {
        return goodsService.updateById(goods) ? Result.success() : Result.failed();
    }

    @GetMapping("/del")
    public Result del(@RequestParam String id) {
        return goodsService.removeById(id) ? Result.success() : Result.failed();
    }

    @PostMapping("/listPage")
    public Result listPage(@RequestBody QueryPageParam query) {
        Map<String, Object> param = query.getParam();
        Page<Goods> page = new Page<>(query.getPageNum(), query.getPageSize());

        LambdaQueryWrapper<Goods> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.like(StringUtils.isNotBlank((String) param.get("name")) && !"null".equals(param.get("name")), Goods::getName, param.get("name"));
        lambdaQueryWrapper.eq(StringUtils.isNotBlank((String) param.get("goodstype")) && !"null".equals(param.get("goodstype")), Goods::getGoodstype, param.get("goodstype"));
        lambdaQueryWrapper.eq(StringUtils.isNotBlank((String) param.get("storage")) && !"null".equals(param.get("storage")), Goods::getStorage, param.get("storage"));

        IPage<Goods> result = goodsService.pageCC(page, lambdaQueryWrapper);
        return Result.success(result.getRecords(), result.getTotal());
    }
}
