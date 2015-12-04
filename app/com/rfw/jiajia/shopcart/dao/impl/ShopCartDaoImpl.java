/**   
 * @Description: TODO
 * @author mlc
 * @date 2015年8月11日 下午3:26:54 
 * @version V1.0   
 */

package com.rfw.jiajia.shopcart.dao.impl;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rfw.jiajia.shopcart.dao.IShopCartDao;
import com.rfw.jiajia.shopcart.dao.mapper.interfaces.IShopCartDaoMapper;
import com.rfw.jiajia.shopcart.models.ShopCart;

import mybatisplay.IbatisSessionFactory;

public class ShopCartDaoImpl implements IShopCartDao {
    private static final Logger LOG = LoggerFactory.getLogger(ShopCartDaoImpl.class);

    private static IShopCartDao instance = new ShopCartDaoImpl();

    private ShopCartDaoImpl() {
    }

    public static IShopCartDao getInstance() {
        return instance;
    }

    @Override
    public ShopCart findByAccount(String account) {
        ShopCart result = null;
        SqlSession session = IbatisSessionFactory.get().openSession();

        try {
            IShopCartDaoMapper mapper = session.getMapper(IShopCartDaoMapper.class);
            result = mapper.findByAccount(account);

        } catch (Exception e) {
            session.rollback(true);
            LOG.warn(e.getMessage(), e);
        } finally {
            session.close();
        }

        return result;
    }

    @Override
    public long insert(ShopCart cart) {
        long result = 0L;
        SqlSession session = IbatisSessionFactory.get().openSession(false);

        try {
            IShopCartDaoMapper mapper = session.getMapper(IShopCartDaoMapper.class);
            result = mapper.insert(cart);
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
