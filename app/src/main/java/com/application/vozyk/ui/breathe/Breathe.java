package com.application.vozyk.ui.breathe;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.application.vozyk.R;

public class Breathe extends Fragment {
    private Spinner inhale;
    private Spinner hold;
    private Spinner exhale;
    private Spinner breaths;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.activity_breathing, container, false);
        LinearLayout linearLayout = root.findViewById(R.id.breath_layout);
        linearLayout.getBackground().setAlpha(50);
        inhale = root.findViewById(R.id.inhale);
        String[] inhaleList = new String[]{"2", "3", "4", "5", "6", "7", "8", "9", "10"};
        ArrayAdapter<String> inhaleAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, inhaleList);
        inhale.setAdapter(inhaleAdapter);

        hold = root.findViewById(R.id.hold);
        String[] holdList = new String[]{"2", "3", "4", "5", "6", "7", "8", "9", "10"};
        ArrayAdapter<String> holdAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, holdList);
        hold.setAdapter(holdAdapter);

        exhale = root.findViewById(R.id.exhale);
        String[] exhaleList = new String[]{"2", "3", "4", "5", "6", "7", "8", "9", "10"};
        ArrayAdapter<String> exhaleAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, exhaleList);
        exhale.setAdapter(exhaleAdapter);

        breaths = root.findViewById(R.id.breaths);
        String[] breathsList = new String[]{"2", "3", "4", "5", "6", "7", "8", "9", "10"};
        ArrayAdapter<String> breathsAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, breathsList);
        breaths.setAdapter(breathsAdapter);

        Button startButton = root.findViewById(R.id.startButton);
        startButton.setOnClickListener(v -> openBreathingExerciseActivity());
        return root;
    }
    public void openBreathingExerciseActivity() {
        Intent intent = new Intent(getContext(), BreathingExerciseActivity.class);
        intent.putExtra("inhale", Integer.parseInt(inhale.getSelectedItem().toString()));
        intent.putExtra("hold", Integer.parseInt(hold.getSelectedItem().toString()));
        intent.putExtra("exhale", Integer.parseInt(exhale.getSelectedItem().toString()));
        intent.putExtra("breaths", Integer.parseInt(breaths.getSelectedItem().toString()));
        startActivity(intent);
    }
}