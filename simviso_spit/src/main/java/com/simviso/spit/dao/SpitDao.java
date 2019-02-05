package com.simviso.spit.dao;

import com.simviso.spit.pojo.Spit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @description: simviso_parent
 * @author: AAR
 * @date: 2/2/2019
 * @Email: liujch1996@gmail.com
 */
public interface SpitDao extends MongoRepository<Spit,String> {
    Page<Spit> findByParentid(String parentid, Pageable pageable);
}
