/**    
 * @author mlc  
 * @version 1.0  
 *
 * 2015年7月15日   
 */
package com.rfw.jiajia.item.dao;

import java.util.List;

import com.rfw.jiajia.item.models.ItemPropImg;

public interface IItemPropImgDao {
    public long insert(ItemPropImg propImg);

    public long insertBatch(List<ItemPropImg> imgs);

    public List<ItemPropImg> findByNumIid(Long numIid);

    public long delete(Long numIid);
}
