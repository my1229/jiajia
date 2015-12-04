/**    
 * @author mlc  
 * @version 1.0  
 *
 * 2015年8月3日   
 */
package com.rfw.jiajia.item.dto;

import java.io.Serializable;

public class ItemPropValueDto implements Serializable {

    /**
     * 属性值
     */
    private String name;

    private Long vid;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getVid() {
        return vid;
    }

    public void setVid(Long vid) {
        this.vid = vid;
    }

}
