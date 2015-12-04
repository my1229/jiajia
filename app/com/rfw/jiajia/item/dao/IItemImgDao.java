/**    
 * @author mlc  
 * @version 1.0  
 *
 * 2015年7月15日   
 */
package com.rfw.jiajia.item.dao;

import java.util.List;

import com.rfw.jiajia.item.models.ItemImg;

public interface IItemImgDao {
    public long insert(ItemImg itemImg);

    public long insertBatch(List<ItemImg> imgs);

    public List<ItemImg> findByNumIid(Long numIid);

    public long delete(Long numIid);
}
