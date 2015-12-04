/**    
 * @author mlc  
 * @version 1.0  
 *
 * 2015年7月24日   
 */
package com.rfw.jiajia.item.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.ListUtils;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rfw.common.utils.DateUtils;
import com.rfw.jiajia.item.dao.IItemOnSaleDao;
import com.rfw.jiajia.item.dao.mapper.interfaces.IItemOnSaleDaoMapper;
import com.rfw.jiajia.item.dto.ItemOnSaleDto;
import com.rfw.jiajia.item.models.ItemOnSale;

import mybatisplay.IbatisSessionFactory;

public class ItemOnSaleDaoImpl implements IItemOnSaleDao {

    private static final Logger LOG = LoggerFactory.getLogger(ItemDescrDaoImpl.class);

    private static IItemOnSaleDao instance = new ItemOnSaleDaoImpl();

    private ItemOnSaleDaoImpl() {
    }

    public static IItemOnSaleDao getInstance() {
        return instance;
    }

    @Override
    public long insert(ItemOnSale onsale) {
        long result = 0L;
        SqlSession session = IbatisSessionFactory.get().openSession(false);

        try {
            IItemOnSaleDaoMapper mapper = session.getMapper(IItemOnSaleDaoMapper.class);
            result = mapper.insert(onsale);
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
    public List<ItemOnSale> findByAccount(String account, int pageNo, int pageSize) {
        List<ItemOnSale> result = ListUtils.EMPTY_LIST;
        SqlSession session = IbatisSessionFactory.get().openSession();

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("account", account);
        map.put("offset", (pageNo - 1) * pageSize);
        map.put("limit", pageSize);
        try {
            IItemOnSaleDaoMapper mapper = session.getMapper(IItemOnSaleDaoMapper.class);
            result = mapper.findByAccount(map);
        } catch (Exception e) {
            session.rollback(true);
            LOG.warn(e.getMessage(), e);
        } finally {
            session.close();
        }

        return result;
    }

    @Override
    public long countByAccount(String account) {
        long result = 0L;
        SqlSession session = IbatisSessionFactory.get().openSession();
        try {
            IItemOnSaleDaoMapper mapper = session.getMapper(IItemOnSaleDaoMapper.class);
            result = mapper.countByAccount(account);
        } catch (Exception e) {
            session.rollback(true);
            LOG.warn(e.getMessage(), e);
        } finally {
            session.close();
        }

        return result;
    }

    @Override
    public List<ItemOnSaleDto> checkByDay(Long start, Long end, Integer pageNo, Integer pageSize) {
        List<ItemOnSaleDto> result = ListUtils.EMPTY_LIST;
        SqlSession session = IbatisSessionFactory.get().openSession();

        Map<String, Object> map = new HashMap<String, Object>();
        if (start == null) {
            start = DateUtils.getFirstMillisOfDay(System.currentTimeMillis(), 0);
        }
        if (end == null) {
            end = DateUtils.getLastMillisOfDay(System.currentTimeMillis(), 0);
        }
        map.put("start", start);
        map.put("end", end);
        map.put("offset", (pageNo - 1) * pageSize);
        map.put("limit", pageSize);
        try {
            IItemOnSaleDaoMapper mapper = session.getMapper(IItemOnSaleDaoMapper.class);
            result = mapper.checkByDay(map);
        } catch (Exception e) {
            session.rollback(true);
            LOG.warn(e.getMessage(), e);
        } finally {
            session.close();
        }

        return result;
    }

    @Override
    public long count(Long start, Long end) {
        long result = 0L;
        SqlSession session = IbatisSessionFactory.get().openSession();
        if (start == null) {
            start = DateUtils.getFirstMillisOfDay(System.currentTimeMillis(), 0);
        }
        if (end == null) {
            end = DateUtils.getLastMillisOfDay(System.currentTimeMillis(), 0);
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("start", start);
        map.put("end", end);

        try {
            IItemOnSaleDaoMapper mapper = session.getMapper(IItemOnSaleDaoMapper.class);
            result = mapper.count(map);
        } catch (Exception e) {
            session.rollback(true);
            LOG.warn(e.getMessage(), e);
        } finally {
            session.close();
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println(System.currentTimeMillis());
    }

    @Override
    public long countAll(Long start, Long end, Integer isOnSale) {
        long result = 0L;
        Map<String, Object> map = new HashMap<String, Object>();
        SqlSession session = IbatisSessionFactory.get().openSession();
        if (start != null) {
            map.put("start", start);
        }
        if (end != null) {
            map.put("end", end);
        }

        if (isOnSale != null) {
            map.put("isOnSale", isOnSale);
        }

        try {
            IItemOnSaleDaoMapper mapper = session.getMapper(IItemOnSaleDaoMapper.class);
            result = mapper.countAll(map);
        } catch (Exception e) {
            session.rollback(true);
            LOG.warn(e.getMessage(), e);
        } finally {
            session.close();
        }

        return result;
    }

    @Override
    public ItemOnSale findByAccountAndNumIid(String accountId, Long numIid) {
        ItemOnSale result = null;
        SqlSession session = IbatisSessionFactory.get().openSession();
        Map<String, Object> map = new HashMap<String, Object>();

        if (accountId != null) {
            map.put("accountId", accountId);
        }

        if (numIid != null) {
            map.put("numIid", numIid);
        }

        try {
            IItemOnSaleDaoMapper mapper = session.getMapper(IItemOnSaleDaoMapper.class);
            result = mapper.findByAccountAndNumIid(map);
        } catch (Exception e) {
            session.rollback(true);
            LOG.warn(e.getMessage(), e);
        } finally {
            session.close();
        }

        return result;
    }
}
