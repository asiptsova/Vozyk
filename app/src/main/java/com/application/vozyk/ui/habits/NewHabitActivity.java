package com.application.vozyk.ui.habits;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.application.vozyk.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class NewHabitActivity extends AppCompatActivity {

    private EditText habitName;
    private EditText habitEndDate;
    private Spinner trackingFrequency;
    private FirebaseAuth mAuth;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_habit);
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        habitName = findViewById(R.id.habit_name);
        habitEndDate = findViewById(R.id.habit_end_date);
        trackingFrequency = findViewById(R.id.habit_frequency);
        Button newHabit = findViewById(R.id.add_new_habit);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.freq,
                android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        trackingFrequency.setAdapter(adapter);

        habitEndDate.setOnClickListener(v -> {
            DatePickerFragment newFragment = new DatePickerFragment(habitEndDate);
            newFragment.show(getSupportFragmentManager(), "datePicker");
        });

        newHabit.setOnClickListener(v -> {
            addHabit();
            Intent i = new Intent(getApplicationContext(), Habits.class);
            startActivity(i);
        });
    }

    private void addHabit() {
        Map<String,String> habit = new HashMap<>();
        habit.put("Habit Name", habitName.getText().toString());
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        String start = sdf.format(Calendar.getInstance().getTime());
        habit.put("Start Date", start);
        habit.put("End Date", habitEndDate.getText().toString());
        habit.put("Frequency", trackingFrequency.getSelectedItem().toString());
        habit.put("Progress", "0");

        db.collection("users").document(mAuth.getCurrentUser().getUid()).collection("habits").document().set(habit);
    }
}
