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
        List list = (List) getGroup(groupPosition);
        return list != null ? list.size() : 0;
    }

    @Override
    public Object getGroup(int groupPosition) {
        if(schedule == null){
            return null;
        }
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
        List group = (List) getGroup(groupPosition);
        return group != null ? group.get(childPosition) : null;
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
        Class currentClass = (Class) getChild(groupPosition, childPosition);
        if(currentClass == null){
            return null;
        }
        if (convertView == null) {
            convertView = LayoutInflater.from(_context).inflate(R.layout.item_class, parent, false);
        }
        TextView tvClassName = (TextView) convertView.findViewById(R.id.tvClassName);
        TextView tvTrainerName = (TextView) convertView.findViewById(R.id.tvTrainerName);
        TextView tvTime = (TextView) convertView.findViewById(R.id.tvTime);
        tvClassName.setText(currentClass.getName());
        tvTrainerName.setText(currentClass.getTrainer());
        tvTime.setText(currentClass.getStartTime() + " - " + currentClass.getEndTime());
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

}
