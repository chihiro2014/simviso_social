package com.simviso.article.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.simviso.article.pojo.Article;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * 数据访问接口
 * @author Administrator
 *
 */
public interface ArticleDao extends JpaRepository<Article,String>,JpaSpecificationExecutor<Article>{

    @Modifying
    @Query(value = "UPDATE tb_article set state = 1 where id = ?",nativeQuery = true)
    void updateState(String id);

    @Modifying
    @Query(value = "UPDATE tb_article set thumbup = thumbup+1 where id = ?",nativeQuery = true)
    void addThumbup(String id);


}
