/**    
 * @author mlc  
 * @version 1.0  
 *
 * 2015年7月15日   
 */
package com.rfw.jiajia.item.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rfw.jiajia.item.dao.IItemLocationDao;
import com.rfw.jiajia.item.dao.mapper.interfaces.IItemLocationDaoMapper;
import com.rfw.jiajia.item.models.ItemLocation;

import mybatisplay.IbatisSessionFactory;

public class ItemLocationDaoImpl implements IItemLocationDao {

    private static final Logger LOG = LoggerFactory.getLogger(ItemLocationDaoImpl.class);

    private static IItemLocationDao instance = new ItemLocationDaoImpl();

    private ItemLocationDaoImpl() {
    }

    public static IItemLocationDao getInstance() {
        return instance;
    }

    @Override
    public long insert(ItemLocation location) {
        long result = 0L;
        SqlSession session = IbatisSessionFactory.get().openSession(false);

        try {
            IItemLocationDaoMapper mapper = session.getMapper(IItemLocationDaoMapper.class);
            result = mapper.insert(location);
            session.commit(true);
        } catch (Exception e) {
            session.rollback(true);
            LOG.warn(e.getMessage(), e);
        } finally {
            session.close();
        }
        return result;
    }

    @Override
    public long insertBatch(List<ItemLocation> locations) {
        long result = 0l;
        SqlSession session = IbatisSessionFactory.get().openSession(false);
        Map<String, Object> params = new HashMap<String, Object>();
        if (CollectionUtils.isNotEmpty(locations)) {
            params.put("itemLocations", locations);
            try {
                IItemLocationDaoMapper mapper = session.getMapper(IItemLocationDaoMapper.class);
                result = mapper.insertBatch(params);
                session.commit(true);
            } catch (Exception e) {
                session.rollback(true);
                LOG.warn(e.getMessage(), e);
            } finally {
                session.close();
            }
        }

        return result;
    }

    @Override
    public ItemLocation findByNumIid(Long numIid) {
        ItemLocation location = null;

        SqlSession session = IbatisSessionFactory.get().openSession();

        try {
            IItemLocationDaoMapper mapper = session.getMapper(IItemLocationDaoMapper.class);
            location = mapper.findByNumIid(numIid);
        } catch (Exception e) {
            LOG.warn(e.getMessage(), e);
        } finally {
            session.close();
        }

        return location;
    }

    @Override
    public boolean updateBatch(List<ItemLocation> locations) {
        boolean result = false;
        SqlSession session = IbatisSessionFactory.get().openSession(false);

        try {
            IItemLocationDaoMapper mapper = session.getMapper(IItemLocationDaoMapper.class);
            for (ItemLocation location : locations) {
                mapper.update(location);
            }
            result = true;
            session.commit(true);
        } catch (Exception e) {
            session.rollback(true);
            LOG.warn(e.getMessage(), e);
        } finally {
            session.close();
        }
        return result;
    }

}
