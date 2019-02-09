package com.simviso.article.controller;

import com.simviso.article.pojo.Comment;
import com.simviso.article.service.CommentService;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @description: simviso_parent
 * @author: AAR
 * @date: 2/2/2019
 * @Email: liujch1996@gmail.com
 */
@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping
    public Result save(Comment comment){
        commentService.add(comment);
        return new Result(true, StatusCode.OK,"评论成功");
    }

    @GetMapping("/article/{articleid}")
    public Result findByArticleId(@PathVariable String articleid){
        return new Result(true, StatusCode.OK, "查询成功",
                commentService.findByArticleid(articleid));
    }

}
