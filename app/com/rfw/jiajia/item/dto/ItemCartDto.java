/**
 * @Description: 购物车中宝贝DTO
 * @author mlc
 * @date 2015年8月26日 下午5:52:09 
 * @version V1.0   
 */

package com.rfw.jiajia.item.dto;

import org.apache.solr.client.solrj.beans.Field;

public class ItemCartDto {

    /**
     * 宝贝Id
     */
    @Field("numIid_l")
    private Long numIid;

    /**
     * 账户Id
     */
    @Field("account_s")
    private String accountId;

    /**
     * 市场Id
     */
    @Field("marketingId_l")
    private Long marketingId;

    @Field("marketingName_s")
    private String marketingName;

    /**
     * 档口Id
     */
    @Field("stallId_l")
    private Long stallId;

    @Field("nick_s")
    private String nick;

    /**
     * 标题
     */
    @Field("title_s")
    private String title;

    /**
     * 主图url
     */
    @Field("picUrl_s")
    private String picUrl;

    /**
     * 批发价
     */
    @Field("tradePrice_d")
    private Double tradePrice;

    /**
     * 促销价
     */
    @Field("reducePrice_d")
    private Double reducePrice;

    /**
     * 售价
     */
    @Field("price_d")
    private Double price;

    @Field("roomName")
    private String roomName;

    @Field("localApproveStatus_i")
    private Integer localApproveStatus;

    @Field("outerId_s")
    private String outerId;

    public Long getNumIid() {
        return numIid;
    }

    public void setNumIid(Long numIid) {
        this.numIid = numIid;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public Long getStallId() {
        return stallId;
    }

    public void setStallId(Long stallId) {
        this.stallId = stallId;
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

    public Double getTradePrice() {
        return tradePrice;
    }

    public void setTradePrice(Double tradePrice) {
        this.tradePrice = tradePrice;
    }

    public Double getReducePrice() {
        return reducePrice;
    }

    public void setReducePrice(Double reducePrice) {
        this.reducePrice = reducePrice;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Long getMarketingId() {
        return marketingId;
    }

    public void setMarketingId(Long marketingId) {
        this.marketingId = marketingId;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public void setLocalApproveStatus(Integer localApproveStatus) {
        this.localApproveStatus = localApproveStatus;
    }

    public Integer getLocalApproveStatus() {
        return localApproveStatus;
    }

    public String getMarketingName() {
        return marketingName;
    }

    public void setMarketingName(String marketingName) {
        this.marketingName = marketingName;
    }

    public String getOuterId() {
        return outerId;
    }

    public void setOuterId(String outerId) {
        this.outerId = outerId;
    }

}
