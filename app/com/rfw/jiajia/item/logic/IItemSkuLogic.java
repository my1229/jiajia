/**
 * @author mlc
 * @date 2015年7月15日
 * @version 1.0
 */
package com.rfw.jiajia.item.logic;

import java.util.List;

import com.rfw.jiajia.item.models.ItemSku;
import com.taobao.api.domain.Item;

public interface IItemSkuLogic {

    /**
     * 批量创建宝贝sku
     * 
     * @param items
     * @return
     */
    public List<ItemSku> createItemSkuBatch(List<Item> items);

    /**
     * 批量插入
     * 
     * @param descrs
     * @return
     */
    public long insertBatch(List<ItemSku> descrs);

    /**
     * 查找
     * 
     * @param numIid
     * @return
     */
    public List<ItemSku> findByNumIid(Long numIid);

    public long delete(Long numIid);

}
