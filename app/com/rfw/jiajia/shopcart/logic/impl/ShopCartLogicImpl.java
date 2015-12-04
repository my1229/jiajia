/**   
 * @Description: TODO
 * @author mlc
 * @date 2015年8月11日 下午2:57:26 
 * @version V1.0   
 */

package com.rfw.jiajia.shopcart.logic.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rfw.common.base.dto.ProcessStatus;
import com.rfw.common.utils.DateUtils;
import com.rfw.common.utils.JsonUtil;
import com.rfw.jiajia.item.dao.IItemPlayDao;
import com.rfw.jiajia.item.dao.impl.ItemPlayDaoImpl;
import com.rfw.jiajia.item.models.ItemPlay;
import com.rfw.jiajia.shopcart.constant.ShopCartStatus;
import com.rfw.jiajia.shopcart.dao.IShopCartDao;
import com.rfw.jiajia.shopcart.dao.IShopCartDetailDao;
import com.rfw.jiajia.shopcart.dao.IShopCartItemDao;
import com.rfw.jiajia.shopcart.dao.impl.ShopCartDaoImpl;
import com.rfw.jiajia.shopcart.dao.impl.ShopCartDetailDaoImpl;
import com.rfw.jiajia.shopcart.dao.impl.ShopCartItemDaoImpl;
import com.rfw.jiajia.shopcart.dto.ShopCartDetailDto;
import com.rfw.jiajia.shopcart.dto.ShopCartDto;
import com.rfw.jiajia.shopcart.dto.SkuDetailDto;
import com.rfw.jiajia.shopcart.dto.SkuDto;
import com.rfw.jiajia.shopcart.dto.StallDetailDto;
import com.rfw.jiajia.shopcart.logic.IShortCartLogic;
import com.rfw.jiajia.shopcart.models.ShopCart;
import com.rfw.jiajia.shopcart.models.ShopCartDetail;
import com.rfw.jiajia.shopcart.models.ShopCartItem;

public class ShopCartLogicImpl implements IShortCartLogic {

    private static final Logger LOG = LoggerFactory.getLogger(ShopCartLogicImpl.class);

    private static IShortCartLogic instance = new ShopCartLogicImpl();

    private ShopCartLogicImpl() {
    }

    public static IShortCartLogic getInstance() {
        return instance;
    }

    private IShopCartDetailDao cartDetailDao = ShopCartDetailDaoImpl.getInstance();

    private IShopCartDao cartDao = ShopCartDaoImpl.getInstance();

    private IItemPlayDao itemDao = ItemPlayDaoImpl.getInstance();

    // private IStallsDao stallDao = StallsDaoImpl.getInstance();

    private IShopCartItemDao cartItemDao = ShopCartItemDaoImpl.getInstance();

