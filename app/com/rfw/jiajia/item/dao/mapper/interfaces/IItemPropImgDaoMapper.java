/**    
 * @author mlc  
 * @version 1.0  
 *
 * 2015年7月15日   
 */
package com.rfw.jiajia.item.dao.mapper.interfaces;

import java.util.List;
import java.util.Map;

import com.rfw.jiajia.item.models.ItemPropImg;

public interface IItemPropImgDaoMapper {

    public long insert(ItemPropImg propImg);

    public long insertBatch(Map map);

    public List<ItemPropImg> findByNumIid(Long numIid);

    public long delete(Long numIid);
}
