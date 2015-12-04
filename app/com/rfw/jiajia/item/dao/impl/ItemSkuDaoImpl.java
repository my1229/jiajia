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
import org.apache.commons.collections.ListUtils;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rfw.jiajia.item.dao.IItemSkuDao;
import com.rfw.jiajia.item.dao.mapper.interfaces.IItemSkuDaoMapper;
import com.rfw.jiajia.item.models.ItemSku;

import mybatisplay.IbatisSessionFactory;

public class ItemSkuDaoImpl implements IItemSkuDao {

    private static final Logger LOG = LoggerFactory.getLogger(ItemSkuDaoImpl.class);

    private static IItemSkuDao instance = new ItemSkuDaoImpl();

    private ItemSkuDaoImpl() {
    }

    public static IItemSkuDao getInstance() {
        return instance;
    }

    @Override
    public long insert(ItemSku sku) {
        long result = 0L;
        SqlSession session = IbatisSessionFactory.get().openSession(false);

        try {
            IItemSkuDaoMapper mapper = session.getMapper(IItemSkuDaoMapper.class);
            result = mapper.insert(sku);
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
    public long insertBatch(List<ItemSku> skus) {
        long result = 0l;
        SqlSession session = IbatisSessionFactory.get().openSession(false);
        Map<String, Object> params = new HashMap<String, Object>();
        if (CollectionUtils.isNotEmpty(skus)) {
            params.put("itemSkus", skus);
            try {
                IItemSkuDaoMapper mapper = session.getMapper(IItemSkuDaoMapper.class);
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
    public List<ItemSku> findByNumIid(Long numIid) {
        List<ItemSku> result = ListUtils.EMPTY_LIST;
        SqlSession session = IbatisSessionFactory.get().openSession();

        try {
            IItemSkuDaoMapper mapper = session.getMapper(IItemSkuDaoMapper.class);
            result = mapper.findByNumIid(numIid);
        } catch (Exception e) {
            session.rollback(true);
            LOG.warn(e.getMessage(), e);
        } finally {
            session.close();
        }
        return result;
    }

    @Override
    public long delete(Long numIid) {
        long result = 0L;
        SqlSession session = IbatisSessionFactory.get().openSession(false);

        try {
            IItemSkuDaoMapper mapper = session.getMapper(IItemSkuDaoMapper.class);
            result = mapper.delete(numIid);
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
