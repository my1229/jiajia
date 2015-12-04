/**    
 * @author mlc  
 * @version 1.0  
 *
 * 2015年7月15日   
 */
package com.rfw.jiajia.item.logic.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rfw.common.base.dto.ProcessStatus;
import com.rfw.common.utils.CommonUtils;
import com.rfw.jiajia.item.constant.ApproveStatus;
import com.rfw.jiajia.item.constant.FreightPayer;
import com.rfw.jiajia.item.constant.ItemRankType;
import com.rfw.jiajia.item.constant.LocalApproveStatus;
import com.rfw.jiajia.item.dao.IItemPlayDao;
import com.rfw.jiajia.item.dao.impl.ItemPlayDaoImpl;
import com.rfw.jiajia.item.dto.ItemPlayDto;
import com.rfw.jiajia.item.dto.ItemRankDto;
import com.rfw.jiajia.item.logic.IItemDescrLogic;
import com.rfw.jiajia.item.logic.IItemImgLogic;
import com.rfw.jiajia.item.logic.IItemLocationLogic;
import com.rfw.jiajia.item.logic.IItemPlayLogic;
import com.rfw.jiajia.item.logic.IItemPropImgLogic;
import com.rfw.jiajia.item.logic.IItemSkuLogic;
import com.rfw.jiajia.item.models.ItemDescr;
import com.rfw.jiajia.item.models.ItemImg;
import com.rfw.jiajia.item.models.ItemLocation;
import com.rfw.jiajia.item.models.ItemPlay;
import com.rfw.jiajia.item.models.ItemPropImg;
import com.rfw.jiajia.item.models.ItemSku;
import com.taobao.api.domain.Item;

import play.cache.Cache;

public class ItemPlayLogicImpl implements IItemPlayLogic {

    private static final Logger LOG = LoggerFactory.getLogger(ItemPlayLogicImpl.class);

    private static IItemPlayLogic instance = new ItemPlayLogicImpl();

    public ItemPlayLogicImpl() {
    }

    public static IItemPlayLogic getInstance() {
        return instance;
    }

    private IItemPlayDao itemPlayDao = ItemPlayDaoImpl.getInstance();

    private IItemPropImgLogic propImgLogic = ItemPropImgLogicImpl.getInstance();

    private IItemDescrLogic descrLogic = ItemDescrLogicImpl.getInstance();

    private IItemImgLogic imgLogic = ItemImgLogicImpl.getInstance();

    private IItemSkuLogic skuLogic = ItemSkuLogicImpl.getInstance();

    private IItemLocationLogic locationLogic = ItemLocationLogicImpl.getInstance();

    private IItemImgLogic itemImgLogic = ItemImgLogicImpl.getInstance();

    @Override
    public ProcessStatus updateItemStatus(String numIids, Integer status, Integer isDelete) {

        ProcessStatus processStatus = new ProcessStatus(false);
        if (StringUtils.isEmpty(numIids)) {
            return processStatus;
        }
        String[] numIidsArr = numIids.split(",");

        for (String id : numIidsArr) {
            updateItem(Long.valueOf(id), null, status, isDelete);

        }
        updateCache(numIidsArr, status, null, null);
        processStatus.setSuccess(true);

        return processStatus;
    }

    @Override
    public long updateIsDelete(long numIid, Integer isDelete) {
        return itemPlayDao.updateIsDelete(numIid, isDelete);
    }

