/**
 * @author mrmin
 * @date 2015年8月11日 下午10:37:50
 * @version 1.0
 */
package com.rfw.jiajia.shopcart.dto;

public class SkuDetailDto {

    /**
     * 数据库Id
     */
    private Long id;

    /**
     * sku
     */
    private String sku;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

}
