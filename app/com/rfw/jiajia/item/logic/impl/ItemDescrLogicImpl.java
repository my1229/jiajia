/**
 * @author mlc
 * @date 2015年7月15日
 * @version 1.0
 */
package com.rfw.jiajia.item.logic.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rfw.jiajia.item.dao.IItemDescrDao;
import com.rfw.jiajia.item.dao.impl.ItemDescrDaoImpl;
import com.rfw.jiajia.item.logic.IItemDescrLogic;
import com.rfw.jiajia.item.logic.IItemPlayLogic;
import com.rfw.jiajia.item.models.ItemDescr;
import com.taobao.api.domain.Item;

public class ItemDescrLogicImpl implements IItemDescrLogic {

    private static final Logger LOG = LoggerFactory.getLogger(ItemDescrLogicImpl.class);

    private static IItemPlayLogic itemPlayLogic = new ItemPlayLogicImpl();

    private static IItemDescrLogic instance = new ItemDescrLogicImpl();

    private ItemDescrLogicImpl() {
    }

    public static IItemDescrLogic getInstance() {
        return instance;
    }

    private IItemDescrDao descDao = ItemDescrDaoImpl.getInstance();

    @Override
    public List<ItemDescr> createItemDescrBatch(List<Item> items) {

        List<ItemDescr> descrs = new ArrayList<ItemDescr>();

        for (Item item : items) {
            ItemDescr descr = new ItemDescr();
            if (item.getDesc() != null) {
                descr.setDescr(item.getDesc());
                descr.setNumIid(item.getNumIid());
                descr.setAddTs(new Date());
                descrs.add(descr);
            }

        }
        return descrs;
    }

    @Override
    public long insertBatch(List<ItemDescr> descrs) {
        return descDao.insertBatch(descrs);
    }

    @Override
    public ItemDescr findByNumIid(Long numIid) {

        return descDao.findByNumIid(numIid);
    }

    @Override
    public boolean updateBatch(List<ItemDescr> descrs) {
        return descDao.updateBatch(descrs);
    }

    @Override
    public boolean insertOrUpdateBatch(List<Item> items) {

        boolean isSuccess = false;
        List<ItemDescr> insertDescrs = new ArrayList<ItemDescr>();

        List<ItemDescr> updateDescrs = new ArrayList<ItemDescr>();

        for (Item item : items) {

            String desc = item.getDesc();

            if (StringUtils.isBlank(desc)) {
                LOG.warn("nick : " + item.getNick() + ", numIid:" + item.getNumIid());
                try {
                    // desc = itemPlayLogic.getDesc(item.getNick(),
                    // item.getNumIid());
                } catch (Exception e) {
                    LOG.warn(e.getMessage(), e);
                }
            }
            if (!StringUtils.isBlank(desc)) {
                ItemDescr descr = findByNumIid(item.getNumIid());

                if (descr != null) {
                    descr.setDescr(desc);
                    updateDescrs.add(descr);
                } else {
                    descr = new ItemDescr();
                    descr.setNumIid(item.getNumIid());
                    descr.setDescr(desc);
                    insertDescrs.add(descr);
                }

            }

        }
        insertBatch(insertDescrs);
        updateBatch(updateDescrs);
        isSuccess = true;
        return isSuccess;
    }

}
