package com.wmsdemo.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wmsdemo.common.QueryPageParam;
import com.wmsdemo.common.Result;
import com.wmsdemo.entity.Storage;
import com.wmsdemo.service.StorageService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/storage")
public class StorageController {

    private final StorageService storageService;

    public StorageController(StorageService storageService) {
        this.storageService = storageService;
    }

    @PostMapping("/save")
    public Result save(@RequestBody Storage storage) {
        return storageService.save(storage) ? Result.success() : Result.failed();
    }

    @PostMapping("/update")
    public Result update(@RequestBody Storage storage) {
        return storageService.updateById(storage) ? Result.success() : Result.failed();
    }

    @GetMapping("/del")
    public Result del(@RequestParam String id) {
        return storageService.removeById(id) ? Result.success() : Result.failed();
    }

    @PostMapping("/listPage")
    public Result listPage(@RequestBody QueryPageParam query) {
        Map<String, Object> param = query.getParam();
        Page<Storage> page = new Page<>(query.getPageNum(), query.getPageSize());

        LambdaQueryWrapper<Storage> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        String name = (String) param.get("name");
        if (StringUtils.isNotBlank(name) && !"null".equals(name)) {
            lambdaQueryWrapper.like(Storage::getName, name);
        }

        IPage<Storage> result = storageService.pageCC(page, lambdaQueryWrapper);
        return Result.success(result.getRecords(), result.getTotal());
    }

    @GetMapping("/list")
    public Result list() {
        List<Storage> list = storageService.list();
        return Result.success(list);
    }
}
