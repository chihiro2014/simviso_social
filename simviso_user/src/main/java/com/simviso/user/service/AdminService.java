package com.simviso.user.service;

import com.simviso.user.dao.AdminDao;
import com.simviso.user.pojo.Admin;
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
 * @date: 2/9/2019
 * @Email: liujch1996@gmail.com
 */
@Service
public class AdminService {

    private final AdminDao adminDao;

    private final IdWorker idWorker;

    @Autowired
    public AdminService(AdminDao adminDao, IdWorker idWorker) {
        this.adminDao = adminDao;
        this.idWorker = idWorker;
    }

    public List<Admin> findAll() {
        return adminDao.findAll();
    }

    public Page<Admin> findSearch(Map whereMap, int page, int size) {
        Specification<Admin> specification = createSpecification(whereMap);
        PageRequest pageRequest =  PageRequest.of(page-1, size);
        return adminDao.findAll(specification, pageRequest);
    }

    public List<Admin> findSearch(Map whereMap) {
        Specification<Admin> specification = createSpecification(whereMap);
        return adminDao.findAll(specification);
    }

    public Admin findById(String id) {
        return adminDao.findById(id).get();
    }

    public void add(Admin admin) {
        admin.setId( idWorker.nextId()+"" );
        adminDao.save(admin);
    }

    public void update(Admin admin) {
        adminDao.save(admin);
    }

    public void deleteById(String id) {
        adminDao.deleteById(id);
    }

    private Specification<Admin> createSpecification(Map searchMap) {

        return (root, query, cb) -> {
            List<Predicate> predicateList = new ArrayList<Predicate>();
            // ID
            if (searchMap.get("id")!=null && !"".equals(searchMap.get("id"))) {
                predicateList.add(cb.like(root.get("id").as(String.class), "%"+(String)searchMap.get("id")+"%"));
            }
            // 登陆名称
            if (searchMap.get("loginname")!=null && !"".equals(searchMap.get("loginname"))) {
                predicateList.add(cb.like(root.get("loginname").as(String.class), "%"+(String)searchMap.get("loginname")+"%"));
            }
            // 密码
            if (searchMap.get("password")!=null && !"".equals(searchMap.get("password"))) {
                predicateList.add(cb.like(root.get("password").as(String.class), "%"+(String)searchMap.get("password")+"%"));
            }
            // 状态
            if (searchMap.get("state")!=null && !"".equals(searchMap.get("state"))) {
                predicateList.add(cb.like(root.get("state").as(String.class), "%"+(String)searchMap.get("state")+"%"));
            }

            return cb.and( predicateList.toArray(new Predicate[predicateList.size()]));

        };

    }
}
