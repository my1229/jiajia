/**   
 * @Description: 购物车
 * @author mlc
 * @date 2015年8月11日 下午12:55:05 
 * 
 * @version V1.0   
 */

package com.rfw.jiajia.shopcart.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.rfw.common.base.models.BasicModel;

@Entity(name = ShopCart.TABLE_NAME)
public class ShopCart extends BasicModel {

    public static final String TABLE_NAME = "shop_cart";
    @Id
    @GeneratedValue
    private Long id;

    /**
     * 用户Id
     */
    private String accountId;

    public ShopCart() {
    }

    public ShopCart(String accountId) {
        this.accountId = accountId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

}
