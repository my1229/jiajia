/**   
 * @Description: TODO
 * @author mlc
 * @date 2015年8月20日 下午4:35:21 
 * @version V1.0   
 */

package com.rfw.jiajia.shopcart.dao.mapper.interfaces;

import java.util.List;
import java.util.Map;

import com.rfw.jiajia.shopcart.models.ShopCartItem;

public interface IShopCartItemDaoMapper {

    public long insert(ShopCartItem item);

    public long update(ShopCartItem item);

    public List<ShopCartItem> findByAccount(String accountId);

    public ShopCartItem findByNumIid(Long numIid);

    public long countByAccount(String accountId);

    public long updateBatch(Map map);

}
