/**   
 * @Description: TODO
 * @author mlc
 * @date 2015年8月11日 下午6:05:25 
 * @version V1.0   
 */

package com.rfw.jiajia.shopcart.dao.mapper.interfaces;

import java.util.List;
import java.util.Map;

import com.rfw.jiajia.shopcart.models.ShopCartDetail;

public interface IShopCartDetailMapper {

    public ShopCartDetail findByNumIidAndProps(Map map);

    public long insert(ShopCartDetail detail);

    public long update(ShopCartDetail detail);

    public List<ShopCartDetail> findByAccount(String accountId);

    public List<ShopCartDetail> findByCartId(Long cartId);

    public long deleteBatch(String ids);

    public long updateBatch(Map map);

    public ShopCartDetail findById(Long id);

    public long countByAccount(String accountId);

}
