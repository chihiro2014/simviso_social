package com.simviso.user.controller;

import com.simviso.user.pojo.User;
import com.simviso.user.service.UserService;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @description: simviso_parent
 * @author: AAR
 * @date: 2/9/2019
 * @Email: liujch1996@gmail.com
 */
@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RedisTemplate redisTemplate;

    @GetMapping
    public Result findAll(){
        return new Result(true, StatusCode.OK,"查询成功",userService.findAll());
    }

    @GetMapping("/{id}")
    public Result findById(@PathVariable String id){
        return new Result(true,StatusCode.OK,"查询成功",userService.findById(id));
    }

    @PostMapping("/search/{page}/{size}")
    public Result findSearch(@RequestBody Map searchMap , @PathVariable int page, @PathVariable int size){
        Page<User> pageList = userService.findSearch(searchMap, page, size);
        return  new Result(true,StatusCode.OK,"查询成功",  new PageResult<User>(pageList.getTotalElements(), pageList.getContent()) );
    }

    @PostMapping("/search")
    public Result findSearch( @RequestBody Map searchMap){
        return new Result(true,StatusCode.OK,"查询成功",userService.findSearch(searchMap));
    }

    @PostMapping
    public Result add(@RequestBody User user  ){
        userService.add(user);
        return new Result(true,StatusCode.OK,"增加成功");
    }

    @PutMapping("/{id}")
    public Result update(@RequestBody User user, @PathVariable String id ){
        user.setId(id);
        userService.update(user);
        return new Result(true,StatusCode.OK,"修改成功");
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable String id ){
        userService.deleteById(id);
        return new Result(true,StatusCode.OK,"删除成功");
    }

    @PostMapping("/sendsms/{mobile}")
    public Result sendSms(@PathVariable String mobile){
        userService.sendSms(mobile);
        return new Result(true, StatusCode.OK,"发送成功");
    }

    @PostMapping("/register/{code}")
    public Result regist(@PathVariable String code,@RequestBody User user){
        String checkcodeRedis = (String) redisTemplate.opsForValue().get("checkcode_" + user.getMobile());
        if (checkcodeRedis.isEmpty()){
            return new Result(false,StatusCode.ERROR,"请先获取验证码");
        }
        if (!checkcodeRedis.equals(code)){
            return new Result(false,StatusCode.ERROR,"验证码错误");
        }
        userService.add(user);
        return new Result(true,StatusCode.OK,"注册成功");
    }
}
