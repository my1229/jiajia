/**   
 * @Description: TODO
 * @author mlc
 * @date 2015年8月11日 下午4:09:18 
 * @version V1.0   
 */

package com.rfw.jiajia.shopcart.dto;

import java.util.Date;
import java.util.List;

public class ShopCartDetailDto implements Comparable<ShopCartDetailDto> {

    /**
     * 宝贝标题
     */
    private String title;

    /**
     * 宝贝图片地址
     */
    private String picPath;

    /**
     * 宝贝Id
     */
    private Long numIid;

    /**
     * 是否下架
     */
    private Integer localApproveStatus;

    /**
     * sku {@link SkuDto}
     */
    private List<SkuDetailDto> skus;

    /**
     * stallId
     */
    private Long stallId;

    /**
     * 店铺名称
     * 
     * @return
     */
    private String shopName;

    /**
     * 手机号
     */
    private String sellerMobile;

    /**
     * 卖家昵称
     */
    private String sellerNick;

    /**
     * 差价
     */
    private Double reducePrice;

    /**
     * 是否是今天添加
     */
    private Integer isToday;

    private Date modified;

    public Long getNumIid() {
        return numIid;
    }

    public void setNumIid(Long numIid) {
        this.numIid = numIid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPicPath() {
        return picPath;
    }

    public void setPicPath(String picPath) {
        this.picPath = picPath;
    }

    public String getSellerNick() {
        return sellerNick;
    }

    public void setSellerNick(String sellerNick) {
        this.sellerNick = sellerNick;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public List<SkuDetailDto> getSkus() {
        return skus;
    }

    public void setSkus(List<SkuDetailDto> skus) {
        this.skus = skus;
    }

    public Long getStallId() {
        return stallId;
    }

    public void setStallId(Long stallId) {
        this.stallId = stallId;
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

    @Override
    public int compareTo(ShopCartDetailDto o) {

        int i = this.getModified().compareTo(o.getModified());

        if (i < 0) {
            return 1;
        } else {
            return -1;
        }
    }

    public Date getModified() {
        return modified;
    }

    public void setModified(Date modified) {
        this.modified = modified;
    }

    public Integer getIsToday() {
        return isToday;
    }

    public void setIsToday(Integer isToday) {
        this.isToday = isToday;
    }

    public Integer getLocalApproveStatus() {
        return localApproveStatus;
    }

    public void setLocalApproveStatus(Integer localApproveStatus) {
        this.localApproveStatus = localApproveStatus;
    }

}
