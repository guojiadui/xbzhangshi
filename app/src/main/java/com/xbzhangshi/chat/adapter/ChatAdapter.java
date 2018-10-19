package com.xbzhangshi.chat.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.xbzhangshi.R;
import com.xbzhangshi.chat.ChatActivity;
import com.xbzhangshi.chat.holder.ImgLeftHolder;
import com.xbzhangshi.chat.holder.ImgRightHolder;
import com.xbzhangshi.chat.holder.NoteHolder;
import com.xbzhangshi.chat.holder.TextLeftHolder;
import com.xbzhangshi.chat.holder.TextRightHolder;

public class ChatAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final int LEFT_TEXT = 1;
    public static final int LEFT_IMG = 2;
    public static final int RIGHT_TEXT = 3;
    public static final int RIGHT_IMG = 4;
    public static final int NOTE = 0;


    private ChatActivity mContext;
    private LayoutInflater mInflater;

    public ChatAdapter(ChatActivity context) {
        mContext = context;
        this.mInflater = LayoutInflater.from(this.mContext);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        RecyclerView.ViewHolder viewHolder = null;
        switch (i) {
            case RIGHT_TEXT:
                viewHolder = new TextRightHolder(mInflater.inflate(R.layout.chat_right_text_adapter_item, viewGroup, false));
                break;
            case RIGHT_IMG:
                viewHolder = new ImgRightHolder(mInflater.inflate(R.layout.chat_right_img_adapter_item, viewGroup, false));
                break;
            case LEFT_TEXT:
                viewHolder = new TextLeftHolder(mInflater.inflate(R.layout.chat_left_text_adapter_item, viewGroup, false));
                break;
            case LEFT_IMG:
                viewHolder = new ImgLeftHolder(mInflater.inflate(R.layout.chat_right_img_adapter_item, viewGroup, false));
                break;
            case NOTE:
                viewHolder = new NoteHolder(mInflater.inflate(R.layout.chat_note_adapter_item, viewGroup, false));
                break;

        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        if (viewHolder instanceof TextRightHolder) {
            ((TextRightHolder) viewHolder).setHolder();
        } else if (viewHolder instanceof ImgRightHolder) {
            ((ImgRightHolder) viewHolder).setHolder();
        } else if (viewHolder instanceof TextLeftHolder) {
            ((TextLeftHolder) viewHolder).setHolder();
        } else if (viewHolder instanceof ImgLeftHolder) {
            ((ImgLeftHolder) viewHolder).setHolder();
        } else if (viewHolder instanceof NoteHolder) {

        }
    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
