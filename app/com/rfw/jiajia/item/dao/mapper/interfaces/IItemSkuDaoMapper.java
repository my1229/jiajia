/**    
 * @author mlc  
 * @version 1.0  
 *
 * 2015年7月15日   
 */
package com.rfw.jiajia.item.dao.mapper.interfaces;

import java.util.List;
import java.util.Map;

import com.rfw.jiajia.item.models.ItemSku;

public interface IItemSkuDaoMapper {

    public long insert(ItemSku sku);

    public long insertBatch(Map map);

    public List<ItemSku> findByNumIid(Long numIid);

    public long delete(Long numIid);
}
