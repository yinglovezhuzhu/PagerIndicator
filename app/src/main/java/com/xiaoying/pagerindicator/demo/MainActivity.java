package com.xiaoying.pagerindicator.demo;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.xiaoying.pagerindicator.PagerIndicator;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ViewPager vp = findViewById(R.id.vp_main);
        final PagerIndicator indicator = findViewById(R.id.vpi_main);
        indicator.setViewPager(vp);

        final MainAdapter adapter = new MainAdapter(getSupportFragmentManager(), indicator);
        vp.setAdapter(adapter);

        indicator.update();

    }

    private class MainAdapter extends FragmentStatePagerAdapter {

        PagerIndicator mIndicator;

        public MainAdapter(FragmentManager fm, PagerIndicator indicator) {
            super(fm);
            this.mIndicator = indicator;
        }

        @Override
        public void notifyDataSetChanged() {
            super.notifyDataSetChanged();
            if(null != mIndicator) {
                mIndicator.update();
            }
        }

        @Override
        public Fragment getItem(int i) {
            Bundle args = new Bundle();
            if(i % 2 == 0) {
                args.putInt("bg_color", Color.CYAN);
            } else {
                args.putInt("bg_color", Color.GREEN);
            }
            return ImageFragment.newInstance(args);
        }

        @Override
        public int getCount() {
            return 10;
        }
    }

}