    @Override
    public List<ItemPlay> createItemPlayBatch(List<Item> items, boolean isExist, boolean needGetTradePrice) {
        // List<ItemPlay> itemList = new ArrayList<ItemPlay>();
        //
        // for (Item item : items) {
        // ItemPlay itemPlay = convertToItemPlay(item, isExist);
        //
        // if (needGetTradePrice) {
        // // 特殊处理
        // if (itemPlay.getNick().equals("wuyuwei456")) {
        // // 设置为原价
        // itemPlay.setTradePrice(itemPlay.getPrice());
        // } else {
        // // 从标题中抠取批发价
        // double price = getPriceFromTitle(item.getTitle(),
        // itemPlay.getPrice());
        // if (price > 0) {
        //
        // if (price > itemPlay.getPrice()) {
        // price = itemPlay.getPrice();
        // }
        //
        // itemPlay.setTradePrice(price);
        //
        // // LOG.warn("item numIid:{}，设置批发价成功", item.getNumIid());
        // if (price / itemPlay.getPrice() < 0.2 && price <= 20) {
        // // 获取到的价格不足原价的30%，则先放库里
        // itemPlay.setNeedCheckPrice(LocalApproveStatus.NEED_CHECK_PRICE);
        // itemPlay.setLocalApproveStatus(LocalApproveStatus.INSTOCK);
        // }
        // } else {
        // // LOG.warn("item numIid:{}，设置批发价为售价",
        // // item.getNumIid());
        // itemPlay.setTradePrice(itemPlay.getPrice());
        // itemPlay.setNeedCheckPrice(LocalApproveStatus.NEED_CHECK_PRICE);
        // // 如果没有价格，先放在库里
        // itemPlay.setLocalApproveStatus(LocalApproveStatus.INSTOCK);
        // }
        // }
        // }
        //
        // itemList.add(itemPlay);
        // }
        //
        // return itemList;
        return null;
    }

    private static String[] specialChars = { "P", "F", "C", "-", "S", "/" };

    @Override
    public double getPriceFromTitle(String title, double originPrice) {
        if (StringUtils.isBlank(title)) {
            return -1;
        }
        title = title.toUpperCase();

        for (String specialChar : specialChars) {

            int index = title.lastIndexOf(specialChar);

            if (index <= 0) {
                continue;
            }

            index = index + 1;
            int endIndex = index;
            for (; endIndex < title.length(); endIndex++) {
                int chr = title.charAt(endIndex);
                if ((chr >= 48 && chr <= 57)) {

                } else {
                    break;
                }
            }

            String price = title.substring(index, endIndex);

            // 如果p后面以0开始，则表示是货号，不是价格
            if (price.startsWith("0")) {
                continue;
            }

            try {
                if (StringUtils.isNotBlank(price)) {
                    double getPrice = Double.valueOf(price);

                    // 大于原价，则不处理
                    if (getPrice > originPrice) {
                        continue;
                    } else {
                        return getPrice;
                    }
                }

            } catch (Exception e) {
                LOG.warn(e.getMessage(), e);
                LOG.warn("title:" + title);
            }
        }
        return -1;
    }

    @Override
    public boolean createItemPlayRef(List<ItemPlay> plays, List<ItemPropImg> propImgs, List<ItemImg> imgs,
            List<ItemLocation> locations, List<ItemDescr> descrs, List<ItemSku> skus) {
        return itemPlayDao.createItemPlayRef(plays, propImgs, imgs, locations, descrs, skus);
    }

    @Override
    public long countByNick(String nick, List<Long> sellerCids, String keyword, Double upPrice, Double lowPrice,
            String outerId, Integer localApproveStatus, Boolean tradePrice) {
        return itemPlayDao.countByNick(nick, outerId, tradePrice, localApproveStatus, sellerCids, keyword, upPrice,
                lowPrice);
    }

    @Override
    public ItemPlayDto findByNumIid(Long numIid, boolean onlySku) {
        return findByNumIid(numIid, false, onlySku);
    }

    @Override
    public ItemPlayDto findByNumIid(Long numIid, boolean onlyBasicInfo, boolean onlySku) {
        return findByNumIid(numIid, false, onlySku);
    }

