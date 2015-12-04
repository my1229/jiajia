/**   
 * @Description: TODO
 * @author mlc
 * @date 2015年8月31日 下午5:27:52 
 * @version V1.0   
 */

package com.rfw.jiajia.item.dto;

public class ItemOnSaleDto {

    private String accountId;

    private String title;

    private Long numIid;

    private Long publishTime;

    private Long oldNumIid;

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getNumIid() {
        return numIid;
    }

    public void setNumIid(Long numIid) {
        this.numIid = numIid;
    }

    public Long getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Long publishTime) {
        this.publishTime = publishTime;
    }

    public Long getOldNumIid() {
        return oldNumIid;
    }

    public void setOldNumIid(Long oldNumIid) {
        this.oldNumIid = oldNumIid;
    }

}
