package com.xbzhangshi.mvp.usercenter.BaseView;

import com.xbzhangshi.mvp.usercenter.bean.DrawMoneyInfoBean;

public interface IDrawingMoneyBaseView {

    public void  setConfigInfo(DrawMoneyInfoBean bean);
    public void  setConfigError(String s);
    public void  drawSuccess( );
    public void  drawError(String s);
}
