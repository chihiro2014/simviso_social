package com.simviso.recruit.service;

import com.simviso.recruit.dao.RecruitDao;
import com.simviso.recruit.pojo.Recruit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import util.IdWorker;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @description: simviso_parent
 * @author: AAR
 * @date: 1/30/2019
 * @Email: liujch1996@gmail.com
 */
@Service
public class RecruitService {

    @Autowired
    private RecruitDao recruitDao;

    @Autowired
    private IdWorker idWorker;

    public List<Recruit> findAll(){
        return recruitDao.findAll();
    }


    /**
     * @param whereMap
     * @param page
     * @param size
     * @return
     */
    public Page<Recruit> findSearch(Map whereMap,int page, int size){
        Specification<Recruit> specification = createSpecification(whereMap);
        PageRequest pageRequest =  PageRequest.of(page-1, size);
        return recruitDao.findAll(specification, pageRequest);
    }

    /**
     * 分页查询
     * @param whereMap
     * @return
     */
    public List<Recruit> findSearch(Map whereMap){
        Specification<Recruit> specification = createSpecification(whereMap);
        return recruitDao.findAll(specification);
    }

    /**
     * 根据ID查询
     * @param id
     * @return
     */
    public Recruit findById(String id){
        return recruitDao.findById(id).get();
    }

    /**
     * 添加
     * @param recruit
     */
    public void add(Recruit recruit){
        recruit.setId(idWorker.nextId()+"");
        recruitDao.save(recruit);
    }

    /**
     * 修改
     * @param recruit
     */
    public void update(Recruit recruit){
        recruitDao.save(recruit);
    }

    /**
     * 根据ID删除
     * @param id
     */
    public void deleteById(String id){
        recruitDao.deleteById(id);
    }

    private Specification<Recruit> createSpecification(Map whereMap) {

        return new Specification<Recruit>() {
            @Override
            public Predicate toPredicate(Root<Recruit> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();
                // ID
                if (whereMap.get("id")!=null && !"".equals(whereMap.get("id"))) {
                    predicates.add(cb.like(root.get("id").as(String.class), "%"+(String)whereMap.get("id")+"%"));
                }
                // 职位名称
                if (whereMap.get("jobname")!=null && !"".equals(whereMap.get("jobname"))) {
                    predicates.add(cb.like(root.get("jobname").as(String.class), "%"+(String)whereMap.get("jobname")+"%"));
                }
                // 薪资范围
                if (whereMap.get("salary")!=null && !"".equals(whereMap.get("salary"))) {
                    predicates.add(cb.like(root.get("salary").as(String.class), "%"+(String)whereMap.get("salary")+"%"));
                }
                // 经验要求
                if (whereMap.get("condition")!=null && !"".equals(whereMap.get("condition"))) {
                    predicates.add(cb.like(root.get("condition").as(String.class), "%"+(String)whereMap.get("condition")+"%"));
                }
                // 学历要求
                if (whereMap.get("education")!=null && !"".equals(whereMap.get("education"))) {
                    predicates.add(cb.like(root.get("education").as(String.class), "%"+(String)whereMap.get("education")+"%"));
                }
                // 任职方式
                if (whereMap.get("type")!=null && !"".equals(whereMap.get("type"))) {
                    predicates.add(cb.like(root.get("type").as(String.class), "%"+(String)whereMap.get("type")+"%"));
                }
                // 办公地址
                if (whereMap.get("address")!=null && !"".equals(whereMap.get("address"))) {
                    predicates.add(cb.like(root.get("address").as(String.class), "%"+(String)whereMap.get("address")+"%"));
                }
                // 企业ID
                if (whereMap.get("eid")!=null && !"".equals(whereMap.get("eid"))) {
                    predicates.add(cb.like(root.get("eid").as(String.class), "%"+(String)whereMap.get("eid")+"%"));
                }
                // 状态
                if (whereMap.get("state")!=null && !"".equals(whereMap.get("state"))) {
                    predicates.add(cb.like(root.get("state").as(String.class), "%"+(String)whereMap.get("state")+"%"));
                }
                // 网址
                if (whereMap.get("url")!=null && !"".equals(whereMap.get("url"))) {
                    predicates.add(cb.like(root.get("url").as(String.class), "%"+(String)whereMap.get("url")+"%"));
                }
                // 标签
                if (whereMap.get("label")!=null && !"".equals(whereMap.get("label"))) {
                    predicates.add(cb.like(root.get("label").as(String.class), "%"+(String)whereMap.get("label")+"%"));
                }
                // 职位描述
                if (whereMap.get("content1")!=null && !"".equals(whereMap.get("content1"))) {
                    predicates.add(cb.like(root.get("content1").as(String.class), "%"+(String)whereMap.get("content1")+"%"));
                }
                // 职位要求
                if (whereMap.get("content2")!=null && !"".equals(whereMap.get("content2"))) {
                    predicates.add(cb.like(root.get("content2").as(String.class), "%"+(String)whereMap.get("content2")+"%"));
                }

                return cb.and( predicates.toArray(new Predicate[predicates.size()]));
            }
        };
    }

    public List<Recruit> findTop4ByStateOrderByCreatetimeDesc(String state){
        return recruitDao.findTop4ByStateOrderByCreatetimeAsc(state);
    }

    public List<Recruit> newlist(){
        return recruitDao.findTop12ByStateNotOrderByCreatetimeDesc("0");
    }
}