    @Override
    public ProcessStatus updateItem(Long numIid, Double tradePrice, Integer status, Integer isDelete) {
        ProcessStatus processStatus = new ProcessStatus(false);
        ItemPlay item = itemPlayDao.findByNumIid(numIid);
        if (item == null) {
            return new ProcessStatus(false);
        }
        if (status != null) {
            item.setLocalApproveStatus(status);
        }
        if (tradePrice != null) {
            item.setTradePrice(tradePrice);
        }

        if (isDelete != null) {
            item.setIsDelete(isDelete);
        }
        boolean isSuccess = itemPlayDao.update(item) > 0;

        if (isSuccess) {
            // 更新缓存
            updateCache(String.valueOf(numIid), tradePrice, null, status);
        }

        processStatus.setSuccess(isSuccess);
        return processStatus;
    }

    private void updateCache(String numIid, Double tradePrice, Double reducePrice, Integer status) {
        if (Cache.get("itemDetail_" + numIid) != null) {
            ItemPlayDto itemDto = (ItemPlayDto) Cache.get("itemDetail_" + numIid);
            if (tradePrice != null) {
                itemDto.setTradePrice(tradePrice);
            }
            if (reducePrice != null) {
                itemDto.setReducePrice(reducePrice);
            }
            if (status != null) {
                itemDto.setLocalApproveStatus(status);
            }
            Cache.set("itemDetail_" + numIid, itemDto);
        }

    }

    @Override
    public ProcessStatus updateTradePrice(String nick, Long numIid, double tradePrice) {
        long result = itemPlayDao.updateTradePrice(nick, numIid, tradePrice);

        if (result > 0) {
            updateItem(numIid, tradePrice, null, null);
        }
        return new ProcessStatus(result > 0);
    }

    @Override
    public ProcessStatus updateStatus(String nick, Long numIid, int localApproveStatus, int approveStatus,
            int isDelete) {
        long result = itemPlayDao.updateStatus(nick, numIid, localApproveStatus, approveStatus, isDelete);
        return new ProcessStatus(result > 0);
    }

    @Override
    public List<ItemPlay> findByNick(String nick, Integer type, String title, String outerId, Boolean hasTradePrice,
            Integer pageNo, Integer pageSize) {
        return itemPlayDao.findByNick(nick, outerId, hasTradePrice, type, null, title, null, pageSize, pageNo, null,
                null, null);
    }

    @Override
    public List<ItemPlay> findByNick(String nick) {

        List<ItemPlay> items = itemPlayDao.findByNick(nick);
        return items;
    }

    @Override
    public long insertBatch(List<ItemPlay> itemPlays) {
        return itemPlayDao.insertBatch(itemPlays);
    }

    @Override
    public long addTBItemsToLocal(List<Item> items) {

        List<ItemPlay> itemPlays = createItemPlayBatch(items, false, true);
        return insertBatch(itemPlays);
    }

    @Override
    public boolean updateItems(List<ItemPlay> items) {
        // update时，不更新宝贝批发价
        // List<ItemPlay> itemPlays = createItemPlayBatch(items, true, false);
        return itemPlayDao.updateBatch(items);
    }

