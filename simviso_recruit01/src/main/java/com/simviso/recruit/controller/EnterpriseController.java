package com.simviso.recruit.controller;

import com.simviso.recruit.pojo.Enterprise;
import com.simviso.recruit.service.EnterpriseService;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @description: simviso_parent
 * @author: AAR
 * @date: 1/26/2019
 * @Email: liujch1996@gmail.com
 */

@RestController
@CrossOrigin
@RequestMapping("/enterprise")
public class EnterpriseController {

    @Autowired
    private EnterpriseService enterpriseService;

    @GetMapping("/search/hotlist")
    public Result hotList(){
        List<Enterprise> hotList = enterpriseService.hotList("1");
        return new Result(true, StatusCode.OK,"查询成功",hotList);
    }

    @GetMapping
    public Result findAll(){
        return new Result(true, StatusCode.OK,"查询成功",enterpriseService.findAll());
    }
}
