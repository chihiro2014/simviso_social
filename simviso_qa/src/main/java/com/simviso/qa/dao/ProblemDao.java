package com.simviso.qa.dao;

import com.simviso.qa.pojo.Problem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

/**
 * @description: simviso_parent
 * @author: AAR
 * @date: 1/31/2019
 * @Email: liujch1996@gmail.com
 */
public interface ProblemDao extends JpaRepository<Problem, String>, JpaSpecificationExecutor<Problem> {

    @Query(value = "SELECT * from tb_problem, tb_pl where id = problemid and labelid = ? ORDER BY replytime DESC",nativeQuery = true)
    Page<Problem> hotList(String labelid, Pageable pageable);

    @Query(nativeQuery = true, value ="SELECT * from tb_problem, tb_pl where id = problemid and labelid = ? ORDER BY reply DESC" )
    Page<Problem> newList(String labelid, Pageable pageable);

    @Query(nativeQuery = true, value = "SELECT * from tb_problem, tb_pl where id = problemid and labelid = ? and reply = 0 ORDER BY replytime DESC")
    Page<Problem> waitList(String labelid, Pageable pageable);
}
