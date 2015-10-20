package jiajia.app.demo.dao.interfaces;

import jiajia.app.demo.models.User;

/**
 * 
 * @author lizhong.chen
 * @date 2015年2月27日上午11:17:58
 * @description user dao impl
 * @version V1.0
 */
public interface IUserDao {

    /**
     * 根据name进行查询
     * 
     * @param name
     * @return
     */
    public User selectByName(String name);

    /**
     * 根据name和pwd进行查询
     * 
     * @param name
     * @param pwd
     */
    public User selectByNameAndPwd(String name, String pwd);

    /**
     * 新增用户
     * 
     * @param user
     */
    public long insert(User user);

    /**
     * 根据user.id进行跟新用户的信息
     * 
     * @param user
     */
    public long updateById(User user);

    /**
     * 根据name进行删除
     * 
     * @param name
     */
    public long deleteByName(String name);

}
