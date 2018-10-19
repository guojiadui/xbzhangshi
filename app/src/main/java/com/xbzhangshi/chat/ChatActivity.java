package com.xbzhangshi.chat;


import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.sj.emoji.EmojiBean;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.xbzhangshi.R;
import com.xbzhangshi.chat.adapter.ChatAdapter;
import com.xbzhangshi.chat.common.Constants;
import com.xbzhangshi.chat.common.SimpleCommonUtils;
import com.xbzhangshi.chat.common.adapter.AppsAdapter;
import com.xbzhangshi.chat.common.widget.SimpleAppsGridView;
import com.xbzhangshi.mvp.base.BaseActivity;
import com.xbzhangshi.util.CameraUtil;
import com.xbzhangshi.view.CustomToolbar;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.bingoogolapple.photopicker.activity.BGAPhotoPickerActivity;
import sj.keyboard.XhsEmoticonsKeyBoard;
import sj.keyboard.data.EmoticonEntity;
import sj.keyboard.interfaces.EmoticonClickListener;
import sj.keyboard.widget.EmoticonsEditText;
import sj.keyboard.widget.FuncLayout;

public class ChatActivity extends BaseActivity implements FuncLayout.OnFuncKeyBoardListener {

    public final  int  CAMERA_CODE = 50;
    public final  int  PICTURE_CODE = 100;

    @BindView(R.id.ek_bar)
    XhsEmoticonsKeyBoard ekBar;
    @BindView(R.id.lt_main_title_left)
    TextView ltMainTitleLeft;
    @BindView(R.id.lt_main_title)
    TextView ltMainTitle;
    @BindView(R.id.lt_main_title_right)
    TextView ltMainTitleRight;
    @BindView(R.id.customtoolbar)
    CustomToolbar customtoolbar;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;

