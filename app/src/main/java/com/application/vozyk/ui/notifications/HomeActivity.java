package com.application.vozyk.ui.notifications;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.application.vozyk.MainActivity;
import com.application.vozyk.R;
import com.application.vozyk.ui.home.Mood;
import com.google.android.material.button.MaterialButtonToggleGroup;
import com.google.android.material.textfield.TextInputLayout;
import com.google.android.material.timepicker.MaterialTimePicker;
import com.google.android.material.timepicker.TimeFormat;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;


public class HomeActivity extends AppCompatActivity {

    private TextInputLayout name;
    private TextInputLayout note;
    private RadioGroup radioGroup;
    private MaterialButtonToggleGroup materialButtonToggleGroup;
    private MaterialButtonToggleGroup materialButtonToggleGroup1;
    private boolean before_food;
    private Button custom_time;
    private String custom_time_value = "0000";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        name = findViewById(R.id.name);
        note = findViewById(R.id.note);
        Button submitBtn = findViewById(R.id.submitbtn);
        materialButtonToggleGroup = findViewById(R.id.toggleButton);
        materialButtonToggleGroup1 = findViewById(R.id.toggleButton1);
        radioGroup = findViewById(R.id.radioGroup);
        Button show = findViewById(R.id.cancel_btn);
        custom_time = findViewById(R.id.custom_time);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference();

        Intent intent = getIntent();
        String PersonID = intent.getStringExtra("Id");

        custom_time.setOnClickListener(view -> {
            MaterialTimePicker picker;
            if (custom_time.getText().toString().contains("Custom")) {

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
                                .setHour(Integer.parseInt(custom_time.getText().toString().substring(0, 2)))
                                .setMinute(Integer.parseInt(custom_time.getText().toString().substring(3, 5)))
                                .build();
            }

            picker.show(getSupportFragmentManager(), "tag");

            picker.addOnPositiveButtonClickListener(view1 -> {
                custom_time.setText(TimeChangeActivity.timeTextView(picker.getHour(), picker.getMinute()));
                custom_time_value = TimeChangeActivity.timeToString(picker.getHour(), picker.getMinute());
            });
        });


        show.setOnClickListener(view -> startActivity(new Intent(HomeActivity.this, Mood.class)));
        submitBtn.setOnClickListener(view -> {

            before_food = R.id.radio_button_1 == radioGroup.getCheckedRadioButtonId();
            List<Integer> arr = materialButtonToggleGroup.getCheckedButtonIds();
            arr.addAll(materialButtonToggleGroup1.getCheckedButtonIds());

            ArrayList<TIME.AlarmBundle> time = new ArrayList<>();
            for (Integer i : arr) {
                if (i == R.id.morning) {
                    time.add(new TIME.AlarmBundle(TIME.MORNING, AlarmManagerHandler.setUniqueNotificationId()));
                } else if (i == R.id.lunch) {
                    time.add(new TIME.AlarmBundle(TIME.AFTERNOON, AlarmManagerHandler.setUniqueNotificationId()));
                } else if (i == R.id.night) {
                    time.add(new TIME.AlarmBundle(TIME.NIGHT, AlarmManagerHandler.setUniqueNotificationId()));
                } else if (i == R.id.custom_time) {
                    time.add(new TIME.AlarmBundle(custom_time_value, AlarmManagerHandler.setUniqueNotificationId()));
                }
            }

            if (InputValidationHandler.inputValidation(name.getEditText().getText().toString(), time)) {

                MedicineRecordHandler mrh = new MedicineRecordHandler(
                        name.getEditText().getText().toString(),
                        note.getEditText().getText().toString(),
                        before_food,
                        time
                );
                myRef.child("MedicineRecord").child(PersonID).child(mrh.getName() + AlarmManagerHandler.setUniqueNotificationId()).setValue(mrh);
                Toast.makeText(getApplicationContext(), "Added Successfully", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(HomeActivity.this, MainActivity.class));
            } else {
                InputValidationHandler.showDialog(HomeActivity.this);
            }
        });

    }


}