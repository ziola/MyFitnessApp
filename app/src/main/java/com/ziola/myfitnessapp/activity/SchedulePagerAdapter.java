package com.ziola.myfitnessapp.activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v13.app.FragmentStatePagerAdapter;

import com.ziola.myfitnessapp.fragment.ScheduleFragment;
import com.ziola.myfitnessapp.util.DateHelper;

import java.util.Calendar;

/**
 * Created by mwypysiak on 2015-02-20.
 */
public class SchedulePagerAdapter extends FragmentStatePagerAdapter {

    public SchedulePagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, i);
        return ScheduleFragment.newInstance(DateHelper.sdf.format(cal.getTime()));
    }

    @Override
    public int getCount() {
        return 7;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, position);
        return DateHelper.sdf.format(cal.getTime());
    }
}