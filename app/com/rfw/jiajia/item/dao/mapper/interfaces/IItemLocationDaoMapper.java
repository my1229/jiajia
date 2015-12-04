/**    
 * @author mlc  
 * @version 1.0  
 *
 * 2015年7月15日   
 */
package com.rfw.jiajia.item.dao.mapper.interfaces;

import java.util.Map;

import com.rfw.jiajia.item.models.ItemLocation;

public interface IItemLocationDaoMapper {

    public long insert(ItemLocation location);

    public long insertBatch(Map map);

    public ItemLocation findByNumIid(Long numIid);

    public long update(ItemLocation location);
}
