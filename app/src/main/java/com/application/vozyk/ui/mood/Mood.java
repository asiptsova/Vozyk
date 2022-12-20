package com.application.vozyk.ui.mood;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.fragment.app.Fragment;
import com.application.vozyk.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import org.jetbrains.annotations.NotNull;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Mood extends Fragment  {
    private Button calender_button_showing_shadow_1,
            calender_button_showing_shadow_2,
            calender_button_showing_shadow_3,
            calender_button_showing_shadow_4,
            calender_button_showing_shadow_5,
            calender_button_showing_shadow_6,
            calender_button_showing_shadow_7,
            calender_button_showing_shadow_8,
            calender_button_showing_shadow_9,
            calender_button_showing_shadow_10,
            calender_button_showing_shadow_11,
            calender_button_showing_shadow_12,
            calender_button_showing_shadow_13,
            calender_button_showing_shadow_14,
            calender_button_showing_shadow_15,
            calender_button_showing_shadow_16,
            calender_button_showing_shadow_17,
            calender_button_showing_shadow_18,
            calender_button_showing_shadow_19,
            calender_button_showing_shadow_20,
            calender_button_showing_shadow_21,
            calender_button_showing_shadow_22,
            calender_button_showing_shadow_23,
            calender_button_showing_shadow_24,
            calender_button_showing_shadow_25,
            calender_button_showing_shadow_26,
            calender_button_showing_shadow_27,
            calender_button_showing_shadow_28,
            calender_button_showing_shadow_29,
            calender_button_showing_shadow_30,
            calender_button_showing_shadow_31,
            calender_button_showing_shadow_32,
            calender_button_showing_shadow_33,
            calender_button_showing_shadow_34,
            calender_button_showing_shadow_35,
            calender_button_showing_shadow_36,
            calender_button_showing_shadow_37;
    private String color_the_today;
    private String[] colors;
    private HashMap<Long, Integer> history_of_mood;
    private Long start_date;
    private int color;
    private final int[] modes_for_four_drawable = new int[37];
    private int month_for_mood_chart = -1;
    private int year_for_mood_chart = -1;
    private LineChart line_chart_for_streak;


    public Mood() {}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_mood, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        call_me_at_start();
    }

    private void call_me_at_start() {
        add_to_list();
        get_the_color();
        color_the_stuff();
        define_start_date();
        define_the_buttons();
        setTheFirstDayOfTheWeekNumber();
        setTheDaysOnTheRealText();
        back_and_front_button_listen();
        color_the_today_value();
        calender_button_listeners();
        remove_the_hiding_buttons();
        color_today();
        color_the_calender();
        yes_and_no_button_listen_under_the_calender();
        divide_it_into_weeks();
        hide_or_un_hide_the_button(0);
        color_the_button_under_the_calender();
        set_up_buttons_once();
        button_listen_at_the_top();
        makeEverythingAverageMood();
        drawTheMoodLineChart();
        backAndFrontButtonListenForTheGraphMood();
        setAllTheFacesInTheMood();
        drawTheBarForAverageMood();
        fadeTheViews();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            call_me_at_start();
        }
    }

    private void setTheFirstDayOfTheWeekNumber() {
        if (getView() != null) {
            Calendar calender = Calendar.getInstance();
            TextView first_day_in_the_week_calender = getView().findViewById(R.id.tv_1);
            TextView second_day_in_the_week_calender = getView().findViewById(R.id.tv_2);
            TextView third_day_in_the_week_calender = getView().findViewById(R.id.tv_3);
            TextView fourth_day_in_the_week_calender = getView().findViewById(R.id.tv_4);
            TextView fifth_day_in_the_week_calender = getView().findViewById(R.id.tv_5);
            TextView sixth_day_in_the_week_calender = getView().findViewById(R.id.tv_6);
            TextView seventh_day_in_the_week_calender = getView().findViewById(R.id.tv_7);
            int year = calender.get(Calendar.YEAR);
            String month = returnMonth(calender.get(Calendar.MONTH));
            TextView month_and_year_in_calender_for_good_habits = getView().findViewById(R.id.tv_month_year);
            month_and_year_in_calender_for_good_habits.setText(month.concat(" ").concat(String.valueOf(year)));
            if (calender.getFirstDayOfWeek() == Calendar.SUNDAY) {
                first_day_in_the_week_calender.setText(R.string.Sunday);
                second_day_in_the_week_calender.setText(R.string.Monday);
                third_day_in_the_week_calender.setText(R.string.Tuesday);
                fourth_day_in_the_week_calender.setText(R.string.Wednesday);
                fifth_day_in_the_week_calender.setText(R.string.Thursday);
                sixth_day_in_the_week_calender.setText(R.string.Friday);
                seventh_day_in_the_week_calender.setText(R.string.Saturday);
            } else if (calender.getFirstDayOfWeek() == Calendar.MONDAY) {
                first_day_in_the_week_calender.setText(R.string.Monday);
                second_day_in_the_week_calender.setText(R.string.Tuesday);
                third_day_in_the_week_calender.setText(R.string.Wednesday);
                fourth_day_in_the_week_calender.setText(R.string.Thursday);
                fifth_day_in_the_week_calender.setText(R.string.Friday);
                sixth_day_in_the_week_calender.setText(R.string.Saturday);
                seventh_day_in_the_week_calender.setText(R.string.Sunday);
            } else if (calender.getFirstDayOfWeek() == Calendar.TUESDAY) {
                first_day_in_the_week_calender.setText(R.string.Tuesday);
                second_day_in_the_week_calender.setText(R.string.Wednesday);
                third_day_in_the_week_calender.setText(R.string.Thursday);
                fourth_day_in_the_week_calender.setText(R.string.Friday);
                fifth_day_in_the_week_calender.setText(R.string.Saturday);
                sixth_day_in_the_week_calender.setText(R.string.Sunday);
                seventh_day_in_the_week_calender.setText(R.string.Monday);
            } else if (calender.getFirstDayOfWeek() == Calendar.WEDNESDAY) {
                first_day_in_the_week_calender.setText(R.string.Wednesday);
                second_day_in_the_week_calender.setText(R.string.Thursday);
                third_day_in_the_week_calender.setText(R.string.Friday);
                fourth_day_in_the_week_calender.setText(R.string.Saturday);
                fifth_day_in_the_week_calender.setText(R.string.Sunday);
                sixth_day_in_the_week_calender.setText(R.string.Monday);
                seventh_day_in_the_week_calender.setText(R.string.Tuesday);
            } else if (calender.getFirstDayOfWeek() == Calendar.THURSDAY) {
                first_day_in_the_week_calender.setText(R.string.Thursday);
                second_day_in_the_week_calender.setText(R.string.Friday);
                third_day_in_the_week_calender.setText(R.string.Saturday);
                fourth_day_in_the_week_calender.setText(R.string.Sunday);
                fifth_day_in_the_week_calender.setText(R.string.Monday);
                sixth_day_in_the_week_calender.setText(R.string.Tuesday);
                seventh_day_in_the_week_calender.setText(R.string.Wednesday);
            } else if (calender.getFirstDayOfWeek() == Calendar.FRIDAY) {
                first_day_in_the_week_calender.setText(R.string.Friday);
                second_day_in_the_week_calender.setText(R.string.Saturday);
                third_day_in_the_week_calender.setText(R.string.Sunday);
                fourth_day_in_the_week_calender.setText(R.string.Monday);
                fifth_day_in_the_week_calender.setText(R.string.Tuesday);
                sixth_day_in_the_week_calender.setText(R.string.Wednesday);
                seventh_day_in_the_week_calender.setText(R.string.Thursday);
            } else {
                first_day_in_the_week_calender.setText(R.string.Saturday);
                second_day_in_the_week_calender.setText(R.string.Sunday);
                third_day_in_the_week_calender.setText(R.string.Monday);
                fourth_day_in_the_week_calender.setText(R.string.Tuesday);
                fifth_day_in_the_week_calender.setText(R.string.Wednesday);
                sixth_day_in_the_week_calender.setText(R.string.Thursday);
                seventh_day_in_the_week_calender.setText(R.string.Friday);
            }
        }
    }

    private String returnMonth(int month) {
        if (month == 0) {
            return "January";
        } else if (month == 1) {
            return "February";
        } else if (month == 2) {
            return "March";
        } else if (month == 3) {
            return "April";
        } else if (month == 4) {
            return "May";
        } else if (month == 5) {
            return "June";
        } else if (month == 6) {
            return "July";
        } else if (month == 7) {
            return "August";
        } else if (month == 8) {
            return "September";
        } else if (month == 9) {
            return "October";
        } else if (month == 10) {
            return "November";
        } else {
            return "December";
        }
    }

    private int returnFirstDayOfMonth() {
        if (getView() != null) {
            TextView month_and_year_in_calender_for_good_habits = getView().findViewById(R.id.tv_month_year);
            String[] splitter = month_and_year_in_calender_for_good_habits.getText().toString().split(" ");
            Calendar calender_for_first_day_of_month_only = Calendar.getInstance();
            calender_for_first_day_of_month_only.set(Calendar.MONTH, returnMonthStringToInt(splitter[0]));
            calender_for_first_day_of_month_only.set(Calendar.YEAR, Integer.parseInt(splitter[1]));
            calender_for_first_day_of_month_only.set(Calendar.DAY_OF_MONTH, calender_for_first_day_of_month_only.getActualMinimum(Calendar.DAY_OF_MONTH));
            return calender_for_first_day_of_month_only.get(Calendar.DAY_OF_WEEK);
        } else {
            return 0;
        }
    }

    private int returnLastDayOfMonth() {
        if (getView() != null) {
            TextView month_and_year_in_calender_for_good_habits = getView().findViewById(R.id.tv_month_year);
            Calendar calender_for_last_day_of_month_only = Calendar.getInstance();
            String[] splitter = month_and_year_in_calender_for_good_habits.getText().toString().split(" ");
            calender_for_last_day_of_month_only.set(Calendar.MONTH, returnMonthStringToInt(splitter[0]));
            calender_for_last_day_of_month_only.set(Calendar.YEAR, Integer.parseInt(splitter[1]));
            calender_for_last_day_of_month_only.set(Calendar.DAY_OF_MONTH, 1);
            return calender_for_last_day_of_month_only.getActualMaximum(Calendar.DAY_OF_MONTH);
        } else {
            return 1;
        }
    }

    private int returnMonthStringToInt(String month) {
        switch (month) {
            case "January":
                return 0;
            case "February":
                return 1;
            case "March":
                return 2;
            case "April":
                return 3;
            case "May":
                return 4;
            case "June":
                return 5;
            case "July":
                return 6;
            case "August":
                return 7;
            case "September":
                return 8;
            case "October":
                return 9;
            case "November":
                return 10;
            default:
                return 11;
        }
    }
    private void setTheDaysOnTheRealText() {
        if (getView() != null) {
            TextView month_and_year_in_calender_for_good_habits = getView().findViewById(R.id.tv_month_year);
            String[] splitter = month_and_year_in_calender_for_good_habits.getText().toString().split(" ");
            Calendar calender_for_first_day_of_month_only = Calendar.getInstance();
            calender_for_first_day_of_month_only.set(Calendar.MONTH, returnMonthStringToInt(splitter[0]));
            calender_for_first_day_of_month_only.set(Calendar.YEAR, Integer.parseInt(splitter[1]));
            if (calender_for_first_day_of_month_only.getFirstDayOfWeek() == Calendar.SUNDAY) {
                dayIsSunday();
            } else if (calender_for_first_day_of_month_only.getFirstDayOfWeek() == Calendar.MONDAY) {
                day_is_monday();
            } else if (calender_for_first_day_of_month_only.getFirstDayOfWeek() == Calendar.TUESDAY) {
                day_is_tuesday();
            } else if (calender_for_first_day_of_month_only.getFirstDayOfWeek() == Calendar.WEDNESDAY) {
                day_is_wednesday();
            } else if (calender_for_first_day_of_month_only.getFirstDayOfWeek() == Calendar.THURSDAY) {
                day_is_thursday();
            } else if (calender_for_first_day_of_month_only.getFirstDayOfWeek() == Calendar.FRIDAY) {
                day_is_friday();
            } else {
                day_is_saturday();
            }
        }
    }
    private void dayIsSunday() {
        int day = 1;
        if (returnFirstDayOfMonth() == Calendar.SUNDAY) {
            calender_button_showing_shadow_1.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_2.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_3.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_4.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_5.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_6.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_7.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_8.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_9.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_10.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_11.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_12.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_13.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_14.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_15.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_16.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_17.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_18.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_19.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_20.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_21.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_22.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_23.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_24.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_25.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_26.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_27.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_28.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_29.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_30.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_31.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_32.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_33.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_34.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_35.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_36.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_37.setText(String.valueOf(day));
            }
        } else if (returnFirstDayOfMonth() == Calendar.MONDAY) {
            calender_button_showing_shadow_2.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_3.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_4.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_5.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_6.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_7.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_8.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_9.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_10.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_11.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_12.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_13.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_14.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_15.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_16.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_17.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_18.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_19.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_20.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_21.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_22.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_23.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_24.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_25.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_26.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_27.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_28.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_29.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_30.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_31.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_32.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_33.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_34.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_35.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_36.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_37.setText(String.valueOf(day));
            }
        } else if (returnFirstDayOfMonth() == Calendar.TUESDAY) {
            calender_button_showing_shadow_3.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_4.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_5.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_6.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_7.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_8.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_9.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_10.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_11.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_12.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_13.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_14.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_15.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_16.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_17.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_18.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_19.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_20.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_21.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_22.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_23.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_24.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_25.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_26.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_27.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_28.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_29.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_30.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_31.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_32.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_33.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_34.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_35.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_36.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_37.setText(String.valueOf(day));
            }
        } else if (returnFirstDayOfMonth() == Calendar.WEDNESDAY) {
            calender_button_showing_shadow_4.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_5.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_6.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_7.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_8.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_9.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_10.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_11.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_12.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_13.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_14.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_15.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_16.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_17.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_18.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_19.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_20.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_21.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_22.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_23.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_24.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_25.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_26.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_27.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_28.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_29.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_30.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_31.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_32.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_33.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_34.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_35.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_36.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_37.setText(String.valueOf(day));
            }
        } else if (returnFirstDayOfMonth() == Calendar.THURSDAY) {
            calender_button_showing_shadow_5.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_6.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_7.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_8.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_9.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_10.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_11.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_12.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_13.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_14.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_15.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_16.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_17.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_18.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_19.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_20.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_21.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_22.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_23.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_24.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_25.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_26.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_27.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_28.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_29.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_30.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_31.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_32.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_33.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_34.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_35.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_36.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_37.setText(String.valueOf(day));
            }
        } else if (returnFirstDayOfMonth() == Calendar.FRIDAY) {
            calender_button_showing_shadow_6.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_7.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_8.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_9.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_10.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_11.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_12.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_13.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_14.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_15.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_16.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_17.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_18.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_19.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_20.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_21.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_22.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_23.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_24.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_25.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_26.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_27.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_28.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_29.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_30.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_31.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_32.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_33.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_34.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_35.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_36.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_37.setText(String.valueOf(day));
            }
        } else {
            calender_button_showing_shadow_7.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_8.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_9.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_10.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_11.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_12.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_13.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_14.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_15.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_16.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_17.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_18.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_19.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_20.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_21.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_22.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_23.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_24.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_25.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_26.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_27.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_28.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_29.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_30.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_31.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_32.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_33.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_34.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_35.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_36.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_37.setText(String.valueOf(day));
            }
        }
    }

    private void day_is_monday() {
        int day = 1;
        if (returnFirstDayOfMonth() == Calendar.MONDAY) {
            calender_button_showing_shadow_1.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_2.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_3.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_4.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_5.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_6.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_7.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_8.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_9.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_10.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_11.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_12.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_13.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_14.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_15.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_16.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_17.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_18.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_19.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_20.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_21.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_22.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_23.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_24.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_25.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_26.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_27.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_28.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_29.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_30.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_31.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_32.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_33.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_34.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_35.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_36.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_37.setText(String.valueOf(day));
            }
        } else if (returnFirstDayOfMonth() == Calendar.TUESDAY) {
            calender_button_showing_shadow_2.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_3.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_4.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_5.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_6.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_7.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_8.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_9.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_10.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_11.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_12.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_13.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_14.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_15.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_16.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_17.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_18.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_19.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_20.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_21.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_22.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_23.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_24.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_25.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_26.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_27.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_28.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_29.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_30.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_31.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_32.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_33.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_34.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_35.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_36.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_37.setText(String.valueOf(day));
            }
        } else if (returnFirstDayOfMonth() == Calendar.WEDNESDAY) {
            calender_button_showing_shadow_3.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_4.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_5.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_6.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_7.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_8.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_9.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_10.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_11.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_12.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_13.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_14.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_15.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_16.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_17.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_18.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_19.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_20.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_21.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_22.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_23.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_24.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_25.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_26.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_27.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_28.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_29.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_30.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_31.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_32.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_33.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_34.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_35.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_36.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_37.setText(String.valueOf(day));
            }
        } else if (returnFirstDayOfMonth() == Calendar.THURSDAY) {
            calender_button_showing_shadow_4.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_5.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_6.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_7.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_8.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_9.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_10.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_11.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_12.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_13.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_14.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_15.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_16.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_17.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_18.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_19.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_20.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_21.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_22.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_23.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_24.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_25.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_26.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_27.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_28.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_29.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_30.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_31.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_32.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_33.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_34.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_35.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_36.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_37.setText(String.valueOf(day));
            }
        } else if (returnFirstDayOfMonth() == Calendar.FRIDAY) {
            calender_button_showing_shadow_5.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_6.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_7.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_8.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_9.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_10.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_11.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_12.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_13.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_14.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_15.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_16.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_17.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_18.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_19.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_20.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_21.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_22.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_23.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_24.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_25.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_26.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_27.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_28.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_29.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_30.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_31.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_32.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_33.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_34.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_35.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_36.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_37.setText(String.valueOf(day));
            }
        } else if (returnFirstDayOfMonth() == Calendar.SATURDAY) {
            calender_button_showing_shadow_6.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_7.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_8.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_9.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_10.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_11.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_12.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_13.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_14.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_15.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_16.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_17.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_18.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_19.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_20.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_21.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_22.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_23.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_24.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_25.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_26.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_27.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_28.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_29.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_30.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_31.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_32.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_33.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_34.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_35.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_36.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_37.setText(String.valueOf(day));
            }
        } else {
            calender_button_showing_shadow_7.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_8.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_9.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_10.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_11.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_12.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_13.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_14.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_15.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_16.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_17.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_18.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_19.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_20.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_21.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_22.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_23.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_24.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_25.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_26.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_27.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_28.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_29.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_30.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_31.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_32.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_33.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_34.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_35.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_36.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_37.setText(String.valueOf(day));
            }
        }
    }

    private void day_is_tuesday() {
        int day = 1;
        if (returnFirstDayOfMonth() == Calendar.TUESDAY) {
            calender_button_showing_shadow_1.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_2.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_3.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_4.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_5.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_6.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_7.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_8.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_9.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_10.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_11.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_12.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_13.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_14.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_15.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_16.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_17.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_18.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_19.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_20.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_21.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_22.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_23.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_24.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_25.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_26.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_27.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_28.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_29.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_30.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_31.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_32.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_33.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_34.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_35.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_36.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_37.setText(String.valueOf(day));
            }
        } else if (returnFirstDayOfMonth() == Calendar.WEDNESDAY) {
            calender_button_showing_shadow_2.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_3.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_4.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_5.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_6.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_7.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_8.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_9.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_10.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_11.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_12.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_13.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_14.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_15.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_16.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_17.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_18.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_19.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_20.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_21.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_22.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_23.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_24.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_25.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_26.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_27.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_28.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_29.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_30.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_31.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_32.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_33.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_34.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_35.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_36.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_37.setText(String.valueOf(day));
            }
        } else if (returnFirstDayOfMonth() == Calendar.THURSDAY) {
            calender_button_showing_shadow_3.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_4.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_5.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_6.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_7.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_8.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_9.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_10.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_11.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_12.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_13.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_14.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_15.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_16.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_17.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_18.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_19.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_20.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_21.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_22.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_23.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_24.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_25.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_26.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_27.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_28.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_29.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_30.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_31.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_32.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_33.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_34.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_35.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_36.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_37.setText(String.valueOf(day));
            }
        } else if (returnFirstDayOfMonth() == Calendar.FRIDAY) {
            calender_button_showing_shadow_4.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_5.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_6.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_7.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_8.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_9.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_10.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_11.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_12.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_13.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_14.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_15.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_16.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_17.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_18.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_19.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_20.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_21.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_22.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_23.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_24.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_25.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_26.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_27.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_28.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_29.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_30.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_31.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_32.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_33.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_34.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_35.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_36.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_37.setText(String.valueOf(day));
            }
        } else if (returnFirstDayOfMonth() == Calendar.SATURDAY) {
            calender_button_showing_shadow_5.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_6.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_7.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_8.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_9.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_10.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_11.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_12.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_13.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_14.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_15.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_16.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_17.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_18.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_19.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_20.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_21.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_22.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_23.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_24.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_25.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_26.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_27.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_28.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_29.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_30.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_31.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_32.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_33.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_34.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_35.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_36.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_37.setText(String.valueOf(day));
            }
        } else if (returnFirstDayOfMonth() == Calendar.SUNDAY) {
            calender_button_showing_shadow_6.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_7.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_8.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_9.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_10.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_11.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_12.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_13.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_14.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_15.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_16.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_17.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_18.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_19.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_20.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_21.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_22.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_23.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_24.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_25.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_26.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_27.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_28.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_29.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_30.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_31.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_32.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_33.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_34.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_35.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_36.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_37.setText(String.valueOf(day));
            }
        } else {
            calender_button_showing_shadow_7.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_8.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_9.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_10.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_11.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_12.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_13.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_14.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_15.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_16.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_17.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_18.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_19.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_20.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_21.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_22.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_23.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_24.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_25.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_26.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_27.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_28.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_29.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_30.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_31.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_32.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_33.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_34.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_35.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_36.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_37.setText(String.valueOf(day));
            }
        }
    }

    private void day_is_wednesday() {
        int day = 1;
        if (returnFirstDayOfMonth() == Calendar.WEDNESDAY) {
            calender_button_showing_shadow_1.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_2.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_3.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_4.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_5.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_6.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_7.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_8.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_9.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_10.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_11.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_12.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_13.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_14.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_15.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_16.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_17.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_18.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_19.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_20.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_21.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_22.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_23.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_24.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_25.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_26.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_27.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_28.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_29.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_30.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_31.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_32.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_33.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_34.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_35.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_36.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_37.setText(String.valueOf(day));
            }
        } else if (returnFirstDayOfMonth() == Calendar.THURSDAY) {
            calender_button_showing_shadow_2.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_3.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_4.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_5.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_6.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_7.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_8.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_9.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_10.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_11.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_12.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_13.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_14.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_15.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_16.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_17.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_18.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_19.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_20.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_21.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_22.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_23.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_24.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_25.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_26.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_27.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_28.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_29.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_30.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_31.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_32.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_33.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_34.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_35.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_36.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_37.setText(String.valueOf(day));
            }
        } else if (returnFirstDayOfMonth() == Calendar.FRIDAY) {
            calender_button_showing_shadow_3.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_4.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_5.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_6.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_7.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_8.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_9.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_10.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_11.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_12.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_13.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_14.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_15.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_16.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_17.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_18.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_19.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_20.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_21.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_22.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_23.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_24.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_25.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_26.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_27.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_28.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_29.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_30.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_31.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_32.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_33.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_34.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_35.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_36.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_37.setText(String.valueOf(day));
            }
        } else if (returnFirstDayOfMonth() == Calendar.SATURDAY) {
            calender_button_showing_shadow_4.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_5.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_6.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_7.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_8.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_9.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_10.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_11.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_12.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_13.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_14.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_15.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_16.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_17.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_18.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_19.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_20.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_21.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_22.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_23.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_24.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_25.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_26.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_27.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_28.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_29.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_30.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_31.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_32.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_33.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_34.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_35.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_36.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_37.setText(String.valueOf(day));
            }
        } else if (returnFirstDayOfMonth() == Calendar.SUNDAY) {
            calender_button_showing_shadow_5.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_6.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_7.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_8.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_9.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_10.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_11.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_12.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_13.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_14.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_15.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_16.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_17.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_18.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_19.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_20.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_21.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_22.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_23.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_24.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_25.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_26.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_27.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_28.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_29.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_30.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_31.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_32.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_33.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_34.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_35.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_36.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_37.setText(String.valueOf(day));
            }
        } else if (returnFirstDayOfMonth() == Calendar.MONDAY) {
            calender_button_showing_shadow_6.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_7.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_8.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_9.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_10.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_11.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_12.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_13.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_14.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_15.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_16.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_17.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_18.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_19.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_20.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_21.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_22.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_23.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_24.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_25.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_26.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_27.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_28.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_29.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_30.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_31.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_32.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_33.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_34.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_35.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_36.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_37.setText(String.valueOf(day));
            }
        } else {
            calender_button_showing_shadow_7.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_8.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_9.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_10.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_11.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_12.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_13.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_14.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_15.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_16.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_17.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_18.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_19.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_20.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_21.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_22.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_23.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_24.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_25.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_26.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_27.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_28.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_29.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_30.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_31.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_32.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_33.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_34.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_35.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_36.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_37.setText(String.valueOf(day));
            }
        }
    }

    private void day_is_thursday() {
        int day = 1;
        if (returnFirstDayOfMonth() == Calendar.THURSDAY) {
            calender_button_showing_shadow_1.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_2.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_3.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_4.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_5.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_6.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_7.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_8.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_9.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_10.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_11.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_12.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_13.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_14.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_15.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_16.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_17.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_18.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_19.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_20.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_21.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_22.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_23.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_24.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_25.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_26.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_27.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_28.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_29.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_30.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_31.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_32.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_33.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_34.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_35.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_36.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_37.setText(String.valueOf(day));
            }
        } else if (returnFirstDayOfMonth() == Calendar.FRIDAY) {
            calender_button_showing_shadow_2.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_3.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_4.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_5.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_6.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_7.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_8.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_9.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_10.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_11.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_12.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_13.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_14.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_15.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_16.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_17.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_18.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_19.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_20.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_21.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_22.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_23.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_24.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_25.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_26.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_27.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_28.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_29.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_30.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_31.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_32.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_33.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_34.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_35.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_36.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_37.setText(String.valueOf(day));
            }
        } else if (returnFirstDayOfMonth() == Calendar.SATURDAY) {
            calender_button_showing_shadow_3.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_4.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_5.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_6.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_7.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_8.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_9.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_10.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_11.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_12.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_13.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_14.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_15.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_16.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_17.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_18.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_19.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_20.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_21.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_22.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_23.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_24.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_25.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_26.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_27.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_28.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_29.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_30.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_31.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_32.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_33.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_34.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_35.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_36.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_37.setText(String.valueOf(day));
            }
        } else if (returnFirstDayOfMonth() == Calendar.SUNDAY) {
            calender_button_showing_shadow_4.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_5.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_6.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_7.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_8.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_9.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_10.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_11.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_12.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_13.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_14.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_15.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_16.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_17.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_18.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_19.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_20.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_21.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_22.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_23.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_24.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_25.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_26.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_27.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_28.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_29.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_30.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_31.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_32.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_33.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_34.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_35.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_36.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_37.setText(String.valueOf(day));
            }
        } else if (returnFirstDayOfMonth() == Calendar.MONDAY) {
            calender_button_showing_shadow_5.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_6.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_7.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_8.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_9.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_10.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_11.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_12.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_13.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_14.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_15.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_16.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_17.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_18.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_19.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_20.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_21.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_22.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_23.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_24.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_25.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_26.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_27.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_28.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_29.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_30.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_31.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_32.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_33.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_34.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_35.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_36.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_37.setText(String.valueOf(day));
            }
        } else if (returnFirstDayOfMonth() == Calendar.TUESDAY) {
            calender_button_showing_shadow_6.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_7.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_8.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_9.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_10.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_11.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_12.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_13.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_14.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_15.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_16.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_17.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_18.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_19.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_20.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_21.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_22.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_23.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_24.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_25.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_26.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_27.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_28.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_29.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_30.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_31.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_32.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_33.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_34.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_35.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_36.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_37.setText(String.valueOf(day));
            }
        } else {
            calender_button_showing_shadow_7.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_8.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_9.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_10.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_11.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_12.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_13.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_14.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_15.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_16.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_17.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_18.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_19.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_20.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_21.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_22.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_23.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_24.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_25.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_26.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_27.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_28.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_29.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_30.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_31.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_32.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_33.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_34.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_35.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_36.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_37.setText(String.valueOf(day));
            }
        }
    }

    private void day_is_friday() {
        int day = 1;
        if (returnFirstDayOfMonth() == Calendar.FRIDAY) {
            calender_button_showing_shadow_1.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_2.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_3.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_4.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_5.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_6.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_7.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_8.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_9.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_10.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_11.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_12.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_13.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_14.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_15.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_16.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_17.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_18.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_19.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_20.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_21.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_22.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_23.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_24.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_25.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_26.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_27.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_28.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_29.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_30.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_31.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_32.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_33.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_34.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_35.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_36.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_37.setText(String.valueOf(day));
            }
        } else if (returnFirstDayOfMonth() == Calendar.SATURDAY) {
            calender_button_showing_shadow_2.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_3.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_4.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_5.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_6.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_7.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_8.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_9.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_10.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_11.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_12.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_13.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_14.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_15.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_16.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_17.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_18.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_19.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_20.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_21.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_22.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_23.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_24.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_25.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_26.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_27.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_28.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_29.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_30.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_31.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_32.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_33.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_34.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_35.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_36.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_37.setText(String.valueOf(day));
            }
        } else if (returnFirstDayOfMonth() == Calendar.SUNDAY) {
            calender_button_showing_shadow_3.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_4.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_5.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_6.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_7.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_8.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_9.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_10.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_11.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_12.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_13.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_14.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_15.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_16.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_17.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_18.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_19.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_20.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_21.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_22.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_23.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_24.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_25.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_26.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_27.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_28.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_29.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_30.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_31.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_32.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_33.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_34.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_35.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_36.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_37.setText(String.valueOf(day));
            }
        } else if (returnFirstDayOfMonth() == Calendar.MONDAY) {
            calender_button_showing_shadow_4.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_5.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_6.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_7.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_8.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_9.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_10.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_11.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_12.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_13.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_14.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_15.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_16.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_17.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_18.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_19.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_20.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_21.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_22.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_23.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_24.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_25.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_26.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_27.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_28.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_29.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_30.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_31.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_32.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_33.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_34.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_35.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_36.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_37.setText(String.valueOf(day));
            }
        } else if (returnFirstDayOfMonth() == Calendar.TUESDAY) {
            calender_button_showing_shadow_5.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_6.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_7.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_8.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_9.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_10.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_11.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_12.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_13.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_14.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_15.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_16.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_17.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_18.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_19.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_20.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_21.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_22.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_23.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_24.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_25.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_26.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_27.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_28.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_29.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_30.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_31.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_32.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_33.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_34.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_35.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_36.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_37.setText(String.valueOf(day));
            }
        } else if (returnFirstDayOfMonth() == Calendar.WEDNESDAY) {
            calender_button_showing_shadow_6.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_7.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_8.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_9.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_10.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_11.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_12.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_13.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_14.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_15.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_16.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_17.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_18.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_19.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_20.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_21.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_22.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_23.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_24.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_25.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_26.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_27.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_28.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_29.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_30.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_31.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_32.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_33.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_34.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_35.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_36.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_37.setText(String.valueOf(day));
            }
        } else {
            calender_button_showing_shadow_7.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_8.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_9.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_10.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_11.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_12.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_13.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_14.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_15.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_16.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_17.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_18.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_19.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_20.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_21.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_22.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_23.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_24.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_25.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_26.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_27.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_28.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_29.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_30.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_31.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_32.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_33.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_34.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_35.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_36.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_37.setText(String.valueOf(day));
            }
        }
    }

    private void day_is_saturday() {
        int day = 1;
        if (returnFirstDayOfMonth() == Calendar.SATURDAY) {
            calender_button_showing_shadow_1.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_2.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_3.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_4.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_5.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_6.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_7.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_8.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_9.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_10.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_11.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_12.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_13.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_14.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_15.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_16.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_17.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_18.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_19.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_20.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_21.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_22.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_23.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_24.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_25.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_26.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_27.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_28.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_29.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_30.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_31.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_32.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_33.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_34.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_35.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_36.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_37.setText(String.valueOf(day));
            }
        } else if (returnFirstDayOfMonth() == Calendar.SUNDAY) {
            calender_button_showing_shadow_2.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_3.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_4.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_5.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_6.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_7.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_8.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_9.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_10.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_11.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_12.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_13.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_14.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_15.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_16.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_17.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_18.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_19.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_20.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_21.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_22.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_23.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_24.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_25.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_26.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_27.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_28.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_29.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_30.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_31.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_32.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_33.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_34.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_35.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_36.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_37.setText(String.valueOf(day));
            }
        } else if (returnFirstDayOfMonth() == Calendar.MONDAY) {
            calender_button_showing_shadow_3.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_4.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_5.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_6.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_7.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_8.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_9.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_10.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_11.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_12.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_13.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_14.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_15.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_16.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_17.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_18.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_19.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_20.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_21.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_22.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_23.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_24.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_25.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_26.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_27.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_28.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_29.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_30.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_31.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_32.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_33.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_34.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_35.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_36.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_37.setText(String.valueOf(day));
            }
        } else if (returnFirstDayOfMonth() == Calendar.TUESDAY) {
            calender_button_showing_shadow_4.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_5.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_6.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_7.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_8.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_9.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_10.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_11.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_12.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_13.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_14.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_15.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_16.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_17.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_18.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_19.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_20.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_21.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_22.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_23.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_24.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_25.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_26.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_27.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_28.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_29.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_30.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_31.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_32.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_33.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_34.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_35.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_36.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_37.setText(String.valueOf(day));
            }
        } else if (returnFirstDayOfMonth() == Calendar.WEDNESDAY) {
            calender_button_showing_shadow_5.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_6.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_7.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_8.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_9.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_10.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_11.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_12.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_13.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_14.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_15.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_16.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_17.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_18.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_19.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_20.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_21.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_22.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_23.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_24.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_25.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_26.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_27.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_28.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_29.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_30.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_31.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_32.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_33.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_34.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_35.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_36.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_37.setText(String.valueOf(day));
            }
        } else if (returnFirstDayOfMonth() == Calendar.THURSDAY) {
            calender_button_showing_shadow_6.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_7.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_8.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_9.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_10.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_11.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_12.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_13.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_14.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_15.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_16.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_17.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_18.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_19.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_20.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_21.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_22.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_23.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_24.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_25.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_26.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_27.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_28.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_29.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_30.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_31.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_32.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_33.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_34.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_35.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_36.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_37.setText(String.valueOf(day));
            }
        } else {
            calender_button_showing_shadow_7.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_8.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_9.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_10.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_11.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_12.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_13.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_14.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_15.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_16.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_17.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_18.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_19.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_20.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_21.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_22.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_23.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_24.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_25.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_26.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_27.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_28.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_29.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_30.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_31.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_32.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_33.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_34.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_35.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_36.setText(String.valueOf(day));
            }
            day = day + 1;
            if (day <= returnLastDayOfMonth()) {
                calender_button_showing_shadow_37.setText(String.valueOf(day));
            }
        }
    }

    private void define_the_buttons() {
        if (getView() != null) {
            calender_button_showing_shadow_1 = getView().findViewById(R.id.bt_1);
            calender_button_showing_shadow_2 = getView().findViewById(R.id.bt_2);
            calender_button_showing_shadow_3 = getView().findViewById(R.id.bt_3);
            calender_button_showing_shadow_4 = getView().findViewById(R.id.bt_4);
            calender_button_showing_shadow_5 = getView().findViewById(R.id.bt_5);
            calender_button_showing_shadow_6 = getView().findViewById(R.id.bt_6);
            calender_button_showing_shadow_7 = getView().findViewById(R.id.bt_7);
            calender_button_showing_shadow_8 = getView().findViewById(R.id.bt_8);
            calender_button_showing_shadow_9 = getView().findViewById(R.id.bt_9);
            calender_button_showing_shadow_10 = getView().findViewById(R.id.bt_10);
            calender_button_showing_shadow_11 = getView().findViewById(R.id.bt_11);
            calender_button_showing_shadow_12 = getView().findViewById(R.id.bt_12);
            calender_button_showing_shadow_13 = getView().findViewById(R.id.bt_13);
            calender_button_showing_shadow_14 = getView().findViewById(R.id.bt_14);
            calender_button_showing_shadow_15 = getView().findViewById(R.id.bt_15);
            calender_button_showing_shadow_16 = getView().findViewById(R.id.bt_16);
            calender_button_showing_shadow_17 = getView().findViewById(R.id.bt_17);
            calender_button_showing_shadow_18 = getView().findViewById(R.id.bt_18);
            calender_button_showing_shadow_19 = getView().findViewById(R.id.bt_19);
            calender_button_showing_shadow_20 = getView().findViewById(R.id.bt_20);
            calender_button_showing_shadow_21 = getView().findViewById(R.id.bt_21);
            calender_button_showing_shadow_22 = getView().findViewById(R.id.bt_22);
            calender_button_showing_shadow_23 = getView().findViewById(R.id.bt_23);
            calender_button_showing_shadow_24 = getView().findViewById(R.id.bt_24);
            calender_button_showing_shadow_25 = getView().findViewById(R.id.bt_25);
            calender_button_showing_shadow_26 = getView().findViewById(R.id.bt_26);
            calender_button_showing_shadow_27 = getView().findViewById(R.id.bt_27);
            calender_button_showing_shadow_28 = getView().findViewById(R.id.bt_28);
            calender_button_showing_shadow_29 = getView().findViewById(R.id.bt_29);
            calender_button_showing_shadow_30 = getView().findViewById(R.id.bt_30);
            calender_button_showing_shadow_31 = getView().findViewById(R.id.bt_31);
            calender_button_showing_shadow_32 = getView().findViewById(R.id.bt_32);
            calender_button_showing_shadow_33 = getView().findViewById(R.id.bt_33);
            calender_button_showing_shadow_34 = getView().findViewById(R.id.bt_34);
            calender_button_showing_shadow_35 = getView().findViewById(R.id.bt_35);
            calender_button_showing_shadow_36 = getView().findViewById(R.id.bt_36);
            calender_button_showing_shadow_37 = getView().findViewById(R.id.bt_37);
        }
    }

    private void back_and_front_button_listen() {
        if (getView() != null) {
            Button button_shadow_for_the_back_for_good_habits = getView().findViewById(R.id.bt_calendar_back);
            Button button_shadow_for_the_front_for_good_habits = getView().findViewById(R.id.bt_calendar_next);
            final TextView month_and_year_in_calender_for_good_habits = getView().findViewById(R.id.tv_month_year);
            button_shadow_for_the_back_for_good_habits.setOnClickListener(v -> {
                String[] splitter = month_and_year_in_calender_for_good_habits.getText().toString().split(" ");
                String month_name;
                if (returnMonthStringToInt(splitter[0]) == 0) {
                    month_name = returnMonth(11);
                    String year = String.valueOf(Integer.parseInt(splitter[1]) - 1);
                    month_and_year_in_calender_for_good_habits.setText(month_name.concat(" ").concat(year));

                } else {
                    month_name = returnMonth(returnMonthStringToInt(splitter[0]) - 1);
                    month_and_year_in_calender_for_good_habits.setText(month_name.concat(" ").concat(splitter[1]));

                }
                clear_the_calender();
                setTheDaysOnTheRealText();
                clear_the_color_from_the_keyboard();
                set_all_buttons_to_visible();
                remove_the_hiding_buttons();
                color_today();
                check_if_date_is_future();
                hide_or_un_hide_the_button(0);
                color_the_calender();
                check_if_date_is_future();
                color_the_button_under_the_calender();
                divide_it_into_weeks();
            });
            button_shadow_for_the_front_for_good_habits.setOnClickListener(v -> {
                String[] splitter = month_and_year_in_calender_for_good_habits.getText().toString().split(" ");
                String month_name;
                if (returnMonthStringToInt((splitter[0])) == 11) {
                    month_name = returnMonth(0);
                    String year = String.valueOf(Integer.parseInt(splitter[1]) + 1);
                    month_and_year_in_calender_for_good_habits.setText(month_name.concat(" ").concat(year));
                } else {
                    month_name = returnMonth(returnMonthStringToInt(splitter[0]) + 1);
                    month_and_year_in_calender_for_good_habits.setText(month_name.concat(" ").concat(splitter[1]));
                }
                clear_the_calender();
                setTheDaysOnTheRealText();
                clear_the_color_from_the_keyboard();
                set_all_buttons_to_visible();
                remove_the_hiding_buttons();
                color_today();
                check_if_date_is_future();
                hide_or_un_hide_the_button(0);
                color_the_calender();
                check_if_date_is_future();
                color_the_button_under_the_calender();
                divide_it_into_weeks();
            });
        }
    }

    private void clear_the_calender() {
        calender_button_showing_shadow_1.setText("");
        calender_button_showing_shadow_2.setText("");
        calender_button_showing_shadow_3.setText("");
        calender_button_showing_shadow_4.setText("");
        calender_button_showing_shadow_5.setText("");
        calender_button_showing_shadow_6.setText("");
        calender_button_showing_shadow_7.setText("");
        calender_button_showing_shadow_8.setText("");
        calender_button_showing_shadow_9.setText("");
        calender_button_showing_shadow_10.setText("");
        calender_button_showing_shadow_11.setText("");
        calender_button_showing_shadow_12.setText("");
        calender_button_showing_shadow_13.setText("");
        calender_button_showing_shadow_14.setText("");
        calender_button_showing_shadow_15.setText("");
        calender_button_showing_shadow_16.setText("");
        calender_button_showing_shadow_17.setText("");
        calender_button_showing_shadow_18.setText("");
        calender_button_showing_shadow_19.setText("");
        calender_button_showing_shadow_20.setText("");
        calender_button_showing_shadow_21.setText("");
        calender_button_showing_shadow_22.setText("");
        calender_button_showing_shadow_23.setText("");
        calender_button_showing_shadow_24.setText("");
        calender_button_showing_shadow_25.setText("");
        calender_button_showing_shadow_26.setText("");
        calender_button_showing_shadow_27.setText("");
        calender_button_showing_shadow_28.setText("");
        calender_button_showing_shadow_29.setText("");
        calender_button_showing_shadow_30.setText("");
        calender_button_showing_shadow_31.setText("");
        calender_button_showing_shadow_32.setText("");
        calender_button_showing_shadow_33.setText("");
        calender_button_showing_shadow_34.setText("");
        calender_button_showing_shadow_35.setText("");
        calender_button_showing_shadow_36.setText("");
        calender_button_showing_shadow_37.setText("");
    }

    private void color_the_selected(int which) {
        if (which == 1) {
            calender_button_showing_shadow_1.setTextColor(Color.WHITE);
            calender_button_showing_shadow_1.setBackgroundResource(R.drawable.round_button_colored_fav);
        } else if (which == 2) {
            calender_button_showing_shadow_2.setTextColor(Color.WHITE);
            calender_button_showing_shadow_2.setBackgroundResource(R.drawable.round_button_colored_fav);
        } else if (which == 3) {
            calender_button_showing_shadow_3.setTextColor(Color.WHITE);
            calender_button_showing_shadow_3.setBackgroundResource(R.drawable.round_button_colored_fav);
        } else if (which == 4) {
            calender_button_showing_shadow_4.setTextColor(Color.WHITE);
            calender_button_showing_shadow_4.setBackgroundResource(R.drawable.round_button_colored_fav);
        } else if (which == 5) {
            calender_button_showing_shadow_5.setTextColor(Color.WHITE);
            calender_button_showing_shadow_5.setBackgroundResource(R.drawable.round_button_colored_fav);
        } else if (which == 6) {
            calender_button_showing_shadow_6.setTextColor(Color.WHITE);
            calender_button_showing_shadow_6.setBackgroundResource(R.drawable.round_button_colored_fav);
        } else if (which == 7) {
            calender_button_showing_shadow_7.setTextColor(Color.WHITE);
            calender_button_showing_shadow_7.setBackgroundResource(R.drawable.round_button_colored_fav);
        } else if (which == 8) {
            calender_button_showing_shadow_8.setTextColor(Color.WHITE);
            calender_button_showing_shadow_8.setBackgroundResource(R.drawable.round_button_colored_fav);
        } else if (which == 9) {
            calender_button_showing_shadow_9.setTextColor(Color.WHITE);
            calender_button_showing_shadow_9.setBackgroundResource(R.drawable.round_button_colored_fav);
        } else if (which == 10) {
            calender_button_showing_shadow_10.setTextColor(Color.WHITE);
            calender_button_showing_shadow_10.setBackgroundResource(R.drawable.round_button_colored_fav);
        } else if (which == 11) {
            calender_button_showing_shadow_11.setTextColor(Color.WHITE);
            calender_button_showing_shadow_11.setBackgroundResource(R.drawable.round_button_colored_fav);
        } else if (which == 12) {
            calender_button_showing_shadow_12.setTextColor(Color.WHITE);
            calender_button_showing_shadow_12.setBackgroundResource(R.drawable.round_button_colored_fav);
        } else if (which == 13) {
            calender_button_showing_shadow_13.setTextColor(Color.WHITE);
            calender_button_showing_shadow_13.setBackgroundResource(R.drawable.round_button_colored_fav);
        } else if (which == 14) {
            calender_button_showing_shadow_14.setTextColor(Color.WHITE);
            calender_button_showing_shadow_14.setBackgroundResource(R.drawable.round_button_colored_fav);
        } else if (which == 15) {
            calender_button_showing_shadow_15.setTextColor(Color.WHITE);
            calender_button_showing_shadow_15.setBackgroundResource(R.drawable.round_button_colored_fav);
        } else if (which == 16) {
            calender_button_showing_shadow_16.setTextColor(Color.WHITE);
            calender_button_showing_shadow_16.setBackgroundResource(R.drawable.round_button_colored_fav);
        } else if (which == 17) {
            calender_button_showing_shadow_17.setTextColor(Color.WHITE);
            calender_button_showing_shadow_17.setBackgroundResource(R.drawable.round_button_colored_fav);
        } else if (which == 18) {
            calender_button_showing_shadow_18.setTextColor(Color.WHITE);
            calender_button_showing_shadow_18.setBackgroundResource(R.drawable.round_button_colored_fav);
        } else if (which == 19) {
            calender_button_showing_shadow_19.setTextColor(Color.WHITE);
            calender_button_showing_shadow_19.setBackgroundResource(R.drawable.round_button_colored_fav);
        } else if (which == 20) {
            calender_button_showing_shadow_20.setTextColor(Color.WHITE);
            calender_button_showing_shadow_20.setBackgroundResource(R.drawable.round_button_colored_fav);
        } else if (which == 21) {
            calender_button_showing_shadow_21.setTextColor(Color.WHITE);
            calender_button_showing_shadow_21.setBackgroundResource(R.drawable.round_button_colored_fav);
        } else if (which == 22) {
            calender_button_showing_shadow_22.setTextColor(Color.WHITE);
            calender_button_showing_shadow_22.setBackgroundResource(R.drawable.round_button_colored_fav);
        } else if (which == 23) {
            calender_button_showing_shadow_23.setTextColor(Color.WHITE);
            calender_button_showing_shadow_23.setBackgroundResource(R.drawable.round_button_colored_fav);
        } else if (which == 24) {
            calender_button_showing_shadow_24.setTextColor(Color.WHITE);
            calender_button_showing_shadow_24.setBackgroundResource(R.drawable.round_button_colored_fav);
        } else if (which == 25) {
            calender_button_showing_shadow_25.setTextColor(Color.WHITE);
            calender_button_showing_shadow_25.setBackgroundResource(R.drawable.round_button_colored_fav);
        } else if (which == 26) {
            calender_button_showing_shadow_26.setTextColor(Color.WHITE);
            calender_button_showing_shadow_26.setBackgroundResource(R.drawable.round_button_colored_fav);
        } else if (which == 27) {
            calender_button_showing_shadow_27.setTextColor(Color.WHITE);
            calender_button_showing_shadow_27.setBackgroundResource(R.drawable.round_button_colored_fav);
        } else if (which == 28) {
            calender_button_showing_shadow_28.setTextColor(Color.WHITE);
            calender_button_showing_shadow_28.setBackgroundResource(R.drawable.round_button_colored_fav);
        } else if (which == 29) {
            calender_button_showing_shadow_29.setTextColor(Color.WHITE);
            calender_button_showing_shadow_29.setBackgroundResource(R.drawable.round_button_colored_fav);
        } else if (which == 30) {
            calender_button_showing_shadow_30.setTextColor(Color.WHITE);
            calender_button_showing_shadow_30.setBackgroundResource(R.drawable.round_button_colored_fav);
        } else if (which == 31) {
            calender_button_showing_shadow_31.setTextColor(Color.WHITE);
            calender_button_showing_shadow_31.setBackgroundResource(R.drawable.round_button_colored_fav);
        } else if (which == 32) {
            calender_button_showing_shadow_32.setTextColor(Color.WHITE);
            calender_button_showing_shadow_32.setBackgroundResource(R.drawable.round_button_colored_fav);
        } else if (which == 33) {
            calender_button_showing_shadow_33.setTextColor(Color.WHITE);
            calender_button_showing_shadow_33.setBackgroundResource(R.drawable.round_button_colored_fav);
        } else if (which == 34) {
            calender_button_showing_shadow_34.setTextColor(Color.WHITE);
            calender_button_showing_shadow_34.setBackgroundResource(R.drawable.round_button_colored_fav);
        } else if (which == 35) {
            calender_button_showing_shadow_35.setTextColor(Color.WHITE);
            calender_button_showing_shadow_35.setBackgroundResource(R.drawable.round_button_colored_fav);
        } else if (which == 36) {
            calender_button_showing_shadow_36.setTextColor(Color.WHITE);
            calender_button_showing_shadow_36.setBackgroundResource(R.drawable.round_button_colored_fav);
        } else {
            calender_button_showing_shadow_37.setTextColor(Color.WHITE);
            calender_button_showing_shadow_37.setBackgroundResource(R.drawable.round_button_colored_fav);
        }
    }

    private void clear_the_color_from_the_keyboard() {
        calender_button_showing_shadow_1.setTextColor(Color.BLACK);
        calender_button_showing_shadow_1.setBackgroundResource(R.drawable.round_button);
        calender_button_showing_shadow_2.setTextColor(Color.BLACK);
        calender_button_showing_shadow_2.setBackgroundResource(R.drawable.round_button);
        calender_button_showing_shadow_3.setTextColor(Color.BLACK);
        calender_button_showing_shadow_3.setBackgroundResource(R.drawable.round_button);
        calender_button_showing_shadow_4.setTextColor(Color.BLACK);
        calender_button_showing_shadow_4.setBackgroundResource(R.drawable.round_button);
        calender_button_showing_shadow_5.setTextColor(Color.BLACK);
        calender_button_showing_shadow_5.setBackgroundResource(R.drawable.round_button);
        calender_button_showing_shadow_6.setTextColor(Color.BLACK);
        calender_button_showing_shadow_6.setBackgroundResource(R.drawable.round_button);
        calender_button_showing_shadow_7.setTextColor(Color.BLACK);
        calender_button_showing_shadow_7.setBackgroundResource(R.drawable.round_button);
        calender_button_showing_shadow_8.setTextColor(Color.BLACK);
        calender_button_showing_shadow_8.setBackgroundResource(R.drawable.round_button);
        calender_button_showing_shadow_9.setTextColor(Color.BLACK);
        calender_button_showing_shadow_9.setBackgroundResource(R.drawable.round_button);
        calender_button_showing_shadow_10.setTextColor(Color.BLACK);
        calender_button_showing_shadow_10.setBackgroundResource(R.drawable.round_button);
        calender_button_showing_shadow_11.setTextColor(Color.BLACK);
        calender_button_showing_shadow_11.setBackgroundResource(R.drawable.round_button);
        calender_button_showing_shadow_12.setTextColor(Color.BLACK);
        calender_button_showing_shadow_12.setBackgroundResource(R.drawable.round_button);
        calender_button_showing_shadow_13.setTextColor(Color.BLACK);
        calender_button_showing_shadow_13.setBackgroundResource(R.drawable.round_button);
        calender_button_showing_shadow_14.setTextColor(Color.BLACK);
        calender_button_showing_shadow_14.setBackgroundResource(R.drawable.round_button);
        calender_button_showing_shadow_15.setTextColor(Color.BLACK);
        calender_button_showing_shadow_15.setBackgroundResource(R.drawable.round_button);
        calender_button_showing_shadow_16.setTextColor(Color.BLACK);
        calender_button_showing_shadow_16.setBackgroundResource(R.drawable.round_button);
        calender_button_showing_shadow_17.setTextColor(Color.BLACK);
        calender_button_showing_shadow_17.setBackgroundResource(R.drawable.round_button);
        calender_button_showing_shadow_18.setTextColor(Color.BLACK);
        calender_button_showing_shadow_18.setBackgroundResource(R.drawable.round_button);
        calender_button_showing_shadow_19.setTextColor(Color.BLACK);
        calender_button_showing_shadow_19.setBackgroundResource(R.drawable.round_button);
        calender_button_showing_shadow_20.setTextColor(Color.BLACK);
        calender_button_showing_shadow_20.setBackgroundResource(R.drawable.round_button);
        calender_button_showing_shadow_21.setTextColor(Color.BLACK);
        calender_button_showing_shadow_21.setBackgroundResource(R.drawable.round_button);
        calender_button_showing_shadow_22.setTextColor(Color.BLACK);
        calender_button_showing_shadow_22.setBackgroundResource(R.drawable.round_button);
        calender_button_showing_shadow_23.setTextColor(Color.BLACK);
        calender_button_showing_shadow_23.setBackgroundResource(R.drawable.round_button);
        calender_button_showing_shadow_24.setTextColor(Color.BLACK);
        calender_button_showing_shadow_24.setBackgroundResource(R.drawable.round_button);
        calender_button_showing_shadow_25.setTextColor(Color.BLACK);
        calender_button_showing_shadow_25.setBackgroundResource(R.drawable.round_button);
        calender_button_showing_shadow_26.setTextColor(Color.BLACK);
        calender_button_showing_shadow_26.setBackgroundResource(R.drawable.round_button);
        calender_button_showing_shadow_27.setTextColor(Color.BLACK);
        calender_button_showing_shadow_27.setBackgroundResource(R.drawable.round_button);
        calender_button_showing_shadow_28.setTextColor(Color.BLACK);
        calender_button_showing_shadow_28.setBackgroundResource(R.drawable.round_button);
        calender_button_showing_shadow_29.setTextColor(Color.BLACK);
        calender_button_showing_shadow_29.setBackgroundResource(R.drawable.round_button);
        calender_button_showing_shadow_30.setTextColor(Color.BLACK);
        calender_button_showing_shadow_30.setBackgroundResource(R.drawable.round_button);
        calender_button_showing_shadow_31.setTextColor(Color.BLACK);
        calender_button_showing_shadow_31.setBackgroundResource(R.drawable.round_button);
        calender_button_showing_shadow_32.setTextColor(Color.BLACK);
        calender_button_showing_shadow_32.setBackgroundResource(R.drawable.round_button);
        calender_button_showing_shadow_33.setTextColor(Color.BLACK);
        calender_button_showing_shadow_33.setBackgroundResource(R.drawable.round_button);
        calender_button_showing_shadow_34.setTextColor(Color.BLACK);
        calender_button_showing_shadow_34.setBackgroundResource(R.drawable.round_button);
        calender_button_showing_shadow_35.setTextColor(Color.BLACK);
        calender_button_showing_shadow_35.setBackgroundResource(R.drawable.round_button);
        calender_button_showing_shadow_36.setTextColor(Color.BLACK);
        calender_button_showing_shadow_36.setBackgroundResource(R.drawable.round_button);
        calender_button_showing_shadow_37.setTextColor(Color.BLACK);
        calender_button_showing_shadow_37.setBackgroundResource(R.drawable.round_button);
    }

    private void color_the_today_value() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        color_the_today = String.valueOf(day).concat("_").concat(String.valueOf(month)).concat("_").concat(String.valueOf(year));
    }

    private void calender_button_listeners() {
        calender_button_showing_shadow_1.setOnClickListener(v -> {
            clear_the_background_only();
            color_only_today();
            color_the_selected(1);
            if (getView() != null) {
                TextView month_and_year_in_calender_for_good_habits = getView().findViewById(R.id.tv_month_year);
                String[] splitter = month_and_year_in_calender_for_good_habits.getText().toString().split(" ");
                int month = returnMonthStringToInt(splitter[0]);
                color_the_today = calender_button_showing_shadow_1.getText().toString().concat("_").concat(String.valueOf(month)).concat("_").concat(splitter[1]);
            }
            check_if_date_is_future();
            color_the_button_under_the_calender();
        });
        calender_button_showing_shadow_2.setOnClickListener(v -> {
            clear_the_background_only();
            color_only_today();
            color_the_selected(2);
            if (getView() != null) {
                TextView month_and_year_in_calender_for_good_habits = getView().findViewById(R.id.tv_month_year);
                String[] splitter = month_and_year_in_calender_for_good_habits.getText().toString().split(" ");
                int month = returnMonthStringToInt(splitter[0]);
                color_the_today = calender_button_showing_shadow_2.getText().toString().concat("_").concat(String.valueOf(month)).concat("_").concat(splitter[1]);
            }
            check_if_date_is_future();
            color_the_button_under_the_calender();
        });
        calender_button_showing_shadow_3.setOnClickListener(v -> {
            clear_the_background_only();
            color_only_today();
            color_the_selected(3);
            if (getView() != null) {
                TextView month_and_year_in_calender_for_good_habits = getView().findViewById(R.id.tv_month_year);
                String[] splitter = month_and_year_in_calender_for_good_habits.getText().toString().split(" ");
                int month = returnMonthStringToInt(splitter[0]);
                color_the_today = calender_button_showing_shadow_3.getText().toString().concat("_").concat(String.valueOf(month)).concat("_").concat(splitter[1]);
            }
            check_if_date_is_future();
            color_the_button_under_the_calender();
        });
        calender_button_showing_shadow_4.setOnClickListener(v -> {
            clear_the_background_only();
            color_only_today();
            color_the_selected(4);
            if (getView() != null) {
                TextView month_and_year_in_calender_for_good_habits = getView().findViewById(R.id.tv_month_year);
                String[] splitter = month_and_year_in_calender_for_good_habits.getText().toString().split(" ");
                int month = returnMonthStringToInt(splitter[0]);
                color_the_today = calender_button_showing_shadow_4.getText().toString().concat("_").concat(String.valueOf(month)).concat("_").concat(splitter[1]);
            }
            check_if_date_is_future();
            color_the_button_under_the_calender();
        });
        calender_button_showing_shadow_5.setOnClickListener(v -> {
            clear_the_background_only();
            color_only_today();
            color_the_selected(5);
            if (getView() != null) {
                TextView month_and_year_in_calender_for_good_habits = getView().findViewById(R.id.tv_month_year);
                String[] splitter = month_and_year_in_calender_for_good_habits.getText().toString().split(" ");
                int month = returnMonthStringToInt(splitter[0]);
                color_the_today = calender_button_showing_shadow_5.getText().toString().concat("_").concat(String.valueOf(month)).concat("_").concat(splitter[1]);
            }
            check_if_date_is_future();
            color_the_button_under_the_calender();
        });
        calender_button_showing_shadow_6.setOnClickListener(v -> {
            clear_the_background_only();
            color_only_today();
            color_the_selected(6);
            if (getView() != null) {
                TextView month_and_year_in_calender_for_good_habits = getView().findViewById(R.id.tv_month_year);
                String[] splitter = month_and_year_in_calender_for_good_habits.getText().toString().split(" ");
                int month = returnMonthStringToInt(splitter[0]);
                color_the_today = calender_button_showing_shadow_6.getText().toString().concat("_").concat(String.valueOf(month)).concat("_").concat(splitter[1]);
            }
            check_if_date_is_future();
            color_the_button_under_the_calender();
        });
        calender_button_showing_shadow_7.setOnClickListener(v -> {
            clear_the_background_only();
            color_only_today();
            color_the_selected(7);
            if (getView() != null) {
                TextView month_and_year_in_calender_for_good_habits = getView().findViewById(R.id.tv_month_year);
                String[] splitter = month_and_year_in_calender_for_good_habits.getText().toString().split(" ");
                int month = returnMonthStringToInt(splitter[0]);
                color_the_today = calender_button_showing_shadow_7.getText().toString().concat("_").concat(String.valueOf(month)).concat("_").concat(splitter[1]);
            }
            check_if_date_is_future();
            color_the_button_under_the_calender();
        });
        calender_button_showing_shadow_8.setOnClickListener(v -> {
            clear_the_background_only();
            color_only_today();
            color_the_selected(8);
            if (getView() != null) {
                TextView month_and_year_in_calender_for_good_habits = getView().findViewById(R.id.tv_month_year);
                String[] splitter = month_and_year_in_calender_for_good_habits.getText().toString().split(" ");
                int month = returnMonthStringToInt(splitter[0]);
                color_the_today = calender_button_showing_shadow_8.getText().toString().concat("_").concat(String.valueOf(month)).concat("_").concat(splitter[1]);
            }
            check_if_date_is_future();
            color_the_button_under_the_calender();
        });
        calender_button_showing_shadow_9.setOnClickListener(v -> {
            clear_the_background_only();
            color_only_today();
            color_the_selected(9);
            if (getView() != null) {
                TextView month_and_year_in_calender_for_good_habits = getView().findViewById(R.id.tv_month_year);
                String[] splitter = month_and_year_in_calender_for_good_habits.getText().toString().split(" ");
                int month = returnMonthStringToInt(splitter[0]);
                color_the_today = calender_button_showing_shadow_9.getText().toString().concat("_").concat(String.valueOf(month)).concat("_").concat(splitter[1]);
            }
            check_if_date_is_future();
            color_the_button_under_the_calender();
        });
        calender_button_showing_shadow_10.setOnClickListener(v -> {
            clear_the_background_only();
            color_only_today();
            color_the_selected(10);
            if (getView() != null) {
                TextView month_and_year_in_calender_for_good_habits = getView().findViewById(R.id.tv_month_year);
                String[] splitter = month_and_year_in_calender_for_good_habits.getText().toString().split(" ");
                int month = returnMonthStringToInt(splitter[0]);
                color_the_today = calender_button_showing_shadow_10.getText().toString().concat("_").concat(String.valueOf(month)).concat("_").concat(splitter[1]);
            }
            check_if_date_is_future();
            color_the_button_under_the_calender();
        });
        calender_button_showing_shadow_11.setOnClickListener(v -> {
            clear_the_background_only();
            color_only_today();
            color_the_selected(11);
            if (getView() != null) {
                TextView month_and_year_in_calender_for_good_habits = getView().findViewById(R.id.tv_month_year);
                String[] splitter = month_and_year_in_calender_for_good_habits.getText().toString().split(" ");
                int month = returnMonthStringToInt(splitter[0]);
                color_the_today = calender_button_showing_shadow_11.getText().toString().concat("_").concat(String.valueOf(month)).concat("_").concat(splitter[1]);
            }
            check_if_date_is_future();
            color_the_button_under_the_calender();
        });
        calender_button_showing_shadow_12.setOnClickListener(v -> {
            clear_the_background_only();
            color_only_today();
            color_the_selected(12);
            if (getView() != null) {
                TextView month_and_year_in_calender_for_good_habits = getView().findViewById(R.id.tv_month_year);
                String[] splitter = month_and_year_in_calender_for_good_habits.getText().toString().split(" ");
                int month = returnMonthStringToInt(splitter[0]);
                color_the_today = calender_button_showing_shadow_12.getText().toString().concat("_").concat(String.valueOf(month)).concat("_").concat(splitter[1]);
            }
            check_if_date_is_future();
            color_the_button_under_the_calender();
        });
        calender_button_showing_shadow_13.setOnClickListener(v -> {
            clear_the_background_only();
            color_only_today();
            color_the_selected(13);
            if (getView() != null) {
                TextView month_and_year_in_calender_for_good_habits = getView().findViewById(R.id.tv_month_year);
                String[] splitter = month_and_year_in_calender_for_good_habits.getText().toString().split(" ");
                int month = returnMonthStringToInt(splitter[0]);
                color_the_today = calender_button_showing_shadow_13.getText().toString().concat("_").concat(String.valueOf(month)).concat("_").concat(splitter[1]);
            }
            check_if_date_is_future();
            color_the_button_under_the_calender();
        });
        calender_button_showing_shadow_14.setOnClickListener(v -> {
            clear_the_background_only();
            color_only_today();
            color_the_selected(14);
            if (getView() != null) {
                TextView month_and_year_in_calender_for_good_habits = getView().findViewById(R.id.tv_month_year);
                String[] splitter = month_and_year_in_calender_for_good_habits.getText().toString().split(" ");
                int month = returnMonthStringToInt(splitter[0]);
                color_the_today = calender_button_showing_shadow_14.getText().toString().concat("_").concat(String.valueOf(month)).concat("_").concat(splitter[1]);
            }
            check_if_date_is_future();
            color_the_button_under_the_calender();
        });
        calender_button_showing_shadow_15.setOnClickListener(v -> {
            clear_the_background_only();
            color_only_today();
            color_the_selected(15);
            if (getView() != null) {
                TextView month_and_year_in_calender_for_good_habits = getView().findViewById(R.id.tv_month_year);
                String[] splitter = month_and_year_in_calender_for_good_habits.getText().toString().split(" ");
                int month = returnMonthStringToInt(splitter[0]);
                color_the_today = calender_button_showing_shadow_15.getText().toString().concat("_").concat(String.valueOf(month)).concat("_").concat(splitter[1]);
            }
            check_if_date_is_future();
            color_the_button_under_the_calender();
        });
        calender_button_showing_shadow_16.setOnClickListener(v -> {
            clear_the_background_only();
            color_only_today();
            color_the_selected(16);
            if (getView() != null) {
                TextView month_and_year_in_calender_for_good_habits = getView().findViewById(R.id.tv_month_year);
                String[] splitter = month_and_year_in_calender_for_good_habits.getText().toString().split(" ");
                int month = returnMonthStringToInt(splitter[0]);
                color_the_today = calender_button_showing_shadow_16.getText().toString().concat("_").concat(String.valueOf(month)).concat("_").concat(splitter[1]);
            }
            check_if_date_is_future();
            color_the_button_under_the_calender();
        });
        calender_button_showing_shadow_17.setOnClickListener(v -> {
            clear_the_background_only();
            color_only_today();
            color_the_selected(17);
            if (getView() != null) {
                TextView month_and_year_in_calender_for_good_habits = getView().findViewById(R.id.tv_month_year);
                String[] splitter = month_and_year_in_calender_for_good_habits.getText().toString().split(" ");
                int month = returnMonthStringToInt(splitter[0]);
                color_the_today = calender_button_showing_shadow_17.getText().toString().concat("_").concat(String.valueOf(month)).concat("_").concat(splitter[1]);
            }
            check_if_date_is_future();
            color_the_button_under_the_calender();
        });
        calender_button_showing_shadow_18.setOnClickListener(v -> {
            clear_the_background_only();
            color_only_today();
            color_the_selected(18);
            if (getView() != null) {
                TextView month_and_year_in_calender_for_good_habits = getView().findViewById(R.id.tv_month_year);
                String[] splitter = month_and_year_in_calender_for_good_habits.getText().toString().split(" ");
                int month = returnMonthStringToInt(splitter[0]);
                color_the_today = calender_button_showing_shadow_18.getText().toString().concat("_").concat(String.valueOf(month)).concat("_").concat(splitter[1]);
            }
            check_if_date_is_future();
            color_the_button_under_the_calender();
        });
        calender_button_showing_shadow_19.setOnClickListener(v -> {
            clear_the_background_only();
            color_only_today();
            color_the_selected(19);
            if (getView() != null) {
                TextView month_and_year_in_calender_for_good_habits = getView().findViewById(R.id.tv_month_year);
                String[] splitter = month_and_year_in_calender_for_good_habits.getText().toString().split(" ");
                int month = returnMonthStringToInt(splitter[0]);
                color_the_today = calender_button_showing_shadow_19.getText().toString().concat("_").concat(String.valueOf(month)).concat("_").concat(splitter[1]);
            }
            check_if_date_is_future();
            color_the_button_under_the_calender();
        });
        calender_button_showing_shadow_20.setOnClickListener(v -> {
            clear_the_background_only();
            color_only_today();
            color_the_selected(20);
            if (getView() != null) {
                TextView month_and_year_in_calender_for_good_habits = getView().findViewById(R.id.tv_month_year);
                String[] splitter = month_and_year_in_calender_for_good_habits.getText().toString().split(" ");
                int month = returnMonthStringToInt(splitter[0]);
                color_the_today = calender_button_showing_shadow_20.getText().toString().concat("_").concat(String.valueOf(month)).concat("_").concat(splitter[1]);
            }
            check_if_date_is_future();
            color_the_button_under_the_calender();
        });
        calender_button_showing_shadow_21.setOnClickListener(v -> {
            clear_the_background_only();
            color_only_today();
            color_the_selected(21);
            if (getView() != null) {
                TextView month_and_year_in_calender_for_good_habits = getView().findViewById(R.id.tv_month_year);
                String[] splitter = month_and_year_in_calender_for_good_habits.getText().toString().split(" ");
                int month = returnMonthStringToInt(splitter[0]);
                color_the_today = calender_button_showing_shadow_21.getText().toString().concat("_").concat(String.valueOf(month)).concat("_").concat(splitter[1]);
            }
            check_if_date_is_future();
            color_the_button_under_the_calender();
        });
        calender_button_showing_shadow_22.setOnClickListener(v -> {
            clear_the_background_only();
            color_only_today();
            color_the_selected(22);
            if (getView() != null) {
                TextView month_and_year_in_calender_for_good_habits = getView().findViewById(R.id.tv_month_year);
                String[] splitter = month_and_year_in_calender_for_good_habits.getText().toString().split(" ");
                int month = returnMonthStringToInt(splitter[0]);
                color_the_today = calender_button_showing_shadow_22.getText().toString().concat("_").concat(String.valueOf(month)).concat("_").concat(splitter[1]);
            }
            check_if_date_is_future();
            color_the_button_under_the_calender();
        });
        calender_button_showing_shadow_23.setOnClickListener(v -> {
            clear_the_background_only();
            color_only_today();
            color_the_selected(23);
            if (getView() != null) {
                TextView month_and_year_in_calender_for_good_habits = getView().findViewById(R.id.tv_month_year);
                String[] splitter = month_and_year_in_calender_for_good_habits.getText().toString().split(" ");
                int month = returnMonthStringToInt(splitter[0]);
                color_the_today = calender_button_showing_shadow_23.getText().toString().concat("_").concat(String.valueOf(month)).concat("_").concat(splitter[1]);
            }
            check_if_date_is_future();
            color_the_button_under_the_calender();
        });
        calender_button_showing_shadow_24.setOnClickListener(v -> {
            clear_the_background_only();
            color_only_today();
            color_the_selected(24);
            if (getView() != null) {
                TextView month_and_year_in_calender_for_good_habits = getView().findViewById(R.id.tv_month_year);
                String[] splitter = month_and_year_in_calender_for_good_habits.getText().toString().split(" ");
                int month = returnMonthStringToInt(splitter[0]);
                color_the_today = calender_button_showing_shadow_24.getText().toString().concat("_").concat(String.valueOf(month)).concat("_").concat(splitter[1]);
            }
            check_if_date_is_future();
            color_the_button_under_the_calender();
        });
        calender_button_showing_shadow_25.setOnClickListener(v -> {
            clear_the_background_only();
            color_only_today();
            color_the_selected(25);
            if (getView() != null) {
                TextView month_and_year_in_calender_for_good_habits = getView().findViewById(R.id.tv_month_year);
                String[] splitter = month_and_year_in_calender_for_good_habits.getText().toString().split(" ");
                int month = returnMonthStringToInt(splitter[0]);
                color_the_today = calender_button_showing_shadow_25.getText().toString().concat("_").concat(String.valueOf(month)).concat("_").concat(splitter[1]);
            }
            check_if_date_is_future();
            color_the_button_under_the_calender();
        });
        calender_button_showing_shadow_26.setOnClickListener(v -> {
            clear_the_background_only();
            color_only_today();
            color_the_selected(26);
            if (getView() != null) {
                TextView month_and_year_in_calender_for_good_habits = getView().findViewById(R.id.tv_month_year);
                String[] splitter = month_and_year_in_calender_for_good_habits.getText().toString().split(" ");
                int month = returnMonthStringToInt(splitter[0]);
                color_the_today = calender_button_showing_shadow_26.getText().toString().concat("_").concat(String.valueOf(month)).concat("_").concat(splitter[1]);
            }
            check_if_date_is_future();
            color_the_button_under_the_calender();
        });
        calender_button_showing_shadow_27.setOnClickListener(v -> {
            clear_the_background_only();
            color_only_today();
            color_the_selected(27);
            if (getView() != null) {
                TextView month_and_year_in_calender_for_good_habits = getView().findViewById(R.id.tv_month_year);
                String[] splitter = month_and_year_in_calender_for_good_habits.getText().toString().split(" ");
                int month = returnMonthStringToInt(splitter[0]);
                color_the_today = calender_button_showing_shadow_27.getText().toString().concat("_").concat(String.valueOf(month)).concat("_").concat(splitter[1]);
            }
            check_if_date_is_future();
            color_the_button_under_the_calender();
        });
        calender_button_showing_shadow_28.setOnClickListener(v -> {
            clear_the_background_only();
            color_only_today();
            color_the_selected(28);
            if (getView() != null) {
                TextView month_and_year_in_calender_for_good_habits = getView().findViewById(R.id.tv_month_year);
                String[] splitter = month_and_year_in_calender_for_good_habits.getText().toString().split(" ");
                int month = returnMonthStringToInt(splitter[0]);
                color_the_today = calender_button_showing_shadow_28.getText().toString().concat("_").concat(String.valueOf(month)).concat("_").concat(splitter[1]);
            }
            check_if_date_is_future();
            color_the_button_under_the_calender();
        });
        calender_button_showing_shadow_29.setOnClickListener(v -> {
            clear_the_background_only();
            color_only_today();
            color_the_selected(29);
            if (getView() != null) {
                TextView month_and_year_in_calender_for_good_habits = getView().findViewById(R.id.tv_month_year);
                String[] splitter = month_and_year_in_calender_for_good_habits.getText().toString().split(" ");
                int month = returnMonthStringToInt(splitter[0]);
                color_the_today = calender_button_showing_shadow_29.getText().toString().concat("_").concat(String.valueOf(month)).concat("_").concat(splitter[1]);
            }
            check_if_date_is_future();
            color_the_button_under_the_calender();
        });
        calender_button_showing_shadow_30.setOnClickListener(v -> {
            clear_the_background_only();
            color_only_today();
            color_the_selected(30);
            if (getView() != null) {
                TextView month_and_year_in_calender_for_good_habits = getView().findViewById(R.id.tv_month_year);
                String[] splitter = month_and_year_in_calender_for_good_habits.getText().toString().split(" ");
                int month = returnMonthStringToInt(splitter[0]);
                color_the_today = calender_button_showing_shadow_30.getText().toString().concat("_").concat(String.valueOf(month)).concat("_").concat(splitter[1]);
            }
            check_if_date_is_future();
            color_the_button_under_the_calender();
        });
        calender_button_showing_shadow_31.setOnClickListener(v -> {
            clear_the_background_only();
            color_only_today();
            color_the_selected(31);
            if (getView() != null) {
                TextView month_and_year_in_calender_for_good_habits = getView().findViewById(R.id.tv_month_year);
                String[] splitter = month_and_year_in_calender_for_good_habits.getText().toString().split(" ");
                int month = returnMonthStringToInt(splitter[0]);
                color_the_today = calender_button_showing_shadow_31.getText().toString().concat("_").concat(String.valueOf(month)).concat("_").concat(splitter[1]);
            }
            check_if_date_is_future();
            color_the_button_under_the_calender();
        });
        calender_button_showing_shadow_32.setOnClickListener(v -> {
            clear_the_background_only();
            color_only_today();
            color_the_selected(32);
            if (getView() != null) {
                TextView month_and_year_in_calender_for_good_habits = getView().findViewById(R.id.tv_month_year);
                String[] splitter = month_and_year_in_calender_for_good_habits.getText().toString().split(" ");
                int month = returnMonthStringToInt(splitter[0]);
                color_the_today = calender_button_showing_shadow_32.getText().toString().concat("_").concat(String.valueOf(month)).concat("_").concat(splitter[1]);
            }
            check_if_date_is_future();
            color_the_button_under_the_calender();
        });
        calender_button_showing_shadow_33.setOnClickListener(v -> {
            clear_the_background_only();
            color_only_today();
            color_the_selected(33);
            if (getView() != null) {
                TextView month_and_year_in_calender_for_good_habits = getView().findViewById(R.id.tv_month_year);
                String[] splitter = month_and_year_in_calender_for_good_habits.getText().toString().split(" ");
                int month = returnMonthStringToInt(splitter[0]);
                color_the_today = calender_button_showing_shadow_33.getText().toString().concat("_").concat(String.valueOf(month)).concat("_").concat(splitter[1]);
            }
            check_if_date_is_future();
            color_the_button_under_the_calender();
        });
        calender_button_showing_shadow_34.setOnClickListener(v -> {
            clear_the_background_only();
            color_only_today();
            color_the_selected(34);
            if (getView() != null) {
                TextView month_and_year_in_calender_for_good_habits = getView().findViewById(R.id.tv_month_year);
                String[] splitter = month_and_year_in_calender_for_good_habits.getText().toString().split(" ");
                int month = returnMonthStringToInt(splitter[0]);
                color_the_today = calender_button_showing_shadow_34.getText().toString().concat("_").concat(String.valueOf(month)).concat("_").concat(splitter[1]);
            }
            check_if_date_is_future();
            color_the_button_under_the_calender();
        });
        calender_button_showing_shadow_35.setOnClickListener(v -> {
            clear_the_background_only();
            color_only_today();
            color_the_selected(35);
            if (getView() != null) {
                TextView month_and_year_in_calender_for_good_habits = getView().findViewById(R.id.tv_month_year);
                String[] splitter = month_and_year_in_calender_for_good_habits.getText().toString().split(" ");
                int month = returnMonthStringToInt(splitter[0]);
                color_the_today = calender_button_showing_shadow_35.getText().toString().concat("_").concat(String.valueOf(month)).concat("_").concat(splitter[1]);
            }
            check_if_date_is_future();
            color_the_button_under_the_calender();
        });
        calender_button_showing_shadow_36.setOnClickListener(v -> {
            clear_the_background_only();
            color_only_today();
            color_the_selected(36);
            if (getView() != null) {
                TextView month_and_year_in_calender_for_good_habits = getView().findViewById(R.id.tv_month_year);
                String[] splitter = month_and_year_in_calender_for_good_habits.getText().toString().split(" ");
                int month = returnMonthStringToInt(splitter[0]);
                color_the_today = calender_button_showing_shadow_36.getText().toString().concat("_").concat(String.valueOf(month)).concat("_").concat(splitter[1]);
            }
            check_if_date_is_future();
            color_the_button_under_the_calender();
        });
        calender_button_showing_shadow_37.setOnClickListener(v -> {
            clear_the_background_only();
            color_only_today();
            color_the_selected(37);
            if (getView() != null) {
                TextView month_and_year_in_calender_for_good_habits = getView().findViewById(R.id.tv_month_year);
                String[] splitter = month_and_year_in_calender_for_good_habits.getText().toString().split(" ");
                int month = returnMonthStringToInt(splitter[0]);
                color_the_today = calender_button_showing_shadow_37.getText().toString().concat("_").concat(String.valueOf(month)).concat("_").concat(splitter[1]);
            }
            check_if_date_is_future();
            color_the_button_under_the_calender();
        });
    }

    private void remove_the_hiding_buttons() {
        if (calender_button_showing_shadow_1.getText().toString().equals("")) {
            calender_button_showing_shadow_1.setVisibility(View.INVISIBLE);
        }
        if (calender_button_showing_shadow_2.getText().toString().equals("")) {
            calender_button_showing_shadow_2.setVisibility(View.INVISIBLE);
        }
        if (calender_button_showing_shadow_3.getText().toString().equals("")) {
            calender_button_showing_shadow_3.setVisibility(View.INVISIBLE);
        }
        if (calender_button_showing_shadow_4.getText().toString().equals("")) {
            calender_button_showing_shadow_4.setVisibility(View.INVISIBLE);
        }
        if (calender_button_showing_shadow_5.getText().toString().equals("")) {
            calender_button_showing_shadow_5.setVisibility(View.INVISIBLE);
        }
        if (calender_button_showing_shadow_6.getText().toString().equals("")) {
            calender_button_showing_shadow_6.setVisibility(View.INVISIBLE);
        }
        if (calender_button_showing_shadow_29.getText().toString().equals("")) {
            calender_button_showing_shadow_29.setVisibility(View.INVISIBLE);
        }
        if (calender_button_showing_shadow_30.getText().toString().equals("")) {
            calender_button_showing_shadow_30.setVisibility(View.INVISIBLE);
        }
        if (calender_button_showing_shadow_31.getText().toString().equals("")) {
            calender_button_showing_shadow_31.setVisibility(View.INVISIBLE);
        }
        if (calender_button_showing_shadow_32.getText().toString().equals("")) {
            calender_button_showing_shadow_32.setVisibility(View.INVISIBLE);
        }
        if (calender_button_showing_shadow_33.getText().toString().equals("")) {
            calender_button_showing_shadow_33.setVisibility(View.INVISIBLE);
        }
        if (calender_button_showing_shadow_34.getText().toString().equals("")) {
            calender_button_showing_shadow_34.setVisibility(View.INVISIBLE);
        }
        if (calender_button_showing_shadow_35.getText().toString().equals("")) {
            calender_button_showing_shadow_35.setVisibility(View.INVISIBLE);
        }
        if (calender_button_showing_shadow_36.getText().toString().equals("")) {
            calender_button_showing_shadow_36.setVisibility(View.INVISIBLE);
        }
        if (calender_button_showing_shadow_37.getText().toString().equals("")) {
            calender_button_showing_shadow_37.setVisibility(View.INVISIBLE);
        }
    }

    private void set_all_buttons_to_visible() {
        calender_button_showing_shadow_1.setVisibility(View.VISIBLE);
        calender_button_showing_shadow_2.setVisibility(View.VISIBLE);
        calender_button_showing_shadow_3.setVisibility(View.VISIBLE);
        calender_button_showing_shadow_4.setVisibility(View.VISIBLE);
        calender_button_showing_shadow_5.setVisibility(View.VISIBLE);
        calender_button_showing_shadow_6.setVisibility(View.VISIBLE);
        calender_button_showing_shadow_7.setVisibility(View.VISIBLE);
        calender_button_showing_shadow_8.setVisibility(View.VISIBLE);
        calender_button_showing_shadow_9.setVisibility(View.VISIBLE);
        calender_button_showing_shadow_10.setVisibility(View.VISIBLE);
        calender_button_showing_shadow_11.setVisibility(View.VISIBLE);
        calender_button_showing_shadow_12.setVisibility(View.VISIBLE);
        calender_button_showing_shadow_13.setVisibility(View.VISIBLE);
        calender_button_showing_shadow_14.setVisibility(View.VISIBLE);
        calender_button_showing_shadow_15.setVisibility(View.VISIBLE);
        calender_button_showing_shadow_16.setVisibility(View.VISIBLE);
        calender_button_showing_shadow_17.setVisibility(View.VISIBLE);
        calender_button_showing_shadow_17.setVisibility(View.VISIBLE);
        calender_button_showing_shadow_18.setVisibility(View.VISIBLE);
        calender_button_showing_shadow_19.setVisibility(View.VISIBLE);
        calender_button_showing_shadow_20.setVisibility(View.VISIBLE);
        calender_button_showing_shadow_21.setVisibility(View.VISIBLE);
        calender_button_showing_shadow_22.setVisibility(View.VISIBLE);
        calender_button_showing_shadow_23.setVisibility(View.VISIBLE);
        calender_button_showing_shadow_24.setVisibility(View.VISIBLE);
        calender_button_showing_shadow_25.setVisibility(View.VISIBLE);
        calender_button_showing_shadow_26.setVisibility(View.VISIBLE);
        calender_button_showing_shadow_27.setVisibility(View.VISIBLE);
        calender_button_showing_shadow_28.setVisibility(View.VISIBLE);
        calender_button_showing_shadow_29.setVisibility(View.VISIBLE);
        calender_button_showing_shadow_30.setVisibility(View.VISIBLE);
        calender_button_showing_shadow_31.setVisibility(View.VISIBLE);
        calender_button_showing_shadow_32.setVisibility(View.VISIBLE);
        calender_button_showing_shadow_33.setVisibility(View.VISIBLE);
        calender_button_showing_shadow_34.setVisibility(View.VISIBLE);
        calender_button_showing_shadow_35.setVisibility(View.VISIBLE);
        calender_button_showing_shadow_36.setVisibility(View.VISIBLE);
        calender_button_showing_shadow_37.setVisibility(View.VISIBLE);
    }

    private int return_which_day_is_linked_to_calender(int which) {
        if (calender_button_showing_shadow_1.getText().toString().equals(String.valueOf(which))) {
            return 1;
        } else if (calender_button_showing_shadow_2.getText().toString().equals(String.valueOf(which))) {
            return 2;
        } else if (calender_button_showing_shadow_3.getText().toString().equals(String.valueOf(which))) {
            return 3;
        } else if (calender_button_showing_shadow_4.getText().toString().equals(String.valueOf(which))) {
            return 4;
        } else if (calender_button_showing_shadow_5.getText().toString().equals(String.valueOf(which))) {
            return 5;
        } else if (calender_button_showing_shadow_6.getText().toString().equals(String.valueOf(which))) {
            return 6;
        } else if (calender_button_showing_shadow_7.getText().toString().equals(String.valueOf(which))) {
            return 7;
        } else if (calender_button_showing_shadow_8.getText().toString().equals(String.valueOf(which))) {
            return 8;
        } else if (calender_button_showing_shadow_9.getText().toString().equals(String.valueOf(which))) {
            return 9;
        } else if (calender_button_showing_shadow_10.getText().toString().equals(String.valueOf(which))) {
            return 10;
        } else if (calender_button_showing_shadow_11.getText().toString().equals(String.valueOf(which))) {
            return 11;
        } else if (calender_button_showing_shadow_12.getText().toString().equals(String.valueOf(which))) {
            return 12;
        } else if (calender_button_showing_shadow_13.getText().toString().equals(String.valueOf(which))) {
            return 13;
        } else if (calender_button_showing_shadow_14.getText().toString().equals(String.valueOf(which))) {
            return 14;
        } else if (calender_button_showing_shadow_15.getText().toString().equals(String.valueOf(which))) {
            return 15;
        } else if (calender_button_showing_shadow_16.getText().toString().equals(String.valueOf(which))) {
            return 16;
        } else if (calender_button_showing_shadow_17.getText().toString().equals(String.valueOf(which))) {
            return 17;
        } else if (calender_button_showing_shadow_18.getText().toString().equals(String.valueOf(which))) {
            return 18;
        } else if (calender_button_showing_shadow_19.getText().toString().equals(String.valueOf(which))) {
            return 19;
        } else if (calender_button_showing_shadow_20.getText().toString().equals(String.valueOf(which))) {
            return 20;
        } else if (calender_button_showing_shadow_21.getText().toString().equals(String.valueOf(which))) {
            return 21;
        } else if (calender_button_showing_shadow_22.getText().toString().equals(String.valueOf(which))) {
            return 22;
        } else if (calender_button_showing_shadow_23.getText().toString().equals(String.valueOf(which))) {
            return 23;
        } else if (calender_button_showing_shadow_24.getText().toString().equals(String.valueOf(which))) {
            return 24;
        } else if (calender_button_showing_shadow_25.getText().toString().equals(String.valueOf(which))) {
            return 25;
        } else if (calender_button_showing_shadow_26.getText().toString().equals(String.valueOf(which))) {
            return 26;
        } else if (calender_button_showing_shadow_27.getText().toString().equals(String.valueOf(which))) {
            return 27;
        } else if (calender_button_showing_shadow_28.getText().toString().equals(String.valueOf(which))) {
            return 28;
        } else if (calender_button_showing_shadow_29.getText().toString().equals(String.valueOf(which))) {
            return 29;
        } else if (calender_button_showing_shadow_30.getText().toString().equals(String.valueOf(which))) {
            return 30;
        } else if (calender_button_showing_shadow_31.getText().toString().equals(String.valueOf(which))) {
            return 31;
        } else if (calender_button_showing_shadow_32.getText().toString().equals(String.valueOf(which))) {
            return 32;
        } else if (calender_button_showing_shadow_33.getText().toString().equals(String.valueOf(which))) {
            return 33;
        } else if (calender_button_showing_shadow_34.getText().toString().equals(String.valueOf(which))) {
            return 34;
        } else if (calender_button_showing_shadow_35.getText().toString().equals(String.valueOf(which))) {
            return 35;
        } else if (calender_button_showing_shadow_36.getText().toString().equals(String.valueOf(which))) {
            return 36;
        } else {
            return 37;
        }
    }

    private void color_today() {
        if (getView() != null) {
            TextView month_and_year_in_calender_for_good_habits = getView().findViewById(R.id.tv_month_year);
            String[] text_to_split = month_and_year_in_calender_for_good_habits.getText().toString().split(" ");
            int year_from_text = Integer.parseInt(text_to_split[1]);
            int month_from_text = returnMonthStringToInt(text_to_split[0]);
            String[] saved_text_split = color_the_today.split("_");
            int year = Integer.parseInt(saved_text_split[2]);
            int month = Integer.parseInt(saved_text_split[1]);
            int day = Integer.parseInt(saved_text_split[0]);
            if ((year_from_text == year) && (month_from_text == month)) {
                if (!calender_button_showing_shadow_1.getText().toString().equals("") && Integer.parseInt(calender_button_showing_shadow_1.getText().toString()) == day) {
                    color_the_selected(1);
                } else if (!calender_button_showing_shadow_2.getText().toString().equals("") && Integer.parseInt(calender_button_showing_shadow_2.getText().toString()) == day) {
                    color_the_selected(2);
                } else if (!calender_button_showing_shadow_3.getText().toString().equals("") && Integer.parseInt(calender_button_showing_shadow_3.getText().toString()) == day) {
                    color_the_selected(3);
                } else if (!calender_button_showing_shadow_4.getText().toString().equals("") && Integer.parseInt(calender_button_showing_shadow_4.getText().toString()) == day) {
                    color_the_selected(4);
                } else if (!calender_button_showing_shadow_5.getText().toString().equals("") && Integer.parseInt(calender_button_showing_shadow_5.getText().toString()) == day) {
                    color_the_selected(5);
                } else if (!calender_button_showing_shadow_6.getText().toString().equals("") && Integer.parseInt(calender_button_showing_shadow_6.getText().toString()) == day) {
                    color_the_selected(6);
                } else if (!calender_button_showing_shadow_7.getText().toString().equals("") && Integer.parseInt(calender_button_showing_shadow_7.getText().toString()) == day) {
                    color_the_selected(7);
                } else if (!calender_button_showing_shadow_8.getText().toString().equals("") && Integer.parseInt(calender_button_showing_shadow_8.getText().toString()) == day) {
                    color_the_selected(8);
                } else if (!calender_button_showing_shadow_9.getText().toString().equals("") && Integer.parseInt(calender_button_showing_shadow_9.getText().toString()) == day) {
                    color_the_selected(9);
                } else if (!calender_button_showing_shadow_10.getText().toString().equals("") && Integer.parseInt(calender_button_showing_shadow_10.getText().toString()) == day) {
                    color_the_selected(10);
                } else if (!calender_button_showing_shadow_11.getText().toString().equals("") && Integer.parseInt(calender_button_showing_shadow_11.getText().toString()) == day) {
                    color_the_selected(11);
                } else if (!calender_button_showing_shadow_12.getText().toString().equals("") && Integer.parseInt(calender_button_showing_shadow_12.getText().toString()) == day) {
                    color_the_selected(12);
                } else if (!calender_button_showing_shadow_13.getText().toString().equals("") && Integer.parseInt(calender_button_showing_shadow_13.getText().toString()) == day) {
                    color_the_selected(13);
                } else if (!calender_button_showing_shadow_14.getText().toString().equals("") && Integer.parseInt(calender_button_showing_shadow_14.getText().toString()) == day) {
                    color_the_selected(14);
                } else if (!calender_button_showing_shadow_15.getText().toString().equals("") && Integer.parseInt(calender_button_showing_shadow_15.getText().toString()) == day) {
                    color_the_selected(15);
                } else if (!calender_button_showing_shadow_16.getText().toString().equals("") && Integer.parseInt(calender_button_showing_shadow_16.getText().toString()) == day) {
                    color_the_selected(16);
                } else if (!calender_button_showing_shadow_17.getText().toString().equals("") && Integer.parseInt(calender_button_showing_shadow_17.getText().toString()) == day) {
                    color_the_selected(17);
                } else if (!calender_button_showing_shadow_18.getText().toString().equals("") && Integer.parseInt(calender_button_showing_shadow_18.getText().toString()) == day) {
                    color_the_selected(18);
                } else if (!calender_button_showing_shadow_19.getText().toString().equals("") && Integer.parseInt(calender_button_showing_shadow_19.getText().toString()) == day) {
                    color_the_selected(19);
                } else if (!calender_button_showing_shadow_20.getText().toString().equals("") && Integer.parseInt(calender_button_showing_shadow_20.getText().toString()) == day) {
                    color_the_selected(20);
                } else if (!calender_button_showing_shadow_21.getText().toString().equals("") && Integer.parseInt(calender_button_showing_shadow_21.getText().toString()) == day) {
                    color_the_selected(21);
                } else if (!calender_button_showing_shadow_22.getText().toString().equals("") && Integer.parseInt(calender_button_showing_shadow_22.getText().toString()) == day) {
                    color_the_selected(22);
                } else if (!calender_button_showing_shadow_23.getText().toString().equals("") && Integer.parseInt(calender_button_showing_shadow_23.getText().toString()) == day) {
                    color_the_selected(23);
                } else if (!calender_button_showing_shadow_24.getText().toString().equals("") && Integer.parseInt(calender_button_showing_shadow_24.getText().toString()) == day) {
                    color_the_selected(24);
                } else if (!calender_button_showing_shadow_25.getText().toString().equals("") && Integer.parseInt(calender_button_showing_shadow_25.getText().toString()) == day) {
                    color_the_selected(25);
                } else if (!calender_button_showing_shadow_26.getText().toString().equals("") && Integer.parseInt(calender_button_showing_shadow_26.getText().toString()) == day) {
                    color_the_selected(26);
                } else if (!calender_button_showing_shadow_27.getText().toString().equals("") && Integer.parseInt(calender_button_showing_shadow_27.getText().toString()) == day) {
                    color_the_selected(27);
                } else if (!calender_button_showing_shadow_28.getText().toString().equals("") && Integer.parseInt(calender_button_showing_shadow_28.getText().toString()) == day) {
                    color_the_selected(28);
                } else if (!calender_button_showing_shadow_29.getText().toString().equals("") && Integer.parseInt(calender_button_showing_shadow_29.getText().toString()) == day) {
                    color_the_selected(29);
                } else if (!calender_button_showing_shadow_30.getText().toString().equals("") && Integer.parseInt(calender_button_showing_shadow_30.getText().toString()) == day) {
                    color_the_selected(30);
                } else if (!calender_button_showing_shadow_31.getText().toString().equals("") && Integer.parseInt(calender_button_showing_shadow_31.getText().toString()) == day) {
                    color_the_selected(31);
                } else if (!calender_button_showing_shadow_32.getText().toString().equals("") && Integer.parseInt(calender_button_showing_shadow_32.getText().toString()) == day) {
                    color_the_selected(32);
                } else if (!calender_button_showing_shadow_33.getText().toString().equals("") && Integer.parseInt(calender_button_showing_shadow_33.getText().toString()) == day) {
                    color_the_selected(33);
                } else if (!calender_button_showing_shadow_34.getText().toString().equals("") && Integer.parseInt(calender_button_showing_shadow_34.getText().toString()) == day) {
                    color_the_selected(34);
                } else if (!calender_button_showing_shadow_35.getText().toString().equals("") && Integer.parseInt(calender_button_showing_shadow_35.getText().toString()) == day) {
                    color_the_selected(35);
                } else if (!calender_button_showing_shadow_36.getText().toString().equals("") && Integer.parseInt(calender_button_showing_shadow_36.getText().toString()) == day) {
                    color_the_selected(36);
                } else if (!calender_button_showing_shadow_37.getText().toString().equals("") && Integer.parseInt(calender_button_showing_shadow_37.getText().toString()) == day) {
                    color_the_selected(37);
                }
            }
        }
    }

    private void color_the_calender() {
        if (getView() != null) {
            TextView month_and_year_in_calender_for_good_habits = getView().findViewById(R.id.tv_month_year);
            String very_bad_color = return_the_color_of_mood(1);
            String bad_color = return_the_color_of_mood(2);
            String ok_color = return_the_color_of_mood(3);
            String good_color = return_the_color_of_mood(4);
            String very_good_color = return_the_color_of_mood(5);
            String no_mood_color = return_the_color_of_mood(0);
            colors = new String[returnLastDayOfMonth() + 1];
            Calendar calendar = Calendar.getInstance();
            String[] split_month_and_year = month_and_year_in_calender_for_good_habits.getText().toString().split(" ");
            int year = Integer.parseInt(split_month_and_year[1]);
            int month = returnMonthStringToInt(split_month_and_year[0]);
            if (!check_past_now_or_future().equals("future")) {
                for (int i = 1; i <= returnLastDayOfMonth(); i++) {
                    calendar.set(year, month, i);
                    long time_in_milli = calendar.getTimeInMillis();
                    if (Simplify_the_time.return_time_in_midnight(time_in_milli) >= Simplify_the_time.return_time_in_midnight(start_date)) {
                        if (Simplify_the_time.return_time_in_midnight(time_in_milli) <= Simplify_the_time.return_time_in_midnight(System.currentTimeMillis())) {
                            if (return_color_of_days(time_in_milli) == 0) {
                                colors[i] = no_mood_color;
                            } else if (return_color_of_days(time_in_milli) == 1) {
                                colors[i] = very_bad_color;
                            } else if (return_color_of_days(time_in_milli) == 2) {
                                colors[i] = bad_color;
                            } else if (return_color_of_days(time_in_milli) == 3) {
                                colors[i] = ok_color;
                            } else if (return_color_of_days(time_in_milli) == 4) {
                                colors[i] = good_color;
                            } else if (return_color_of_days(time_in_milli) == 5) {
                                colors[i] = very_good_color;
                            }
                        } else {
                            colors[i] = no_mood_color;
                        }
                    } else {
                        colors[i] = no_mood_color;
                    }
                }
            } else {
                for (int i = 1; i <= returnLastDayOfMonth(); i++) {
                    colors[i] = no_mood_color;
                }
            }
            for (int i = 1; i <= returnLastDayOfMonth(); i++) {
                if (return_which_day_is_linked_to_calender(i) == 1) {
                    if (calender_button_showing_shadow_1.getCurrentTextColor() != Color.WHITE) {
                        calender_button_showing_shadow_1.setTextColor(Color.parseColor(colors[i]));
                    }
                } else if (return_which_day_is_linked_to_calender(i) == 2) {
                    if (calender_button_showing_shadow_2.getCurrentTextColor() != Color.WHITE) {
                        calender_button_showing_shadow_2.setTextColor(Color.parseColor(colors[i]));
                    }
                } else if (return_which_day_is_linked_to_calender(i) == 3) {
                    if (calender_button_showing_shadow_3.getCurrentTextColor() != Color.WHITE) {
                        calender_button_showing_shadow_3.setTextColor(Color.parseColor(colors[i]));
                    }
                } else if (return_which_day_is_linked_to_calender(i) == 4) {
                    if (calender_button_showing_shadow_4.getCurrentTextColor() != Color.WHITE) {
                        calender_button_showing_shadow_4.setTextColor(Color.parseColor(colors[i]));
                    }
                } else if (return_which_day_is_linked_to_calender(i) == 5) {
                    if (calender_button_showing_shadow_5.getCurrentTextColor() != Color.WHITE) {
                        calender_button_showing_shadow_5.setTextColor(Color.parseColor(colors[i]));
                    }
                } else if (return_which_day_is_linked_to_calender(i) == 6) {
                    if (calender_button_showing_shadow_6.getCurrentTextColor() != Color.WHITE) {
                        calender_button_showing_shadow_6.setTextColor(Color.parseColor(colors[i]));
                    }
                } else if (return_which_day_is_linked_to_calender(i) == 7) {
                    if (calender_button_showing_shadow_7.getCurrentTextColor() != Color.WHITE) {
                        calender_button_showing_shadow_7.setTextColor(Color.parseColor(colors[i]));
                    }
                } else if (return_which_day_is_linked_to_calender(i) == 8) {
                    if (calender_button_showing_shadow_8.getCurrentTextColor() != Color.WHITE) {
                        calender_button_showing_shadow_8.setTextColor(Color.parseColor(colors[i]));
                    }
                } else if (return_which_day_is_linked_to_calender(i) == 9) {
                    if (calender_button_showing_shadow_9.getCurrentTextColor() != Color.WHITE) {
                        calender_button_showing_shadow_9.setTextColor(Color.parseColor(colors[i]));
                    }
                } else if (return_which_day_is_linked_to_calender(i) == 10) {
                    if (calender_button_showing_shadow_10.getCurrentTextColor() != Color.WHITE) {
                        calender_button_showing_shadow_10.setTextColor(Color.parseColor(colors[i]));
                    }
                } else if (return_which_day_is_linked_to_calender(i) == 11) {
                    if (calender_button_showing_shadow_11.getCurrentTextColor() != Color.WHITE) {
                        calender_button_showing_shadow_11.setTextColor(Color.parseColor(colors[i]));
                    }
                } else if (return_which_day_is_linked_to_calender(i) == 12) {
                    if (calender_button_showing_shadow_12.getCurrentTextColor() != Color.WHITE) {
                        calender_button_showing_shadow_12.setTextColor(Color.parseColor(colors[i]));
                    }
                } else if (return_which_day_is_linked_to_calender(i) == 13) {
                    if (calender_button_showing_shadow_13.getCurrentTextColor() != Color.WHITE) {
                        calender_button_showing_shadow_13.setTextColor(Color.parseColor(colors[i]));
                    }
                } else if (return_which_day_is_linked_to_calender(i) == 14) {
                    if (calender_button_showing_shadow_14.getCurrentTextColor() != Color.WHITE) {
                        calender_button_showing_shadow_14.setTextColor(Color.parseColor(colors[i]));
                    }
                } else if (return_which_day_is_linked_to_calender(i) == 15) {
                    if (calender_button_showing_shadow_15.getCurrentTextColor() != Color.WHITE) {
                        calender_button_showing_shadow_15.setTextColor(Color.parseColor(colors[i]));
                    }
                } else if (return_which_day_is_linked_to_calender(i) == 16) {
                    if (calender_button_showing_shadow_16.getCurrentTextColor() != Color.WHITE) {
                        calender_button_showing_shadow_16.setTextColor(Color.parseColor(colors[i]));
                    }
                } else if (return_which_day_is_linked_to_calender(i) == 17) {
                    if (calender_button_showing_shadow_17.getCurrentTextColor() != Color.WHITE) {
                        calender_button_showing_shadow_17.setTextColor(Color.parseColor(colors[i]));
                    }
                } else if (return_which_day_is_linked_to_calender(i) == 18) {
                    if (calender_button_showing_shadow_18.getCurrentTextColor() != Color.WHITE) {
                        calender_button_showing_shadow_18.setTextColor(Color.parseColor(colors[i]));
                    }
                } else if (return_which_day_is_linked_to_calender(i) == 19) {
                    if (calender_button_showing_shadow_19.getCurrentTextColor() != Color.WHITE) {
                        calender_button_showing_shadow_19.setTextColor(Color.parseColor(colors[i]));
                    }
                } else if (return_which_day_is_linked_to_calender(i) == 20) {
                    if (calender_button_showing_shadow_20.getCurrentTextColor() != Color.WHITE) {
                        calender_button_showing_shadow_20.setTextColor(Color.parseColor(colors[i]));
                    }
                } else if (return_which_day_is_linked_to_calender(i) == 21) {
                    if (calender_button_showing_shadow_21.getCurrentTextColor() != Color.WHITE) {
                        calender_button_showing_shadow_21.setTextColor(Color.parseColor(colors[i]));
                    }
                } else if (return_which_day_is_linked_to_calender(i) == 22) {
                    if (calender_button_showing_shadow_22.getCurrentTextColor() != Color.WHITE) {
                        calender_button_showing_shadow_22.setTextColor(Color.parseColor(colors[i]));
                    }
                } else if (return_which_day_is_linked_to_calender(i) == 23) {
                    if (calender_button_showing_shadow_23.getCurrentTextColor() != Color.WHITE) {
                        calender_button_showing_shadow_23.setTextColor(Color.parseColor(colors[i]));
                    }
                } else if (return_which_day_is_linked_to_calender(i) == 24) {
                    if (calender_button_showing_shadow_24.getCurrentTextColor() != Color.WHITE) {
                        calender_button_showing_shadow_24.setTextColor(Color.parseColor(colors[i]));
                    }
                } else if (return_which_day_is_linked_to_calender(i) == 25) {
                    if (calender_button_showing_shadow_25.getCurrentTextColor() != Color.WHITE) {
                        calender_button_showing_shadow_25.setTextColor(Color.parseColor(colors[i]));
                    }
                } else if (return_which_day_is_linked_to_calender(i) == 26) {
                    if (calender_button_showing_shadow_26.getCurrentTextColor() != Color.WHITE) {
                        calender_button_showing_shadow_26.setTextColor(Color.parseColor(colors[i]));
                    }
                } else if (return_which_day_is_linked_to_calender(i) == 27) {
                    if (calender_button_showing_shadow_27.getCurrentTextColor() != Color.WHITE) {
                        calender_button_showing_shadow_27.setTextColor(Color.parseColor(colors[i]));
                    }
                } else if (return_which_day_is_linked_to_calender(i) == 28) {
                    if (calender_button_showing_shadow_28.getCurrentTextColor() != Color.WHITE) {
                        calender_button_showing_shadow_28.setTextColor(Color.parseColor(colors[i]));
                    }
                } else if (return_which_day_is_linked_to_calender(i) == 29) {
                    if (calender_button_showing_shadow_29.getCurrentTextColor() != Color.WHITE) {
                        calender_button_showing_shadow_29.setTextColor(Color.parseColor(colors[i]));
                    }
                } else if (return_which_day_is_linked_to_calender(i) == 30) {
                    if (calender_button_showing_shadow_30.getCurrentTextColor() != Color.WHITE) {
                        calender_button_showing_shadow_30.setTextColor(Color.parseColor(colors[i]));
                    }
                } else if (return_which_day_is_linked_to_calender(i) == 31) {
                    if (calender_button_showing_shadow_31.getCurrentTextColor() != Color.WHITE) {
                        calender_button_showing_shadow_31.setTextColor(Color.parseColor(colors[i]));
                    }
                } else if (return_which_day_is_linked_to_calender(i) == 32) {
                    if (calender_button_showing_shadow_32.getCurrentTextColor() != Color.WHITE) {
                        calender_button_showing_shadow_32.setTextColor(Color.parseColor(colors[i]));
                    }
                } else if (return_which_day_is_linked_to_calender(i) == 33) {
                    if (calender_button_showing_shadow_33.getCurrentTextColor() != Color.WHITE) {
                        calender_button_showing_shadow_33.setTextColor(Color.parseColor(colors[i]));
                    }
                } else if (return_which_day_is_linked_to_calender(i) == 34) {
                    if (calender_button_showing_shadow_34.getCurrentTextColor() != Color.WHITE) {
                        calender_button_showing_shadow_34.setTextColor(Color.parseColor(colors[i]));
                    }
                } else if (return_which_day_is_linked_to_calender(i) == 35) {
                    if (calender_button_showing_shadow_35.getCurrentTextColor() != Color.WHITE) {
                        calender_button_showing_shadow_35.setTextColor(Color.parseColor(colors[i]));
                    }
                } else if (return_which_day_is_linked_to_calender(i) == 36) {
                    if (calender_button_showing_shadow_36.getCurrentTextColor() != Color.WHITE) {
                        calender_button_showing_shadow_36.setTextColor(Color.parseColor(colors[i]));
                    }
                } else {
                    if (calender_button_showing_shadow_37.getCurrentTextColor() != Color.WHITE) {
                        calender_button_showing_shadow_37.setTextColor(Color.parseColor(colors[i]));
                    }
                }
            }
        }
    }

    private void check_if_date_is_future() {
        if (getView() != null) {
            TextView month_and_year_in_calender_for_good_habits = getView().findViewById(R.id.tv_month_year);
            String[] split_for_day_month_year = color_the_today.split("_");
            String[] month_and_year = month_and_year_in_calender_for_good_habits.getText().toString().split(" ");
            int calender_day = Integer.parseInt(split_for_day_month_year[0]);
            int calender_month = returnMonthStringToInt(month_and_year[0]);
            int calender_year = Integer.parseInt(month_and_year[1]);
            Calendar calendar = Calendar.getInstance();
            int real_year = calendar.get(Calendar.YEAR);
            int real_month = calendar.get(Calendar.MONTH);
            int real_day = calendar.get(Calendar.DAY_OF_MONTH);
            Calendar calendar_new = Calendar.getInstance();
            calendar_new.set(calender_year, calender_month, calender_day);
            String[] splitter_temp_from_text = month_and_year_in_calender_for_good_habits.getText().toString().split(" ");
            String month_from_text = String.valueOf(returnMonthStringToInt(splitter_temp_from_text[0]));
            String year_from_text = String.valueOf(splitter_temp_from_text[1]);
            String[] splitter_for_colored_value = color_the_today.split("_");
            if (calendar_new.getTimeInMillis() < Simplify_the_time.return_time_in_midnight(start_date)) {
                hide_or_un_hide_the_button(0);
            } else {
                if (calender_year > real_year) {
                    hide_or_un_hide_the_button(0);
                } else if (calender_year == real_year) {
                    if (calender_month > real_month) {
                        hide_or_un_hide_the_button(0);
                    } else if (calender_month == real_month) {
                        if (calender_day >= real_day) {
                            hide_or_un_hide_the_button(0);
                        } else {
                            if (month_from_text.equals(splitter_for_colored_value[1]) && year_from_text.equals(splitter_for_colored_value[2])) {
                                hide_or_un_hide_the_button(1);
                            } else {
                                hide_or_un_hide_the_button(0);
                            }
                        }
                    } else {
                        if (month_from_text.equals(splitter_for_colored_value[1]) && year_from_text.equals(splitter_for_colored_value[2])) {
                            hide_or_un_hide_the_button(1);
                        } else {
                            hide_or_un_hide_the_button(0);
                        }
                    }
                } else {
                    if (month_from_text.equals(splitter_for_colored_value[1]) && year_from_text.equals(splitter_for_colored_value[2])) {
                        hide_or_un_hide_the_button(1);
                    } else {
                        hide_or_un_hide_the_button(0);
                    }
                }
            }
        }
    }

    private void hide_or_un_hide_the_button(int which) {
        if (getView() != null && getContext() != null) {
            Button very_bad_mood_button_in_habits = getView().findViewById(R.id.bt_very_bad_mood_edit);
            Button bad_mood_button_in_habits = getView().findViewById(R.id.bt_bad_mood_edit);
            Button ok_mood_button_in_habits = getView().findViewById(R.id.bt_ok_mood_edit);
            Button good_mood_button_in_habits = getView().findViewById(R.id.bt_good_mood_edit);
            Button very_good_mood_button_in_habits = getView().findViewById(R.id.bt_very_good_mood_edit);
            View very_bad_mood_in_habits = getView().findViewById(R.id.view_very_bad_mood_edit);
            View bad_mood_in_habits = getView().findViewById(R.id.view_bad_mood_edit);
            View ok_mood_in_habits = getView().findViewById(R.id.view_ok_mood_edit);
            View good_mood_in_habits = getView().findViewById(R.id.view_good_mood_edit);
            View very_good_mood_in_habits = getView().findViewById(R.id.view_very_good_mood_edit);
            View very_bad_mood_shade_in_habits = getView().findViewById(R.id.view_very_bad_mood_edit_background);
            View bad_mood_shade_in_habits = getView().findViewById(R.id.view_bad_mood_edit_background);
            View ok_mood_shade_in_habits = getView().findViewById(R.id.view_ok_mood_edit_background);
            View good_mood_shade_in_habits = getView().findViewById(R.id.view_good_mood_edit_background);
            View very_good_mood_shade_in_habits = getView().findViewById(R.id.view_very_good_mood_edit_background);
            View very_bad_mood_check_mark_in_mood = getView().findViewById(R.id.view_very_bad_mood_edit_check);
            View bad_mood_check_mark_in_mood = getView().findViewById(R.id.view_bad_mood_edit_check);
            View ok_mood_check_mark_in_mood = getView().findViewById(R.id.view_ok_mood_edit_check);
            View good_mood_check_mark_in_mood = getView().findViewById(R.id.view_good_mood_edit_check);
            View very_good_mood_check_mark_in_mood = getView().findViewById(R.id.view_very_good_mood_edit_check);
            TextView text_asking_did_you_relapse_in_share = getView().findViewById(R.id.tv_edit_mood);
            if (which == 0) {
                very_bad_mood_button_in_habits.setVisibility(View.GONE);
                bad_mood_button_in_habits.setVisibility(View.GONE);
                ok_mood_button_in_habits.setVisibility(View.GONE);
                good_mood_button_in_habits.setVisibility(View.GONE);
                very_good_mood_button_in_habits.setVisibility(View.GONE);
                text_asking_did_you_relapse_in_share.setVisibility(View.GONE);
                very_bad_mood_in_habits.setVisibility(View.GONE);
                bad_mood_in_habits.setVisibility(View.GONE);
                ok_mood_in_habits.setVisibility(View.GONE);
                good_mood_in_habits.setVisibility(View.GONE);
                very_good_mood_in_habits.setVisibility(View.GONE);
                very_bad_mood_shade_in_habits.setVisibility(View.GONE);
                bad_mood_shade_in_habits.setVisibility(View.GONE);
                ok_mood_shade_in_habits.setVisibility(View.GONE);
                good_mood_shade_in_habits.setVisibility(View.GONE);
                very_good_mood_shade_in_habits.setVisibility(View.GONE);
                very_bad_mood_check_mark_in_mood.setVisibility(View.GONE);
                bad_mood_check_mark_in_mood.setVisibility(View.GONE);
                ok_mood_check_mark_in_mood.setVisibility(View.GONE);
                good_mood_check_mark_in_mood.setVisibility(View.GONE);
                very_good_mood_check_mark_in_mood.setVisibility(View.GONE);
                ConstraintLayout constraintLayout = getView().findViewById(R.id.lc_calendar);
                ConstraintSet constraintSet = new ConstraintSet();
                constraintSet.clone(constraintLayout);
                constraintSet.applyTo(constraintLayout);
            } else {
                very_bad_mood_button_in_habits.setVisibility(View.VISIBLE);
                bad_mood_button_in_habits.setVisibility(View.VISIBLE);
                ok_mood_button_in_habits.setVisibility(View.VISIBLE);
                good_mood_button_in_habits.setVisibility(View.VISIBLE);
                very_good_mood_button_in_habits.setVisibility(View.VISIBLE);
                text_asking_did_you_relapse_in_share.setVisibility(View.VISIBLE);
                very_bad_mood_in_habits.setVisibility(View.VISIBLE);
                bad_mood_in_habits.setVisibility(View.VISIBLE);
                ok_mood_in_habits.setVisibility(View.VISIBLE);
                good_mood_in_habits.setVisibility(View.VISIBLE);
                very_good_mood_in_habits.setVisibility(View.VISIBLE);
                text_asking_did_you_relapse_in_share.setVisibility(View.VISIBLE);
                ConstraintLayout constraintLayout = getView().findViewById(R.id.lc_calendar);
                ConstraintSet constraintSet = new ConstraintSet();
                constraintSet.clone(constraintLayout);
                constraintSet.connect(text_asking_did_you_relapse_in_share.getId(), ConstraintSet.TOP, constraintLayout.getId(), ConstraintSet.TOP, (int) convertDpToPixel(return_the_length_of_stat() + 28, getContext()));
                constraintSet.applyTo(constraintLayout);
            }
        }
    }

    public static float convertDpToPixel(float dp, Context context) {
        return dp * ((float) context.getResources().getDisplayMetrics().densityDpi / DisplayMetrics.DENSITY_DEFAULT);
    }

    private int return_the_length_of_stat() {
        if (calender_button_showing_shadow_36.getText().toString().equals("")) {
            if (calender_button_showing_shadow_29.getText().toString().equals("")) {
                return 240;
            } else {
                return 280;
            }
        } else {
            return 320;
        }
    }



    private void color_only_today() {
        if (getView() != null) {
            TextView month_and_year_in_calender_for_good_habits = getView().findViewById(R.id.tv_month_year);
            String[] split_the_month_and_year = month_and_year_in_calender_for_good_habits.getText().toString().split(" ");
            String[] split_the_color = color_the_today.split("_");
            int old_day = Integer.parseInt(split_the_color[0]);
            int old_month = Integer.parseInt(split_the_color[1]);
            int old_year = Integer.parseInt(split_the_color[2]);
            int new_month = returnMonthStringToInt(split_the_month_and_year[0]);
            int new_year = Integer.parseInt(split_the_month_and_year[1]);
            if (old_month == new_month && old_year == new_year) {
                if (colors[old_day] != null) {
                    if (calender_button_showing_shadow_1.getText().toString().equals(String.valueOf(old_day))) {
                        calender_button_showing_shadow_1.setTextColor(Color.parseColor(colors[old_day]));
                    } else if (calender_button_showing_shadow_2.getText().toString().equals(String.valueOf(old_day))) {
                        calender_button_showing_shadow_2.setTextColor(Color.parseColor(colors[old_day]));
                    } else if (calender_button_showing_shadow_3.getText().toString().equals(String.valueOf(old_day))) {
                        calender_button_showing_shadow_3.setTextColor(Color.parseColor(colors[old_day]));
                    } else if (calender_button_showing_shadow_4.getText().toString().equals(String.valueOf(old_day))) {
                        calender_button_showing_shadow_4.setTextColor(Color.parseColor(colors[old_day]));
                    } else if (calender_button_showing_shadow_5.getText().toString().equals(String.valueOf(old_day))) {
                        calender_button_showing_shadow_5.setTextColor(Color.parseColor(colors[old_day]));
                    } else if (calender_button_showing_shadow_6.getText().toString().equals(String.valueOf(old_day))) {
                        calender_button_showing_shadow_6.setTextColor(Color.parseColor(colors[old_day]));
                    } else if (calender_button_showing_shadow_7.getText().toString().equals(String.valueOf(old_day))) {
                        calender_button_showing_shadow_7.setTextColor(Color.parseColor(colors[old_day]));
                    } else if (calender_button_showing_shadow_8.getText().toString().equals(String.valueOf(old_day))) {
                        calender_button_showing_shadow_8.setTextColor(Color.parseColor(colors[old_day]));
                    } else if (calender_button_showing_shadow_9.getText().toString().equals(String.valueOf(old_day))) {
                        calender_button_showing_shadow_9.setTextColor(Color.parseColor(colors[old_day]));
                    } else if (calender_button_showing_shadow_10.getText().toString().equals(String.valueOf(old_day))) {
                        calender_button_showing_shadow_10.setTextColor(Color.parseColor(colors[old_day]));
                    } else if (calender_button_showing_shadow_11.getText().toString().equals(String.valueOf(old_day))) {
                        calender_button_showing_shadow_11.setTextColor(Color.parseColor(colors[old_day]));
                    } else if (calender_button_showing_shadow_12.getText().toString().equals(String.valueOf(old_day))) {
                        calender_button_showing_shadow_12.setTextColor(Color.parseColor(colors[old_day]));
                    } else if (calender_button_showing_shadow_13.getText().toString().equals(String.valueOf(old_day))) {
                        calender_button_showing_shadow_13.setTextColor(Color.parseColor(colors[old_day]));
                    } else if (calender_button_showing_shadow_14.getText().toString().equals(String.valueOf(old_day))) {
                        calender_button_showing_shadow_14.setTextColor(Color.parseColor(colors[old_day]));
                    } else if (calender_button_showing_shadow_15.getText().toString().equals(String.valueOf(old_day))) {
                        calender_button_showing_shadow_15.setTextColor(Color.parseColor(colors[old_day]));
                    } else if (calender_button_showing_shadow_16.getText().toString().equals(String.valueOf(old_day))) {
                        calender_button_showing_shadow_16.setTextColor(Color.parseColor(colors[old_day]));
                    } else if (calender_button_showing_shadow_17.getText().toString().equals(String.valueOf(old_day))) {
                        calender_button_showing_shadow_17.setTextColor(Color.parseColor(colors[old_day]));
                    } else if (calender_button_showing_shadow_18.getText().toString().equals(String.valueOf(old_day))) {
                        calender_button_showing_shadow_18.setTextColor(Color.parseColor(colors[old_day]));
                    } else if (calender_button_showing_shadow_19.getText().toString().equals(String.valueOf(old_day))) {
                        calender_button_showing_shadow_19.setTextColor(Color.parseColor(colors[old_day]));
                    } else if (calender_button_showing_shadow_20.getText().toString().equals(String.valueOf(old_day))) {
                        calender_button_showing_shadow_20.setTextColor(Color.parseColor(colors[old_day]));
                    } else if (calender_button_showing_shadow_21.getText().toString().equals(String.valueOf(old_day))) {
                        calender_button_showing_shadow_21.setTextColor(Color.parseColor(colors[old_day]));
                    } else if (calender_button_showing_shadow_22.getText().toString().equals(String.valueOf(old_day))) {
                        calender_button_showing_shadow_22.setTextColor(Color.parseColor(colors[old_day]));
                    } else if (calender_button_showing_shadow_23.getText().toString().equals(String.valueOf(old_day))) {
                        calender_button_showing_shadow_23.setTextColor(Color.parseColor(colors[old_day]));
                    } else if (calender_button_showing_shadow_24.getText().toString().equals(String.valueOf(old_day))) {
                        calender_button_showing_shadow_24.setTextColor(Color.parseColor(colors[old_day]));
                    } else if (calender_button_showing_shadow_25.getText().toString().equals(String.valueOf(old_day))) {
                        calender_button_showing_shadow_25.setTextColor(Color.parseColor(colors[old_day]));
                    } else if (calender_button_showing_shadow_26.getText().toString().equals(String.valueOf(old_day))) {
                        calender_button_showing_shadow_26.setTextColor(Color.parseColor(colors[old_day]));
                    } else if (calender_button_showing_shadow_27.getText().toString().equals(String.valueOf(old_day))) {
                        calender_button_showing_shadow_27.setTextColor(Color.parseColor(colors[old_day]));
                    } else if (calender_button_showing_shadow_28.getText().toString().equals(String.valueOf(old_day))) {
                        calender_button_showing_shadow_28.setTextColor(Color.parseColor(colors[old_day]));
                    } else if (calender_button_showing_shadow_29.getText().toString().equals(String.valueOf(old_day))) {
                        calender_button_showing_shadow_29.setTextColor(Color.parseColor(colors[old_day]));
                    } else if (calender_button_showing_shadow_30.getText().toString().equals(String.valueOf(old_day))) {
                        calender_button_showing_shadow_30.setTextColor(Color.parseColor(colors[old_day]));
                    } else if (calender_button_showing_shadow_31.getText().toString().equals(String.valueOf(old_day))) {
                        calender_button_showing_shadow_31.setTextColor(Color.parseColor(colors[old_day]));
                    } else if (calender_button_showing_shadow_32.getText().toString().equals(String.valueOf(old_day))) {
                        calender_button_showing_shadow_32.setTextColor(Color.parseColor(colors[old_day]));
                    } else if (calender_button_showing_shadow_33.getText().toString().equals(String.valueOf(old_day))) {
                        calender_button_showing_shadow_33.setTextColor(Color.parseColor(colors[old_day]));
                    } else if (calender_button_showing_shadow_34.getText().toString().equals(String.valueOf(old_day))) {
                        calender_button_showing_shadow_34.setTextColor(Color.parseColor(colors[old_day]));
                    } else if (calender_button_showing_shadow_35.getText().toString().equals(String.valueOf(old_day))) {
                        calender_button_showing_shadow_35.setTextColor(Color.parseColor(colors[old_day]));
                    } else if (calender_button_showing_shadow_36.getText().toString().equals(String.valueOf(old_day))) {
                        calender_button_showing_shadow_36.setTextColor(Color.parseColor(colors[old_day]));
                    } else {
                        calender_button_showing_shadow_37.setTextColor(Color.parseColor(colors[old_day]));
                    }
                }
            }
        }
    }

    private void clear_the_background_only() {
        calender_button_showing_shadow_1.setBackgroundResource(R.drawable.round_button);
        calender_button_showing_shadow_2.setBackgroundResource(R.drawable.round_button);
        calender_button_showing_shadow_3.setBackgroundResource(R.drawable.round_button);
        calender_button_showing_shadow_4.setBackgroundResource(R.drawable.round_button);
        calender_button_showing_shadow_5.setBackgroundResource(R.drawable.round_button);
        calender_button_showing_shadow_6.setBackgroundResource(R.drawable.round_button);
        calender_button_showing_shadow_7.setBackgroundResource(R.drawable.round_button);
        calender_button_showing_shadow_8.setBackgroundResource(R.drawable.round_button);
        calender_button_showing_shadow_9.setBackgroundResource(R.drawable.round_button);
        calender_button_showing_shadow_10.setBackgroundResource(R.drawable.round_button);
        calender_button_showing_shadow_11.setBackgroundResource(R.drawable.round_button);
        calender_button_showing_shadow_12.setBackgroundResource(R.drawable.round_button);
        calender_button_showing_shadow_13.setBackgroundResource(R.drawable.round_button);
        calender_button_showing_shadow_14.setBackgroundResource(R.drawable.round_button);
        calender_button_showing_shadow_15.setBackgroundResource(R.drawable.round_button);
        calender_button_showing_shadow_16.setBackgroundResource(R.drawable.round_button);
        calender_button_showing_shadow_17.setBackgroundResource(R.drawable.round_button);
        calender_button_showing_shadow_18.setBackgroundResource(R.drawable.round_button);
        calender_button_showing_shadow_19.setBackgroundResource(R.drawable.round_button);
        calender_button_showing_shadow_20.setBackgroundResource(R.drawable.round_button);
        calender_button_showing_shadow_21.setBackgroundResource(R.drawable.round_button);
        calender_button_showing_shadow_22.setBackgroundResource(R.drawable.round_button);
        calender_button_showing_shadow_23.setBackgroundResource(R.drawable.round_button);
        calender_button_showing_shadow_24.setBackgroundResource(R.drawable.round_button);
        calender_button_showing_shadow_25.setBackgroundResource(R.drawable.round_button);
        calender_button_showing_shadow_26.setBackgroundResource(R.drawable.round_button);
        calender_button_showing_shadow_27.setBackgroundResource(R.drawable.round_button);
        calender_button_showing_shadow_28.setBackgroundResource(R.drawable.round_button);
        calender_button_showing_shadow_29.setBackgroundResource(R.drawable.round_button);
        calender_button_showing_shadow_30.setBackgroundResource(R.drawable.round_button);
        calender_button_showing_shadow_31.setBackgroundResource(R.drawable.round_button);
        calender_button_showing_shadow_32.setBackgroundResource(R.drawable.round_button);
        calender_button_showing_shadow_33.setBackgroundResource(R.drawable.round_button);
        calender_button_showing_shadow_34.setBackgroundResource(R.drawable.round_button);
        calender_button_showing_shadow_35.setBackgroundResource(R.drawable.round_button);
        calender_button_showing_shadow_36.setBackgroundResource(R.drawable.round_button);
        calender_button_showing_shadow_37.setBackgroundResource(R.drawable.round_button);
    }

    private String check_past_now_or_future() {
        if (getView() != null) {
            TextView month_and_year_in_calender_for_good_habits = getView().findViewById(R.id.tv_month_year);
            String[] split_month_and_year = month_and_year_in_calender_for_good_habits.getText().toString().split(" ");
            int calender_month = returnMonthStringToInt(split_month_and_year[0]);
            int calender_year = Integer.parseInt(split_month_and_year[1]);
            Calendar calendar = Calendar.getInstance();
            int real_year = calendar.get(Calendar.YEAR);
            int real_month = calendar.get(Calendar.MONTH);
            if (calender_year > real_year) {
                return "future";
            } else if (calender_year == real_year) {
                if (calender_month > real_month) {
                    return "future";
                } else if (calender_month == real_month) {
                    return "current";
                } else {
                    return "past";
                }
            } else {
                return "past";
            }
        } else {
            return "future";
        }
    }



    private void color_the_button_under_the_calender() {
        if (getView() != null) {
            TextView month_and_year_in_calender_for_good_habits = getView().findViewById(R.id.tv_month_year);
            String very_bad_color = return_the_color_of_mood(1);
            String bad_color = return_the_color_of_mood(2);
            String ok_color = return_the_color_of_mood(3);
            String good_color = return_the_color_of_mood(4);
            String very_good_color = return_the_color_of_mood(5);
            String no_mood_color = return_the_color_of_mood(0);
            String[] splitter_temp = month_and_year_in_calender_for_good_habits.getText().toString().split(" ");
            String month = String.valueOf(returnMonthStringToInt(splitter_temp[0]));
            String year = String.valueOf(splitter_temp[1]);
            String[] splitter = color_the_today.split("_");
            View very_bad_mood_shade_in_habits = getView().findViewById(R.id.view_very_bad_mood_edit_background);
            View bad_mood_shade_in_habits = getView().findViewById(R.id.view_bad_mood_edit_background);
            View ok_mood_shade_in_habits = getView().findViewById(R.id.view_ok_mood_edit_background);
            View good_mood_shade_in_habits = getView().findViewById(R.id.view_good_mood_edit_background);
            View very_good_mood_shade_in_habits = getView().findViewById(R.id.view_very_good_mood_edit_background);
            View very_good_mood_in_habits = getView().findViewById(R.id.view_very_good_mood_edit);
            if (month.equals(splitter[1]) && year.equals(splitter[2])) {
                if (colors[Integer.parseInt(splitter[0])].equals(very_bad_color)) {
                    if (very_bad_mood_shade_in_habits.getVisibility() != View.VISIBLE && very_good_mood_in_habits.getVisibility() == View.VISIBLE) {
                        select_the_mood_in_calender(1);
                    }
                } else if (colors[Integer.parseInt(splitter[0])].equals(bad_color) && very_good_mood_in_habits.getVisibility() == View.VISIBLE) {
                    if (bad_mood_shade_in_habits.getVisibility() != View.VISIBLE) {
                        select_the_mood_in_calender(2);
                    }
                } else if (colors[Integer.parseInt(splitter[0])].equals(ok_color) && very_good_mood_in_habits.getVisibility() == View.VISIBLE) {
                    if (ok_mood_shade_in_habits.getVisibility() != View.VISIBLE) {
                        select_the_mood_in_calender(3);
                    }
                } else if (colors[Integer.parseInt(splitter[0])].equals(good_color) && very_good_mood_in_habits.getVisibility() == View.VISIBLE) {
                    if (good_mood_shade_in_habits.getVisibility() != View.VISIBLE) {
                        select_the_mood_in_calender(4);
                    }
                } else if (colors[Integer.parseInt(splitter[0])].equals(very_good_color) && very_good_mood_in_habits.getVisibility() == View.VISIBLE) {
                    if (very_good_mood_shade_in_habits.getVisibility() != View.VISIBLE) {
                        select_the_mood_in_calender(5);
                    }
                } else if (colors[Integer.parseInt(splitter[0])].equals(no_mood_color) && very_good_mood_in_habits.getVisibility() == View.VISIBLE) {
                    select_the_mood_in_calender(0);
                }
            }
        }
    }

    private void yes_and_no_button_listen_under_the_calender() {
        if (getView() != null) {
            final Button very_bad_mood_button_in_habits = getView().findViewById(R.id.bt_very_bad_mood_edit);
            final Button bad_mood_button_in_habits = getView().findViewById(R.id.bt_bad_mood_edit);
            final Button ok_mood_button_in_habits = getView().findViewById(R.id.bt_ok_mood_edit);
            final Button good_mood_button_in_habits = getView().findViewById(R.id.bt_good_mood_edit);
            final Button very_good_mood_button_in_habits = getView().findViewById(R.id.bt_very_good_mood_edit);
            final View very_bad_mood_shade_in_habits = getView().findViewById(R.id.view_very_bad_mood_edit_background);
            final View bad_mood_shade_in_habits = getView().findViewById(R.id.view_bad_mood_edit_background);
            final View ok_mood_shade_in_habits = getView().findViewById(R.id.view_ok_mood_edit_background);
            final View good_mood_shade_in_habits = getView().findViewById(R.id.view_good_mood_edit_background);
            final View very_good_mood_shade_in_habits = getView().findViewById(R.id.view_very_good_mood_edit_background);
            final TextView month_and_year_in_calender_for_good_habits = getView().findViewById(R.id.tv_month_year);
            very_bad_mood_button_in_habits.setOnClickListener(view -> {
                String[] split_for_day_month_year = color_the_today.split("_");
                String[] month_and_year = month_and_year_in_calender_for_good_habits.getText().toString().split(" ");
                int calender_day = Integer.parseInt(split_for_day_month_year[0]);
                int calender_month = returnMonthStringToInt(month_and_year[0]);
                int calender_year = Integer.parseInt(month_and_year[1]);
                Calendar calendar = Calendar.getInstance();
                calendar.set(calender_year, calender_month, calender_day);
                if (very_bad_mood_shade_in_habits.getVisibility() == View.VISIBLE) {
                    select_the_mood_in_calender(0);
                    save_the_input_for_good_habit_input(0, calendar.getTimeInMillis());
                } else {
                    select_the_mood_in_calender(0);
                    select_the_mood_in_calender(1);
                    save_the_input_for_good_habit_input(1, calendar.getTimeInMillis());
                }
                color_the_calender();
                divide_it_into_weeks();
                makeEverythingAverageMood();
                drawTheMoodLineChart();
                drawTheRightBarChartMood();
                line_chart_for_streak.fitScreen();

            });
            bad_mood_button_in_habits.setOnClickListener(view -> {
                String[] split_for_day_month_year = color_the_today.split("_");
                String[] month_and_year = month_and_year_in_calender_for_good_habits.getText().toString().split(" ");
                int calender_day = Integer.parseInt(split_for_day_month_year[0]);
                int calender_month = returnMonthStringToInt(month_and_year[0]);
                int calender_year = Integer.parseInt(month_and_year[1]);
                Calendar calendar = Calendar.getInstance();
                calendar.set(calender_year, calender_month, calender_day);
                if (bad_mood_shade_in_habits.getVisibility() == View.VISIBLE) {
                    select_the_mood_in_calender(0);
                    save_the_input_for_good_habit_input(0, calendar.getTimeInMillis());
                } else {
                    select_the_mood_in_calender(0);
                    select_the_mood_in_calender(2);
                    save_the_input_for_good_habit_input(2, calendar.getTimeInMillis());
                }
                color_the_calender();
                divide_it_into_weeks();
                makeEverythingAverageMood();
                drawTheMoodLineChart();
                drawTheRightBarChartMood();
                line_chart_for_streak.fitScreen();

            });
            ok_mood_button_in_habits.setOnClickListener(view -> {
                String[] split_for_day_month_year = color_the_today.split("_");
                String[] month_and_year = month_and_year_in_calender_for_good_habits.getText().toString().split(" ");
                int calender_day = Integer.parseInt(split_for_day_month_year[0]);
                int calender_month = returnMonthStringToInt(month_and_year[0]);
                int calender_year = Integer.parseInt(month_and_year[1]);
                Calendar calendar = Calendar.getInstance();
                calendar.set(calender_year, calender_month, calender_day);
                if (ok_mood_shade_in_habits.getVisibility() == View.VISIBLE) {
                    select_the_mood_in_calender(0);
                    save_the_input_for_good_habit_input(0, calendar.getTimeInMillis());
                } else {
                    select_the_mood_in_calender(0);
                    select_the_mood_in_calender(3);
                    save_the_input_for_good_habit_input(3, calendar.getTimeInMillis());
                }
                color_the_calender();
                divide_it_into_weeks();
                makeEverythingAverageMood();
                drawTheMoodLineChart();
                drawTheRightBarChartMood();
                line_chart_for_streak.fitScreen();

            });
            good_mood_button_in_habits.setOnClickListener(view -> {
                String[] split_for_day_month_year = color_the_today.split("_");
                String[] month_and_year = month_and_year_in_calender_for_good_habits.getText().toString().split(" ");
                int calender_day = Integer.parseInt(split_for_day_month_year[0]);
                int calender_month = returnMonthStringToInt(month_and_year[0]);
                int calender_year = Integer.parseInt(month_and_year[1]);
                Calendar calendar = Calendar.getInstance();
                calendar.set(calender_year, calender_month, calender_day);
                if (good_mood_shade_in_habits.getVisibility() == View.VISIBLE) {
                    select_the_mood_in_calender(0);
                    save_the_input_for_good_habit_input(0, calendar.getTimeInMillis());
                } else {
                    select_the_mood_in_calender(0);
                    select_the_mood_in_calender(4);
                    save_the_input_for_good_habit_input(4, calendar.getTimeInMillis());
                }
                color_the_calender();
                divide_it_into_weeks();
                makeEverythingAverageMood();
                drawTheMoodLineChart();
                drawTheRightBarChartMood();
                line_chart_for_streak.fitScreen();
            });
            very_good_mood_button_in_habits.setOnClickListener(view -> {
                String[] split_for_day_month_year = color_the_today.split("_");
                String[] month_and_year = month_and_year_in_calender_for_good_habits.getText().toString().split(" ");
                int calender_day = Integer.parseInt(split_for_day_month_year[0]);
                int calender_month = returnMonthStringToInt(month_and_year[0]);
                int calender_year = Integer.parseInt(month_and_year[1]);
                Calendar calendar = Calendar.getInstance();
                calendar.set(calender_year, calender_month, calender_day);
                if (very_good_mood_shade_in_habits.getVisibility() == View.VISIBLE) {
                    select_the_mood_in_calender(0);
                    save_the_input_for_good_habit_input(0, calendar.getTimeInMillis());
                } else {
                    select_the_mood_in_calender(0);
                    select_the_mood_in_calender(5);
                    save_the_input_for_good_habit_input(5, calendar.getTimeInMillis());
                }
                color_the_calender();
                divide_it_into_weeks();
                makeEverythingAverageMood();
                drawTheMoodLineChart();
                drawTheRightBarChartMood();
                line_chart_for_streak.fitScreen();
            });
        }
    }

    private void divide_it_into_weeks() {
        int month_start_day;
        if (calender_button_showing_shadow_1.getVisibility() == View.VISIBLE) {
            month_start_day = 7;
        } else if (calender_button_showing_shadow_2.getVisibility() == View.VISIBLE) {
            month_start_day = 6;
        } else if (calender_button_showing_shadow_3.getVisibility() == View.VISIBLE) {
            month_start_day = 5;
        } else if (calender_button_showing_shadow_4.getVisibility() == View.VISIBLE) {
            month_start_day = 4;
        } else if (calender_button_showing_shadow_5.getVisibility() == View.VISIBLE) {
            month_start_day = 3;
        } else if (calender_button_showing_shadow_6.getVisibility() == View.VISIBLE) {
            month_start_day = 2;
        } else {
            month_start_day = 1;
        }
        String[] splitter_read = returnTheStateOfTheDays().split("split");
        String first_line_string = "";
        String second_line_string = "";
        String three_line_string = "";
        String four_line_string = "";
        String five_line_string = "";
        String six_line_string = "";
        for (int i = 0; i < month_start_day; i++) {
            first_line_string = first_line_string.concat(splitter_read[i]).concat("split");
        }
        for (int i = month_start_day; i < month_start_day + 7; i++) {
            second_line_string = second_line_string.concat(splitter_read[i]).concat("split");
        }
        for (int i = month_start_day + 7; i < month_start_day + 14; i++) {
            three_line_string = three_line_string.concat(splitter_read[i]).concat("split");
        }
        for (int i = month_start_day + 14; i < month_start_day + 21; i++) {
            four_line_string = four_line_string.concat(splitter_read[i]).concat("split");
        }
        int how_many_are_empty = returnLastDayOfMonth() + (7 - month_start_day);
        if (how_many_are_empty <= 35) {
            for (int i = month_start_day + 21; i < splitter_read.length; i++) {
                five_line_string = five_line_string.concat(splitter_read[i]).concat("split");
            }
        } else {
            for (int i = month_start_day + 21; i < month_start_day + 28; i++) {
                five_line_string = five_line_string.concat(splitter_read[i]).concat("split");
            }
            for (int i = month_start_day + 28; i < splitter_read.length; i++) {
                six_line_string = six_line_string.concat(splitter_read[i]).concat("split");
            }
        }
        set_the_first_line(first_line_string);
        set_the_second_line(second_line_string);
        set_the_third_line(three_line_string);
        set_the_fourth_line(four_line_string);
        set_the_fifth_line(five_line_string);
        set_the_sixth_line(six_line_string);
    }

    private void set_the_first_line(String date) {
        if (getView() != null) {
            String[] date_split = date.split("split");
            int empty_length = 7 - date_split.length;
            for (int i = 0; i < empty_length; i++) {
                date = "empty".concat("split").concat(date);
            }
            String[] splitter_second = date.split("split");
            switch (splitter_second[0]) {
                case "empty":
                    modes_for_four_drawable[0] = 0;
                    break;
                case "start":
                    modes_for_four_drawable[0] = 1;
                    break;
                case "middle":
                    modes_for_four_drawable[0] = 2;
                    break;
                case "end":
                    modes_for_four_drawable[0] = 3;
                    break;
                case "beg_last":
                    modes_for_four_drawable[0] = 4;
                    break;
            }
            switch (splitter_second[1]) {
                case "empty":
                    modes_for_four_drawable[1] = 0;
                    break;
                case "start":
                    modes_for_four_drawable[1] = 1;
                    break;
                case "middle":
                    modes_for_four_drawable[1] = 2;
                    break;
                case "end":
                    modes_for_four_drawable[1] = 3;
                    break;
                case "beg_last":
                    modes_for_four_drawable[1] = 4;
                    break;
            }
            switch (splitter_second[2]) {
                case "empty":
                    modes_for_four_drawable[2] = 0;
                    break;
                case "start":
                    modes_for_four_drawable[2] = 1;
                    break;
                case "middle":
                    modes_for_four_drawable[2] = 2;
                    break;
                case "end":
                    modes_for_four_drawable[2] = 3;
                    break;
                case "beg_last":
                    modes_for_four_drawable[2] = 4;
                    break;
            }
            switch (splitter_second[3]) {
                case "empty":
                    modes_for_four_drawable[3] = 0;
                    break;
                case "start":
                    modes_for_four_drawable[3] = 1;
                    break;
                case "middle":
                    modes_for_four_drawable[3] = 2;
                    break;
                case "end":
                    modes_for_four_drawable[3] = 3;
                    break;
                case "beg_last":
                    modes_for_four_drawable[3] = 4;
                    break;
            }
            switch (splitter_second[4]) {
                case "empty":
                    modes_for_four_drawable[4] = 0;
                    break;
                case "start":
                    modes_for_four_drawable[4] = 1;
                    break;
                case "middle":
                    modes_for_four_drawable[4] = 2;
                    break;
                case "end":
                    modes_for_four_drawable[4] = 3;
                    break;
                case "beg_last":
                    modes_for_four_drawable[4] = 4;
                    break;
            }
            switch (splitter_second[5]) {
                case "empty":
                    modes_for_four_drawable[5] = 0;
                    break;
                case "start":
                    modes_for_four_drawable[5] = 1;
                    break;
                case "middle":
                    modes_for_four_drawable[5] = 2;
                    break;
                case "end":
                    modes_for_four_drawable[5] = 3;
                    break;
                case "beg_last":
                    modes_for_four_drawable[5] = 4;
                    break;
            }
            switch (splitter_second[6]) {
                case "empty":
                    modes_for_four_drawable[6] = 0;
                    break;
                case "start":
                    modes_for_four_drawable[6] = 1;
                    break;
                case "middle":
                    modes_for_four_drawable[6] = 2;
                    break;
                case "end":
                    modes_for_four_drawable[6] = 3;
                    break;
                case "beg_last":
                    modes_for_four_drawable[6] = 4;
                    break;
            }
        }
    }

    private void set_the_second_line(String date) {
        if (getView() != null) {
            String[] date_split = date.split("split");
            switch (date_split[0]) {
                case "empty":
                    modes_for_four_drawable[7] = 0;
                    break;
                case "start":
                    modes_for_four_drawable[7] = 1;
                    break;
                case "middle":
                    modes_for_four_drawable[7] = 2;
                    break;
                case "end":
                    modes_for_four_drawable[7] = 3;
                    break;
                case "beg_last":
                    modes_for_four_drawable[7] = 4;
                    break;
            }
            switch (date_split[1]) {
                case "empty":
                    modes_for_four_drawable[8] = 0;
                    break;
                case "start":
                    modes_for_four_drawable[8] = 1;
                    break;
                case "middle":
                    modes_for_four_drawable[8] = 2;
                    break;
                case "end":
                    modes_for_four_drawable[8] = 3;
                    break;
                case "beg_last":
                    modes_for_four_drawable[8] = 4;
                    break;
            }
            switch (date_split[2]) {
                case "empty":
                    modes_for_four_drawable[9] = 0;
                    break;
                case "start":
                    modes_for_four_drawable[9] = 1;
                    break;
                case "middle":
                    modes_for_four_drawable[9] = 2;
                    break;
                case "end":
                    modes_for_four_drawable[9] = 3;
                    break;
                case "beg_last":
                    modes_for_four_drawable[9] = 4;
                    break;
            }
            switch (date_split[3]) {
                case "empty":
                    modes_for_four_drawable[10] = 0;
                    break;
                case "start":
                    modes_for_four_drawable[10] = 1;
                    break;
                case "middle":
                    modes_for_four_drawable[10] = 2;
                    break;
                case "end":
                    modes_for_four_drawable[10] = 3;
                    break;
                case "beg_last":
                    modes_for_four_drawable[10] = 4;
                    break;
            }
            switch (date_split[4]) {
                case "empty":
                    modes_for_four_drawable[11] = 0;
                    break;
                case "start":
                    modes_for_four_drawable[11] = 1;
                    break;
                case "middle":
                    modes_for_four_drawable[11] = 2;
                    break;
                case "end":
                    modes_for_four_drawable[11] = 3;
                    break;
                case "beg_last":
                    modes_for_four_drawable[11] = 4;
                    break;
            }
            switch (date_split[5]) {
                case "empty":
                    modes_for_four_drawable[12] = 0;
                    break;
                case "start":
                    modes_for_four_drawable[12] = 1;
                    break;
                case "middle":
                    modes_for_four_drawable[12] = 2;
                    break;
                case "end":
                    modes_for_four_drawable[12] = 3;
                    break;
                case "beg_last":
                    modes_for_four_drawable[12] = 4;
                    break;
            }
            switch (date_split[6]) {
                case "empty":
                    modes_for_four_drawable[13] = 0;
                    break;
                case "start":
                    modes_for_four_drawable[13] = 1;
                    break;
                case "middle":
                    modes_for_four_drawable[13] = 2;
                    break;
                case "end":
                    modes_for_four_drawable[13] = 3;
                    break;
                case "beg_last":
                    modes_for_four_drawable[13] = 4;
                    break;
            }
        }
    }

    private void set_the_third_line(String date) {
        if (getView() != null) {
            String[] date_split = date.split("split");
            switch (date_split[0]) {
                case "empty":
                    modes_for_four_drawable[14] = 0;
                    break;
                case "start":
                    modes_for_four_drawable[14] = 1;
                    break;
                case "middle":
                    modes_for_four_drawable[14] = 2;
                    break;
                case "end":
                    modes_for_four_drawable[14] = 3;
                    break;
                case "beg_last":
                    modes_for_four_drawable[14] = 4;
                    break;
            }
            switch (date_split[1]) {
                case "empty":
                    modes_for_four_drawable[15] = 0;
                    break;
                case "start":
                    modes_for_four_drawable[15] = 1;
                    break;
                case "middle":
                    modes_for_four_drawable[15] = 2;
                    break;
                case "end":
                    modes_for_four_drawable[15] = 3;
                    break;
                case "beg_last":
                    modes_for_four_drawable[15] = 4;
                    break;
            }
            switch (date_split[2]) {
                case "empty":
                    modes_for_four_drawable[16] = 0;
                    break;
                case "start":
                    modes_for_four_drawable[16] = 1;
                    break;
                case "middle":
                    modes_for_four_drawable[16] = 2;
                    break;
                case "end":
                    modes_for_four_drawable[16] = 3;
                    break;
                case "beg_last":
                    modes_for_four_drawable[16] = 4;
                    break;
            }
            switch (date_split[3]) {
                case "empty":
                    modes_for_four_drawable[17] = 0;
                    break;
                case "start":
                    modes_for_four_drawable[17] = 1;
                    break;
                case "middle":
                    modes_for_four_drawable[17] = 2;
                    break;
                case "end":
                    modes_for_four_drawable[17] = 3;
                    break;
                case "beg_last":
                    modes_for_four_drawable[17] = 4;
                    break;
            }
            switch (date_split[4]) {
                case "empty":
                    modes_for_four_drawable[18] = 0;
                    break;
                case "start":
                    modes_for_four_drawable[18] = 1;
                    break;
                case "middle":
                    modes_for_four_drawable[18] = 2;
                    break;
                case "end":
                    modes_for_four_drawable[18] = 3;
                    break;
                case "beg_last":
                    modes_for_four_drawable[18] = 4;
                    break;
            }
            switch (date_split[5]) {
                case "empty":
                    modes_for_four_drawable[19] = 0;
                    break;
                case "start":
                    modes_for_four_drawable[19] = 1;
                    break;
                case "middle":
                    modes_for_four_drawable[19] = 2;
                    break;
                case "end":
                    modes_for_four_drawable[19] = 3;
                    break;
                case "beg_last":
                    modes_for_four_drawable[19] = 4;
                    break;
            }
            switch (date_split[6]) {
                case "empty":
                    modes_for_four_drawable[20] = 0;
                    break;
                case "start":
                    modes_for_four_drawable[20] = 1;
                    break;
                case "middle":
                    modes_for_four_drawable[20] = 2;
                    break;
                case "end":
                    modes_for_four_drawable[20] = 3;
                    break;
                case "beg_last":
                    modes_for_four_drawable[20] = 4;
                    break;
            }
        }
    }

    private void set_the_fourth_line(String date) {
        if (getView() != null) {
            String[] date_split = date.split("split");
            switch (date_split[0]) {
                case "empty":
                    modes_for_four_drawable[21] = 0;
                    break;
                case "start":
                    modes_for_four_drawable[21] = 1;
                    break;
                case "middle":
                    modes_for_four_drawable[21] = 2;
                    break;
                case "end":
                    modes_for_four_drawable[21] = 3;
                    break;
                case "beg_last":
                    modes_for_four_drawable[21] = 4;
                    break;
            }
            switch (date_split[1]) {
                case "empty":
                    modes_for_four_drawable[22] = 0;
                    break;
                case "start":
                    modes_for_four_drawable[22] = 1;
                    break;
                case "middle":
                    modes_for_four_drawable[22] = 2;
                    break;
                case "end":
                    modes_for_four_drawable[22] = 3;
                    break;
                case "beg_last":
                    modes_for_four_drawable[22] = 4;
                    break;
            }
            switch (date_split[2]) {
                case "empty":
                    modes_for_four_drawable[23] = 0;
                    break;
                case "start":
                    modes_for_four_drawable[23] = 1;
                    break;
                case "middle":
                    modes_for_four_drawable[23] = 2;
                    break;
                case "end":
                    modes_for_four_drawable[23] = 3;
                    break;
                case "beg_last":
                    modes_for_four_drawable[23] = 4;
                    break;
            }
            switch (date_split[3]) {
                case "empty":
                    modes_for_four_drawable[24] = 0;
                    break;
                case "start":
                    modes_for_four_drawable[24] = 1;
                    break;
                case "middle":
                    modes_for_four_drawable[24] = 2;
                    break;
                case "end":
                    modes_for_four_drawable[24] = 3;
                    break;
                case "beg_last":
                    modes_for_four_drawable[24] = 4;
                    break;
            }
            switch (date_split[4]) {
                case "empty":
                    modes_for_four_drawable[25] = 0;
                    break;
                case "start":
                    modes_for_four_drawable[25] = 1;
                    break;
                case "middle":
                    modes_for_four_drawable[25] = 2;
                    break;
                case "end":
                    modes_for_four_drawable[25] = 3;
                    break;
                case "beg_last":
                    modes_for_four_drawable[25] = 4;
                    break;
            }
            switch (date_split[5]) {
                case "empty":
                    modes_for_four_drawable[26] = 0;
                    break;
                case "start":
                    modes_for_four_drawable[26] = 1;
                    break;
                case "middle":
                    modes_for_four_drawable[26] = 2;
                    break;
                case "end":
                    modes_for_four_drawable[26] = 3;
                    break;
                case "beg_last":
                    modes_for_four_drawable[26] = 4;
                    break;
            }
            switch (date_split[6]) {
                case "empty":
                    modes_for_four_drawable[27] = 0;
                    break;
                case "start":
                    modes_for_four_drawable[27] = 1;
                    break;
                case "middle":
                    modes_for_four_drawable[27] = 2;
                    break;
                case "end":
                    modes_for_four_drawable[27] = 3;
                    break;
                case "beg_last":
                    modes_for_four_drawable[27] = 4;
                    break;
            }
        }
    }

    private void set_the_fifth_line(String date) {
        if (getView() != null) {
            String[] date_split = date.split("split");
            if (date_split.length > 0) {
                switch (date_split[0]) {
                    case "empty":
                        modes_for_four_drawable[28] = 0;
                        break;
                    case "start":
                        modes_for_four_drawable[28] = 1;
                        break;
                    case "middle":
                        modes_for_four_drawable[28] = 2;
                        break;
                    case "end":
                        modes_for_four_drawable[28] = 3;
                        break;
                    case "beg_last":
                        modes_for_four_drawable[28] = 4;
                        break;
                }
            } else {
                modes_for_four_drawable[28] = 0;
            }
            if (date_split.length > 1) {
                switch (date_split[1]) {
                    case "empty":
                        modes_for_four_drawable[29] = 0;
                        break;
                    case "start":
                        modes_for_four_drawable[29] = 1;
                        break;
                    case "middle":
                        modes_for_four_drawable[29] = 2;
                        break;
                    case "end":
                        modes_for_four_drawable[29] = 3;
                        break;
                    case "beg_last":
                        modes_for_four_drawable[29] = 4;
                        break;
                }
            } else {
                modes_for_four_drawable[29] = 0;
            }
            if (date_split.length > 2) {
                switch (date_split[2]) {
                    case "empty":
                        modes_for_four_drawable[30] = 0;
                        break;
                    case "start":
                        modes_for_four_drawable[30] = 1;
                        break;
                    case "middle":
                        modes_for_four_drawable[30] = 2;
                        break;
                    case "end":
                        modes_for_four_drawable[30] = 3;
                        break;
                    case "beg_last":
                        modes_for_four_drawable[30] = 4;
                        break;
                }
            } else {
                modes_for_four_drawable[30] = 0;
            }
            if (date_split.length > 3) {
                switch (date_split[3]) {
                    case "empty":
                        modes_for_four_drawable[31] = 0;
                        break;
                    case "start":
                        modes_for_four_drawable[31] = 1;
                        break;
                    case "middle":
                        modes_for_four_drawable[31] = 2;
                        break;
                    case "end":
                        modes_for_four_drawable[31] = 3;
                        break;
                    case "beg_last":
                        modes_for_four_drawable[31] = 4;
                        break;
                }
            } else {
                modes_for_four_drawable[31] = 0;
            }
            if (date_split.length > 4) {
                switch (date_split[4]) {
                    case "empty":
                        modes_for_four_drawable[32] = 0;
                        break;
                    case "start":
                        modes_for_four_drawable[32] = 1;
                        break;
                    case "middle":
                        modes_for_four_drawable[32] = 2;
                        break;
                    case "end":
                        modes_for_four_drawable[32] = 3;
                        break;
                    case "beg_last":
                        modes_for_four_drawable[32] = 4;
                        break;
                }
            } else {
                modes_for_four_drawable[32] = 0;
            }
            if (date_split.length > 5) {
                switch (date_split[5]) {
                    case "empty":
                        modes_for_four_drawable[33] = 0;
                        break;
                    case "start":
                        modes_for_four_drawable[33] = 1;
                        break;
                    case "middle":
                        modes_for_four_drawable[33] = 2;
                        break;
                    case "end":
                        modes_for_four_drawable[33] = 3;
                        break;
                    case "beg_last":
                        modes_for_four_drawable[33] = 4;
                        break;
                }
            } else {
                modes_for_four_drawable[33] = 0;
            }
            if (date_split.length > 6) {
                switch (date_split[6]) {
                    case "empty":
                        modes_for_four_drawable[34] = 0;
                        break;
                    case "start":
                        modes_for_four_drawable[34] = 1;
                        break;
                    case "middle":
                        modes_for_four_drawable[34] = 2;
                        break;
                    case "end":
                        modes_for_four_drawable[34] = 3;
                        break;
                    case "beg_last":
                        modes_for_four_drawable[34] = 4;
                        break;
                }
            } else {
                modes_for_four_drawable[34] = 0;
            }
        }
    }

    private void set_the_sixth_line(String date) {
        if (getView() != null) {
            String[] date_split = date.split("split");
            if (date_split.length > 0) {
                switch (date_split[0]) {
                    case "empty":
                        modes_for_four_drawable[35] = 0;
                        break;
                    case "start":
                        modes_for_four_drawable[35] = 1;
                        break;
                    case "middle":
                        modes_for_four_drawable[35] = 2;
                        break;
                    case "end":
                        modes_for_four_drawable[35] = 3;
                        break;
                    case "beg_last":
                        modes_for_four_drawable[35] = 4;
                        break;
                }
            } else {
                modes_for_four_drawable[35] = 0;
            }
            if (date_split.length > 1) {
                switch (date_split[1]) {
                    case "empty":
                        modes_for_four_drawable[36] = 0;
                        break;
                    case "start":
                        modes_for_four_drawable[36] = 1;
                        break;
                    case "middle":
                        modes_for_four_drawable[36] = 2;
                        break;
                    case "end":
                        modes_for_four_drawable[36] = 3;
                        break;
                    case "beg_last":
                        modes_for_four_drawable[36] = 4;
                        break;
                }
            } else {
                modes_for_four_drawable[36] = 0;
            }
        }
    }

    private String returnTheStateOfTheDays() {
        if (getView() != null) {
            String month_info = "";
            TextView month_and_year_in_calender_for_good_habits = getView().findViewById(R.id.tv_month_year);
            String[] splitter = month_and_year_in_calender_for_good_habits.getText().toString().split(" ");
            Calendar calendar = Calendar.getInstance();
            Calendar real_calender = Calendar.getInstance();
            calendar.set(Calendar.MONTH, returnMonthStringToInt(splitter[0]));
            calendar.set(Calendar.YEAR, Integer.parseInt(splitter[1]));
            calendar.set(Calendar.DAY_OF_MONTH, 1);

            if (!check_past_now_or_future().equals("future")) {
                if (Simplify_the_time.return_time_in_midnight(calendar.getTimeInMillis()) < Simplify_the_time.return_time_in_midnight(start_date))
                    month_info = "empty".concat("split");
                 else {
                    if (return_the_last_day_of_last_month().equals("continue")) {
                        if (return_state_of_day(calendar.getTimeInMillis()))
                            month_info = "middle".concat("split");
                         else
                            month_info = "end".concat("split");
                    } else {
                        if (return_state_of_day(calendar.getTimeInMillis()))
                            month_info = "start".concat("split");
                         else
                            month_info = "beg_last".concat("split");
                    }
                }
                if (check_past_now_or_future().equals("current")) {
                    for (int i = 2; i <= returnLastDayOfMonth(); i++) {
                        calendar.set(Calendar.DAY_OF_MONTH, i);
                        if (real_calender.get(Calendar.DAY_OF_MONTH) < i || Simplify_the_time.return_time_in_midnight(calendar.getTimeInMillis()) < Simplify_the_time.return_time_in_midnight(start_date)) {
                            month_info = month_info.concat("empty").concat("split");
                        } else {
                            String substring = month_info.substring(month_info.length() - 7, month_info.length() - 5);
                            if (substring.equals("rt") || substring.equals("le")) {
                                if (return_state_of_day(calendar.getTimeInMillis())) {
                                    month_info = month_info.concat("middle").concat("split");
                                } else {
                                    month_info = month_info.concat("end").concat("split");
                                }
                            } else {
                                if (return_state_of_day(calendar.getTimeInMillis())) {
                                    month_info = month_info.concat("start").concat("split");
                                } else {
                                    month_info = month_info.concat("beg_last").concat("split");
                                }
                            }
                        }
                    }
                } else {
                    for (int i = 2; i <= returnLastDayOfMonth(); i++) {
                        calendar.set(Calendar.DAY_OF_MONTH, i);
                        if (Simplify_the_time.return_time_in_midnight(calendar.getTimeInMillis()) < Simplify_the_time.return_time_in_midnight(start_date)) {
                            month_info = month_info.concat("empty").concat("split");
                        } else {
                            String substring = month_info.substring(month_info.length() - 7, month_info.length() - 5);
                            if (substring.equals("rt") || substring.equals("le")) {
                                if (return_state_of_day(calendar.getTimeInMillis())) {
                                    month_info = month_info.concat("middle").concat("split");
                                } else {
                                    month_info = month_info.concat("end").concat("split");
                                }
                            } else {
                                if (return_state_of_day(calendar.getTimeInMillis())) {
                                    month_info = month_info.concat("start").concat("split");
                                } else {
                                    month_info = month_info.concat("beg_last").concat("split");
                                }
                            }
                        }
                    }
                }
            } else {
                for (int i = 1; i <= 31; i++) {
                    month_info = month_info.concat("empty").concat("split");
                }
            }
            return month_info;
        } else {
            return "";
        }
    }

    private boolean return_state_of_day(long milli) {
        milli = Simplify_the_time.return_time_in_midnight(milli);
        return history_of_mood.containsKey(milli);
    }

    private void add_to_list() {
        if (getActivity() != null) {
            SharedPreferences sharedPreferences = getActivity().getSharedPreferences("mood", Context.MODE_PRIVATE);
            String shared = sharedPreferences.getString("mood_stats", "");
            history_of_mood = new HashMap<>();
            if (shared != null && !shared.equals("")) {
                String[] split = shared.split("max_divide");
                for (String s : split) {
                    String[] small_split = s.split("small_split");
                    history_of_mood.put(Long.parseLong(small_split[1]), Integer.parseInt(small_split[0]));
                }
            }
        }
    }

    private String return_the_last_day_of_last_month() {
        Calendar calendar = Calendar.getInstance();
        TextView month_and_year_in_calender_for_good_habits = getView().findViewById(R.id.tv_month_year);
        String[] splitter = month_and_year_in_calender_for_good_habits.getText().toString().split(" ");
        int month = returnMonthStringToInt(splitter[0]);
        int year = Integer.parseInt(splitter[1]);
        if (month == 0) {
            month = 11;
            year--;
        } else {
            month--;
        }
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        int day = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        calendar.set(Calendar.DAY_OF_MONTH, day);
        if (Simplify_the_time.return_time_in_midnight(start_date) <= Simplify_the_time.return_time_in_midnight(calendar.getTimeInMillis())) {
            if (return_state_of_day(calendar.getTimeInMillis())) {
                return "continue";
            } else {
                return "start";
            }
        } else {
            return "start";
        }
    }

    private void get_the_color() {
        if (getActivity() != null) {
            SharedPreferences sharedPreferences = getActivity().getSharedPreferences("mood", Context.MODE_PRIVATE);
            String shared = sharedPreferences.getString("mood_color", "");
            if (shared == null || shared.equals("")) {
                color = Color.parseColor("#000075");
            } else {
                color = Color.parseColor(shared);
            }

        }
    }

    private void save_the_input_for_good_habit_input(int mood, long milli) {
        if (getActivity() != null) {
            milli = Simplify_the_time.return_time_in_midnight(milli);
            SharedPreferences sharedPreferences = getActivity().getSharedPreferences("mood", Context.MODE_PRIVATE);
            if (mood == 0) {
                history_of_mood.remove(milli);
            } else {
                if (history_of_mood.containsKey(milli)) {
                    history_of_mood.remove(milli);
                    history_of_mood.put(milli, mood);
                } else {
                    history_of_mood.put(milli, mood);
                }
            }
            String save_me = "";
            for (Map.Entry<Long, Integer> map : history_of_mood.entrySet()) {
                save_me = save_me.concat(String.valueOf(map.getValue())).concat("small_split").concat(String.valueOf(map.getKey())).concat("max_divide");
            }
            SharedPreferences.Editor myEdit = sharedPreferences.edit();
            myEdit.putString("mood_stats", save_me);
            myEdit.apply();
        }
    }

    private void select_the_mood_in_calender(int which) {
        if (getView() != null) {
            View very_bad_mood_shade_in_habits = getView().findViewById(R.id.view_very_bad_mood_edit_background);
            View bad_mood_shade_in_habits = getView().findViewById(R.id.view_bad_mood_edit_background);
            View ok_mood_shade_in_habits = getView().findViewById(R.id.view_ok_mood_edit_background);
            View good_mood_shade_in_habits = getView().findViewById(R.id.view_good_mood_edit_background);
            View very_good_mood_shade_in_habits = getView().findViewById(R.id.view_very_good_mood_edit_background);
            View very_bad_mood_check_mark_in_mood = getView().findViewById(R.id.view_very_bad_mood_edit_check);
            View bad_mood_check_mark_in_mood = getView().findViewById(R.id.view_bad_mood_edit_check);
            View ok_mood_check_mark_in_mood = getView().findViewById(R.id.view_ok_mood_edit_check);
            View good_mood_check_mark_in_mood = getView().findViewById(R.id.view_good_mood_edit_check);
            View very_good_mood_check_mark_in_mood = getView().findViewById(R.id.view_very_good_mood_edit_check);
            if (which == 0) {
                very_bad_mood_shade_in_habits.setVisibility(View.INVISIBLE);
                bad_mood_shade_in_habits.setVisibility(View.INVISIBLE);
                ok_mood_shade_in_habits.setVisibility(View.INVISIBLE);
                good_mood_shade_in_habits.setVisibility(View.INVISIBLE);
                very_good_mood_shade_in_habits.setVisibility(View.INVISIBLE);
                very_bad_mood_check_mark_in_mood.setVisibility(View.INVISIBLE);
                bad_mood_check_mark_in_mood.setVisibility(View.INVISIBLE);
                ok_mood_check_mark_in_mood.setVisibility(View.INVISIBLE);
                good_mood_check_mark_in_mood.setVisibility(View.INVISIBLE);
                very_good_mood_check_mark_in_mood.setVisibility(View.INVISIBLE);
            } else if (which == 1) {
                very_bad_mood_shade_in_habits.setVisibility(View.VISIBLE);
                bad_mood_shade_in_habits.setVisibility(View.INVISIBLE);
                ok_mood_shade_in_habits.setVisibility(View.INVISIBLE);
                good_mood_shade_in_habits.setVisibility(View.INVISIBLE);
                very_good_mood_shade_in_habits.setVisibility(View.INVISIBLE);
                very_bad_mood_check_mark_in_mood.setVisibility(View.VISIBLE);
                bad_mood_check_mark_in_mood.setVisibility(View.INVISIBLE);
                ok_mood_check_mark_in_mood.setVisibility(View.INVISIBLE);
                good_mood_check_mark_in_mood.setVisibility(View.INVISIBLE);
                very_good_mood_check_mark_in_mood.setVisibility(View.INVISIBLE);
            } else if (which == 2) {
                very_bad_mood_shade_in_habits.setVisibility(View.INVISIBLE);
                bad_mood_shade_in_habits.setVisibility(View.VISIBLE);
                ok_mood_shade_in_habits.setVisibility(View.INVISIBLE);
                good_mood_shade_in_habits.setVisibility(View.INVISIBLE);
                very_good_mood_shade_in_habits.setVisibility(View.INVISIBLE);
                very_bad_mood_check_mark_in_mood.setVisibility(View.INVISIBLE);
                bad_mood_check_mark_in_mood.setVisibility(View.VISIBLE);
                ok_mood_check_mark_in_mood.setVisibility(View.INVISIBLE);
                good_mood_check_mark_in_mood.setVisibility(View.INVISIBLE);
                very_good_mood_check_mark_in_mood.setVisibility(View.INVISIBLE);
            } else if (which == 3) {
                very_bad_mood_shade_in_habits.setVisibility(View.INVISIBLE);
                bad_mood_shade_in_habits.setVisibility(View.INVISIBLE);
                ok_mood_shade_in_habits.setVisibility(View.VISIBLE);
                good_mood_shade_in_habits.setVisibility(View.INVISIBLE);
                very_good_mood_shade_in_habits.setVisibility(View.INVISIBLE);
                very_bad_mood_check_mark_in_mood.setVisibility(View.INVISIBLE);
                bad_mood_check_mark_in_mood.setVisibility(View.INVISIBLE);
                ok_mood_check_mark_in_mood.setVisibility(View.VISIBLE);
                good_mood_check_mark_in_mood.setVisibility(View.INVISIBLE);
                very_good_mood_check_mark_in_mood.setVisibility(View.INVISIBLE);
            } else if (which == 4) {
                very_bad_mood_shade_in_habits.setVisibility(View.INVISIBLE);
                bad_mood_shade_in_habits.setVisibility(View.INVISIBLE);
                ok_mood_shade_in_habits.setVisibility(View.INVISIBLE);
                good_mood_shade_in_habits.setVisibility(View.VISIBLE);
                very_good_mood_shade_in_habits.setVisibility(View.INVISIBLE);
                very_bad_mood_check_mark_in_mood.setVisibility(View.INVISIBLE);
                bad_mood_check_mark_in_mood.setVisibility(View.INVISIBLE);
                ok_mood_check_mark_in_mood.setVisibility(View.INVISIBLE);
                good_mood_check_mark_in_mood.setVisibility(View.VISIBLE);
                very_good_mood_check_mark_in_mood.setVisibility(View.INVISIBLE);
            } else if (which == 5) {
                very_bad_mood_shade_in_habits.setVisibility(View.INVISIBLE);
                bad_mood_shade_in_habits.setVisibility(View.INVISIBLE);
                ok_mood_shade_in_habits.setVisibility(View.INVISIBLE);
                good_mood_shade_in_habits.setVisibility(View.INVISIBLE);
                very_good_mood_shade_in_habits.setVisibility(View.VISIBLE);
                very_bad_mood_check_mark_in_mood.setVisibility(View.INVISIBLE);
                bad_mood_check_mark_in_mood.setVisibility(View.INVISIBLE);
                ok_mood_check_mark_in_mood.setVisibility(View.INVISIBLE);
                good_mood_check_mark_in_mood.setVisibility(View.INVISIBLE);
                very_good_mood_check_mark_in_mood.setVisibility(View.VISIBLE);
            }
        }
    }

    private void define_start_date() {
        start_date = returnStartDate();
    }

    private String return_the_color_of_mood(int which) {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("mood", Context.MODE_PRIVATE);
        String very_bad = sharedPreferences.getString("very_bad", "");
        String bad = sharedPreferences.getString("bad", "");
        String ok = sharedPreferences.getString("ok", "");
        String good = sharedPreferences.getString("good", "");
        String very_good = sharedPreferences.getString("very_good", "");
        String not_selected = sharedPreferences.getString("not_selected", "");
        if (which == 0) {
            if (not_selected == null || not_selected.equals("")) {
                return "#000000";
            } else {
                return not_selected;
            }
        }
        if (which == 1) {
            if (very_bad == null || very_bad.equals("")) {
                return "#f55656";
            } else {
                return very_bad;
            }
        } else if (which == 2) {
            if (bad == null || bad.equals("")) {
                return "#ffcb6b";
            } else {
                return bad;
            }
        } else if (which == 3) {
            if (ok == null || ok.equals("")) {
                return "#e06dfc";
            } else {
                return ok;
            }
        } else if (which == 4) {
            if (good == null || good.equals("")) {
                return "#69e9f5";
            } else {
                return good;
            }
        } else if (which == 5) {
            if (very_good == null || very_good.equals("")) {
                return "#83d964";
            } else {
                return very_good;
            }
        }
        return "#83d964";
    }

    private int return_color_of_days(long milli) {
        return history_of_mood.getOrDefault( Simplify_the_time.return_time_in_midnight(milli), 0);
    }

    private void color_the_stuff() {
        if (getView() != null) {
            ConstraintLayout layout_inside_scroll_in_the_bad_habits = getView().findViewById(R.id.cl_mood);
            TextView text_asking_did_you_relapse_in_share = getView().findViewById(R.id.tv_edit_mood);
            ProgressBar progress_bar_number_one_mood = getView().findViewById(R.id.pb_1);
            ProgressBar progress_bar_number_two_mood = getView().findViewById(R.id.pb_2);
            ProgressBar progress_bar_number_three_mood = getView().findViewById(R.id.pb_3);
            ProgressBar progress_bar_number_four_mood = getView().findViewById(R.id.pb_4);
            ProgressBar progress_bar_number_five_mood = getView().findViewById(R.id.pb_5);
            Drawable drawable_for_buttons_two = ContextCompat.getDrawable(getContext(), R.drawable.ripple_button_fav_any_circle).mutate();
            drawable_for_buttons_two = DrawableCompat.wrap(drawable_for_buttons_two);
            DrawableCompat.setTint(drawable_for_buttons_two, color);
            Drawable drawable_for_buttons_three = ContextCompat.getDrawable(getContext(), R.drawable.ripple_button_fav_any_circle).mutate();
            drawable_for_buttons_three = DrawableCompat.wrap(drawable_for_buttons_three);
            DrawableCompat.setTint(drawable_for_buttons_three, color);
            layout_inside_scroll_in_the_bad_habits.setBackgroundColor(getResources().getColor(R.color.Hex));
            text_asking_did_you_relapse_in_share.setTextColor(color);

            String very_bad_color = return_the_color_of_mood(1);
            String bad_color = return_the_color_of_mood(2);
            String ok_color = return_the_color_of_mood(3);
            String good_color = return_the_color_of_mood(4);
            String very_good_color = return_the_color_of_mood(5);
            {
                Drawable background = progress_bar_number_one_mood.getProgressDrawable().mutate();
                background.setTint(Color.parseColor(very_bad_color));
            }
            {
                Drawable background = progress_bar_number_two_mood.getProgressDrawable().mutate();
                background.setTint(Color.parseColor(bad_color));
            }
            {
                Drawable background = progress_bar_number_three_mood.getProgressDrawable().mutate();
                background.setTint(Color.parseColor(ok_color));
            }
            {
                Drawable background = progress_bar_number_four_mood.getProgressDrawable().mutate();
                background.setTint(Color.parseColor(good_color));
            }
            {
                Drawable background = progress_bar_number_five_mood.getProgressDrawable().mutate();
                background.setTint(Color.parseColor(very_good_color));
            }
            {
                Drawable unwrappedDrawable = AppCompatResources.getDrawable(getContext(), R.drawable.round_more_vert_24).mutate();
                Drawable wrappedDrawable = DrawableCompat.wrap(unwrappedDrawable);
                DrawableCompat.setTint(wrappedDrawable, color);
            }
            {
                Drawable back_ground = ContextCompat.getDrawable(getContext(), R.drawable.mood_tracker_circle_under_pie).mutate();
                back_ground.setTint(Color.parseColor(very_good_color));
            }
            {
                Drawable back_ground = ContextCompat.getDrawable(getContext(), R.drawable.mood_tracker_circle_under_pie).mutate();
                back_ground.setTint(Color.parseColor(good_color));
            }
            {
                Drawable back_ground = ContextCompat.getDrawable(getContext(), R.drawable.mood_tracker_circle_under_pie).mutate();
                back_ground.setTint(Color.parseColor(ok_color));
            }
            {
                Drawable back_ground = ContextCompat.getDrawable(getContext(), R.drawable.mood_tracker_circle_under_pie).mutate();
                back_ground.setTint(Color.parseColor(bad_color));
            }
            {
                Drawable back_ground = ContextCompat.getDrawable(getContext(), R.drawable.mood_tracker_circle_under_pie).mutate();
                back_ground.setTint(Color.parseColor(very_bad_color));
            }
        }
    }


    private void make_the_buttons_in_the_top_mood(int result) {
        if (getView() != null) {
            View very_bad_mood_shade_in_habits_in_the_top_today = getView().findViewById(R.id.view_background1);
            View bad_mood_shade_in_habits_in_the_top_today = getView().findViewById(R.id.view_background2);
            View ok_mood_shade_in_habits_in_the_top_today = getView().findViewById(R.id.view_background3);
            View good_mood_shade_in_habits_in_the_top_today = getView().findViewById(R.id.view_background4);
            View very_good_mood_shade_in_habits_in_the_top_today = getView().findViewById(R.id.very_good_mood_check);
            View very_bad_mood_check_mark_in_mood_in_the_top_today = getView().findViewById(R.id.very_bad_mood_check);
            View bad_mood_check_mark_in_mood_in_the_top_today = getView().findViewById(R.id.bad_mood_check);
            View ok_mood_check_mark_in_mood_in_the_top_today = getView().findViewById(R.id.ok_mood_check);
            View good_mood_check_mark_in_mood_in_the_top_today = getView().findViewById(R.id.good_mood_check);
            View very_good_mood_check_mark_in_mood_in_the_top_today = getView().findViewById(R.id.mood_check);
            if (result == 0) {
                very_bad_mood_shade_in_habits_in_the_top_today.setVisibility(View.INVISIBLE);
                bad_mood_shade_in_habits_in_the_top_today.setVisibility(View.INVISIBLE);
                ok_mood_shade_in_habits_in_the_top_today.setVisibility(View.INVISIBLE);
                good_mood_shade_in_habits_in_the_top_today.setVisibility(View.INVISIBLE);
                very_good_mood_shade_in_habits_in_the_top_today.setVisibility(View.INVISIBLE);
                very_bad_mood_check_mark_in_mood_in_the_top_today.setVisibility(View.INVISIBLE);
                bad_mood_check_mark_in_mood_in_the_top_today.setVisibility(View.INVISIBLE);
                ok_mood_check_mark_in_mood_in_the_top_today.setVisibility(View.INVISIBLE);
                good_mood_check_mark_in_mood_in_the_top_today.setVisibility(View.INVISIBLE);
                very_good_mood_check_mark_in_mood_in_the_top_today.setVisibility(View.INVISIBLE);
            } else if (result == 1) {
                very_bad_mood_shade_in_habits_in_the_top_today.setVisibility(View.VISIBLE);
                bad_mood_shade_in_habits_in_the_top_today.setVisibility(View.INVISIBLE);
                ok_mood_shade_in_habits_in_the_top_today.setVisibility(View.INVISIBLE);
                good_mood_shade_in_habits_in_the_top_today.setVisibility(View.INVISIBLE);
                very_good_mood_shade_in_habits_in_the_top_today.setVisibility(View.INVISIBLE);
                very_bad_mood_check_mark_in_mood_in_the_top_today.setVisibility(View.VISIBLE);
                bad_mood_check_mark_in_mood_in_the_top_today.setVisibility(View.INVISIBLE);
                ok_mood_check_mark_in_mood_in_the_top_today.setVisibility(View.INVISIBLE);
                good_mood_check_mark_in_mood_in_the_top_today.setVisibility(View.INVISIBLE);
                very_good_mood_check_mark_in_mood_in_the_top_today.setVisibility(View.INVISIBLE);
            } else if (result == 2) {
                very_bad_mood_shade_in_habits_in_the_top_today.setVisibility(View.INVISIBLE);
                bad_mood_shade_in_habits_in_the_top_today.setVisibility(View.VISIBLE);
                ok_mood_shade_in_habits_in_the_top_today.setVisibility(View.INVISIBLE);
                good_mood_shade_in_habits_in_the_top_today.setVisibility(View.INVISIBLE);
                very_good_mood_shade_in_habits_in_the_top_today.setVisibility(View.INVISIBLE);
                very_bad_mood_check_mark_in_mood_in_the_top_today.setVisibility(View.INVISIBLE);
                bad_mood_check_mark_in_mood_in_the_top_today.setVisibility(View.VISIBLE);
                ok_mood_check_mark_in_mood_in_the_top_today.setVisibility(View.INVISIBLE);
                good_mood_check_mark_in_mood_in_the_top_today.setVisibility(View.INVISIBLE);
                very_good_mood_check_mark_in_mood_in_the_top_today.setVisibility(View.INVISIBLE);
            } else if (result == 3) {
                very_bad_mood_shade_in_habits_in_the_top_today.setVisibility(View.INVISIBLE);
                bad_mood_shade_in_habits_in_the_top_today.setVisibility(View.INVISIBLE);
                ok_mood_shade_in_habits_in_the_top_today.setVisibility(View.VISIBLE);
                good_mood_shade_in_habits_in_the_top_today.setVisibility(View.INVISIBLE);
                very_good_mood_shade_in_habits_in_the_top_today.setVisibility(View.INVISIBLE);
                very_bad_mood_check_mark_in_mood_in_the_top_today.setVisibility(View.INVISIBLE);
                bad_mood_check_mark_in_mood_in_the_top_today.setVisibility(View.INVISIBLE);
                ok_mood_check_mark_in_mood_in_the_top_today.setVisibility(View.VISIBLE);
                good_mood_check_mark_in_mood_in_the_top_today.setVisibility(View.INVISIBLE);
                very_good_mood_check_mark_in_mood_in_the_top_today.setVisibility(View.INVISIBLE);
            } else if (result == 4) {
                very_bad_mood_shade_in_habits_in_the_top_today.setVisibility(View.INVISIBLE);
                bad_mood_shade_in_habits_in_the_top_today.setVisibility(View.INVISIBLE);
                ok_mood_shade_in_habits_in_the_top_today.setVisibility(View.INVISIBLE);
                good_mood_shade_in_habits_in_the_top_today.setVisibility(View.VISIBLE);
                very_good_mood_shade_in_habits_in_the_top_today.setVisibility(View.INVISIBLE);
                very_bad_mood_check_mark_in_mood_in_the_top_today.setVisibility(View.INVISIBLE);
                bad_mood_check_mark_in_mood_in_the_top_today.setVisibility(View.INVISIBLE);
                ok_mood_check_mark_in_mood_in_the_top_today.setVisibility(View.INVISIBLE);
                good_mood_check_mark_in_mood_in_the_top_today.setVisibility(View.VISIBLE);
                very_good_mood_check_mark_in_mood_in_the_top_today.setVisibility(View.INVISIBLE);
            } else if (result == 5) {
                very_bad_mood_shade_in_habits_in_the_top_today.setVisibility(View.INVISIBLE);
                bad_mood_shade_in_habits_in_the_top_today.setVisibility(View.INVISIBLE);
                ok_mood_shade_in_habits_in_the_top_today.setVisibility(View.INVISIBLE);
                good_mood_shade_in_habits_in_the_top_today.setVisibility(View.INVISIBLE);
                very_good_mood_shade_in_habits_in_the_top_today.setVisibility(View.VISIBLE);
                very_bad_mood_check_mark_in_mood_in_the_top_today.setVisibility(View.INVISIBLE);
                bad_mood_check_mark_in_mood_in_the_top_today.setVisibility(View.INVISIBLE);
                ok_mood_check_mark_in_mood_in_the_top_today.setVisibility(View.INVISIBLE);
                good_mood_check_mark_in_mood_in_the_top_today.setVisibility(View.INVISIBLE);
                very_good_mood_check_mark_in_mood_in_the_top_today.setVisibility(View.VISIBLE);
            }
        }
    }

    private void button_listen_at_the_top() {
        if (getView() != null) {
            Button very_bad_mood_button_in_habits_in_the_top_today = getView().findViewById(R.id.b_very_bad_mood);
            Button bad_mood_button_in_habits_in_the_top_today = getView().findViewById(R.id.b_bad_mood);
            Button ok_mood_button_in_habits_in_the_top_today = getView().findViewById(R.id.b_ok_mood);
            Button good_mood_button_in_habits_in_the_top_today = getView().findViewById(R.id.b_good_mood);
            Button very_good_mood_button_in_habits_in_the_top_today = getView().findViewById(R.id.b_very_good_mood);
            final View very_bad_mood_shade_in_habits_in_the_top_today = getView().findViewById(R.id.view_background1);
            final View bad_mood_shade_in_habits_in_the_top_today = getView().findViewById(R.id.view_background2);
            final View ok_mood_shade_in_habits_in_the_top_today = getView().findViewById(R.id.view_background3);
            final View good_mood_shade_in_habits_in_the_top_today = getView().findViewById(R.id.view_background4);
            final View very_good_mood_shade_in_habits_in_the_top_today = getView().findViewById(R.id.very_good_mood_check);
            very_bad_mood_button_in_habits_in_the_top_today.setOnClickListener(view -> {
                if (very_bad_mood_shade_in_habits_in_the_top_today.getVisibility() == View.VISIBLE) {
                    make_the_buttons_in_the_top_mood(0);
                    save_the_input_for_good_habit_input(0, System.currentTimeMillis());
                } else {
                    make_the_buttons_in_the_top_mood(1);
                    save_the_input_for_good_habit_input(1, System.currentTimeMillis());
                }
                color_the_calender();
                divide_it_into_weeks();
                makeEverythingAverageMood();
                drawTheMoodLineChart();
                drawTheRightBarChartMood();
                line_chart_for_streak.fitScreen();
            });
            bad_mood_button_in_habits_in_the_top_today.setOnClickListener(view -> {
                if (bad_mood_shade_in_habits_in_the_top_today.getVisibility() == View.VISIBLE) {
                    make_the_buttons_in_the_top_mood(0);
                    save_the_input_for_good_habit_input(0, System.currentTimeMillis());
                } else {
                    make_the_buttons_in_the_top_mood(2);
                    save_the_input_for_good_habit_input(2, System.currentTimeMillis());
                }
                color_the_calender();
                divide_it_into_weeks();
                makeEverythingAverageMood();
                drawTheMoodLineChart();
                drawTheRightBarChartMood();
                line_chart_for_streak.fitScreen();
            });
            ok_mood_button_in_habits_in_the_top_today.setOnClickListener(view -> {
                if (ok_mood_shade_in_habits_in_the_top_today.getVisibility() == View.VISIBLE) {
                    make_the_buttons_in_the_top_mood(0);
                    save_the_input_for_good_habit_input(0, System.currentTimeMillis());
                } else {
                    make_the_buttons_in_the_top_mood(3);
                    save_the_input_for_good_habit_input(3, System.currentTimeMillis());
                }
                color_the_calender();
                divide_it_into_weeks();
                makeEverythingAverageMood();
                drawTheMoodLineChart();
                drawTheRightBarChartMood();
                line_chart_for_streak.fitScreen();
            });
            good_mood_button_in_habits_in_the_top_today.setOnClickListener(view -> {
                if (good_mood_shade_in_habits_in_the_top_today.getVisibility() == View.VISIBLE) {
                    make_the_buttons_in_the_top_mood(0);
                    save_the_input_for_good_habit_input(0, System.currentTimeMillis());
                } else {
                    make_the_buttons_in_the_top_mood(4);
                    save_the_input_for_good_habit_input(4, System.currentTimeMillis());
                }
                color_the_calender();
                divide_it_into_weeks();
                makeEverythingAverageMood();
                drawTheMoodLineChart();
                drawTheRightBarChartMood();
                line_chart_for_streak.fitScreen();
            });
            very_good_mood_button_in_habits_in_the_top_today.setOnClickListener(view -> {
                if (very_good_mood_shade_in_habits_in_the_top_today.getVisibility() == View.VISIBLE) {
                    make_the_buttons_in_the_top_mood(0);
                    save_the_input_for_good_habit_input(0, System.currentTimeMillis());
                } else {
                    make_the_buttons_in_the_top_mood(5);
                    save_the_input_for_good_habit_input(5, System.currentTimeMillis());
                }
                color_the_calender();
                divide_it_into_weeks();
                makeEverythingAverageMood();
                drawTheMoodLineChart();
                drawTheRightBarChartMood();
                line_chart_for_streak.fitScreen();
            });
        }
    }

    private void set_up_buttons_once() {
        make_the_buttons_in_the_top_mood(return_color_of_days(System.currentTimeMillis()));
    }

    private void makeEverythingAverageMood() {
        if (getView() != null) {
            TextView text_showing_average_mood_number = getView().findViewById(R.id.tv_average_mark);
            View location_above_bmi_weight = getView().findViewById(R.id.location_average_chart);
            if (history_of_mood.isEmpty()) {
                makeEverythingGoForAverageMood(0);
                return;
            } else {
                makeEverythingGoForAverageMood(1);
            }
            String very_bad_color = return_the_color_of_mood(1);
            String bad_color = return_the_color_of_mood(2);
            String ok_color = return_the_color_of_mood(3);
            String good_color = return_the_color_of_mood(4);
            String very_good_color = return_the_color_of_mood(5);
            float average = 0;
            for (Map.Entry<Long, Integer> map : history_of_mood.entrySet()) {
                average = average + map.getValue();
            }
            average = average / history_of_mood.size();
            Drawable back_ground_location = location_above_bmi_weight.getBackground();
            float position_in_map = (average - 1) / 4;
            if (average <= 1.5) {
                back_ground_location.setTint(Color.parseColor(very_bad_color));
                text_showing_average_mood_number.setTextColor(Color.parseColor(very_bad_color));
            } else if (average <= 2.5) {
                back_ground_location.setTint(Color.parseColor(bad_color));
                text_showing_average_mood_number.setTextColor(Color.parseColor(bad_color));
            } else if (average <= 3.5) {
                back_ground_location.setTint(Color.parseColor(ok_color));
                text_showing_average_mood_number.setTextColor(Color.parseColor(ok_color));
            } else if (average <= 4.5) {
                back_ground_location.setTint(Color.parseColor(good_color));
                text_showing_average_mood_number.setTextColor(Color.parseColor(good_color));
            } else {
                back_ground_location.setTint(Color.parseColor(very_good_color));
                text_showing_average_mood_number.setTextColor(Color.parseColor(very_good_color));
            }
            ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams) location_above_bmi_weight.getLayoutParams();
            params.horizontalBias = position_in_map;
            location_above_bmi_weight.setLayoutParams(params);
            if ((int) average == average) {
                text_showing_average_mood_number.setText(String.valueOf((int) average));
            } else {
                text_showing_average_mood_number.setText(String.format("%.1f", average));
            }

        }
    }

    private void makeEverythingGoForAverageMood(int which) {
        TextView text_showing_average_mood_number = getView().findViewById(R.id.tv_average_mark);
        View very_bad_mood_in_habits_in_the_average_mood = getView().findViewById(R.id.view_mood1);
        View bad_mood_in_habits_in_the_average_mood = getView().findViewById(R.id.view_mood2);
        View ok_mood_in_habits_in_the_average_mood = getView().findViewById(R.id.view_mood3);
        View good_mood_in_habits_in_the_average_mood = getView().findViewById(R.id.view_mood4);
        View very_good_mood_in_habits_in_the_average_mood = getView().findViewById(R.id.view_mood5);
        View location_above_bmi_weight = getView().findViewById(R.id.location_average_chart);
        ProgressBar progress_bar_number_one_mood = getView().findViewById(R.id.pb_1);
        ProgressBar progress_bar_number_two_mood = getView().findViewById(R.id.pb_2);
        ProgressBar progress_bar_number_three_mood = getView().findViewById(R.id.pb_3);
        ProgressBar progress_bar_number_four_mood = getView().findViewById(R.id.pb_4);
        ProgressBar progress_bar_number_five_mood = getView().findViewById(R.id.pb_5);
        TextView text_view_saying_that_there_is_not_enough_data_to_draw_this_chart_for_average_bar = getView().findViewById(R.id.tv_not_enough_data_average);
        if (which == 0) {
            text_view_saying_that_there_is_not_enough_data_to_draw_this_chart_for_average_bar.setVisibility(View.VISIBLE);
            text_showing_average_mood_number.setVisibility(View.INVISIBLE);
            very_bad_mood_in_habits_in_the_average_mood.setVisibility(View.INVISIBLE);
            bad_mood_in_habits_in_the_average_mood.setVisibility(View.INVISIBLE);
            ok_mood_in_habits_in_the_average_mood.setVisibility(View.INVISIBLE);
            good_mood_in_habits_in_the_average_mood.setVisibility(View.INVISIBLE);
            very_good_mood_in_habits_in_the_average_mood.setVisibility(View.INVISIBLE);
            location_above_bmi_weight.setVisibility(View.INVISIBLE);
            progress_bar_number_one_mood.setVisibility(View.INVISIBLE);
            progress_bar_number_two_mood.setVisibility(View.INVISIBLE);
            progress_bar_number_three_mood.setVisibility(View.INVISIBLE);
            progress_bar_number_four_mood.setVisibility(View.INVISIBLE);
            progress_bar_number_five_mood.setVisibility(View.INVISIBLE);
        } else if (which == 1) {
            text_view_saying_that_there_is_not_enough_data_to_draw_this_chart_for_average_bar.setVisibility(View.INVISIBLE);
            text_showing_average_mood_number.setVisibility(View.VISIBLE);
            very_bad_mood_in_habits_in_the_average_mood.setVisibility(View.VISIBLE);
            bad_mood_in_habits_in_the_average_mood.setVisibility(View.VISIBLE);
            ok_mood_in_habits_in_the_average_mood.setVisibility(View.VISIBLE);
            good_mood_in_habits_in_the_average_mood.setVisibility(View.VISIBLE);
            very_good_mood_in_habits_in_the_average_mood.setVisibility(View.VISIBLE);
            location_above_bmi_weight.setVisibility(View.VISIBLE);
            progress_bar_number_one_mood.setVisibility(View.VISIBLE);
            progress_bar_number_two_mood.setVisibility(View.VISIBLE);
            progress_bar_number_three_mood.setVisibility(View.VISIBLE);
            progress_bar_number_four_mood.setVisibility(View.VISIBLE);
            progress_bar_number_five_mood.setVisibility(View.VISIBLE);
        }
    }

    private void drawTheMoodLineChart() {
        if (getView() != null) {
            TextView text_saying_month_year_in_the_chart_mood_tracker = getView().findViewById(R.id.tv_month_year_chart);
            Button button_to_show_forward_above_mood_tracker_graph = getView().findViewById(R.id.bt_line_chart_forward);
            View view_to_show_back_above_mood_tracker_graph = getView().findViewById(R.id.view_line_chart_back);

            Calendar calendar = Calendar.getInstance();
            int real_month = calendar.get(Calendar.MONTH);
            int real_year = calendar.get(Calendar.YEAR);
            if (month_for_mood_chart == -1) {
                month_for_mood_chart = real_month;
                year_for_mood_chart = real_year;
                button_to_show_forward_above_mood_tracker_graph.setVisibility(View.INVISIBLE);
                view_to_show_back_above_mood_tracker_graph.setVisibility(View.INVISIBLE);
            } else if (month_for_mood_chart == real_month && year_for_mood_chart == real_year) {
                button_to_show_forward_above_mood_tracker_graph.setVisibility(View.INVISIBLE);
                view_to_show_back_above_mood_tracker_graph.setVisibility(View.INVISIBLE);
            } else {
                button_to_show_forward_above_mood_tracker_graph.setVisibility(View.VISIBLE);
                view_to_show_back_above_mood_tracker_graph.setVisibility(View.VISIBLE);
            }
            text_saying_month_year_in_the_chart_mood_tracker.setText(returnMonth(month_for_mood_chart).concat(" ").concat(String.valueOf(year_for_mood_chart)));


            int color_card = color;
            line_chart_for_streak = getView().findViewById(R.id.line_chart);
            TextView text_view_saying_that_there_is_not_enough_data_to_draw_this_line_chart = getView().findViewById(R.id.tv_not_enough_data_line_chart);
            line_chart_for_streak.invalidate();
            line_chart_for_streak.clear();
            ArrayList<Entry> y_values = new ArrayList<>();
            String string_to_split = returnDataMoodForMoodLineChart();
            if (string_to_split.equals("")) {
                text_view_saying_that_there_is_not_enough_data_to_draw_this_line_chart.setVisibility(View.VISIBLE);
                line_chart_for_streak.setVisibility(View.INVISIBLE);
                return;
            }
            text_view_saying_that_there_is_not_enough_data_to_draw_this_line_chart.setVisibility(View.INVISIBLE);
            line_chart_for_streak.setVisibility(View.VISIBLE);
            String[] split_data = string_to_split.split("big_split");
            String[] small_split_for_last_value = split_data[split_data.length - 1].split("small_split");
            int last_value = Integer.parseInt(small_split_for_last_value[0]);

            for (String split_datum : split_data) {
                String[] small_split = split_datum.split("small_split");
                switch (small_split[1]) {
                    case "1":
                        y_values.add(new Entry(Integer.parseInt(small_split[0]), Integer.parseInt(small_split[1]), returnMoodLogoVeryBad()));
                        break;
                    case "2":
                        y_values.add(new Entry(Integer.parseInt(small_split[0]), Integer.parseInt(small_split[1]), returnMoodLogoBad()));
                        break;
                    case "3":
                        y_values.add(new Entry(Integer.parseInt(small_split[0]), Integer.parseInt(small_split[1]), returnMoodLogoOk()));
                        break;
                    case "4":
                        y_values.add(new Entry(Integer.parseInt(small_split[0]), Integer.parseInt(small_split[1]), returnMoodLogoGood()));
                        break;
                    case "5":
                        y_values.add(new Entry(Integer.parseInt(small_split[0]), Integer.parseInt(small_split[1]), returnMoodLogoVeryGood()));
                        break;
                }
            }
            line_chart_for_streak.fitScreen();
            line_chart_for_streak.setPadding(0, 0, 0, 0);
            line_chart_for_streak.getXAxis().setDrawGridLines(false);
            line_chart_for_streak.getAxisLeft().setDrawGridLines(true);
            line_chart_for_streak.getAxisRight().setDrawGridLines(false);

            XAxis xAxis = line_chart_for_streak.getXAxis();
            xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
            xAxis.setLabelCount(y_values.size(), false);
            xAxis.setGranularity(1.0f);
            xAxis.setGranularityEnabled(true);
            xAxis.setAvoidFirstLastClipping(true);
            xAxis.setAxisMaximum(last_value + 0.1f);
            line_chart_for_streak.getDescription().setText("");
            line_chart_for_streak.setScaleEnabled(false);
            line_chart_for_streak.getLegend().setEnabled(false);

            line_chart_for_streak.getAxisRight().setEnabled(false);
            line_chart_for_streak.getAxisLeft().setAxisMinimum(1);
            line_chart_for_streak.getAxisLeft().setDrawAxisLine(false);
            line_chart_for_streak.getAxisLeft().setGranularity(1.0f);
            line_chart_for_streak.getAxisLeft().setGranularityEnabled(true);
            line_chart_for_streak.getAxisLeft().setAxisMaximum(5);

            LineDataSet lineDataSet = new LineDataSet(y_values, "data");
            lineDataSet.setColor(color_card);
            ArrayList<ILineDataSet> dataSets = new ArrayList<>();
            dataSets.add(lineDataSet);
            LineData data = new LineData(dataSets);
            line_chart_for_streak.setData(data);
            line_chart_for_streak.getData().setHighlightEnabled(false);
            lineDataSet.setLineWidth(3f);
            lineDataSet.setCircleRadius(10f);
            lineDataSet.setCircleHoleColor(Color.WHITE);
            lineDataSet.setCircleColor(Color.WHITE);
            lineDataSet.setDrawValues(false);
            line_chart_for_streak.invalidate();
            line_chart_for_streak.setVisibleXRangeMaximum(9);
            line_chart_for_streak.moveViewToX(y_values.size());
        }
    }

    private void backAndFrontButtonListenForTheGraphMood() {
        if (getView() != null) {
            Button button_to_show_back_above_mood_tracker_graph = getView().findViewById(R.id.bt_line_chart_back);
            Button button_to_show_forward_above_mood_tracker_graph = getView().findViewById(R.id.bt_line_chart_forward);
            button_to_show_back_above_mood_tracker_graph.setOnClickListener(view -> {
                if ((month_for_mood_chart) == 0) {
                    month_for_mood_chart = 11;
                    year_for_mood_chart--;
                } else {
                    month_for_mood_chart--;
                }
                drawTheMoodLineChart();
            });
            button_to_show_forward_above_mood_tracker_graph.setOnClickListener(view -> {
                if ((month_for_mood_chart) == 11) {
                    month_for_mood_chart = 0;
                    year_for_mood_chart++;
                } else {
                    month_for_mood_chart++;
                }
                drawTheMoodLineChart();
            });
        }
    }

    private String returnDataMoodForMoodLineChart() {
        String return_me = "";
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year_for_mood_chart);
        calendar.set(Calendar.MONTH, month_for_mood_chart);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        Calendar new_calender = Calendar.getInstance();
        for (int i = 1; i <= calendar.getActualMaximum(Calendar.DAY_OF_MONTH); i++) {
            new_calender.set(year_for_mood_chart, month_for_mood_chart, i);
            long time_in_milli = new_calender.getTimeInMillis();
            if (Simplify_the_time.return_time_in_midnight(time_in_milli) >= Simplify_the_time.return_time_in_midnight(start_date)) {
                if (Simplify_the_time.return_time_in_midnight(time_in_milli) <= Simplify_the_time.return_time_in_midnight(System.currentTimeMillis())) {
                    if (return_color_of_days(time_in_milli) == 1) {
                        return_me = return_me.concat(String.valueOf(i)).concat("small_split").concat("1").concat("big_split");
                    } else if (return_color_of_days(time_in_milli) == 2) {
                        return_me = return_me.concat(String.valueOf(i)).concat("small_split").concat("2").concat("big_split");
                    } else if (return_color_of_days(time_in_milli) == 3) {
                        return_me = return_me.concat(String.valueOf(i)).concat("small_split").concat("3").concat("big_split");
                    } else if (return_color_of_days(time_in_milli) == 4) {
                        return_me = return_me.concat(String.valueOf(i)).concat("small_split").concat("4").concat("big_split");
                    } else if (return_color_of_days(time_in_milli) == 5) {
                        return_me = return_me.concat(String.valueOf(i)).concat("small_split").concat("5").concat("big_split");
                    }
                }
            }
        }
        return return_me;
    }

    private Drawable returnMoodLogoVeryBad() {
        String very_bad_color = return_the_color_of_mood(1);
        Drawable unwrappedDrawable = AppCompatResources.getDrawable(getContext(), R.drawable.round_sentiment_very_dissatisfied_24).mutate();
        Drawable wrappedDrawable = DrawableCompat.wrap(unwrappedDrawable);
        DrawableCompat.setTint(wrappedDrawable, Color.parseColor(very_bad_color));
        return wrappedDrawable;
    }

    private Drawable returnMoodLogoBad() {
        String bad_color = return_the_color_of_mood(2);
        Drawable unwrappedDrawable = AppCompatResources.getDrawable(getContext(), R.drawable.round_mood_bad_24).mutate();
        Drawable wrappedDrawable = DrawableCompat.wrap(unwrappedDrawable);
        DrawableCompat.setTint(wrappedDrawable, Color.parseColor(bad_color));
        return wrappedDrawable;
    }

    private Drawable returnMoodLogoOk() {
        String ok_color = return_the_color_of_mood(3);
        Drawable unwrappedDrawable = AppCompatResources.getDrawable(getContext(), R.drawable.round_face_24).mutate();
        Drawable wrappedDrawable = DrawableCompat.wrap(unwrappedDrawable);
        DrawableCompat.setTint(wrappedDrawable, Color.parseColor(ok_color));
        return wrappedDrawable;
    }

    private Drawable returnMoodLogoGood() {
        String good_color = return_the_color_of_mood(4);
        Drawable unwrappedDrawable = AppCompatResources.getDrawable(getContext(), R.drawable.round_mood_24).mutate();
        Drawable wrappedDrawable = DrawableCompat.wrap(unwrappedDrawable);
        DrawableCompat.setTint(wrappedDrawable, Color.parseColor(good_color));
        return wrappedDrawable;
    }

    private Drawable returnMoodLogoVeryGood() {
        String very_good_color = return_the_color_of_mood(5);
        Drawable unwrappedDrawable = AppCompatResources.getDrawable(getContext(), R.drawable.round_sentiment_very_satisfied_24).mutate();
        Drawable wrappedDrawable = DrawableCompat.wrap(unwrappedDrawable);
        DrawableCompat.setTint(wrappedDrawable, Color.parseColor(very_good_color));
        return wrappedDrawable;
    }

    private void setAllTheFacesInTheMood() {
        View veryBadMoodInHabitsInTheTopToday = getView().findViewById(R.id.view_very_bad_mood);
        View badMoodInHabitsInTheTopToday = getView().findViewById(R.id.view_bad_mood);
        View okMoodInHabitsInTheTopToday = getView().findViewById(R.id.view_ok_mood);
        View goodMoodInHabitsInTheTopToday = getView().findViewById(R.id.view_good_mood);
        View veryGoodMoodInHabitsInTheTopToday = getView().findViewById(R.id.view_very_good_mood);

        View very_bad_mood_in_habits = getView().findViewById(R.id.view_very_bad_mood_edit);
        View bad_mood_in_habits = getView().findViewById(R.id.view_bad_mood_edit);
        View ok_mood_in_habits = getView().findViewById(R.id.view_ok_mood_edit);
        View good_mood_in_habits = getView().findViewById(R.id.view_good_mood_edit);
        View very_good_mood_in_habits = getView().findViewById(R.id.view_very_good_mood_edit);

        View very_bad_mood_in_habits_in_the_average_mood = getView().findViewById(R.id.view_mood1);
        View bad_mood_in_habits_in_the_average_mood = getView().findViewById(R.id.view_mood2);
        View ok_mood_in_habits_in_the_average_mood = getView().findViewById(R.id.view_mood3);
        View good_mood_in_habits_in_the_average_mood = getView().findViewById(R.id.view_mood4);
        View very_good_mood_in_habits_in_the_average_mood = getView().findViewById(R.id.view_mood5);

        veryBadMoodInHabitsInTheTopToday.setBackground(returnMoodLogoVeryBad());
        very_bad_mood_in_habits.setBackground(returnMoodLogoVeryBad());
        very_bad_mood_in_habits_in_the_average_mood.setBackground(returnMoodLogoVeryBad());

        badMoodInHabitsInTheTopToday.setBackground(returnMoodLogoBad());
        bad_mood_in_habits.setBackground(returnMoodLogoBad());
        bad_mood_in_habits_in_the_average_mood.setBackground(returnMoodLogoBad());

        okMoodInHabitsInTheTopToday.setBackground(returnMoodLogoOk());
        ok_mood_in_habits.setBackground(returnMoodLogoOk());
        ok_mood_in_habits_in_the_average_mood.setBackground(returnMoodLogoOk());

        goodMoodInHabitsInTheTopToday.setBackground(returnMoodLogoGood());
        good_mood_in_habits.setBackground(returnMoodLogoGood());
        good_mood_in_habits_in_the_average_mood.setBackground(returnMoodLogoGood());
        veryGoodMoodInHabitsInTheTopToday.setBackground(returnMoodLogoVeryGood());
        very_good_mood_in_habits.setBackground(returnMoodLogoVeryGood());
        very_good_mood_in_habits_in_the_average_mood.setBackground(returnMoodLogoVeryGood());


    }

    private String returnTheDaysOfTheGoodHabit() {
        if (getView() != null) {
            int monday = 0, tuesday = 0, wednesday = 0, thursday = 0, friday = 0, saturday = 0, sunday = 0;
            float monday_average = 0, tuesday_average = 0, wednesday_average = 0, thursday_average = 0, friday_average = 0, saturday_average = 0, sunday_average = 0;
            for (Map.Entry<Long, Integer> map : history_of_mood.entrySet()) {
                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(map.getKey());
                if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY) {
                    monday = monday + 1;
                    monday_average = monday_average + map.getValue();
                } else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.TUESDAY) {
                    tuesday = tuesday + 1;
                    tuesday_average = tuesday_average + map.getValue();
                } else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.WEDNESDAY) {
                    wednesday = wednesday + 1;
                    wednesday_average = wednesday_average + map.getValue();
                } else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.THURSDAY) {
                    thursday = thursday + 1;
                    thursday_average = thursday_average + map.getValue();
                } else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY) {
                    friday = friday + 1;
                    friday_average = friday_average + map.getValue();
                } else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
                    saturday = saturday + 1;
                    saturday_average = saturday_average + map.getValue();
                } else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
                    sunday = sunday + 1;
                    sunday_average = sunday_average + map.getValue();
                }
            }
            if (monday != 0) {
                monday_average = monday_average / monday;
            }
            if (tuesday != 0) {
                tuesday_average = tuesday_average / tuesday;
            }
            if (wednesday != 0) {
                wednesday_average = wednesday_average / wednesday;
            }
            if (thursday != 0) {
                thursday_average = thursday_average / thursday;
            }
            if (friday != 0) {
                friday_average = friday_average / friday;
            }
            if (saturday != 0) {
                saturday_average = saturday_average / saturday;
            }
            if (sunday != 0) {
                sunday_average = sunday_average / sunday;
            }
            return String.valueOf(monday_average).concat("split").concat(String.valueOf(tuesday_average)).concat("split").concat(String.valueOf(wednesday_average)).concat("split").concat(String.valueOf(thursday_average)).concat("split").concat(String.valueOf(friday_average)).concat("split").concat(String.valueOf(saturday_average)).concat("split").concat(String.valueOf(sunday_average));
        }
        return "";
    }

    private void drawTheBarForAverageMood() {
        if (getView() != null) {
            TextView text_view_saying_that_there_is_not_enough_data_to_draw_this_chart_for_daily_relapse = getView().findViewById(R.id.tv_not_enough_data);
            BarChart chart_in_mood_about_the_average_for_each_month = getView().findViewById(R.id.bar_chart);
            BarChart chart_in_good_habits_about_how_many_times_for_each_days_of_week = getView().findViewById(R.id.bar_chart2);

            float max_days = 0;
            String days_of_week = returnTheDaysOfTheGoodHabit();
            String[] split_days_of_week = days_of_week.split("split");
            for (String s : split_days_of_week) {
                if (max_days < Float.parseFloat(s)) {
                    max_days = Float.parseFloat(s);
                }
            }
            if (max_days == 0) {
                text_view_saying_that_there_is_not_enough_data_to_draw_this_chart_for_daily_relapse.setVisibility(View.VISIBLE);
                chart_in_mood_about_the_average_for_each_month.setVisibility(View.INVISIBLE);
                chart_in_good_habits_about_how_many_times_for_each_days_of_week.setVisibility(View.INVISIBLE);
                return;
            } else {
                text_view_saying_that_there_is_not_enough_data_to_draw_this_chart_for_daily_relapse.setVisibility(View.INVISIBLE);
            }
            final float max_days_final = max_days;
            chart_in_good_habits_about_how_many_times_for_each_days_of_week.setVisibility(View.VISIBLE);
            chart_in_mood_about_the_average_for_each_month.setVisibility(View.INVISIBLE);

            CustomBarChartRenderer barChartRender = new CustomBarChartRenderer(chart_in_good_habits_about_how_many_times_for_each_days_of_week, chart_in_good_habits_about_how_many_times_for_each_days_of_week.getAnimator(), chart_in_good_habits_about_how_many_times_for_each_days_of_week.getViewPortHandler());
            barChartRender.setRadius(8);
            chart_in_good_habits_about_how_many_times_for_each_days_of_week.setRenderer(barChartRender);
            List<BarEntry> entries = new ArrayList<>();
            String[] xAxisLabels;
            int start_value;
            if (returnFirstDayOfWeek().equals("monday")) {
                xAxisLabels = new String[]{"M", "T", "W", "T", "F", "S", "S"};
                entries.add(new BarEntry(0, Float.parseFloat(split_days_of_week[0])));
                entries.add(new BarEntry(1, Float.parseFloat(split_days_of_week[1])));
                entries.add(new BarEntry(2, Float.parseFloat(split_days_of_week[2])));
                entries.add(new BarEntry(3, Float.parseFloat(split_days_of_week[3])));
                entries.add(new BarEntry(4, Float.parseFloat(split_days_of_week[4])));
                entries.add(new BarEntry(5, Float.parseFloat(split_days_of_week[5])));
                entries.add(new BarEntry(6, Float.parseFloat(split_days_of_week[6])));
                start_value = 0;
            } else if (returnFirstDayOfWeek().equals("tuesday")) {
                xAxisLabels = new String[]{"T", "W", "T", "F", "S", "S", "M"};
                entries.add(new BarEntry(0, Float.parseFloat(split_days_of_week[1])));
                entries.add(new BarEntry(1, Float.parseFloat(split_days_of_week[2])));
                entries.add(new BarEntry(2, Float.parseFloat(split_days_of_week[3])));
                entries.add(new BarEntry(3, Float.parseFloat(split_days_of_week[4])));
                entries.add(new BarEntry(4, Float.parseFloat(split_days_of_week[5])));
                entries.add(new BarEntry(5, Float.parseFloat(split_days_of_week[6])));
                entries.add(new BarEntry(6, Float.parseFloat(split_days_of_week[0])));
                start_value = 1;
            } else if (returnFirstDayOfWeek().equals("wednesday")) {
                xAxisLabels = new String[]{"W", "T", "F", "S", "S", "M", "T"};
                entries.add(new BarEntry(0, Float.parseFloat(split_days_of_week[2])));
                entries.add(new BarEntry(1, Float.parseFloat(split_days_of_week[3])));
                entries.add(new BarEntry(2, Float.parseFloat(split_days_of_week[4])));
                entries.add(new BarEntry(3, Float.parseFloat(split_days_of_week[5])));
                entries.add(new BarEntry(4, Float.parseFloat(split_days_of_week[6])));
                entries.add(new BarEntry(5, Float.parseFloat(split_days_of_week[0])));
                entries.add(new BarEntry(6, Float.parseFloat(split_days_of_week[1])));
                start_value = 2;
            } else if (returnFirstDayOfWeek().equals("thursday")) {
                xAxisLabels = new String[]{"T", "F", "S", "S", "M", "T", "W"};
                entries.add(new BarEntry(0, Float.parseFloat(split_days_of_week[3])));
                entries.add(new BarEntry(1, Float.parseFloat(split_days_of_week[4])));
                entries.add(new BarEntry(2, Float.parseFloat(split_days_of_week[5])));
                entries.add(new BarEntry(3, Float.parseFloat(split_days_of_week[6])));
                entries.add(new BarEntry(4, Float.parseFloat(split_days_of_week[0])));
                entries.add(new BarEntry(5, Float.parseFloat(split_days_of_week[1])));
                entries.add(new BarEntry(6, Float.parseFloat(split_days_of_week[2])));
                start_value = 3;
            } else if (returnFirstDayOfWeek().equals("friday")) {
                xAxisLabels = new String[]{"F", "S", "S", "M", "T", "W", "T"};
                entries.add(new BarEntry(0, Float.parseFloat(split_days_of_week[4])));
                entries.add(new BarEntry(1, Float.parseFloat(split_days_of_week[5])));
                entries.add(new BarEntry(2, Float.parseFloat(split_days_of_week[6])));
                entries.add(new BarEntry(3, Float.parseFloat(split_days_of_week[0])));
                entries.add(new BarEntry(4, Float.parseFloat(split_days_of_week[1])));
                entries.add(new BarEntry(5, Float.parseFloat(split_days_of_week[2])));
                entries.add(new BarEntry(6, Float.parseFloat(split_days_of_week[3])));
                start_value = 4;
            } else if (returnFirstDayOfWeek().equals("saturday")) {
                xAxisLabels = new String[]{"S", "S", "M", "T", "W", "T", "F"};
                entries.add(new BarEntry(0, Float.parseFloat(split_days_of_week[5])));
                entries.add(new BarEntry(1, Float.parseFloat(split_days_of_week[6])));
                entries.add(new BarEntry(2, Float.parseFloat(split_days_of_week[0])));
                entries.add(new BarEntry(3, Float.parseFloat(split_days_of_week[1])));
                entries.add(new BarEntry(4, Float.parseFloat(split_days_of_week[2])));
                entries.add(new BarEntry(5, Float.parseFloat(split_days_of_week[3])));
                entries.add(new BarEntry(6, Float.parseFloat(split_days_of_week[4])));
                start_value = 5;
            } else {
                xAxisLabels = new String[]{"S", "M", "T", "W", "T", "F", "S"};
                entries.add(new BarEntry(0, Float.parseFloat(split_days_of_week[6])));
                entries.add(new BarEntry(1, Float.parseFloat(split_days_of_week[0])));
                entries.add(new BarEntry(2, Float.parseFloat(split_days_of_week[1])));
                entries.add(new BarEntry(3, Float.parseFloat(split_days_of_week[2])));
                entries.add(new BarEntry(4, Float.parseFloat(split_days_of_week[3])));
                entries.add(new BarEntry(5, Float.parseFloat(split_days_of_week[4])));
                entries.add(new BarEntry(6, Float.parseFloat(split_days_of_week[5])));
                start_value = 6;
            }
            chart_in_good_habits_about_how_many_times_for_each_days_of_week.getXAxis().setValueFormatter(new IndexAxisValueFormatter(xAxisLabels));
            ValueFormatter valueFormatter = new ValueFormatter() { //value format here, here is the overridden method
                @Override
                public String getFormattedValue(float value) {
                    if (value == 0 || (value / max_days_final < 0.1)) {
                        return "";
                    } else {
                        if ((int) value == value) {
                            return "" + (int) value;
                        } else {
                            return String.format("%.1f", value);
                        }
                    }
                }
            };
            String very_bad_color = return_the_color_of_mood(1);
            String bad_color = return_the_color_of_mood(2);
            String ok_color = return_the_color_of_mood(3);
            String good_color = return_the_color_of_mood(4);
            String very_good_color = return_the_color_of_mood(5);
            int[] colors_for_bars = new int[7];
            int counter = 0;
            for (int i = start_value; i < start_value + 7; i++) {
                float average_value = Float.parseFloat(split_days_of_week[i % 7]);
                if (average_value <= 1.5) {
                    colors_for_bars[counter] = Color.parseColor(very_bad_color);
                } else if (average_value <= 2.5) {
                    colors_for_bars[counter] = Color.parseColor(bad_color);
                } else if (average_value <= 3.5) {
                    colors_for_bars[counter] = Color.parseColor(ok_color);
                } else if (average_value <= 4.5) {
                    colors_for_bars[counter] = Color.parseColor(good_color);
                } else {
                    colors_for_bars[counter] = Color.parseColor(very_good_color);
                }
                counter++;
            }
            BarDataSet set = new BarDataSet(entries, "BarDataSet");
            set.setColors(colors_for_bars);
            set.setValueTextColor(Color.WHITE);
            set.setValueTextSize(15);
            set.setValueTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
            BarData data = new BarData(set);
            data.setValueFormatter(valueFormatter);
            data.setBarWidth(0.9f);
            chart_in_good_habits_about_how_many_times_for_each_days_of_week.setData(data);
            chart_in_good_habits_about_how_many_times_for_each_days_of_week.invalidate();
            chart_in_good_habits_about_how_many_times_for_each_days_of_week.setScaleEnabled(false);
            chart_in_good_habits_about_how_many_times_for_each_days_of_week.getLegend().setEnabled(false);
            chart_in_good_habits_about_how_many_times_for_each_days_of_week.setExtraOffsets(0, 0, 0, 0);
            Description description = new Description();
            description.setText("");
            chart_in_good_habits_about_how_many_times_for_each_days_of_week.setDescription(description);
            XAxis xAxis = chart_in_good_habits_about_how_many_times_for_each_days_of_week.getXAxis();
            xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
            data.setHighlightEnabled(false);
            data.setBarWidth(0.7f);
            chart_in_good_habits_about_how_many_times_for_each_days_of_week.getAxisLeft().setAxisMinimum(0f);
            chart_in_good_habits_about_how_many_times_for_each_days_of_week.getAxisRight().setAxisMinimum(0f);
            chart_in_good_habits_about_how_many_times_for_each_days_of_week.getXAxis().setDrawGridLines(false);
            chart_in_good_habits_about_how_many_times_for_each_days_of_week.getAxisLeft().setDrawAxisLine(false);
            chart_in_good_habits_about_how_many_times_for_each_days_of_week.getAxisRight().setDrawAxisLine(false);
            chart_in_good_habits_about_how_many_times_for_each_days_of_week.getAxisLeft().setDrawLabels(false);
            chart_in_good_habits_about_how_many_times_for_each_days_of_week.getAxisRight().setDrawLabels(false);
            chart_in_good_habits_about_how_many_times_for_each_days_of_week.setDrawValueAboveBar(false);
        }
    }


    private String returnFirstDayOfWeek() {
        Calendar calendar = Calendar.getInstance();
        if (calendar.getFirstDayOfWeek() == Calendar.MONDAY) {
            return this.getResources().getString(R.string.Monday) ;
        } else if (calendar.getFirstDayOfWeek() == Calendar.TUESDAY) {
            return this.getResources().getString(R.string.Tuesday) ;
        } else if (calendar.getFirstDayOfWeek() == Calendar.WEDNESDAY) {
            return this.getResources().getString(R.string.Wednesday) ;
        } else if (calendar.getFirstDayOfWeek() == Calendar.THURSDAY) {
            return this.getResources().getString(R.string.Thursday) ;
        } else if (calendar.getFirstDayOfWeek() == Calendar.FRIDAY) {
            return this.getResources().getString(R.string.Friday) ;
        } else if (calendar.getFirstDayOfWeek() == Calendar.SATURDAY) {
            return this.getResources().getString(R.string.Saturday) ;
        } else {
            return this.getResources().getString(R.string.Sunday) ;
        }
    }

    private String returnDataForBarChartYearly() {
        if (getView() != null) {
            int january = 0,february = 0,march = 0,april = 0,may = 0,june = 0, july = 0, august = 0, september = 0,october = 0, november = 0, december = 0;
            float jan_avg = 0, feb_average = 0, march_average = 0, april_average = 0, may_average = 0, june_average = 0, july_average = 0, august_average = 0, september_average = 0, october_average = 0, november_average = 0, december_average = 0;
            for (Map.Entry<Long, Integer> map : history_of_mood.entrySet()) {
                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(map.getKey());
                if (calendar.get(Calendar.MONTH) == Calendar.JANUARY) {
                    january = january + 1;
                    jan_avg = jan_avg + map.getValue();
                } else if (calendar.get(Calendar.MONTH) == Calendar.FEBRUARY) {
                    february = february + 1;
                    feb_average = feb_average + map.getValue();
                } else if (calendar.get(Calendar.MONTH) == Calendar.MARCH) {
                    march = march + 1;
                    march_average = march_average + map.getValue();
                } else if (calendar.get(Calendar.MONTH) == Calendar.APRIL) {
                    april = april + 1;
                    april_average = april_average + map.getValue();
                } else if (calendar.get(Calendar.MONTH) == Calendar.MAY) {
                    may = may + 1;
                    may_average = may_average + map.getValue();
                } else if (calendar.get(Calendar.MONTH) == Calendar.JUNE) {
                    june = june + 1;
                    june_average = june_average + map.getValue();
                } else if (calendar.get(Calendar.MONTH) == Calendar.JULY) {
                    july = july + 1;
                    july_average = july_average + map.getValue();
                } else if (calendar.get(Calendar.MONTH) == Calendar.AUGUST) {
                    august = august + 1;
                    august_average = august_average + map.getValue();
                } else if (calendar.get(Calendar.MONTH) == Calendar.SEPTEMBER) {
                    september = september + 1;
                    september_average = september_average + map.getValue();
                } else if (calendar.get(Calendar.MONTH) == Calendar.OCTOBER) {
                    october = october + 1;
                    october_average = october_average + map.getValue();
                } else if (calendar.get(Calendar.MONTH) == Calendar.NOVEMBER) {
                    november = november + 1;
                    november_average = november_average + map.getValue();
                } else if (calendar.get(Calendar.MONTH) == Calendar.DECEMBER) {
                    december = december + 1;
                    december_average = december_average + map.getValue();
                }
            }
            if (january != 0) {
                jan_avg = jan_avg / january;
            }
            if (february != 0) {
                feb_average = feb_average / february;
            }
            if (march != 0) {
                march_average = march_average / march;
            }
            if (april != 0) {
                april_average = april_average / april;
            }
            if (may != 0) {
                may_average = may_average / may;
            }
            if (june != 0) {
                june_average = june_average / june;
            }
            if (july != 0) {
                july_average = july_average / july;
            }
            if (august != 0) {
                august_average = august_average / august;
            }
            if (september != 0) {
                september_average = september_average / september;
            }
            if (october != 0) {
                october_average = october_average / october;
            }
            if (november != 0) {
                november_average = november_average / november;
            }
            if (december != 0) {
                december_average = december_average / december;
            }
            return String.valueOf(jan_avg).concat("split").concat(String.valueOf(feb_average)).concat("split").concat(String.valueOf(march_average)).concat("split").concat(String.valueOf(april_average)).concat("split").concat(String.valueOf(may_average)).concat("split").concat(String.valueOf(june_average)).concat("split").concat(String.valueOf(july_average)).concat("split").concat(String.valueOf(august_average)).concat("split").concat(String.valueOf(september_average)).concat("split").concat(String.valueOf(october_average)).concat("split").concat(String.valueOf(november_average)).concat("split").concat(String.valueOf(december_average));
        }
        return "";
    }

    private void drawBarForAverageMoodOverTheYear() {
        if (getView() != null) {
            float max_days = 0;
            String days_of_week = returnDataForBarChartYearly();
            String[] split_days_of_week = days_of_week.split("split");
            for (String s : split_days_of_week) {
                if (max_days < Float.parseFloat(s)) {
                    max_days = Float.parseFloat(s);
                }
            }
            TextView text_view_saying_that_there_is_not_enough_data_to_draw_this_chart_for_daily_relapse = getView().findViewById(R.id.tv_not_enough_data);
            BarChart chart_in_mood_about_the_average_for_each_month = getView().findViewById(R.id.bar_chart);
            BarChart chart_in_good_habits_about_how_many_times_for_each_days_of_week = getView().findViewById(R.id.bar_chart2);
            if (max_days == 0) {
                text_view_saying_that_there_is_not_enough_data_to_draw_this_chart_for_daily_relapse.setVisibility(View.VISIBLE);
                chart_in_mood_about_the_average_for_each_month.setVisibility(View.INVISIBLE);
                chart_in_good_habits_about_how_many_times_for_each_days_of_week.setVisibility(View.INVISIBLE);
                return;
            } else {
                text_view_saying_that_there_is_not_enough_data_to_draw_this_chart_for_daily_relapse.setVisibility(View.INVISIBLE);
            }
            final float max_days_final = max_days;
            chart_in_good_habits_about_how_many_times_for_each_days_of_week.setVisibility(View.INVISIBLE);
            chart_in_mood_about_the_average_for_each_month.setVisibility(View.VISIBLE);
            CustomBarChartRenderer barChartRender = new CustomBarChartRenderer(chart_in_mood_about_the_average_for_each_month, chart_in_mood_about_the_average_for_each_month.getAnimator(), chart_in_mood_about_the_average_for_each_month.getViewPortHandler());
            barChartRender.setRadius(8);
            chart_in_mood_about_the_average_for_each_month.setRenderer(barChartRender);
            List<BarEntry> entries = new ArrayList<>();
            String[] xAxisLabels;
            xAxisLabels = new String[]{"J", "F", "M", "A", "M", "J", "J", "A", "S", "O", "N", "D"};
            entries.add(new BarEntry(0, Float.parseFloat(split_days_of_week[0])));
            entries.add(new BarEntry(1, Float.parseFloat(split_days_of_week[1])));
            entries.add(new BarEntry(2, Float.parseFloat(split_days_of_week[2])));
            entries.add(new BarEntry(3, Float.parseFloat(split_days_of_week[3])));
            entries.add(new BarEntry(4, Float.parseFloat(split_days_of_week[4])));
            entries.add(new BarEntry(5, Float.parseFloat(split_days_of_week[5])));
            entries.add(new BarEntry(6, Float.parseFloat(split_days_of_week[6])));
            entries.add(new BarEntry(7, Float.parseFloat(split_days_of_week[7])));
            entries.add(new BarEntry(8, Float.parseFloat(split_days_of_week[8])));
            entries.add(new BarEntry(9, Float.parseFloat(split_days_of_week[9])));
            entries.add(new BarEntry(10, Float.parseFloat(split_days_of_week[10])));
            entries.add(new BarEntry(11, Float.parseFloat(split_days_of_week[11])));
            chart_in_mood_about_the_average_for_each_month.getXAxis().setValueFormatter(new IndexAxisValueFormatter(xAxisLabels));
            ValueFormatter valueFormatter = new ValueFormatter() { //value format here, here is the overridden method
                @Override
                public String getFormattedValue(float value) {
                    if (value == 0 || (value / max_days_final < 0.1)) {
                        return "";
                    } else {
                        if ((int) value == value) {
                            return "" + (int) value;
                        } else {
                            return String.format("%.1f", value);
                        }
                    }
                }
            };
            String very_bad_color = return_the_color_of_mood(1);
            String bad_color = return_the_color_of_mood(2);
            String ok_color = return_the_color_of_mood(3);
            String good_color = return_the_color_of_mood(4);
            String very_good_color = return_the_color_of_mood(5);
            int[] colors_for_bars = new int[12];
            for (int i = 0; i < 12; i++) {
                float average_value = Float.parseFloat(split_days_of_week[i]);
                if (average_value <= 1.5) {
                    colors_for_bars[i] = Color.parseColor(very_bad_color);
                } else if (average_value <= 2.5) {
                    colors_for_bars[i] = Color.parseColor(bad_color);
                } else if (average_value <= 3.5) {
                    colors_for_bars[i] = Color.parseColor(ok_color);
                } else if (average_value <= 4.5) {
                    colors_for_bars[i] = Color.parseColor(good_color);
                } else {
                    colors_for_bars[i] = Color.parseColor(very_good_color);
                }
            }
            BarDataSet set = new BarDataSet(entries, "BarDataSet");
            set.setColors(colors_for_bars);
            set.setValueTextColor(Color.WHITE);
            set.setValueTextSize(15);
            set.setValueTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
            BarData data = new BarData(set);
            data.setValueFormatter(valueFormatter);
            data.setBarWidth(0.9f);
            chart_in_mood_about_the_average_for_each_month.setData(data);
            chart_in_mood_about_the_average_for_each_month.invalidate();
            chart_in_mood_about_the_average_for_each_month.setScaleEnabled(false);
            chart_in_mood_about_the_average_for_each_month.getLegend().setEnabled(false);
            chart_in_mood_about_the_average_for_each_month.setExtraOffsets(0, 0, 0, 0);
            Description description = new Description();
            description.setText("");
            chart_in_mood_about_the_average_for_each_month.setDescription(description);
            XAxis xAxis = chart_in_mood_about_the_average_for_each_month.getXAxis();
            xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
            data.setHighlightEnabled(false);
            data.setBarWidth(0.7f);
            chart_in_mood_about_the_average_for_each_month.getAxisLeft().setAxisMinimum(0f);
            chart_in_mood_about_the_average_for_each_month.getAxisRight().setAxisMinimum(0f);
            chart_in_mood_about_the_average_for_each_month.getXAxis().setDrawGridLines(false);
            chart_in_mood_about_the_average_for_each_month.getAxisLeft().setDrawAxisLine(false);
            chart_in_mood_about_the_average_for_each_month.getAxisRight().setDrawAxisLine(false);
            chart_in_mood_about_the_average_for_each_month.getAxisLeft().setDrawLabels(false);
            chart_in_mood_about_the_average_for_each_month.getAxisRight().setDrawLabels(false);
            chart_in_mood_about_the_average_for_each_month.setDrawValueAboveBar(false);
            chart_in_mood_about_the_average_for_each_month.setVisibleXRangeMaximum(7);
            chart_in_mood_about_the_average_for_each_month.moveViewToX(entries.size());
        }
    }


    private void drawTheRightBarChartMood() {
        BarChart chart_in_mood_about_the_average_for_each_month = getView().findViewById(R.id.bar_chart);
        if (chart_in_mood_about_the_average_for_each_month.getVisibility() == View.VISIBLE) {
            drawBarForAverageMoodOverTheYear();
        } else {
            drawTheBarForAverageMood();
        }
    }
    private long returnStartDate() {
        if (getActivity() != null) {
            SharedPreferences sharedPreferences = getActivity().getSharedPreferences("first_date_of_mood", Context.MODE_PRIVATE);
            long first_time = sharedPreferences.getLong("first_date", -1);
            if (first_time == -1) {
                SharedPreferences.Editor myEdit = sharedPreferences.edit();
                myEdit.putLong("first_date", Simplify_the_time.return_time_in_midnight(System.currentTimeMillis()));
                myEdit.apply();
                return Simplify_the_time.return_time_in_midnight(System.currentTimeMillis());
            } else {
                return first_time;
            }
        } else {
            return Simplify_the_time.return_time_in_midnight(System.currentTimeMillis());
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 22) {
            int year_global = data.getIntExtra("year", 2001);
            int month_global = data.getIntExtra("month", 1);
            int day_global = data.getIntExtra("day", 20);
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.YEAR, year_global);
            calendar.set(Calendar.MONTH, month_global);
            calendar.set(Calendar.DAY_OF_MONTH, day_global);
            SharedPreferences sharedPreferences = getActivity().getSharedPreferences("first_date_of_mood", Context.MODE_PRIVATE);
            SharedPreferences.Editor myEdit = sharedPreferences.edit();
            myEdit.putLong("first_date", Simplify_the_time.return_time_in_midnight(calendar.getTimeInMillis()));
            myEdit.apply();
            call_me_at_start();
        }
    }
    private void fadeTheViews() {
        if (getView() != null && getContext() != null) {
            TextView title_of_the_card_saying_this_is_the_graph_card = getView().findViewById(R.id.tv_mood_chart);
            TextView text_view_saying_that_there_is_not_enough_data_to_draw_this_line_chart = getView().findViewById(R.id.tv_not_enough_data_line_chart);
            Button button_to_show_back_above_mood_tracker_graph = getView().findViewById(R.id.bt_line_chart_back);
            Button button_to_show_forward_above_mood_tracker_graph = getView().findViewById(R.id.bt_line_chart_forward);
            View view_to_show_back_above_mood_tracker_graph = getView().findViewById(R.id.view_line_chart_back);
            View view_button_over_for_good_habits = getView().findViewById(R.id.view_line_chart_forward);
            TextView text_saying_month_year_in_the_chart_mood_tracker = getView().findViewById(R.id.tv_month_year_chart);
            LineChart chart_showing_mood_in_mood_tracker = getView().findViewById(R.id.line_chart);

            TextView text_title_of_weekly_daily_habit_in_card = getView().findViewById(R.id.tv_average);
            BarChart chart_in_good_habits_about_how_many_times_for_each_days_of_week = getView().findViewById(R.id.bar_chart2);
            BarChart chart_in_mood_about_the_average_for_each_month = getView().findViewById(R.id.bar_chart);
            TextView text_view_saying_that_there_is_not_enough_data_to_draw_this_chart_for_daily_relapse = getView().findViewById(R.id.tv_not_enough_data);

            title_of_the_card_saying_this_is_the_graph_card.setAlpha(1f);
            text_view_saying_that_there_is_not_enough_data_to_draw_this_line_chart.setAlpha(1f);
            button_to_show_back_above_mood_tracker_graph.setAlpha(1f);
            button_to_show_forward_above_mood_tracker_graph.setAlpha(1f);
            view_to_show_back_above_mood_tracker_graph.setAlpha(1f);
            view_button_over_for_good_habits.setAlpha(1f);
            text_saying_month_year_in_the_chart_mood_tracker.setAlpha(1f);
            chart_showing_mood_in_mood_tracker.setAlpha(1f);

            text_title_of_weekly_daily_habit_in_card.setAlpha(1f);
            chart_in_good_habits_about_how_many_times_for_each_days_of_week.setAlpha(1f);
            chart_in_mood_about_the_average_for_each_month.setAlpha(1f);
            text_view_saying_that_there_is_not_enough_data_to_draw_this_chart_for_daily_relapse.setAlpha(1f);
        }
    }
}