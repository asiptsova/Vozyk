package com.application.vozyk.ui.relax;

import static android.content.Context.INPUT_METHOD_SERVICE;
import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.application.vozyk.R;

import java.util.Locale;

public class Meditate extends Fragment {
    MediaPlayer meditateSound;
    private EditText timeMeditate;
    private TextView countDown;
    private Button set, startPause, reset;
    private CountDownTimer countDownTimer;
    private boolean timerRunning;
    private long startTimeInMillis, timeLeftInMillis, endTime;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.activity_meditate, container, false);
        timeMeditate = root.findViewById(R.id.edit_text_input);
        countDown = root.findViewById(R.id.tv_countdown);
        set = root.findViewById(R.id.button_set);
        startPause = root.findViewById(R.id.button_start_pause);
        reset = root.findViewById(R.id.button_reset);
        meditateSound = MediaPlayer.create(getContext(), R.raw.meditationmusic);
        set.setOnClickListener(v -> {
            String input = timeMeditate.getText().toString();
            if (input.length() == 0) {
                Toast.makeText(getContext(), getResources().getString(R.string.empty_field), Toast.LENGTH_SHORT).show();
                return;
            }

            if (Long.parseLong(input) < 5 || Long.parseLong(input) > 30) {
                Toast.makeText(getContext(), getResources().getString(R.string.range), Toast.LENGTH_SHORT).show();
                return;
            }
            long millis = Long.parseLong(input) * 60000;
            if (millis == 0) {
                Toast.makeText(getContext(), getResources().getString(R.string.positive_number), Toast.LENGTH_SHORT).show();
                return;
            }
            setTime(millis);
            timeMeditate.setText("");
        });
        startPause.setOnClickListener(v -> {
            if (timerRunning) {
                meditateSound.pause();
                pauseTimer();
            } else {
                meditateSound.start();
                startTimer();
            }
        });
        reset.setOnClickListener(v -> resetTimer());
        return root;
    }

    public void onPause() {
        super.onPause();
        try {
            meditateSound.pause();
            pauseTimer();
        } catch (Exception ignored) {
        }
    }

    private void setTime(long milliseconds) {
        startTimeInMillis = milliseconds;
        resetTimer();
        closeKeyboard();
    }

    private void startTimer() {
        endTime = System.currentTimeMillis() + timeLeftInMillis;
        countDownTimer = new CountDownTimer(timeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMillis = millisUntilFinished;
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                timerRunning = false;
                updateWatchInterface();
            }
        }.start();
        timerRunning = true;
        updateWatchInterface();
    }

    private void pauseTimer() {
        try {
            countDownTimer.cancel();
            timerRunning = false;
            updateWatchInterface();
        } catch (Exception ignored) {
        }
    }

    private void resetTimer() {
        timeLeftInMillis = startTimeInMillis;
        updateCountDownText();
        updateWatchInterface();
    }

    private void updateCountDownText() {
        int hours = (int) (timeLeftInMillis / 1000) / 3600;
        int minutes = (int) ((timeLeftInMillis / 1000) % 3600) / 60;
        int seconds = (int) (timeLeftInMillis / 1000) % 60;
        String timeLeftFormatted;
        if (hours > 0)
            timeLeftFormatted = String.format(Locale.getDefault(),
                    "%d:%02d:%02d", hours, minutes, seconds);
        else
            timeLeftFormatted = String.format(Locale.getDefault(),
                    "%02d:%02d", minutes, seconds);
        countDown.setText(timeLeftFormatted);
    }

    private void updateWatchInterface() {
        if (timerRunning) {
            timeMeditate.setVisibility(View.INVISIBLE);
            set.setVisibility(View.INVISIBLE);
            reset.setVisibility(View.INVISIBLE);
            startPause.setText(getResources().getString(R.string.pause));
        } else {
            timeMeditate.setVisibility(View.VISIBLE);
            set.setVisibility(View.VISIBLE);
            startPause.setText(getResources().getString(R.string.start));
            if (timeLeftInMillis < 1000)
                startPause.setVisibility(View.INVISIBLE);
            else
                startPause.setVisibility(View.VISIBLE);
            if (timeLeftInMillis < startTimeInMillis)
                reset.setVisibility(View.VISIBLE);
            else
                reset.setVisibility(View.INVISIBLE);
        }
    }

    public void closeKeyboard() {
        View view = getActivity().getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        SharedPreferences prefs = getActivity().getSharedPreferences("prefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putLong("startTimeInMillis", startTimeInMillis);
        editor.putLong("millisLeft", timeLeftInMillis);
        editor.putBoolean("timerRunning", timerRunning);
        editor.putLong("endTime", endTime);
        editor.apply();
        if (countDownTimer != null)
            countDownTimer.cancel();
    }

    @Override
    public void onStart() {
        super.onStart();
        SharedPreferences prefs = getActivity().getSharedPreferences("prefs", MODE_PRIVATE);
        startTimeInMillis = prefs.getLong("startTimeInMillis", 600000);
        timeLeftInMillis = prefs.getLong("millisLeft", startTimeInMillis);
        timerRunning = prefs.getBoolean("timerRunning", false);
        updateCountDownText();
        updateWatchInterface();
        if (timerRunning) {
            endTime = prefs.getLong("endTime", 0);
            timeLeftInMillis = endTime - System.currentTimeMillis();
            if (timeLeftInMillis < 0) {
                timeLeftInMillis = 0;
                timerRunning = false;
                updateCountDownText();
                updateWatchInterface();
            } else
                startTimer();
        }
    }
}