    @Override
    public ItemPlay updateByItem(ItemPlay itemPlay, Item item) {

        itemPlay.setApproveStatus(ApproveStatus.parseApproveStatus(item.getApproveStatus()));
        itemPlay.setCid(item.getCid());

        if (item.getDelistTime() != null) {
            itemPlay.setDeListTime(item.getDelistTime().getTime());
        }

        if (item.getListTime() != null) {
            itemPlay.setListTime(item.getListTime().getTime());
        }

        if (item.getCreated() != null) {
            // LOG.warn("item created:{}", item.getCreated());
            itemPlay.setCreated(item.getCreated().getTime());
        } else {
            // LOG.warn("created is null~~~~，设置为当前时间");
            itemPlay.setCreated(new Date().getTime());

        }
        itemPlay.setEmsFee(CommonUtils.String2Double(item.getEmsFee()));
        itemPlay.setExpressFee(CommonUtils.String2Double(item.getExpressFee()));
        itemPlay.setFreightPayer(FreightPayer.parseFreightPayer(item.getFreightPayer()));
        itemPlay.setHasShowCase(item.getHasShowcase());

        itemPlay.setModified(item.getModified().getTime());
        itemPlay.setNick(item.getNick());
        itemPlay.setNum(item.getNum());
        itemPlay.setNumIid(item.getNumIid());
        itemPlay.setOuterId(item.getOuterId());
        itemPlay.setPicUrl(item.getPicUrl());
        itemPlay.setPostFee(CommonUtils.String2Double(item.getPostFee()));
        itemPlay.setPostageId(item.getPostageId());
        itemPlay.setPrice(CommonUtils.String2Double(item.getPrice()));
        itemPlay.setPropsName(item.getPropsName());
        itemPlay.setSellerCids(item.getSellerCids());
        itemPlay.setTitle(item.getTitle());
        itemPlay.setGmtCreated(new Date());
        itemPlay.setIsDelete(0);

        // trade price

        if (itemPlay.getNick().equals("wuyuwei456")) {
            // 设置为原价
            itemPlay.setTradePrice(itemPlay.getPrice());
        } else {
            // 从标题中抠取批发价
            double price = getPriceFromTitle(item.getTitle(), itemPlay.getPrice());
            if (price > 0) {

                if (price > itemPlay.getPrice()) {
                    // 不更新批发价
                    price = itemPlay.getPrice();
                }

                itemPlay.setTradePrice(price);

                // LOG.warn("item numIid:{}，设置批发价成功", item.getNumIid());
                if (price / itemPlay.getPrice() < 0.2 && price <= 20) {
                    // 获取到的价格不足原价的30%，则先放库里
                    itemPlay.setNeedCheckPrice(LocalApproveStatus.NEED_CHECK_PRICE);
                    itemPlay.setLocalApproveStatus(LocalApproveStatus.INSTOCK);
                }

            } else if (itemPlay.getTradePrice() > itemPlay.getPrice()) {
                // 原来的价格有问题
                itemPlay.setTradePrice(itemPlay.getPrice());
                itemPlay.setNeedCheckPrice(LocalApproveStatus.NEED_CHECK_PRICE);
                // 如果没有价格，先放在库里
                itemPlay.setLocalApproveStatus(LocalApproveStatus.INSTOCK);
            } else {
                // 价格正常，则不更新
            }
        }

        return itemPlay;
    }

