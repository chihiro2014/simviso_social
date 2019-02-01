package com.simviso.base.dao;

import com.simviso.base.pojo.Label;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @description: simviso_parent
 * @author: AAR
 * @date: 1/24/2019
 * @Email: liujch1996@gmail.com
 */
public interface LabelDao extends JpaRepository<Label, String>, JpaSpecificationExecutor<Label> {}