   ChatAdapter chatAdapter;
    RxPermissions rxPermissions;
    String picpath;//相机图片路径
    @Override
    protected int getlayout() {
        return R.layout.chat_activity_layout;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
       ltMainTitleLeft.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               finish();
           }
       });

        LinearLayoutManager    layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);
        recyclerview.setLayoutManager(layoutManager);
        chatAdapter = new ChatAdapter(this);
        recyclerview.setAdapter(chatAdapter);

        initEmoticonsKeyBoardBar();
    }


    private void initEmoticonsKeyBoardBar() {
        SimpleCommonUtils.initEmoticonsEditText(ekBar.getEtChat());
        ekBar.setAdapter(SimpleCommonUtils.getCommonAdapter(this, emoticonClickListener));
        ekBar.addOnFuncKeyBoardListener(this);
        ekBar.addFuncView(new SimpleAppsGridView(this, new AppsAdapter.OnFuncItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                if (position == 0) {
                    //查看照片
                   choicePhotoWrapper();
                } else if (position == 1) {
                    //拍照
                    checkPermissinsTakePicture();

                }
            }
        }));

        ekBar.getEtChat().setOnSizeChangedListener(new EmoticonsEditText.OnSizeChangedListener() {
            @Override
            public void onSizeChanged(int w, int h, int oldw, int oldh) {
                scrollToBottom();
            }
        });
        //发送文本
        ekBar.getBtnSend().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OnSendBtnClick(ekBar.getEtChat().getText().toString());
                ekBar.getEtChat().setText("");
            }
        });
    }


    private void OnSendBtnClick(String msg) {
        if (!TextUtils.isEmpty(msg)) {

            scrollToBottom();
        }
    }

    private void OnSendImage(String image) {
        if (!TextUtils.isEmpty(image)) {
            OnSendBtnClick("[img]" + image);
        }
    }

    private void scrollToBottom() {

    }

    @Override
    public void OnFuncPop(int height) {
        scrollToBottom();
    }

    @Override
    public void OnFuncClose() {
    }

    @Override
    protected void onPause() {
        super.onPause();
        ekBar.reset();
    }


    /**
     * 照相
     */
    private void checkPermissinsTakePicture() {
        if (rxPermissions == null) {
            rxPermissions = new RxPermissions(this);
        }

        rxPermissions
                .requestEachCombined(Manifest.permission.CAMERA,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .subscribe(permission -> { // will emit 1 Permission object
                    if (permission.granted) {
                        picpath = CameraUtil.takePicture(this, CAMERA_CODE);
                    } else if (permission.shouldShowRequestPermissionRationale) {
                        final AlertDialog.Builder normalDialog = new AlertDialog.Builder(this);
                        normalDialog.setMessage("拍照需要获取内存卡读写权限，相机权限");
                        normalDialog.setPositiveButton("确定",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                        checkPermissinsTakePicture();
                                    }
                                });
                        normalDialog.setNegativeButton("取消",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                });
                        // 显示
                        normalDialog.show();
                    } else {
                        final AlertDialog.Builder normalDialog = new AlertDialog.Builder(this);
                        normalDialog.setMessage("拍照需要获取内存卡读写权限，相机权限");
                        normalDialog.setPositiveButton("确定",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                        Uri packageURI = Uri.parse("package:" + getPackageName());
                                        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, packageURI);
                                        startActivity(intent);
                                    }
                                });
                        normalDialog.setNegativeButton("取消",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                });
                        // 显示
                        normalDialog.show();
                    }
                });
    }

    /**
     * 选中图片
     */
    private void choicePhotoWrapper() {
        if (rxPermissions == null) {
            rxPermissions = new RxPermissions(this);
        }

        rxPermissions
                .requestEachCombined(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .subscribe(permission -> { // will emit 1 Permission object
                    if (permission.granted) {
                        Intent photoPickerIntent = new BGAPhotoPickerActivity.IntentBuilder(this)
                                .maxChooseCount(1) // 图片选择张数的最大值
                                .selectedPhotos(null) // 当前已选中的图片路径集合
                                .pauseOnScroll(false) // 滚动列表时是否暂停加载图片
                                .build();
                        startActivityForResult(photoPickerIntent, PICTURE_CODE);
                    } else if (permission.shouldShowRequestPermissionRationale) {
                        final AlertDialog.Builder normalDialog = new AlertDialog.Builder(this);
                        normalDialog.setMessage("获取相册需要获取内存卡读权限");
                        normalDialog.setPositiveButton("确定",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                        checkPermissinsTakePicture();
                                    }
                                });
                        normalDialog.setNegativeButton("取消",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                });
                        // 显示
                        normalDialog.show();
                    } else {
                        final AlertDialog.Builder normalDialog = new AlertDialog.Builder(this);
                        normalDialog.setMessage("拍照需要获取内存卡读写权限，相机权限");
                        normalDialog.setPositiveButton("确定",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                        Uri packageURI = Uri.parse("package:" + getPackageName());
                                        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, packageURI);
                                        startActivity(intent);
                                    }
                                });
                        normalDialog.setNegativeButton("取消",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                });
                        // 显示
                        normalDialog.show();
                    }
                });

    }

    EmoticonClickListener emoticonClickListener = new EmoticonClickListener() {
        @Override
        public void onEmoticonClick(Object o, int actionType, boolean isDelBtn) {

            if (isDelBtn) {
                SimpleCommonUtils.delClick(ekBar.getEtChat());
            } else {
                if (o == null) {
                    return;
                }
                if (actionType == Constants.EMOTICON_CLICK_BIGIMAGE) {
                    if (o instanceof EmoticonEntity) {
                        OnSendImage(((EmoticonEntity) o).getIconUri());
                    }
                } else {
                    String content = null;
                    if (o instanceof EmojiBean) {
                        content = ((EmojiBean) o).emoji;
                    } else if (o instanceof EmoticonEntity) {
                        content = ((EmoticonEntity) o).getContent();
                    }

                    if (TextUtils.isEmpty(content)) {
                        return;
                    }
                    int index = ekBar.getEtChat().getSelectionStart();
                    Editable editable = ekBar.getEtChat().getText();
                    editable.insert(index, content);
                }
            }
        }
    };


}
