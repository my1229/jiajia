package com.rfw.common.base.models;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * 基类，继承自{@link BasicGenericModel} ，扩展ID
 * 
 */
@MappedSuperclass
public class BasicModel extends BasicGenericModel implements Serializable {
    /**
     * 主键自增长
     */
    @Id
    @GeneratedValue
    public Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
