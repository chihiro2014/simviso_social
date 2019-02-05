package com.simviso.qa.service;

import com.simviso.qa.dao.ReplyDao;
import com.simviso.qa.pojo.Reply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import util.IdWorker;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @description: simviso_parent
 * @author: AAR
 * @date: 1/31/2019
 * @Email: liujch1996@gmail.com
 */
@Service
public class ReplyService {

    @Autowired
    private ReplyDao replyDao;

    @Autowired
    private IdWorker idWorker;

    public List<Reply> findAll() {
        return replyDao.findAll();
    }

    public Page<Reply> findSearch(Map whereMap, int page, int size) {
        Specification<Reply> specification = createSpecification(whereMap);
        PageRequest pageRequest =  PageRequest.of(page-1, size);
        return replyDao.findAll(specification, pageRequest);
    }

    public List<Reply> findSearch(Map whereMap) {
        Specification<Reply> specification = createSpecification(whereMap);
        return replyDao.findAll(specification);
    }

    public Reply findById(String id) {
        return replyDao.findById(id).get();
    }


    public void add(Reply reply) {
        reply.setId( idWorker.nextId()+"" );
        replyDao.save(reply);
    }

    public void update(Reply reply) {
        replyDao.save(reply);
    }

    public void deleteById(String id) {
        replyDao.deleteById(id);
    }


    private Specification<Reply> createSpecification(Map searchMap) {

        return (root, query, cb) -> {
            List<Predicate> predicateList = new ArrayList<Predicate>();
            // 编号
            if (searchMap.get("id")!=null && !"".equals(searchMap.get("id"))) {
                predicateList.add(cb.like(root.get("id").as(String.class), "%"+(String)searchMap.get("id")+"%"));
            }
            // 问题ID
            if (searchMap.get("problemid")!=null && !"".equals(searchMap.get("problemid"))) {
                predicateList.add(cb.like(root.get("problemid").as(String.class), "%"+(String)searchMap.get("problemid")+"%"));
            }
            // 回答内容
            if (searchMap.get("content")!=null && !"".equals(searchMap.get("content"))) {
                predicateList.add(cb.like(root.get("content").as(String.class), "%"+(String)searchMap.get("content")+"%"));
            }
            // 回答人ID
            if (searchMap.get("userid")!=null && !"".equals(searchMap.get("userid"))) {
                predicateList.add(cb.like(root.get("userid").as(String.class), "%"+(String)searchMap.get("userid")+"%"));
            }
            // 回答人昵称
            if (searchMap.get("nickname")!=null && !"".equals(searchMap.get("nickname"))) {
                predicateList.add(cb.like(root.get("nickname").as(String.class), "%"+(String)searchMap.get("nickname")+"%"));
            }

            return cb.and( predicateList.toArray(new Predicate[predicateList.size()]));

        };

    }
}
