package com.simviso.qa.service;

import com.simviso.qa.dao.ProblemDao;
import com.simviso.qa.pojo.Problem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import util.IdWorker;

import java.util.List;

/**
 * @description: simviso_parent
 * @author: AAR
 * @date: 1/31/2019
 * @Email: liujch1996@gmail.com
 */
@Service
public class ProblemService {

    @Autowired
    private ProblemDao problemDao;

    @Autowired
    private IdWorker idWorker;

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

    }
}
