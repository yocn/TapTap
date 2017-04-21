package com.tele.forum.taptap.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tele.forum.taptap.R;
import com.tele.forum.taptap.presenter.util.Loger;
import com.tele.forum.taptap.view.service.FloatService;

/**
 * 首页设置listview的adapter
 * Yocn on 17.1.7.
 */

public class FindAdapter extends BaseAdapter {
    LayoutInflater mInflater;
    private String[] settings = {};
    Context mContext;

    public FindAdapter(String[] settings, Context context) {
        this.settings = settings;
        mContext = context;
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
    public View getView(final int position, View convertView, ViewGroup arg2) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_setting, null);
            holder = new ViewHolder();
            holder.tv_setting = (TextView) convertView.findViewById(R.id.tv_setting);
            holder.ll_setting = (LinearLayout) convertView.findViewById(R.id.ll_setting);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tv_setting.setText(settings[position]);
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Loger.d("position");
                if (position == 0) {
                    Intent floatService = new Intent(mContext, FloatService.class);
                    mContext.startService(floatService);
                }
            }
        });
        return convertView;
    }

    public class ViewHolder {
        LinearLayout ll_setting;
        public TextView tv_setting;
    }
}
