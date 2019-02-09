package com.simviso.search.service;

import com.simviso.search.dao.ArticleDao;
import com.simviso.search.pojo.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import util.IdWorker;

/**
 * @description: simviso_parent
 * @author: AAR
 * @date: 2/3/2019
 * @Email: liujch1996@gmail.com
 */
@Service
public class ArticleService {
    @Autowired
    private ArticleDao articleDao;

    @Autowired
    private IdWorker idWorker;

    public void save(Article article){
        article.setId(idWorker.nextId()+"");
        articleDao.save(article);
    }

    public Page<Article> findByKey(String key, int page, int size) {
        Pageable pageable = PageRequest.of(page-1, size);
        return articleDao.findByTitleOrContentLike(key, key, pageable);
    }
}
