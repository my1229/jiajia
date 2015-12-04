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

import com.rfw.jiajia.item.dao.IItemImgDao;
import com.rfw.jiajia.item.dao.mapper.interfaces.IItemImgDaoMapper;
import com.rfw.jiajia.item.models.ItemImg;

import mybatisplay.IbatisSessionFactory;

public class ItemImgDaoImpl implements IItemImgDao {

    private static final Logger LOG = LoggerFactory.getLogger(ItemImgDaoImpl.class);

    private static IItemImgDao instance = new ItemImgDaoImpl();

    private ItemImgDaoImpl() {
    }

    public static IItemImgDao getInstance() {
        return instance;
    }

    @Override
    public long insert(ItemImg itemImg) {
        long result = 0L;
        SqlSession session = IbatisSessionFactory.get().openSession(false);

        try {
            IItemImgDaoMapper mapper = session.getMapper(IItemImgDaoMapper.class);
            result = mapper.insert(itemImg);
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
    public long insertBatch(List<ItemImg> imgs) {
        long result = 0l;
        SqlSession session = IbatisSessionFactory.get().openSession(false);
        Map<String, Object> params = new HashMap<String, Object>();
        if (CollectionUtils.isNotEmpty(imgs)) {
            params.put("itemImgs", imgs);
            try {
                IItemImgDaoMapper mapper = session.getMapper(IItemImgDaoMapper.class);
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
    public List<ItemImg> findByNumIid(Long numIid) {
        List<ItemImg> result = ListUtils.EMPTY_LIST;
        SqlSession session = IbatisSessionFactory.get().openSession(false);

        try {
            IItemImgDaoMapper mapper = session.getMapper(IItemImgDaoMapper.class);
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
    public long delete(Long numIid) {
        long result = 0L;
        SqlSession session = IbatisSessionFactory.get().openSession(false);

        try {
            IItemImgDaoMapper mapper = session.getMapper(IItemImgDaoMapper.class);
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
