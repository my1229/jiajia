package com.rfw.jiajia.item.constant;

/**
 * 宝贝出售状态
 * 
 * @author liangyang
 * @date 2015年2月13日 下午1:24:45
 * @version 1.0
 */
public class ApproveStatus {

    /**
     * 在售
     */
    public final static int ONSALE = 1;
    public final static String ONSALE_STR = "onsale";

    /**
     * 库存
     */
    public final static int INSTOCK = 2;
    public final static String INSTOCK_STR = "instock";

    public static int parseApproveStatus(String approveStatus) {

        if ("onsale".equals(approveStatus.toLowerCase())) {
            return ONSALE;
        } else if ("instock".equals(approveStatus.toLowerCase())) {
            return INSTOCK;
        } else {
            return ONSALE;
        }
    }

}
