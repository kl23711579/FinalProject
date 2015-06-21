package com.example.oao.finalproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.example.oao.finalproject.HistoryView;

import java.util.List;

/**
 * Created by OAO on 2015/6/22.
 */
public class MyAdapter extends BaseAdapter{
    private LayoutInflater myInflater;
    private List<HistoryView> HistoryList;

    public MyAdapter(Context context, List<HistoryView> HistoryList){
        myInflater = LayoutInflater.from(context);
        this.HistoryList = HistoryList;
    }

    /*private view holder class*/
    private class ViewHolder{
        TextView HistoryItem;
        TextView HistoryPrice;
        public ViewHolder(TextView HistoryItem, TextView HistoryPrice){
            this.HistoryItem = HistoryItem;
            this.HistoryPrice = HistoryPrice;
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        ViewHolder holder = null;
        if(convertView == null){
            convertView = myInflater.inflate(R.layout.myhistorylayout, null);
            holder = new ViewHolder(
                    (TextView) convertView.findViewById(R.id.HistoryItem),
                    (TextView) convertView.findViewById(R.id.HistoryPrice)
            );
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }

        HistoryView HistoryList = (HistoryView) getItem(position);

        holder.HistoryItem.setText(HistoryList.getItem());
        holder.HistoryPrice.setText(Integer.toString(HistoryList.getPrice()));

        return convertView;
    }

    @Override
    public int getCount() {
        return HistoryList.size();
    }

    @Override
    public Object getItem(int arg0) {
        return HistoryList.get(arg0);
    }

    @Override
    public long getItemId(int position) {
        return HistoryList.indexOf(getItem(position));
    }
}
