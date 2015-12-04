/**    
 * @author mlc  
 * @version 1.0  
 *
 * 2015年7月24日   
 */
package com.rfw.jiajia.item.dao.mapper.interfaces;

import java.util.List;
import java.util.Map;

import com.rfw.jiajia.item.dto.ItemOnSaleDto;
import com.rfw.jiajia.item.models.ItemOnSale;

public interface IItemOnSaleDaoMapper {

    public long insert(ItemOnSale onsale);

    public List<ItemOnSale> findByAccount(Map map);

    public long countByAccount(String account);

    public List<ItemOnSaleDto> checkByDay(Map map);

    public long count(Map map);

    public long countAll(Map map);

    public long countByUser(String accountId);

    public long countByUserAndItem(String accountId);

    public ItemOnSale findByAccountAndNumIid(Map map);

}
