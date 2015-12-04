/**    
 * @author mlc  
 * @version 1.0  
 *
 * 2015年7月15日   
 */
package com.rfw.jiajia.item.dao;

import java.util.List;

import com.rfw.jiajia.item.models.ItemSku;

public interface IItemSkuDao {
    public long insert(ItemSku sku);

    public long insertBatch(List<ItemSku> skus);

    public List<ItemSku> findByNumIid(Long numIid);

    public long delete(Long numIid);
}
