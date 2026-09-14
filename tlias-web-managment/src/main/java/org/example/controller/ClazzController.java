package org.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.mapper.ClazzMapper;
import org.example.pojo.Clazz;
import org.example.pojo.ClazzQueryParam;
import org.example.pojo.PageResult;
import org.example.pojo.Result;
import org.example.service.ClazzService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/clazzs")
public class ClazzController {

    private final ClazzService clazzService;

    public ClazzController(ClazzService clazzService) {
        this.clazzService = clazzService;
    }

    @GetMapping
    public Result list(ClazzQueryParam clazzQueryParam) {
        log.info("分页查询班级列表：{}", clazzQueryParam);
        PageResult<Clazz> pageResult = clazzService.page(clazzQueryParam);
        return Result.success(pageResult);
    }

    @DeleteMapping
    public Result delete(@RequestParam Integer id) {
        log.info("删除班级:{}", id);
        clazzService.delete(id);
        return Result.success();
    }

    @PostMapping
    public Result insert(@RequestBody Clazz clazz) {
        log.info("添加班级:{}", clazz);
        clazzService.insert(clazz);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result getInfo(@PathVariable Integer id) {
        log.info("根据ID查询班级:{}", id);
        Clazz clazz = clazzService.getInfo(id);
        return Result.success(clazz);
    }

    @PutMapping
    public Result update(@RequestBody Clazz clazz) {
        log.info("修改班级信息:{}", clazz);
        clazzService.update(clazz);
        return Result.success();
    }

    @GetMapping("/list")
    public Result findAll() {
        log.info("查询所有班级");
        List<Clazz> list = clazzService.findAll();
        return Result.success(list);
    }
}

