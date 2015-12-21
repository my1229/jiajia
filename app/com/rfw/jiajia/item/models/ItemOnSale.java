/**    
 * @author mlc  
 * @version 1.0  
 *
 * 2015年7月24日   
 */
package com.rfw.jiajia.item.models;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.rfw.common.base.models.BasicModel;

//@Entity(name = ItemOnSale.TABLE_NAME)
public class ItemOnSale extends BasicModel {

	public static final String TABLE_NAME = "item_onsale";
	@Id
	@GeneratedValue
	private Long id;

	/**
	 * 用户账号
	 */
	private String account;

	/**
	 * 宝贝Id
	 */
	private Long numIid;

	/**
	 * 原宝贝numIId
	 * 
	 */
	private Long oldNumIid;

	/**
	 * 发布时间
	 */
	private Long publishTime;

	/**
	 * 上传至淘宝 or 美丽说 or 阿里巴巴 {@link AppConfigs.ThirdPartySource}
	 */
	private Integer source;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public Long getNumIid() {
		return numIid;
	}

	public void setNumIid(Long numIid) {
		this.numIid = numIid;
	}

	public Long getPublishTime() {
		return publishTime;
	}

	public void setPublishTime(Long publishTime) {
		this.publishTime = publishTime;
	}

	public Integer getSource() {
		return source;
	}

	public void setSource(Integer source) {
		this.source = source;
	}

	public Long getOldNumIid() {
		return oldNumIid;
	}

	public void setOldNumIid(Long oldNumIid) {
		this.oldNumIid = oldNumIid;
	}

}
