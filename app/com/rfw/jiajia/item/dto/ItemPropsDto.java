/**    
 * @author mlc  
 * @version 1.0  
 * 属性Dto
 * 2015年8月3日   
 */
package com.rfw.jiajia.item.dto;

import java.io.Serializable;
import java.util.List;

public class ItemPropsDto implements Serializable {

    /**
     * 类目Cid
     */
    private Long cid;

    /**
     * 属性Id
     */
    private Long pid;

    /**
     * 属性名
     */
    private String propName;

    /**
     * 是否必填
     */
    private Integer must;

    /**
     * 属性值
     */
    private List<ItemPropValueDto> values;

    public Long getCid() {
        return cid;
    }

    public void setCid(Long cid) {
        this.cid = cid;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public Integer getMust() {
        return must;
    }

    public void setMust(Integer must) {
        this.must = must;
    }

    public String getPropName() {
        return propName;
    }

    public void setPropName(String propName) {
        this.propName = propName;
    }

    public List<ItemPropValueDto> getValues() {
        return values;
    }

    public void setValues(List<ItemPropValueDto> values) {
        this.values = values;
    }

}
