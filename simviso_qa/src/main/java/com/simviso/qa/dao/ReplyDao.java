package com.simviso.qa.dao;

import com.simviso.qa.pojo.Reply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @description: simviso_parent
 * @author: AAR
 * @date: 1/31/2019
 * @Email: liujch1996@gmail.com
 */
public interface ReplyDao extends JpaSpecificationExecutor<Reply>, JpaRepository<Reply,String> {
}
