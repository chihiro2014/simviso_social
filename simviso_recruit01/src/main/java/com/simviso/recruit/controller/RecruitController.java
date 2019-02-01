package com.simviso.recruit.controller;

import com.simviso.recruit.pojo.Recruit;
import com.simviso.recruit.service.RecruitService;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @description: simviso_parent
 * @author: AAR
 * @date: 1/26/2019
 * @Email: liujch1996@gmail.com
 */
@RestController
@RequestMapping("/recruit")
@CrossOrigin
public class RecruitController {

    private final RecruitService recruitService;

    @Autowired
    public RecruitController(RecruitService recruitService) {
        this.recruitService = recruitService;
    }

    @GetMapping
    public Result findAll(){
        return new Result(true, StatusCode.OK,"查询成功",recruitService.findAll());
    }

    @GetMapping("/{id}")
    public Result findById(@PathVariable String id){
        return new Result(true, StatusCode.OK,"查询成功",recruitService.findById(id));
    }

    @PostMapping("/search/{page}/{size}")
    public Result findSearch(Map searchMap, @PathVariable int page, @PathVariable int size){
        Page<Recruit> pageList = recruitService.findSearch(searchMap,page,size);
        return new Result(true, StatusCode.OK,"查询成功",
                new PageResult<>(pageList.getTotalElements(), pageList.getContent()));
    }

    @PostMapping("/search")
    public Result findSearch(Map searchMap){
        return new Result(true, StatusCode.OK,"查询成功",recruitService.findSearch(searchMap));
    }

    @PostMapping
    public Result add(@RequestBody Recruit recruit){
        recruitService.add(recruit);
        return new Result(true,StatusCode.OK,"添加成功");
    }

    @PutMapping("/{id}")
    public Result update(@RequestBody Recruit recruit, @PathVariable String id){
        recruit.setId(id);
        recruitService.update(recruit);
        return new Result(true, StatusCode.OK,"修改成功");
    }

    @DeleteMapping("/{id}")
    public Result deleteById(@PathVariable String id){
        recruitService.deleteById(id);
        return new Result(true, StatusCode.OK,"删除成功");
    }

    @GetMapping("/recommend")
    public Result recommend(){
        List<Recruit> recommendList = recruitService.findTop4ByStateOrderByCreatetimeDesc("2");
        return new Result(true, StatusCode.OK,"查询成功",recommendList);
    }

    @GetMapping("/search/new")
    public Result newList(){
        List<Recruit> newList = recruitService.newlist();
        return new Result(true, StatusCode.OK,"查询成功",newList);
    }
}
