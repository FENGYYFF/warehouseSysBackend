package com.wmsdemo.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wmsdemo.common.QueryPageParam;
import com.wmsdemo.common.Result;
import com.wmsdemo.entity.Menu;
import com.wmsdemo.entity.User;
import com.wmsdemo.service.MenuService;
import com.wmsdemo.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final MenuService menuService;

    public UserController(UserService userService, MenuService menuService) {
        this.userService = userService;
        this.menuService = menuService;
    }

    @GetMapping("/list")
    public List<User> list() {
        return userService.list();
    }

    @GetMapping("/findByNo")
    public Result findByNo(@RequestParam String no) {
        List<User> list = userService.lambdaQuery().eq(User::getNo, no).list();
        return list.isEmpty() ? Result.failed() : Result.success(list);
    }

    @PostMapping("/save")
    public Result save(@RequestBody User user) {
        return userService.save(user) ? Result.success() : Result.failed();
    }

    @PostMapping("/update")
    public Result update(@RequestBody User user) {
        return userService.updateById(user) ? Result.success() : Result.failed();
    }

    @GetMapping("/del")
    public Result del(@RequestParam String id) {
        return userService.removeById(id) ? Result.success() : Result.failed();
    }

    @PostMapping("/login")
    public Result login(@RequestBody User user) {
        List<User> list = userService.lambdaQuery().eq(User::getNo, user.getNo()).eq(User::getPassword, user.getPassword()).list();

        if (!list.isEmpty()) {
            User user1 = list.get(0);
            List<Menu> menuList = menuService.lambdaQuery().like(Menu::getMenuright, user1.getRoleId()).list();
            Map<String, Object> res = new HashMap<>();
            res.put("user", user1);
            res.put("menu", menuList);
            return Result.success(res);
        }
        return Result.failed();
    }

    @PostMapping("/saveOrMod")
    public boolean saveOrMod(@RequestBody User user) {
        return userService.saveOrUpdate(user);
    }

    @GetMapping("/delete")
    public boolean delete(@RequestParam Integer id) {
        return userService.removeById(id);
    }

    @PostMapping("/listP")
    public Result listP(@RequestBody User user) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        if (StringUtils.isNotBlank(user.getName())) {
            wrapper.like(User::getName, user.getName());
        }
        return Result.success(userService.list(wrapper));
    }

    @PostMapping("/listPage")
    public Result listPage(@RequestBody QueryPageParam query) {
        return getPagedUserResult(query);
    }

    @PostMapping("/listPageC")
    public Result listPageC(@RequestBody QueryPageParam query) {
        return getPagedUserResult(query);
    }

    @PostMapping("/listPageC1")
    public Result listPageC1(@RequestBody QueryPageParam query) {
        Map<String, Object> param = query.getParam();
        LambdaQueryWrapper<User> wrapper = createQueryWrapper(param);
        Page<User> page = new Page<>(query.getPageNum(), query.getPageSize());

        IPage<User> result = userService.pageCC(page, wrapper);
        return Result.success(result.getRecords(), result.getTotal());
    }

    private Result getPagedUserResult(QueryPageParam query) {
        Map<String, Object> param = query.getParam();
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        String name = (String) param.get("name");
        if (StringUtils.isNotBlank(name)) {
            wrapper.like(User::getName, name);
        }

        Page<User> page = new Page<>(query.getPageNum(), query.getPageSize());
        IPage<User> result = userService.page(page, wrapper);

        return Result.success(result.getRecords(), result.getTotal());
    }

    private LambdaQueryWrapper<User> createQueryWrapper(Map<String, Object> param) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        String name = (String) param.get("name");
        String sex = (String) param.get("sex");
        String roleId = (String) param.get("roleId");

        if (StringUtils.isNotBlank(name) && !"null".equals(name)) {
            wrapper.like(User::getName, name);
        }
        if (StringUtils.isNotBlank(sex)) {
            wrapper.eq(User::getSex, sex);
        }
        if (StringUtils.isNotBlank(roleId)) {
            wrapper.eq(User::getRoleId, roleId);
        }

        return wrapper;
    }
}
