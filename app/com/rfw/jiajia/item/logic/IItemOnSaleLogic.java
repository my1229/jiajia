/**
 * @author mlc
 * @date 2015年7月25日
 * @version 1.0
 */
package com.rfw.jiajia.item.logic;

import java.util.List;

import com.rfw.common.base.dto.ProcessStatus;
import com.rfw.jiajia.item.dto.ItemOnSaleDto;
import com.rfw.jiajia.item.models.ItemOnSale;
import com.rfw.jiajia.item.models.ItemPlay;

public interface IItemOnSaleLogic {

    /**
     * 添加
     * 
     * @param onSale
     * @return
     */
    public long insert(ItemOnSale onSale);

    /**
     * 分页查找
     * 
     * @param account
     * @param pageNo
     * @param pageSize
     * @return
     */
    public List<ItemPlay> findByAccount(String account, int pageNo, int pageSize);

    /**
     * 根据账号统计
     * 
     * @param account
     * @return
     */
    public long countByAccount(String account);

    /**
     * 根据天数查找
     */
    public List<ItemOnSaleDto> checkByDay(Long start, Long end, Integer pageNo, Integer pageSize);

    /**
     * 统计
     * 
     * @param start
     * @param end
     * @return
     */
    public long count(Long start, Long end);

    /**
     * 检测是否已经复制过
     * 
     * @param accountId
     * @param numIid
     * @return
     */
    public ProcessStatus checkIsCopy(String accountId, Long numIid);

}
