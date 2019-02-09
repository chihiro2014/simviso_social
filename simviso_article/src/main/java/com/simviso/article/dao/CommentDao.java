package com.simviso.article.dao;

import com.simviso.article.pojo.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * @description: simviso_parent
 * @author: AAR
 * @date: 2/2/2019
 * @Email: liujch1996@gmail.com
 */
public interface CommentDao extends MongoRepository<Comment,String> {

    List<Comment> findByArticleid(String articleid);
}
