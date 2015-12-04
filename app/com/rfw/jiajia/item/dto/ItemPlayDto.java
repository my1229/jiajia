/**    
 * @author mlc  
 * @version 1.0  
 *
 * 2015年7月16日   
 */
package com.rfw.jiajia.item.dto;

import java.io.Serializable;
import java.util.List;

import com.rfw.jiajia.item.models.ItemImg;
import com.rfw.jiajia.item.models.ItemSku;

public class ItemPlayDto implements Serializable {

    /**
     * 宝贝id
     */
    private long numIid;

    /**
     * 宝贝标题
     */
    private String title;

    /**
     * 宝贝类目
     */
    private Long cid;

    /**
     * 宝贝类目名称
     */
    private String catName;

    /**
     * 父类目
     */
    private Long parentCid;

    /**
     * 父类目名称
     */
    private String parentCatName;
    /**
     * 卖家自定义类目
     */
    private String sellerCids;

    /**
     * 批发价
     */
    private Double tradePrice;

    /**
     * 差价
     */
    private Double reducePrice;

    /**
     * 售价
     */
    private Double price;

    /**
     * 城市
     */
    private String city;

    /**
     * 省份
     */
    private String state;

    /**
     * 宝贝展示图片
     */
    private List<ItemImg> itemImgs;

    /**
     * sku
     */
    private List<ItemSku> skus;

    /**
     * 宝贝属性
     * 
     * @return
     */
    private String propsName;

    /**
     * 上架时间
     * 
     * @return
     */
    private Long created;

    /**
     * 编码
     *
     */
    private String outerId;

    /**
     * 是否已授权
     */
    private Integer isOauth;

    /**
     * 上下架状态
     */
    private Integer localApproveStatus;

    /**
     * 昵称
     */
    private String nick;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getCid() {
        return cid;
    }

    public void setCid(Long cid) {
        this.cid = cid;
    }

    public String getSellerCids() {
        return sellerCids;
    }

    public void setSellerCids(String sellerCids) {
        this.sellerCids = sellerCids;
    }

    public Double getTradePrice() {
        return tradePrice;
    }

    public void setTradePrice(Double tradePrice) {
        this.tradePrice = tradePrice;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public List<ItemImg> getItemImgs() {
        return itemImgs;
    }

    public void setItemImgs(List<ItemImg> itemImgs) {
        this.itemImgs = itemImgs;
    }

    public List<ItemSku> getSkus() {
        return skus;
    }

    public void setSkus(List<ItemSku> skus) {
        this.skus = skus;
    }

    public long getNumIid() {
        return numIid;
    }

    public void setNumIid(long numIid) {
        this.numIid = numIid;
    }

    public String getPropsName() {
        return propsName;
    }

    public void setPropsName(String propsName) {
        this.propsName = propsName;
    }

    public Long getCreated() {
        return created;
    }

    public void setCreated(Long created) {
        this.created = created;
    }

    public String getOuterId() {
        return outerId;
    }

    public void setOuterId(String outerId) {
        this.outerId = outerId;
    }

    public Integer getIsOauth() {
        return isOauth;
    }

    public void setIsOauth(Integer isOauth) {
        this.isOauth = isOauth;
    }

    public Double getReducePrice() {
        return reducePrice;
    }

    public void setReducePrice(Double reducePrice) {
        this.reducePrice = reducePrice;
    }

    public Integer getLocalApproveStatus() {
        return localApproveStatus;
    }

    public void setLocalApproveStatus(Integer localApproveStatus) {
        this.localApproveStatus = localApproveStatus;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    public String getParentCatName() {
        return parentCatName;
    }

    public void setParentCatName(String parentCatName) {
        this.parentCatName = parentCatName;
    }

    public Long getParentCid() {
        return parentCid;
    }

    public void setParentCid(Long parentCid) {
        this.parentCid = parentCid;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

}
