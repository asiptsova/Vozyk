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
    private Button day1,day2,day3,day4,day5,day6,day7,day8,day9,day10,
            day11,day12,day13,day14,day15,day16,day17,day18,day19, day20,
            day21,day22,day23,day24,day25,day26,day27,day28,day29,day30,
            day31,day32, day33, day34, day35, day36,day37;
    TextView monthYear;
    private String colorToday;
    private String[] colors;
    private HashMap<Long, Integer> historyMood;
    private Long startDate;
    private int color;
    private final int[] modesDrawable = new int[37];
    private int monthForMoodChart = -1;
    private int yearForMoodChart = -1;
    private LineChart lineChartForStreak;


    public Mood() {}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_mood, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        callMeAtStart();
    }

    private void callMeAtStart() {
        addToList();
        getColor();
        colorStuff();
        StartDate();
        Buttons();
        setFirstDayOfTheWeek();
        setDaysText();
        backAndForwardButton();
        colorTodayValue();
        calenderButton();
        removeButtons();
        colorToday();
        calendarColor();
        yesAndNoButtons();
        divideItIntoWeeks();
        hideOrUnHideTheButton(0);
        colorButtonUnderTheCalendar();
        setUpButtonsOnce();
        buttonListenAtTheTop();
        makeEverythingAverageMood();
        lineChart();
        buttonTheGraphMood();
        setMood();
        averageMood();
        fadeTheViews();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden)
            callMeAtStart();
    }

    private void setFirstDayOfTheWeek() {
        if (getView() != null) {
            Calendar calender = Calendar.getInstance();
            TextView firstDayInTheWeek = getView().findViewById(R.id.tv_1);
            TextView secondDayInTheWeek = getView().findViewById(R.id.tv_2);
            TextView thirdDayInTheWeek = getView().findViewById(R.id.tv_3);
            TextView fourthDayInTheWeek = getView().findViewById(R.id.tv_4);
            TextView fifthDayInTheWeek = getView().findViewById(R.id.tv_5);
            TextView sixthDayInTheWeek = getView().findViewById(R.id.tv_6);
            TextView seventhDayInTheWeek = getView().findViewById(R.id.tv_7);
            String month = returnMonth(calender.get(Calendar.MONTH));
            monthYear = getView().findViewById(R.id.tv_month_year);
            monthYear.setText(month.concat(" ").concat(String.valueOf(calender.get(Calendar.YEAR))));
            if (calender.getFirstDayOfWeek() == Calendar.SUNDAY) {
                firstDayInTheWeek.setText(R.string.Sunday);
                secondDayInTheWeek.setText(R.string.Monday);
                thirdDayInTheWeek.setText(R.string.Tuesday);
                fourthDayInTheWeek.setText(R.string.Wednesday);
                fifthDayInTheWeek.setText(R.string.Thursday);
                sixthDayInTheWeek.setText(R.string.Friday);
                seventhDayInTheWeek.setText(R.string.Saturday);
            } else if (calender.getFirstDayOfWeek() == Calendar.MONDAY) {
                firstDayInTheWeek.setText(R.string.Monday);
                secondDayInTheWeek.setText(R.string.Tuesday);
                thirdDayInTheWeek.setText(R.string.Wednesday);
                fourthDayInTheWeek.setText(R.string.Thursday);
                fifthDayInTheWeek.setText(R.string.Friday);
                sixthDayInTheWeek.setText(R.string.Saturday);
                seventhDayInTheWeek.setText(R.string.Sunday);
            } else if (calender.getFirstDayOfWeek() == Calendar.TUESDAY) {
                firstDayInTheWeek.setText(R.string.Tuesday);
                secondDayInTheWeek.setText(R.string.Wednesday);
                thirdDayInTheWeek.setText(R.string.Thursday);
                fourthDayInTheWeek.setText(R.string.Friday);
                fifthDayInTheWeek.setText(R.string.Saturday);
                sixthDayInTheWeek.setText(R.string.Sunday);
                seventhDayInTheWeek.setText(R.string.Monday);
            } else if (calender.getFirstDayOfWeek() == Calendar.WEDNESDAY) {
                firstDayInTheWeek.setText(R.string.Wednesday);
                secondDayInTheWeek.setText(R.string.Thursday);
                thirdDayInTheWeek.setText(R.string.Friday);
                fourthDayInTheWeek.setText(R.string.Saturday);
                fifthDayInTheWeek.setText(R.string.Sunday);
                sixthDayInTheWeek.setText(R.string.Monday);
                seventhDayInTheWeek.setText(R.string.Tuesday);
            } else if (calender.getFirstDayOfWeek() == Calendar.THURSDAY) {
                firstDayInTheWeek.setText(R.string.Thursday);
                secondDayInTheWeek.setText(R.string.Friday);
                thirdDayInTheWeek.setText(R.string.Saturday);
                fourthDayInTheWeek.setText(R.string.Sunday);
                fifthDayInTheWeek.setText(R.string.Monday);
                sixthDayInTheWeek.setText(R.string.Tuesday);
                seventhDayInTheWeek.setText(R.string.Wednesday);
            } else if (calender.getFirstDayOfWeek() == Calendar.FRIDAY) {
                firstDayInTheWeek.setText(R.string.Friday);
                secondDayInTheWeek.setText(R.string.Saturday);
                thirdDayInTheWeek.setText(R.string.Sunday);
                fourthDayInTheWeek.setText(R.string.Monday);
                fifthDayInTheWeek.setText(R.string.Tuesday);
                sixthDayInTheWeek.setText(R.string.Wednesday);
                seventhDayInTheWeek.setText(R.string.Thursday);
            } else {
                firstDayInTheWeek.setText(R.string.Saturday);
                secondDayInTheWeek.setText(R.string.Sunday);
                thirdDayInTheWeek.setText(R.string.Monday);
                fourthDayInTheWeek.setText(R.string.Tuesday);
                fifthDayInTheWeek.setText(R.string.Wednesday);
                sixthDayInTheWeek.setText(R.string.Thursday);
                seventhDayInTheWeek.setText(R.string.Friday);
            }
        }
    }

    private String returnMonth(int month) {
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

    private int returnFirstDayOfMonth() {
        if (getView() != null) {
            monthYear = getView().findViewById(R.id.tv_month_year);
            String[] splitter = monthYear.getText().toString().split(" ");
            Calendar firstDayOfMonthOnly = Calendar.getInstance();
            firstDayOfMonthOnly.set(Calendar.MONTH, returnMonthStringToInt(splitter[0]));
            firstDayOfMonthOnly.set(Calendar.YEAR, Integer.parseInt(splitter[1]));
            firstDayOfMonthOnly.set(Calendar.DAY_OF_MONTH, firstDayOfMonthOnly.getActualMinimum(Calendar.DAY_OF_MONTH));
            return firstDayOfMonthOnly.get(Calendar.DAY_OF_WEEK);
        } else
            return 0;
    }

    private int returnLastDayOfMonth() {
        if (getView() != null) {
            monthYear = getView().findViewById(R.id.tv_month_year);
            Calendar lastDayOfMonthOnly = Calendar.getInstance();
            String[] splitter = monthYear.getText().toString().split(" ");
            lastDayOfMonthOnly.set(Calendar.MONTH, returnMonthStringToInt(splitter[0]));
            lastDayOfMonthOnly.set(Calendar.YEAR, Integer.parseInt(splitter[1]));
            lastDayOfMonthOnly.set(Calendar.DAY_OF_MONTH, 1);
            return lastDayOfMonthOnly.getActualMaximum(Calendar.DAY_OF_MONTH);
        } else
            return 1;
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
    private void setDaysText() {
        if (getView() != null) {
            monthYear = getView().findViewById(R.id.tv_month_year);
            String[] splitter = monthYear.getText().toString().split(" ");
            Calendar calenderForFirstDayOfMonthOnly = Calendar.getInstance();
            calenderForFirstDayOfMonthOnly.set(Calendar.MONTH, returnMonthStringToInt(splitter[0]));
            calenderForFirstDayOfMonthOnly.set(Calendar.YEAR, Integer.parseInt(splitter[1]));
            if (calenderForFirstDayOfMonthOnly.getFirstDayOfWeek() == Calendar.SUNDAY)
                dayIsSunday();
            else if (calenderForFirstDayOfMonthOnly.getFirstDayOfWeek() == Calendar.MONDAY)
                dayIsMonday();
            else if (calenderForFirstDayOfMonthOnly.getFirstDayOfWeek() == Calendar.TUESDAY)
                dayIsTuesday();
            else if (calenderForFirstDayOfMonthOnly.getFirstDayOfWeek() == Calendar.WEDNESDAY)
                dayIsWednesday();
            else if (calenderForFirstDayOfMonthOnly.getFirstDayOfWeek() == Calendar.THURSDAY)
                dayIsThursday();
            else if (calenderForFirstDayOfMonthOnly.getFirstDayOfWeek() == Calendar.FRIDAY)
                dayIsFriday();
            else
                dayIsSaturday();
        }
    }
    private void dayIsSunday() {
        int day = 1;
        if (returnFirstDayOfMonth() == Calendar.SUNDAY) {
            day1.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day2.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day3.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day4.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day5.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day6.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day7.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day8.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day9.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day10.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day11.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day12.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day13.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day14.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day15.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day16.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day17.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day18.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day19.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day20.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day21.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day22.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day23.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day24.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day25.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day26.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day27.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day28.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day29.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day30.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day31.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day32.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day33.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day34.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day35.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day36.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day37.setText(String.valueOf(day));
        } else if (returnFirstDayOfMonth() == Calendar.MONDAY) {
            day2.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day3.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day4.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day5.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day6.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day7.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day8.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day9.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day10.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day11.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day12.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day13.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day14.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day15.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day16.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day17.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day18.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day19.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day20.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day21.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day22.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day23.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day24.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day25.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day26.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day27.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day28.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day29.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day30.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day31.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day32.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day33.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day34.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day35.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day36.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day37.setText(String.valueOf(day));
        } else if (returnFirstDayOfMonth() == Calendar.TUESDAY) {
            day3.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day4.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day5.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day6.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day7.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day8.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day9.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day10.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day11.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day12.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day13.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day14.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day15.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day16.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day17.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day18.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day19.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day20.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day21.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day22.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day23.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day24.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day25.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day26.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day27.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day28.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day29.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day30.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day31.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day32.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day33.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day34.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day35.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day36.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day37.setText(String.valueOf(day));
        } else if (returnFirstDayOfMonth() == Calendar.WEDNESDAY) {
            day4.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day5.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day6.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day7.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day8.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day9.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day10.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day11.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day12.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day13.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day14.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day15.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day16.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day17.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day18.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day19.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day20.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day21.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day22.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day23.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day24.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day25.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day26.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day27.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day28.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day29.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day30.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day31.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day32.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day33.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day34.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day35.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day36.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day37.setText(String.valueOf(day));
        } else if (returnFirstDayOfMonth() == Calendar.THURSDAY) {
            day5.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day6.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day7.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day8.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day9.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day10.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day11.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day12.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day13.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day14.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day15.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day16.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day17.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day18.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day19.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day20.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day21.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day22.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day23.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day24.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day25.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day26.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day27.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day28.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day29.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day30.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day31.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day32.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day33.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day34.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day35.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day36.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day37.setText(String.valueOf(day));
        } else if (returnFirstDayOfMonth() == Calendar.FRIDAY) {
            day6.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day7.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day8.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day9.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day10.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day11.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day12.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day13.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day14.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day15.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day16.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day17.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day18.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day19.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day20.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day21.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day22.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day23.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day24.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day25.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day26.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day27.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day28.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day29.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day30.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day31.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day32.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day33.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day34.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day35.setText(String.valueOf(day));
            day = day + 1;
          if (day <= returnLastDayOfMonth())
                day36.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day37.setText(String.valueOf(day));
        } else {
            day7.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day8.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day9.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day10.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day11.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day12.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day13.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day14.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day15.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day16.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day17.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day18.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day19.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day20.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day21.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day22.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day23.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day24.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day25.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day26.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day27.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day28.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day29.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day30.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day31.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day32.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day33.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day34.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day35.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day36.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day37.setText(String.valueOf(day));
        }
    }

    private void dayIsMonday() {
        int day = 1;
        if (returnFirstDayOfMonth() == Calendar.MONDAY) {
            day1.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day2.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day3.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day4.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day5.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day6.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day7.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day8.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day9.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day10.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day11.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day12.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day13.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day14.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day15.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day16.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day17.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day18.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day19.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day20.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day21.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day22.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day23.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day24.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day25.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day26.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day27.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day28.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day29.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day30.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day31.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day32.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day33.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day34.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day35.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day36.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day37.setText(String.valueOf(day));
        } else if (returnFirstDayOfMonth() == Calendar.TUESDAY) {
            day2.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day3.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day4.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day5.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day6.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day7.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day8.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day9.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day10.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day11.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day12.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day13.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day14.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day15.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day16.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day17.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day18.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day19.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day20.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day21.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day22.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day23.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day24.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day25.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day26.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day27.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day28.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day29.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day30.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day31.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day32.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day33.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day34.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day35.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day36.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day37.setText(String.valueOf(day));
        } else if (returnFirstDayOfMonth() == Calendar.WEDNESDAY) {
            day3.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day4.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day5.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day6.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day7.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day8.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day9.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day10.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day11.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day12.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day13.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day14.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day15.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day16.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day17.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day18.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day19.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day20.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day21.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day22.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day23.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day24.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day25.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day26.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day27.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day28.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day29.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day30.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day31.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day32.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day33.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day34.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day35.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day36.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day37.setText(String.valueOf(day));
        } else if (returnFirstDayOfMonth() == Calendar.THURSDAY) {
            day4.setText(String.valueOf(day));
            day = day + 1;
          if (day <= returnLastDayOfMonth())
                day5.setText(String.valueOf(day));
          day = day + 1;
            if (day <= returnLastDayOfMonth())
                day6.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day7.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day8.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day9.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day10.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day11.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day12.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day13.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day14.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day15.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day16.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day17.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day18.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day19.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day20.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day21.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day22.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day23.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day24.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day25.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day26.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day27.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day28.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day29.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day30.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day31.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day32.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day33.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day34.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day35.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day36.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day37.setText(String.valueOf(day));
        } else if (returnFirstDayOfMonth() == Calendar.FRIDAY) {
            day5.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day6.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day7.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day8.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day9.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day10.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day11.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day12.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day13.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day14.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day15.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day16.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day17.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day18.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day19.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day20.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day21.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day22.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day23.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day24.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day25.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day26.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day27.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day28.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day29.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day30.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day31.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day32.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day33.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day34.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day35.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day36.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day37.setText(String.valueOf(day));
        } else if (returnFirstDayOfMonth() == Calendar.SATURDAY) {
            day6.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day7.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day8.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day9.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day10.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day11.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day12.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day13.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day14.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day15.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day16.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day17.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day18.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day19.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day20.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day21.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day22.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day23.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day24.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day25.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day26.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day27.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day28.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day29.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day30.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day31.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day32.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day33.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day34.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day35.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day36.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day37.setText(String.valueOf(day));
        } else {
            day7.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day8.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day9.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day10.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day11.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day12.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day13.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day14.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day15.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day16.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day17.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day18.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day19.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day20.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day21.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day22.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day23.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day24.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day25.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day26.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day27.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day28.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day29.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day30.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day31.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day32.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day33.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day34.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day35.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day36.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day37.setText(String.valueOf(day));
        }
    }

    private void dayIsTuesday() {
        int day = 1;
        if (returnFirstDayOfMonth() == Calendar.TUESDAY) {
            day1.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day2.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day3.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day4.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day5.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day6.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day7.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day8.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day9.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day10.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day11.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day12.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day13.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day14.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day15.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day16.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day17.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day18.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day19.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day20.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day21.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day22.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day23.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day24.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day25.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day26.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day27.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day28.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day29.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day30.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day31.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day32.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day33.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day34.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day35.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day36.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day37.setText(String.valueOf(day));
        } else if (returnFirstDayOfMonth() == Calendar.WEDNESDAY) {
            day2.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day3.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day4.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day5.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day6.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day7.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day8.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day9.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day10.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day11.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day12.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day13.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day14.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day15.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day16.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day17.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day18.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day19.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day20.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day21.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day22.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day23.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day24.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day25.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day26.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day27.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day28.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day29.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day30.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day31.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day32.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day33.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day34.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day35.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day36.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day37.setText(String.valueOf(day));
        } else if (returnFirstDayOfMonth() == Calendar.THURSDAY) {
            day3.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day4.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day5.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day6.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day7.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day8.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day9.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day10.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day11.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day12.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day13.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day14.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day15.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day16.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day17.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day18.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day19.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day20.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day21.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day22.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day23.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day24.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day25.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day26.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day27.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day28.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day29.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day30.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day31.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day32.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day33.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day34.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day35.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day36.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day37.setText(String.valueOf(day));
        } else if (returnFirstDayOfMonth() == Calendar.FRIDAY) {
            day4.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day5.setText(String.valueOf(day));
            day = day + 1;
          if (day <= returnLastDayOfMonth())
                day6.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day7.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day8.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day9.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day10.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day11.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day12.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day13.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day14.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day15.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day16.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day17.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day18.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day19.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day20.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day21.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day22.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day23.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day24.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day25.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day26.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day27.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day28.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day29.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day30.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day31.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day32.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day33.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day34.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day35.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day36.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day37.setText(String.valueOf(day));
        } else if (returnFirstDayOfMonth() == Calendar.SATURDAY) {
            day5.setText(String.valueOf(day));
            day = day + 1;
          if (day <= returnLastDayOfMonth())
                day6.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day7.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day8.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day9.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day10.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day11.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day12.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day13.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day14.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day15.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day16.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day17.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day18.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day19.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day20.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day21.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day22.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day23.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day24.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day25.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day26.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day27.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day28.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day29.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day30.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day31.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day32.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day33.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day34.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day35.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day36.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day37.setText(String.valueOf(day));
        } else if (returnFirstDayOfMonth() == Calendar.SUNDAY) {
            day6.setText(String.valueOf(day));
            day = day + 1;
          if (day <= returnLastDayOfMonth())
                day7.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day8.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day9.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day10.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day11.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day12.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day13.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day14.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day15.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day16.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day17.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day18.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day19.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day20.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day21.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day22.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day23.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day24.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day25.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day26.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day27.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day28.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day29.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day30.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day31.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day32.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day33.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day34.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day35.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day36.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day37.setText(String.valueOf(day));
        } else {
            day7.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day8.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day9.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day10.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day11.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day12.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day13.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day14.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day15.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day16.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day17.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day18.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day19.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day20.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day21.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day22.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day23.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day24.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day25.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day26.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day27.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day28.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day29.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day30.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day31.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day32.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day33.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day34.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day35.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day36.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day37.setText(String.valueOf(day));
        }
        }

    private void dayIsWednesday() {
        int day = 1;
        if (returnFirstDayOfMonth() == Calendar.WEDNESDAY) {
            day1.setText(String.valueOf(day));
            day = day + 1;
          if (day <= returnLastDayOfMonth())
                day2.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day3.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day4.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day5.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day6.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day7.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day8.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day9.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day10.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day11.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day12.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day13.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day14.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day15.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day16.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day17.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day18.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day19.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day20.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day21.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day22.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day23.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day24.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day25.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day26.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day27.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day28.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day29.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day30.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day31.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day32.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day33.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day34.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day35.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day36.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day37.setText(String.valueOf(day));
        } else if (returnFirstDayOfMonth() == Calendar.THURSDAY) {
            day2.setText(String.valueOf(day));
            day = day + 1;
          if (day <= returnLastDayOfMonth())
                day3.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day4.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day5.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day6.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day7.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day8.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day9.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day10.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day11.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day12.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day13.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day14.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day15.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day16.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day17.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day18.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day19.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day20.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day21.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day22.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day23.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day24.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day25.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day26.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day27.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day28.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day29.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day30.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day31.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day32.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day33.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day34.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day35.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day36.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day37.setText(String.valueOf(day));
        } else if (returnFirstDayOfMonth() == Calendar.FRIDAY) {
            day3.setText(String.valueOf(day));
            day = day + 1;
          if (day <= returnLastDayOfMonth())
                day4.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day5.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day6.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day7.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day8.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day9.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day10.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day11.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day12.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day13.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day14.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day15.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day16.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day17.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day18.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day19.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day20.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day21.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day22.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day23.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day24.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day25.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day26.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day27.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day28.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day29.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day30.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day31.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day32.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day33.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day34.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day35.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day36.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day37.setText(String.valueOf(day));
        } else if (returnFirstDayOfMonth() == Calendar.SATURDAY) {
            day4.setText(String.valueOf(day));
            day = day + 1;
          if (day <= returnLastDayOfMonth())
                day5.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day6.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day7.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day8.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day9.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day10.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day11.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day12.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day13.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day14.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day15.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day16.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day17.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day18.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day19.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day20.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day21.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day22.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day23.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day24.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day25.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day26.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day27.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day28.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day29.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day30.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day31.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day32.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day33.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day34.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day35.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day36.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day37.setText(String.valueOf(day));
        } else if (returnFirstDayOfMonth() == Calendar.SUNDAY) {
            day5.setText(String.valueOf(day));
            day = day + 1;
          if (day <= returnLastDayOfMonth())
                day6.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day7.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day8.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day9.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day10.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day11.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day12.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day13.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day14.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day15.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day16.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day17.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day18.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day19.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day20.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day21.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day22.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day23.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day24.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day25.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day26.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day27.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day28.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day29.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day30.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day31.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day32.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day33.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day34.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day35.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day36.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day37.setText(String.valueOf(day));
        } else if (returnFirstDayOfMonth() == Calendar.MONDAY) {
            day6.setText(String.valueOf(day));
            day = day + 1;
          if (day <= returnLastDayOfMonth())
                day7.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day8.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day9.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day10.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day11.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day12.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day13.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day14.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day15.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day16.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day17.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day18.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day19.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day20.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day21.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day22.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day23.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day24.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day25.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day26.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day27.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day28.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day29.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day30.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day31.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day32.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day33.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day34.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day35.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day36.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day37.setText(String.valueOf(day));
        } else {
            day7.setText(String.valueOf(day));
            day = day + 1;
          if (day <= returnLastDayOfMonth())
                day8.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day9.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day10.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day11.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day12.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day13.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day14.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day15.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day16.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day17.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day18.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day19.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day20.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day21.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day22.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day23.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day24.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day25.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day26.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day27.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day28.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day29.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day30.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day31.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day32.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day33.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day34.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day35.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day36.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day37.setText(String.valueOf(day));
        }
    }

    private void dayIsThursday() {
        int day = 1;
        if (returnFirstDayOfMonth() == Calendar.THURSDAY) {
            day1.setText(String.valueOf(day));
            day = day + 1;
          if (day <= returnLastDayOfMonth())
                day2.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day3.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day4.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day5.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day6.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day7.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day8.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day9.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day10.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day11.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day12.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day13.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day14.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day15.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day16.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day17.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day18.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day19.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day20.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day21.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day22.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day23.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day24.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day25.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day26.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day27.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day28.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day29.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day30.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day31.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day32.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day33.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day34.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day35.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day36.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day37.setText(String.valueOf(day));
        } else if (returnFirstDayOfMonth() == Calendar.FRIDAY) {
            day2.setText(String.valueOf(day));
            day = day + 1;
          if (day <= returnLastDayOfMonth())
                day3.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day4.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day5.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day6.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day7.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day8.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day9.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day10.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day11.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day12.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day13.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day14.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day15.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day16.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day17.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day18.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day19.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day20.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day21.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day22.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day23.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day24.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day25.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day26.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day27.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day28.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day29.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day30.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day31.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day32.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day33.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day34.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day35.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day36.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day37.setText(String.valueOf(day));
        } else if (returnFirstDayOfMonth() == Calendar.SATURDAY) {
            day3.setText(String.valueOf(day));
            day = day + 1;
          if (day <= returnLastDayOfMonth())
                day4.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day5.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day6.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day7.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day8.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day9.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day10.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day11.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day12.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day13.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day14.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day15.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day16.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day17.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day18.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day19.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day20.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day21.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day22.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day23.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day24.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day25.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day26.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day27.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day28.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day29.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day30.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day31.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day32.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day33.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day34.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day35.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day36.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day37.setText(String.valueOf(day));
        } else if (returnFirstDayOfMonth() == Calendar.SUNDAY) {
            day4.setText(String.valueOf(day));
            day = day + 1;
          if (day <= returnLastDayOfMonth())
                day5.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day6.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day7.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day8.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day9.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day10.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day11.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day12.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day13.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day14.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day15.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day16.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day17.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day18.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day19.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day20.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day21.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day22.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day23.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day24.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day25.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day26.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day27.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day28.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day29.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day30.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day31.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day32.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day33.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day34.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day35.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day36.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day37.setText(String.valueOf(day));
        } else if (returnFirstDayOfMonth() == Calendar.MONDAY) {
            day5.setText(String.valueOf(day));
            day = day + 1;
          if (day <= returnLastDayOfMonth())
                day6.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day7.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day8.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day9.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day10.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day11.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day12.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day13.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day14.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day15.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day16.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day17.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day18.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day19.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day20.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day21.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day22.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day23.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day24.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day25.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day26.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day27.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day28.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day29.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day30.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day31.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day32.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day33.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day34.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day35.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day36.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day37.setText(String.valueOf(day));
        } else if (returnFirstDayOfMonth() == Calendar.TUESDAY) {
            day6.setText(String.valueOf(day));
            day = day + 1;
          if (day <= returnLastDayOfMonth())
                day7.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day8.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day9.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day10.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day11.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day12.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day13.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day14.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day15.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day16.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day17.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day18.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day19.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day20.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day21.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day22.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day23.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day24.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day25.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day26.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day27.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day28.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day29.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day30.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day31.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day32.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day33.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day34.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day35.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day36.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day37.setText(String.valueOf(day));
        } else {
            day7.setText(String.valueOf(day));
            day = day + 1;
          if (day <= returnLastDayOfMonth())
                day8.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day9.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day10.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day11.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day12.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day13.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day14.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day15.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day16.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day17.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day18.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day19.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day20.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day21.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day22.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day23.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day24.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day25.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day26.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day27.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day28.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day29.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day30.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day31.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day32.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day33.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day34.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day35.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day36.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day37.setText(String.valueOf(day));
        }
    }

    private void dayIsFriday() {
        int day = 1;
        if (returnFirstDayOfMonth() == Calendar.FRIDAY) {
            day1.setText(String.valueOf(day));
            day = day + 1;
          if (day <= returnLastDayOfMonth())
                day2.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day3.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day4.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day5.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day6.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day7.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day8.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day9.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day10.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day11.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day12.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day13.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day14.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day15.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day16.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day17.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day18.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day19.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day20.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day21.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day22.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day23.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day24.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day25.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day26.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day27.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day28.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day29.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day30.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day31.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day32.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day33.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day34.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day35.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day36.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day37.setText(String.valueOf(day));
        } else if (returnFirstDayOfMonth() == Calendar.SATURDAY) {
            day2.setText(String.valueOf(day));
            day = day + 1;
          if (day <= returnLastDayOfMonth())
                day3.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day4.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day5.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day6.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day7.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day8.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day9.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day10.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day11.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day12.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day13.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day14.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day15.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day16.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day17.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day18.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day19.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day20.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day21.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day22.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day23.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day24.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day25.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day26.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day27.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day28.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day29.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day30.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day31.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day32.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day33.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day34.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day35.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day36.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day37.setText(String.valueOf(day));
        } else if (returnFirstDayOfMonth() == Calendar.SUNDAY) {
            day3.setText(String.valueOf(day));
            day = day + 1;
          if (day <= returnLastDayOfMonth())
                day4.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day5.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day6.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day7.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day8.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day9.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day10.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day11.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day12.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day13.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day14.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day15.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day16.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day17.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day18.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day19.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day20.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day21.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day22.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day23.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day24.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day25.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day26.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day27.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day28.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day29.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day30.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day31.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day32.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day33.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day34.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day35.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day36.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day37.setText(String.valueOf(day));
        } else if (returnFirstDayOfMonth() == Calendar.MONDAY) {
            day4.setText(String.valueOf(day));
            day = day + 1;
          if (day <= returnLastDayOfMonth())
                day5.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day6.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day7.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day8.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day9.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day10.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day11.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day12.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day13.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day14.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day15.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day16.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day17.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day18.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day19.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day20.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day21.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day22.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day23.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day24.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day25.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day26.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day27.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day28.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day29.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day30.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day31.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day32.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day33.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day34.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day35.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day36.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day37.setText(String.valueOf(day));
        } else if (returnFirstDayOfMonth() == Calendar.TUESDAY) {
            day5.setText(String.valueOf(day));
            day = day + 1;
          if (day <= returnLastDayOfMonth())
                day6.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day7.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day8.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day9.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day10.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day11.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day12.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day13.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day14.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day15.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day16.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day17.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day18.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day19.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day20.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day21.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day22.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day23.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day24.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day25.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day26.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day27.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day28.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day29.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day30.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day31.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day32.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day33.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day34.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day35.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day36.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day37.setText(String.valueOf(day));
        } else if (returnFirstDayOfMonth() == Calendar.WEDNESDAY) {
            day6.setText(String.valueOf(day));
            day = day + 1;
          if (day <= returnLastDayOfMonth())
                day7.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day8.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day9.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day10.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day11.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day12.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day13.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day14.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day15.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day16.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day17.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day18.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day19.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day20.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day21.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day22.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day23.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day24.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day25.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day26.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day27.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day28.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day29.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day30.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day31.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day32.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day33.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day34.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day35.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day36.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day37.setText(String.valueOf(day));
        } else {
            day7.setText(String.valueOf(day));
            day = day + 1;
          if (day <= returnLastDayOfMonth())
                day8.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day9.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day10.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day11.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day12.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day13.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day14.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day15.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day16.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day17.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day18.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day19.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day20.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day21.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day22.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day23.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day24.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day25.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day26.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day27.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day28.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day29.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day30.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day31.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day32.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day33.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day34.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day35.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day36.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day37.setText(String.valueOf(day));
        }
    }

    private void dayIsSaturday() {
        int day = 1;
        if (returnFirstDayOfMonth() == Calendar.SATURDAY) {
            day1.setText(String.valueOf(day));
            day = day + 1;
          if (day <= returnLastDayOfMonth())
                day2.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day3.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day4.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day5.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day6.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day7.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day8.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day9.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day10.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day11.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day12.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day13.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day14.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day15.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day16.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day17.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day18.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day19.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day20.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day21.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day22.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day23.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day24.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day25.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day26.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day27.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day28.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day29.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day30.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day31.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day32.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day33.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day34.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day35.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day36.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day37.setText(String.valueOf(day));
        } else if (returnFirstDayOfMonth() == Calendar.SUNDAY) {
            day2.setText(String.valueOf(day));
            day = day + 1;
          if (day <= returnLastDayOfMonth())
                day3.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day4.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day5.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day6.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day7.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day8.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day9.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day10.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day11.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day12.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day13.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day14.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day15.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day16.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day17.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day18.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day19.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day20.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day21.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day22.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day23.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day24.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day25.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day26.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day27.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day28.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day29.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day30.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day31.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day32.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day33.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day34.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day35.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day36.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day37.setText(String.valueOf(day));
        } else if (returnFirstDayOfMonth() == Calendar.MONDAY) {
            day3.setText(String.valueOf(day));
            day = day + 1;
          if (day <= returnLastDayOfMonth())
                day4.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day5.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day6.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day7.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day8.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day9.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day10.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day11.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day12.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day13.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day14.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day15.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day16.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day17.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day18.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day19.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day20.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day21.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day22.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day23.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day24.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day25.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day26.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day27.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day28.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day29.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day30.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day31.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day32.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day33.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day34.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day35.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day36.setText(String.valueOf(day));
          day = day + 1;
          if (day <= returnLastDayOfMonth())
                day37.setText(String.valueOf(day));
        } else if (returnFirstDayOfMonth() == Calendar.TUESDAY) {
            day4.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day5.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day6.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day7.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day8.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day9.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day10.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day11.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day12.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day13.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day14.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day15.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day16.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day17.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day18.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day19.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day20.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day21.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day22.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day23.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day24.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day25.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day26.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day27.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day28.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day29.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day30.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day31.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day32.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day33.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day34.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day35.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day36.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day37.setText(String.valueOf(day));
        } else if (returnFirstDayOfMonth() == Calendar.WEDNESDAY) {
            day5.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day6.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day7.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day8.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day9.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day10.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day11.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day12.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day13.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day14.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day15.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day16.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day17.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day18.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day19.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day20.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day21.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day22.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day23.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day24.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day25.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day26.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day27.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day28.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day29.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day30.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day31.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day32.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day33.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day34.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day35.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day36.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day37.setText(String.valueOf(day));
        } else if (returnFirstDayOfMonth() == Calendar.THURSDAY) {
            day6.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day7.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day8.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day9.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day10.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day11.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day12.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day13.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day14.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day15.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day16.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day17.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day18.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day19.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day20.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day21.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day22.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day23.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day24.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day25.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day26.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day27.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day28.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day29.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day30.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day31.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day32.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day33.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day34.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day35.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day36.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day37.setText(String.valueOf(day));
        } else {
            day7.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day8.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day9.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day10.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day11.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day12.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day13.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day14.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day15.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day16.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day17.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day18.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day19.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day20.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day21.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day22.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day23.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day24.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day25.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day26.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day27.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day28.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day29.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day30.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day31.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day32.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day33.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day34.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day35.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day36.setText(String.valueOf(day));
            day = day + 1;
            if (day <= returnLastDayOfMonth())
                day37.setText(String.valueOf(day));
        }
    }

    private void Buttons() {
        if (getView() != null) {
            day1 = getView().findViewById(R.id.bt_1);
            day2 = getView().findViewById(R.id.bt_2);
            day3 = getView().findViewById(R.id.bt_3);
            day4 = getView().findViewById(R.id.bt_4);
            day5 = getView().findViewById(R.id.bt_5);
            day6 = getView().findViewById(R.id.bt_6);
            day7 = getView().findViewById(R.id.bt_7);
            day8 = getView().findViewById(R.id.bt_8);
            day9 = getView().findViewById(R.id.bt_9);
            day10 = getView().findViewById(R.id.bt_10);
            day11 = getView().findViewById(R.id.bt_11);
            day12 = getView().findViewById(R.id.bt_12);
            day13 = getView().findViewById(R.id.bt_13);
            day14 = getView().findViewById(R.id.bt_14);
            day15 = getView().findViewById(R.id.bt_15);
            day16 = getView().findViewById(R.id.bt_16);
            day17 = getView().findViewById(R.id.bt_17);
            day18 = getView().findViewById(R.id.bt_18);
            day19 = getView().findViewById(R.id.bt_19);
            day20 = getView().findViewById(R.id.bt_20);
            day21 = getView().findViewById(R.id.bt_21);
            day22 = getView().findViewById(R.id.bt_22);
            day23 = getView().findViewById(R.id.bt_23);
            day24 = getView().findViewById(R.id.bt_24);
            day25 = getView().findViewById(R.id.bt_25);
            day26 = getView().findViewById(R.id.bt_26);
            day27 = getView().findViewById(R.id.bt_27);
            day28 = getView().findViewById(R.id.bt_28);
            day29 = getView().findViewById(R.id.bt_29);
            day30 = getView().findViewById(R.id.bt_30);
            day31 = getView().findViewById(R.id.bt_31);
            day32 = getView().findViewById(R.id.bt_32);
            day33 = getView().findViewById(R.id.bt_33);
            day34 = getView().findViewById(R.id.bt_34);
            day35 = getView().findViewById(R.id.bt_35);
            day36 = getView().findViewById(R.id.bt_36);
            day37 = getView().findViewById(R.id.bt_37);
        }
    }

    private void backAndForwardButton() {
        if (getView() != null) {
            Button buttonShadowForTheBack = getView().findViewById(R.id.bt_calendar_back);
            Button buttonShadowForTheFront = getView().findViewById(R.id.bt_calendar_next);
            monthYear = getView().findViewById(R.id.tv_month_year);
            buttonShadowForTheBack.setOnClickListener(v -> {
                String[] splitter = monthYear.getText().toString().split(" ");
                String month_name;
                if (returnMonthStringToInt(splitter[0]) == 0) {
                    month_name = returnMonth(11);
                    String year = String.valueOf(Integer.parseInt(splitter[1]) - 1);
                    monthYear.setText(month_name.concat(" ").concat(year));
                } else {
                    month_name = returnMonth(returnMonthStringToInt(splitter[0]) - 1);
                    monthYear.setText(month_name.concat(" ").concat(splitter[1]));
                }
                clearTheCalender();
                setDaysText();
                clearTheColorFromTheKeyboard();
                setAllButtonsToVisible();
                removeButtons();
                colorToday();
                dateFutureCheck();
                hideOrUnHideTheButton(0);
                calendarColor();
                dateFutureCheck();
                colorButtonUnderTheCalendar();
                divideItIntoWeeks();
            });
            buttonShadowForTheFront.setOnClickListener(v -> {
                String[] splitter = monthYear.getText().toString().split(" ");
                String monthName;
                if (returnMonthStringToInt((splitter[0])) == 11) {
                    monthName = returnMonth(0);
                    monthYear.setText(monthName.concat(" ").concat(String.valueOf(Integer.parseInt(splitter[1]) + 1)));
                } else {
                    monthName = returnMonth(returnMonthStringToInt(splitter[0]) + 1);
                    monthYear.setText(monthName.concat(" ").concat(splitter[1]));
                }
                clearTheCalender();
                setDaysText();
                clearTheColorFromTheKeyboard();
                setAllButtonsToVisible();
                removeButtons();
                colorToday();
                dateFutureCheck();
                hideOrUnHideTheButton(0);
                calendarColor();
                dateFutureCheck();
                colorButtonUnderTheCalendar();
                divideItIntoWeeks();
            });
        }
    }

    private void clearTheCalender() {
        day1.setText("");
        day2.setText("");
        day3.setText("");
        day4.setText("");
        day5.setText("");
        day6.setText("");
        day7.setText("");
        day8.setText("");
        day9.setText("");
        day10.setText("");
        day11.setText("");
        day12.setText("");
        day13.setText("");
        day14.setText("");
        day15.setText("");
        day16.setText("");
        day17.setText("");
        day18.setText("");
        day19.setText("");
        day20.setText("");
        day21.setText("");
        day22.setText("");
        day23.setText("");
        day24.setText("");
        day25.setText("");
        day26.setText("");
        day27.setText("");
        day28.setText("");
        day29.setText("");
        day30.setText("");
        day31.setText("");
        day32.setText("");
        day33.setText("");
        day34.setText("");
        day35.setText("");
        day36.setText("");
        day37.setText("");
    }

    private void selectedColor(int which) {
        if (which == 1) {
            day1.setTextColor(Color.WHITE);
            day1.setBackgroundResource(R.drawable.round_button_colored_fav);
        } else if (which == 2) {
            day2.setTextColor(Color.WHITE);
            day2.setBackgroundResource(R.drawable.round_button_colored_fav);
        } else if (which == 3) {
            day3.setTextColor(Color.WHITE);
            day3.setBackgroundResource(R.drawable.round_button_colored_fav);
        } else if (which == 4) {
            day4.setTextColor(Color.WHITE);
            day4.setBackgroundResource(R.drawable.round_button_colored_fav);
        } else if (which == 5) {
            day5.setTextColor(Color.WHITE);
            day5.setBackgroundResource(R.drawable.round_button_colored_fav);
        } else if (which == 6) {
            day6.setTextColor(Color.WHITE);
            day6.setBackgroundResource(R.drawable.round_button_colored_fav);
        } else if (which == 7) {
            day7.setTextColor(Color.WHITE);
            day7.setBackgroundResource(R.drawable.round_button_colored_fav);
        } else if (which == 8) {
            day8.setTextColor(Color.WHITE);
            day8.setBackgroundResource(R.drawable.round_button_colored_fav);
        } else if (which == 9) {
            day9.setTextColor(Color.WHITE);
            day9.setBackgroundResource(R.drawable.round_button_colored_fav);
        } else if (which == 10) {
            day10.setTextColor(Color.WHITE);
            day10.setBackgroundResource(R.drawable.round_button_colored_fav);
        } else if (which == 11) {
            day11.setTextColor(Color.WHITE);
            day11.setBackgroundResource(R.drawable.round_button_colored_fav);
        } else if (which == 12) {
            day12.setTextColor(Color.WHITE);
            day12.setBackgroundResource(R.drawable.round_button_colored_fav);
        } else if (which == 13) {
            day13.setTextColor(Color.WHITE);
            day13.setBackgroundResource(R.drawable.round_button_colored_fav);
        } else if (which == 14) {
            day14.setTextColor(Color.WHITE);
            day14.setBackgroundResource(R.drawable.round_button_colored_fav);
        } else if (which == 15) {
            day15.setTextColor(Color.WHITE);
            day15.setBackgroundResource(R.drawable.round_button_colored_fav);
        } else if (which == 16) {
            day16.setTextColor(Color.WHITE);
            day16.setBackgroundResource(R.drawable.round_button_colored_fav);
        } else if (which == 17) {
            day17.setTextColor(Color.WHITE);
            day17.setBackgroundResource(R.drawable.round_button_colored_fav);
        } else if (which == 18) {
            day18.setTextColor(Color.WHITE);
            day18.setBackgroundResource(R.drawable.round_button_colored_fav);
        } else if (which == 19) {
            day19.setTextColor(Color.WHITE);
            day19.setBackgroundResource(R.drawable.round_button_colored_fav);
        } else if (which == 20) {
            day20.setTextColor(Color.WHITE);
            day20.setBackgroundResource(R.drawable.round_button_colored_fav);
        } else if (which == 21) {
            day21.setTextColor(Color.WHITE);
            day21.setBackgroundResource(R.drawable.round_button_colored_fav);
        } else if (which == 22) {
            day22.setTextColor(Color.WHITE);
            day22.setBackgroundResource(R.drawable.round_button_colored_fav);
        } else if (which == 23) {
            day23.setTextColor(Color.WHITE);
            day23.setBackgroundResource(R.drawable.round_button_colored_fav);
        } else if (which == 24) {
            day24.setTextColor(Color.WHITE);
            day24.setBackgroundResource(R.drawable.round_button_colored_fav);
        } else if (which == 25) {
            day25.setTextColor(Color.WHITE);
            day25.setBackgroundResource(R.drawable.round_button_colored_fav);
        } else if (which == 26) {
            day26.setTextColor(Color.WHITE);
            day26.setBackgroundResource(R.drawable.round_button_colored_fav);
        } else if (which == 27) {
            day27.setTextColor(Color.WHITE);
            day27.setBackgroundResource(R.drawable.round_button_colored_fav);
        } else if (which == 28) {
            day28.setTextColor(Color.WHITE);
            day28.setBackgroundResource(R.drawable.round_button_colored_fav);
        } else if (which == 29) {
            day29.setTextColor(Color.WHITE);
            day29.setBackgroundResource(R.drawable.round_button_colored_fav);
        } else if (which == 30) {
            day30.setTextColor(Color.WHITE);
            day30.setBackgroundResource(R.drawable.round_button_colored_fav);
        } else if (which == 31) {
            day31.setTextColor(Color.WHITE);
            day31.setBackgroundResource(R.drawable.round_button_colored_fav);
        } else if (which == 32) {
            day32.setTextColor(Color.WHITE);
            day32.setBackgroundResource(R.drawable.round_button_colored_fav);
        } else if (which == 33) {
            day33.setTextColor(Color.WHITE);
            day33.setBackgroundResource(R.drawable.round_button_colored_fav);
        } else if (which == 34) {
            day34.setTextColor(Color.WHITE);
            day34.setBackgroundResource(R.drawable.round_button_colored_fav);
        } else if (which == 35) {
            day35.setTextColor(Color.WHITE);
            day35.setBackgroundResource(R.drawable.round_button_colored_fav);
        } else if (which == 36) {
            day36.setTextColor(Color.WHITE);
            day36.setBackgroundResource(R.drawable.round_button_colored_fav);
        } else {
            day37.setTextColor(Color.WHITE);
            day37.setBackgroundResource(R.drawable.round_button_colored_fav);
        }
    }

    private void clearTheColorFromTheKeyboard() {
        day1.setTextColor(Color.BLACK);
        day1.setBackgroundResource(R.drawable.round_button);
        day2.setTextColor(Color.BLACK);
        day2.setBackgroundResource(R.drawable.round_button);
        day3.setTextColor(Color.BLACK);
        day3.setBackgroundResource(R.drawable.round_button);
        day4.setTextColor(Color.BLACK);
        day4.setBackgroundResource(R.drawable.round_button);
        day5.setTextColor(Color.BLACK);
        day5.setBackgroundResource(R.drawable.round_button);
        day6.setTextColor(Color.BLACK);
        day6.setBackgroundResource(R.drawable.round_button);
        day7.setTextColor(Color.BLACK);
        day7.setBackgroundResource(R.drawable.round_button);
        day8.setTextColor(Color.BLACK);
        day8.setBackgroundResource(R.drawable.round_button);
        day9.setTextColor(Color.BLACK);
        day9.setBackgroundResource(R.drawable.round_button);
        day10.setTextColor(Color.BLACK);
        day10.setBackgroundResource(R.drawable.round_button);
        day11.setTextColor(Color.BLACK);
        day11.setBackgroundResource(R.drawable.round_button);
        day12.setTextColor(Color.BLACK);
        day12.setBackgroundResource(R.drawable.round_button);
        day13.setTextColor(Color.BLACK);
        day13.setBackgroundResource(R.drawable.round_button);
        day14.setTextColor(Color.BLACK);
        day14.setBackgroundResource(R.drawable.round_button);
        day15.setTextColor(Color.BLACK);
        day15.setBackgroundResource(R.drawable.round_button);
        day16.setTextColor(Color.BLACK);
        day16.setBackgroundResource(R.drawable.round_button);
        day17.setTextColor(Color.BLACK);
        day17.setBackgroundResource(R.drawable.round_button);
        day18.setTextColor(Color.BLACK);
        day18.setBackgroundResource(R.drawable.round_button);
        day19.setTextColor(Color.BLACK);
        day19.setBackgroundResource(R.drawable.round_button);
        day20.setTextColor(Color.BLACK);
        day20.setBackgroundResource(R.drawable.round_button);
        day21.setTextColor(Color.BLACK);
        day21.setBackgroundResource(R.drawable.round_button);
        day22.setTextColor(Color.BLACK);
        day22.setBackgroundResource(R.drawable.round_button);
        day23.setTextColor(Color.BLACK);
        day23.setBackgroundResource(R.drawable.round_button);
        day24.setTextColor(Color.BLACK);
        day24.setBackgroundResource(R.drawable.round_button);
        day25.setTextColor(Color.BLACK);
        day25.setBackgroundResource(R.drawable.round_button);
        day26.setTextColor(Color.BLACK);
        day26.setBackgroundResource(R.drawable.round_button);
        day27.setTextColor(Color.BLACK);
        day27.setBackgroundResource(R.drawable.round_button);
        day28.setTextColor(Color.BLACK);
        day28.setBackgroundResource(R.drawable.round_button);
        day29.setTextColor(Color.BLACK);
        day29.setBackgroundResource(R.drawable.round_button);
        day30.setTextColor(Color.BLACK);
        day30.setBackgroundResource(R.drawable.round_button);
        day31.setTextColor(Color.BLACK);
        day31.setBackgroundResource(R.drawable.round_button);
        day32.setTextColor(Color.BLACK);
        day32.setBackgroundResource(R.drawable.round_button);
        day33.setTextColor(Color.BLACK);
        day33.setBackgroundResource(R.drawable.round_button);
        day34.setTextColor(Color.BLACK);
        day34.setBackgroundResource(R.drawable.round_button);
        day35.setTextColor(Color.BLACK);
        day35.setBackgroundResource(R.drawable.round_button);
        day36.setTextColor(Color.BLACK);
        day36.setBackgroundResource(R.drawable.round_button);
        day37.setTextColor(Color.BLACK);
        day37.setBackgroundResource(R.drawable.round_button);
    }

    private void colorTodayValue() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        colorToday = String.valueOf(day).concat("_").concat(String.valueOf(month)).concat("_").concat(String.valueOf(year));
    }

    private void calenderButton() {
        monthYear = getView().findViewById(R.id.tv_month_year);
        day1.setOnClickListener(v -> {
            clearBackground();
            colorOnlyToday();
            selectedColor(1);
            if (getView() != null) {
                String[] splitter = monthYear.getText().toString().split(" ");
                colorToday = day1.getText().toString().concat("_").concat(String.valueOf(returnMonthStringToInt(splitter[0]))).concat("_").concat(splitter[1]);
            }
            dateFutureCheck();
            colorButtonUnderTheCalendar();
        });
        day2.setOnClickListener(v -> {
            clearBackground();
            colorOnlyToday();
            selectedColor(2);
            if (getView() != null) {
                String[] splitter = monthYear.getText().toString().split(" ");
                colorToday = day2.getText().toString().concat("_").concat(String.valueOf(returnMonthStringToInt(splitter[0]))).concat("_").concat(splitter[1]);
            }
            dateFutureCheck();
            colorButtonUnderTheCalendar();
        });
        day3.setOnClickListener(v -> {
            clearBackground();
            colorOnlyToday();
            selectedColor(3);
            if (getView() != null) {
                String[] splitter = monthYear.getText().toString().split(" ");
                int month = returnMonthStringToInt(splitter[0]);
                colorToday = day3.getText().toString().concat("_").concat(String.valueOf(month)).concat("_").concat(splitter[1]);
            }
            dateFutureCheck();
            colorButtonUnderTheCalendar();
        });
        day4.setOnClickListener(v -> {
            clearBackground();
            colorOnlyToday();
            selectedColor(4);
            if (getView() != null) {
                String[] splitter = monthYear.getText().toString().split(" ");
                colorToday = day4.getText().toString().concat("_").concat(String.valueOf(returnMonthStringToInt(splitter[0]))).concat("_").concat(splitter[1]);
            }
            dateFutureCheck();
            colorButtonUnderTheCalendar();
        });
        day5.setOnClickListener(v -> {
            clearBackground();
            colorOnlyToday();
            selectedColor(5);
            if (getView() != null) {
                String[] splitter = monthYear.getText().toString().split(" ");
                int month = returnMonthStringToInt(splitter[0]);
                colorToday = day5.getText().toString().concat("_").concat(String.valueOf(month)).concat("_").concat(splitter[1]);
            }
            dateFutureCheck();
            colorButtonUnderTheCalendar();
        });
        day6.setOnClickListener(v -> {
            clearBackground();
            colorOnlyToday();
            selectedColor(6);
            if (getView() != null) {
                String[] splitter = monthYear.getText().toString().split(" ");
                int month = returnMonthStringToInt(splitter[0]);
                colorToday = day6.getText().toString().concat("_").concat(String.valueOf(month)).concat("_").concat(splitter[1]);
            }
            dateFutureCheck();
            colorButtonUnderTheCalendar();
        });
        day7.setOnClickListener(v -> {
            clearBackground();
            colorOnlyToday();
            selectedColor(7);
            if (getView() != null) {
                String[] splitter = monthYear.getText().toString().split(" ");
                colorToday = day7.getText().toString().concat("_").concat(String.valueOf(returnMonthStringToInt(splitter[0]))).concat("_").concat(splitter[1]);
            }
            dateFutureCheck();
            colorButtonUnderTheCalendar();
        });
        day8.setOnClickListener(v -> {
            clearBackground();
            colorOnlyToday();
            selectedColor(8);
            if (getView() != null) {
                String[] splitter = monthYear.getText().toString().split(" ");
                colorToday = day8.getText().toString().concat("_").concat(String.valueOf(returnMonthStringToInt(splitter[0]))).concat("_").concat(splitter[1]);
            }
            dateFutureCheck();
            colorButtonUnderTheCalendar();
        });
        day9.setOnClickListener(v -> {
            clearBackground();
            colorOnlyToday();
            selectedColor(9);
            if (getView() != null) {
                String[] splitter = monthYear.getText().toString().split(" ");
                colorToday = day9.getText().toString().concat("_").concat(String.valueOf(returnMonthStringToInt(splitter[0]))).concat("_").concat(splitter[1]);
            }
            dateFutureCheck();
            colorButtonUnderTheCalendar();
        });
        day10.setOnClickListener(v -> {
            clearBackground();
            colorOnlyToday();
            selectedColor(10);
            if (getView() != null) {
                String[] splitter = monthYear.getText().toString().split(" ");
                colorToday = day10.getText().toString().concat("_").concat(String.valueOf(returnMonthStringToInt(splitter[0]))).concat("_").concat(splitter[1]);
            }
            dateFutureCheck();
            colorButtonUnderTheCalendar();
        });
        day11.setOnClickListener(v -> {
            clearBackground();
            colorOnlyToday();
            selectedColor(11);
            if (getView() != null) {
                String[] splitter = monthYear.getText().toString().split(" ");
                colorToday = day11.getText().toString().concat("_").concat(String.valueOf(returnMonthStringToInt(splitter[0])).concat("_").concat(splitter[1]));
            }
            dateFutureCheck();
            colorButtonUnderTheCalendar();
        });
        day12.setOnClickListener(v -> {
            clearBackground();
            colorOnlyToday();
            selectedColor(12);
            if (getView() != null) {
                String[] splitter = monthYear.getText().toString().split(" ");
                colorToday = day12.getText().toString().concat("_").concat(String.valueOf(returnMonthStringToInt(splitter[0]))).concat("_").concat(splitter[1]);
            }
            dateFutureCheck();
            colorButtonUnderTheCalendar();
        });
        day13.setOnClickListener(v -> {
            clearBackground();
            colorOnlyToday();
            selectedColor(13);
            if (getView() != null) {
                String[] splitter = monthYear.getText().toString().split(" ");
                colorToday = day13.getText().toString().concat("_").concat(String.valueOf(returnMonthStringToInt(splitter[0]))).concat("_").concat(splitter[1]);
            }
            dateFutureCheck();
            colorButtonUnderTheCalendar();
        });
        day14.setOnClickListener(v -> {
            clearBackground();
            colorOnlyToday();
            selectedColor(14);
            if (getView() != null) {
                String[] splitter = monthYear.getText().toString().split(" ");
                colorToday = day14.getText().toString().concat("_").concat(String.valueOf(returnMonthStringToInt(splitter[0]))).concat("_").concat(splitter[1]);
            }
            dateFutureCheck();
            colorButtonUnderTheCalendar();
        });
        day15.setOnClickListener(v -> {
            clearBackground();
            colorOnlyToday();
            selectedColor(15);
            if (getView() != null) {
                String[] splitter = monthYear.getText().toString().split(" ");
                colorToday = day15.getText().toString().concat("_").concat(String.valueOf(returnMonthStringToInt(splitter[0]))).concat("_").concat(splitter[1]);
            }
            dateFutureCheck();
            colorButtonUnderTheCalendar();
        });
        day16.setOnClickListener(v -> {
            clearBackground();
            colorOnlyToday();
            selectedColor(16);
            if (getView() != null) {
                String[] splitter = monthYear.getText().toString().split(" ");
                colorToday = day16.getText().toString().concat("_").concat(String.valueOf(returnMonthStringToInt(splitter[0]))).concat("_").concat(splitter[1]);
            }
            dateFutureCheck();
            colorButtonUnderTheCalendar();
        });
        day17.setOnClickListener(v -> {
            clearBackground();
            colorOnlyToday();
            selectedColor(17);
            if (getView() != null) {
                String[] splitter = monthYear.getText().toString().split(" ");
                colorToday = day17.getText().toString().concat("_").concat(String.valueOf(returnMonthStringToInt(splitter[0]))).concat("_").concat(splitter[1]);
            }
            dateFutureCheck();
            colorButtonUnderTheCalendar();
        });
        day18.setOnClickListener(v -> {
            clearBackground();
            colorOnlyToday();
            selectedColor(18);
            if (getView() != null) {
                String[] splitter = monthYear.getText().toString().split(" ");
                colorToday = day18.getText().toString().concat("_").concat(String.valueOf(returnMonthStringToInt(splitter[0]))).concat("_").concat(splitter[1]);
            }
            dateFutureCheck();
            colorButtonUnderTheCalendar();
        });
        day19.setOnClickListener(v -> {
            clearBackground();
            colorOnlyToday();
            selectedColor(19);
            if (getView() != null) {
                String[] splitter = monthYear.getText().toString().split(" ");
                colorToday = day19.getText().toString().concat("_").concat(String.valueOf(returnMonthStringToInt(splitter[0]))).concat("_").concat(splitter[1]);
            }
            dateFutureCheck();
            colorButtonUnderTheCalendar();
        });
        day20.setOnClickListener(v -> {
            clearBackground();
            colorOnlyToday();
            selectedColor(20);
            if (getView() != null) {
                String[] splitter = monthYear.getText().toString().split(" ");
                colorToday = day20.getText().toString().concat("_").concat(String.valueOf(returnMonthStringToInt(splitter[0]))).concat("_").concat(splitter[1]);
            }
            dateFutureCheck();
            colorButtonUnderTheCalendar();
        });
        day21.setOnClickListener(v -> {
            clearBackground();
            colorOnlyToday();
            selectedColor(21);
            if (getView() != null) {
                String[] splitter = monthYear.getText().toString().split(" ");
                colorToday = day21.getText().toString().concat("_").concat(String.valueOf(returnMonthStringToInt(splitter[0]))).concat("_").concat(splitter[1]);
            }
            dateFutureCheck();
            colorButtonUnderTheCalendar();
        });
        day22.setOnClickListener(v -> {
            clearBackground();
            colorOnlyToday();
            selectedColor(22);
            if (getView() != null) {
                String[] splitter = monthYear.getText().toString().split(" ");
                colorToday = day22.getText().toString().concat("_").concat(String.valueOf(returnMonthStringToInt(splitter[0]))).concat("_").concat(splitter[1]);
            }
            dateFutureCheck();
            colorButtonUnderTheCalendar();
        });
        day23.setOnClickListener(v -> {
            clearBackground();
            colorOnlyToday();
            selectedColor(23);
            if (getView() != null) {
                String[] splitter = monthYear.getText().toString().split(" ");
                colorToday = day23.getText().toString().concat("_").concat(String.valueOf(returnMonthStringToInt(splitter[0]))).concat("_").concat(splitter[1]);
            }
            dateFutureCheck();
            colorButtonUnderTheCalendar();
        });
        day24.setOnClickListener(v -> {
            clearBackground();
            colorOnlyToday();
            selectedColor(24);
            if (getView() != null) {
                String[] splitter = monthYear.getText().toString().split(" ");
                colorToday = day24.getText().toString().concat("_").concat(String.valueOf(returnMonthStringToInt(splitter[0]))).concat("_").concat(splitter[1]);
            }
            dateFutureCheck();
            colorButtonUnderTheCalendar();
        });
        day25.setOnClickListener(v -> {
            clearBackground();
            colorOnlyToday();
            selectedColor(25);
            if (getView() != null) {
                String[] splitter = monthYear.getText().toString().split(" ");
                colorToday = day25.getText().toString().concat("_").concat(String.valueOf(returnMonthStringToInt(splitter[0]))).concat("_").concat(splitter[1]);
            }
            dateFutureCheck();
            colorButtonUnderTheCalendar();
        });
        day26.setOnClickListener(v -> {
            clearBackground();
            colorOnlyToday();
            selectedColor(26);
            if (getView() != null) {
                String[] splitter = monthYear.getText().toString().split(" ");
                colorToday = day26.getText().toString().concat("_").concat(String.valueOf(returnMonthStringToInt(splitter[0]))).concat("_").concat(splitter[1]);
            }
            dateFutureCheck();
            colorButtonUnderTheCalendar();
        });
        day27.setOnClickListener(v -> {
            clearBackground();
            colorOnlyToday();
            selectedColor(27);
            if (getView() != null) {
                String[] splitter = monthYear.getText().toString().split(" ");
                colorToday = day27.getText().toString().concat("_").concat(String.valueOf(returnMonthStringToInt(splitter[0]))).concat("_").concat(splitter[1]);
            }
            dateFutureCheck();
            colorButtonUnderTheCalendar();
        });
        day28.setOnClickListener(v -> {
            clearBackground();
            colorOnlyToday();
            selectedColor(28);
            if (getView() != null) {
                String[] splitter = monthYear.getText().toString().split(" ");
                colorToday = day28.getText().toString().concat("_").concat(String.valueOf(returnMonthStringToInt(splitter[0]))).concat("_").concat(splitter[1]);
            }
            dateFutureCheck();
            colorButtonUnderTheCalendar();
        });
        day29.setOnClickListener(v -> {
            clearBackground();
            colorOnlyToday();
            selectedColor(29);
            if (getView() != null) {
                String[] splitter = monthYear.getText().toString().split(" ");
                colorToday = day29.getText().toString().concat("_").concat(String.valueOf(returnMonthStringToInt(splitter[0]))).concat("_").concat(splitter[1]);
            }
            dateFutureCheck();
            colorButtonUnderTheCalendar();
        });
        day30.setOnClickListener(v -> {
            clearBackground();
            colorOnlyToday();
            selectedColor(30);
            if (getView() != null) {
                String[] splitter = monthYear.getText().toString().split(" ");
                colorToday = day30.getText().toString().concat("_").concat(String.valueOf(returnMonthStringToInt(splitter[0]))).concat("_").concat(splitter[1]);
            }
            dateFutureCheck();
            colorButtonUnderTheCalendar();
        });
        day31.setOnClickListener(v -> {
            clearBackground();
            colorOnlyToday();
            selectedColor(31);
            if (getView() != null) {
                String[] splitter = monthYear.getText().toString().split(" ");
                colorToday = day31.getText().toString().concat("_").concat(String.valueOf(returnMonthStringToInt(splitter[0]))).concat("_").concat(splitter[1]);
            }
            dateFutureCheck();
            colorButtonUnderTheCalendar();
        });
        day32.setOnClickListener(v -> {
            clearBackground();
            colorOnlyToday();
            selectedColor(32);
            if (getView() != null) {
                String[] splitter = monthYear.getText().toString().split(" ");
                colorToday = day32.getText().toString().concat("_").concat(String.valueOf(returnMonthStringToInt(splitter[0]))).concat("_").concat(splitter[1]);
            }
            dateFutureCheck();
            colorButtonUnderTheCalendar();
        });
        day33.setOnClickListener(v -> {
            clearBackground();
            colorOnlyToday();
            selectedColor(33);
            if (getView() != null) {
                String[] splitter = monthYear.getText().toString().split(" ");
                colorToday = day33.getText().toString().concat("_").concat(String.valueOf(returnMonthStringToInt(splitter[0]))).concat("_").concat(splitter[1]);
            }
            dateFutureCheck();
            colorButtonUnderTheCalendar();
        });
        day34.setOnClickListener(v -> {
            clearBackground();
            colorOnlyToday();
            selectedColor(34);
            if (getView() != null) {
                String[] splitter = monthYear.getText().toString().split(" ");
                colorToday = day34.getText().toString().concat("_").concat(String.valueOf(returnMonthStringToInt(splitter[0]))).concat("_").concat(splitter[1]);
            }
            dateFutureCheck();
            colorButtonUnderTheCalendar();
        });
        day35.setOnClickListener(v -> {
            clearBackground();
            colorOnlyToday();
            selectedColor(35);
            if (getView() != null) {
                String[] splitter = monthYear.getText().toString().split(" ");
                colorToday = day35.getText().toString().concat("_").concat(String.valueOf(returnMonthStringToInt(splitter[0]))).concat("_").concat(splitter[1]);
            }
            dateFutureCheck();
            colorButtonUnderTheCalendar();
        });
        day36.setOnClickListener(v -> {
            clearBackground();
            colorOnlyToday();
            selectedColor(36);
            if (getView() != null) {
                String[] splitter = monthYear.getText().toString().split(" ");
                colorToday = day36.getText().toString().concat("_").concat(String.valueOf(monthYear)).concat("_").concat(splitter[1]);
            }
            dateFutureCheck();
            colorButtonUnderTheCalendar();
        });
        day37.setOnClickListener(v -> {
            clearBackground();
            colorOnlyToday();
            selectedColor(37);
            if (getView() != null) {
                String[] splitter = monthYear.getText().toString().split(" ");
                colorToday = day37.getText().toString().concat("_").concat(String.valueOf(returnMonthStringToInt(splitter[0]))).concat("_").concat(splitter[1]);
            }
            dateFutureCheck();
            colorButtonUnderTheCalendar();
        });
    }

    private void removeButtons() {
        if (day1.getText().toString().equals(""))
            day1.setVisibility(View.INVISIBLE);
        if (day2.getText().toString().equals(""))
            day2.setVisibility(View.INVISIBLE);
        if (day3.getText().toString().equals(""))
            day3.setVisibility(View.INVISIBLE);
        if (day4.getText().toString().equals(""))
            day4.setVisibility(View.INVISIBLE);
        if (day5.getText().toString().equals(""))
            day5.setVisibility(View.INVISIBLE);
        if (day6.getText().toString().equals(""))
            day6.setVisibility(View.INVISIBLE);
        if (day29.getText().toString().equals(""))
            day29.setVisibility(View.INVISIBLE);
        if (day30.getText().toString().equals(""))
            day30.setVisibility(View.INVISIBLE);
        if (day31.getText().toString().equals(""))
            day31.setVisibility(View.INVISIBLE);
        if (day32.getText().toString().equals(""))
            day32.setVisibility(View.INVISIBLE);
        if (day33.getText().toString().equals(""))
            day33.setVisibility(View.INVISIBLE);
        if (day34.getText().toString().equals(""))
            day34.setVisibility(View.INVISIBLE);
        if (day35.getText().toString().equals(""))
            day35.setVisibility(View.INVISIBLE);
        if (day36.getText().toString().equals(""))
            day36.setVisibility(View.INVISIBLE);
        if (day37.getText().toString().equals(""))
            day37.setVisibility(View.INVISIBLE);
    }

    private void setAllButtonsToVisible() {
        day1.setVisibility(View.VISIBLE);
        day2.setVisibility(View.VISIBLE);
        day3.setVisibility(View.VISIBLE);
        day4.setVisibility(View.VISIBLE);
        day5.setVisibility(View.VISIBLE);
        day6.setVisibility(View.VISIBLE);
        day7.setVisibility(View.VISIBLE);
        day8.setVisibility(View.VISIBLE);
        day9.setVisibility(View.VISIBLE);
        day10.setVisibility(View.VISIBLE);
        day11.setVisibility(View.VISIBLE);
        day12.setVisibility(View.VISIBLE);
        day13.setVisibility(View.VISIBLE);
        day14.setVisibility(View.VISIBLE);
        day15.setVisibility(View.VISIBLE);
        day16.setVisibility(View.VISIBLE);
        day17.setVisibility(View.VISIBLE);
        day17.setVisibility(View.VISIBLE);
        day18.setVisibility(View.VISIBLE);
        day19.setVisibility(View.VISIBLE);
        day20.setVisibility(View.VISIBLE);
        day21.setVisibility(View.VISIBLE);
        day22.setVisibility(View.VISIBLE);
        day23.setVisibility(View.VISIBLE);
        day24.setVisibility(View.VISIBLE);
        day25.setVisibility(View.VISIBLE);
        day26.setVisibility(View.VISIBLE);
        day27.setVisibility(View.VISIBLE);
        day28.setVisibility(View.VISIBLE);
        day29.setVisibility(View.VISIBLE);
        day30.setVisibility(View.VISIBLE);
        day31.setVisibility(View.VISIBLE);
        day32.setVisibility(View.VISIBLE);
        day33.setVisibility(View.VISIBLE);
        day34.setVisibility(View.VISIBLE);
        day35.setVisibility(View.VISIBLE);
        day36.setVisibility(View.VISIBLE);
        day37.setVisibility(View.VISIBLE);
    }

    private int returnDay(int day) {
        if (day1.getText().toString().equals(String.valueOf(day)))
            return 1;
        else if (day2.getText().toString().equals(String.valueOf(day)))
            return 2;
        else if (day3.getText().toString().equals(String.valueOf(day)))
            return 3;
        else if (day4.getText().toString().equals(String.valueOf(day)))
            return 4;
        else if (day5.getText().toString().equals(String.valueOf(day)))
            return 5;
        else if (day6.getText().toString().equals(String.valueOf(day)))
            return 6;
        else if (day7.getText().toString().equals(String.valueOf(day)))
            return 7;
        else if (day8.getText().toString().equals(String.valueOf(day)))
            return 8;
        else if (day9.getText().toString().equals(String.valueOf(day)))
            return 9;
        else if (day10.getText().toString().equals(String.valueOf(day)))
            return 10;
        else if (day11.getText().toString().equals(String.valueOf(day)))
            return 11;
        else if (day12.getText().toString().equals(String.valueOf(day)))
            return 12;
        else if (day13.getText().toString().equals(String.valueOf(day)))
            return 13;
        else if (day14.getText().toString().equals(String.valueOf(day)))
            return 14;
        else if (day15.getText().toString().equals(String.valueOf(day)))
            return 15;
        else if (day16.getText().toString().equals(String.valueOf(day)))
            return 16;
        else if (day17.getText().toString().equals(String.valueOf(day)))
            return 17;
        else if (day18.getText().toString().equals(String.valueOf(day)))
            return 18;
        else if (day19.getText().toString().equals(String.valueOf(day)))
            return 19;
        else if (day20.getText().toString().equals(String.valueOf(day)))
            return 20;
        else if (day21.getText().toString().equals(String.valueOf(day)))
            return 21;
        else if (day22.getText().toString().equals(String.valueOf(day)))
            return 22;
        else if (day23.getText().toString().equals(String.valueOf(day)))
            return 23;
        else if (day24.getText().toString().equals(String.valueOf(day)))
            return 24;
        else if (day25.getText().toString().equals(String.valueOf(day)))
            return 25;
        else if (day26.getText().toString().equals(String.valueOf(day)))
            return 26;
        else if (day27.getText().toString().equals(String.valueOf(day)))
            return 27;
        else if (day28.getText().toString().equals(String.valueOf(day)))
            return 28;
        else if (day29.getText().toString().equals(String.valueOf(day)))
            return 29;
        else if (day30.getText().toString().equals(String.valueOf(day)))
            return 30;
        else if (day31.getText().toString().equals(String.valueOf(day)))
            return 31;
        else if (day32.getText().toString().equals(String.valueOf(day)))
            return 32;
        else if (day33.getText().toString().equals(String.valueOf(day)))
            return 33;
        else if (day34.getText().toString().equals(String.valueOf(day)))
            return 34;
        else if (day35.getText().toString().equals(String.valueOf(day)))
            return 35;
        else if (day36.getText().toString().equals(String.valueOf(day)))
            return 36;
        else
            return 37;
    }

    private void colorToday() {
        monthYear=getView().findViewById(R.id.tv_month_year);
        if (getView() != null) {
            String[] text_to_split = monthYear.getText().toString().split(" ");
            int yearText = Integer.parseInt(text_to_split[1]);
            int monthText = returnMonthStringToInt(text_to_split[0]);
            String[] savedSplit = colorToday.split("_");
            int year = Integer.parseInt(savedSplit[2]);
            int month = Integer.parseInt(savedSplit[1]);
            int day = Integer.parseInt(savedSplit[0]);
            if ((yearText == year) && (monthText == month)) {
                if (!day1.getText().toString().equals("") && Integer.parseInt(day1.getText().toString()) == day)
                    selectedColor(1);
                else if (!day2.getText().toString().equals("") && Integer.parseInt(day2.getText().toString()) == day)
                    selectedColor(2);
                else if (!day3.getText().toString().equals("") && Integer.parseInt(day3.getText().toString()) == day)
                    selectedColor(3);
                else if (!day4.getText().toString().equals("") && Integer.parseInt(day4.getText().toString()) == day)
                    selectedColor(4);
                else if (!day5.getText().toString().equals("") && Integer.parseInt(day5.getText().toString()) == day)
                    selectedColor(5);
                else if (!day6.getText().toString().equals("") && Integer.parseInt(day6.getText().toString()) == day)
                    selectedColor(6);
                else if (!day7.getText().toString().equals("") && Integer.parseInt(day7.getText().toString()) == day)
                    selectedColor(7);
                else if (!day8.getText().toString().equals("") && Integer.parseInt(day8.getText().toString()) == day)
                    selectedColor(8);
                else if (!day9.getText().toString().equals("") && Integer.parseInt(day9.getText().toString()) == day)
                    selectedColor(9);
                else if (!day10.getText().toString().equals("") && Integer.parseInt(day10.getText().toString()) == day)
                    selectedColor(10);
                else if (!day11.getText().toString().equals("") && Integer.parseInt(day11.getText().toString()) == day)
                    selectedColor(11);
                else if (!day12.getText().toString().equals("") && Integer.parseInt(day12.getText().toString()) == day)
                    selectedColor(12);
                else if (!day13.getText().toString().equals("") && Integer.parseInt(day13.getText().toString()) == day)
                    selectedColor(13);
                else if (!day14.getText().toString().equals("") && Integer.parseInt(day14.getText().toString()) == day)
                    selectedColor(14);
                else if (!day15.getText().toString().equals("") && Integer.parseInt(day15.getText().toString()) == day)
                    selectedColor(15);
                else if (!day16.getText().toString().equals("") && Integer.parseInt(day16.getText().toString()) == day)
                    selectedColor(16);
                else if (!day17.getText().toString().equals("") && Integer.parseInt(day17.getText().toString()) == day)
                    selectedColor(17);
                else if (!day18.getText().toString().equals("") && Integer.parseInt(day18.getText().toString()) == day)
                    selectedColor(18);
                else if (!day19.getText().toString().equals("") && Integer.parseInt(day19.getText().toString()) == day)
                    selectedColor(19);
                else if (!day20.getText().toString().equals("") && Integer.parseInt(day20.getText().toString()) == day)
                    selectedColor(20);
                else if (!day21.getText().toString().equals("") && Integer.parseInt(day21.getText().toString()) == day)
                    selectedColor(21);
                else if (!day22.getText().toString().equals("") && Integer.parseInt(day22.getText().toString()) == day)
                    selectedColor(22);
                else if (!day23.getText().toString().equals("") && Integer.parseInt(day23.getText().toString()) == day)
                    selectedColor(23);
                else if (!day24.getText().toString().equals("") && Integer.parseInt(day24.getText().toString()) == day)
                    selectedColor(24);
                else if (!day25.getText().toString().equals("") && Integer.parseInt(day25.getText().toString()) == day)
                    selectedColor(25);
                else if (!day26.getText().toString().equals("") && Integer.parseInt(day26.getText().toString()) == day)
                    selectedColor(26);
                else if (!day27.getText().toString().equals("") && Integer.parseInt(day27.getText().toString()) == day)
                    selectedColor(27);
                else if (!day28.getText().toString().equals("") && Integer.parseInt(day28.getText().toString()) == day)
                    selectedColor(28);
                else if (!day29.getText().toString().equals("") && Integer.parseInt(day29.getText().toString()) == day)
                    selectedColor(29);
                else if (!day30.getText().toString().equals("") && Integer.parseInt(day30.getText().toString()) == day)
                    selectedColor(30);
                else if (!day31.getText().toString().equals("") && Integer.parseInt(day31.getText().toString()) == day)
                    selectedColor(31);
                else if (!day32.getText().toString().equals("") && Integer.parseInt(day32.getText().toString()) == day)
                    selectedColor(32);
                else if (!day33.getText().toString().equals("") && Integer.parseInt(day33.getText().toString()) == day)
                    selectedColor(33);
                else if (!day34.getText().toString().equals("") && Integer.parseInt(day34.getText().toString()) == day)
                    selectedColor(34);
                else if (!day35.getText().toString().equals("") && Integer.parseInt(day35.getText().toString()) == day)
                    selectedColor(35);
                else if (!day36.getText().toString().equals("") && Integer.parseInt(day36.getText().toString()) == day)
                    selectedColor(36);
                else if (!day37.getText().toString().equals("") && Integer.parseInt(day37.getText().toString()) == day)
                    selectedColor(37);
            }
        }
    }

    private void calendarColor() {
        monthYear=getView().findViewById(R.id.tv_month_year);
        if (getView() != null) {
            String veryBadColor = returnTheColorOfMood(1);
            String badColor = returnTheColorOfMood(2);
            String okColor = returnTheColorOfMood(3);
            String goodColor = returnTheColorOfMood(4);
            String veryGoodColor = returnTheColorOfMood(5);
            String noMoodColor = returnTheColorOfMood(0);
            colors = new String[returnLastDayOfMonth() + 1];
            Calendar calendar = Calendar.getInstance();
            String[] splitMonthAndYear = monthYear.getText().toString().split(" ");
            int year = Integer.parseInt(splitMonthAndYear[1]);
            int month = returnMonthStringToInt(splitMonthAndYear[0]);
            if (!checkPastNowFuture().equals("future")) {
                for (int i = 1; i <= returnLastDayOfMonth(); i++) {
                    calendar.set(year, month, i);
                    long timeInMillis = calendar.getTimeInMillis();
                    if (TimeMood.returnTimeInMidnight(timeInMillis) >= TimeMood.returnTimeInMidnight(startDate)) {
                        if (TimeMood.returnTimeInMidnight(timeInMillis) <= TimeMood.returnTimeInMidnight(System.currentTimeMillis())) {
                            if (returnColorOfDays(timeInMillis) == 0)
                                colors[i] = noMoodColor;
                            else if (returnColorOfDays(timeInMillis) == 1)
                                colors[i] = veryBadColor;
                            else if (returnColorOfDays(timeInMillis) == 2)
                                colors[i] = badColor;
                            else if (returnColorOfDays(timeInMillis) == 3)
                                colors[i] = okColor;
                            else if (returnColorOfDays(timeInMillis) == 4)
                                colors[i] = goodColor;
                            else if (returnColorOfDays(timeInMillis) == 5)
                                colors[i] = veryGoodColor;
                        } else
                            colors[i] = noMoodColor;
                    } else
                        colors[i] = noMoodColor;
                }
            } else {
                for (int i = 1; i <= returnLastDayOfMonth(); i++)
                    colors[i] = noMoodColor;
            }
            for (int i = 1; i <= returnLastDayOfMonth(); i++) {
                if (returnDay(i) == 1) {
                    if (day1.getCurrentTextColor() != Color.WHITE)
                        day1.setTextColor(Color.parseColor(colors[i]));
                } else if (returnDay(i) == 2) {
                    if (day2.getCurrentTextColor() != Color.WHITE)
                        day2.setTextColor(Color.parseColor(colors[i]));
                } else if (returnDay(i) == 3) {
                    if (day3.getCurrentTextColor() != Color.WHITE)
                        day3.setTextColor(Color.parseColor(colors[i]));
                } else if (returnDay(i) == 4) {
                    if (day4.getCurrentTextColor() != Color.WHITE)
                        day4.setTextColor(Color.parseColor(colors[i]));
                } else if (returnDay(i) == 5) {
                    if (day5.getCurrentTextColor() != Color.WHITE)
                        day5.setTextColor(Color.parseColor(colors[i]));
                } else if (returnDay(i) == 6) {
                    if (day6.getCurrentTextColor() != Color.WHITE)
                        day6.setTextColor(Color.parseColor(colors[i]));
                } else if (returnDay(i) == 7) {
                    if (day7.getCurrentTextColor() != Color.WHITE)
                        day7.setTextColor(Color.parseColor(colors[i]));
                } else if (returnDay(i) == 8) {
                    if (day8.getCurrentTextColor() != Color.WHITE)
                        day8.setTextColor(Color.parseColor(colors[i]));
                } else if (returnDay(i) == 9) {
                    if (day9.getCurrentTextColor() != Color.WHITE)
                        day9.setTextColor(Color.parseColor(colors[i]));
                } else if (returnDay(i) == 10) {
                    if (day10.getCurrentTextColor() != Color.WHITE)
                        day10.setTextColor(Color.parseColor(colors[i]));
                } else if (returnDay(i) == 11) {
                    if (day11.getCurrentTextColor() != Color.WHITE)
                        day11.setTextColor(Color.parseColor(colors[i]));
                } else if (returnDay(i) == 12) {
                    if (day12.getCurrentTextColor() != Color.WHITE)
                        day12.setTextColor(Color.parseColor(colors[i]));
                } else if (returnDay(i) == 13) {
                    if (day13.getCurrentTextColor() != Color.WHITE)
                        day13.setTextColor(Color.parseColor(colors[i]));
                } else if (returnDay(i) == 14) {
                    if (day14.getCurrentTextColor() != Color.WHITE)
                        day14.setTextColor(Color.parseColor(colors[i]));
                } else if (returnDay(i) == 15) {
                    if (day15.getCurrentTextColor() != Color.WHITE)
                        day15.setTextColor(Color.parseColor(colors[i]));
                } else if (returnDay(i) == 16) {
                    if (day16.getCurrentTextColor() != Color.WHITE)
                        day16.setTextColor(Color.parseColor(colors[i]));
                } else if (returnDay(i) == 17) {
                    if (day17.getCurrentTextColor() != Color.WHITE)
                        day17.setTextColor(Color.parseColor(colors[i]));
                } else if (returnDay(i) == 18) {
                    if (day18.getCurrentTextColor() != Color.WHITE)
                        day18.setTextColor(Color.parseColor(colors[i]));
                } else if (returnDay(i) == 19) {
                    if (day19.getCurrentTextColor() != Color.WHITE)
                        day19.setTextColor(Color.parseColor(colors[i]));
                } else if (returnDay(i) == 20) {
                    if (day20.getCurrentTextColor() != Color.WHITE)
                        day20.setTextColor(Color.parseColor(colors[i]));
                } else if (returnDay(i) == 21) {
                    if (day21.getCurrentTextColor() != Color.WHITE)
                        day21.setTextColor(Color.parseColor(colors[i]));
                } else if (returnDay(i) == 22) {
                    if (day22.getCurrentTextColor() != Color.WHITE)
                        day22.setTextColor(Color.parseColor(colors[i]));
                } else if (returnDay(i) == 23) {
                    if (day23.getCurrentTextColor() != Color.WHITE)
                        day23.setTextColor(Color.parseColor(colors[i]));
                } else if (returnDay(i) == 24) {
                    if (day24.getCurrentTextColor() != Color.WHITE)
                        day24.setTextColor(Color.parseColor(colors[i]));
                } else if (returnDay(i) == 25) {
                    if (day25.getCurrentTextColor() != Color.WHITE)
                        day25.setTextColor(Color.parseColor(colors[i]));
                } else if (returnDay(i) == 26) {
                    if (day26.getCurrentTextColor() != Color.WHITE)
                        day26.setTextColor(Color.parseColor(colors[i]));
                } else if (returnDay(i) == 27) {
                    if (day27.getCurrentTextColor() != Color.WHITE)
                        day27.setTextColor(Color.parseColor(colors[i]));
                } else if (returnDay(i) == 28) {
                    if (day28.getCurrentTextColor() != Color.WHITE)
                        day28.setTextColor(Color.parseColor(colors[i]));
                } else if (returnDay(i) == 29) {
                    if (day29.getCurrentTextColor() != Color.WHITE)
                        day29.setTextColor(Color.parseColor(colors[i]));
                } else if (returnDay(i) == 30) {
                    if (day30.getCurrentTextColor() != Color.WHITE)
                        day30.setTextColor(Color.parseColor(colors[i]));
                } else if (returnDay(i) == 31) {
                    if (day31.getCurrentTextColor() != Color.WHITE)
                        day31.setTextColor(Color.parseColor(colors[i]));
                } else if (returnDay(i) == 32) {
                    if (day32.getCurrentTextColor() != Color.WHITE)
                        day32.setTextColor(Color.parseColor(colors[i]));
                } else if (returnDay(i) == 33) {
                    if (day33.getCurrentTextColor() != Color.WHITE)
                        day33.setTextColor(Color.parseColor(colors[i]));
                } else if (returnDay(i) == 34) {
                    if (day34.getCurrentTextColor() != Color.WHITE)
                        day34.setTextColor(Color.parseColor(colors[i]));
                } else if (returnDay(i) == 35) {
                    if (day35.getCurrentTextColor() != Color.WHITE)
                        day35.setTextColor(Color.parseColor(colors[i]));
                } else if (returnDay(i) == 36) {
                    if (day36.getCurrentTextColor() != Color.WHITE)
                        day36.setTextColor(Color.parseColor(colors[i]));
                } else {
                    if (day37.getCurrentTextColor() != Color.WHITE)
                        day37.setTextColor(Color.parseColor(colors[i]));
                }
            }
        }
    }

    private void dateFutureCheck() {
        if (getView() != null) {
            monthYear = getView().findViewById(R.id.tv_month_year);
            String[] splitMonthYear = colorToday.split("_");
            String[] monthYear = this.monthYear.getText().toString().split(" ");
            int day = Integer.parseInt(splitMonthYear[0]);
            int month = returnMonthStringToInt(monthYear[0]);
            int year = Integer.parseInt(monthYear[1]);
            Calendar calendar = Calendar.getInstance();
            int yearNow = calendar.get(Calendar.YEAR);
            int monthNow = calendar.get(Calendar.MONTH);
            int dayNow = calendar.get(Calendar.DAY_OF_MONTH);
            Calendar calendarNew = Calendar.getInstance();
            calendarNew.set(year, month, day);
            String[] splitterTmp = this.monthYear.getText().toString().split(" ");
            String monthText = String.valueOf(returnMonthStringToInt(splitterTmp[0]));
            String yearText = String.valueOf(splitterTmp[1]);
            String[] splitterValue = colorToday.split("_");
            if (calendarNew.getTimeInMillis() < TimeMood.returnTimeInMidnight(startDate))
                hideOrUnHideTheButton(0);
            else {
                if (year > yearNow)
                    hideOrUnHideTheButton(0);
                else if (year == yearNow) {
                    if (month > monthNow)
                        hideOrUnHideTheButton(0);
                    else if (month == monthNow) {
                        if (day >= dayNow)
                            hideOrUnHideTheButton(0);
                        else {
                            if (monthText.equals(splitterValue[1]) && yearText.equals(splitterValue[2]))
                                hideOrUnHideTheButton(1);
                            else
                                hideOrUnHideTheButton(0);
                        }
                    } else {
                        if (monthText.equals(splitterValue[1]) && yearText.equals(splitterValue[2]))
                            hideOrUnHideTheButton(1);
                        else
                            hideOrUnHideTheButton(0);
                    }
                } else {
                    if (monthText.equals(splitterValue[1]) && yearText.equals(splitterValue[2]))
                        hideOrUnHideTheButton(1);
                    else
                        hideOrUnHideTheButton(0);
                }
            }
        }
    }

    private void hideOrUnHideTheButton(int which) {
        if (getView() != null && getContext() != null) {
            Button veryBadMoodButton = getView().findViewById(R.id.bt_very_bad_mood_edit);
            Button badMoodButton = getView().findViewById(R.id.bt_bad_mood_edit);
            Button okMoodButton = getView().findViewById(R.id.bt_ok_mood_edit);
            Button goodMoodButton = getView().findViewById(R.id.bt_good_mood_edit);
            Button veryGoodMoodButton = getView().findViewById(R.id.bt_very_good_mood_edit);
            View veryBadMoodView = getView().findViewById(R.id.view_very_bad_mood_edit);
            View badMoodView = getView().findViewById(R.id.view_bad_mood_edit);
            View okMoodView = getView().findViewById(R.id.view_ok_mood_edit);
            View goodMoodView = getView().findViewById(R.id.view_good_mood_edit);
            View veryGoodMoodView = getView().findViewById(R.id.view_very_good_mood_edit);
            View veryBadMoodBackground = getView().findViewById(R.id.view_very_bad_mood_edit_background);
            View badMoodBackground = getView().findViewById(R.id.view_bad_mood_edit_background);
            View okMoodBackground = getView().findViewById(R.id.view_ok_mood_edit_background);
            View goodMoodBackground = getView().findViewById(R.id.view_good_mood_edit_background);
            View veryGoodMoodBackground = getView().findViewById(R.id.view_very_good_mood_edit_background);
            View veryBadMoodCheck = getView().findViewById(R.id.view_very_bad_mood_edit_check);
            View badMoodCheck = getView().findViewById(R.id.view_bad_mood_edit_check);
            View okMoodCheck = getView().findViewById(R.id.view_ok_mood_edit_check);
            View goodMoodCheck = getView().findViewById(R.id.view_good_mood_edit_check);
            View veryGooDMoodCheck = getView().findViewById(R.id.view_very_good_mood_edit_check);
            TextView editMood = getView().findViewById(R.id.tv_edit_mood);
            if (which == 0) {
                veryBadMoodButton.setVisibility(View.GONE);
                badMoodButton.setVisibility(View.GONE);
                okMoodButton.setVisibility(View.GONE);
                goodMoodButton.setVisibility(View.GONE);
                veryGoodMoodButton.setVisibility(View.GONE);
                editMood.setVisibility(View.GONE);
                veryBadMoodView.setVisibility(View.GONE);
                badMoodView.setVisibility(View.GONE);
                okMoodView.setVisibility(View.GONE);
                goodMoodView.setVisibility(View.GONE);
                veryGoodMoodView.setVisibility(View.GONE);
                veryBadMoodBackground.setVisibility(View.GONE);
                badMoodBackground.setVisibility(View.GONE);
                okMoodBackground.setVisibility(View.GONE);
                goodMoodBackground.setVisibility(View.GONE);
                veryGoodMoodBackground.setVisibility(View.GONE);
                veryBadMoodCheck.setVisibility(View.GONE);
                badMoodCheck.setVisibility(View.GONE);
                okMoodCheck.setVisibility(View.GONE);
                goodMoodCheck.setVisibility(View.GONE);
                veryGooDMoodCheck.setVisibility(View.GONE);
                ConstraintLayout constraintLayout = getView().findViewById(R.id.lc_calendar);
                ConstraintSet constraintSet = new ConstraintSet();
                constraintSet.clone(constraintLayout);
                constraintSet.applyTo(constraintLayout);
            } else {
                veryBadMoodButton.setVisibility(View.VISIBLE);
                badMoodButton.setVisibility(View.VISIBLE);
                okMoodButton.setVisibility(View.VISIBLE);
                goodMoodButton.setVisibility(View.VISIBLE);
                veryGoodMoodButton.setVisibility(View.VISIBLE);
                editMood.setVisibility(View.VISIBLE);
                veryBadMoodView.setVisibility(View.VISIBLE);
                badMoodView.setVisibility(View.VISIBLE);
                okMoodView.setVisibility(View.VISIBLE);
                goodMoodView.setVisibility(View.VISIBLE);
                veryGoodMoodView.setVisibility(View.VISIBLE);
                editMood.setVisibility(View.VISIBLE);
                ConstraintLayout constraintLayout = getView().findViewById(R.id.lc_calendar);
                ConstraintSet constraintSet = new ConstraintSet();
                constraintSet.clone(constraintLayout);
                constraintSet.connect(editMood.getId(), ConstraintSet.TOP, constraintLayout.getId(), ConstraintSet.TOP, (int) convertDpToPixel(returnTheLengthOfStat() + 28, getContext()));
                constraintSet.applyTo(constraintLayout);
            }
        }
    }

    public static float convertDpToPixel(float dp, Context context) {
        return dp * ((float) context.getResources().getDisplayMetrics().densityDpi / DisplayMetrics.DENSITY_DEFAULT);
    }

    private int returnTheLengthOfStat() {
        if (day36.getText().toString().equals("")) {
            if (day29.getText().toString().equals(""))
                return 240;
            else
                return 280;
        } else
            return 320;
    }


    private void colorOnlyToday() {
        if (getView() != null) {
            monthYear = getView().findViewById(R.id.tv_month_year);
            String[] split_the_month_and_year = monthYear.getText().toString().split(" ");
            String[] split_the_color = colorToday.split("_");
            int oldDay = Integer.parseInt(split_the_color[0]);
            int oldMonth = Integer.parseInt(split_the_color[1]);
            int oldYear = Integer.parseInt(split_the_color[2]);
            int newMonth = returnMonthStringToInt(split_the_month_and_year[0]);
            int newYear = Integer.parseInt(split_the_month_and_year[1]);
            if (oldMonth == newMonth && oldYear == newYear) {
                if (colors[oldDay] != null) {
                    if (day1.getText().toString().equals(String.valueOf(oldDay)))
                        day1.setTextColor(Color.parseColor(colors[oldDay]));
                    else if (day2.getText().toString().equals(String.valueOf(oldDay)))
                        day2.setTextColor(Color.parseColor(colors[oldDay]));
                    else if (day3.getText().toString().equals(String.valueOf(oldDay)))
                        day3.setTextColor(Color.parseColor(colors[oldDay]));
                    else if (day4.getText().toString().equals(String.valueOf(oldDay)))
                        day4.setTextColor(Color.parseColor(colors[oldDay]));
                    else if (day5.getText().toString().equals(String.valueOf(oldDay)))
                        day5.setTextColor(Color.parseColor(colors[oldDay]));
                    else if (day6.getText().toString().equals(String.valueOf(oldDay)))
                        day6.setTextColor(Color.parseColor(colors[oldDay]));
                    else if (day7.getText().toString().equals(String.valueOf(oldDay)))
                        day7.setTextColor(Color.parseColor(colors[oldDay]));
                    else if (day8.getText().toString().equals(String.valueOf(oldDay)))
                        day8.setTextColor(Color.parseColor(colors[oldDay]));
                    else if (day9.getText().toString().equals(String.valueOf(oldDay)))
                        day9.setTextColor(Color.parseColor(colors[oldDay]));
                    else if (day10.getText().toString().equals(String.valueOf(oldDay)))
                        day10.setTextColor(Color.parseColor(colors[oldDay]));
                    else if (day11.getText().toString().equals(String.valueOf(oldDay)))
                        day11.setTextColor(Color.parseColor(colors[oldDay]));
                    else if (day12.getText().toString().equals(String.valueOf(oldDay)))
                        day12.setTextColor(Color.parseColor(colors[oldDay]));
                    else if (day13.getText().toString().equals(String.valueOf(oldDay)))
                        day13.setTextColor(Color.parseColor(colors[oldDay]));
                    else if (day14.getText().toString().equals(String.valueOf(oldDay)))
                        day14.setTextColor(Color.parseColor(colors[oldDay]));
                    else if (day15.getText().toString().equals(String.valueOf(oldDay)))
                        day15.setTextColor(Color.parseColor(colors[oldDay]));
                    else if (day16.getText().toString().equals(String.valueOf(oldDay)))
                        day16.setTextColor(Color.parseColor(colors[oldDay]));
                    else if (day17.getText().toString().equals(String.valueOf(oldDay)))
                        day17.setTextColor(Color.parseColor(colors[oldDay]));
                    else if (day18.getText().toString().equals(String.valueOf(oldDay)))
                        day18.setTextColor(Color.parseColor(colors[oldDay]));
                    else if (day19.getText().toString().equals(String.valueOf(oldDay)))
                        day19.setTextColor(Color.parseColor(colors[oldDay]));
                    else if (day20.getText().toString().equals(String.valueOf(oldDay)))
                        day20.setTextColor(Color.parseColor(colors[oldDay]));
                    else if (day21.getText().toString().equals(String.valueOf(oldDay)))
                        day21.setTextColor(Color.parseColor(colors[oldDay]));
                    else if (day22.getText().toString().equals(String.valueOf(oldDay)))
                        day22.setTextColor(Color.parseColor(colors[oldDay]));
                    else if (day23.getText().toString().equals(String.valueOf(oldDay)))
                        day23.setTextColor(Color.parseColor(colors[oldDay]));
                    else if (day24.getText().toString().equals(String.valueOf(oldDay)))
                        day24.setTextColor(Color.parseColor(colors[oldDay]));
                    else if (day25.getText().toString().equals(String.valueOf(oldDay)))
                        day25.setTextColor(Color.parseColor(colors[oldDay]));
                    else if (day26.getText().toString().equals(String.valueOf(oldDay)))
                        day26.setTextColor(Color.parseColor(colors[oldDay]));
                    else if (day27.getText().toString().equals(String.valueOf(oldDay)))
                        day27.setTextColor(Color.parseColor(colors[oldDay]));
                    else if (day28.getText().toString().equals(String.valueOf(oldDay)))
                        day28.setTextColor(Color.parseColor(colors[oldDay]));
                    else if (day29.getText().toString().equals(String.valueOf(oldDay)))
                        day29.setTextColor(Color.parseColor(colors[oldDay]));
                    else if (day30.getText().toString().equals(String.valueOf(oldDay)))
                        day30.setTextColor(Color.parseColor(colors[oldDay]));
                    else if (day31.getText().toString().equals(String.valueOf(oldDay)))
                        day31.setTextColor(Color.parseColor(colors[oldDay]));
                    else if (day32.getText().toString().equals(String.valueOf(oldDay)))
                        day32.setTextColor(Color.parseColor(colors[oldDay]));
                    else if (day33.getText().toString().equals(String.valueOf(oldDay)))
                        day33.setTextColor(Color.parseColor(colors[oldDay]));
                    else if (day34.getText().toString().equals(String.valueOf(oldDay)))
                        day34.setTextColor(Color.parseColor(colors[oldDay]));
                    else if (day35.getText().toString().equals(String.valueOf(oldDay)))
                        day35.setTextColor(Color.parseColor(colors[oldDay]));
                    else if (day36.getText().toString().equals(String.valueOf(oldDay)))
                        day36.setTextColor(Color.parseColor(colors[oldDay]));
                    else
                        day37.setTextColor(Color.parseColor(colors[oldDay]));
                }
            }
        }
    }

    private void clearBackground() {
        day1.setBackgroundResource(R.drawable.round_button);
        day2.setBackgroundResource(R.drawable.round_button);
        day3.setBackgroundResource(R.drawable.round_button);
        day4.setBackgroundResource(R.drawable.round_button);
        day5.setBackgroundResource(R.drawable.round_button);
        day6.setBackgroundResource(R.drawable.round_button);
        day7.setBackgroundResource(R.drawable.round_button);
        day8.setBackgroundResource(R.drawable.round_button);
        day9.setBackgroundResource(R.drawable.round_button);
        day10.setBackgroundResource(R.drawable.round_button);
        day11.setBackgroundResource(R.drawable.round_button);
        day12.setBackgroundResource(R.drawable.round_button);
        day13.setBackgroundResource(R.drawable.round_button);
        day14.setBackgroundResource(R.drawable.round_button);
        day15.setBackgroundResource(R.drawable.round_button);
        day16.setBackgroundResource(R.drawable.round_button);
        day17.setBackgroundResource(R.drawable.round_button);
        day18.setBackgroundResource(R.drawable.round_button);
        day19.setBackgroundResource(R.drawable.round_button);
        day20.setBackgroundResource(R.drawable.round_button);
        day21.setBackgroundResource(R.drawable.round_button);
        day22.setBackgroundResource(R.drawable.round_button);
        day23.setBackgroundResource(R.drawable.round_button);
        day24.setBackgroundResource(R.drawable.round_button);
        day25.setBackgroundResource(R.drawable.round_button);
        day26.setBackgroundResource(R.drawable.round_button);
        day27.setBackgroundResource(R.drawable.round_button);
        day28.setBackgroundResource(R.drawable.round_button);
        day29.setBackgroundResource(R.drawable.round_button);
        day30.setBackgroundResource(R.drawable.round_button);
        day31.setBackgroundResource(R.drawable.round_button);
        day32.setBackgroundResource(R.drawable.round_button);
        day33.setBackgroundResource(R.drawable.round_button);
        day34.setBackgroundResource(R.drawable.round_button);
        day35.setBackgroundResource(R.drawable.round_button);
        day36.setBackgroundResource(R.drawable.round_button);
        day37.setBackgroundResource(R.drawable.round_button);
    }

    private String checkPastNowFuture() {
        if (getView() != null) {
            monthYear = getView().findViewById(R.id.tv_month_year);
            String[] splitMonthAndYear = monthYear.getText().toString().split(" ");
            int calendarMonth = returnMonthStringToInt(splitMonthAndYear[0]);
            int calendarYear = Integer.parseInt(splitMonthAndYear[1]);
            Calendar calendar = Calendar.getInstance();
            int yearNow = calendar.get(Calendar.YEAR);
            int monthNow = calendar.get(Calendar.MONTH);
            if (calendarYear > yearNow)
                return "future";
            else if (calendarYear == yearNow) {
                if (calendarMonth > monthNow)
                    return "future";
                else if (calendarMonth == monthNow)
                    return "current";
                else
                    return "past";
            } else
                return "past";
        } else
            return "future";
    }



    private void colorButtonUnderTheCalendar() {
        if (getView() != null) {
            monthYear = getView().findViewById(R.id.tv_month_year);
            String veryBadColor = returnTheColorOfMood(1);
            String badColor = returnTheColorOfMood(2);
            String okColor = returnTheColorOfMood(3);
            String goodColor = returnTheColorOfMood(4);
            String veryGoodColor = returnTheColorOfMood(5);
            String noMoodColor = returnTheColorOfMood(0);
            String[] splitterTemp = monthYear.getText().toString().split(" ");
            String month = String.valueOf(returnMonthStringToInt(splitterTemp[0]));
            String year = String.valueOf(splitterTemp[1]);
            String[] splitter = colorToday.split("_");
            final View veryBadMoodBackground = getView().findViewById(R.id.view_very_bad_mood_edit_background);
            final View badMoodBackground = getView().findViewById(R.id.view_bad_mood_edit_background);
            final View okMoodBackground = getView().findViewById(R.id.view_ok_mood_edit_background);
            final View goodMoodBackground = getView().findViewById(R.id.view_good_mood_edit_background);
            final View veryGoodMoodBackground = getView().findViewById(R.id.view_very_good_mood_edit_background);
            View very_good_mood_in_habits = getView().findViewById(R.id.view_very_good_mood_edit);
            if (month.equals(splitter[1]) && year.equals(splitter[2])) {
                if (colors[Integer.parseInt(splitter[0])].equals(veryBadColor)) {
                    if (veryBadMoodBackground.getVisibility() != View.VISIBLE && very_good_mood_in_habits.getVisibility() == View.VISIBLE)
                        selectTheMoodInCalendar(1);
                } else if (colors[Integer.parseInt(splitter[0])].equals(badColor) && very_good_mood_in_habits.getVisibility() == View.VISIBLE) {
                    if (badMoodBackground.getVisibility() != View.VISIBLE)
                        selectTheMoodInCalendar(2);
                } else if (colors[Integer.parseInt(splitter[0])].equals(okColor) && very_good_mood_in_habits.getVisibility() == View.VISIBLE) {
                    if (okMoodBackground.getVisibility() != View.VISIBLE)
                        selectTheMoodInCalendar(3);
                } else if (colors[Integer.parseInt(splitter[0])].equals(goodColor) && very_good_mood_in_habits.getVisibility() == View.VISIBLE) {
                    if (goodMoodBackground.getVisibility() != View.VISIBLE)
                        selectTheMoodInCalendar(4);
                } else if (colors[Integer.parseInt(splitter[0])].equals(veryGoodColor) && very_good_mood_in_habits.getVisibility() == View.VISIBLE) {
                    if (veryGoodMoodBackground.getVisibility() != View.VISIBLE)
                        selectTheMoodInCalendar(5);
                } else if (colors[Integer.parseInt(splitter[0])].equals(noMoodColor) && very_good_mood_in_habits.getVisibility() == View.VISIBLE)
                    selectTheMoodInCalendar(0);
            }
        }
    }

    private void yesAndNoButtons() {
        if (getView() != null) {
            final Button veryBadMoodButton = getView().findViewById(R.id.bt_very_bad_mood_edit);
            final Button badMoodButton = getView().findViewById(R.id.bt_bad_mood_edit);
            final Button okMoodButton = getView().findViewById(R.id.bt_ok_mood_edit);
            final Button goodMoodButton = getView().findViewById(R.id.bt_good_mood_edit);
            final Button veryGoodMoodButton = getView().findViewById(R.id.bt_very_good_mood_edit);
            final View veryBadMoodBackground = getView().findViewById(R.id.view_very_bad_mood_edit_background);
            final View badMoodBackground = getView().findViewById(R.id.view_bad_mood_edit_background);
            final View okMoodBackground = getView().findViewById(R.id.view_ok_mood_edit_background);
            final View goodMoodBackground = getView().findViewById(R.id.view_good_mood_edit_background);
            final View veryGoodMoodBackground = getView().findViewById(R.id.view_very_good_mood_edit_background);
            monthYear = getView().findViewById(R.id.tv_month_year);
            veryBadMoodButton.setOnClickListener(view -> {
                String[] split_for_day_month_year = colorToday.split("_");
                String[] month_and_year = monthYear.getText().toString().split(" ");
                int day = Integer.parseInt(split_for_day_month_year[0]);
                int month = returnMonthStringToInt(month_and_year[0]);
                int year = Integer.parseInt(month_and_year[1]);
                Calendar calendar = Calendar.getInstance();
                calendar.set(year, month, day);
                if (veryBadMoodBackground.getVisibility() == View.VISIBLE) {
                    selectTheMoodInCalendar(0);
                    saveTheInput(0, calendar.getTimeInMillis());
                } else {
                    selectTheMoodInCalendar(0);
                    selectTheMoodInCalendar(1);
                    saveTheInput(1, calendar.getTimeInMillis());
                }
                calendarColor();
                divideItIntoWeeks();
                makeEverythingAverageMood();
                lineChart();
                drawTheRightBarChartMood();
                lineChartForStreak.fitScreen();

            });
            badMoodButton.setOnClickListener(view -> {
                String[] split_for_day_month_year = colorToday.split("_");
                String[] month_and_year = monthYear.getText().toString().split(" ");
                int day = Integer.parseInt(split_for_day_month_year[0]);
                int month = returnMonthStringToInt(month_and_year[0]);
                int year = Integer.parseInt(month_and_year[1]);
                Calendar calendar = Calendar.getInstance();
                calendar.set(year, month, day);
                if (badMoodBackground.getVisibility() == View.VISIBLE) {
                    selectTheMoodInCalendar(0);
                    saveTheInput(0, calendar.getTimeInMillis());
                } else {
                    selectTheMoodInCalendar(0);
                    selectTheMoodInCalendar(2);
                    saveTheInput(2, calendar.getTimeInMillis());
                }
                calendarColor();
                divideItIntoWeeks();
                makeEverythingAverageMood();
                lineChart();
                drawTheRightBarChartMood();
                lineChartForStreak.fitScreen();

            });
            okMoodButton.setOnClickListener(view -> {
                String[] split_for_day_month_year = colorToday.split("_");
                String[] month_and_year = monthYear.getText().toString().split(" ");
                int day = Integer.parseInt(split_for_day_month_year[0]);
                int month = returnMonthStringToInt(month_and_year[0]);
                int year = Integer.parseInt(month_and_year[1]);
                Calendar calendar = Calendar.getInstance();
                calendar.set(year, month, day);
                if (okMoodBackground.getVisibility() == View.VISIBLE) {
                    selectTheMoodInCalendar(0);
                    saveTheInput(0, calendar.getTimeInMillis());
                } else {
                    selectTheMoodInCalendar(0);
                    selectTheMoodInCalendar(3);
                    saveTheInput(3, calendar.getTimeInMillis());
                }
                calendarColor();
                divideItIntoWeeks();
                makeEverythingAverageMood();
                lineChart();
                drawTheRightBarChartMood();
                lineChartForStreak.fitScreen();

            });
            goodMoodButton.setOnClickListener(view -> {
                String[] split_for_day_month_year = colorToday.split("_");
                String[] month_and_year = monthYear.getText().toString().split(" ");
                int day = Integer.parseInt(split_for_day_month_year[0]);
                int month = returnMonthStringToInt(month_and_year[0]);
                int year = Integer.parseInt(month_and_year[1]);
                Calendar calendar = Calendar.getInstance();
                calendar.set(year, month, day);
                if (goodMoodBackground.getVisibility() == View.VISIBLE) {
                    selectTheMoodInCalendar(0);
                    saveTheInput(0, calendar.getTimeInMillis());
                } else {
                    selectTheMoodInCalendar(0);
                    selectTheMoodInCalendar(4);
                    saveTheInput(4, calendar.getTimeInMillis());
                }
                calendarColor();
                divideItIntoWeeks();
                makeEverythingAverageMood();
                lineChart();
                drawTheRightBarChartMood();
                lineChartForStreak.fitScreen();
            });
            veryGoodMoodButton.setOnClickListener(view -> {
                String[] split_for_day_month_year = colorToday.split("_");
                String[] month_and_year = monthYear.getText().toString().split(" ");
                int day = Integer.parseInt(split_for_day_month_year[0]);
                int month = returnMonthStringToInt(month_and_year[0]);
                int year = Integer.parseInt(month_and_year[1]);
                Calendar calendar = Calendar.getInstance();
                calendar.set(year, month, day);
                if (veryGoodMoodBackground.getVisibility() == View.VISIBLE) {
                    selectTheMoodInCalendar(0);
                    saveTheInput(0, calendar.getTimeInMillis());
                } else {
                    selectTheMoodInCalendar(0);
                    selectTheMoodInCalendar(5);
                    saveTheInput(5, calendar.getTimeInMillis());
                }
                calendarColor();
                divideItIntoWeeks();
                makeEverythingAverageMood();
                lineChart();
                drawTheRightBarChartMood();
                lineChartForStreak.fitScreen();
            });
        }
    }

    private void divideItIntoWeeks() {
        int month_start_day;
        if (day1.getVisibility() == View.VISIBLE)
            month_start_day = 7;
        else if (day2.getVisibility() == View.VISIBLE)
            month_start_day = 6;
        else if (day3.getVisibility() == View.VISIBLE)
            month_start_day = 5;
        else if (day4.getVisibility() == View.VISIBLE)
            month_start_day = 4;
        else if (day5.getVisibility() == View.VISIBLE)
            month_start_day = 3;
        else if (day6.getVisibility() == View.VISIBLE)
            month_start_day = 2;
        else
            month_start_day = 1;
        String[] splitter_read = returnTheStateOfTheDays().split("split");
        String firstLineString = "";
        String secondLineString = "";
        String threeLineString = "";
        String fourLineString = "";
        String fiveLineString = "";
        String sixLineString = "";
        for (int i = 0; i < month_start_day; i++)
            firstLineString = firstLineString.concat(splitter_read[i]).concat("split");
        for (int i = month_start_day; i < month_start_day + 7; i++)
            secondLineString = secondLineString.concat(splitter_read[i]).concat("split");
        for (int i = month_start_day + 7; i < month_start_day + 14; i++)
            threeLineString = threeLineString.concat(splitter_read[i]).concat("split");
        for (int i = month_start_day + 14; i < month_start_day + 21; i++)
            fourLineString = fourLineString.concat(splitter_read[i]).concat("split");
        int empty = returnLastDayOfMonth() + (7 - month_start_day);
        if (empty <= 35) {
            for (int i = month_start_day + 21; i < splitter_read.length; i++)
                fiveLineString = fiveLineString.concat(splitter_read[i]).concat("split");
        } else {
            for (int i = month_start_day + 21; i < month_start_day + 28; i++)
                fiveLineString = fiveLineString.concat(splitter_read[i]).concat("split");
            for (int i = month_start_day + 28; i < splitter_read.length; i++)
                sixLineString = sixLineString.concat(splitter_read[i]).concat("split");
        }
        setTheFirstLine(firstLineString);
        setTheSecondLine(secondLineString);
        setTheThirdLine(threeLineString);
        setTheFourthLine(fourLineString);
        setTheFifthLine(fiveLineString);
        setTheSixthLine(sixLineString);
    }

    private void setTheFirstLine(String date) {
        if (getView() != null) {
            String[] dateSplit = date.split("split");
            int empty_length = 7 - dateSplit.length;
            for (int i = 0; i < empty_length; i++)
                date = "empty".concat("split").concat(date);
            String[] splitter_second = date.split("split");
            switch (splitter_second[0]) {
                case "empty":
                    modesDrawable[0] = 0;
                    break;
                case "start":
                    modesDrawable[0] = 1;
                    break;
                case "middle":
                    modesDrawable[0] = 2;
                    break;
                case "end":
                    modesDrawable[0] = 3;
                    break;
                case "beg_last":
                    modesDrawable[0] = 4;
                    break;
            }
            switch (splitter_second[1]) {
                case "empty":
                    modesDrawable[1] = 0;
                    break;
                case "start":
                    modesDrawable[1] = 1;
                    break;
                case "middle":
                    modesDrawable[1] = 2;
                    break;
                case "end":
                    modesDrawable[1] = 3;
                    break;
                case "beg_last":
                    modesDrawable[1] = 4;
                    break;
            }
            switch (splitter_second[2]) {
                case "empty":
                    modesDrawable[2] = 0;
                    break;
                case "start":
                    modesDrawable[2] = 1;
                    break;
                case "middle":
                    modesDrawable[2] = 2;
                    break;
                case "end":
                    modesDrawable[2] = 3;
                    break;
                case "beg_last":
                    modesDrawable[2] = 4;
                    break;
            }
            switch (splitter_second[3]) {
                case "empty":
                    modesDrawable[3] = 0;
                    break;
                case "start":
                    modesDrawable[3] = 1;
                    break;
                case "middle":
                    modesDrawable[3] = 2;
                    break;
                case "end":
                    modesDrawable[3] = 3;
                    break;
                case "beg_last":
                    modesDrawable[3] = 4;
                    break;
            }
            switch (splitter_second[4]) {
                case "empty":
                    modesDrawable[4] = 0;
                    break;
                case "start":
                    modesDrawable[4] = 1;
                    break;
                case "middle":
                    modesDrawable[4] = 2;
                    break;
                case "end":
                    modesDrawable[4] = 3;
                    break;
                case "beg_last":
                    modesDrawable[4] = 4;
                    break;
            }
            switch (splitter_second[5]) {
                case "empty":
                    modesDrawable[5] = 0;
                    break;
                case "start":
                    modesDrawable[5] = 1;
                    break;
                case "middle":
                    modesDrawable[5] = 2;
                    break;
                case "end":
                    modesDrawable[5] = 3;
                    break;
                case "beg_last":
                    modesDrawable[5] = 4;
                    break;
            }
            switch (splitter_second[6]) {
                case "empty":
                    modesDrawable[6] = 0;
                    break;
                case "start":
                    modesDrawable[6] = 1;
                    break;
                case "middle":
                    modesDrawable[6] = 2;
                    break;
                case "end":
                    modesDrawable[6] = 3;
                    break;
                case "beg_last":
                    modesDrawable[6] = 4;
                    break;
            }
        }
    }

    private void setTheSecondLine(String date) {
        if (getView() != null) {
            String[] dateSplit = date.split("split");
            switch (dateSplit[0]) {
                case "empty":
                    modesDrawable[7] = 0;
                    break;
                case "start":
                    modesDrawable[7] = 1;
                    break;
                case "middle":
                    modesDrawable[7] = 2;
                    break;
                case "end":
                    modesDrawable[7] = 3;
                    break;
                case "beg_last":
                    modesDrawable[7] = 4;
                    break;
            }
            switch (dateSplit[1]) {
                case "empty":
                    modesDrawable[8] = 0;
                    break;
                case "start":
                    modesDrawable[8] = 1;
                    break;
                case "middle":
                    modesDrawable[8] = 2;
                    break;
                case "end":
                    modesDrawable[8] = 3;
                    break;
                case "beg_last":
                    modesDrawable[8] = 4;
                    break;
            }
            switch (dateSplit[2]) {
                case "empty":
                    modesDrawable[9] = 0;
                    break;
                case "start":
                    modesDrawable[9] = 1;
                    break;
                case "middle":
                    modesDrawable[9] = 2;
                    break;
                case "end":
                    modesDrawable[9] = 3;
                    break;
                case "beg_last":
                    modesDrawable[9] = 4;
                    break;
            }
            switch (dateSplit[3]) {
                case "empty":
                    modesDrawable[10] = 0;
                    break;
                case "start":
                    modesDrawable[10] = 1;
                    break;
                case "middle":
                    modesDrawable[10] = 2;
                    break;
                case "end":
                    modesDrawable[10] = 3;
                    break;
                case "beg_last":
                    modesDrawable[10] = 4;
                    break;
            }
            switch (dateSplit[4]) {
                case "empty":
                    modesDrawable[11] = 0;
                    break;
                case "start":
                    modesDrawable[11] = 1;
                    break;
                case "middle":
                    modesDrawable[11] = 2;
                    break;
                case "end":
                    modesDrawable[11] = 3;
                    break;
                case "beg_last":
                    modesDrawable[11] = 4;
                    break;
            }
            switch (dateSplit[5]) {
                case "empty":
                    modesDrawable[12] = 0;
                    break;
                case "start":
                    modesDrawable[12] = 1;
                    break;
                case "middle":
                    modesDrawable[12] = 2;
                    break;
                case "end":
                    modesDrawable[12] = 3;
                    break;
                case "beg_last":
                    modesDrawable[12] = 4;
                    break;
            }
            switch (dateSplit[6]) {
                case "empty":
                    modesDrawable[13] = 0;
                    break;
                case "start":
                    modesDrawable[13] = 1;
                    break;
                case "middle":
                    modesDrawable[13] = 2;
                    break;
                case "end":
                    modesDrawable[13] = 3;
                    break;
                case "beg_last":
                    modesDrawable[13] = 4;
                    break;
            }
        }
    }

    private void setTheThirdLine(String date) {
        if (getView() != null) {
            String[] dateSplit = date.split("split");
            switch (dateSplit[0]) {
                case "empty":
                    modesDrawable[14] = 0;
                    break;
                case "start":
                    modesDrawable[14] = 1;
                    break;
                case "middle":
                    modesDrawable[14] = 2;
                    break;
                case "end":
                    modesDrawable[14] = 3;
                    break;
                case "beg_last":
                    modesDrawable[14] = 4;
                    break;
            }
            switch (dateSplit[1]) {
                case "empty":
                    modesDrawable[15] = 0;
                    break;
                case "start":
                    modesDrawable[15] = 1;
                    break;
                case "middle":
                    modesDrawable[15] = 2;
                    break;
                case "end":
                    modesDrawable[15] = 3;
                    break;
                case "beg_last":
                    modesDrawable[15] = 4;
                    break;
            }
            switch (dateSplit[2]) {
                case "empty":
                    modesDrawable[16] = 0;
                    break;
                case "start":
                    modesDrawable[16] = 1;
                    break;
                case "middle":
                    modesDrawable[16] = 2;
                    break;
                case "end":
                    modesDrawable[16] = 3;
                    break;
                case "beg_last":
                    modesDrawable[16] = 4;
                    break;
            }
            switch (dateSplit[3]) {
                case "empty":
                    modesDrawable[17] = 0;
                    break;
                case "start":
                    modesDrawable[17] = 1;
                    break;
                case "middle":
                    modesDrawable[17] = 2;
                    break;
                case "end":
                    modesDrawable[17] = 3;
                    break;
                case "beg_last":
                    modesDrawable[17] = 4;
                    break;
            }
            switch (dateSplit[4]) {
                case "empty":
                    modesDrawable[18] = 0;
                    break;
                case "start":
                    modesDrawable[18] = 1;
                    break;
                case "middle":
                    modesDrawable[18] = 2;
                    break;
                case "end":
                    modesDrawable[18] = 3;
                    break;
                case "beg_last":
                    modesDrawable[18] = 4;
                    break;
            }
            switch (dateSplit[5]) {
                case "empty":
                    modesDrawable[19] = 0;
                    break;
                case "start":
                    modesDrawable[19] = 1;
                    break;
                case "middle":
                    modesDrawable[19] = 2;
                    break;
                case "end":
                    modesDrawable[19] = 3;
                    break;
                case "beg_last":
                    modesDrawable[19] = 4;
                    break;
            }
            switch (dateSplit[6]) {
                case "empty":
                    modesDrawable[20] = 0;
                    break;
                case "start":
                    modesDrawable[20] = 1;
                    break;
                case "middle":
                    modesDrawable[20] = 2;
                    break;
                case "end":
                    modesDrawable[20] = 3;
                    break;
                case "beg_last":
                    modesDrawable[20] = 4;
                    break;
            }
        }
    }

    private void setTheFourthLine(String date) {
        if (getView() != null) {
            String[] dateSplit = date.split("split");
            switch (dateSplit[0]) {
                case "empty":
                    modesDrawable[21] = 0;
                    break;
                case "start":
                    modesDrawable[21] = 1;
                    break;
                case "middle":
                    modesDrawable[21] = 2;
                    break;
                case "end":
                    modesDrawable[21] = 3;
                    break;
                case "beg_last":
                    modesDrawable[21] = 4;
                    break;
            }
            switch (dateSplit[1]) {
                case "empty":
                    modesDrawable[22] = 0;
                    break;
                case "start":
                    modesDrawable[22] = 1;
                    break;
                case "middle":
                    modesDrawable[22] = 2;
                    break;
                case "end":
                    modesDrawable[22] = 3;
                    break;
                case "beg_last":
                    modesDrawable[22] = 4;
                    break;
            }
            switch (dateSplit[2]) {
                case "empty":
                    modesDrawable[23] = 0;
                    break;
                case "start":
                    modesDrawable[23] = 1;
                    break;
                case "middle":
                    modesDrawable[23] = 2;
                    break;
                case "end":
                    modesDrawable[23] = 3;
                    break;
                case "beg_last":
                    modesDrawable[23] = 4;
                    break;
            }
            switch (dateSplit[3]) {
                case "empty":
                    modesDrawable[24] = 0;
                    break;
                case "start":
                    modesDrawable[24] = 1;
                    break;
                case "middle":
                    modesDrawable[24] = 2;
                    break;
                case "end":
                    modesDrawable[24] = 3;
                    break;
                case "beg_last":
                    modesDrawable[24] = 4;
                    break;
            }
            switch (dateSplit[4]) {
                case "empty":
                    modesDrawable[25] = 0;
                    break;
                case "start":
                    modesDrawable[25] = 1;
                    break;
                case "middle":
                    modesDrawable[25] = 2;
                    break;
                case "end":
                    modesDrawable[25] = 3;
                    break;
                case "beg_last":
                    modesDrawable[25] = 4;
                    break;
            }
            switch (dateSplit[5]) {
                case "empty":
                    modesDrawable[26] = 0;
                    break;
                case "start":
                    modesDrawable[26] = 1;
                    break;
                case "middle":
                    modesDrawable[26] = 2;
                    break;
                case "end":
                    modesDrawable[26] = 3;
                    break;
                case "beg_last":
                    modesDrawable[26] = 4;
                    break;
            }
            switch (dateSplit[6]) {
                case "empty":
                    modesDrawable[27] = 0;
                    break;
                case "start":
                    modesDrawable[27] = 1;
                    break;
                case "middle":
                    modesDrawable[27] = 2;
                    break;
                case "end":
                    modesDrawable[27] = 3;
                    break;
                case "beg_last":
                    modesDrawable[27] = 4;
                    break;
            }
        }
    }

    private void setTheFifthLine(String date) {
        if (getView() != null) {
            String[] dateSplit = date.split("split");
            if (dateSplit.length > 0) {
                switch (dateSplit[0]) {
                    case "empty":
                        modesDrawable[28] = 0;
                        break;
                    case "start":
                        modesDrawable[28] = 1;
                        break;
                    case "middle":
                        modesDrawable[28] = 2;
                        break;
                    case "end":
                        modesDrawable[28] = 3;
                        break;
                    case "beg_last":
                        modesDrawable[28] = 4;
                        break;
                }
            } else
                modesDrawable[28] = 0;
            if (dateSplit.length > 1) {
                switch (dateSplit[1]) {
                    case "empty":
                        modesDrawable[29] = 0;
                        break;
                    case "start":
                        modesDrawable[29] = 1;
                        break;
                    case "middle":
                        modesDrawable[29] = 2;
                        break;
                    case "end":
                        modesDrawable[29] = 3;
                        break;
                    case "beg_last":
                        modesDrawable[29] = 4;
                        break;
                }
            } else
                modesDrawable[29] = 0;
            if (dateSplit.length > 2) {
                switch (dateSplit[2]) {
                    case "empty":
                        modesDrawable[30] = 0;
                        break;
                    case "start":
                        modesDrawable[30] = 1;
                        break;
                    case "middle":
                        modesDrawable[30] = 2;
                        break;
                    case "end":
                        modesDrawable[30] = 3;
                        break;
                    case "beg_last":
                        modesDrawable[30] = 4;
                        break;
                }
            } else
                modesDrawable[30] = 0;
            if (dateSplit.length > 3) {
                switch (dateSplit[3]) {
                    case "empty":
                        modesDrawable[31] = 0;
                        break;
                    case "start":
                        modesDrawable[31] = 1;
                        break;
                    case "middle":
                        modesDrawable[31] = 2;
                        break;
                    case "end":
                        modesDrawable[31] = 3;
                        break;
                    case "beg_last":
                        modesDrawable[31] = 4;
                        break;
                }
            } else
                modesDrawable[31] = 0;
            if (dateSplit.length > 4) {
                switch (dateSplit[4]) {
                    case "empty":
                        modesDrawable[32] = 0;
                        break;
                    case "start":
                        modesDrawable[32] = 1;
                        break;
                    case "middle":
                        modesDrawable[32] = 2;
                        break;
                    case "end":
                        modesDrawable[32] = 3;
                        break;
                    case "beg_last":
                        modesDrawable[32] = 4;
                        break;
                }
            } else
                modesDrawable[32] = 0;
            if (dateSplit.length > 5) {
                switch (dateSplit[5]) {
                    case "empty":
                        modesDrawable[33] = 0;
                        break;
                    case "start":
                        modesDrawable[33] = 1;
                        break;
                    case "middle":
                        modesDrawable[33] = 2;
                        break;
                    case "end":
                        modesDrawable[33] = 3;
                        break;
                    case "beg_last":
                        modesDrawable[33] = 4;
                        break;
                }
            } else
                modesDrawable[33] = 0;

            if (dateSplit.length > 6) {
                switch (dateSplit[6]) {
                    case "empty":
                        modesDrawable[34] = 0;
                        break;
                    case "start":
                        modesDrawable[34] = 1;
                        break;
                    case "middle":
                        modesDrawable[34] = 2;
                        break;
                    case "end":
                        modesDrawable[34] = 3;
                        break;
                    case "beg_last":
                        modesDrawable[34] = 4;
                        break;
                }
            } else
                modesDrawable[34] = 0;
        }
    }

    private void setTheSixthLine(String date) {
        if (getView() != null) {
            String[] date_split = date.split("split");
            if (date_split.length > 0) {
                switch (date_split[0]) {
                    case "empty":
                        modesDrawable[35] = 0;
                        break;
                    case "start":
                        modesDrawable[35] = 1;
                        break;
                    case "middle":
                        modesDrawable[35] = 2;
                        break;
                    case "end":
                        modesDrawable[35] = 3;
                        break;
                    case "beg_last":
                        modesDrawable[35] = 4;
                        break;
                }
            } else
                modesDrawable[35] = 0;
            if (date_split.length > 1) {
                switch (date_split[1]) {
                    case "empty":
                        modesDrawable[36] = 0;
                        break;
                    case "start":
                        modesDrawable[36] = 1;
                        break;
                    case "middle":
                        modesDrawable[36] = 2;
                        break;
                    case "end":
                        modesDrawable[36] = 3;
                        break;
                    case "beg_last":
                        modesDrawable[36] = 4;
                        break;
                }
            } else
                modesDrawable[36] = 0;
        }
    }

    private String returnTheStateOfTheDays() {
        if (getView() != null) {
            String monthInfo = "";
            monthYear = getView().findViewById(R.id.tv_month_year);
            String[] splitter = monthYear.getText().toString().split(" ");
            Calendar calendar = Calendar.getInstance();
            Calendar calendarNow = Calendar.getInstance();
            calendar.set(Calendar.MONTH, returnMonthStringToInt(splitter[0]));
            calendar.set(Calendar.YEAR, Integer.parseInt(splitter[1]));
            calendar.set(Calendar.DAY_OF_MONTH, 1);

            if (!checkPastNowFuture().equals("future")) {
                if (TimeMood.returnTimeInMidnight(calendar.getTimeInMillis()) < TimeMood.returnTimeInMidnight(startDate))
                    monthInfo = "empty".concat("split");
                else {
                    if (returnTheLastDayOfLastMonth().equals("continue")) {
                        if (returnStateOfDay(calendar.getTimeInMillis()))
                            monthInfo = "middle".concat("split");
                        else
                            monthInfo = "end".concat("split");
                    } else {
                        if (returnStateOfDay(calendar.getTimeInMillis()))
                            monthInfo = "start".concat("split");
                        else
                            monthInfo = "beg_last".concat("split");
                    }
                }
                if (checkPastNowFuture().equals("current")) {
                    for (int i = 2; i <= returnLastDayOfMonth(); i++) {
                        calendar.set(Calendar.DAY_OF_MONTH, i);
                        if (calendarNow.get(Calendar.DAY_OF_MONTH) < i || TimeMood.returnTimeInMidnight(calendar.getTimeInMillis()) < TimeMood.returnTimeInMidnight(startDate))
                            monthInfo = monthInfo.concat("empty").concat("split");
                        else {
                            String substring = monthInfo.substring(monthInfo.length() - 7, monthInfo.length() - 5);
                            if (substring.equals("rt") || substring.equals("le")) {
                                if (returnStateOfDay(calendar.getTimeInMillis()))
                                    monthInfo = monthInfo.concat("middle").concat("split");
                                else
                                    monthInfo = monthInfo.concat("end").concat("split");
                            } else {
                                if (returnStateOfDay(calendar.getTimeInMillis()))
                                    monthInfo = monthInfo.concat("start").concat("split");
                                else
                                    monthInfo = monthInfo.concat("beg_last").concat("split");
                            }
                        }
                    }
                } else {
                    for (int i = 2; i <= returnLastDayOfMonth(); i++) {
                        calendar.set(Calendar.DAY_OF_MONTH, i);
                        if (TimeMood.returnTimeInMidnight(calendar.getTimeInMillis()) < TimeMood.returnTimeInMidnight(startDate))
                            monthInfo = monthInfo.concat("empty").concat("split");
                        else {
                            String substring = monthInfo.substring(monthInfo.length() - 7, monthInfo.length() - 5);
                            if (substring.equals("rt") || substring.equals("le")) {
                                if (returnStateOfDay(calendar.getTimeInMillis()))
                                    monthInfo = monthInfo.concat("middle").concat("split");
                                else
                                    monthInfo = monthInfo.concat("end").concat("split");

                            } else {
                                if (returnStateOfDay(calendar.getTimeInMillis()))
                                    monthInfo = monthInfo.concat("start").concat("split");
                                else
                                    monthInfo = monthInfo.concat("beg_last").concat("split");
                            }
                        }
                    }
                }
            } else {
                for (int i = 1; i <= 31; i++)
                    monthInfo = monthInfo.concat("empty").concat("split");
            }
            return monthInfo;
        } else
            return "";
    }

    private boolean returnStateOfDay(long milli) {
        return historyMood.containsKey(TimeMood.returnTimeInMidnight(milli));
    }

    private void addToList() {
        if (getActivity() != null) {
            SharedPreferences sharedPreferences = getActivity().getSharedPreferences("mood", Context.MODE_PRIVATE);
            String shared = sharedPreferences.getString("mood_stats", "");
            historyMood = new HashMap<>();
            if (shared != null && !shared.equals("")) {
                String[] split = shared.split("max_divide");
                for (String s : split) {
                    String[] small_split = s.split("small_split");
                    historyMood.put(Long.parseLong(small_split[1]), Integer.parseInt(small_split[0]));
                }
            }
        }
    }

    private String returnTheLastDayOfLastMonth() {
        Calendar calendar = Calendar.getInstance();
        monthYear = getView().findViewById(R.id.tv_month_year);
        String[] splitter = monthYear.getText().toString().split(" ");
        int month = returnMonthStringToInt(splitter[0]);
        int year = Integer.parseInt(splitter[1]);
        if (month == 0) {
            month = 11;
            year--;
        } else
            month--;
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        int day = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        calendar.set(Calendar.DAY_OF_MONTH, day);
        if (TimeMood.returnTimeInMidnight(startDate) <= TimeMood.returnTimeInMidnight(calendar.getTimeInMillis())) {
            if (returnStateOfDay(calendar.getTimeInMillis()))
                return "continue";
            else
                return "start";
        } else
            return "start";
    }

    private void getColor() {
        if (getActivity() != null) {
            SharedPreferences sharedPreferences = getActivity().getSharedPreferences("mood", Context.MODE_PRIVATE);
            String shared = sharedPreferences.getString("mood_color", "");
            if (shared == null || shared.equals(""))
                color = Color.parseColor("#000075");
            else
                color = Color.parseColor(shared);
        }
    }

    private void saveTheInput(int mood, long milli) {
        if (getActivity() != null) {
            milli = TimeMood.returnTimeInMidnight(milli);
            SharedPreferences sharedPreferences = getActivity().getSharedPreferences("mood", Context.MODE_PRIVATE);
            if (mood == 0)
                historyMood.remove(milli);
            else {
                if (historyMood.containsKey(milli)) {
                    historyMood.remove(milli);
                    historyMood.put(milli, mood);
                } else
                    historyMood.put(milli, mood);
            }
            String saveMe = "";
            for (Map.Entry<Long, Integer> map : historyMood.entrySet())
                saveMe = saveMe.concat(String.valueOf(map.getValue())).concat("small_split").concat(String.valueOf(map.getKey())).concat("max_divide");
            SharedPreferences.Editor myEdit = sharedPreferences.edit();
            myEdit.putString("mood_stats", saveMe);
            myEdit.apply();
        }
    }

    private void selectTheMoodInCalendar(int which) {
        if (getView() != null) {
            View veryBadMoodBackground = getView().findViewById(R.id.view_very_bad_mood_edit_background);
            View badMoodBackground = getView().findViewById(R.id.view_bad_mood_edit_background);
            View okMoodBackground = getView().findViewById(R.id.view_ok_mood_edit_background);
            View goodMoodBackground = getView().findViewById(R.id.view_good_mood_edit_background);
            View veryGoodMoodBackground = getView().findViewById(R.id.view_very_good_mood_edit_background);
            View veryBadMoodEdit = getView().findViewById(R.id.view_very_bad_mood_edit_check);
            View badMoodEdit = getView().findViewById(R.id.view_bad_mood_edit_check);
            View okMoodEdit = getView().findViewById(R.id.view_ok_mood_edit_check);
            View goodMoodEdit = getView().findViewById(R.id.view_good_mood_edit_check);
            View veryGoodMoodEdit = getView().findViewById(R.id.view_very_good_mood_edit_check);
            if (which == 0) {
                veryBadMoodBackground.setVisibility(View.INVISIBLE);
                badMoodBackground.setVisibility(View.INVISIBLE);
                okMoodBackground.setVisibility(View.INVISIBLE);
                goodMoodBackground.setVisibility(View.INVISIBLE);
                veryGoodMoodBackground.setVisibility(View.INVISIBLE);
                veryBadMoodEdit.setVisibility(View.INVISIBLE);
                badMoodEdit.setVisibility(View.INVISIBLE);
                okMoodEdit.setVisibility(View.INVISIBLE);
                goodMoodEdit.setVisibility(View.INVISIBLE);
                veryGoodMoodEdit.setVisibility(View.INVISIBLE);
            } else if (which == 1) {
                veryBadMoodBackground.setVisibility(View.VISIBLE);
                badMoodBackground.setVisibility(View.INVISIBLE);
                okMoodBackground.setVisibility(View.INVISIBLE);
                goodMoodBackground.setVisibility(View.INVISIBLE);
                veryGoodMoodBackground.setVisibility(View.INVISIBLE);
                veryBadMoodEdit.setVisibility(View.VISIBLE);
                badMoodEdit.setVisibility(View.INVISIBLE);
                okMoodEdit.setVisibility(View.INVISIBLE);
                goodMoodEdit.setVisibility(View.INVISIBLE);
                veryGoodMoodEdit.setVisibility(View.INVISIBLE);
            } else if (which == 2) {
                veryBadMoodBackground.setVisibility(View.INVISIBLE);
                badMoodBackground.setVisibility(View.VISIBLE);
                okMoodBackground.setVisibility(View.INVISIBLE);
                goodMoodBackground.setVisibility(View.INVISIBLE);
                veryGoodMoodBackground.setVisibility(View.INVISIBLE);
                veryBadMoodEdit.setVisibility(View.INVISIBLE);
                badMoodEdit.setVisibility(View.VISIBLE);
                okMoodEdit.setVisibility(View.INVISIBLE);
                goodMoodEdit.setVisibility(View.INVISIBLE);
                veryGoodMoodEdit.setVisibility(View.INVISIBLE);
            } else if (which == 3) {
                veryBadMoodBackground.setVisibility(View.INVISIBLE);
                badMoodBackground.setVisibility(View.INVISIBLE);
                okMoodBackground.setVisibility(View.VISIBLE);
                goodMoodBackground.setVisibility(View.INVISIBLE);
                veryGoodMoodBackground.setVisibility(View.INVISIBLE);
                veryBadMoodEdit.setVisibility(View.INVISIBLE);
                badMoodEdit.setVisibility(View.INVISIBLE);
                okMoodEdit.setVisibility(View.VISIBLE);
                goodMoodEdit.setVisibility(View.INVISIBLE);
                veryGoodMoodEdit.setVisibility(View.INVISIBLE);
            } else if (which == 4) {
                veryBadMoodBackground.setVisibility(View.INVISIBLE);
                badMoodBackground.setVisibility(View.INVISIBLE);
                okMoodBackground.setVisibility(View.INVISIBLE);
                goodMoodBackground.setVisibility(View.VISIBLE);
                veryGoodMoodBackground.setVisibility(View.INVISIBLE);
                veryBadMoodEdit.setVisibility(View.INVISIBLE);
                badMoodEdit.setVisibility(View.INVISIBLE);
                okMoodEdit.setVisibility(View.INVISIBLE);
                goodMoodEdit.setVisibility(View.VISIBLE);
                veryGoodMoodEdit.setVisibility(View.INVISIBLE);
            } else if (which == 5) {
                veryBadMoodBackground.setVisibility(View.INVISIBLE);
                badMoodBackground.setVisibility(View.INVISIBLE);
                okMoodBackground.setVisibility(View.INVISIBLE);
                goodMoodBackground.setVisibility(View.INVISIBLE);
                veryGoodMoodBackground.setVisibility(View.VISIBLE);
                veryBadMoodEdit.setVisibility(View.INVISIBLE);
                badMoodEdit.setVisibility(View.INVISIBLE);
                okMoodEdit.setVisibility(View.INVISIBLE);
                goodMoodEdit.setVisibility(View.INVISIBLE);
                veryGoodMoodEdit.setVisibility(View.VISIBLE);
            }
        }
    }

    private void StartDate() {
        startDate = returnStartDate();
    }

    private String returnTheColorOfMood(int which) {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("mood", Context.MODE_PRIVATE);
        String veryBad = sharedPreferences.getString("very_bad", "");
        String bad = sharedPreferences.getString("bad", "");
        String ok = sharedPreferences.getString("ok", "");
        String good = sharedPreferences.getString("good", "");
        String veryGood = sharedPreferences.getString("very_good", "");
        String notSelected = sharedPreferences.getString("not_selected", "");
        if (which == 0) {
            if (notSelected == null || notSelected.equals(""))
                return "#000000";
            else
                return notSelected;
        }
        if (which == 1) {
            if (veryBad == null || veryBad.equals(""))
                return "#f55656";
            else
                return veryBad;
        } else if (which == 2) {
            if (bad == null || bad.equals(""))
                return "#ffcb6b";
            else
                return bad;
        } else if (which == 3) {
            if (ok == null || ok.equals(""))
                return "#e06dfc";
            else
                return ok;
        } else if (which == 4) {
            if (good == null || good.equals(""))
                return "#69e9f5";
            else
                return good;
        } else if (which == 5) {
            if (veryGood == null || veryGood.equals(""))
                return "#83d964";
            else
                return veryGood;
        }
        return "#83d964";
    }

    private int returnColorOfDays(long milli) {
        return historyMood.getOrDefault( TimeMood.returnTimeInMidnight(milli), 0);
    }

    private void colorStuff() {
        if (getView() != null) {
            ConstraintLayout clMood = getView().findViewById(R.id.cl_mood);
            TextView editMood = getView().findViewById(R.id.tv_edit_mood);
            ProgressBar pb1 = getView().findViewById(R.id.pb_1);
            ProgressBar pb2 = getView().findViewById(R.id.pb_2);
            ProgressBar pb3 = getView().findViewById(R.id.pb_3);
            ProgressBar pb4 = getView().findViewById(R.id.pb_4);
            ProgressBar pb5 = getView().findViewById(R.id.pb_5);
            Drawable drawableForButtonsTwo = ContextCompat.getDrawable(getContext(), R.drawable.ripple_button_fav_any_circle).mutate();
            drawableForButtonsTwo = DrawableCompat.wrap(drawableForButtonsTwo);
            DrawableCompat.setTint(drawableForButtonsTwo, color);
            Drawable drawableForButtonsThree = ContextCompat.getDrawable(getContext(), R.drawable.ripple_button_fav_any_circle).mutate();
            drawableForButtonsThree = DrawableCompat.wrap(drawableForButtonsThree);
            DrawableCompat.setTint(drawableForButtonsThree, color);
            clMood.setBackgroundColor(getResources().getColor(R.color.Hex));
            editMood.setTextColor(color);

            String veryBadColor = returnTheColorOfMood(1);
            String badColor = returnTheColorOfMood(2);
            String okColor = returnTheColorOfMood(3);
            String goodColor = returnTheColorOfMood(4);
            String veryGoodColor = returnTheColorOfMood(5);
                Drawable background1 = pb1.getProgressDrawable().mutate();
                background1.setTint(Color.parseColor(veryBadColor));
                Drawable background2 = pb2.getProgressDrawable().mutate();
                background2.setTint(Color.parseColor(badColor));
                Drawable background3 = pb3.getProgressDrawable().mutate();
                background3.setTint(Color.parseColor(okColor));
                Drawable background4 = pb4.getProgressDrawable().mutate();
                background4.setTint(Color.parseColor(goodColor));
                Drawable background5 = pb5.getProgressDrawable().mutate();
                background5.setTint(Color.parseColor(veryGoodColor));
                Drawable unwrappedDrawable = AppCompatResources.getDrawable(getContext(), R.drawable.round_more_vert_24).mutate();
                Drawable wrappedDrawable = DrawableCompat.wrap(unwrappedDrawable);
                DrawableCompat.setTint(wrappedDrawable, color);
                Drawable backGround1 = ContextCompat.getDrawable(getContext(), R.drawable.mood_tracker_circle_under_pie).mutate();
                backGround1.setTint(Color.parseColor(veryGoodColor));
                Drawable backGround2 = ContextCompat.getDrawable(getContext(), R.drawable.mood_tracker_circle_under_pie).mutate();
                backGround2.setTint(Color.parseColor(goodColor));
                Drawable backGround3 = ContextCompat.getDrawable(getContext(), R.drawable.mood_tracker_circle_under_pie).mutate();
                backGround3.setTint(Color.parseColor(okColor));
                Drawable backGround4 = ContextCompat.getDrawable(getContext(), R.drawable.mood_tracker_circle_under_pie).mutate();
                backGround4.setTint(Color.parseColor(badColor));
                Drawable backGround5 = ContextCompat.getDrawable(getContext(), R.drawable.mood_tracker_circle_under_pie).mutate();
                backGround5.setTint(Color.parseColor(veryBadColor));
        }
    }


    private void makeTheButtonsInTheTopMood(int result) {
        if (getView() != null) {
            View viewBackground1 = getView().findViewById(R.id.view_background1);
            View viewBackground2 = getView().findViewById(R.id.view_background2);
            View viewBackground3 = getView().findViewById(R.id.view_background3);
            View viewBackground4 = getView().findViewById(R.id.view_background4);
            View viewBackground5 = getView().findViewById(R.id.very_good_mood_check);
            View veryBadMoodCheck = getView().findViewById(R.id.very_bad_mood_check);
            View badMoodCheck = getView().findViewById(R.id.bad_mood_check);
            View okMoodCheck = getView().findViewById(R.id.ok_mood_check);
            View goodMoodCheck = getView().findViewById(R.id.good_mood_check);
            View veryGoodMoodCheck = getView().findViewById(R.id.mood_check);
            if (result == 0) {
                viewBackground1.setVisibility(View.INVISIBLE);
                viewBackground2.setVisibility(View.INVISIBLE);
                viewBackground3.setVisibility(View.INVISIBLE);
                viewBackground4.setVisibility(View.INVISIBLE);
                viewBackground5.setVisibility(View.INVISIBLE);
                veryBadMoodCheck.setVisibility(View.INVISIBLE);
                badMoodCheck.setVisibility(View.INVISIBLE);
                okMoodCheck.setVisibility(View.INVISIBLE);
                goodMoodCheck.setVisibility(View.INVISIBLE);
                veryGoodMoodCheck.setVisibility(View.INVISIBLE);
            } else if (result == 1) {
                viewBackground1.setVisibility(View.VISIBLE);
                viewBackground2.setVisibility(View.INVISIBLE);
                viewBackground3.setVisibility(View.INVISIBLE);
                viewBackground4.setVisibility(View.INVISIBLE);
                viewBackground5.setVisibility(View.INVISIBLE);
                veryBadMoodCheck.setVisibility(View.VISIBLE);
                badMoodCheck.setVisibility(View.INVISIBLE);
                okMoodCheck.setVisibility(View.INVISIBLE);
                goodMoodCheck.setVisibility(View.INVISIBLE);
                veryGoodMoodCheck.setVisibility(View.INVISIBLE);
            } else if (result == 2) {
                viewBackground1.setVisibility(View.INVISIBLE);
                viewBackground2.setVisibility(View.VISIBLE);
                viewBackground3.setVisibility(View.INVISIBLE);
                viewBackground4.setVisibility(View.INVISIBLE);
                viewBackground5.setVisibility(View.INVISIBLE);
                veryBadMoodCheck.setVisibility(View.INVISIBLE);
                badMoodCheck.setVisibility(View.VISIBLE);
                okMoodCheck.setVisibility(View.INVISIBLE);
                goodMoodCheck.setVisibility(View.INVISIBLE);
                veryGoodMoodCheck.setVisibility(View.INVISIBLE);
            } else if (result == 3) {
                viewBackground1.setVisibility(View.INVISIBLE);
                viewBackground2.setVisibility(View.INVISIBLE);
                viewBackground3.setVisibility(View.VISIBLE);
                viewBackground4.setVisibility(View.INVISIBLE);
                viewBackground5.setVisibility(View.INVISIBLE);
                veryBadMoodCheck.setVisibility(View.INVISIBLE);
                badMoodCheck.setVisibility(View.INVISIBLE);
                okMoodCheck.setVisibility(View.VISIBLE);
                goodMoodCheck.setVisibility(View.INVISIBLE);
                veryGoodMoodCheck.setVisibility(View.INVISIBLE);
            } else if (result == 4) {
                viewBackground1.setVisibility(View.INVISIBLE);
                viewBackground2.setVisibility(View.INVISIBLE);
                viewBackground3.setVisibility(View.INVISIBLE);
                viewBackground4.setVisibility(View.VISIBLE);
                viewBackground5.setVisibility(View.INVISIBLE);
                veryBadMoodCheck.setVisibility(View.INVISIBLE);
                badMoodCheck.setVisibility(View.INVISIBLE);
                okMoodCheck.setVisibility(View.INVISIBLE);
                goodMoodCheck.setVisibility(View.VISIBLE);
                veryGoodMoodCheck.setVisibility(View.INVISIBLE);
            } else if (result == 5) {
                viewBackground1.setVisibility(View.INVISIBLE);
                viewBackground2.setVisibility(View.INVISIBLE);
                viewBackground3.setVisibility(View.INVISIBLE);
                viewBackground4.setVisibility(View.INVISIBLE);
                viewBackground5.setVisibility(View.VISIBLE);
                veryBadMoodCheck.setVisibility(View.INVISIBLE);
                badMoodCheck.setVisibility(View.INVISIBLE);
                okMoodCheck.setVisibility(View.INVISIBLE);
                goodMoodCheck.setVisibility(View.INVISIBLE);
                veryGoodMoodCheck.setVisibility(View.VISIBLE);
            }
        }
    }

    private void buttonListenAtTheTop() {
        if (getView() != null) {
            Button veryBadMood = getView().findViewById(R.id.b_very_bad_mood);
            Button badMood = getView().findViewById(R.id.b_bad_mood);
            Button okMood = getView().findViewById(R.id.b_ok_mood);
            Button goodMood = getView().findViewById(R.id.b_good_mood);
            Button veryGoodMood = getView().findViewById(R.id.b_very_good_mood);
            final View viewBackground1 = getView().findViewById(R.id.view_background1);
            final View viewBackground2 = getView().findViewById(R.id.view_background2);
            final View viewBackground3 = getView().findViewById(R.id.view_background3);
            final View viewBackground4 = getView().findViewById(R.id.view_background4);
            final View viewVeryGoodMoodCheck = getView().findViewById(R.id.very_good_mood_check);
            veryBadMood.setOnClickListener(view -> {
                if (viewBackground1.getVisibility() == View.VISIBLE) {
                    makeTheButtonsInTheTopMood(0);
                    saveTheInput(0, System.currentTimeMillis());
                } else {
                    makeTheButtonsInTheTopMood(1);
                    saveTheInput(1, System.currentTimeMillis());
                }
                calendarColor();
                divideItIntoWeeks();
                makeEverythingAverageMood();
                lineChart();
                drawTheRightBarChartMood();
                lineChartForStreak.fitScreen();
            });
            badMood.setOnClickListener(view -> {
                if (viewBackground2.getVisibility() == View.VISIBLE) {
                    makeTheButtonsInTheTopMood(0);
                    saveTheInput(0, System.currentTimeMillis());
                } else {
                    makeTheButtonsInTheTopMood(2);
                    saveTheInput(2, System.currentTimeMillis());
                }
                calendarColor();
                divideItIntoWeeks();
                makeEverythingAverageMood();
                lineChart();
                drawTheRightBarChartMood();
                lineChartForStreak.fitScreen();
            });
            okMood.setOnClickListener(view -> {
                if (viewBackground3.getVisibility() == View.VISIBLE) {
                    makeTheButtonsInTheTopMood(0);
                    saveTheInput(0, System.currentTimeMillis());
                } else {
                    makeTheButtonsInTheTopMood(3);
                    saveTheInput(3, System.currentTimeMillis());
                }
                calendarColor();
                divideItIntoWeeks();
                makeEverythingAverageMood();
                lineChart();
                drawTheRightBarChartMood();
                lineChartForStreak.fitScreen();
            });
            goodMood.setOnClickListener(view -> {
                if (viewBackground4.getVisibility() == View.VISIBLE) {
                    makeTheButtonsInTheTopMood(0);
                    saveTheInput(0, System.currentTimeMillis());
                } else {
                    makeTheButtonsInTheTopMood(4);
                    saveTheInput(4, System.currentTimeMillis());
                }
                calendarColor();
                divideItIntoWeeks();
                makeEverythingAverageMood();
                lineChart();
                drawTheRightBarChartMood();
                lineChartForStreak.fitScreen();
            });
            veryGoodMood.setOnClickListener(view -> {
                if (viewVeryGoodMoodCheck.getVisibility() == View.VISIBLE) {
                    makeTheButtonsInTheTopMood(0);
                    saveTheInput(0, System.currentTimeMillis());
                } else {
                    makeTheButtonsInTheTopMood(5);
                    saveTheInput(5, System.currentTimeMillis());
                }
                calendarColor();
                divideItIntoWeeks();
                makeEverythingAverageMood();
                lineChart();
                drawTheRightBarChartMood();
                lineChartForStreak.fitScreen();
            });
        }
    }

    private void setUpButtonsOnce() {
        makeTheButtonsInTheTopMood(returnColorOfDays(System.currentTimeMillis()));
    }

    private void makeEverythingAverageMood() {
        if (getView() != null) {
            TextView averageMark = getView().findViewById(R.id.tv_average_mark);
            View locationAverageChart = getView().findViewById(R.id.location_average_chart);
            if (historyMood.isEmpty()) {
                makeEverythingGoForAverageMood(0);
                return;
            } else
                makeEverythingGoForAverageMood(1);

            String veryBadColor = returnTheColorOfMood(1);
            String badColor = returnTheColorOfMood(2);
            String okColor = returnTheColorOfMood(3);
            String goodColor = returnTheColorOfMood(4);
            String veryGoodColor = returnTheColorOfMood(5);
            float average = 0;
            for (Map.Entry<Long, Integer> map : historyMood.entrySet())
                average = average + map.getValue();
            average = average / historyMood.size();
            Drawable backgroundLocation = locationAverageChart.getBackground();
            float position_in_map = (average - 1) / 4;
            if (average <= 1.5) {
                backgroundLocation.setTint(Color.parseColor(veryBadColor));
                averageMark.setTextColor(Color.parseColor(veryBadColor));
            } else if (average <= 2.5) {
                backgroundLocation.setTint(Color.parseColor(badColor));
                averageMark.setTextColor(Color.parseColor(badColor));
            } else if (average <= 3.5) {
                backgroundLocation.setTint(Color.parseColor(okColor));
                averageMark.setTextColor(Color.parseColor(okColor));
            } else if (average <= 4.5) {
                backgroundLocation.setTint(Color.parseColor(goodColor));
                averageMark.setTextColor(Color.parseColor(goodColor));
            } else {
                backgroundLocation.setTint(Color.parseColor(veryGoodColor));
                averageMark.setTextColor(Color.parseColor(veryGoodColor));
            }
            ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams) locationAverageChart.getLayoutParams();
            params.horizontalBias = position_in_map;
            locationAverageChart.setLayoutParams(params);
            if ((int) average == average)
                averageMark.setText(String.valueOf((int) average));
            else
                averageMark.setText(String.format("%.1f", average));
        }
    }

    private void makeEverythingGoForAverageMood(int which) {
        TextView averageMark = getView().findViewById(R.id.tv_average_mark);
        View viewMood1 = getView().findViewById(R.id.view_mood1);
        View viewMood2 = getView().findViewById(R.id.view_mood2);
        View viewMood3 = getView().findViewById(R.id.view_mood3);
        View viewMood4 = getView().findViewById(R.id.view_mood4);
        View viewMood5 = getView().findViewById(R.id.view_mood5);
        View locationAverageChart = getView().findViewById(R.id.location_average_chart);
        ProgressBar pb1 = getView().findViewById(R.id.pb_1);
        ProgressBar pb2 = getView().findViewById(R.id.pb_2);
        ProgressBar pb3 = getView().findViewById(R.id.pb_3);
        ProgressBar pb4 = getView().findViewById(R.id.pb_4);
        ProgressBar pb5 = getView().findViewById(R.id.pb_5);
        TextView notEnoughDataAverage = getView().findViewById(R.id.tv_not_enough_data_average);
        if (which == 0) {
            notEnoughDataAverage.setVisibility(View.VISIBLE);
            averageMark.setVisibility(View.INVISIBLE);
            viewMood1.setVisibility(View.INVISIBLE);
            viewMood2.setVisibility(View.INVISIBLE);
            viewMood3.setVisibility(View.INVISIBLE);
            viewMood4.setVisibility(View.INVISIBLE);
            viewMood5.setVisibility(View.INVISIBLE);
            locationAverageChart.setVisibility(View.INVISIBLE);
            pb1.setVisibility(View.INVISIBLE);
            pb2.setVisibility(View.INVISIBLE);
            pb3.setVisibility(View.INVISIBLE);
            pb4.setVisibility(View.INVISIBLE);
            pb5.setVisibility(View.INVISIBLE);
        } else if (which == 1) {
            notEnoughDataAverage.setVisibility(View.INVISIBLE);
            averageMark.setVisibility(View.VISIBLE);
            viewMood1.setVisibility(View.VISIBLE);
            viewMood2.setVisibility(View.VISIBLE);
            viewMood3.setVisibility(View.VISIBLE);
            viewMood4.setVisibility(View.VISIBLE);
            viewMood5.setVisibility(View.VISIBLE);
            locationAverageChart.setVisibility(View.VISIBLE);
            pb1.setVisibility(View.VISIBLE);
            pb2.setVisibility(View.VISIBLE);
            pb3.setVisibility(View.VISIBLE);
            pb4.setVisibility(View.VISIBLE);
            pb5.setVisibility(View.VISIBLE);
        }
    }

    private void lineChart() {
        if (getView() != null) {
            TextView monthYearChart = getView().findViewById(R.id.tv_month_year_chart);
            Button lineChartForward = getView().findViewById(R.id.bt_line_chart_forward);
            View lineChartBack = getView().findViewById(R.id.view_line_chart_back);

            Calendar calendar = Calendar.getInstance();
            int monthNow = calendar.get(Calendar.MONTH);
            int yearNow = calendar.get(Calendar.YEAR);
            if (monthForMoodChart == -1) {
                monthForMoodChart = monthNow;
                yearForMoodChart = yearNow;
                lineChartForward.setVisibility(View.INVISIBLE);
                lineChartBack.setVisibility(View.INVISIBLE);
            } else if (monthForMoodChart == monthNow && yearForMoodChart == yearNow) {
                lineChartForward.setVisibility(View.INVISIBLE);
                lineChartBack.setVisibility(View.INVISIBLE);
            } else {
                lineChartForward.setVisibility(View.VISIBLE);
                lineChartBack.setVisibility(View.VISIBLE);
            }
            monthYearChart.setText(returnMonth(monthForMoodChart).concat(" ").concat(String.valueOf(yearForMoodChart)));
            int color_card = color;
            lineChartForStreak = getView().findViewById(R.id.line_chart);
            TextView notEnoughData = getView().findViewById(R.id.tv_not_enough_data_line_chart);
            lineChartForStreak.invalidate();
            lineChartForStreak.clear();
            ArrayList<Entry> y_values = new ArrayList<>();
            String stringToSplit = returnDataMoodForMoodLineChart();
            if (stringToSplit.equals("")) {
                notEnoughData.setVisibility(View.VISIBLE);
                lineChartForStreak.setVisibility(View.INVISIBLE);
                return;
            }
            notEnoughData.setVisibility(View.INVISIBLE);
            lineChartForStreak.setVisibility(View.VISIBLE);
            String[] split_data = stringToSplit.split("big_split");
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
            lineChartForStreak.fitScreen();
            lineChartForStreak.setPadding(0, 0, 0, 0);
            lineChartForStreak.getXAxis().setDrawGridLines(false);
            lineChartForStreak.getAxisLeft().setDrawGridLines(true);
            lineChartForStreak.getAxisRight().setDrawGridLines(false);

            XAxis xAxis = lineChartForStreak.getXAxis();
            xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
            xAxis.setLabelCount(y_values.size(), false);
            xAxis.setGranularity(1.0f);
            xAxis.setGranularityEnabled(true);
            xAxis.setAvoidFirstLastClipping(true);
            xAxis.setAxisMaximum(last_value + 0.1f);
            lineChartForStreak.getDescription().setText("");
            lineChartForStreak.setScaleEnabled(false);
            lineChartForStreak.getLegend().setEnabled(false);

            lineChartForStreak.getAxisRight().setEnabled(false);
            lineChartForStreak.getAxisLeft().setAxisMinimum(1);
            lineChartForStreak.getAxisLeft().setDrawAxisLine(false);
            lineChartForStreak.getAxisLeft().setGranularity(1.0f);
            lineChartForStreak.getAxisLeft().setGranularityEnabled(true);
            lineChartForStreak.getAxisLeft().setAxisMaximum(5);

            LineDataSet lineDataSet = new LineDataSet(y_values, "data");
            lineDataSet.setColor(color_card);
            ArrayList<ILineDataSet> dataSets = new ArrayList<>();
            dataSets.add(lineDataSet);
            LineData data = new LineData(dataSets);
            lineChartForStreak.setData(data);
            lineChartForStreak.getData().setHighlightEnabled(false);
            lineDataSet.setLineWidth(3f);
            lineDataSet.setCircleRadius(10f);
            lineDataSet.setCircleHoleColor(Color.WHITE);
            lineDataSet.setCircleColor(Color.WHITE);
            lineDataSet.setDrawValues(false);
            lineChartForStreak.invalidate();
            lineChartForStreak.setVisibleXRangeMaximum(9);
            lineChartForStreak.moveViewToX(y_values.size());
        }
    }

    private void buttonTheGraphMood() {
        if (getView() != null) {
            Button lineChartBack = getView().findViewById(R.id.bt_line_chart_back);
            Button lineChartForward = getView().findViewById(R.id.bt_line_chart_forward);
            lineChartBack.setOnClickListener(view -> {
                if ((monthForMoodChart) == 0) {
                    monthForMoodChart = 11;
                    yearForMoodChart--;
                } else {
                    monthForMoodChart--;
                }
                lineChart();
            });
            lineChartForward.setOnClickListener(view -> {
                if ((monthForMoodChart) == 11) {
                    monthForMoodChart = 0;
                    yearForMoodChart++;
                } else
                    monthForMoodChart++;
                lineChart();
            });
        }
    }

    private String returnDataMoodForMoodLineChart() {
        String returnMe = "";
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, yearForMoodChart);
        calendar.set(Calendar.MONTH, monthForMoodChart);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        Calendar new_calender = Calendar.getInstance();
        for (int i = 1; i <= calendar.getActualMaximum(Calendar.DAY_OF_MONTH); i++) {
            new_calender.set(yearForMoodChart, monthForMoodChart, i);
            long timeInMillis = new_calender.getTimeInMillis();
            if (TimeMood.returnTimeInMidnight(timeInMillis) >= TimeMood.returnTimeInMidnight(startDate)) {
                if (TimeMood.returnTimeInMidnight(timeInMillis) <= TimeMood.returnTimeInMidnight(System.currentTimeMillis())) {
                    if (returnColorOfDays(timeInMillis) == 1)
                        returnMe = returnMe.concat(String.valueOf(i)).concat("small_split").concat("1").concat("big_split");
                    else if (returnColorOfDays(timeInMillis) == 2)
                        returnMe = returnMe.concat(String.valueOf(i)).concat("small_split").concat("2").concat("big_split");
                    else if (returnColorOfDays(timeInMillis) == 3)
                        returnMe = returnMe.concat(String.valueOf(i)).concat("small_split").concat("3").concat("big_split");
                    else if (returnColorOfDays(timeInMillis) == 4)
                        returnMe = returnMe.concat(String.valueOf(i)).concat("small_split").concat("4").concat("big_split");
                    else if (returnColorOfDays(timeInMillis) == 5)
                        returnMe = returnMe.concat(String.valueOf(i)).concat("small_split").concat("5").concat("big_split");
                }
            }
        }
        return returnMe;
    }

    private Drawable returnMoodLogoVeryBad() {
        String veryBadColor = returnTheColorOfMood(1);
        Drawable unwrappedDrawable = AppCompatResources.getDrawable(getContext(), R.drawable.round_sentiment_very_dissatisfied_24).mutate();
        Drawable wrappedDrawable = DrawableCompat.wrap(unwrappedDrawable);
        DrawableCompat.setTint(wrappedDrawable, Color.parseColor(veryBadColor));
        return wrappedDrawable;
    }

    private Drawable returnMoodLogoBad() {
        String badColor = returnTheColorOfMood(2);
        Drawable unwrappedDrawable = AppCompatResources.getDrawable(getContext(), R.drawable.round_mood_bad_24).mutate();
        Drawable wrappedDrawable = DrawableCompat.wrap(unwrappedDrawable);
        DrawableCompat.setTint(wrappedDrawable, Color.parseColor(badColor));
        return wrappedDrawable;
    }

    private Drawable returnMoodLogoOk() {
        String okColor = returnTheColorOfMood(3);
        Drawable unwrappedDrawable = AppCompatResources.getDrawable(getContext(), R.drawable.round_face_24).mutate();
        Drawable wrappedDrawable = DrawableCompat.wrap(unwrappedDrawable);
        DrawableCompat.setTint(wrappedDrawable, Color.parseColor(okColor));
        return wrappedDrawable;
    }

    private Drawable returnMoodLogoGood() {
        String goodColor = returnTheColorOfMood(4);
        Drawable unwrappedDrawable = AppCompatResources.getDrawable(getContext(), R.drawable.round_mood_24).mutate();
        Drawable wrappedDrawable = DrawableCompat.wrap(unwrappedDrawable);
        DrawableCompat.setTint(wrappedDrawable, Color.parseColor(goodColor));
        return wrappedDrawable;
    }

    private Drawable returnMoodLogoVeryGood() {
        String veryGoodColor = returnTheColorOfMood(5);
        Drawable unwrappedDrawable = AppCompatResources.getDrawable(getContext(), R.drawable.round_sentiment_very_satisfied_24).mutate();
        Drawable wrappedDrawable = DrawableCompat.wrap(unwrappedDrawable);
        DrawableCompat.setTint(wrappedDrawable, Color.parseColor(veryGoodColor));
        return wrappedDrawable;
    }

    private void setMood() {
        View veryBadMoodInHabitsInTheTopToday = getView().findViewById(R.id.view_very_bad_mood);
        View badMoodInHabitsInTheTopToday = getView().findViewById(R.id.view_bad_mood);
        View okMoodInHabitsInTheTopToday = getView().findViewById(R.id.view_ok_mood);
        View goodMoodInHabitsInTheTopToday = getView().findViewById(R.id.view_good_mood);
        View veryGoodMoodInHabitsInTheTopToday = getView().findViewById(R.id.view_very_good_mood);

        View viewVeryBadMoodEdit = getView().findViewById(R.id.view_very_bad_mood_edit);
        View viewBadMoodEdit = getView().findViewById(R.id.view_bad_mood_edit);
        View viewOkMoodEdit = getView().findViewById(R.id.view_ok_mood_edit);
        View viewGoodMoodEdit = getView().findViewById(R.id.view_good_mood_edit);
        View viewVeryGoodMoodEdit = getView().findViewById(R.id.view_very_good_mood_edit);

        View viewMood1 = getView().findViewById(R.id.view_mood1);
        View viewMood2 = getView().findViewById(R.id.view_mood2);
        View viewMood3 = getView().findViewById(R.id.view_mood3);
        View viewMood4 = getView().findViewById(R.id.view_mood4);
        View viewMood5 = getView().findViewById(R.id.view_mood5);

        veryBadMoodInHabitsInTheTopToday.setBackground(returnMoodLogoVeryBad());
        viewBadMoodEdit.setBackground(returnMoodLogoVeryBad());
        viewMood1.setBackground(returnMoodLogoVeryBad());

        badMoodInHabitsInTheTopToday.setBackground(returnMoodLogoBad());
        viewBadMoodEdit.setBackground(returnMoodLogoBad());
        viewMood2.setBackground(returnMoodLogoBad());

        okMoodInHabitsInTheTopToday.setBackground(returnMoodLogoOk());
        viewOkMoodEdit.setBackground(returnMoodLogoOk());
        viewMood3.setBackground(returnMoodLogoOk());

        goodMoodInHabitsInTheTopToday.setBackground(returnMoodLogoGood());
        viewGoodMoodEdit.setBackground(returnMoodLogoGood());
        viewMood4.setBackground(returnMoodLogoGood());
        veryGoodMoodInHabitsInTheTopToday.setBackground(returnMoodLogoVeryGood());
        viewVeryGoodMoodEdit.setBackground(returnMoodLogoVeryGood());
        viewMood5.setBackground(returnMoodLogoVeryGood());


    }

    private String returnTheDaysOfTheGoodHabit() {
        if (getView() != null) {
            int monday = 0, tuesday = 0, wednesday = 0, thursday = 0, friday = 0, saturday = 0, sunday = 0;
            float mondayAverage = 0, tuesdayAverage = 0, wednesdayAverage = 0, thursdayAverage = 0, fridayAverage = 0, saturdayAverage = 0, sundayAverage = 0;
            for (Map.Entry<Long, Integer> map : historyMood.entrySet()) {
                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(map.getKey());
                if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY) {
                    monday = monday + 1;
                    mondayAverage = mondayAverage + map.getValue();
                } else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.TUESDAY) {
                    tuesday = tuesday + 1;
                    tuesdayAverage = tuesdayAverage + map.getValue();
                } else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.WEDNESDAY) {
                    wednesday = wednesday + 1;
                    wednesdayAverage = wednesdayAverage + map.getValue();
                } else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.THURSDAY) {
                    thursday = thursday + 1;
                    thursdayAverage = thursdayAverage + map.getValue();
                } else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY) {
                    friday = friday + 1;
                    fridayAverage = fridayAverage + map.getValue();
                } else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
                    saturday = saturday + 1;
                    saturdayAverage = saturdayAverage + map.getValue();
                } else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
                    sunday = sunday + 1;
                    sundayAverage = sundayAverage + map.getValue();
                }
            }
            if (monday != 0)
                mondayAverage = mondayAverage / monday;
            if (tuesday != 0)
                tuesdayAverage = tuesdayAverage / tuesday;
            if (wednesday != 0)
                wednesdayAverage = wednesdayAverage / wednesday;
            if (thursday != 0)
                thursdayAverage = thursdayAverage / thursday;
            if (friday != 0)
                fridayAverage = fridayAverage / friday;
            if (saturday != 0)
                saturdayAverage = saturdayAverage / saturday;
            if (sunday != 0)
                sundayAverage = sundayAverage / sunday;
            return String.valueOf(mondayAverage).concat("split").concat(String.valueOf(tuesdayAverage)).concat("split").concat(String.valueOf(wednesdayAverage)).concat("split").concat(String.valueOf(thursdayAverage)).concat("split").concat(String.valueOf(fridayAverage)).concat("split").concat(String.valueOf(saturdayAverage)).concat("split").concat(String.valueOf(sundayAverage));
        }
        return "";
    }

    private void averageMood() {
        if (getView() != null) {
            TextView not_enough_data = getView().findViewById(R.id.tv_not_enough_data);
            BarChart averageForMonth = getView().findViewById(R.id.bar_chart);
            BarChart chartTimes = getView().findViewById(R.id.bar_chart2);

            float max_days = 0;
            String daysOfWeek = returnTheDaysOfTheGoodHabit();
            String[] split_days_of_week = daysOfWeek.split("split");
            for (String s : split_days_of_week) {
                if (max_days < Float.parseFloat(s))
                    max_days = Float.parseFloat(s);
            }
            if (max_days == 0) {
                not_enough_data.setVisibility(View.VISIBLE);
                averageForMonth.setVisibility(View.INVISIBLE);
                chartTimes.setVisibility(View.INVISIBLE);
                return;
            } else
                not_enough_data.setVisibility(View.INVISIBLE);
            final float maxDaysFinal = max_days;
            chartTimes.setVisibility(View.VISIBLE);
            averageForMonth.setVisibility(View.INVISIBLE);

            CustomBarChartRenderer barChartRender = new CustomBarChartRenderer(chartTimes, chartTimes.getAnimator(), chartTimes.getViewPortHandler());
            barChartRender.setRadius(8);
            chartTimes.setRenderer(barChartRender);
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
            chartTimes.getXAxis().setValueFormatter(new IndexAxisValueFormatter(xAxisLabels));
            ValueFormatter valueFormatter = new ValueFormatter() {
                @Override
                public String getFormattedValue(float value) {
                    if (value == 0 || (value / maxDaysFinal < 0.1))
                        return "";
                    else {
                        if ((int) value == value)
                            return "" + (int) value;
                        else
                            return String.format("%.1f", value);
                    }
                }
            };
            String veryBadColor = returnTheColorOfMood(1);
            String badColor = returnTheColorOfMood(2);
            String okColor = returnTheColorOfMood(3);
            String goodColor = returnTheColorOfMood(4);
            String veryGoodColor = returnTheColorOfMood(5);
            int[] colorsForBars = new int[7];
            int counter = 0;
            for (int i = start_value; i < start_value + 7; i++) {
                float averageValue = Float.parseFloat(split_days_of_week[i % 7]);
                if (averageValue <= 1.5)
                    colorsForBars[counter] = Color.parseColor(veryBadColor);
                else if (averageValue <= 2.5)
                    colorsForBars[counter] = Color.parseColor(badColor);
                else if (averageValue <= 3.5)
                    colorsForBars[counter] = Color.parseColor(okColor);
                else if (averageValue <= 4.5)
                    colorsForBars[counter] = Color.parseColor(goodColor);
                else
                    colorsForBars[counter] = Color.parseColor(veryGoodColor);
                counter++;
            }
            BarDataSet set = new BarDataSet(entries, "BarDataSet");
            set.setColors(colorsForBars);
            set.setValueTextColor(Color.WHITE);
            set.setValueTextSize(15);
            set.setValueTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
            BarData data = new BarData(set);
            data.setValueFormatter(valueFormatter);
            data.setBarWidth(0.9f);
            chartTimes.setData(data);
            chartTimes.invalidate();
            chartTimes.setScaleEnabled(false);
            chartTimes.getLegend().setEnabled(false);
            chartTimes.setExtraOffsets(0, 0, 0, 0);
            Description description = new Description();
            description.setText("");
            chartTimes.setDescription(description);
            XAxis xAxis = chartTimes.getXAxis();
            xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
            data.setHighlightEnabled(false);
            data.setBarWidth(0.7f);
            chartTimes.getAxisLeft().setAxisMinimum(0f);
            chartTimes.getAxisRight().setAxisMinimum(0f);
            chartTimes.getXAxis().setDrawGridLines(false);
            chartTimes.getAxisLeft().setDrawAxisLine(false);
            chartTimes.getAxisRight().setDrawAxisLine(false);
            chartTimes.getAxisLeft().setDrawLabels(false);
            chartTimes.getAxisRight().setDrawLabels(false);
            chartTimes.setDrawValueAboveBar(false);
        }
    }


    private String returnFirstDayOfWeek() {
        Calendar calendar = Calendar.getInstance();
        if (calendar.getFirstDayOfWeek() == Calendar.MONDAY)
            return this.getResources().getString(R.string.Monday) ;
        else if (calendar.getFirstDayOfWeek() == Calendar.TUESDAY)
            return this.getResources().getString(R.string.Tuesday) ;
        else if (calendar.getFirstDayOfWeek() == Calendar.WEDNESDAY)
            return this.getResources().getString(R.string.Wednesday) ;
        else if (calendar.getFirstDayOfWeek() == Calendar.THURSDAY)
            return this.getResources().getString(R.string.Thursday) ;
        else if (calendar.getFirstDayOfWeek() == Calendar.FRIDAY)
            return this.getResources().getString(R.string.Friday) ;
        else if (calendar.getFirstDayOfWeek() == Calendar.SATURDAY)
            return this.getResources().getString(R.string.Saturday) ;
        else
            return this.getResources().getString(R.string.Sunday) ;
    }

    private String returnDataForBarChartYearly() {
        if (getView() != null) {
            int january = 0,february = 0,march = 0,april = 0,may = 0,june = 0, july = 0, august = 0, september = 0,october = 0, november = 0, december = 0;
            float janAvg = 0, febAverage = 0, marchAverage = 0, aprilAverage = 0, mayAverage = 0, juneAverage = 0, julyAverage = 0, augustAverage = 0, septemberAverage = 0, octoberAverage = 0, novemberAverage = 0, decemberAverage = 0;
            for (Map.Entry<Long, Integer> map : historyMood.entrySet()) {
                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(map.getKey());
                if (calendar.get(Calendar.MONTH) == Calendar.JANUARY) {
                    january = january + 1;
                    janAvg = janAvg + map.getValue();
                } else if (calendar.get(Calendar.MONTH) == Calendar.FEBRUARY) {
                    february = february + 1;
                    febAverage = febAverage + map.getValue();
                } else if (calendar.get(Calendar.MONTH) == Calendar.MARCH) {
                    march = march + 1;
                    marchAverage = marchAverage + map.getValue();
                } else if (calendar.get(Calendar.MONTH) == Calendar.APRIL) {
                    april = april + 1;
                    aprilAverage = aprilAverage + map.getValue();
                } else if (calendar.get(Calendar.MONTH) == Calendar.MAY) {
                    may = may + 1;
                    mayAverage = mayAverage + map.getValue();
                } else if (calendar.get(Calendar.MONTH) == Calendar.JUNE) {
                    june = june + 1;
                    juneAverage = juneAverage + map.getValue();
                } else if (calendar.get(Calendar.MONTH) == Calendar.JULY) {
                    july = july + 1;
                    julyAverage = julyAverage + map.getValue();
                } else if (calendar.get(Calendar.MONTH) == Calendar.AUGUST) {
                    august = august + 1;
                    augustAverage = augustAverage + map.getValue();
                } else if (calendar.get(Calendar.MONTH) == Calendar.SEPTEMBER) {
                    september = september + 1;
                    septemberAverage = septemberAverage + map.getValue();
                } else if (calendar.get(Calendar.MONTH) == Calendar.OCTOBER) {
                    october = october + 1;
                    octoberAverage = octoberAverage + map.getValue();
                } else if (calendar.get(Calendar.MONTH) == Calendar.NOVEMBER) {
                    november = november + 1;
                    novemberAverage = novemberAverage + map.getValue();
                } else if (calendar.get(Calendar.MONTH) == Calendar.DECEMBER) {
                    december = december + 1;
                    decemberAverage = decemberAverage + map.getValue();
                }
            }
            if (january != 0)
                janAvg = janAvg / january;
            if (february != 0)
                febAverage = febAverage / february;
            if (march != 0)
                marchAverage = marchAverage / march;
            if (april != 0)
                aprilAverage = aprilAverage / april;
            if (may != 0)
                mayAverage = mayAverage / may;
            if (june != 0)
                juneAverage = juneAverage / june;
            if (july != 0)
                julyAverage = julyAverage / july;
            if (august != 0)
                augustAverage = augustAverage / august;
            if (september != 0)
                septemberAverage = septemberAverage / september;
            if (october != 0)
                octoberAverage = octoberAverage / october;
            if (november != 0)
                novemberAverage = novemberAverage / november;
            if (december != 0)
                decemberAverage = decemberAverage / december;
            return String.valueOf(janAvg).concat("split").concat(String.valueOf(febAverage)).concat("split").concat(String.valueOf(marchAverage)).concat("split").concat(String.valueOf(aprilAverage)).concat("split").concat(String.valueOf(mayAverage)).concat("split").concat(String.valueOf(juneAverage)).concat("split").concat(String.valueOf(julyAverage)).concat("split").concat(String.valueOf(augustAverage)).concat("split").concat(String.valueOf(septemberAverage)).concat("split").concat(String.valueOf(octoberAverage)).concat("split").concat(String.valueOf(novemberAverage)).concat("split").concat(String.valueOf(decemberAverage));
        }
        return "";
    }

    private void drawBarForAverageMoodOverTheYear() {
        if (getView() != null) {
            float maxDays = 0;
            String daysOfWeek = returnDataForBarChartYearly();
            String[] split_days_of_week = daysOfWeek.split("split");
            for (String s : split_days_of_week) {
                if (maxDays < Float.parseFloat(s))
                    maxDays = Float.parseFloat(s);
            }
            TextView notEnoughData = getView().findViewById(R.id.tv_not_enough_data);
            BarChart chartMood = getView().findViewById(R.id.bar_chart);
            BarChart chartTimes = getView().findViewById(R.id.bar_chart2);
            if (maxDays == 0) {
                notEnoughData.setVisibility(View.VISIBLE);
                chartMood.setVisibility(View.INVISIBLE);
                chartTimes.setVisibility(View.INVISIBLE);
                return;
            } else
                notEnoughData.setVisibility(View.INVISIBLE);
            final float max_days_final = maxDays;
            chartTimes.setVisibility(View.INVISIBLE);
            chartMood.setVisibility(View.VISIBLE);
            CustomBarChartRenderer barChartRender = new CustomBarChartRenderer(chartMood, chartMood.getAnimator(), chartMood.getViewPortHandler());
            barChartRender.setRadius(8);
            chartMood.setRenderer(barChartRender);
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
            chartMood.getXAxis().setValueFormatter(new IndexAxisValueFormatter(xAxisLabels));
            ValueFormatter valueFormatter = new ValueFormatter() { //value format here, here is the overridden method
                @Override
                public String getFormattedValue(float value) {
                    if (value == 0 || (value / max_days_final < 0.1))
                        return "";
                    else {
                        if ((int) value == value)
                            return "" + (int) value;
                        else
                            return String.format("%.1f", value);
                    }
                }
            };
            String veryBadColor = returnTheColorOfMood(1);
            String badColor = returnTheColorOfMood(2);
            String okColor = returnTheColorOfMood(3);
            String goodColor = returnTheColorOfMood(4);
            String veryGoodColor = returnTheColorOfMood(5);
            int[] colorsForBars = new int[12];
            for (int i = 0; i < 12; i++) {
                float average_value = Float.parseFloat(split_days_of_week[i]);
                if (average_value <= 1.5)
                    colorsForBars[i] = Color.parseColor(veryBadColor);
                else if (average_value <= 2.5)
                    colorsForBars[i] = Color.parseColor(badColor);
                else if (average_value <= 3.5)
                    colorsForBars[i] = Color.parseColor(okColor);
                else if (average_value <= 4.5)
                    colorsForBars[i] = Color.parseColor(goodColor);
                else
                    colorsForBars[i] = Color.parseColor(veryGoodColor);
            }
            BarDataSet set = new BarDataSet(entries, "BarDataSet");
            set.setColors(colorsForBars);
            set.setValueTextColor(Color.WHITE);
            set.setValueTextSize(15);
            set.setValueTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
            BarData data = new BarData(set);
            data.setValueFormatter(valueFormatter);
            data.setBarWidth(0.9f);
            chartMood.setData(data);
            chartMood.invalidate();
            chartMood.setScaleEnabled(false);
            chartMood.getLegend().setEnabled(false);
            chartMood.setExtraOffsets(0, 0, 0, 0);
            Description description = new Description();
            description.setText("");
            chartMood.setDescription(description);
            XAxis xAxis = chartMood.getXAxis();
            xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
            data.setHighlightEnabled(false);
            data.setBarWidth(0.7f);
            chartMood.getAxisLeft().setAxisMinimum(0f);
            chartMood.getAxisRight().setAxisMinimum(0f);
            chartMood.getXAxis().setDrawGridLines(false);
            chartMood.getAxisLeft().setDrawAxisLine(false);
            chartMood.getAxisRight().setDrawAxisLine(false);
            chartMood.getAxisLeft().setDrawLabels(false);
            chartMood.getAxisRight().setDrawLabels(false);
            chartMood.setDrawValueAboveBar(false);
            chartMood.setVisibleXRangeMaximum(7);
            chartMood.moveViewToX(entries.size());
        }
    }


    private void drawTheRightBarChartMood() {
        BarChart barChart = getView().findViewById(R.id.bar_chart);
        if (barChart.getVisibility() == View.VISIBLE)
            drawBarForAverageMoodOverTheYear();
        else
            averageMood();
    }

    private long returnStartDate() {
        if (getActivity() != null) {
            SharedPreferences sharedPreferences = getActivity().getSharedPreferences("first_date_of_mood", Context.MODE_PRIVATE);
            long firstTime = sharedPreferences.getLong("first_date", -1);
            if (firstTime == -1) {
                SharedPreferences.Editor myEdit = sharedPreferences.edit();
                myEdit.putLong("first_date", TimeMood.returnTimeInMidnight(System.currentTimeMillis()));
                myEdit.apply();
                return TimeMood.returnTimeInMidnight(System.currentTimeMillis());
            } else
                return firstTime;
        } else
            return TimeMood.returnTimeInMidnight(System.currentTimeMillis());
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 22) {
            int year = data.getIntExtra("year", 2001);
            int month = data.getIntExtra("month", 1);
            int day = data.getIntExtra("day", 20);
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, month);
            calendar.set(Calendar.DAY_OF_MONTH, day);
            SharedPreferences sharedPreferences = getActivity().getSharedPreferences("first_date_of_mood", Context.MODE_PRIVATE);
            SharedPreferences.Editor myEdit = sharedPreferences.edit();
            myEdit.putLong("first_date", TimeMood.returnTimeInMidnight(calendar.getTimeInMillis()));
            myEdit.apply();
            callMeAtStart();
        }
    }
    private void fadeTheViews() {
        if (getView() != null && getContext() != null) {
            TextView moodChart = getView().findViewById(R.id.tv_mood_chart);
            TextView notEnoughData = getView().findViewById(R.id.tv_not_enough_data_line_chart);
            Button buttonBack = getView().findViewById(R.id.bt_line_chart_back);
            Button buttonForward = getView().findViewById(R.id.bt_line_chart_forward);
            View viewBack = getView().findViewById(R.id.view_line_chart_back);
            View viewForward = getView().findViewById(R.id.view_line_chart_forward);
            TextView monthYearChart = getView().findViewById(R.id.tv_month_year_chart);
            LineChart lineChart = getView().findViewById(R.id.line_chart);

            TextView average = getView().findViewById(R.id.tv_average);
            BarChart barChart2 = getView().findViewById(R.id.bar_chart2);
            BarChart barChart = getView().findViewById(R.id.bar_chart);
            TextView noEnoughDataBar = getView().findViewById(R.id.tv_not_enough_data);

            moodChart.setAlpha(1f);
            notEnoughData.setAlpha(1f);
            buttonBack.setAlpha(1f);
            buttonForward.setAlpha(1f);
            viewBack.setAlpha(1f);
            viewForward.setAlpha(1f);
            monthYearChart.setAlpha(1f);
            lineChart.setAlpha(1f);

            average.setAlpha(1f);
            barChart2.setAlpha(1f);
            barChart.setAlpha(1f);
            noEnoughDataBar.setAlpha(1f);
        }
    }
}