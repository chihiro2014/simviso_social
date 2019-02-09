package com.simviso.article.service;

import com.simviso.article.dao.CommentDao;
import com.simviso.article.pojo.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import util.IdWorker;

import java.util.List;

/**
 * @description: simviso_parent
 * @author: AAR
 * @date: 2/2/2019
 * @Email: liujch1996@gmail.com
 */
@Service
public class CommentService {

    private final CommentDao commentDao;

    @Autowired
    private IdWorker idWorker;

    @Autowired
    public CommentService(CommentDao commentDao) {
        this.commentDao = commentDao;
    }

    public void add(Comment comment){
        comment.set_id(idWorker.nextId()+"");
        commentDao.save(comment);
    }

    public List<Comment> findByArticleid(String articleid){
        return commentDao.findByArticleid(articleid);
    }
}
