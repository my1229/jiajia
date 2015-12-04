/**    
 * @author mlc  
 * @version 1.0  
 *
 * 2015年7月24日   
 */
package com.rfw.jiajia.item.dao;

import java.util.List;

import com.rfw.jiajia.item.dto.ItemOnSaleDto;
import com.rfw.jiajia.item.models.ItemOnSale;

public interface IItemOnSaleDao {

    /**
     * 添加
     * 
     * @param onsale
     * @return
     */
    public long insert(ItemOnSale onsale);

    /**
     * 根据账号查找
     * 
     * @param account
     * @return
     */
    public List<ItemOnSale> findByAccount(String account, int pageNo, int pageSize);

    /**
     * 根据账号统计
     * 
     * @param account
     * @return
     */
    public long countByAccount(String account);

    /**
     * 根据天数统计
     * 
     * @param start
     * @param end
     * @param pageNo
     * @param pageSize
     * @return
     */
    public List<ItemOnSaleDto> checkByDay(Long start, Long end, Integer pageNo, Integer pageSize);

    /**
     * 统计
     * 
     * @param start
     * @param end
     * @return
     */
    public long count(Long start, Long end);

    /**
     * 统计总数
     * 
     * @param start
     * @param end
     * @return
     */
    public long countAll(Long start, Long end, Integer isOnSale);

    /**
     * 根据账号和宝贝Id查找
     * 
     * @param accountId
     * @param numIid
     * @return
     */
    public ItemOnSale findByAccountAndNumIid(String accountId, Long numIid);

}
