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

import com.rfw.jiajia.item.dao.IItemPropImgDao;
import com.rfw.jiajia.item.dao.impl.ItemPropImgDaoImpl;
import com.rfw.jiajia.item.logic.IItemPropImgLogic;
import com.rfw.jiajia.item.models.ItemPropImg;
import com.taobao.api.domain.Item;
import com.taobao.api.domain.PropImg;

public class ItemPropImgLogicImpl implements IItemPropImgLogic {

    private static final Logger LOG = LoggerFactory.getLogger(ItemPropImgLogicImpl.class);

    private static IItemPropImgLogic instance = new ItemPropImgLogicImpl();

    private ItemPropImgLogicImpl() {
    }

    public static IItemPropImgLogic getInstance() {
        return instance;
    }

    private IItemPropImgDao propImgDao = ItemPropImgDaoImpl.getInstance();

    @Override
    public List<ItemPropImg> createItemPropImgBatch(List<Item> items) {
        List<ItemPropImg> imgs = new ArrayList<ItemPropImg>();

        for (Item item : items) {
            if (CollectionUtils.isNotEmpty(item.getPropImgs())) {
                List<ItemPropImg> itemImgs = covertToItemImgDB(item.getPropImgs(), item.getNumIid());
                imgs.addAll(itemImgs);
            }

        }
        return imgs;
    }

    @Override
    public long insertBatch(List<ItemPropImg> imgs) {
        return propImgDao.insertBatch(imgs);
    }

    private List<ItemPropImg> covertToItemImgDB(List<PropImg> tbImgs, Long numIid) {
        List<ItemPropImg> itemImgs = new ArrayList<ItemPropImg>();

        for (PropImg tbImg : tbImgs) {

            ItemPropImg itemImg = new ItemPropImg();
            itemImg.setId(tbImg.getId());
            itemImg.setPosition(tbImg.getPosition());
            itemImg.setUrl(tbImg.getUrl());
            itemImg.setNumIid(numIid);
            itemImg.setAddTs(new Date());
            itemImgs.add(itemImg);
        }

        return itemImgs;
    }

    @Override
    public List<ItemPropImg> findByNumIid(Long numIid) {
        return propImgDao.findByNumIid(numIid);
    }

    @Override
    public long delete(Long numIid) {
        return propImgDao.delete(numIid);
    }

}
