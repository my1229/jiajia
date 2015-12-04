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

import com.rfw.jiajia.item.dao.IItemDescrDao;
import com.rfw.jiajia.item.dao.mapper.interfaces.IItemDescrDaoMapper;
import com.rfw.jiajia.item.models.ItemDescr;

import mybatisplay.IbatisSessionFactory;

public class ItemDescrDaoImpl implements IItemDescrDao {
    private static final Logger LOG = LoggerFactory.getLogger(ItemDescrDaoImpl.class);

    private static IItemDescrDao instance = new ItemDescrDaoImpl();

    private ItemDescrDaoImpl() {
    }

    public static IItemDescrDao getInstance() {
        return instance;
    }

    @Override
    public long insert(ItemDescr descr) {
        long result = 0L;
        SqlSession session = IbatisSessionFactory.get().openSession(false);

        try {
            IItemDescrDaoMapper mapper = session.getMapper(IItemDescrDaoMapper.class);
            result = mapper.insert(descr);
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
    public long insertBatch(List<ItemDescr> descrs) {
        long result = 0l;
        SqlSession session = IbatisSessionFactory.get().openSession(false);
        Map<String, Object> params = new HashMap<String, Object>();
        if (CollectionUtils.isNotEmpty(descrs)) {
            params.put("itemDescrs", descrs);
            try {
                IItemDescrDaoMapper mapper = session.getMapper(IItemDescrDaoMapper.class);
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
    public ItemDescr findByNumIid(Long numIid) {
        ItemDescr result = null;
        SqlSession session = IbatisSessionFactory.get().openSession(false);

        try {
            IItemDescrDaoMapper mapper = session.getMapper(IItemDescrDaoMapper.class);
            result = mapper.findByNumIid(numIid);
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
    public ItemDescr findById(Long id) {
        ItemDescr result = null;
        SqlSession session = IbatisSessionFactory.get().openSession(false);

        try {
            IItemDescrDaoMapper mapper = session.getMapper(IItemDescrDaoMapper.class);
            result = mapper.findById(id);
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
    public boolean updateBatch(List<ItemDescr> descrs) {
        boolean result = false;
        SqlSession session = IbatisSessionFactory.get().openSession(false);
        Map<String, Object> params = new HashMap<String, Object>();
        if (CollectionUtils.isNotEmpty(descrs)) {
            params.put("itemDescrs", descrs);
        }

        try {
            IItemDescrDaoMapper mapper = session.getMapper(IItemDescrDaoMapper.class);

            for (ItemDescr descr : descrs) {
                mapper.update(descr);
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
