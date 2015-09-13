package com.ziola.myfitnessapp.async;

import android.os.AsyncTask;
import android.util.Log;

import com.ziola.myfitnessapp.activity.CallerActivity;
import com.ziola.myfitnessapp.model.Class;
import com.ziola.myfitnessapp.model.DailySchedule;
import com.ziola.myfitnessapp.model.ROOM;
import com.ziola.myfitnessapp.model.SharedData;
import com.ziola.myfitnessapp.util.DateHelper;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import static com.ziola.myfitnessapp.util.DateHelper.dateFormat;
import static java.util.Calendar.DATE;

/**
 * Created by mwypysiak on 2015-02-19.
 */
public class DownloadSchedule extends AsyncTask<Object, String, Map<String, DailySchedule>> {
    private static final String TAG = DownloadSchedule.class.toString();

    private static final String SCHEDULE_URL = "http://timetable.x.creadhoc.com/pl/timetable?";
    private static final String SCHEDULE_START = "start=";
    private static final String SCHEDULE_END = "&end=";
    private static final String GYM_DATA = "&clubId=1&studioId=0&languageSymbol=PL";

    private final CallerActivity activity;

    public DownloadSchedule(CallerActivity activity) {
        this.activity = activity;
    }

    @Override
    protected Map<String, DailySchedule> doInBackground(Object[] params) {
        Map<String, DailySchedule> schedule = new HashMap<>();
        Log.i(TAG, "Getting schedule data");
        try {
            Calendar calendar = Calendar.getInstance();
            String startDate = dateFormat.format(calendar.getTime());
            calendar.add(DATE, 7);
            String endDate = dateFormat.format(calendar.getTime());
            Document document = Jsoup.connect(SCHEDULE_URL + SCHEDULE_START + startDate + SCHEDULE_END + endDate + GYM_DATA).get();
            Elements children = document.getElementById("mp-timetable-screen-container").children();
            DailySchedule dailySchedule = null;
            for (Element elem : children) {
                String className = elem.className();
                if (className.contains("mp-timetable-list-cal")) {
                    dailySchedule = initDailySchedule(elem);
                    continue;
                }
                if (className.contains("timetable-list-tab") && dailySchedule != null) {
                    dailySchedule = parseDailySchedule(dailySchedule, elem);
                    schedule.put(dateFormat.format(dailySchedule.getDate()), dailySchedule);
                    continue;
                }
            }
            Log.i(TAG, "Schedule data downloaded");
        } catch (Exception e) {
            Log.e(TAG, "Error getting schedule data", e);
        }
        return schedule;
    }

    private DailySchedule parseDailySchedule(DailySchedule dailySchedule, Element elem) {
        Elements rows = elem.getElementsByTag("tbody").get(0).getElementsByTag("tr");
        for (Element row : rows) {
            ROOM type = ROOM.get(row.child(3).text().trim());
            dailySchedule.addClass(new Class(
                    row.attr("data-classname").trim(),
                    row.attr("data-trainername").trim(),
                    row.child(0).text().trim(),
                    row.child(1).text().trim(),
                    type), type);
        }
        return dailySchedule;
    }

    private DailySchedule initDailySchedule(Element elem) {
        int day = Integer.parseInt(elem.child(1).text());
        Calendar calendar = Calendar.getInstance();
        int currentDay = calendar.get(DATE);
        int toAdd = day - currentDay;
        if (toAdd < 0) {
            toAdd = calendar.getActualMaximum(DATE) - currentDay + day;
        }
        calendar.add(DATE, toAdd);
        DailySchedule dailySchedule = new DailySchedule();
        dailySchedule.setDate(calendar.getTime());
        return dailySchedule;
    }

    @Override
    protected void onPostExecute(Map<String, DailySchedule> schedule) {
        SharedData.schedule.clear();
        SharedData.schedule.putAll(schedule);
        activity.postExecuteCallback();
    }
}
