package com.keshava.urlshortener.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.keshava.urlshortener.R;

import java.util.ArrayList;

public class ListItemView extends BaseAdapter {
    private final LayoutInflater layoutInflater;
    private final ArrayList<UrlModel> urlModels;

    public ListItemView(Context context, ArrayList<UrlModel> urls) {
        layoutInflater = LayoutInflater.from(context);
        urlModels = urls;
    }

    @Override
    public int getCount() {
        return urlModels.size();
    }

    @Override
    public Object getItem(int i) {
        return urlModels.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null) {
            viewHolder = new ViewHolder();
            view = layoutInflater.inflate(R.layout.list_item_view, null);
            viewHolder.nameTextView = view.findViewById(R.id.short_text);
            viewHolder.emailTextView = view.findViewById(R.id.expansion_text);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.nameTextView.setText(urlModels.get(i).getShortString());
        viewHolder.emailTextView.setText(urlModels.get(i).getExpansionString());
        return view;
    }

    private class ViewHolder {
        TextView nameTextView, emailTextView;
    }

}
