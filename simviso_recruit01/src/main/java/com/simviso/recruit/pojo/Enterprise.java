package com.simviso.recruit.pojo;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @description: simviso_parent
 * @author: AAR
 * @date: 1/26/2019
 * @Email: liujch1996@gmail.com
 */
@Entity
@Table(name = "tb_enterprise")
@Data
@Accessors(chain = true)
public class Enterprise implements Serializable {

    @Id
    private String id;
    private String name;
    private String summary;
    private String address;
    private String labels;
    private String coordinate;
    private String ishot;
    private String logo;
    private Integer jobcount;
    private String url;
}
