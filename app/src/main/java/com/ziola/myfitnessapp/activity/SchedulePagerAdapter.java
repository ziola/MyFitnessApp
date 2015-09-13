package com.ziola.myfitnessapp.activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v13.app.FragmentStatePagerAdapter;

import com.ziola.myfitnessapp.fragment.ScheduleFragment;
import com.ziola.myfitnessapp.util.DateHelper;

import java.util.Calendar;

import static com.ziola.myfitnessapp.util.DateHelper.dateFormat;
import static java.util.Calendar.DATE;

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
        cal.add(DATE, i);
        return ScheduleFragment.newInstance(dateFormat.format(cal.getTime()));
    }

    @Override
    public int getCount() {
        return 7;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        Calendar cal = Calendar.getInstance();
        cal.add(DATE, position);
        return dateFormat.format(cal.getTime());
    }
}