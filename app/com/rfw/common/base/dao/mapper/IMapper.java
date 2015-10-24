package com.rfw.common.base.dao.mapper;

import java.util.List;
import java.util.Map;

/**
 * 
 * @author lizhong.chen
 * @date 2015年2月26日下午2:27:58
 * @description mybatis base dao mapper
 * @version V1.0
 */
public interface IMapper<O> {
    public O selectOne(Map<String, Object> params);

    public List<O> select(Map<String, Object> params);

    public long count(Map<String, Object> params);

    public int insert(O user);

    public int insertBatch(List<O> users);

    public int update(O user);

    public int delete(Map<String, Object> params);
}
