package com.xiaoying.pagerindicator;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

/**
 * Page indicator，only used on ViewPager
 * <br/>Author：yunying.zhang
 * <br/>Email: yinglovezhuzhu@gmail.com
 * <br/>Date: 2018/11/24
 */
public class PagerIndicator extends RadioGroup {

    private ViewPager mViewPager;
    private ViewPager.OnPageChangeListener mPageChangerListener;

    private int mIndicatorDrawableId;

    public PagerIndicator(Context context) {
        super(context);
        init(context, null);
    }

    public PagerIndicator(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    @Override
    public void onViewAdded(View child) {
        super.onViewAdded(child);

        if(getChildCount() > 0 && null == mPageChangerListener && null != mViewPager) {
            // Had child view, set an OnPageChangerListener
            mPageChangerListener = new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                }

                @Override
                public void onPageSelected(int position) {
                    // Set the current page indicator check state
                    setChecked(position);
                }

                @Override
                public void onPageScrollStateChanged(int state) {
                }
            };
            mViewPager.addOnPageChangeListener(mPageChangerListener);
        }
    }

    @Override
    public void onViewRemoved(View child) {
        super.onViewRemoved(child);

        if(getChildCount() < 1 && null != mPageChangerListener && null != mViewPager) {
            // No child view， remove OnPageChangerListener
            mViewPager.removeOnPageChangeListener(mPageChangerListener);
        }
    }

    /**
     * Set the ViewPager witch need indicator.
     * @param viewPager ViewPager
     */
    public void setViewPager(ViewPager viewPager) {
        this.mViewPager = viewPager;
    }

    /**
     * Update indicator, you should call when ViewPager data is changed.
     */
    public void update() {
        if(null == mViewPager) {
            return;
        }
        final PagerAdapter adapter = mViewPager.getAdapter();
        if(null == adapter) {
            // Not set pager adapter
            return;
        }
        final int count = adapter.getCount();
        final int childCount = getChildCount();
        if(count < childCount) {
            for(int i = 1; i <= childCount - count; i++) {
                removeItem(childCount - i);
            }
        } else if (count > childCount) {
            for(int i = 0; i < count - childCount; i++) {
                addItem();
            }
        }
        // Set the current checked position
        setChecked(mViewPager.getCurrentItem());
    }

    /**
     * init
     * @param context
     * @param attr
     */
    private void init(Context context, AttributeSet attr) {
        if(null == attr) {
            // To set default values
            // Default orientation is horizontal
            setOrientation(HORIZONTAL);
        } else {
            TypedArray a = context.obtainStyledAttributes(attr, R.styleable.PagerIndicator);
            if(null != a) {
                // Default orientation is horizontal
                setOrientation(a.getInt(R.styleable.PagerIndicator_android_orientation, HORIZONTAL));
                mIndicatorDrawableId = a.getResourceId(R.styleable.PagerIndicator_indicatorDrawable,
                        R.drawable.view_pager_default_indicator_selector);

                a.recycle();
            }
        }

    }

    /**
     * Add an indicator item
     */
    private void addItem() {
        final RadioButton rb = new RadioButton(getContext());
        rb.setButtonDrawable(mIndicatorDrawableId);
        // RadioGroup extends LinearLayout
        LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        lp.leftMargin = 5;
        lp.rightMargin = 5;
        lp.topMargin = 5;
        lp.bottomMargin = 5;
        addView(rb, lp);

    }

    /**
     * Remove the indicator item
     * @param index index
     */
    private void removeItem(int index) {
        removeViewAt(index);
    }

    /**
     * Set the check item
     * @param index checked item index.
     */
    private void setChecked(int index) {
        if(index < getChildCount()) {
            final RadioButton rb = (RadioButton) PagerIndicator.this.getChildAt(index);
            rb.setChecked(true);
        }
    }
}
