/**   
 * @Description: TODO
 * @author mlc
 * @date 2015年8月11日 下午2:57:10 
 * @version V1.0   
 */

package com.rfw.jiajia.shopcart.logic;

import com.rfw.common.base.dto.ProcessStatus;
import com.rfw.jiajia.shopcart.dto.ShopCartDto;

public interface IShortCartLogic {

    /**
     * 添加到购物车
     * 
     * @param numIid
     * @param skuJson
     * @return
     */
    public ProcessStatus addItemToCart(String accountId, Long numIid, String skuJson, Integer from, Integer isAdd);

    /**
     * 获取购物车详情
     * 
     * @param account
     * @return
     */
    public ShopCartDto findByAccount(String account);

    /**
     * 删除
     * 
     * @param account
     * @param ids
     * @return
     */
    public ProcessStatus deleteItems(String account, String ids, String numIids);

    /**
     * 账户统计
     * 
     * @param accountId
     * @return
     */
    public long countByAccount(String accountId);
}
