/**
 * @author mlc
 * @date 2015年7月25日
 * @version 1.0
 */
package com.rfw.jiajia.item.logic.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rfw.common.base.dto.ProcessStatus;
import com.rfw.jiajia.item.dao.IItemOnSaleDao;
import com.rfw.jiajia.item.dao.IItemPlayDao;
import com.rfw.jiajia.item.dao.impl.ItemOnSaleDaoImpl;
import com.rfw.jiajia.item.dao.impl.ItemPlayDaoImpl;
import com.rfw.jiajia.item.dto.ItemOnSaleDto;
import com.rfw.jiajia.item.logic.IItemOnSaleLogic;
import com.rfw.jiajia.item.models.ItemOnSale;
import com.rfw.jiajia.item.models.ItemPlay;

public class ItemOnSaleLogicImpl implements IItemOnSaleLogic {

    private static final Logger LOG = LoggerFactory.getLogger(ItemOnSaleLogicImpl.class);

    private static IItemOnSaleLogic instance = new ItemOnSaleLogicImpl();

    private ItemOnSaleLogicImpl() {
    }

    public static IItemOnSaleLogic getInstance() {
        return instance;
    }

    private IItemOnSaleDao onSaleDao = ItemOnSaleDaoImpl.getInstance();

    private IItemPlayDao playDao = ItemPlayDaoImpl.getInstance();

    @Override
    public long insert(ItemOnSale onSale) {

        return onSaleDao.insert(onSale);
    }

    @Override
    public List<ItemPlay> findByAccount(String account, int pageNo, int pageSize) {
        List<ItemOnSale> onSales = onSaleDao.findByAccount(account, pageNo, pageSize);
        List<ItemPlay> itemPlays = new ArrayList<ItemPlay>();

        if (CollectionUtils.isEmpty(onSales)) {
            return itemPlays;
        }

        for (ItemOnSale onSale : onSales) {
            ItemPlay play = playDao.findByNumIid(onSale.getNumIid());
            // 上传时间
            play.setCreated(onSale.getPublishTime());
            itemPlays.add(play);
        }

        return itemPlays;
    }

    @Override
    public long countByAccount(String account) {
        return onSaleDao.countByAccount(account);
    }

    @Override
    public List<ItemOnSaleDto> checkByDay(Long start, Long end, Integer pageNo, Integer pageSize) {
        return onSaleDao.checkByDay(start, end, pageNo, pageSize);
    }

    @Override
    public long count(Long start, Long end) {
        return onSaleDao.count(start, end);
    }

    @Override
    public ProcessStatus checkIsCopy(String accountId, Long numIid) {

        ItemOnSale onSale = onSaleDao.findByAccountAndNumIid(accountId, numIid);

        boolean isSuccess = onSale == null ? false : true;

        return new ProcessStatus(isSuccess);
    }
}
