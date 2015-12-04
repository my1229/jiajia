package com.rfw.jiajia.item.dto;

import java.io.Serializable;
import java.util.List;

public class ItemCatPropDto implements Serializable {

    private Long cid;

    private List<ItemPropsDto> propDtos;

    public List<ItemPropsDto> getPropDtos() {
        return propDtos;
    }

    public void setPropDtos(List<ItemPropsDto> propDtos) {
        this.propDtos = propDtos;
    }

    public Long getCid() {
        return cid;
    }

    public void setCid(Long cid) {
        this.cid = cid;
    }
}
