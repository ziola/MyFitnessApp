package com.ziola.myfitnessapp.fragment;

/**
 * Created by mwypysiak on 2015-02-19.
 */

import android.app.ActionBar;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.ziola.myfitnessapp.R;
import com.ziola.myfitnessapp.adapter.ClassesAdapter;
import com.ziola.myfitnessapp.model.DailySchedule;
import com.ziola.myfitnessapp.model.SharedData;

public class ScheduleFragment extends Fragment {

    public static final String DAY = "DAY";
    private String mDay;
    private DailySchedule mDailySchedule;
    private ClassesAdapter mStudioAdapter;
    private ExpandableListView mLvClasses;


    public static ScheduleFragment newInstance(String date) {
        ScheduleFragment fragmentFirst = new ScheduleFragment();
        Bundle args = new Bundle();
        args.putString(DAY, date);
        fragmentFirst.setArguments(args);
        return fragmentFirst;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDay = getArguments().getString(DAY);
        mDailySchedule = SharedData.schedule.get(mDay);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_schedule, null);
        mLvClasses = (ExpandableListView) rootView.findViewById(R.id.lvClasses);
        mStudioAdapter = new ClassesAdapter(this.getActivity(), mDailySchedule);
        mLvClasses.setAdapter(mStudioAdapter);
        mLvClasses.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                return true;
            }
        });
        for (int i = 0; i < mStudioAdapter.getGroupCount(); i++) {
            mLvClasses.expandGroup(i);
        }
        return rootView;
    }

}
