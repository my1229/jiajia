/**    
 * @author mlc  
 * @version 1.0  
 * 商品属性图片
 * 2015年7月15日   
 */
package com.rfw.jiajia.item.models;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.Index;
import org.hibernate.annotations.Index;

import com.rfw.common.base.models.BasicModel;

//@Entity(name = ItemPropImg.TABLE_NAME)
//@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "id" }) })
public class ItemPropImg extends BasicModel {

	public static final String TABLE_NAME = "item_prop_img";
	/**
	 * 图片Id
	 */
	@Id
	@GeneratedValue
	private Long id;
	/**
	 * 图片Id
	 */
	private Long imgId;

	/**
	 * 宝贝Id
	 */
	@Index(name = "_index_numIid")
	private Long numIid;

	/**
	 * 图片地址
	 */
	private String url;

	/**
	 * 属性
	 */
	private String properties;

	/**
	 * 位置
	 */
	private Long position;

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

	public String getProperties() {
		return properties;
	}

	public void setProperties(String properties) {
		this.properties = properties;
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

	public Long getImgId() {
		return imgId;
	}

	public void setImgId(Long imgId) {
		this.imgId = imgId;
	}

}
