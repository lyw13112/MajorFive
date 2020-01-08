package com.bawei.week2_practice.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

/**
 * @author 刘云蔚
 * @createTime 2020/1/4 9:30
 * @description
 */
@Entity
public class GreenDaoCacheBean {
    String json;

    @Generated(hash = 936881946)
    public GreenDaoCacheBean(String json) {
        this.json = json;
    }

    @Generated(hash = 763619188)
    public GreenDaoCacheBean() {
    }

    public String getJson() {
        return this.json;
    }

    public void setJson(String json) {
        this.json = json;
    }
}
