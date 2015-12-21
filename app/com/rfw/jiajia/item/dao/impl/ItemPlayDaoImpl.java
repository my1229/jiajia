/**    
 * @author mlc  
 * @version 1.0  
 *
 * 2015年7月15日   
 */
package com.rfw.jiajia.item.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.ListUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rfw.jiajia.item.dao.IItemPlayDao;
import com.rfw.jiajia.item.dao.mapper.interfaces.IItemDescrDaoMapper;
import com.rfw.jiajia.item.dao.mapper.interfaces.IItemImgDaoMapper;
import com.rfw.jiajia.item.dao.mapper.interfaces.IItemLocationDaoMapper;
import com.rfw.jiajia.item.dao.mapper.interfaces.IItemPlayDaoMapper;
import com.rfw.jiajia.item.dao.mapper.interfaces.IItemPropImgDaoMapper;
import com.rfw.jiajia.item.dao.mapper.interfaces.IItemSkuDaoMapper;
import com.rfw.jiajia.item.dto.ItemRankDto;
import com.rfw.jiajia.item.models.ItemDescr;
import com.rfw.jiajia.item.models.ItemImg;
import com.rfw.jiajia.item.models.ItemLocation;
import com.rfw.jiajia.item.models.ItemPlay;
import com.rfw.jiajia.item.models.ItemPropImg;
import com.rfw.jiajia.item.models.ItemSku;

import mybatisplay.IbatisSessionFactory;

public class ItemPlayDaoImpl implements IItemPlayDao {

	private static final Logger LOG = LoggerFactory.getLogger(ItemPlayDaoImpl.class);

	private static IItemPlayDao instance = new ItemPlayDaoImpl();

	private ItemPlayDaoImpl() {
	}

	public static IItemPlayDao getInstance() {
		return instance;
	}

