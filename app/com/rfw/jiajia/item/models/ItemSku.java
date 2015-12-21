/**    
 * @author mlc  
 * @version 1.0  
 *
 * 2015年7月15日   
 */
package com.rfw.jiajia.item.models;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.Index;

import com.rfw.common.base.models.BasicModel;

@JsonIgnoreProperties(value = { "barCode", "entityId", "created", "gmtCreated", "gmtModified", "id", "modified",
		"outerId", "persistent" })
// @Entity(name = ItemSku.TBABLE_NAME)
public class ItemSku extends BasicModel {

	public static final String TBABLE_NAME = "item_sku";

	/**
	 * skuId
	 */
	@Id
	@GeneratedValue
	private Long Id;

	private Long skuId;

	/**
	 * 宝贝Id
	 */
	@Index(name = "_index_numIid")
	private Long numIid;

	/**
	 * 属性
	 */
	private String properties;

	/**
	 * 属于这个sku的商品数量
	 */
	private Long quantity;

	/**
	 * 属于这个sku商品的价格
	 */
	private String price;

	/**
	 * 创建时间
	 */
	private String created;

	/**
	 * 编辑时间
	 */
	private String modified;

	/**
	 * sku所对应的销售属性的中文名字串
	 */
	private String propertiesName;

	/**
	 * 商家设置的外部id
	 */
	private String outerId;

	/**
	 * 商品级别的条形码
	 */
	private String barCode;

	public Long getSkuId() {
		return skuId;
	}

	public void setSkuId(Long skuId) {
		this.skuId = skuId;
	}

	public Long getNumIid() {
		return numIid;
	}

	public void setNumIid(Long numIid) {
		this.numIid = numIid;
	}

	public String getProperties() {
		return properties;
	}

	public void setProperties(String properties) {
		this.properties = properties;
	}

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getCreated() {
		return created;
	}

	public void setCreated(String created) {
		this.created = created;
	}

	public String getModified() {
		return modified;
	}

	public void setModified(String modified) {
		this.modified = modified;
	}

	public String getPropertiesName() {
		return propertiesName;
	}

	public void setPropertiesName(String propertiesName) {
		this.propertiesName = propertiesName;
	}

	public String getOuterId() {
		return outerId;
	}

	public void setOuterId(String outerId) {
		this.outerId = outerId;
	}

	public String getBarCode() {
		return barCode;
	}

	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

}
