package com.moon.store.test.designpattern.templatemethod;

public class CancelOrderBtn extends AbstractBtn {

    public boolean canShow(BtnContext btnContext){
        return true;
    }

    public ButtonInfo buildBtn(BtnContext btnContext){
        return new ButtonInfo();
    }

}
