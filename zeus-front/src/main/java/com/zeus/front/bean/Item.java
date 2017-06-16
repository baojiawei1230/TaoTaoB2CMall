package com.zeus.front.bean;

import org.apache.commons.lang3.StringUtils;

public class Item extends com.zeus.manage.pojo.Item {

    public String[] getImages() {
        return super.getImage() == null ? null : StringUtils.split(super.getImage(), ',');
    }

}
