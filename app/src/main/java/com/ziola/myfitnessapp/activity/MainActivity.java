package com.ziola.myfitnessapp.activity;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.widget.TextView;

import com.ziola.myfitnessapp.R;
import com.ziola.myfitnessapp.async.DownloadHandlerActivity;
import com.ziola.myfitnessapp.async.DownloadSchedule;
import com.ziola.myfitnessapp.fragment.ScheduleFragment;


public class MainActivity extends Activity implements DownloadHandlerActivity {

    private static final String TAG = MainActivity.class.toString();

    private ScheduleFragment schedule = new ScheduleFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction().add(R.id.container, schedule).commit();
        }

        if(isNetworkAvailable()){
            DownloadSchedule ds = new DownloadSchedule(this);
            ds.execute();
        }

    }

    private boolean isNetworkAvailable(){
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }

    @Override
    public void handleResult(Object data) {
        schedule.updateView((String)data);
    }
}
