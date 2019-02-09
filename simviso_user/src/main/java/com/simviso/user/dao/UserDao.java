package com.simviso.user.dao;

import com.simviso.user.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @description: simviso_parent
 * @author: AAR
 * @date: 2/9/2019
 * @Email: liujch1996@gmail.com
 */
public interface UserDao extends JpaRepository<User,String>, JpaSpecificationExecutor<User> {
}
