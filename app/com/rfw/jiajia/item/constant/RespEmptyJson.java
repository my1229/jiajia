package com.rfw.jiajia.item.constant;

/**
 * @author minlingchao
 * @date 2015-6-4 下午2:57:17
 * @description
 * @version
 */

public enum RespEmptyJson {
    ITEM_PROPS_GET_RESPONSE("{\"itemprops_get_response\":{}");
    private String json;

    private RespEmptyJson(String json) {
        this.setJson(json);
    }

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }
}
