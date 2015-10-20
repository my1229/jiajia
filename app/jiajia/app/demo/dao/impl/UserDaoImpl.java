package jiajia.app.demo.dao.impl;

import java.util.HashMap;
import java.util.Map;

import mybatisplay.IbatisSessionFactory;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;

import jiajia.app.demo.configs.LoggerConfigs;
import jiajia.app.demo.dao.interfaces.IUserDao;
import jiajia.app.demo.dao.mappers.IUserMapper;
import jiajia.app.demo.models.User;

/**
 * 
 * @author lizhong.chen
 * @date 2015年2月27日上午11:16:44
 * @description user dao impl
 * @version V1.0
 */
public class UserDaoImpl implements IUserDao {
    private static final Logger LOG = LoggerConfigs.DAO_LOG;

    // 单例实现开始
    private UserDaoImpl() {
    }

    private static class UserDaoImplHolder {
        private static IUserDao instance = new UserDaoImpl();
    }

    /**
     * 获取单例
     * 
     * @return
     */
    public static IUserDao getInstance() {
        return UserDaoImplHolder.instance;
    }

    // 单例实现结束

    @Override
    public User selectByName(String name) {
        if (StringUtils.isEmpty(name)) {
            // 若查询条件为空，则返回为空
            return null;
        }
        // 有查询条件，则继续查询
        User result = null;
        SqlSession session = IbatisSessionFactory.get().openSession(false);

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("name", name);
        params.put("table", "r");
        try {
            IUserMapper mapper = session.getMapper(IUserMapper.class);
            result = mapper.selectOne(params);
        } catch (Exception e) {
            // 进行回滚
            session.rollback(true);
            LOG.error(e.getMessage(), e);
        } finally {
            session.close();
        }
        return result;
    }

    @Override
    public User selectByNameAndPwd(String name, String pwd) {
        if (StringUtils.isEmpty(name) || StringUtils.isEmpty(pwd)) {
            // 若查询条件为空，则返回为空
            return null;
        }
        // 有查询条件，则继续查询
        User result = null;
        SqlSession session = IbatisSessionFactory.get().openSession(false);

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("name", name);
        params.put("pwd", pwd);
        try {
            IUserMapper mapper = session.getMapper(IUserMapper.class);
            result = mapper.selectOne(params);
        } catch (Exception e) {
            // 进行回滚
            session.rollback(true);
            LOG.error(e.getMessage(), e);
        } finally {
            session.close();
        }
        return result;
    }

    @Override
    public long insert(User user) {
        // 有查询条件，则继续查询
        long result = 0;
        SqlSession session = IbatisSessionFactory.get().openSession(false);

        try {
            IUserMapper mapper = session.getMapper(IUserMapper.class);
            result = mapper.insert(user);
            session.commit(true);
        } catch (Exception e) {
            // 进行回滚
            session.rollback(true);
            LOG.error(e.getMessage(), e);
        } finally {
            session.close();
        }
        return result;
    }

    @Override
    public long updateById(User user) {
        long result = 0;
        SqlSession session = IbatisSessionFactory.get().openSession(false);

        try {
            IUserMapper mapper = session.getMapper(IUserMapper.class);
            result = mapper.update(user);
            session.commit(true);
        } catch (Exception e) {
            // 进行回滚
            session.rollback(true);
            LOG.error(e.getMessage(), e);
        } finally {
            session.close();
        }
        return result;
    }

    @Override
    public long deleteByName(String name) {
        long result = 0;
        SqlSession session = IbatisSessionFactory.get().openSession(false);
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("name", name);
        try {
            IUserMapper mapper = session.getMapper(IUserMapper.class);
            result = mapper.delete(params);
            session.commit(true);
        } catch (Exception e) {
            // 进行回滚
            session.rollback(true);
            LOG.error(e.getMessage(), e);
        } finally {
            session.close();
        }
        return result;
    }

}
