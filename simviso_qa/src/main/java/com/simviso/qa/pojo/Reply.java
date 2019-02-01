package com.simviso.qa.pojo;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @description: simviso_parent
 * @author: AAR
 * @date: 1/31/2019
 * @Email: liujch1996@gmail.com
 */
@Entity
@Table(name = "tb_reply")
@Data
@Accessors(chain = true)
public class Reply {

    @Id
    private String id;
    private String problemid;
    private String content;
    private Date createtime;
    private Date updatetime;
    private String userid;
    private String nickname;
}
