package com.xiaoying.pagerindicator.demo;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Test Fragment
 * <br/>Author：yunying.zhang
 * <br/>Email: yinglovezhuzhu@gmail.com
 * <br/>Date: 2018/11/24
 */
public class ImageFragment extends Fragment {

    public static ImageFragment newInstance(Bundle args) {
        ImageFragment fragment = new ImageFragment();
        if(null != args) {
            fragment.setArguments(args);
        }
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ImageView iv = new ImageView(getActivity());
        iv.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        Bundle args = getArguments();
        if(null != args && args.containsKey("bg_color")) {
            iv.setBackgroundColor(args.getInt("bg_color"));
        }
        iv.setImageResource(R.mipmap.ic_launcher);
        return iv;
    }
}
