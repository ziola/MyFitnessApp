package com.ziola.myfitnessapp.fragment;

/**
 * Created by mwypysiak on 2015-02-19.
 */

import android.app.Fragment;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ziola.myfitnessapp.R;
import com.ziola.myfitnessapp.model.Schedule;

public class ScheduleFragment extends Fragment {

    public static final String DAY_DELTA = "DAY_DELTA";
    private TextView mTvDisplay;
    private int dayDelta;

    public static ScheduleFragment newInstance(int dayDelta) {
        ScheduleFragment fragmentFirst = new ScheduleFragment();
        Bundle args = new Bundle();
        args.putInt(DAY_DELTA, dayDelta);
        fragmentFirst.setArguments(args);
        return fragmentFirst;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dayDelta = getArguments().getInt(DAY_DELTA, 0);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_schedule, container, false);
        mTvDisplay = (TextView) rootView.findViewById(R.id.displayData);
        mTvDisplay.setText(Integer.toString(dayDelta));
        return rootView;
    }

}
