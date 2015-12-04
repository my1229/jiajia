/**   
 * @Description: TODO
 * @author mlc
 * @date 2015年8月26日 下午5:28:22 
 * @version V1.0   
 */

package com.rfw.jiajia.item.dto;

public class ItemRankDto {

    /**
     * 宝贝标题
     */
    private String title;

    /**
     * 宝贝图片
     */
    private String picUrl;

    /**
     * 订单量/收藏量
     */
    private Long count;

    /**
     * 批发价
     */
    private Double tradePrice;

    /**
     * 售价
     */
    private Double price;

    /**
     * 宝贝Id
     */
    private Long numIid;

    /**
     * 卖家昵称
     */
    private String nick;

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

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public Double getTradePrice() {
        return tradePrice;
    }

    public void setTradePrice(Double tradePrice) {
        this.tradePrice = tradePrice;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
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

}
