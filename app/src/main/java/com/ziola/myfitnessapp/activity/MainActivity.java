package com.ziola.myfitnessapp.activity;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.view.ViewPager;
import android.widget.TextView;

import com.ziola.myfitnessapp.R;
import com.ziola.myfitnessapp.async.DownloadHandlerActivity;
import com.ziola.myfitnessapp.async.DownloadSchedule;
import com.ziola.myfitnessapp.fragment.ScheduleFragment;
import com.ziola.myfitnessapp.model.Schedule;


public class MainActivity extends FragmentActivity {

    SchedulePagerAdapter mSchedulePagerAdapter;
    ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSchedulePagerAdapter = new SchedulePagerAdapter(getFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.pager);

        LocalBroadcastManager manager = LocalBroadcastManager.getInstance();
        //manager.registerReceiver();
        if(isNetworkAvailable()){
            DownloadSchedule ds = new DownloadSchedule(getApplicationContext());
            ds.execute();
        }

    }

    private boolean isNetworkAvailable(){
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }

}
