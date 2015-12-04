/**
 * @author mlc
 * @date 2015年7月15日
 * @version 1.0
 */
package com.rfw.jiajia.item.logic;

import java.util.List;

import com.rfw.jiajia.item.models.ItemLocation;
import com.taobao.api.domain.Item;

public interface IItemLocationLogic {
    /**
     * 批量创建宝贝地址
     * 
     * @param items
     * @return
     */
    public List<ItemLocation> createItemLocationBatch(List<Item> items);

    /**
     * 批量插入
     * 
     * @param descrs
     * @return
     */
    public long insertBatch(List<ItemLocation> descrs);

    /**
     * 查找
     * 
     * @param numIid
     * @return
     */
    public ItemLocation findByNumIid(Long numIid);

    /**
     * 批量插入或更新
     * 
     * @param items
     * @return
     */
    public boolean insertOrUpdateBatch(List<Item> items);

    /**
     * 批量更新
     */
    public boolean updateBatch(List<ItemLocation> locations);

}
