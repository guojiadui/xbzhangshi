package com.xbzhangshi.chat;

public class ChatActivity   {

   /* @BindView(R.id.lt_main_title_left)
    TextView ltMainTitleLeft;
    @BindView(R.id.lt_main_title)
    TextView ltMainTitle;
    @BindView(R.id.lt_main_title_right)
    TextView ltMainTitleRight;
    @BindView(R.id.customtoolbar)
    CustomToolbar customtoolbar;
    @BindView(R.id.lv_chat)
    RecyclerView lvChat;
    @BindView(R.id.ek_bar)
    XhsEmoticonsKeyBoard ekBar;

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
    }

    private void initEmoticonsKeyBoardBar() {
        SimpleCommonUtils.initEmoticonsEditText(ekBar.getEtChat());
        //ekBar.setAdapter(SimpleCommonUtils.getCommonAdapter(this, emoticonClickListener));
      //  ekBar.addOnFuncKeyBoardListener(this);
        ekBar.addFuncView(new SimpleAppsGridView(this));

        ekBar.getEtChat().setOnSizeChangedListener(new EmoticonsEditText.OnSizeChangedListener() {
            @Override
            public void onSizeChanged(int w, int h, int oldw, int oldh) {
             //   scrollToBottom();
            }
        });
        //发送文本
        ekBar.getBtnSend().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  OnSendBtnClick(ekBar.getEtChat().getText().toString());
                ekBar.getEtChat().setText("");
            }
        });

    }*/
}
