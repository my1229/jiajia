/**    
 * @author mlc  
 * @version 1.0  
 * 商品描述
 * 2015年7月15日   
 */
package com.rfw.jiajia.item.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.Index;

import com.rfw.common.base.models.BasicModel;

@Entity(name = ItemDescr.TBALE_NAME)
public class ItemDescr extends BasicModel {

    public static final String TBALE_NAME = "item_descr";

    @Id
    @GeneratedValue
    private Long id;

    /**
     * 宝贝Id
     */
    @Index(name = "_index_numIid")
    @Column(unique = true)
    private Long numIid;

    /**
     * 宝贝描述
     */
    @Column(columnDefinition = "longtext")
    private String descr;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNumIid() {
        return numIid;
    }

    public void setNumIid(Long numIid) {
        this.numIid = numIid;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

}
