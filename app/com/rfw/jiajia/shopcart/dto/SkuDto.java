/**   
 * @Description: sku DTO相关
 * @author mlc
 * @date 2015年8月11日 下午1:23:13 
 * @version V1.0   
 */

package com.rfw.jiajia.shopcart.dto;

public class SkuDto {

    /**
     * 属性编号
     */
    private String properties;

    /**
     * 属性名
     */
    private String propertiesName;

    /**
     * 数量
     */
    private Long num;

    /**
     * 价格
     */
    private Double price;

    public String getProperties() {
        return properties;
    }

    public void setProperties(String properties) {
        this.properties = properties;
    }

    public String getPropertiesName() {
        return propertiesName;
    }

    public void setPropertiesName(String propertiesName) {
        this.propertiesName = propertiesName;
    }

    public Long getNum() {
        return num;
    }

    public void setNum(Long num) {
        this.num = num;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

}
