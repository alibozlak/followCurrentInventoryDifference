package dev.bozlak.followcurrentinventorydifference.views;

import android.widget.CalendarView;

import java.util.Calendar;

public class Utils {
    public static CalendarView.OnDateChangeListener onDateChangeListener = (view, year, month, dayOfMonth) -> {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, dayOfMonth);
        view.setDate(calendar.getTimeInMillis());
    };
}
