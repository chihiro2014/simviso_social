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
@Table(name = "tb_problem")
@Data
@Accessors(chain = true)
public class Problem implements Serializable {

    @Id
    private String id;
    private String title;
    private String content;
    private java.util.Date createtime;
    private java.util.Date updatetime;
    private String userid;
    private String nickname;
    private Long visits;
    private Long thumbup;
    private Long reply;
    private String solve;
    private String replyname;
    private java.util.Date replytime;

}
