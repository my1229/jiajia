/**    
 * @author mlc  
 * @version 1.0  
 *
 * 2015年7月15日   
 */
package com.rfw.jiajia.item.dao;

import java.util.List;

import com.rfw.jiajia.item.dto.ItemRankDto;
import com.rfw.jiajia.item.models.ItemDescr;
import com.rfw.jiajia.item.models.ItemImg;
import com.rfw.jiajia.item.models.ItemLocation;
import com.rfw.jiajia.item.models.ItemPlay;
import com.rfw.jiajia.item.models.ItemPropImg;
import com.rfw.jiajia.item.models.ItemSku;

public interface IItemPlayDao {
    /**
     * 添加
     * 
     * @param item
     * @return
     */
    public long insert(ItemPlay item);

    /**
     * 批量添加
     * 
     * @param itemPlays
     * @return
     */
    public long insertBatch(List<ItemPlay> itemPlays);

    /**
     * 宝贝相关信息创建
     */
    public boolean createItemPlayRef(List<ItemPlay> plays, List<ItemPropImg> propImgs, List<ItemImg> imgs,
            List<ItemLocation> locations, List<ItemDescr> descrs, List<ItemSku> skus);

    /**
     * 分次查询
     * 
     * @param offset
     * @param limit
     * @return
     */
    public List<ItemPlay> getItemList(long offset, long limit);

    /**
     * 计算总数
     * 
     * @return
     */
    public long count();

    /**
     * 条件搜索
     * 
     * @param nick
     * @param subCids
     * @param approveStatus
     * @param keyword
     * @param orderBy
     * @param pageSize
     * @param pageNo
     * @param upPrice
     * @param lowPrice
     * @param sort
     * @return
     */
    public List<ItemPlay> findByNick(String nick, String outerId, Boolean hasTradePrice, Integer type,
            List<Long> subCids, String keyword, String orderBy, int pageSize, int pageNo, Double upPrice,
            Double lowPrice, String sort);

    /**
     * 搜索总数
     * 
     * @param userId
     * @param sellerCids
     * @param approveStatus
     * @param query
     * @param outerId
     * @param hasShowCase
     * @param freightPayer
     * @param postageIds
     * @param isNotStarting
     * @return
     */
    public long countByNick(String nick, String outerId, Boolean hasTradePrice, Integer type, List<Long> sellerCids,
            String keyword, Double upPrice, Double lowPrice);

    /**
     * 根据NumIid获取宝贝
     * 
     * @param numIid
     * @return
     */
    public ItemPlay findByNumIid(Long numIid);

    /**
     * 更新
     */
    public boolean updateBatch(List<ItemPlay> plays);

    /**
     * 更新单个Item信息
     */
    public long update(ItemPlay play);

    /**
     * 根据昵称查找
     */

    public List<ItemPlay> findByNick(String nick);

    /**
     * 查找所有cid
     * 
     * @return
     */
    public List<Long> findAllCid();

    /**
     * 更新授权状态
     * 
     * @param nick
     * @return
     */
    public long updateIsOauth(String nick, Integer isOauth);

    /**
     * 更新状态
     * 
     * @param numIid
     * @param status
     * @return
     */
    public long updateIsDelete(long numIid, Integer status);

    /**
     * 找出字段不完全的numIid
     * 
     * @return
     */
    public List<ItemPlay> findInCompleteItems();

    /**
     * 找出没有desc的item
     * 
     * @return
     */
    public List<ItemPlay> findNoDescItems();

    /**
     * 更新差价
     * 
     * @param numIids
     * @param reducePrice
     * @return
     */
    public long updateTradePrice(String nick, long numIid, double tradePrice);

    public long updateStatus(String nick, long numIid, int localApproveStatus, int approveStatus, int isDelete);

    /**
     * 更新差价
     * 
     * @param numIids
     * @param reducePrice
     * @return
     */
    public long updateReducePrice(String nick, String numIids, double reducePrice);

    /**
     * 根据创建时间获取宝贝
     * 
     * @param limit
     * @return
     */
    public List<ItemPlay> findByGmtCreated(int limit, int offset);

    public List<ItemPlay> findNeedCheckPriceItems(Integer pageNo, Integer pageSize);

    public long countNeedCheckPriceItems();

    /**
     * 根据订单排行
     * 
     * @param nick
     * @param pageNo
     * @param pageSize
     * @return
     */
    public List<ItemRankDto> getItemByOrders(String nick, Integer pageNo, Integer pageSize);

    /**
     * 根据收藏量排行
     * 
     * @param nick
     * @param pageNo
     * @param pageSize
     * @return
     */
    public List<ItemRankDto> getItemByCollection(String nick, Integer pageNo, Integer pageSize);

    /**
     * 订单总量
     * 
     * @param map
     * @return
     */
    public long countForOrderRank(String nick);

    /**
     * 收藏总量
     * 
     * @param map
     * @return
     */
    public long countForCollectRank(String nick);

    /**
     * 查找最新宝贝
     * 
     * @param limit
     * @return
     */
    public List<ItemPlay> findHot(Integer limit);
}
