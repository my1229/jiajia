/**    
 * @author mlc  
 * @version 1.0  
 *
 * 2015年7月15日   
 */
package com.rfw.jiajia.item.dao;

import java.util.List;

import com.rfw.jiajia.item.models.ItemDescr;

public interface IItemDescrDao {
    public long insert(ItemDescr descr);

    public long insertBatch(List<ItemDescr> descrs);

    public ItemDescr findByNumIid(Long numIid);

    public ItemDescr findById(Long numIid);

    public boolean updateBatch(List<ItemDescr> descrs);
}
