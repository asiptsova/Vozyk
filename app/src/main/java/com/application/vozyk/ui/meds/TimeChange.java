package com.application.vozyk.ui.meds;

import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class TimeChange extends AppCompatActivity {


    public static String timeTextView(int hour, int minute) {
        String result;
        if (hour < 10) {
            result = "0" + hour;
        } else {
            result = Integer.toString(hour);
        }
        result += ":";
        if (minute < 10) {
            result += "0" + minute;
        } else {
            result += Integer.toString(minute);
        }

        return result;
    }

    public static String timeToString(int hour, int minute) {
        String result;
        if (hour < 10) {
            result = "0" + hour;
        } else {
            result = Integer.toString(hour);
        }
        if (minute < 10) {
            result += "0" + minute;
        } else {
            result += Integer.toString(minute);
        }

        return result;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    public void getTime() {
        SharedPreferences sharedPref = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        Time.MORNING = sharedPref.getString("MORNING", "");
        Time.AFTERNOON = sharedPref.getString("AFTERNOON", "");
        Time.NIGHT = sharedPref.getString("NIGHT", "");
    }

    public void setTime(String time, int flag) {
        SharedPreferences sharedPref = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();

        if (flag == 0) {
            editor.putString("MORNING", time);
        } else if (flag == 1) {
            editor.putString("AFTERNOON", time);
        } else if (flag == 2) {
            editor.putString("NIGHT", time);
        }
        editor.apply();
        getTime();
    }

}
