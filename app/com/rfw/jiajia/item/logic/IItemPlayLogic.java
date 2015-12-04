/**    
 * @author mlc  
 * @version 1.0  
 *
 * 2015年7月15日   
 */
package com.rfw.jiajia.item.logic;

import java.util.List;

import com.rfw.common.base.dto.ProcessStatus;
import com.rfw.jiajia.item.dto.ItemPlayDto;
import com.rfw.jiajia.item.dto.ItemRankDto;
import com.rfw.jiajia.item.models.ItemDescr;
import com.rfw.jiajia.item.models.ItemImg;
import com.rfw.jiajia.item.models.ItemLocation;
import com.rfw.jiajia.item.models.ItemPlay;
import com.rfw.jiajia.item.models.ItemPropImg;
import com.rfw.jiajia.item.models.ItemSku;
import com.taobao.api.domain.Item;

public interface IItemPlayLogic {

    /**
     * 上下架
     * 
     * @param numIid
     * @param status
     * @return
     */
    public ProcessStatus updateItemStatus(String numIid, Integer status, Integer isDelete);

    public long updateIsDelete(long numIid, Integer isDelete);

    /**
     * 从淘宝获取item
     * 
     * @param numIid
     * @return
     */
    // public Item getItemFromTB(long numIid);

    /**
     * 批量插入宝贝信息
     * 
     * @param items
     * @return
     */
    public List<ItemPlay> createItemPlayBatch(List<Item> items, boolean isExist, boolean needGetTradePrice);

    /**
     * 标题获取价格
     * 
     * @param title
     * @param originPrice
     * @return
     */
    public double getPriceFromTitle(String title, double originPrice);

    /**
     * 创建宝贝相关信息
     * 
     * @param plays
     * @param propImgs
     * @param imgs
     * @param locations
     * @param descrs
     * @param skus
     * @return
     */
    public boolean createItemPlayRef(List<ItemPlay> plays, List<ItemPropImg> propImgs, List<ItemImg> imgs,
            List<ItemLocation> locations, List<ItemDescr> descrs, List<ItemSku> skus);

    /**
     * 搜索总数
     * 
     * @param nick
     *            昵称
     * @param sellerCids
     *            类目
     * @param keyword
     *            关键词
     * @param upPrice
     *            价格上限
     * @param lowPrice
     *            价格下限
     * @return
     */
    public long countByNick(String nick, List<Long> sellerCids, String keyword, Double upPrice, Double lowPrice,
            String outerId, Integer localApproveStatus, Boolean tradePrice);

    /**
     * 根据Numiid获取宝贝信息
     * 
     * @param numIid
     * @return
     */
    public ItemPlayDto findByNumIid(Long numIid, boolean onlySku);

    public ItemPlayDto findByNumIid(Long numIid, boolean onlyBasicInfo, boolean onlySku);

    /**
     * 更新item信息
     * 
     * @param numIid
     * @param tradePrice
     * @param status
     * @return
     */
    public ProcessStatus updateItem(Long numIid, Double tradePrice, Integer status, Integer isDelete);

    public ProcessStatus updateTradePrice(String nick, Long numIid, double tradePrice);

    public ProcessStatus updateStatus(String nick, Long numIid, int localApproveStatus, int approveStatus,
            int isDelete);

    /**
     * 查看档口的宝贝
     * 
     * @param type
     * @param title
     * @param outerId
     * @param hasTradePrice
     * @param pageNo
     * @param pageSize
     * @return
     */
    public List<ItemPlay> findByNick(String nick, Integer type, String title, String outerId, Boolean hasTradePrice,
            Integer pageNo, Integer pageSize);

    /**
     * 根据昵称查找
     * 
     * @param nick
     * @return
     */
    public List<ItemPlay> findByNick(String nick);

    /**
     * 批量插入
     * 
     * @param itemPlays
     * @return
     */
    public long insertBatch(List<ItemPlay> itemPlays);

    /**
     * 添加淘宝端宝贝至本地
     * 
     * @param items
     * @return
     */
    public long addTBItemsToLocal(List<Item> items);

    /**
     * 更新宝贝信息
     */
    public boolean updateItems(List<ItemPlay> items);

    public ItemPlay updateByItem(ItemPlay itemPlay, Item item);

    /**
     * 宝贝详情页面静态化
     * 
     * @param url
     * @param numIid
     * @return
     */
    public String createNewDetailInfo(String url, Long numIid);

    /**
     * 同步数据到数据库
     * 
     * @param items
     */
    public void updateItemToDb(List<Item> items);

    /**
     * 更新宝贝授权状态
     * 
     * @param nick
     * @return
     */
    public long updateIsOauth(String nick, Integer isOauth);

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
     * 获取需要验证价格的宝贝
     * 
     * @param pageNo
     * @param pageSize
     * @return
     */
    public List<ItemPlay> findNeedCheckPriceItems(Integer pageNo, Integer pageSize);

    public long countNeedCheckPriceItems();

    /**
     * 更新缓存
     * 
     * @param numIidsArr
     * @param tradePrice
     * @param reducePrice
     */
    public void updateCache(String[] numIidsArr, Integer status, Double tradePrice, Double reducePrice);

    /**
     * 获取店铺内宝贝排行
     * 
     * @param accountId
     * @param type
     */
    public List<ItemRankDto> getItemRank(String accountId, Integer type, Integer pageNo, Integer pageSize);

    /**
     * 排行宝贝数量
     * 
     * @param nick
     * @param type
     * @return
     */
    public long countForRank(String accountId, Integer type);

    /**
     * 从标题获取货号
     * 
     * @param title
     * @return
     */
    public String matchOuterId(String title);

}