    @Override
    public String createNewDetailInfo(String page, Long numIid) {

        Document doc = Jsoup.parse(page, "UTF-8");

        Element itemBox = doc.getElementById("item-box");
        // 宝贝图片
        Element itemPics = itemBox.getElementById("item-pics");
        // 缩略图
        Elements itemThumb = itemPics.getElementsByClass("item-pics-thumb");
        // 宝贝详细信息
        Element itemInfo = itemBox.getElementById("item-info");
        // 所在地
        Element city = itemInfo.getElementById("city");
        // 宝贝标题
        Element itemTitle = itemInfo.getElementById("item-title");
        // 批发价
        Element tradePrice = itemInfo.getElementById("tradePrice");

        // 批发价
        Elements itemSave = itemInfo.getElementsByClass("item-save");

        Element taoqiprice = itemInfo.getElementById("taoqiprice");
        Element taoqipriceLabel = itemInfo.getElementById("taoqiprice-label");
        // sku
        Element skuList = itemInfo.getElementById("sku-list");

        // 售价
        Element price = itemInfo.getElementById("price");
        Element otherPrice = itemInfo.getElementById("other-price");

        Elements skuSelect = itemInfo.getElementsByClass("sku-select");
        Element zoom = itemPics.getElementById("zoom");

        Elements zoomImg = zoom.getElementsByClass("zoomImg");
        Elements itemPicBig = zoom.getElementsByClass("item-pics-big");
        ItemPlayDto itemPlay = findByNumIid(numIid, false);

        if (itemPlay == null) {
            return null;
        }
        List<ItemImg> itemImgs = itemPlay.getItemImgs();

        List<ItemSku> itemSkus = itemPlay.getSkus();
        StringBuilder sb = new StringBuilder();
        StringBuilder sb1 = new StringBuilder();
        int i = 0;
        if (CollectionUtils.isNotEmpty(itemImgs)) {
            for (ItemImg itemImg : itemImgs) {
                if (i == 0) {
                    sb.append("<li class=\"cur\"><img src=" + itemImg.getUrl() + "_60x60.jpg turl=" + itemImg.getUrl()
                            + " /></li>");
                } else {
                    sb.append("<li class=\" \"><img src=" + itemImg.getUrl() + "_60x60.jpg turl=" + itemImg.getUrl()
                            + " /></li>");
                }
                i++;

            }

            sb1.append("<img src=" + itemImgs.get(0).getUrl() + " class=\"item-pics-big\">");
            itemThumb.append(sb.toString());
            zoomImg.attr("src", itemImgs.get(0).getUrl());
            itemPicBig.attr("src", itemImgs.get(0).getUrl());

        }

        if (itemPlay.getReducePrice() != 0) {
            itemSave.append("节省  ￥" + itemPlay.getReducePrice());
            taoqiprice.append("￥" + (itemPlay.getTradePrice() - itemPlay.getReducePrice()));

            tradePrice.append("￥" + itemPlay.getTradePrice());
            price.append("￥" + itemPlay.getPrice());
        } else {
            itemSave.attr("style", "display:none");
            // tradePrice.attr(attributeKey)
            taoqipriceLabel.empty();
            taoqipriceLabel.html("批发价：");
            taoqiprice.append("￥" + itemPlay.getTradePrice());

            tradePrice.attr("style", "display:none");
            otherPrice.html("售价:<s id=\"price\">￥" + itemPlay.getPrice() + "</s>");

        }

        city.append(itemPlay.getState() + " " + itemPlay.getCity());

        return doc.html();

    }

    @Override
    public void updateItemToDb(List<Item> items) {

        List<Item> newItems = new ArrayList<Item>();
        List<ItemPlay> needUpdate = new ArrayList<ItemPlay>();
        for (Item item : items) {
            if (!item.getStuffStatus().equals("new")) {
                LOG.warn("item stuffstatus:{},不是新品", item.getStuffStatus());
                continue;
            }
            ItemPlay itemPlay = itemPlayDao.findByNumIid(item.getNumIid());

            // 本地不存在则保存至仓库中
            if (itemPlay == null) {
                LOG.warn("item numIid:{} is new, save in store", item.getNumIid());
                newItems.add(item);
            } else {
                // 更新宝贝信息
                // update itemplay with item
                itemPlay = updateByItem(itemPlay, item);
                needUpdate.add(itemPlay);
            }

            // LOG.warn(item.getProps());

            // LOG.warn(item.getPropsName());
        }

        // 添加新宝贝至数据库

        if (CollectionUtils.isNotEmpty(newItems)) {
            LOG.warn("add new item to database~~~~~");
            addTBItemsToLocal(newItems);
        }

        // 更新宝贝信息

        if (CollectionUtils.isNotEmpty(needUpdate)) {
            LOG.warn("update item info~~~~~~~~");
            updateItems(needUpdate);
        }

        // 更新宝贝其他相关信息
        processSyncItemDesc(items);

        processSyncItemImg(items);

        processSyncItemLocation(items);

        processSyncItemPropImgs(items);

        processSyncItemSkus(items);
    }

    /**
     * 同步宝贝描述信息
     */
    private boolean processSyncItemDesc(List<Item> items) {
        return descrLogic.insertOrUpdateBatch(items);
    }

