package com.application.vozyk.ui.meds;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.application.vozyk.MainActivity;
import com.application.vozyk.R;
import com.application.vozyk.ui.mood.Mood;
import com.google.android.material.button.MaterialButtonToggleGroup;
import com.google.android.material.textfield.TextInputLayout;
import com.google.android.material.timepicker.MaterialTimePicker;
import com.google.android.material.timepicker.TimeFormat;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;


public class MedsAdd extends AppCompatActivity {

    private TextInputLayout name, dose;
    private RadioGroup radioGroup;
    private MaterialButtonToggleGroup materialButtonToggleGroup, materialButtonToggleGroup1;
    private boolean beforeFood;
    private Button customTime;
    private String timeValue = "0000";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_meds);
        getSupportActionBar().hide();

        name = findViewById(R.id.name);
        dose = findViewById(R.id.note);
        Button submitBtn = findViewById(R.id.submit_btn);
        materialButtonToggleGroup = findViewById(R.id.toggleButton);
        materialButtonToggleGroup1 = findViewById(R.id.toggleButton1);
        radioGroup = findViewById(R.id.radioGroup);
        Button show = findViewById(R.id.cancel_btn);
        customTime = findViewById(R.id.custom_time);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference();

        Intent intent = getIntent();
        String PersonID = intent.getStringExtra("Id");

        customTime.setOnClickListener(view -> {
            MaterialTimePicker picker;
            if (customTime.getText().toString().contains("Custom")) {
                picker =
                        new MaterialTimePicker.Builder()
                                .setTimeFormat(TimeFormat.CLOCK_24H)
                                .setHour(0)
                                .setMinute(0)
                                .build();
            } else {
                picker =
                        new MaterialTimePicker.Builder()
                                .setTimeFormat(TimeFormat.CLOCK_24H)
                                .setHour(Integer.parseInt(customTime.getText().toString().substring(0, 2)))
                                .setMinute(Integer.parseInt(customTime.getText().toString().substring(3, 5)))
                                .build();
            }

            picker.show(getSupportFragmentManager(), "tag");

            picker.addOnPositiveButtonClickListener(view1 -> {
                customTime.setText(TimeChange.timeTextView(picker.getHour(), picker.getMinute()));
                timeValue = TimeChange.timeToString(picker.getHour(), picker.getMinute());
            });
        });


        show.setOnClickListener(view -> startActivity(new Intent(MedsAdd.this, Mood.class)));
        submitBtn.setOnClickListener(view -> {

            beforeFood = R.id.radio_button_1 == radioGroup.getCheckedRadioButtonId();
            List<Integer> arr = materialButtonToggleGroup.getCheckedButtonIds();
            arr.addAll(materialButtonToggleGroup1.getCheckedButtonIds());

            ArrayList<Time.AlarmBundle> time = new ArrayList<>();
            for (Integer i : arr) {
                if (i == R.id.morning)
                    time.add(new Time.AlarmBundle(Time.MORNING));
                else if (i == R.id.lunch)
                    time.add(new Time.AlarmBundle(Time.AFTERNOON));
                else if (i == R.id.night)
                    time.add(new Time.AlarmBundle(Time.NIGHT));
                else if (i == R.id.custom_time)
                    time.add(new Time.AlarmBundle(timeValue));
            }

            if (InputValidationHandler.inputValidation(name.getEditText().getText().toString(), time)) {

                MedsRecordHandler mrh = new MedsRecordHandler(
                        name.getEditText().getText().toString(),
                        dose.getEditText().getText().toString(),
                        beforeFood,
                        time
                );
                myRef.child("MedicineRecord").child(PersonID).child(mrh.getName()).setValue(mrh);
                Toast.makeText(getApplicationContext(), "Added Successfully", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MedsAdd.this, MainActivity.class));
            } else
                InputValidationHandler.showDialog(MedsAdd.this);
        });
    }
}