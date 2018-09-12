package com.xbzhangshi.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.xbzhangshi.R;
import com.xbzhangshi.mvp.usercenter.adapter.RecordSelectAdapter;
import com.xbzhangshi.mvp.usercenter.bean.BettingItemBean;

import java.util.ArrayList;
import java.util.List;

public class SelectListDialog extends Dialog {
    Context context;
    RecyclerView recyclerView;
    List<String> list;
    SelectListener selectListener;
    RecordSelectAdapter recordSelectAdapter;
   String curString;
    public SelectListDialog(@NonNull Context context,String curString, List<String> list, SelectListener selectListener) {
        super(context, R.style.MyDialog);
        this.context = context;
        this.list = list;
        this.selectListener = selectListener;
        this.curString = curString;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        init();
    }

    public void init() {
        setCanceledOnTouchOutside(false);
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.select_list_dialog_layout, null);
        recyclerView = view.findViewById(R.id.recyclerView);
        TextView cancel = view.findViewById(R.id.cancel_action);
        TextView yes = view.findViewById(R.id.yes_action);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
              if(selectListener!=null&&list!=null){
                  selectListener.onSelectListener(list.get(recordSelectAdapter.curSelect));
              }
            }
        });

        recordSelectAdapter = new RecordSelectAdapter(list,curString);
        recordSelectAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                RecordSelectAdapter recordSelectAdapter1 = (RecordSelectAdapter) adapter;
                recordSelectAdapter1.curSelect = position;
                recordSelectAdapter1.notifyDataSetChanged();
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(recordSelectAdapter);
        setContentView(view);

    }

    public interface SelectListener {
        public void onSelectListener(String string);
    }

}
