/**   
 * @Description: TODO
 * @author mlc
 * @date 2015年8月11日 下午5:45:43 
 * @version V1.0   
 */

package com.rfw.jiajia.shopcart.dto;

import java.util.Date;
import java.util.List;

public class StallDetailDto implements Comparable<StallDetailDto> {

    private String shopName;

    private Long stallId;

    private String sellerMobile;

    private String sellerNick;

    private Date modified;

    private Integer isToday;

    private List<ShopCartDetailDto> shopCartDetailDtos;

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public Long getStallId() {
        return stallId;
    }

    public void setStallId(Long stallId) {
        this.stallId = stallId;
    }

    public String getSellerNick() {
        return sellerNick;
    }

    public void setSellerNick(String sellerNick) {
        this.sellerNick = sellerNick;
    }

    public List<ShopCartDetailDto> getShopCartDetailDtos() {
        return shopCartDetailDtos;
    }

    public void setShopCartDetailDtos(List<ShopCartDetailDto> shopCartDetailDtos) {
        this.shopCartDetailDtos = shopCartDetailDtos;
    }

    public String getSellerMobile() {
        return sellerMobile;
    }

    public void setSellerMobile(String sellerMobile) {
        this.sellerMobile = sellerMobile;
    }

    public Date getModified() {
        return modified;
    }

    public void setModified(Date modified) {
        this.modified = modified;
    }

    @Override
    public int compareTo(StallDetailDto o) {

        int i = this.getModified().compareTo(o.getModified());

        if (i < 0) {
            return 1;
        } else {
            return -1;
        }
    }

    public Integer getIsToday() {
        return isToday;
    }

    public void setIsToday(Integer isToday) {
        this.isToday = isToday;
    }

}
