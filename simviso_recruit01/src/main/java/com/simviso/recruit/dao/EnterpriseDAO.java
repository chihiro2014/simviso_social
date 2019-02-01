package com.simviso.recruit.dao;

import com.simviso.recruit.pojo.Enterprise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * @description: simviso_parent
 * @author: AAR
 * @date: 1/26/2019
 * @Email: liujch1996@gmail.com
 */
public interface EnterpriseDAO extends JpaRepository<Enterprise,String>, JpaSpecificationExecutor<Enterprise> {

    public List<Enterprise> findByIshot(String ishot);

}
