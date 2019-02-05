package com.simviso.qa.service;

import com.simviso.qa.dao.ProblemDao;
import com.simviso.qa.pojo.Problem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
public class ProblemService {

    private final ProblemDao problemDao;

    private final IdWorker idWorker;

    @Autowired
    public ProblemService(ProblemDao problemDao, IdWorker idWorker) {
        this.problemDao = problemDao;
        this.idWorker = idWorker;
    }


    public Page<Problem> newList(String labelid, int page, int rows){
        Pageable pageable = PageRequest.of(page-1,rows);
        return problemDao.newList(labelid,pageable);
    }


    public Page<Problem> hotList(String labelid, int page, int rows){
        Pageable pageable = PageRequest.of(page-1,rows);
        return problemDao.hotList(labelid,pageable);
    }

    public Page<Problem> waitList(String labelid, int page, int rows){
        Pageable pageable = PageRequest.of(page-1,rows);
        return problemDao.waitList(labelid,pageable);
    }

    public List<Problem> findAll(){
        return problemDao.findAll();
    }

    public void add(Problem problem){
        problem.setId(idWorker.nextId()+"");
        problemDao.save(problem);
    }

    public void update(Problem problem){
        problemDao.save(problem);
    }

    public void deleteById(String id){
        problemDao.deleteById(id);
    }

    public Problem findById(String id){
        return problemDao.findById(id).get();
    }

    public List<Problem> findSearch(Map whereMap) {
        Specification<Problem> specification = createSpecification(whereMap);
        return problemDao.findAll(specification);
    }



    private Specification<Problem> createSpecification(Map searchMap) {

        return (root, query, cb) -> {
            List<Predicate> predicateList = new ArrayList<Predicate>();
            // ID
            if (searchMap.get("id")!=null && !"".equals(searchMap.get("id"))) {
                predicateList.add(cb.like(root.get("id").as(String.class), "%"+(String)searchMap.get("id")+"%"));
            }
            // 标题
            if (searchMap.get("title")!=null && !"".equals(searchMap.get("title"))) {
                predicateList.add(cb.like(root.get("title").as(String.class), "%"+(String)searchMap.get("title")+"%"));
            }
            // 内容
            if (searchMap.get("content")!=null && !"".equals(searchMap.get("content"))) {
                predicateList.add(cb.like(root.get("content").as(String.class), "%"+(String)searchMap.get("content")+"%"));
            }
            // 用户ID
            if (searchMap.get("userid")!=null && !"".equals(searchMap.get("userid"))) {
                predicateList.add(cb.like(root.get("userid").as(String.class), "%"+(String)searchMap.get("userid")+"%"));
            }
            // 昵称
            if (searchMap.get("nickname")!=null && !"".equals(searchMap.get("nickname"))) {
                predicateList.add(cb.like(root.get("nickname").as(String.class), "%"+(String)searchMap.get("nickname")+"%"));
            }
            // 是否解决
            if (searchMap.get("solve")!=null && !"".equals(searchMap.get("solve"))) {
                predicateList.add(cb.like(root.get("solve").as(String.class), "%"+(String)searchMap.get("solve")+"%"));
            }
            // 回复人昵称
            if (searchMap.get("replyname")!=null && !"".equals(searchMap.get("replyname"))) {
                predicateList.add(cb.like(root.get("replyname").as(String.class), "%"+(String)searchMap.get("replyname")+"%"));
            }

            return cb.and( predicateList.toArray(new Predicate[predicateList.size()]));

        };

    }


    public Page<Problem> findSearch(Map searchMap, int page, int size) {
        Specification<Problem> specification = createSpecification(searchMap);
        PageRequest pageRequest =  PageRequest.of(page-1, size);
        return problemDao.findAll(specification, pageRequest);
    }
}
