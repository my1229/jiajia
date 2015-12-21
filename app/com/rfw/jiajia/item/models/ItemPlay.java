/**
 * @author mlc
 * @date 2015年7月14日
 * @version 1.0
 */
package com.rfw.jiajia.item.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.apache.solr.client.solrj.beans.Field;
import org.hibernate.annotations.Index;

//@Entity(name = ItemPlay.TABLE_NAME)
//@Table(uniqueConstraints = { @UniqueConstraint(columnNames = "numIid") })
public class ItemPlay {
	public static final String TABLE_NAME = "item";

	/**
	 * numIid
	 */
	@Id
	@GeneratedValue
	@Field("id_l")
	private Long id;

	/**
	 * 插入时的时间戳
	 */

	@Column(columnDefinition = "  TIMESTAMP  DEFAULT '0000-00-00 00:00:00' ")
	@Field("gmtCreated_dt")
	private Date gmtCreated;

	/**
	 * 更新时的时间戳
	 */
	@Column(columnDefinition = " TIMESTAMP  NOT NULL DEFAULT now() ON UPDATE now()")
	@Field("gmtModified_dt")
	private Date gmtModified;

	@Field("numIid_l")
	@Index(name = "_index_numIid")
	private Long numIid;

	/**
	 * 卖家昵称
	 */
	@Field("nick_s")
	@Index(name = "_index_nick")
	private String nick;

	/**
	 * 标题
	 */
	@Field("title_s")
	private String title;

	/**
	 * 图片地址
	 */
	@Field("picUrl_s")
	private String picUrl;

	/**
	 * 商家外部编码
	 */
	@Field("outerId_s")
	private String outerId;

	/**
	 * 类目
	 */
	@Field("cid_l")
	private Long cid;

	/**
	 * 价格
	 */
	@Field("price_d")
	@Column(columnDefinition = "double default 0")
	private Double price;

	/**
	 * 上架状态
	 */
	@Field("approveStatus_i")
	private int approveStatus;

	/**
	 * 上架时间
	 */
	@Field("listTime_l")
	private Long listTime;

	/**
	 * 下架时间
	 */
	@Field("deListTime_l")
	private Long deListTime;

	/**
	 * 创建时间
	 */
	@Field("created_l")
	private Long created;

	/**
	 * 最后修改时间
	 */
	private Long modified;

	/**
	 * 库存
	 */
	private Long num;

	/**
	 * 是否橱窗
	 */
	@Field("hasShowCase_b")
	private Boolean hasShowCase;

	/**
	 * 商品属性
	 */
	@Column(columnDefinition = "longtext")
	private String propsName;

	/**
	 * 卖家自定义类目
	 */
	@Field("sellerCids_s")
	private String sellerCids;

	/**
	 * 宝贝所属的运费模板ID，如果没有返回则说明没有使用运费模板
	 */
	private Long postageId;

	/**
	 * 运费承担方式,1（卖家承担），2(买家承担）
	 */
	private int freightPayer;

	/**
	 * 平邮费用
	 */
	private double postFee;

	/**
	 * ems费用
	 */
	private double emsFee;

	/**
	 * 快递费用
	 */
	private double expressFee;

	/**
	 * 批发价
	 */
	@Field("tradePrice_d")
	private Double tradePrice;

	/**
	 * 差价
	 */
	@Field("reducePrice_d")
	private Double reducePrice = 0.0;

	/**
	 * 本地上下架状态
	 */
	@Field("localApproveStatus_i")
	@Column(columnDefinition = "int default 0")
	private Integer localApproveStatus;

	/**
	 * 标记是否可以删除
	 * 
	 */
	@Column(columnDefinition = "int default 0")
	@Field("isDelete_i")
	private Integer isDelete;

	/**
	 * 标记该宝贝卖家是否已授权
	 */
	@Column(columnDefinition = "int default 0")
	@Field("isOauth_i")
	private Integer isOauth;

	@Column(columnDefinition = "int default 0")
	@Field("isShowCase_i")
	private Integer isShowCase;

