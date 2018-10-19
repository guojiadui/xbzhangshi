package com.xbzhangshi.util;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.content.FileProvider;

import java.io.File;

public class CameraUtil {

    /**
     * 拍照
     */
    public static String takePicture(Activity context, int resultCode) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        //图片路径
        File takePhotoDir = FileUtils.createPictureFile(context);
        File file = new File(takePhotoDir, System.currentTimeMillis() + ".png");
//        String picPath = takePhotoDir.getAbsolutePath() + "/" + System.currentTimeMillis() + ".png";
        Uri mOriginUri;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {//7.0以后的不同
            // FileProvider fileProvider = new FileProvider();
            mOriginUri = FileProvider.getUriForFile(context.getApplicationContext(), context.getApplicationContext().getPackageName() + ".fileProvider",
                    file);
        } else {
            mOriginUri = Uri.fromFile(file);
        }
        intent.putExtra(MediaStore.EXTRA_OUTPUT, mOriginUri);
        context.startActivityForResult(intent, resultCode);
        return file.getAbsolutePath();
    }

    /**
     * 拍照
     */
    public static String takePicture(Activity context, Fragment fragment, int resultCode) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        //图片路径
        File takePhotoDir = FileUtils.createPictureFile(context);
        File file = new File(takePhotoDir, System.currentTimeMillis() + ".png");
//        String picPath = takePhotoDir.getAbsolutePath() + "/" + System.currentTimeMillis() + ".png";
        Uri mOriginUri;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {//7.0以后的不同
            // FileProvider fileProvider = new FileProvider();
            mOriginUri = FileProvider.getUriForFile(context.getApplicationContext(), context.getApplicationContext().getPackageName() + ".fileProvider",
                    file);
        } else {
            mOriginUri = Uri.fromFile(file);
        }
        intent.putExtra(MediaStore.EXTRA_OUTPUT, mOriginUri);
        fragment.startActivityForResult(intent, resultCode);
        return file.getAbsolutePath();
    }
}
