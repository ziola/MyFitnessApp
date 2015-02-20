package com.ziola.myfitnessapp.activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v13.app.FragmentStatePagerAdapter;

import com.ziola.myfitnessapp.fragment.ScheduleFragment;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by mwypysiak on 2015-02-20.
 */
public class SchedulePagerAdapter extends FragmentStatePagerAdapter {

    private SimpleDateFormat sdf = new SimpleDateFormat("dd.MM");

    public SchedulePagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        return ScheduleFragment.newInstance(i);
    }

    @Override
    public int getCount() {
        return 7;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        Calendar cal = Calendar.getInstance();
        DateFormat df = DateFormat.getDateInstance();
        cal.add(Calendar.DAY_OF_MONTH, position);
        return sdf.format(cal.getTime());
    }
}