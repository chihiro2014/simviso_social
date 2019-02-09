package com.simviso.spit.controller;

import com.simviso.spit.pojo.Spit;
import com.simviso.spit.service.SpitService;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

/**
 * @description: simviso_parent
 * @author: AAR
 * @date: 2/2/2019
 * @Email: liujch1996@gmail.com
 */
@RestController
@CrossOrigin
@RequestMapping("/spit")
public class SpitController {

    private final SpitService spitService;

    private final RedisTemplate redisTemplate;

    @Autowired
    public SpitController(SpitService spitService, RedisTemplate redisTemplate) {
        this.spitService = spitService;
        this.redisTemplate = redisTemplate;
    }

    @GetMapping
    public Result findAll(){
        return new Result(true, StatusCode.OK,"查询成功",spitService.findAll());
    }

    @GetMapping("/{spitId}")
    public Result findById(@PathVariable String spitId){
        return new Result(true, StatusCode.OK, "查询成功", spitService.findById(spitId));
    }

    @PostMapping
    public Result save(@RequestBody Spit spit){
        spitService.save(spit);
        return new Result(true, StatusCode.OK, "添加成功");
    }

    @PutMapping("/{spitId}")
    public Result update(@PathVariable String spitId, @RequestBody Spit spit){
        spit.set_id(spitId);
        spitService.update(spit);
        return new Result(true, StatusCode.OK, "修改成功");
    }


    @DeleteMapping("/{spitId}")
    public Result delete(@PathVariable String spitId){
        spitService.deleteById(spitId);
        return new Result(true, StatusCode.OK, "删除成功");
    }

    @GetMapping("/comment/{parentid}/{page}/{size}")
    public Result comment(@PathVariable String parentid, @PathVariable int page, @PathVariable int size){
        Page<Spit> pageData = spitService.pageQuery(parentid, page, size);
        return new Result(true, StatusCode.OK, "查询成功",
                new PageResult<>(pageData.getTotalElements(), pageData.getContent()));
    }

    @PutMapping("/thumbup/{spitId}")
    public Result addthumbup(@PathVariable String spitId){
        String userid = "11111";
        //先判断该用户是否已经点赞了。
        if(redisTemplate.opsForValue().get("spit_"+userid+"_"+spitId)!=null){
            return new Result(false, StatusCode.REPERROR, "不能重复点赞");
        }
        spitService.addthumbup(spitId);
        redisTemplate.opsForValue().set("spit_"+userid+"_"+spitId, 1);
        return new Result(true, StatusCode.OK, "点赞成功");
    }
}
