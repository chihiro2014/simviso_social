package com.simviso.recruit.dao;

import com.simviso.recruit.pojo.Recruit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * @description: simviso_parent
 * @author: AAR
 * @date: 1/26/2019
 * @Email: liujch1996@gmail.com
 */
public interface RecruitDao extends JpaRepository<Recruit,String>, JpaSpecificationExecutor<Recruit> {

    public List<Recruit> findTop4ByStateOrderByCreatetimeAsc(String state);

    public List<Recruit> findTop12ByStateNotOrderByCreatetimeDesc(String state);
}
