/**
 * @author mlc
 * @date 2015年7月15日
 * @version 1.0
 */
package com.rfw.jiajia.item.logic;

import java.util.List;

import com.rfw.jiajia.item.models.ItemPropImg;
import com.taobao.api.domain.Item;

public interface IItemPropImgLogic {

    /**
     * 批量创建宝贝属性图片
     * 
     * @param items
     * @return
     */
    public List<ItemPropImg> createItemPropImgBatch(List<Item> items);

    /**
     * 批量插入
     * 
     * @param descrs
     * @return
     */
    public long insertBatch(List<ItemPropImg> descrs);

    /**
     * 查找
     * 
     * @param numIid
     * @return
     */
    public List<ItemPropImg> findByNumIid(Long numIid);

    public long delete(Long numIid);

}