	@Override
	public long insert(ItemPlay item) {
		long result = 0L;
		SqlSession session = IbatisSessionFactory.get().openSession(false);

		try {
			IItemPlayDaoMapper mapper = session.getMapper(IItemPlayDaoMapper.class);
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
	public List<ItemPlay> getItemList(long offset, long limit) {
		List<ItemPlay> result = ListUtils.EMPTY_LIST;
		SqlSession session = IbatisSessionFactory.get().openSession();

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("offset", offset);
		map.put("limit", limit);
		try {
			IItemPlayDaoMapper mapper = session.getMapper(IItemPlayDaoMapper.class);
			result = mapper.getItemList(map);
		} catch (Exception e) {
			session.rollback(true);
			LOG.warn(e.getMessage(), e);
		} finally {
			session.close();
		}

		return result;
	}

	@Override
	public long count() {
		long result = 0L;
		SqlSession session = IbatisSessionFactory.get().openSession();

		try {
			IItemPlayDaoMapper mapper = session.getMapper(IItemPlayDaoMapper.class);
			result = mapper.count();
		} catch (Exception e) {
			session.rollback(true);
			LOG.warn(e.getMessage(), e);
		} finally {
			session.close();
		}

		return result;
	}

	@Override
	public long insertBatch(List<ItemPlay> itemPlays) {
		long result = 0l;
		SqlSession session = IbatisSessionFactory.get().openSession(false);
		Map<String, Object> params = new HashMap<String, Object>();
		if (CollectionUtils.isNotEmpty(itemPlays)) {
			params.put("itemPlays", itemPlays);
		}

		try {
			IItemPlayDaoMapper mapper = session.getMapper(IItemPlayDaoMapper.class);
			result = mapper.insertBatch(params);
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
	public boolean createItemPlayRef(List<ItemPlay> plays, List<ItemPropImg> propImgs, List<ItemImg> imgs,
			List<ItemLocation> locations, List<ItemDescr> descrs, List<ItemSku> skus) {

		if (CollectionUtils.isEmpty(plays)) {
			return false;
		}
		boolean isSuccess = false;
		SqlSession session = IbatisSessionFactory.get().openSession(false);

		Map<String, Object> playMap = new HashMap<String, Object>();
		Map<String, Object> propImgMap = new HashMap<String, Object>();
		Map<String, Object> imgMap = new HashMap<String, Object>();
		Map<String, Object> locationMap = new HashMap<String, Object>();
		Map<String, Object> descrMap = new HashMap<String, Object>();
		Map<String, Object> skuMap = new HashMap<String, Object>();

		if (CollectionUtils.isNotEmpty(plays)) {
			playMap.put("itemPlays", plays);
		}
		if (CollectionUtils.isNotEmpty(propImgs)) {
			propImgMap.put("itemPropImgs", propImgs);
		}
		if (CollectionUtils.isNotEmpty(imgs)) {
			imgMap.put("itemImgs", imgs);
		}
		if (CollectionUtils.isNotEmpty(locations)) {
			locationMap.put("itemLocations", locations);
		}
		if (CollectionUtils.isNotEmpty(descrs)) {
			descrMap.put("itemDescrs", descrs);
		}
		if (CollectionUtils.isNotEmpty(skus)) {
			skuMap.put("itemSkus", skus);
		}

		try {
			IItemPlayDaoMapper playMapper = session.getMapper(IItemPlayDaoMapper.class);

			for (ItemPlay itemPlay : plays) {
				playMapper.update(itemPlay);
			}

			if (CollectionUtils.isNotEmpty(propImgs)) {
				IItemPropImgDaoMapper propImgMapper = session.getMapper(IItemPropImgDaoMapper.class);
				propImgMapper.insertBatch(propImgMap);
			}

			IItemImgDaoMapper imgMapper = session.getMapper(IItemImgDaoMapper.class);
			imgMapper.insertBatch(imgMap);

			IItemLocationDaoMapper locationMapper = session.getMapper(IItemLocationDaoMapper.class);
			locationMapper.insertBatch(locationMap);

			IItemDescrDaoMapper descrMapper = session.getMapper(IItemDescrDaoMapper.class);

			for (ItemDescr descr : descrs) {
				descrMapper.insert(descr);

			}

			IItemSkuDaoMapper skuMapper = session.getMapper(IItemSkuDaoMapper.class);
			skuMapper.insertBatch(skuMap);
			session.commit(true);

			isSuccess = true;
		} catch (Exception e) {
			session.rollback(true);
			LOG.warn(e.getMessage(), e);
		} finally {
			session.close();
		}

		return isSuccess;
	}

	public static void main(String[] args) {
		String str = "张杰同款を酷炫日韩风~型男必备街舞手套男士手套 街舞 情侣手套";
		str.replaceAll("[\\pP|~|$|^|<|>|\\||\\+|=]*", "");
		System.out.println(str);
	}

	@Override
	public List<ItemPlay> findByNick(String nick, String outerId, Boolean hasTradePrice, Integer type,
			List<Long> subCids, String keyword, String orderBy, int pageSize, int pageNo, Double upPrice,
			Double lowPrice, String sort) {

		List<ItemPlay> itemPlays = ListUtils.EMPTY_LIST;
		Map<String, Object> params = new HashMap<String, Object>();
		if (CollectionUtils.isNotEmpty(subCids)) {
			params.put("subCids", numToMatchStr(subCids));
		}

		if (StringUtils.isNotBlank(keyword)) {
			params.put("title", "%" + keyword + "%");
		}

		if (StringUtils.isNotBlank(nick)) {
			params.put("nick", nick);
		}
		if (upPrice != null) {
			params.put("upPrice", upPrice);
		}
		if (lowPrice != null) {
			params.put("lowPrice", lowPrice);
		}

		if (StringUtils.isNotBlank(sort)) {
			params.put("sort", sort);
		}

		if (StringUtils.isNotBlank(orderBy)) {
			params.put("orderBy", orderBy);
		}

		if (StringUtils.isNotBlank(outerId)) {
			params.put("outerId", "%" + outerId + "%");
		}

		if (hasTradePrice != null) {
			params.put("tradePrice", hasTradePrice);
		}

		if (type != null) {
			params.put("localApproveStatus", type);
		}
		params.put("offset", (pageNo - 1) * pageSize);
		params.put("limit", pageSize);

		SqlSession session = IbatisSessionFactory.get().openSession(false);

		try {
			IItemPlayDaoMapper itemDaoMapper = session.getMapper(IItemPlayDaoMapper.class);
			itemPlays = itemDaoMapper.search(params);

			session.commit(true);
		} catch (Exception e) {
			LOG.warn(e.getMessage(), e);
		} finally {
			session.close();
		}

		return itemPlays;
	}

	/**
	 * 查询条件，数字转化成可匹配的字符串，例：10000L —— %10000%
	 * 
	 * @param nums
	 * @return
	 */
	private static <T> List<String> numToMatchStr(List<T> nums) {

		if (CollectionUtils.isEmpty(nums)) {
			LOG.warn("prase nums to MatchStr, parameter nums is null~");
			return null;
		}

		List<String> matchStrs = new ArrayList<String>(nums.size());
		for (Object o : nums) {
			String con = "%" + o + "%";
			matchStrs.add(con);
		}

		return matchStrs;
	}

	@Override
	public long countByNick(String nick, String outerId, Boolean hasTradePrice, Integer type, List<Long> sellerCids,
			String keyword, Double upPrice, Double lowPrice) {
		Long result = 0L;
		Map<String, Object> params = new HashMap<String, Object>();

		if (nick != null) {
			params.put("nick", nick);
		}
		if (CollectionUtils.isNotEmpty(sellerCids)) {
			params.put("sellerCids", numToMatchStr(sellerCids));
		}

		if (!StringUtils.isBlank(keyword)) {
			params.put("title", "%" + keyword + "%");
		}

		if (upPrice != null) {
			params.put("upPrice", upPrice);
		}
		if (lowPrice != null) {
			params.put("lowPrice", lowPrice);
		}

		if (outerId != null) {
			params.put("outerId", "%" + outerId + "%");
		}

		if (hasTradePrice != null) {
			params.put("tradePrice", hasTradePrice);
		}

		if (type != null) {
			params.put("localApproveStatus", type);
		}
		SqlSession session = IbatisSessionFactory.get().openSession();

		try {
			IItemPlayDaoMapper itemDaoMapper = session.getMapper(IItemPlayDaoMapper.class);
			result = itemDaoMapper.countByNick(params);
		} catch (Exception e) {
			LOG.warn(e.getMessage(), e);
		} finally {
			session.close();
		}
		return result;
	}

	@Override
	public ItemPlay findByNumIid(Long numIid) {
		ItemPlay itemPlay = null;

		SqlSession session = IbatisSessionFactory.get().openSession();

		try {
			IItemPlayDaoMapper itemDaoMapper = session.getMapper(IItemPlayDaoMapper.class);
			itemPlay = itemDaoMapper.findByNumIid(numIid);
		} catch (Exception e) {
			LOG.warn(e.getMessage(), e);
		} finally {
			session.close();
		}

		return itemPlay;
	}

	@Override
	public boolean updateBatch(List<ItemPlay> plays) {
		boolean isSuccess = false;
		SqlSession session = IbatisSessionFactory.get().openSession(false);
		try {
			IItemPlayDaoMapper playMapper = session.getMapper(IItemPlayDaoMapper.class);
			for (ItemPlay itemPlay : plays) {
				playMapper.update(itemPlay);
			}
			session.commit(true);
			isSuccess = true;
		} catch (Exception e) {
			LOG.warn(e.getMessage(), e);
		} finally {
			session.close();
		}
		return isSuccess;
	}

	@Override
	public long update(ItemPlay play) {
		long result = 0L;
		SqlSession session = IbatisSessionFactory.get().openSession(false);

		try {
			IItemPlayDaoMapper mapper = session.getMapper(IItemPlayDaoMapper.class);
			result = mapper.update(play);
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
	public List<ItemPlay> findByNick(String nick) {
		List<ItemPlay> result = ListUtils.EMPTY_LIST;
		SqlSession session = IbatisSessionFactory.get().openSession(false);

		try {
			IItemPlayDaoMapper mapper = session.getMapper(IItemPlayDaoMapper.class);
			result = mapper.findByNick(nick);
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
	public List<Long> findAllCid() {
		List<Long> result = ListUtils.EMPTY_LIST;
		SqlSession session = IbatisSessionFactory.get().openSession(false);

		try {
			IItemPlayDaoMapper mapper = session.getMapper(IItemPlayDaoMapper.class);
			result = mapper.findAllCid();
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
	public long updateIsOauth(String nick, Integer isOauth) {
		long result = 0L;
		SqlSession session = IbatisSessionFactory.get().openSession(false);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("nick", nick);
		map.put("isOauth", isOauth);
		try {

			IItemPlayDaoMapper mapper = session.getMapper(IItemPlayDaoMapper.class);
			result = mapper.updateIsOauth(map);
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
	public long updateIsDelete(long numIid, Integer isDelete) {
		long result = 0L;
		SqlSession session = IbatisSessionFactory.get().openSession(false);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("numIid", numIid);
		map.put("isDelete", isDelete);
		try {

			IItemPlayDaoMapper mapper = session.getMapper(IItemPlayDaoMapper.class);
			result = mapper.updateIsDelete(map);
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
	public List<ItemPlay> findInCompleteItems() {
		List<ItemPlay> result = new ArrayList<ItemPlay>();
		SqlSession session = IbatisSessionFactory.get().openSession(false);

		try {

			IItemPlayDaoMapper mapper = session.getMapper(IItemPlayDaoMapper.class);
			result = mapper.findInCompleteItems();
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
	public List<ItemPlay> findNoDescItems() {
		List<ItemPlay> result = new ArrayList<ItemPlay>();
		SqlSession session = IbatisSessionFactory.get().openSession(false);

		try {

			IItemPlayDaoMapper mapper = session.getMapper(IItemPlayDaoMapper.class);
			result = mapper.findNoDescItems();
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
	public long updateTradePrice(String nick, long numIid, double tradePrice) {

		long result = 0L;
		SqlSession session = IbatisSessionFactory.get().openSession(false);

		Map<String, Object> map = new HashMap<String, Object>();

		map.put("nick", nick);
		map.put("numIid", numIid);
		map.put("tradePrice", tradePrice);

		try {

			IItemPlayDaoMapper mapper = session.getMapper(IItemPlayDaoMapper.class);
			result = mapper.updateTradePrice(map);
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
	public long updateStatus(String nick, long numIid, int localApproveStatus, int approveStatus, int isDelete) {

		long result = 0L;
		SqlSession session = IbatisSessionFactory.get().openSession(false);

		Map<String, Object> map = new HashMap<String, Object>();

		map.put("nick", nick);
		map.put("numIid", numIid);
		map.put("localApproveStatus", localApproveStatus);
		map.put("approveStatus", approveStatus);
		map.put("isDelete", isDelete);

		try {

			IItemPlayDaoMapper mapper = session.getMapper(IItemPlayDaoMapper.class);
			result = mapper.updateStatus(map);
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
	public long updateReducePrice(String nick, String numIids, double reducePrice) {

		long result = 0L;
		SqlSession session = IbatisSessionFactory.get().openSession(false);

		Map<String, Object> map = new HashMap<String, Object>();
		try {
			if (!StringUtils.isBlank(numIids)) {
				String[] numIidArray = numIids.split(",");
				List<Long> numIidList = new ArrayList<Long>();
				if (numIidArray != null && numIidArray.length > 0) {
					for (String numIid : numIidArray) {
						numIidList.add(Long.valueOf(numIid));
					}
				}
				map.put("numIids", numIidList);

			}

		} catch (Exception e) {
			LOG.warn(e.getMessage(), e);
		}
		map.put("nick", nick);

		map.put("reducePrice", reducePrice);

		LOG.warn("nick:" + nick + ", reducePrice:" + reducePrice);
		try {

			IItemPlayDaoMapper mapper = session.getMapper(IItemPlayDaoMapper.class);
			if (StringUtils.isBlank(numIids)) {
				result = mapper.updateReducePriceByNick(map);
			} else {
				result = mapper.updateReducePrice(map);
			}
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
	public List<ItemPlay> findByGmtCreated(int limit, int offset) {
		List<ItemPlay> result = new ArrayList<ItemPlay>();
		SqlSession session = IbatisSessionFactory.get().openSession(false);

		try {

			IItemPlayDaoMapper mapper = session.getMapper(IItemPlayDaoMapper.class);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("limit", limit);
			map.put("offset", offset);

			result = mapper.findByGmtCreated(map);
			session.commit(true);
		} catch (Exception e) {
			session.rollback(true);
			LOG.warn(e.getMessage(), e);
		} finally {
			session.close();
		}

		return result;
	}

	public List<ItemPlay> findNeedCheckPriceItems(Integer pageNo, Integer pageSize) {
		List<ItemPlay> result = new ArrayList<ItemPlay>();
		SqlSession session = IbatisSessionFactory.get().openSession(false);

		try {

			IItemPlayDaoMapper mapper = session.getMapper(IItemPlayDaoMapper.class);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("limit", pageSize);
			map.put("offset", pageSize * (pageNo - 1));

			result = mapper.findNeedCheckPriceItems(map);
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
	public long countNeedCheckPriceItems() {
		Long result = 0L;

		SqlSession session = IbatisSessionFactory.get().openSession();

		try {
			IItemPlayDaoMapper itemDaoMapper = session.getMapper(IItemPlayDaoMapper.class);
			result = itemDaoMapper.countNeedCheckPriceItems();
		} catch (Exception e) {
			LOG.warn(e.getMessage(), e);
		} finally {
			session.close();
		}
		return result;
	}

	@Override
	public List<ItemRankDto> getItemByOrders(String nick, Integer pageNo, Integer pageSize) {
		List<ItemRankDto> result = new ArrayList<ItemRankDto>();
		SqlSession session = IbatisSessionFactory.get().openSession(false);

		try {

			IItemPlayDaoMapper mapper = session.getMapper(IItemPlayDaoMapper.class);
			Map<String, Object> map = new HashMap<String, Object>();
			if (StringUtils.isNotBlank(nick)) {
				map.put("nick", nick);
			}
			map.put("limit", (pageNo - 1) * pageSize);
			map.put("offset", pageSize);

			result = mapper.getItemByOrders(map);
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
	public List<ItemRankDto> getItemByCollection(String nick, Integer pageNo, Integer pageSize) {
		List<ItemRankDto> result = new ArrayList<ItemRankDto>();
		SqlSession session = IbatisSessionFactory.get().openSession(false);

		try {

			IItemPlayDaoMapper mapper = session.getMapper(IItemPlayDaoMapper.class);
			Map<String, Object> map = new HashMap<String, Object>();
			if (StringUtils.isNotBlank(nick)) {
				map.put("nick", nick);
			}
			map.put("limit", (pageNo - 1) * pageSize);
			map.put("offset", pageSize);

			result = mapper.getItemByCollection(map);
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
	public long countForOrderRank(String nick) {
		long result = 0L;
		SqlSession session = IbatisSessionFactory.get().openSession(false);

		try {

			IItemPlayDaoMapper mapper = session.getMapper(IItemPlayDaoMapper.class);
			Map<String, Object> map = new HashMap<String, Object>();
			if (StringUtils.isNotBlank(nick)) {
				map.put("nick", nick);
			}

			result = mapper.countForOrderRank(map);
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
	public long countForCollectRank(String nick) {
		long result = 0L;
		SqlSession session = IbatisSessionFactory.get().openSession(false);

		try {

			IItemPlayDaoMapper mapper = session.getMapper(IItemPlayDaoMapper.class);
			Map<String, Object> map = new HashMap<String, Object>();
			if (StringUtils.isNotBlank(nick)) {
				map.put("nick", nick);
			}

			result = mapper.countForCollectRank(map);
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
	public List<ItemPlay> findHot(Integer limit) {
		List<ItemPlay> result = new ArrayList<ItemPlay>();
		SqlSession session = IbatisSessionFactory.get().openSession(false);

		try {

			IItemPlayDaoMapper mapper = session.getMapper(IItemPlayDaoMapper.class);
			result = mapper.findHot(limit);
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
