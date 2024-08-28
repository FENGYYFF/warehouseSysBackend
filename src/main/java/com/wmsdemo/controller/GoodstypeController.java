package com.wmsdemo.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wmsdemo.common.QueryPageParam;
import com.wmsdemo.common.Result;
import com.wmsdemo.entity.Goodstype;
import com.wmsdemo.service.GoodstypeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/goodstype")
public class GoodstypeController {

    private final GoodstypeService goodstypeService;

    public GoodstypeController(GoodstypeService goodstypeService) {
        this.goodstypeService = goodstypeService;
    }

    @PostMapping("/save")
    public Result save(@RequestBody Goodstype goodstype) {
        return goodstypeService.save(goodstype) ? Result.success() : Result.failed();
    }

    @PostMapping("/update")
    public Result update(@RequestBody Goodstype goodstype) {
        return goodstypeService.updateById(goodstype) ? Result.success() : Result.failed();
    }

    @GetMapping("/del")
    public Result del(@RequestParam String id) {
        return goodstypeService.removeById(id) ? Result.success() : Result.failed();
    }

    @PostMapping("/listPage")
    public Result listPage(@RequestBody QueryPageParam query) {
        Map<String, Object> param = query.getParam();
        Page<Goodstype> page = new Page<>(query.getPageNum(), query.getPageSize());

        LambdaQueryWrapper<Goodstype> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.like(StringUtils.isNotBlank((String) param.get("name")) && !"null".equals(param.get("name")), Goodstype::getName, param.get("name"));

        IPage<Goodstype> result = goodstypeService.pageCC(page, lambdaQueryWrapper);
        return Result.success(result.getRecords(), result.getTotal());
    }

    @GetMapping("/list")
    public Result list() {
        List<Goodstype> list = goodstypeService.list();
        return Result.success(list);
    }
}