    @Override
    public ProcessStatus addItemToCart(String accountId, Long numIid, String skuJson, Integer from, Integer isAdd) {

        ProcessStatus processStatus = new ProcessStatus(false);

        if (StringUtils.isBlank(skuJson)) {
            return new ProcessStatus(false, "请选选择宝贝~~~~");
        }

        // 添加shopCart
        processStatus = createShopCart(accountId);
        if (!processStatus.isSuccess()) {
            return processStatus;
        }
        long shopCartId = (Long) processStatus.getResult();

        // 添加shopCartItem
        processStatus = createShopCartItem(numIid, accountId, shopCartId, from);
        if (!processStatus.isSuccess()) {
            return processStatus;
        }

        SkuDto[] skuDtos = JsonUtil.toObject(skuJson, SkuDto[].class);

        if (skuDtos == null) {
            return new ProcessStatus(false, "请选择相应的sku~~~~~");
        }

        for (SkuDto skuDto : skuDtos) {

            ShopCartDetail shopCartDetail = cartDetailDao.findByNumIidAndProps(accountId, numIid,
                    skuDto.getPropertiesName());
            try {

                if (shopCartDetail == null) {
                    if (skuDto.getNum() > 0) {
                        String sku = JsonUtil.toJsonWithException(skuDto);
                        processStatus = createCartDetail(accountId, shopCartId, numIid, skuDto, sku, from);
                    }

                } else {

                    if (isAdd != null) {
                        if (shopCartDetail.getStatus() != ShopCartStatus.IS_DELETE) {
                            skuDto.setPrice(skuDto.getPrice() + shopCartDetail.getPrice());
                            skuDto.setNum(skuDto.getNum() + shopCartDetail.getNum());
                        }
                    }
                    String sku = JsonUtil.toJsonWithException(skuDto);
                    processStatus = updateDetail(shopCartDetail, skuDto, sku, from, isAdd);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        return processStatus;
    }

    private ProcessStatus createShopCart(String accountId) {

        ProcessStatus processStatus = new ProcessStatus(false);

        ShopCart shopCart = cartDao.findByAccount(accountId);
        if (shopCart == null) {
            // TODO 创建新的 shopcart
            processStatus = createCart(accountId);
        } else {
            processStatus.setResult(shopCart.getId());
            processStatus.setSuccess(true);
        }

        return processStatus;
    }

    private ProcessStatus createShopCartItem(Long numIid, String accountId, Long shopCartId, Integer from) {
        ProcessStatus processStatus = new ProcessStatus(false);
        ShopCartItem cartItem = cartItemDao.findByNumIid(numIid);
        // 创建shopCartItem
        if (cartItem == null) {
            processStatus = createShopCartItem(accountId, numIid, shopCartId);
        } else {
            processStatus = updateShopCartItem(cartItem, from);
        }

        return processStatus;
    }

    private ProcessStatus updateShopCartItem(ShopCartItem cartItem, Integer from) {
        ProcessStatus processStatus = new ProcessStatus(false);
        if (from == null) {
            cartItem.setModified(new Date());
        }
        if (cartItem.getIsDelete() == ShopCartStatus.IS_DELETE) {
            cartItem.setIsDelete(ShopCartStatus.IS_NOT_DELETE);
        }

        boolean isSuccess = cartItemDao.update(cartItem) > 0;
        processStatus.setSuccess(isSuccess);
        return processStatus;
    }

    private ProcessStatus createShopCartItem(String accountId, Long numIid, Long shopCartId) {
        ShopCartItem cartItem = new ShopCartItem();
        cartItem.setAccountId(accountId);
        cartItem.setNumIid(numIid);
        cartItem.setShopCartId(shopCartId);
        cartItem.setModified(new Date());
        cartItem.setIsDelete(ShopCartStatus.IS_NOT_DELETE);
        ProcessStatus processStatus = new ProcessStatus(false);
        boolean isSuccess = cartItemDao.insert(cartItem) > 0;
        processStatus.setSuccess(isSuccess);
        return processStatus;
    }

    @Override
    public ShopCartDto findByAccount(String account) {

        ShopCartDto shopCartDto = new ShopCartDto();

        ShopCart shopCart = cartDao.findByAccount(account);

        if (shopCart == null) {
            return shopCartDto;
        }

        List<ShopCartDetail> shopCartDetails = cartDetailDao.findByCartId(shopCart.getId());

        Map<Long, List<ShopCartDetailDto>> map = groupForDetail(shopCartDetails);

        shopCartDto = convertToDetailDto(shopCart, map);

        return shopCartDto;
    }

    @Override
    public ProcessStatus deleteItems(String account, String ids, String numIids) {

        if (StringUtils.isBlank(ids)) {
            return new ProcessStatus(false, "请先选择宝贝~~~~");
        }

        ProcessStatus processStatus = new ProcessStatus(false);
        boolean isSuccess = cartDetailDao.updateBatch(account, ids, 0, ShopCartStatus.IS_DELETE) > 0;
        boolean isSuccess1 = cartItemDao.updateBatch(account, numIids, ShopCartStatus.IS_DELETE) > 0;
        processStatus.setSuccess(isSuccess1);
        return processStatus;
    }

    private ProcessStatus updateDetail(ShopCartDetail detail, SkuDto skuDto, String skuJson, Integer from,
            Integer isAdd) {
        ProcessStatus processStatus = new ProcessStatus(false);

        LOG.warn("skuDto num:{}", skuDto.getNum());
        if (skuDto.getNum() > 0) {

            detail.setSku(skuJson);
            detail.setPrice(skuDto.getPrice());
            detail.setNum(skuDto.getNum());

            if (detail.getStatus() == ShopCartStatus.IS_DELETE) {
                LOG.warn("原先购物车中已存在~~~");
                detail.setStatus(ShopCartStatus.IS_NOT_DELETE);
            }
            if (from == null) {
                detail.setModified(new Date());
            }
            boolean isSuccess = cartDetailDao.update(detail) > 0;
            processStatus.setSuccess(isSuccess);
            return processStatus;
        } else {
            boolean isSuccess = cartDetailDao.updateBatch(detail.getAccountId(), String.valueOf(detail.getId()), 0,
                    ShopCartStatus.IS_DELETE) > 0;
            processStatus.setSuccess(isSuccess);
            return processStatus;
        }

    }

    private ProcessStatus createCart(String accountId) {
        ProcessStatus processStatus = new ProcessStatus(false);
        ShopCart shopCart = new ShopCart(accountId);

        boolean isSuccess = cartDao.insert(shopCart) > 0;
        if (isSuccess) {
            processStatus.setSuccess(isSuccess);
            processStatus.setResult(shopCart.getId());
            return processStatus;
        }
        processStatus.setMessage("添加购物车失败!");
        return processStatus;

    }

    private ShopCartDto convertToDetailDto(ShopCart shopCart, Map<Long, List<ShopCartDetailDto>> map) {

        ShopCartDto shopCartDto = new ShopCartDto();
        shopCartDto.setAccountId(shopCart.getAccountId());

        List<StallDetailDto> stallDetailDtos = new ArrayList<StallDetailDto>();

        for (Entry<Long, List<ShopCartDetailDto>> m : map.entrySet()) {

            List<ShopCartDetailDto> list = m.getValue();
            // 排序
            Collections.sort(list);
            m.setValue(list);

            String sellerNick = list.get(0).getSellerNick();
            String shopName = list.get(0).getShopName();
            StallDetailDto stallDetailDto = new StallDetailDto();
            stallDetailDto.setShopCartDetailDtos(list);
            stallDetailDto.setSellerNick(sellerNick);
            stallDetailDto.setShopName(shopName);
            stallDetailDto.setModified(list.get(0).getModified());
            stallDetailDto.setSellerMobile(list.get(0).getSellerMobile());
            stallDetailDto.setStallId(list.get(0).getStallId());

            if (DateUtils.formCurrDate() == DateUtils.formDailyTimestamp(list.get(0).getModified())) {
                stallDetailDto.setIsToday(ShopCartStatus.IS_TODAY);
            } else {
                stallDetailDto.setIsToday(ShopCartStatus.IS_NOT_TODAY);
            }
            stallDetailDtos.add(stallDetailDto);

        }

        // 店铺排序
        Collections.sort(stallDetailDtos);
        shopCartDto.setDetails(stallDetailDtos);
        shopCartDto.setShopCartId(shopCart.getId());
        return shopCartDto;
    }

    private Map<Long, List<ShopCartDetailDto>> groupForDetail(List<ShopCartDetail> details) {

        Map<Long, List<ShopCartDetail>> map = new HashMap<Long, List<ShopCartDetail>>();

        // 按照Stall分组
        for (ShopCartDetail detail : details) {
            if (map.containsKey(detail.getStallId())) {
                List<ShopCartDetail> detailList = map.get(detail.getStallId());
                detailList.add(detail);
            } else {
                List<ShopCartDetail> detailList = new ArrayList<ShopCartDetail>();
                detailList.add(detail);
                map.put(detail.getStallId(), detailList);
            }
        }

        // 按照numIid分组

        // 最终结果
        Map<Long, List<ShopCartDetailDto>> mapLast = new HashMap<Long, List<ShopCartDetailDto>>();
        for (Entry<Long, List<ShopCartDetail>> m : map.entrySet()) {
            List<ShopCartDetail> cartDetail = m.getValue();

            Map<Long, ShopCartDetailDto> mapDetail = new HashMap<Long, ShopCartDetailDto>();
            for (ShopCartDetail detail : cartDetail) {
                if (mapDetail.containsKey(detail.getNumIid())) {

                    List<SkuDetailDto> skus = mapDetail.get(detail.getNumIid()).getSkus();
                    SkuDetailDto skuDetailDto = new SkuDetailDto();
                    skuDetailDto.setId(detail.getId());
                    skuDetailDto.setSku(detail.getSku());
                    skus.add(skuDetailDto);
                } else {
                    ShopCartDetailDto dto = new ShopCartDetailDto();
                    dto.setNumIid(detail.getNumIid());
                    dto.setPicPath(detail.getPicPath());
                    List<SkuDetailDto> skus = new ArrayList<SkuDetailDto>();

                    ShopCartItem cartItem = cartItemDao.findByNumIid(detail.getNumIid());

                    SkuDetailDto skuDetailDto = new SkuDetailDto();
                    skuDetailDto.setId(detail.getId());
                    skuDetailDto.setSku(detail.getSku());
                    skus.add(skuDetailDto);
                    dto.setSkus(skus);
                    dto.setTitle(detail.getTitle());
                    dto.setShopName(detail.getShopName());

                    dto.setSellerNick(detail.getSellerNick());
                    dto.setStallId(detail.getStallId());
                    dto.setSellerMobile(detail.getSellerMobile());
                    dto.setReducePrice(detail.getReducePrice());
                    dto.setModified(cartItem.getModified());

                    ItemPlay item = ItemPlayDaoImpl.getInstance().findByNumIid(detail.getNumIid());
                    dto.setLocalApproveStatus(item.getLocalApproveStatus());
                    if (DateUtils.formCurrDate() == DateUtils.formDailyTimestamp(cartItem.getModified())) {
                        dto.setIsToday(ShopCartStatus.IS_TODAY);
                    } else {
                        dto.setIsToday(ShopCartStatus.IS_NOT_TODAY);
                    }
                    mapDetail.put(detail.getNumIid(), dto);
                }
            }

            for (Entry<Long, ShopCartDetailDto> md : mapDetail.entrySet()) {

                if (mapLast.containsKey(m.getKey())) {
                    List<ShopCartDetailDto> dtos = mapLast.get(m.getKey());
                    dtos.add(md.getValue());
                } else {
                    List<ShopCartDetailDto> list = new ArrayList<ShopCartDetailDto>();
                    list.add(md.getValue());
                    mapLast.put(m.getKey(), list);
                }

            }
        }

        return mapLast;
    }

    private ProcessStatus createCartDetail(String accountId, Long shopCartId, Long numIid, SkuDto skuDto,
            String skuJson, Integer from) {

        ProcessStatus processStatus = new ProcessStatus(false);
        ShopCartDetail shopCartDetail = new ShopCartDetail();

        ItemPlay item = itemDao.findByNumIid(numIid);

        String nick = item.getNick();

        // Stalls stalls = stallDao.findByNick(nick);
        shopCartDetail.setAccountId(accountId);
        shopCartDetail.setNum(skuDto.getNum());
        // shopCartDetail.setSellerNick(stalls.getWangwang());
        // shopCartDetail.setStallId(stalls.getId());
        shopCartDetail.setPrice(skuDto.getPrice());
        shopCartDetail.setShopCartId(shopCartId);
        shopCartDetail.setNumIid(numIid);
        shopCartDetail.setTitle(item.getTitle());
        shopCartDetail.setPicPath(item.getPicUrl());
        shopCartDetail.setSku(skuJson);
        // shopCartDetail.setShopName(stalls.getShopName());
        shopCartDetail.setPropsName(skuDto.getPropertiesName());
        shopCartDetail.setOuterId(item.getOuterId());
        shopCartDetail.setReducePrice(item.getReducePrice());
        // shopCartDetail.setSellerMobile(stalls.getMobile());

        if (from == null) {
            shopCartDetail.setModified(new Date());
        }
        boolean isSuccess = cartDetailDao.insert(shopCartDetail) > 0;

        processStatus.setSuccess(isSuccess);
        return processStatus;
    }

    @Override
    public long countByAccount(String accountId) {
        return cartDetailDao.countByAccount(accountId);
    }
}
