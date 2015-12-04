package com.rfw.jiajia.item.constant;

import org.apache.commons.lang.StringUtils;

/**
 * 运费承担方式,seller（卖家承担），buyer(买家承担）
 * 
 * @author liangyang
 * @date 2015年2月13日 下午2:51:30
 * @version 1.0
 */
public class FreightPayer {

    public final static int UN_SET = 6;

    public final static int SELLER = 1;

    public final static int BUYER = 2;

    public final static String SELLER_STR = "seller";

    public final static String BUYER_STR = "buyer";

    public static int parseFreightPayer(String freightPayer) {

        if (StringUtils.isBlank(freightPayer)) {
            return UN_SET;
        }

        if ("seller".equals(freightPayer.toLowerCase())) {
            return SELLER;
        } else if ("buyer".equals(freightPayer.toLowerCase())) {
            return BUYER;
        } else {
            return UN_SET;
        }
    }

    public static String parseFreightPayerStr(int freightPayer) {

        if (SELLER == freightPayer) {
            return SELLER_STR;
        } else {
            return BUYER_STR;
        }
    }
}
