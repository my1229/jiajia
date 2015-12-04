/**   
 * @Description: 购物车详情
 * @author mlc
 * @date 2015年8月11日 下午12:55:19 
 * @version V1.0   
 */

package com.rfw.jiajia.shopcart.models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.rfw.common.base.models.BasicModel;
import com.rfw.jiajia.shopcart.dto.SkuDto;

@Entity(name = ShopCartDetail.TABLE_NAME)
public class ShopCartDetail extends BasicModel {

    public static final String TABLE_NAME = "shop_cart_detail";

    @Id
    @GeneratedValue
    private Long id;

    /**
     * 宝贝Id
     */
    private Long numIid;

    /**
     * 用户Id
     */
    private String accountId;
    /**
     * 档口Id
     */
    private Long stallId;

    /**
     * 档口昵称
     */
    private String sellerNick;

    /**
     * 卖家手机号
     */
    private String sellerMobile;

    /**
     * 档口名称
     */
    private String shopName;

    /**
     * 价格
     */
    private Double price;

    /**
     * 差价
     */
    private Double reducePrice = 0.0;

    /**
     * 购物车Id
     */
    private Long shopCartId;

    /**
     * sku属性名
     */
    private String propsName;

    /**
     * sku {@link SkuDto}
     */
    private String sku;

    /**
     * 数量
     */
    private Long num;

    /**
     * 宝贝图片
     */
    private String picPath;

    /**
     * 宝贝标题
     */
    private String title;

    /**
     * 商家编码
     */
    private String outerId;

    /**
     * 状态
     */
    private int status;

    private Date modified;

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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Long getShopCartId() {
        return shopCartId;
    }

    public void setShopCartId(Long shopCartId) {
        this.shopCartId = shopCartId;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getSellerNick() {
        return sellerNick;
    }

    public void setSellerNick(String sellerNick) {
        this.sellerNick = sellerNick;
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

    public Long getNum() {
        return num;
    }

    public void setNum(Long num) {
        this.num = num;
    }

    public String getPicPath() {
        return picPath;
    }

    public void setPicPath(String picPath) {
        this.picPath = picPath;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getPropsName() {
        return propsName;
    }

    public void setPropsName(String propsName) {
        this.propsName = propsName;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getOuterId() {
        return outerId;
    }

    public void setOuterId(String outerId) {
        this.outerId = outerId;
    }

    public String getSellerMobile() {
        return sellerMobile;
    }

    public void setSellerMobile(String sellerMobile) {
        this.sellerMobile = sellerMobile;
    }

    public Double getReducePrice() {
        return reducePrice;
    }

    public void setReducePrice(Double reducePrice) {
        this.reducePrice = reducePrice;
    }

    public Date getModified() {
        return modified;
    }

    public void setModified(Date modified) {
        this.modified = modified;
    }

}
