package com.xbzhangshi.mvp.base;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.xbzhangshi.R;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFragment extends Fragment {

    protected View mView;

    /**
     * 表示View是否被初始化
     */
    protected boolean isViewInitiated;
    /**
     * 表示对用户是否可见
     */
    protected boolean isVisibleToUser;
    /**
     * 表示数据是否初始化
     */
    protected boolean isDataInitiated;
    public Activity mActivity;
    Unbinder unbinder;

    @Override
    public void onAttach(Context context) {
        LogUtils.i(getClass().getSimpleName() + ">>>>>>>>>>>　　onAttach");
        super.onAttach(context);
        mActivity = getActivity();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        LogUtils.i(getClass().getSimpleName() + ">>>>>>>>>>>　　onCreate");
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable
            Bundle savedInstanceState) {
        LogUtils.i(getClass().getSimpleName() + ">>>>>>>>>>>　　onCreateView");
        if (mView == null) {
            mView = inflater.inflate(getLayoutId(), container, false);
            unbinder = ButterKnife.bind(this, mView);
            initView(mView);
            LogUtils.i(getClass().getSimpleName() + ">>>>>>>>>>>　　initCreatView");
        } else {
            // 缓存的rootView需要判断是否已经被加过parent，如果有parent需要从parent删除，
            // 要不然会发生这个rootview已经有parent的错误。
            ViewGroup parent = (ViewGroup) mView.getParent();
            if (parent != null) {
                parent.removeView(mView);
            }
            unbinder = ButterKnife.bind(this, mView);
            LogUtils.i(getClass().getSimpleName() + ">>>>>>>>>>>　　removeView");
        }
      /*  mView = inflater.inflate(getLayoutId(), container, false);
        unbinder = ButterKnife.bind(this, mView);
        initView(mView);*/
        return mView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initData(savedInstanceState);
    }
  /*  @Override
    public Animation onCreateAnimation(int transit, boolean enter, int nextAnim) {
        if (enter) {
            return AnimationUtils.loadAnimation(getActivity(), R.anim.anim_left_in);
        } else {
            return AnimationUtils.loadAnimation(getActivity(), R.anim.anim_left_out);
        }
    }*/
    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        LogUtils.i(getClass().getSimpleName() + ">>>>>>>>>>>　　onActivityCreated");
        isViewInitiated = true;
        prepareFetchData();
    }


    /**
     * @return 获取fragment的布局
     */
    protected abstract int getLayoutId();

    /**
     * 初始化view id
     *
     * @param view fragment的布局
     */
    protected abstract void initView(View view);

    protected void initData(Bundle savedInstanceState) {

    }

    /**
     * 判断是否是初始化Fragment
     */
    public boolean hasStarted = false;


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        this.isVisibleToUser = isVisibleToUser;
        prepareFetchData();
        if (isVisibleToUser) {
            hasStarted = true;
            LogUtils.i(getClass().getSimpleName() + "：开始界面");
        } else {
            if (hasStarted) {
                hasStarted = false;
                LogUtils.i(getClass().getSimpleName() + "：结束界面");
                closeView();
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
//        MobclickAgent.onPageStart(this.getClass().getName()); //统计页面("MainScreen"为页面名称，可自定义)
    }

    @Override
    public void onPause() {
        super.onPause();
//        MobclickAgent.onPageEnd(this.getClass().getName());
    }

    public void closeView() {

    }

    public void fetchData() {
    }

    public boolean prepareFetchData() {
        return prepareFetchData(false);
    }

    /***
     *
     * @param forceUpdate 表示是否在界面可见的时候是否强制刷新数据
     *  @return 强制刷新是否完成
     */
    public boolean prepareFetchData(boolean forceUpdate) {
        boolean b = isVisibleToUser && isViewInitiated && (!isDataInitiated || forceUpdate);
        if (b) {
            //界面可见的时候再去加载数据
            fetchData();
            isDataInitiated = true;
            return true;
        }
        return false;
    }


    @Override
    public void onDestroy() {
        LogUtils.i(getClass().getSimpleName() + ">>>>>>>>>>>　　onDestroy");
        super.onDestroy();

    }
}
