/**   
 * @Description: TODO
 * @author mlc
 * @date 2015年8月20日 下午4:33:06 
 * @version V1.0   
 */

package com.rfw.jiajia.shopcart.models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.rfw.common.base.models.BasicModel;

@Entity(name = ShopCartItem.TABLE_NAME)
public class ShopCartItem extends BasicModel {

    public static final String TABLE_NAME = "shop_cart_item";

    @Id
    @GeneratedValue
    private Long id;
    /**
     * 购物车Id
     */
    private Long shopCartId;

    /**
     * 用户Id
     */
    private String accountId;

    /**
     * 宝贝Id
     */
    private Long numIid;

    /**
     * 修改时间
     */
    private Date modified;

    /**
     * 是否已删除
     * 
     * @return
     */
    private int isDelete = 0;

    public Long getNumIid() {
        return numIid;
    }

    public void setNumIid(Long numIid) {
        this.numIid = numIid;
    }

    public Long getShopCartId() {
        return shopCartId;
    }

    public void setShopCartId(Long shopCartId) {
        this.shopCartId = shopCartId;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public Date getModified() {
        return modified;
    }

    public void setModified(Date modified) {
        this.modified = modified;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(int isDelete) {
        this.isDelete = isDelete;
    }

}
