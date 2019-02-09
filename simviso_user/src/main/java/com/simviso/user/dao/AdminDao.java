package com.simviso.user.dao;

import com.simviso.user.pojo.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @description: simviso_parent
 * @author: AAR
 * @date: 2/9/2019
 * @Email: liujch1996@gmail.com
 */
public interface AdminDao extends JpaRepository<Admin,String>, JpaSpecificationExecutor<Admin> {

}
