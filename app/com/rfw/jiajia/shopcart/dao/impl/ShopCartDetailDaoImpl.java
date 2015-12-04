/**   
 * @Description: TODO
 * @author mlc
 * @date 2015年8月11日 下午3:02:55 
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

import com.rfw.jiajia.shopcart.dao.IShopCartDetailDao;
import com.rfw.jiajia.shopcart.dao.mapper.interfaces.IShopCartDetailMapper;
import com.rfw.jiajia.shopcart.models.ShopCartDetail;

import mybatisplay.IbatisSessionFactory;

public class ShopCartDetailDaoImpl implements IShopCartDetailDao {

    private static final Logger LOG = LoggerFactory.getLogger(ShopCartDetailDaoImpl.class);

    private static IShopCartDetailDao instance = new ShopCartDetailDaoImpl();

    private ShopCartDetailDaoImpl() {
    }

    public static IShopCartDetailDao getInstance() {
        return instance;
    }

    @Override
    public ShopCartDetail findByNumIidAndProps(String accountId, Long numIid, String propsName) {
        ShopCartDetail result = null;
        SqlSession session = IbatisSessionFactory.get().openSession();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("accountId", accountId);
        map.put("numIid", numIid);
        map.put("propsName", propsName);
        try {
            IShopCartDetailMapper mapper = session.getMapper(IShopCartDetailMapper.class);
            result = mapper.findByNumIidAndProps(map);

        } catch (Exception e) {
            session.rollback(true);
            LOG.warn(e.getMessage(), e);
        } finally {
            session.close();
        }

        return result;
    }

    @Override
    public long insert(ShopCartDetail detail) {
        long result = 0L;
        SqlSession session = IbatisSessionFactory.get().openSession(false);

        try {
            IShopCartDetailMapper mapper = session.getMapper(IShopCartDetailMapper.class);
            result = mapper.insert(detail);
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
    public long update(ShopCartDetail detail) {
        long result = 0L;
        SqlSession session = IbatisSessionFactory.get().openSession(false);

        try {
            IShopCartDetailMapper mapper = session.getMapper(IShopCartDetailMapper.class);
            result = mapper.update(detail);
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
    public List<ShopCartDetail> findByAccount(String accountId) {
        List<ShopCartDetail> result = ListUtils.EMPTY_LIST;
        SqlSession session = IbatisSessionFactory.get().openSession();

        try {
            IShopCartDetailMapper mapper = session.getMapper(IShopCartDetailMapper.class);
            result = mapper.findByAccount(accountId);

        } catch (Exception e) {
            session.rollback(true);
            LOG.warn(e.getMessage(), e);
        } finally {
            session.close();
        }

        return result;
    }

    @Override
    public List<ShopCartDetail> findByCartId(Long cartId) {
        List<ShopCartDetail> result = ListUtils.EMPTY_LIST;
        SqlSession session = IbatisSessionFactory.get().openSession();

        try {
            IShopCartDetailMapper mapper = session.getMapper(IShopCartDetailMapper.class);
            result = mapper.findByCartId(cartId);

        } catch (Exception e) {
            session.rollback(true);
            LOG.warn(e.getMessage(), e);
        } finally {
            session.close();
        }

        return result;
    }

    @Override
    public List<Long> findNumIidByCartId(Long cartId) {
        return null;
    }

    @Override
    public long deleteBatch(String ids) {
        return 0;
    }

    @Override
    public long updateBatch(String account, String ids, Integer num, Integer status) {
        long result = 0L;
        SqlSession session = IbatisSessionFactory.get().openSession(false);

        Map<String, Object> map = new HashMap<String, Object>();
        String[] idsArr = ids.split(",");

        map.put("account", account);
        map.put("ids", Arrays.asList(idsArr));
        map.put("status", status);
        map.put("num", num);
        try {
            IShopCartDetailMapper mapper = session.getMapper(IShopCartDetailMapper.class);
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
    public ShopCartDetail findById(Long id) {

        ShopCartDetail result = null;
        SqlSession session = IbatisSessionFactory.get().openSession();

        try {
            IShopCartDetailMapper mapper = session.getMapper(IShopCartDetailMapper.class);
            result = mapper.findById(id);
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
            IShopCartDetailMapper mapper = session.getMapper(IShopCartDetailMapper.class);
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
