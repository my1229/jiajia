/**
 * @author mlc
 * @date 2015年7月15日
 * @version 1.0
 */
package com.rfw.jiajia.item.logic.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rfw.jiajia.item.dao.IItemLocationDao;
import com.rfw.jiajia.item.dao.impl.ItemLocationDaoImpl;
import com.rfw.jiajia.item.logic.IItemLocationLogic;
import com.rfw.jiajia.item.models.ItemLocation;
import com.taobao.api.domain.Item;
import com.taobao.api.domain.Location;

public class ItemLocationLogicImpl implements IItemLocationLogic {

    private static final Logger LOG = LoggerFactory.getLogger(ItemLocationLogicImpl.class);

    private static IItemLocationLogic instance = new ItemLocationLogicImpl();

    private ItemLocationLogicImpl() {
    }

    public static IItemLocationLogic getInstance() {
        return instance;
    }

    private IItemLocationDao locationDao = ItemLocationDaoImpl.getInstance();

    @Override
    public List<ItemLocation> createItemLocationBatch(List<Item> items) {

        List<ItemLocation> locations = new ArrayList<ItemLocation>();

        for (Item item : items) {

            if (item.getLocation() != null) {
                ItemLocation l = convertToLocationDB(item.getLocation(), item.getNumIid());
                locations.add(l);
            }

        }
        return locations;
    }

    @Override
    public long insertBatch(List<ItemLocation> locations) {
        return locationDao.insertBatch(locations);
    }

    private ItemLocation convertToLocationDB(Location location, Long numIid) {
        ItemLocation itemLocation = new ItemLocation();
        itemLocation.setCity(location.getCity());
        itemLocation.setState(location.getState());
        itemLocation.setAddTs(new Date());
        itemLocation.setNumIid(numIid);
        return itemLocation;
    }

    @Override
    public ItemLocation findByNumIid(Long numIid) {
        return locationDao.findByNumIid(numIid);
    }

    @Override
    public boolean insertOrUpdateBatch(List<Item> items) {

        List<ItemLocation> insertBatch = new ArrayList<ItemLocation>();

        List<ItemLocation> updateBatch = new ArrayList<ItemLocation>();

        for (Item item : items) {
            ItemLocation itemLocation = findByNumIid(item.getNumIid());
            if (itemLocation != null) {
                updateBatch.add(itemLocation);
            } else {
                itemLocation = convertToLocationDB(item.getLocation(), item.getNumIid());
                insertBatch.add(itemLocation);
            }

        }
        insertBatch(insertBatch);
        updateBatch(updateBatch);
        return false;
    }

    @Override
    public boolean updateBatch(List<ItemLocation> locations) {
        return locationDao.updateBatch(locations);
    }

}
