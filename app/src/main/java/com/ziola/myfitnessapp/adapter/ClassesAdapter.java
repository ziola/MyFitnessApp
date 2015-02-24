package com.ziola.myfitnessapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.ziola.myfitnessapp.R;
import com.ziola.myfitnessapp.model.Class;
import com.ziola.myfitnessapp.model.DailySchedule;
import com.ziola.myfitnessapp.model.ROOM;

import java.util.List;

/**
 * Created by mwypysiak on 2015-02-23.
 */
public class ClassesAdapter extends BaseExpandableListAdapter {

    Context _context;
    DailySchedule schedule;

    public ClassesAdapter(Context _context, DailySchedule schedule) {
        this._context = _context;
        this.schedule = schedule;
    }

    public void setSchedule(DailySchedule schedule) {
        this.schedule = schedule;
    }

    @Override
    public int getGroupCount() {
        return 2;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return ((List) getGroup(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        switch (groupPosition) {
            case 0:
                return schedule.getStudioClasses();
            case 1:
                return schedule.getFloorClasses();
        }
        return null;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        switch (groupPosition) {
            case 0:
                return schedule.getStudioClasses().get(childPosition);
            case 1:
                return schedule.getFloorClasses().get(childPosition);
        }
        return null;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(_context).inflate(R.layout.item_class_header, parent, false);
        }
        switch (groupPosition) {
            case 0:
                ((TextView) convertView.findViewById(R.id.tvClassType)).setText(ROOM.STUDIO.getName());
                break;
            case 1:
                ((TextView) convertView.findViewById(R.id.tvClassType)).setText(ROOM.FLOOR.getName());
                break;
        }
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Class currentClass = (Class) getChild(groupPosition, childPosition);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(_context).inflate(R.layout.item_class, parent, false);
        }
        // Lookup view for data population
        TextView tvClassName = (TextView) convertView.findViewById(R.id.tvClassName);
        TextView tvTrainerName = (TextView) convertView.findViewById(R.id.tvTrainerName);
        TextView tvTime = (TextView) convertView.findViewById(R.id.tvTime);
        // Populate the data into the template view using the data object
        tvClassName.setText(currentClass.getName());
        tvTrainerName.setText(currentClass.getTrainer());
        tvTime.setText(currentClass.getStartTime() + " - " + currentClass.getEndTime());
        // Return the completed view to render on screen
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

}
