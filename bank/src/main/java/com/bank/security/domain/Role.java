package com.bank.security.domain;

import java.io.Serializable;

/**
 * @classDesc: 角色模型
 * @author: Vipin Zheng
 * @createDate: 2018-04-17 17:32:41
 * @version: v1.0
 */
public class Role implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private RoleName name;

    public Role() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RoleName getName() {
        return name;
    }

    public void setName(RoleName name) {
        this.name = name;
    }
}
