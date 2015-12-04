/**   
 * @Description: TODO
 * @author mlc
 * @date 2015年8月20日 下午5:01:21 
 * @version V1.0   
 */

package com.rfw.jiajia.shopcart.dao.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.ListUtils;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rfw.jiajia.shopcart.dao.IShopCartItemDao;
import com.rfw.jiajia.shopcart.dao.mapper.interfaces.IShopCartItemDaoMapper;
import com.rfw.jiajia.shopcart.models.ShopCartItem;

import mybatisplay.IbatisSessionFactory;

public class ShopCartItemDaoImpl implements IShopCartItemDao {

    private static final Logger LOG = LoggerFactory.getLogger(ShopCartItemDaoImpl.class);

    private static IShopCartItemDao instance = new ShopCartItemDaoImpl();

    private ShopCartItemDaoImpl() {
    }

    public static IShopCartItemDao getInstance() {
        return instance;
    }

    @Override
    public long insert(ShopCartItem item) {
        long result = 0L;
        SqlSession session = IbatisSessionFactory.get().openSession(false);

        try {
            IShopCartItemDaoMapper mapper = session.getMapper(IShopCartItemDaoMapper.class);
            result = mapper.insert(item);
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
    public long update(ShopCartItem item) {
        long result = 0L;
        SqlSession session = IbatisSessionFactory.get().openSession(false);

        try {
            IShopCartItemDaoMapper mapper = session.getMapper(IShopCartItemDaoMapper.class);
            result = mapper.update(item);
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
    public long updateBatch(String account, String ids, Integer status) {
        long result = 0L;
        SqlSession session = IbatisSessionFactory.get().openSession(false);

        Map<String, Object> map = new HashMap<String, Object>();
        String[] idsArr = ids.split(",");

        map.put("account", account);
        map.put("ids", Arrays.asList(idsArr));
        map.put("status", status);
        try {
            IShopCartItemDaoMapper mapper = session.getMapper(IShopCartItemDaoMapper.class);
            result = mapper.updateBatch(map);
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
    public List<ShopCartItem> findByNumIid(String accountId) {
        List<ShopCartItem> result = ListUtils.EMPTY_LIST;
        SqlSession session = IbatisSessionFactory.get().openSession(false);

        try {
            IShopCartItemDaoMapper mapper = session.getMapper(IShopCartItemDaoMapper.class);
            result = mapper.findByAccount(accountId);
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
    public ShopCartItem findByNumIid(Long numIid) {
        ShopCartItem result = null;
        SqlSession session = IbatisSessionFactory.get().openSession(false);

        try {
            IShopCartItemDaoMapper mapper = session.getMapper(IShopCartItemDaoMapper.class);
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
    public long countByAccount(String accountId) {
        long result = 0L;
        SqlSession session = IbatisSessionFactory.get().openSession();

        try {
            IShopCartItemDaoMapper mapper = session.getMapper(IShopCartItemDaoMapper.class);
            result = mapper.countByAccount(accountId);
        } catch (Exception e) {
            session.rollback(true);
            LOG.warn(e.getMessage(), e);
        } finally {
            session.close();
        }

        return result;
    }
}
