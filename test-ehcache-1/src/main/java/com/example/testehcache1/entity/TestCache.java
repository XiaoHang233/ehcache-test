package com.example.testehcache1.entity;


import com.example.basecache.model.CacheBean;

import javax.persistence.*;


/**
 * Created by zenghang on 2019/3/28.
 */
@Entity
@Table(name = "TEST_CACHE")
public class TestCache extends CacheBean {
    @Id
    @GeneratedValue
    @Column(name = "T_ID")
    private Long id;
    @Column(name = "T_NAME")
    private String name;
    @Column(name = "T_VALUE")
    private String value;
    @Column(name = "T_DESC")
    private String desc;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