    /**
     * 同步宝贝图片
     * 
     * @param items
     * @return
     */
    private boolean processSyncItemImg(List<Item> items) {

        LOG.warn("同步宝贝图片信息中~~~~~");

        for (Item item : items) {
            // LOG.warn("sync itemImg for item numIid:{}", item.getNumIid());
            itemImgLogic.delete(item.getNumIid());
        }

        List<ItemImg> imgs = itemImgLogic.createItemImgBatch(items);
        return itemImgLogic.insertBatch(imgs) > 0;
    }

    /**
     * 同步宝贝位置
     * 
     * @param items
     * @return
     */
    private boolean processSyncItemLocation(List<Item> items) {
        LOG.warn("同步宝贝位置信息中~~~~~");
        return locationLogic.insertOrUpdateBatch(items);
    }

    /**
     * 同步宝贝属性图片
     * 
     * @param items
     * @return
     */
    private boolean processSyncItemPropImgs(List<Item> items) {
        LOG.warn("同步宝贝属性图片信息中~~~~~");
        for (Item item : items) {
            // LOG.warn("sync itemPropImg for item:{}", item.getNumIid());
            propImgLogic.delete(item.getNumIid());
        }

        List<ItemPropImg> imgs = propImgLogic.createItemPropImgBatch(items);
        return propImgLogic.insertBatch(imgs) > 0;
    }

    /**
     * 同步sku
     * 
     * @param items
     * @return
     */
    private boolean processSyncItemSkus(List<Item> items) {
        LOG.warn("同步sku信息中~~~~~");
        for (Item item : items) {
            // LOG.warn("sync itemSku for item:{}", item.getNumIid());
            skuLogic.delete(item.getNumIid());
        }

        List<ItemSku> skus = skuLogic.createItemSkuBatch(items);
        return skuLogic.insertBatch(skus) > 0;
    }

    @Override
    public long updateIsOauth(String nick, Integer isOauth) {
        return itemPlayDao.updateIsOauth(nick, isOauth);
    }

    @Override
    public List<ItemPlay> findInCompleteItems() {
        return itemPlayDao.findInCompleteItems();
    }

    @Override
    public List<ItemPlay> findNoDescItems() {
        return itemPlayDao.findNoDescItems();
    }

    @Override
    public List<ItemPlay> findNeedCheckPriceItems(Integer pageNo, Integer pageSize) {
        return itemPlayDao.findNeedCheckPriceItems(pageNo, pageSize);
    }

    @Override
    public long countNeedCheckPriceItems() {
        return itemPlayDao.countNeedCheckPriceItems();

    }

    @Override
    public void updateCache(String[] numIidsArr, Integer status, Double tradePrice, Double reducePrice) {

        for (String id : numIidsArr) {
            updateCache(id, tradePrice, reducePrice, status);
        }

    }

    @Override
    public List<ItemRankDto> getItemRank(String nick, Integer type, Integer pageNo, Integer pageSize) {
        // Stalls stall = stallsDao.findByAccount(accountId);
        if (type == ItemRankType.COLLECT) {
            return itemPlayDao.getItemByCollection(nick, pageNo, pageSize);
        } else {
            return itemPlayDao.getItemByOrders(nick, pageNo, pageSize);
        }
    }

    @Override
    public long countForRank(String nick, Integer type) {
        // Stalls stall = stallsDao.findByAccount(accountId);
        if (type == ItemRankType.COLLECT) {
            return itemPlayDao.countForCollectRank(nick);
        } else {
            return itemPlayDao.countForOrderRank(nick);
        }
    }

    @Override
    public String matchOuterId(String title) {
        Pattern p = Pattern.compile("[A-Za-z0-9-/]+");
        Matcher m = p.matcher(title);

        int i = 0;

        List<String> array = new ArrayList<String>();
        while (m.find()) {
            array.add(m.group());
        }
        int index = 0;
        for (int j = 0; j < array.size(); j++) {
            if (array.get(j).length() > array.get(index).length())
                index = j;
        }
        System.out.println(array.get(index));
        return array.get(index);
    }

}
