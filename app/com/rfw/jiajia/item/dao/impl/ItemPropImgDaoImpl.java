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

import com.rfw.jiajia.item.dao.IItemPropImgDao;
import com.rfw.jiajia.item.dao.mapper.interfaces.IItemPropImgDaoMapper;
import com.rfw.jiajia.item.models.ItemPropImg;

import mybatisplay.IbatisSessionFactory;

public class ItemPropImgDaoImpl implements IItemPropImgDao {

    private static final Logger LOG = LoggerFactory.getLogger(ItemPropImgDaoImpl.class);

    private static IItemPropImgDao instance = new ItemPropImgDaoImpl();

    private ItemPropImgDaoImpl() {
    }

    public static IItemPropImgDao getInstance() {
        return instance;
    }

    @Override
    public long insert(ItemPropImg propImg) {
        long result = 0L;
        SqlSession session = IbatisSessionFactory.get().openSession(false);

        try {
            IItemPropImgDaoMapper mapper = session.getMapper(IItemPropImgDaoMapper.class);
            result = mapper.insert(propImg);
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
    public long insertBatch(List<ItemPropImg> imgs) {
        long result = 0l;
        SqlSession session = IbatisSessionFactory.get().openSession(false);
        Map<String, Object> params = new HashMap<String, Object>();
        if (CollectionUtils.isNotEmpty(imgs)) {
            params.put("itemPropImgs", imgs);
            try {
                IItemPropImgDaoMapper mapper = session.getMapper(IItemPropImgDaoMapper.class);
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
    public List<ItemPropImg> findByNumIid(Long numIid) {
        List<ItemPropImg> result = ListUtils.EMPTY_LIST;
        SqlSession session = IbatisSessionFactory.get().openSession();

        try {
            IItemPropImgDaoMapper mapper = session.getMapper(IItemPropImgDaoMapper.class);
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
            IItemPropImgDaoMapper mapper = session.getMapper(IItemPropImgDaoMapper.class);
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
