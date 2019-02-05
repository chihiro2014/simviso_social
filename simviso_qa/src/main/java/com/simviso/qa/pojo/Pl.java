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
public class Pl implements Serializable {

    @Id
    private String problemid;

    @Id
    private String labelid;

    public String getProblemid() {
        return problemid;
    }

    public void setProblemid(String problemid) {
        this.problemid = problemid;
    }

    public String getLabelid() {
        return labelid;
    }

    public void setLabelid(String labelid) {
        this.labelid = labelid;
    }
}
