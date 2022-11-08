package com.application.vozyk.ui.other;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.application.vozyk.R;
import com.application.vozyk.Registration;
import com.application.vozyk.ui.habits.Habit;
import com.application.vozyk.ui.habits.Habits;

public class Other extends Fragment {
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_other, container, false);
        Button habit_link=root.findViewById(R.id.habit_link);
        habit_link.setOnClickListener(v -> startActivity(new Intent(getContext(), Habits.class)));

        return root;
    }
}