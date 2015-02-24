package com.ziola.myfitnessapp.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

import com.ziola.myfitnessapp.R;


public class MainActivity extends FragmentActivity {

    SchedulePagerAdapter mSchedulePagerAdapter;
    ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSchedulePagerAdapter = new SchedulePagerAdapter(getFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mSchedulePagerAdapter);
    }

}
