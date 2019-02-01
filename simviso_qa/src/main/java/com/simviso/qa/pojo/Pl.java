package com.simviso.qa.pojo;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @description: simviso_parent
 * @author: AAR
 * @date: 1/31/2019
 * @Email: liujch1996@gmail.com
 */
@Entity
@Table(name = "tb_pl")
@Data
@Accessors(chain = true)
public class Pl implements Serializable {

    @Id
    private String problemid;

    @Id
    private String labelid;
}
