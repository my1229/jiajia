/**   
 * @Description: TODO
 * @author mlc
 * @date 2015年8月11日 下午6:05:10 
 * @version V1.0   
 */

package com.rfw.jiajia.shopcart.dao.mapper.interfaces;

import com.rfw.jiajia.shopcart.models.ShopCart;

public interface IShopCartDaoMapper {

    public ShopCart findByAccount(String account);

    public long insert(ShopCart cart);
}
