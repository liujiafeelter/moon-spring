package com.moon.store.test.designpattern.templatemethod;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ButtonBuilder {

    private HashMap<String, AbstractBtn> btnMap = new HashMap<>(16);

    /**
     * 构建按钮
     * @return
     */
    private List<ButtonInfo> buildButtons(String orderStatus){
        List<ButtonInfo> btnList = new ArrayList<>(10);
        BtnContext btnContext = new BtnContext();

        //从配置中心读取按钮的配置,不同状态获取不同的按钮配置
        List<String> btnKeyList = this.getBtnFromConfig(orderStatus);

        for(String btnKey: btnKeyList){
            AbstractBtn btn = btnMap.get(btnKey);
            btn.buildBtn(btnContext, btnList);
        }

        return btnList;
    }

    private List<String> getBtnFromConfig(String orderStatus){
        List<String> btnStr = new ArrayList<>(10);
        btnStr.add("cancelOrderBtn");
        return btnStr;
    }


}
