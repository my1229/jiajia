/**
 * @author mlc
 * @date 2015年7月15日
 * @version 1.0
 */
package com.rfw.jiajia.item.logic;

import java.util.List;

import com.rfw.jiajia.item.models.ItemDescr;
import com.taobao.api.domain.Item;

public interface IItemDescrLogic {

    /**
     * 批量创建宝贝描述
     * 
     * @param items
     * @return
     */
    public List<ItemDescr> createItemDescrBatch(List<Item> items);

    /**
     * 批量插入
     * 
     * @param descrs
     * @return
     */
    public long insertBatch(List<ItemDescr> descrs);

    /**
     * 批量更新
     * 
     * @param descrs
     * @return
     */
    public boolean updateBatch(List<ItemDescr> descrs);

    /**
     * 查找
     * 
     * @param numIid
     * @return
     */
    public ItemDescr findByNumIid(Long numIid);

    /**
     * 批量插入或更新
     * 
     * @param items
     * @return
     */
    public boolean insertOrUpdateBatch(List<Item> items);
}
