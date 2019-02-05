package com.simviso.qa.controller;

import com.simviso.qa.pojo.Problem;
import com.simviso.qa.service.ProblemService;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @description: simviso_parent
 * @author: AAR
 * @date: 1/31/2019
 * @Email: liujch1996@gmail.com
 */
@RestController
@RequestMapping("/problem")
@CrossOrigin
public class ProblemController {

    private final ProblemService problemService;

    @Autowired
    public ProblemController(ProblemService problemService) {
        this.problemService = problemService;
    }

    @GetMapping
    public Result findAll(){
        return new Result(true, StatusCode.OK,"查询成功",problemService.findAll());
    }

    @GetMapping("/{id}")
    public Result findById(@PathVariable String id){
        return new Result(true,StatusCode.OK,"查询成功",problemService.findById(id));
    }


    @PostMapping("/search/{page}/{size}")
    public Result findSearch(@RequestBody Map searchMap , @PathVariable int page, @PathVariable int size){
        Page<Problem> pageList = problemService.findSearch(searchMap,page,size);
        return  new Result(true,StatusCode.OK,"查询成功", new PageResult<>(pageList.getTotalElements(), pageList.getContent()) );
    }


    @PostMapping("/search")
    public Result findSearch( @RequestBody Map searchMap){
        return new Result(true,StatusCode.OK,"查询成功",problemService.findSearch(searchMap));
    }

    @PostMapping
    public Result add(@RequestBody Problem problem  ){
        problemService.add(problem);
        return new Result(true,StatusCode.OK,"增加成功");
    }

    @PutMapping("/{id}")
    public Result update(@RequestBody Problem problem, @PathVariable String id ){
        problem.setId(id);
        problemService.update(problem);
        return new Result(true,StatusCode.OK,"修改成功");
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable String id ){
        problemService.deleteById(id);
        return new Result(true,StatusCode.OK,"删除成功");
    }

}
