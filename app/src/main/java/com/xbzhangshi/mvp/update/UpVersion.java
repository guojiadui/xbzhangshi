package com.xbzhangshi.mvp.update;

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
import com.allenliu.versionchecklib.v2.callback.RequestVersionListener;
import com.blankj.utilcode.util.FileUtils;
import com.xbzhangshi.R;
import com.xbzhangshi.app.Url;
import com.xbzhangshi.mvp.home.bean.VersionBean;
import com.xbzhangshi.view.dialog.UpVersionDialog;

import java.io.File;

public class UpVersion {


    private DownloadBuilder builder;
    public static boolean isDownloading = false;

    public void upVersion(Context context) {
        if (isDownloading) {
            return;
        }
        AllenVersionChecker
                .getInstance()
                .downloadOnly(
                        UpVersion.crateUIData()
                )
                .setForceRedownload(false)//本地有安装包缓存也不会重新下载
                .setShowDownloadingDialog(false)//显示下载进度
                .setShowNotification(true)//通知栏
                .setNotificationBuilder(createCustomNotification())//自定通知栏
                .setCustomVersionDialogListener(new CustomVersionDialogListener() {
                    @Override
                    public Dialog getCustomVersionDialog(Context context, UIData versionBundle) {
                        UpVersionDialog upVersionDialog = new UpVersionDialog(context);
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
                })
                .excuteMission(context);
    }

    /**
     * @return
     * @important 使用请求版本功能，可以在这里设置downloadUrl
     * 这里可以构造UI需要显示的数据
     * UIData 内部是一个Bundle
     */
    public static UIData crateUIData() {
        UIData uiData = UIData.create();
        uiData.setTitle("本部2.0");
        uiData.setDownloadUrl("http://test-1251233192.coscd.myqcloud.com/1_1.apk");
        uiData.setContent("修复部分bug");
        return uiData;
    }

    private NotificationBuilder createCustomNotification() {
        return NotificationBuilder.create()
                .setRingtone(true)
                .setIcon(R.mipmap.logo)
                .setTicker(R.string.app_name+"更新");
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
