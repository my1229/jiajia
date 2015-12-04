/**
 * @author mlc
 * @date 2015年7月15日
 * @version 1.0
 */
package com.rfw.jiajia.item.logic.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rfw.jiajia.item.dao.IItemImgDao;
import com.rfw.jiajia.item.dao.impl.ItemImgDaoImpl;
import com.rfw.jiajia.item.logic.IItemImgLogic;
import com.rfw.jiajia.item.models.ItemImg;
import com.taobao.api.domain.Item;

public class ItemImgLogicImpl implements IItemImgLogic {

    private static final Logger LOG = LoggerFactory.getLogger(ItemImgLogicImpl.class);

    private static IItemImgLogic instance = new ItemImgLogicImpl();

    private ItemImgLogicImpl() {
    }

    public static IItemImgLogic getInstance() {
        return instance;
    }

    private IItemImgDao imgDao = ItemImgDaoImpl.getInstance();

    @Override
    public List<ItemImg> createItemImgBatch(List<Item> items) {

        List<ItemImg> imgs = new ArrayList<ItemImg>();

        for (Item item : items) {
            if (CollectionUtils.isNotEmpty(item.getItemImgs())) {
                List<ItemImg> itemImgs = covertToItemImgDB(item.getItemImgs(), item.getNumIid());
                imgs.addAll(itemImgs);
            }

        }
        return imgs;
    }

    @Override
    public long insertBatch(List<ItemImg> imgs) {
        return imgDao.insertBatch(imgs);
    }

    private List<ItemImg> covertToItemImgDB(List<com.taobao.api.domain.ItemImg> tbImgs, Long numIid) {
        List<ItemImg> itemImgs = new ArrayList<ItemImg>();

        for (com.taobao.api.domain.ItemImg tbImg : tbImgs) {
            ItemImg itemImg = new ItemImg();
            itemImg.setImgId(tbImg.getId());
            itemImg.setPosition(tbImg.getPosition());
            itemImg.setUrl(tbImg.getUrl());
            itemImg.setNumIid(numIid);
            itemImg.setAddTs(new Date());
            if (tbImg.getCreated() != null) {
                itemImg.setCreated(tbImg.getCreated().getTime());
            }

            itemImgs.add(itemImg);
        }

        return itemImgs;
    }

    @Override
    public List<ItemImg> findByNumIid(Long numIid) {
        return imgDao.findByNumIid(numIid);
    }

    @Override
    public boolean insertOrUpdateBatch(List<Item> items) {
        return false;
    }

    @Override
    public long delete(Long numIid) {
        // LOG.warn("delete for numIid:{}", numIid);
        return imgDao.delete(numIid);
    }
}
