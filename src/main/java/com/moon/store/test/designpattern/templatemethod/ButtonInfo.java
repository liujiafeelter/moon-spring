package com.moon.store.test.designpattern.templatemethod;

import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * 按钮实体类
 */
public class ButtonInfo {

    /**按钮文案*/
    public String showLabel;
    /**按钮id*/
    public int showLabelId;
    /**图标**/
    private String icon;
    /**按钮是否可点击*/
    public boolean canClick;
    /**按钮跳转url*/
    public String url;
    /**按钮跳转类型："0":"普通链接","1":"原生跳转","2":"OpenApp","3":"url刷新","4":"原生刷新","5":"OA刷新"*/
    public String opType;
    /**按钮业务Map*/
    public Map<String,Object> businessMap = new HashMap<>(16);
    /**按钮埋点Map*/
    public Map<String,Object> pointMap = new HashMap<>(16);


    public String getShowLabel() {
        return showLabel;
    }

    public void setShowLabel(String showLabel) {
        this.showLabel = showLabel;
    }

    public int getShowLabelId() {
        return showLabelId;
    }

    public void setShowLabelId(int showLabelId) {
        this.showLabelId = showLabelId;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public boolean isCanClick() {
        return canClick;
    }

    public void setCanClick(boolean canClick) {
        this.canClick = canClick;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getOpType() {
        return opType;
    }

    public void setOpType(String opType) {
        this.opType = opType;
    }

    public Map<String, Object> getBusinessMap() {
        return businessMap;
    }

    public void setBusinessMap(Map<String, Object> businessMap) {
        this.businessMap = businessMap;
    }

    public Map<String, Object> getPointMap() {
        return pointMap;
    }

    public void setPointMap(Map<String, Object> pointMap) {
        this.pointMap = pointMap;
    }
}
