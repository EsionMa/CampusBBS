package com.yan.campusbbs.module.selfcenter.adapterholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.yan.campusbbs.R;

/**
 * Created by yan on 2017/2/7.
 */

public class SelfCenterViewHolderString extends RecyclerView.ViewHolder {
    public TextView textView;

    public SelfCenterViewHolderString(View itemView) {
        super(itemView);
        textView = (TextView) itemView.findViewById(R.id.tv_string);
    }
}