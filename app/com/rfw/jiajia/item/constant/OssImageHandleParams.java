package com.rfw.jiajia.item.constant;

public class OssImageHandleParams {

    /**
     * 强制宽为640
     */
    public static final String COMPEL_WIDTH_640 = "@640w";

    /**
     * 中心位置截图640x832
     */
    public static final String RANGE_CUT_640_832 = "@640x832-5rc";

    /**
     * 按比例缩放使宽为640，再中心位置区域截图640x832
     */
    public static final String W_RANGE_CUT_640_832 = "@640w|640x832-5rc";

    /**
     * 按比例缩放使高为832，再中心位置区域截图640x832
     */
    public static final String H_RANGE_CUT_640_832 = "@832h|640x932-5rc";

    /**
     * 强制宽为640，高为832，若目标缩略图大于原图强制拉伸
     */
    public static final String COMPEL_WIDTH_HEIGHT_640_832 = "@640w_832h_4e";

    /**
     * 强制宽为95，高为93，若目标缩略图大于原图强制拉伸
     */
    public static final String COMPEL_WIDTH_HEIGHT_95_93 = "@95w_93h_4e";

    /**
     * 强制宽为170，高为200，若目标缩略图大于原图强制拉伸
     */
    public static final String COMPEL_WIDTH_HEIGHT_170_200 = "@170w_200h_4e";

}
