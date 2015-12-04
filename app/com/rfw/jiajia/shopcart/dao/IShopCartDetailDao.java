/**   
 * @Description: TODO
 * @author mlc
 * @date 2015年8月11日 下午3:02:29 
 * @version V1.0   
 */

package com.rfw.jiajia.shopcart.dao;

import java.util.List;

import com.rfw.jiajia.shopcart.models.ShopCartDetail;

public interface IShopCartDetailDao {

    /**
     * 根据numIId和sku查找
     * 
     * @param numIid
     * @param propsName
     * @return
     */
    public ShopCartDetail findByNumIidAndProps(String accountId, Long numIid, String propsName);

    /**
     * 添加
     * 
     * @param detail
     * @return
     */
    public long insert(ShopCartDetail detail);

    /**
     * 更新
     * 
     * @param detail
     * @return
     */
    public long update(ShopCartDetail detail);

    /**
     * 根据账户Id查找
     * 
     * @param accountId
     * @return
     */
    public List<ShopCartDetail> findByAccount(String accountId);

    /**
     * 根据购物车Id查找
     * 
     * @param cartId
     * @return
     */
    public List<ShopCartDetail> findByCartId(Long cartId);

    /**
     * 查找numIId
     * 
     * @param cartId
     * @return
     */
    public List<Long> findNumIidByCartId(Long cartId);

    /**
     * 批量删除
     * 
     * @param ids
     * @return
     */
    public long deleteBatch(String ids);

    /**
     * 批量更新
     * 
     * @param ids
     * @param status
     * @return
     */
    public long updateBatch(String account, String ids, Integer num, Integer status);

    /**
     * 根据Id查询
     * 
     * @param id
     * @return
     */
    public ShopCartDetail findById(Long id);

    /**
     * 根据账户统计
     */
    public long countByAccount(String accountId);

}
