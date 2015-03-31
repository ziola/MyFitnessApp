package com.ziola.myfitnessapp.activity;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.Window;

import com.ziola.myfitnessapp.R;


public class MainActivity extends FragmentActivity {

    SchedulePagerAdapter mSchedulePagerAdapter;
    ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_ACTION_BAR_OVERLAY);
        getActionBar().setHideOnContentScrollEnabled(true);
        setContentView(R.layout.activity_main);
        mSchedulePagerAdapter = new SchedulePagerAdapter(getFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mSchedulePagerAdapter);

    }

}
