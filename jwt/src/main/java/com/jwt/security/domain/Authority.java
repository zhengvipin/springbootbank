package com.jwt.security.domain;

import java.io.Serializable;

/**
 * @classDesc: 授权模型
 * @author: Vipin Zheng
 * @createDate: 2018-04-17 02:21:18
 * @version: v1.0
 */
public class Authority implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private AuthorityName name;

    public Authority() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public AuthorityName getName() {
        return name;
    }

    public void setName(AuthorityName name) {
        this.name = name;
    }
}
