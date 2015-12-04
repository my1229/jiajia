/**    
 * @author mlc  
 * @version 1.0  
 * 商品图片列表
 * 2015年7月15日   
 */
package com.rfw.jiajia.item.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.Index;

import com.rfw.common.base.models.BasicModel;

@Entity(name = ItemImg.TABLE_NAME)
public class ItemImg extends BasicModel {

    public static final String TABLE_NAME = "item_img";

    /**
     * 商品图片Id
     */
    @Id
    @GeneratedValue
    private Long id;

    private Long imgId;

    /**
     * 宝贝ID
     */
    @Index(name = "_index_numIid")
    private Long numIid;

    /**
     * 图片地址
     */
    private String url;

    /**
     * 位置
     */
    private Long position;

    /**
     * 创建时间
     */
    private Long created;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Long getPosition() {
        return position;
    }

    public void setPosition(Long position) {
        this.position = position;
    }

    public Long getNumIid() {
        return numIid;
    }

    public void setNumIid(Long numIid) {
        this.numIid = numIid;
    }

    public Long getCreated() {
        return created;
    }

    public void setCreated(Long created) {
        this.created = created;
    }

    public Long getImgId() {
        return imgId;
    }

    public void setImgId(Long imgId) {
        this.imgId = imgId;
    }

}
