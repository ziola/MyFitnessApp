package com.ziola.myfitnessapp.fragment;

/**
 * Created by mwypysiak on 2015-02-19.
 */

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ziola.myfitnessapp.R;

public class ScheduleFragment extends Fragment {

    public ScheduleFragment() {
    }

    private TextView mTvDisplay;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_harmonogram, container, false);
        mTvDisplay = (TextView) rootView.findViewById(R.id.displayData);
        return rootView;
    }

    public void updateView(String data) {
        mTvDisplay.setText(data);
    }
}
