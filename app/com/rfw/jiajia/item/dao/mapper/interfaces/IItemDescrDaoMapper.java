/**    
 * @author mlc  
 * @version 1.0  
 *
 * 2015年7月15日   
 */
package com.rfw.jiajia.item.dao.mapper.interfaces;

import java.util.Map;

import com.rfw.jiajia.item.models.ItemDescr;

public interface IItemDescrDaoMapper {

    public long insert(ItemDescr descr);

    public long insertBatch(Map map);

    public ItemDescr findByNumIid(Long numIid);

    public ItemDescr findById(Long id);

    public long update(ItemDescr descrs);

}
