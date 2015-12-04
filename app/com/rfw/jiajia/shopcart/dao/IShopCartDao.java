/**   
 * @Description: TODO
 * @author mlc
 * @date 2015年8月11日 下午3:26:42 
 * @version V1.0   
 */

package com.rfw.jiajia.shopcart.dao;

import com.rfw.jiajia.shopcart.models.ShopCart;

public interface IShopCartDao {

    /**
     * 根据account查找
     * 
     * @param account
     * @return
     */
    public ShopCart findByAccount(String account);

    /**
     * 添加
     * 
     * @param cart
     * @return
     */
    public long insert(ShopCart cart);

}
