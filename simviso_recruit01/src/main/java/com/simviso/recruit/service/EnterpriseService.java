package com.simviso.recruit.service;

import com.simviso.recruit.dao.EnterpriseDAO;
import com.simviso.recruit.pojo.Enterprise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import util.IdWorker;

import java.util.List;

/**
 * @description: simviso_parent
 * @author: AAR
 * @date: 1/26/2019
 * @Email: liujch1996@gmail.com
 */
@Service
public class EnterpriseService {

    @Autowired
    private EnterpriseDAO enterpriseDAO;

    @Autowired
    private IdWorker idWorker;

    public List<Enterprise> hotList(String ishot){
        return enterpriseDAO.findByIshot(ishot);
    }

    public List<Enterprise> findAll(){
        return enterpriseDAO.findAll();
    }
}
