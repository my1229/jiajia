/**   
 * @Description: TODO
 * @author mlc
 * @date 2015年8月11日 下午4:00:09 
 * @version V1.0   
 */

package com.rfw.jiajia.shopcart.dto;

import java.util.List;

public class ShopCartDto {

    /**
     * accountId
     */
    private String accountId;

    /**
     * 购物车Id
     */
    private Long shopCartId;

    /**
     * 购物车详情
     */
    private List<StallDetailDto> details;

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public Long getShopCartId() {
        return shopCartId;
    }

    public void setShopCartId(Long shopCartId) {
        this.shopCartId = shopCartId;
    }

    public List<StallDetailDto> getDetails() {
        return details;
    }

    public void setDetails(List<StallDetailDto> details) {
        this.details = details;
    }

}
