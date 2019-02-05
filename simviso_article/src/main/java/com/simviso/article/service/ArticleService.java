package com.simviso.article.service;

import com.simviso.article.dao.ArticleDao;
import com.simviso.article.pojo.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import util.IdWorker;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 服务层
 *
 * @author Administrator
 */
@Service
public class ArticleService {

    private final ArticleDao articleDao;

    private final IdWorker idWorker;

    private final RedisTemplate redisTemplate;

    @Autowired
    public ArticleService(ArticleDao articleDao, IdWorker idWorker, RedisTemplate redisTemplate) {
        this.articleDao = articleDao;
        this.idWorker = idWorker;
        this.redisTemplate = redisTemplate;
    }

    public void updateState(String id){
        articleDao.updateState(id);
    }

    public void addThumbup(String id){
        articleDao.addThumbup(id);
    }
    /**
     * 查询全部列表
     *
     * @return
     */
    public List<Article> findAll() {
        return articleDao.findAll();
    }


    /**
     * 条件查询+分页
     *
     * @param whereMap
     * @param page
     * @param size
     * @return
     */
    public Page<Article> findSearch(Map whereMap, int page, int size) {
        Specification<Article> specification = createSpecification(whereMap);
        PageRequest pageRequest = PageRequest.of(page - 1, size);
        return articleDao.findAll(specification, pageRequest);
    }


    /**
     * 条件查询
     *
     * @param whereMap
     * @return
     */
    public List<Article> findSearch(Map whereMap) {
        Specification<Article> specification = createSpecification(whereMap);
        return articleDao.findAll(specification);
    }

    /**
     * 根据ID查询实体
     *
     * @param id
     * @return
     */
    public Article findById(String id) {
        Article article = (Article) redisTemplate.opsForValue().get("article_" + id);
        if (article == null){
            article = articleDao.findById(id).get();
            redisTemplate.opsForValue().set("article_" + id,article);
        }
        return article;
    }

    /**
     * 增加
     *
     * @param article
     */
    public void add(Article article) {
        article.setId(idWorker.nextId() + "");
        articleDao.save(article);
    }

    /**
     * 修改
     *
     * @param article
     */
    public void update(Article article) {
        redisTemplate.delete("article_"+article.getId());
        articleDao.save(article);
    }

    /**
     * 删除
     *
     * @param id
     */
    public void deleteById(String id) {
        redisTemplate.delete("article_"+ id);
        articleDao.deleteById(id);
    }

    /**
     * 动态条件构建
     *
     * @param searchMap
     * @return
     */
    private Specification<Article> createSpecification(Map searchMap) {

        return (root, query, cb) -> {
            List<Predicate> predicateList = new ArrayList<Predicate>();
            if (searchMap.get("id") != null && !"".equals(searchMap.get("id"))) {
                predicateList.add(cb.like(root.get("id").as(String.class), "%" + (String) searchMap.get("id") + "%"));
            }

            if (searchMap.get("columnid") != null && !"".equals(searchMap.get("columnid"))) {
                predicateList.add(cb.like(root.get("columnid").as(String.class), "%" + (String) searchMap.get("columnid") + "%"));
            }
            if (searchMap.get("userid") != null && !"".equals(searchMap.get("userid"))) {
                predicateList.add(cb.like(root.get("userid").as(String.class), "%" + (String) searchMap.get("userid") + "%"));
            }
            if (searchMap.get("title") != null && !"".equals(searchMap.get("title"))) {
                predicateList.add(cb.like(root.get("title").as(String.class), "%" + (String) searchMap.get("title") + "%"));
            }
            if (searchMap.get("content") != null && !"".equals(searchMap.get("content"))) {
                predicateList.add(cb.like(root.get("content").as(String.class), "%" + (String) searchMap.get("content") + "%"));
            }
            if (searchMap.get("image") != null && !"".equals(searchMap.get("image"))) {
                predicateList.add(cb.like(root.get("image").as(String.class), "%" + (String) searchMap.get("image") + "%"));
            }
            if (searchMap.get("ispublic") != null && !"".equals(searchMap.get("ispublic"))) {
                predicateList.add(cb.like(root.get("ispublic").as(String.class), "%" + (String) searchMap.get("ispublic") + "%"));
            }
            if (searchMap.get("istop") != null && !"".equals(searchMap.get("istop"))) {
                predicateList.add(cb.like(root.get("istop").as(String.class), "%" + (String) searchMap.get("istop") + "%"));
            }
            if (searchMap.get("state") != null && !"".equals(searchMap.get("state"))) {
                predicateList.add(cb.like(root.get("state").as(String.class), "%" + (String) searchMap.get("state") + "%"));
            }
            if (searchMap.get("channelid") != null && !"".equals(searchMap.get("channelid"))) {
                predicateList.add(cb.like(root.get("channelid").as(String.class), "%" + (String) searchMap.get("channelid") + "%"));
            }
            if (searchMap.get("url") != null && !"".equals(searchMap.get("url"))) {
                predicateList.add(cb.like(root.get("url").as(String.class), "%" + (String) searchMap.get("url") + "%"));
            }
            if (searchMap.get("type") != null && !"".equals(searchMap.get("type"))) {
                predicateList.add(cb.like(root.get("type").as(String.class), "%" + (String) searchMap.get("type") + "%"));
            }
            return cb.and(predicateList.toArray(new Predicate[predicateList.size()]));

        };

    }

    public void examine(String id) {
        articleDao.updateState(id);
    }
}
