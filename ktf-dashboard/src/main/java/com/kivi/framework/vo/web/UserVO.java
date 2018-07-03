package com.kivi.framework.vo.web;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserVO implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private Long              id;

    private String            avatar;

    private String            type;

    private String            account;

    private String            password;

    private String            salt;

    private String            name;

    private Date              birthday;

    private String            sex;

    private String            email;

    private String            phone;

    private String            roleId;

    private Long              deptId;

    private Short             status;

    private Date              gmtCreate;

    private Date              gmtUpdate;

    private Integer           version;

    private Set<Long>         roles;

}
