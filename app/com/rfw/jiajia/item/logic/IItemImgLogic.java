/**
 * @author mlc
 * @date 2015年7月15日
 * @version 1.0
 */
package com.rfw.jiajia.item.logic;

import java.util.List;

import com.rfw.jiajia.item.models.ItemImg;
import com.taobao.api.domain.Item;

public interface IItemImgLogic {

    /**
     * 批量创建宝贝图片
     * 
     * @param items
     * @return
     */
    public List<ItemImg> createItemImgBatch(List<Item> items);

    /**
     * 批量插入
     * 
     * @param descrs
     * @return
     */
    public long insertBatch(List<ItemImg> descrs);

    public List<ItemImg> findByNumIid(Long numIid);

    /**
     * 批量插入或更新
     * 
     * @param items
     * @return
     */
    public boolean insertOrUpdateBatch(List<Item> items);

    /**
     * 删除
     */
    public long delete(Long numIid);
}
