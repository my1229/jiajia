/**   
 * @Description: TODO
 * @author mlc
 * @date 2015年8月20日 下午5:01:11 
 * @version V1.0   
 */

package com.rfw.jiajia.shopcart.dao;

import java.util.List;

import com.rfw.jiajia.shopcart.models.ShopCartItem;

public interface IShopCartItemDao {

    public long insert(ShopCartItem item);

    public long update(ShopCartItem item);

    public List<ShopCartItem> findByNumIid(String accountId);

    public ShopCartItem findByNumIid(Long numIid);

    public long countByAccount(String accountId);

    public long updateBatch(String account, String ids, Integer status);

}
