/**    
 * @author mlc  
 * @version 1.0  
 *
 * 2015年7月15日   
 */
package com.rfw.jiajia.item.dao.mapper.interfaces;

import java.util.List;
import java.util.Map;

import com.rfw.jiajia.item.dto.ItemRankDto;
import com.rfw.jiajia.item.models.ItemPlay;

public interface IItemPlayDaoMapper {

    public long insert(ItemPlay item);

    public long insertBatch(Map params);

    public long update(ItemPlay item);

    public List<ItemPlay> getItemList(Map params);

    public List<ItemPlay> search(Map params);

    public ItemPlay findByNumIid(Long numIid);

    public long count();

    public long countByNick(Map parmas);

    public List<ItemPlay> findByNick(String nick);

    public List<Long> findAllCid();

    public long updateIsOauth(Map map);

    public long updateIsDelete(Map map);

    public List<ItemPlay> findInCompleteItems();

    public List<ItemPlay> findNoDescItems();

    public long updateReducePrice(Map map);

    public long updateStatus(Map map);

    public long updateTradePrice(Map map);

    public long updateReducePriceByNick(Map map);

    public List<ItemPlay> findByGmtCreated(Map map);

    public List<ItemPlay> findNeedCheckPriceItems(Map map);

    public long countNeedCheckPriceItems();

    public List<ItemRankDto> getItemByOrders(Map map);

    public List<ItemRankDto> getItemByCollection(Map map);

    public long countForOrderRank(Map map);

    public long countForCollectRank(Map map);

    public List<ItemPlay> findHot(Integer limit);

}
