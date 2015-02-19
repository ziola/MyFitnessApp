package com.ziola.myfitnessapp.async;

import android.os.AsyncTask;
import android.util.Log;

import com.ziola.myfitnessapp.model.Class;
import com.ziola.myfitnessapp.model.DailySchedule;
import static com.ziola.myfitnessapp.model.ROOM.FLOOR;
import static com.ziola.myfitnessapp.model.ROOM.STUDIO;
import com.ziola.myfitnessapp.model.Schedule;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by mwypysiak on 2015-02-19.
 */
public class DownloadSchedule extends AsyncTask<Object, String, Schedule> {
    private static final String TAG = DownloadSchedule.class.toString();

    private static final String SCHEDULE_URL = "http://timetable.x.creadhoc.com/pl/timetable?";
    private static final String SCHEDULE_START = "start=";
    private static final String SCHEDULE_END = "&end=";
    private static final String GYM_DATA ="&clubId=1&studioId=0&languageSymbol=PL";

    private final DownloadHandlerActivity caller;

    public DownloadSchedule(DownloadHandlerActivity caller) {
        this.caller = caller;
    }

    @Override
    protected Schedule doInBackground(Object[] params) {
        Schedule schedule;
        Log.i(TAG, "Getting schedule data");
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Calendar calendar = Calendar.getInstance();
            String url = SCHEDULE_URL + SCHEDULE_START + sdf.format(calendar.getTime()) + SCHEDULE_END;
            calendar.add(Calendar.DATE, 7);
            url += sdf.format(calendar.getTime()) + GYM_DATA;
            Document document = Jsoup.connect(url).get();
            Elements children = document.getElementById("mp-timetable-screen-container").children();
            for (Element elem : children){
                String className = elem.className();
                if(className.contains("mp-timetable-list-cal")){
                    initDailySchedule(elem);
                    continue;
                }
                if(className.contains("timetable-list-tab")){
                    parseDaliySchedule(elem);
                    continue;
                }
            }


            Log.i(TAG, "Schedule data downloaded");
        } catch (Exception e) {
            Log.e(TAG, "Error getting schedule data", e);
            schedule = mockSchedule();
        }
        return null;
    }

    private void parseDaliySchedule(Element elem) {

    }

    private void initDailySchedule(Element elem) {
    }

    private Schedule mockSchedule() {
        Log.i(TAG, "Creating mock schedule datas");
        Schedule schedule;Map<String, DailySchedule> classes = new HashMap<>();
        DailySchedule todayClasses = new DailySchedule();
        todayClasses.addClass(new Class("ABS", "Trainer 1", "09:00", "10:00", STUDIO));
        todayClasses.addClass(new Class("ABS", "Trainer 1", "16:00", "17:00", STUDIO));
        todayClasses.addClass(new Class("ABS", "Trainer 1", "16:00", "17:00", FLOOR));
        todayClasses.addClass(new Class("ABS", "Trainer 1", "17:00", "18:00", STUDIO));
        todayClasses.addClass(new Class("ABS", "Trainer 1", "17:00", "17:30", FLOOR));
        classes.put("02-19", todayClasses);
        schedule = new Schedule();
        schedule.setDailyClasses(classes);
        return schedule;
    }

    @Override
    protected void onPostExecute(Schedule schedule) {
        caller.handleResult(schedule);
    }

    private Schedule parseSchedule(String content) {
        Log.i(TAG, "parsing schedule data");
        return null;
    }

    private String filterContent(String content) {
        Log.i(TAG, "Filtering schedule data");
        return content;
    }

    private String readStream(InputStream in) {
        Log.i(TAG, "Reading schedule data");
        StringBuilder sb = new StringBuilder();
        //BufferedReader reader = null;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(in))){
            //reader = new BufferedReader(new InputStreamReader(in));
            String line = "";
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            Log.e(TAG, "Error getting schedule data", e);
//        } finally {
//            if (reader != null) {
//                try {
//                    reader.close();
//                } catch (IOException e) {
//                    Log.e(TAG, "Error closing  getting schedule data", e);
//                }
//            }
        }
        return sb.toString();
    }
}