	/**
	 * 需要确认价格
	 */
	@Column(columnDefinition = "int default 0")
	private Integer needCheckPrice = 0;

	public ItemPlay() {

	}

	public ItemPlay(long numIid, String nick) {
		this.numIid = numIid;
		this.nick = nick;
	}

	public Long getNumIid() {
		return numIid;
	}

	public void setNumIid(Long numIid) {
		this.numIid = numIid;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	public String getOuterId() {
		return outerId;
	}

	public void setOuterId(String outerId) {
		this.outerId = outerId;
	}

	public Long getCid() {
		return cid;
	}

	public void setCid(Long cid) {
		this.cid = cid;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Long getListTime() {
		return listTime;
	}

	public void setListTime(Long listTime) {
		this.listTime = listTime;
	}

	public Long getDeListTime() {
		return deListTime;
	}

	public void setDeListTime(Long deListTime) {
		this.deListTime = deListTime;
	}

	public Long getModified() {
		return modified;
	}

	public void setModified(Long modified) {
		this.modified = modified;
	}

	public Long getNum() {
		return num;
	}

	public void setNum(Long num) {
		this.num = num;
	}

	public Boolean isHasShowCase() {
		return hasShowCase;
	}

	public void setHasShowCase(Boolean hasShowCase) {
		this.hasShowCase = hasShowCase;
	}

	public String getSellerCids() {
		return sellerCids;
	}

	public void setSellerCids(String sellerCids) {
		this.sellerCids = sellerCids;
	}

	public Long getPostageId() {
		return postageId;
	}

	public void setPostageId(Long postageId) {
		this.postageId = postageId;
	}

	public int getFreightPayer() {
		return freightPayer;
	}

	public void setFreightPayer(int freightPayer) {
		this.freightPayer = freightPayer;
	}

	public double getPostFee() {
		return postFee;
	}

	public void setPostFee(double postFee) {
		this.postFee = postFee;
	}

	public double getEmsFee() {
		return emsFee;
	}

	public void setEmsFee(double emsFee) {
		this.emsFee = emsFee;
	}

	public double getExpressFee() {
		return expressFee;
	}

	public void setExpressFee(double expressFee) {
		this.expressFee = expressFee;
	}

	public Double getTradePrice() {
		return tradePrice;
	}

	public void setTradePrice(Double tradePrice) {
		this.tradePrice = tradePrice;
	}

	public String getPropsName() {
		return propsName;
	}

	public void setPropsName(String propsName) {
		this.propsName = propsName;
	}

	public int getApproveStatus() {
		return approveStatus;
	}

	public void setApproveStatus(int approveStatus) {
		this.approveStatus = approveStatus;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getLocalApproveStatus() {
		return localApproveStatus;
	}

	public void setLocalApproveStatus(Integer localApproveStatus) {
		this.localApproveStatus = localApproveStatus;
	}

	public Long getCreated() {
		return created;
	}

	public void setCreated(Long created) {
		this.created = created;
	}

	public Integer getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}

	public Date getGmtCreated() {
		return gmtCreated;
	}

	public void setGmtCreated(Date gmtCreated) {
		this.gmtCreated = gmtCreated;
	}

	public Date getGmtModified() {
		return gmtModified;
	}

	public void setGmtModified(Date gmtModified) {
		this.gmtModified = gmtModified;
	}

	public Integer getIsOauth() {
		return isOauth;
	}

	public void setIsOauth(Integer isOauth) {
		this.isOauth = isOauth;
	}

	public Double getReducePrice() {
		return reducePrice;
	}

	public void setReducePrice(Double reducePrice) {
		this.reducePrice = reducePrice;
	}

	public Integer getIsShowCase() {
		return isShowCase;
	}

	public void setIsShowCase(Integer isShowCase) {
		this.isShowCase = isShowCase;
	}

	public Integer getNeedCheckPrice() {
		return needCheckPrice;
	}

	public void setNeedCheckPrice(Integer needCheckPrice) {
		this.needCheckPrice = needCheckPrice;
	}

}
