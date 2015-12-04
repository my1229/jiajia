/**    
 * @author mlc  
 * @version 1.0  
 *
 * 2015年7月15日   
 */
package com.rfw.jiajia.item.dao;

import java.util.List;

import com.rfw.jiajia.item.models.ItemLocation;

public interface IItemLocationDao {
    public long insert(ItemLocation location);

    public long insertBatch(List<ItemLocation> locations);

    public ItemLocation findByNumIid(Long numIid);

    public boolean updateBatch(List<ItemLocation> locations);
}
