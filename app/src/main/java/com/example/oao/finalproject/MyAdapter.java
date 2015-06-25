package com.example.oao.finalproject;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import com.example.oao.finalproject.HistoryView;

import java.util.List;

/**
 * Created by OAO on 2015/6/22.
 */
public class MyAdapter extends BaseAdapter{
    private LayoutInflater myInflater;
    private List<HistoryView> HistoryList;
    private History history;

    public MyAdapter(History context, List<HistoryView> HistoryList){
        this.history = context;
        myInflater = LayoutInflater.from(context);
        this.HistoryList = HistoryList;
    }

    /*private view holder class*/
    private class ViewHolder{
        TextView HistoryItem;
        TextView HistoryPrice;
        Button btnDel;
        public ViewHolder(TextView HistoryItem, TextView HistoryPrice, Button btnDel){
            this.HistoryItem = HistoryItem;
            this.HistoryPrice = HistoryPrice;
            this.btnDel = btnDel;
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        ViewHolder holder = null;
        if(convertView == null){
            // 取得listItem容器 view
            convertView = myInflater.inflate(R.layout.myhistorylayout, null);

            // 建構listItem內容view
            holder = new ViewHolder(
                    (TextView) convertView.findViewById(R.id.HistoryItem),
                    (TextView) convertView.findViewById(R.id.HistoryPrice),
                    (Button) convertView.findViewById(R.id.HistoryDel)
            );

            // 設置容器內容
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }

        HistoryView HistoryList = (HistoryView) getItem(position);

        holder.HistoryItem.setText(HistoryList.getItem());
        holder.HistoryPrice.setText(Integer.toString(HistoryList.getPrice()));
        //設定按鈕監聽事件及傳入 MainActivity 實體
        holder.btnDel.setOnClickListener(new ItemButton_Click(this.history, position));

        return convertView;
    }

    class ItemButton_Click implements OnClickListener{
        private int position;
        private History history;

        ItemButton_Click(History context, int pos) {
            this.history = context;
            position = pos;
        }

        @Override
        public void onClick(View v){
            this.history.myDialog(HistoryList.get(position).get_ID());
        }
    }

    @Override
    public int getCount() {
        return HistoryList.size();
    }

    @Override
    public Object getItem(int position) {
        return HistoryList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return HistoryList.indexOf(getItem(position));
    }
}
