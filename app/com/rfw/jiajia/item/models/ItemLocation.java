/**    
 * @author mlc  
 * @version 1.0  
 * 商品所在地
 * 2015年7月15日   
 */
package com.rfw.jiajia.item.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.Index;

import com.rfw.common.base.models.BasicModel;

@Entity(name = ItemLocation.TABLE_NAME)
public class ItemLocation extends BasicModel {

    public static final String TABLE_NAME = "item_location";

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
     * 城市
     */
    private String city;

    /**
     * 省份
     */
    private String state;

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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

}
