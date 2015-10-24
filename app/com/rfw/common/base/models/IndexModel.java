/**
 * 
 */
package com.rfw.common.base.models;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * @author lizhong.chen
 * @date 2014年11月18日下午1:27:12
 * @description 索引
 * @version V1.0
 */
@MappedSuperclass
public class IndexModel extends BasicModel {
    @Column(name = "last_index_ts")
    private long lastIndexTs;

    public long getLastIndexTs() {
        return lastIndexTs;
    }

    public void setLastIndexTs(long lastIndexTs) {
        this.lastIndexTs = lastIndexTs;
    }

}
