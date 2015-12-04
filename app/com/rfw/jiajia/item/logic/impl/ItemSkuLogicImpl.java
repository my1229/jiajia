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

import com.rfw.jiajia.item.dao.IItemSkuDao;
import com.rfw.jiajia.item.dao.impl.ItemSkuDaoImpl;
import com.rfw.jiajia.item.logic.IItemSkuLogic;
import com.rfw.jiajia.item.models.ItemSku;
import com.taobao.api.domain.Item;
import com.taobao.api.domain.Sku;

public class ItemSkuLogicImpl implements IItemSkuLogic {

    private static final Logger LOG = LoggerFactory.getLogger(ItemSkuLogicImpl.class);

    private static IItemSkuLogic instance = new ItemSkuLogicImpl();

    private ItemSkuLogicImpl() {
    }

    public static IItemSkuLogic getInstance() {
        return instance;
    }

    private IItemSkuDao skuDao = ItemSkuDaoImpl.getInstance();

    @Override
    public List<ItemSku> createItemSkuBatch(List<Item> items) {
        List<ItemSku> itemSkus = new ArrayList<ItemSku>();

        for (Item item : items) {
            if (CollectionUtils.isNotEmpty(item.getSkus())) {
                List<ItemSku> skus = convertToSkuDB(item.getSkus(), item.getNumIid());
                itemSkus.addAll(skus);
            }

        }
        return itemSkus;
    }

    @Override
    public long insertBatch(List<ItemSku> skus) {
        return skuDao.insertBatch(skus);
    }

    private List<ItemSku> convertToSkuDB(List<Sku> skus, Long numIid) {
        List<ItemSku> itemSkus = new ArrayList<ItemSku>();

        for (Sku sku : skus) {
            ItemSku itemSku = new ItemSku();
            itemSku.setBarCode(sku.getBarcode());
            itemSku.setCreated(sku.getCreated());
            itemSku.setAddTs(new Date());
            itemSku.setModified(sku.getModified());
            itemSku.setNumIid(numIid);
            itemSku.setOuterId(sku.getOuterId());
            itemSku.setPrice(sku.getPrice());
            itemSku.setProperties(sku.getProperties());
            itemSku.setPropertiesName(sku.getPropertiesName());
            itemSku.setQuantity(sku.getQuantity());
            itemSku.setSkuId(sku.getSkuId());
            itemSkus.add(itemSku);
            // LOG.warn("ssku Quantity:{}", sku.getQuantity());
        }

        return itemSkus;
    }

    @Override
    public List<ItemSku> findByNumIid(Long numIid) {
        return skuDao.findByNumIid(numIid);
    }

    @Override
    public long delete(Long numIid) {
        return skuDao.delete(numIid);
    }

}
