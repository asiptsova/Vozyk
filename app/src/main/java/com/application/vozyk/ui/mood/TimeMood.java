package com.application.vozyk.ui.mood;

import android.content.Context;
import android.content.SharedPreferences;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimeMood {
    public static String getting_the_time(String current_time) {
        String[] split_for_current_time = current_time.split("_");
        return "Date: ".concat(return_month(split_for_current_time[2])).concat(" ").concat(split_for_current_time[1]).concat(",").concat(" ").concat(return_year(split_for_current_time[3])).concat(return_time(split_for_current_time[0]));

    }

    public static String return_time(String time) {
        String[] min_and_hour = time.split(":");
        int hour = Integer.parseInt(min_and_hour[1]);
        int minutes = Integer.parseInt(min_and_hour[0]);
        String amPm;
        String nowHour;
        String nowMinute;
        if (hour >= 12) {
            if (hour != 12)
                nowHour = String.valueOf(hour - 12);
            else
                nowHour = "12";
            amPm = "PM";
        } else {
            if (hour != 0)
                nowHour = String.valueOf(hour);
            else
                nowHour = "12";
            amPm = "AM";
        }
        if (minutes >= 10)
            nowMinute = String.valueOf(minutes);
        else
            nowMinute = "0".concat(String.valueOf(minutes));
        return nowHour.concat(":").concat(nowMinute).concat(" ").concat(amPm);
    }

    public static String return_month(String month_string) {
        int month = Integer.parseInt(month_string);
        if (month == 0)
            return "January";
        else if (month == 1)
            return "February";
        else if (month == 2)
            return "March";
        else if (month == 3)
            return "April";
        else if (month == 4)
            return "May";
        else if (month == 5)
            return "June";
        else if (month == 6)
            return "July";
        else if (month == 7)
            return "August";
        else if (month == 8)
            return "September";
        else if (month == 9)
            return "October";
        else if (month == 10)
            return "November";
        else
            return "December";
    }

    private static String return_year(String year_string) {
        int yearInt = Integer.parseInt(year_string);
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        if (currentYear != yearInt)
            return String.valueOf(yearInt).concat(", ");
        else
            return "";
    }

    public static void save_the_milli(String time, Context context) {
        String[] splitTime = time.split("_");
        String[] splitToHourAndMinute = splitTime[0].split(":");
        String final_time = splitTime[3].concat("-").concat(return_month_number(splitTime[2])).concat("-").concat(return_day_number(splitTime[1])).concat(" ").concat(return_the_hour(splitToHourAndMinute[1])).concat(":").concat(return_minute_number(splitToHourAndMinute[0]));
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        try {
            Date date = formatter.parse(final_time);
            SharedPreferences shared = context.getSharedPreferences("temp_time_to_save", context.MODE_PRIVATE);
            SharedPreferences.Editor edit = shared.edit();
            edit.putLong("temp_time_to_save", date.getTime());
            edit.apply();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private static String return_the_hour(String hour) {
        int hourInt = Integer.parseInt(hour);
        if (hourInt >= 10)
            return String.valueOf(hourInt);
        else
            return "0".concat(String.valueOf(hourInt));
    }

    private static String return_month_number(String month) {
        int monthInt = Integer.parseInt(month);
        if ((monthInt + 1) >= 10)
            return String.valueOf(monthInt + 1);
        else
            return "0".concat(String.valueOf(monthInt + 1));
    }

    private static String return_day_number(String day) {
        int dayInt = Integer.parseInt(day);
        if (dayInt >= 10)
            return day;
        else
            return "0".concat(day);

    }

    private static String return_minute_number(String minute) {
        int minuteInt = Integer.parseInt(minute);
        if (minuteInt >= 10)
            return minute;
        else
            return "0".concat(minute);
    }

    public static long return_time_in_midnight(long millis) {
        Calendar calendar_old = Calendar.getInstance();
        calendar_old.setTimeInMillis(millis);
        int day = calendar_old.get(Calendar.DAY_OF_MONTH);
        int month = calendar_old.get(Calendar.MONTH);
        int year = calendar_old.get(Calendar.YEAR);
        Calendar calendar_new = Calendar.getInstance();
        calendar_new.set(Calendar.YEAR, year);
        calendar_new.set(Calendar.MONTH, month);
        calendar_new.set(Calendar.DAY_OF_MONTH, day);
        calendar_new.set(Calendar.HOUR_OF_DAY, 0);
        calendar_new.set(Calendar.MINUTE, 0);
        calendar_new.set(Calendar.SECOND, 0);
        calendar_new.set(Calendar.MILLISECOND, 0);
        return calendar_new.getTimeInMillis();
    }
}
