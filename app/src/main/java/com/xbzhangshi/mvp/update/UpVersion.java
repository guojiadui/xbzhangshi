package com.xbzhangshi.mvp.update;

import android.app.Application;
import android.app.Dialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.allenliu.versionchecklib.callback.APKDownloadListener;
import com.allenliu.versionchecklib.v2.AllenVersionChecker;
import com.allenliu.versionchecklib.v2.builder.DownloadBuilder;
import com.allenliu.versionchecklib.v2.builder.NotificationBuilder;
import com.allenliu.versionchecklib.v2.builder.UIData;
import com.allenliu.versionchecklib.v2.callback.CustomVersionDialogListener;
import com.allenliu.versionchecklib.v2.callback.ForceUpdateListener;
import com.allenliu.versionchecklib.v2.callback.RequestVersionListener;
import com.blankj.utilcode.util.FileUtils;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;
import com.xbzhangshi.R;
import com.xbzhangshi.app.MyApplication;
import com.xbzhangshi.app.Url;
import com.xbzhangshi.http.HttpManager;
import com.xbzhangshi.mvp.home.bean.VersionBean;
import com.xbzhangshi.view.dialog.UpVersionDialog;

import java.io.File;

public class UpVersion {


    public static boolean isDownloading = false;

    /**
     * 更新本部内容到服务器
     *
     * @param
     * @param
     */
    public static void setUpVerisonContent(Context content) {
        HttpParams httpParams = new HttpParams();
        httpParams.put("flag", "android");
        httpParams.put("version", "1");
        httpParams.put("content", "版本更新测试");
        httpParams.put("isUpdate", "2");//1为强制更新
        httpParams.put("url", "https://app.xbzhanshi.com/download/xbzhangshi2.0.apk");
        HttpManager.post(content, Url.saveAppUpdate, httpParams, new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                Log.e("Up", response.body());
            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);
            }
        });
    }


    public static void upVersion(Context context, VersionBean versionBean) {
        if (isDownloading) {
            return;
        }
        if (versionBean.getContent().getIsUpdate() == 1) {
            //强制更新
            AllenVersionChecker
                    .getInstance()
                    .downloadOnly(
                            UpVersion.crateUIData().setTitle("版本更新")
                                    .setContent(versionBean.getContent().getContent())
                                    .setDownloadUrl(versionBean.getContent().getUrl())

                    )
                    .setDownloadAPKPath(com.xbzhangshi.util.FileUtils.createAppStoragePath("apk").getAbsolutePath() + File.separator)
                    .setForceRedownload(false)//本地有安装包缓存也不会重新下载
                    .setShowDownloadingDialog(true)//显示下载进度
                    .setNewestVersionCode(Integer.parseInt(versionBean.getContent().getVersion()))
                    .setShowNotification(true)//通知栏
                    .setNotificationBuilder(createCustomNotification())//自定通知栏
                    .setCustomVersionDialogListener(new CustomVersionDialogListener() {
                        @Override
                        public Dialog getCustomVersionDialog(Context context, UIData versionBundle) {
                            UpVersionDialog upVersionDialog = new UpVersionDialog(context);
                            upVersionDialog.setCanceledOnTouchOutside(false);
                            TextView mTitle = upVersionDialog.findViewById(R.id.title);
                            TextView mContentTip = upVersionDialog.findViewById(R.id.content_tip);
                            mTitle.setText(versionBundle.getTitle());
                            mContentTip.setText(versionBundle.getContent());
                            return upVersionDialog;
                        }
                    })
                    .setApkDownloadListener(new APKDownloadListener() {
                        @Override
                        public void onDownloading(int progress) {
                            isDownloading = true;
                        }

                        @Override
                        public void onDownloadSuccess(File file) {
                            isDownloading = false;
                        }

                        @Override
                        public void onDownloadFail() {
                            isDownloading = false;
                        }
                    }).setForceUpdateListener(new ForceUpdateListener() {
                @Override
                public void onShouldForceUpdate() {
                    //强制更新
                    if (versionBean.getContent().getIsUpdate() == 1) {
                        ((MyApplication) context.getApplicationContext()).exit();
                    }
                }
            }).excuteMission(context);


        } else {
            //非强制更新
            AllenVersionChecker
                    .getInstance()
                    .downloadOnly(
                            UpVersion.crateUIData().setTitle("版本更新")
                                    .setContent(versionBean.getContent().getContent())
                                    .setDownloadUrl(versionBean.getContent().getUrl())

                    )
                    .setDownloadAPKPath(com.xbzhangshi.util.FileUtils.createAppStoragePath("apk").getAbsolutePath() + File.separator)
                    .setForceRedownload(false)//本地有安装包缓存也不会重新下载
                    .setShowDownloadingDialog(false)//显示下载进度
                    .setNewestVersionCode(Integer.parseInt(versionBean.getContent().getVersion()))
                    .setShowNotification(true)//通知栏
                    .setNotificationBuilder(createCustomNotification())//自定通知栏
                    .setCustomVersionDialogListener(new CustomVersionDialogListener() {
                        @Override
                        public Dialog getCustomVersionDialog(Context context, UIData versionBundle) {
                            UpVersionDialog upVersionDialog = new UpVersionDialog(context);
                            upVersionDialog.setCanceledOnTouchOutside(false);
                            TextView mTitle = upVersionDialog.findViewById(R.id.title);
                            TextView mContentTip = upVersionDialog.findViewById(R.id.content_tip);
                            mTitle.setText(versionBundle.getTitle());
                            mContentTip.setText(versionBundle.getContent());
                            return upVersionDialog;
                        }
                    })
                    .setApkDownloadListener(new APKDownloadListener() {
                        @Override
                        public void onDownloading(int progress) {
                            isDownloading = true;
                        }

                        @Override
                        public void onDownloadSuccess(File file) {
                            isDownloading = false;
                        }

                        @Override
                        public void onDownloadFail() {
                            isDownloading = false;
                        }
                    }).excuteMission(context);
        }

    }

    /**
     * @return
     * @important 使用请求版本功能，可以在这里设置downloadUrl
     * 这里可以构造UI需要显示的数据
     * UIData 内部是一个Bundle
     */
    public static UIData crateUIData() {
        UIData uiData = UIData.create();
        return uiData;
    }

    private static NotificationBuilder createCustomNotification() {
        return NotificationBuilder.create()
                .setRingtone(true)
                .setIcon(R.mipmap.logo)
                .setTicker(R.string.app_name + "更新");
    }

    public static int getVersionCode(Context mContext) {
        int versionCode = 0;
        try {
            //获取软件版本号，对应AndroidManifest.xml下android:versionCode
            versionCode = mContext.getPackageManager().
                    getPackageInfo(mContext.getPackageName(), 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionCode;
    }

}
