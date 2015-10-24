package com.rfw.common.base.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * 基类，包含插入字段和更新字段
 * 
 */
@MappedSuperclass
public class BasicGenericModel implements Serializable {
    /**
     * 插入时的时间戳
     */

    @Column(name = "add_ts", columnDefinition = " TIMESTAMP  NOT NULL DEFAULT 0 ")
    public Date addTs;

    /**
     * 更新时的时间戳
     */
    @Column(name = "update_ts", columnDefinition = " TIMESTAMP  NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    public Date updateTs;

    public Date getAddTs() {
        return addTs;
    }

    public void setAddTs(Date addTs) {
        this.addTs = addTs;
    }

    public Date getUpdateTs() {
        return updateTs;
    }

    public void setUpdateTs(Date updateTs) {
        this.updateTs = updateTs;
    }
}
