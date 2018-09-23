package com.xbzhangshi.mvp.usercenter.presener;

import com.xbzhangshi.mvp.base.BasePresenter;
import com.xbzhangshi.mvp.login.BaseView.ILoginView;
import com.xbzhangshi.mvp.login.bean.LoginUserInfoBean;
import com.xbzhangshi.mvp.login.presenter.LogInPresenter;
import com.xbzhangshi.mvp.usercenter.BaseView.IUserInfoBaseView;
import com.xbzhangshi.single.UserInfo;

import java.util.List;

public class UserInfoPresener  extends BasePresenter{
    public static UserInfoPresener newInstance(IUserInfoBaseView contentView) {
        return new UserInfoPresener(contentView);
    }

    IUserInfoBaseView contentView;

    public UserInfoPresener(IUserInfoBaseView contentView) {
        this.contentView = contentView;
    }

    public  void  initData(){
        LoginUserInfoBean loginBean = UserInfo.getInstance().loginUserInfoBean;
        if(loginBean==null){
            return;
        }
        contentView.setAccount(loginBean.getContent().getAccount());
        contentView.setRealName(loginBean.getContent().getUserName());
        contentView.setRPhoneNumber(loginBean.getContent().getPhone());
        contentView.setEmail(loginBean.getContent().getEmail());
        contentView.setQQ(loginBean.getContent().getQq());
        contentView.setBankNum(loginBean.getContent().getCardNo());
        contentView.setOpeningBank(loginBean.getContent().getBankName());
        contentView.setBankAddress(loginBean.getContent().getBankAddress());
    }


}
