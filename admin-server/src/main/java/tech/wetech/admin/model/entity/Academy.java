package tech.wetech.admin.model.entity;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "sys_academy")
@Data
public class Academy {

    /**
     * 编号
     */
    @Id
    @GeneratedValue
    private Long id;
    /**
     * 用户名
     */
    private String name;

    /**
     * 拥有的角色列表
     */
    private String classIds;

    private Long NumOfStu;
    private Long NumOfTea;
    private Integer locked;


}
