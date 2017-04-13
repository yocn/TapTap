package com.tele.forum.taptap.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.tele.forum.taptap.R;

import java.util.Random;

/**
 * 首页设置listview的adapter
 * Yocn on 17.1.7.
 */

public class HomeSettingAdapter extends BaseAdapter {
    LayoutInflater mInflater;
    private String[] settings = {};

    public HomeSettingAdapter(String[] settings, Context context) {
        this.settings = settings;
        mInflater = LayoutInflater.from(context);
    }

    public void setSettings(String[] settings) {
        this.settings = settings;
    }

    @Override
    public int getCount() {
        return settings.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup arg2) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_setting, null);
            holder = new ViewHolder();
            holder.tv_setting = (TextView) convertView.findViewById(R.id.tv_setting);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tv_setting.setText(settings[position]);
        return convertView;
    }

    public class ViewHolder {
        public TextView tv_setting;
    }
}
