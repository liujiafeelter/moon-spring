package com.moon.store.test.designpattern.templatemethod;

import java.util.List;

public abstract class AbstractBtn {

    public void buildBtn(BtnContext btnContext, List<ButtonInfo> btnList){
        if(this.canShow(btnContext)){
            ButtonInfo buttonInfo = this.buildBtn(btnContext);
            btnList.add(buttonInfo);
        }
    }

    protected abstract boolean canShow(BtnContext btnContext);

    protected abstract ButtonInfo buildBtn(BtnContext btnContext);

}
