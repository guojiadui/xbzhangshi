package com.xbzhangshi.mvp.update;

import android.app.Dialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.annotation.Nullable;
import android.widget.TextView;
import android.widget.Toast;

import com.allenliu.versionchecklib.v2.AllenVersionChecker;
import com.allenliu.versionchecklib.v2.builder.DownloadBuilder;
import com.allenliu.versionchecklib.v2.builder.NotificationBuilder;
import com.allenliu.versionchecklib.v2.builder.UIData;
import com.allenliu.versionchecklib.v2.callback.CustomVersionDialogListener;
import com.allenliu.versionchecklib.v2.callback.RequestVersionListener;
import com.blankj.utilcode.util.FileUtils;
import com.xbzhangshi.R;
import com.xbzhangshi.app.Url;
import com.xbzhangshi.view.dialog.UpVersionDialog;

public class UpVersion {


    private DownloadBuilder builder;

    public void upVersion(Context context) {
        builder = AllenVersionChecker
                .getInstance()
                .requestVersion()
                .setRequestUrl("https://www.baidu.com")
                .request(new RequestVersionListener() {
                    @Nullable
                    @Override
                    public UIData onRequestVersionSuccess(String result) {
                        // Toast.makeText(context, "request successful", Toast.LENGTH_SHORT).show();
                        int curVersion = getVersionCode(context);
                      //  builder.setNewestVersionCode(100);
                        //是最新的版本
                        if (true) {
                            //有本部更新
                            return crateUIData();
                        } else {
                            //FileUtils.deleteAllInDir(Url.verPath);
                            //没有本部更新
                            return null;
                        }

                    }

                    @Override
                    public void onRequestVersionFailure(String message) {

                    }
                });
        builder.setForceRedownload(false);//本地有安装包缓存也不会重新下载
        builder.setShowDownloadingDialog(false);//显示下载进度
        builder.setShowNotification(true);//通知栏
        builder.setNotificationBuilder(createCustomNotification());//自定通知栏
        // builder.setDownloadAPKPath(Url.verPath);//下载路径
        builder.setCustomVersionDialogListener(new CustomVersionDialogListener() {//自定义更新dialog
            @Override
            public Dialog getCustomVersionDialog(Context context, UIData versionBundle) {
                UpVersionDialog upVersionDialog = new UpVersionDialog(context);
                TextView mTitle = upVersionDialog.findViewById(R.id.title);
                TextView mContentTip = upVersionDialog.findViewById(R.id.content_tip);
                mTitle.setText(versionBundle.getTitle());
                mContentTip.setText(versionBundle.getContent());
                return upVersionDialog;
            }
        });
        builder.excuteMission(context);

    }

    /**
     * @return
     * @important 使用请求版本功能，可以在这里设置downloadUrl
     * 这里可以构造UI需要显示的数据
     * UIData 内部是一个Bundle
     */
    private UIData crateUIData() {
        UIData uiData = UIData.create();
        uiData.setTitle("本部2.0");
        uiData.setDownloadUrl("http://test-1251233192.coscd.myqcloud.com/1_1.apk");
        uiData.setContent("修复部分bug");
        return uiData;
    }

    private NotificationBuilder createCustomNotification() {
        return NotificationBuilder.create()
                .setRingtone(true)
                .setIcon(R.mipmap.ic_launcher)
                .setTicker("custom_ticker")
                .setContentTitle("custom title")
                .setContentText("ddddddddddddd");
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
