package com.simviso.search.dao;

import com.simviso.search.pojo.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @description: simviso_parent
 * @author: AAR
 * @date: 2/3/2019
 * @Email: liujch1996@gmail.com
 */
public interface ArticleDao extends ElasticsearchRepository<Article,String> {

    Page<Article> findByTitleOrContentLike(String title, String content, Pageable pageable);
}
