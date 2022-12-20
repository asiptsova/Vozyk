package com.application.vozyk.ui.meds;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioGroup;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.application.vozyk.R;
import com.google.android.material.button.MaterialButtonToggleGroup;
import com.google.android.material.textfield.TextInputLayout;
import com.google.android.material.timepicker.MaterialTimePicker;
import com.google.android.material.timepicker.TimeFormat;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class UpdateMeds extends AppCompatActivity {
    MedsRecordHandler oldRecord;
    private TextInputLayout name;
    private TextInputLayout note;
    private RadioGroup radioGroup;
    private MaterialButtonToggleGroup materialButtonToggleGroup;
    private MaterialButtonToggleGroup materialButtonToggleGroup1;
    private Button up_custom_time;
    private String custom_time_value = "0000";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        getSupportActionBar().hide();
        Intent intent = getIntent();
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseDatabase database = FirebaseDatabase.getInstance();


        String KEY = intent.getStringExtra("key");
        FirebaseUser user = mAuth.getCurrentUser();
        DatabaseReference myRef = database.getReference().child("MedicineRecord").child(Objects.requireNonNull(user.getUid()));

        myRef.child(KEY).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                MedsRecordHandler mrd = snapshot.getValue(MedsRecordHandler.class);
                assert mrd != null;
                prefillData(mrd);
                oldRecord = mrd;
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        name = findViewById(R.id.meds_edit_name);
        note = findViewById(R.id.meds_edit_dose);
        radioGroup = findViewById(R.id.up_radioGroup);
        materialButtonToggleGroup = findViewById(R.id.up_toggleButton);
        materialButtonToggleGroup1 = findViewById(R.id.up_toggleButton1);
        Button updateBtn = findViewById(R.id.up_update_btn);
        Button cancelBtn = findViewById(R.id.up_cancel);
        up_custom_time = findViewById(R.id.up_custom_time);


        updateBtn.setOnClickListener(view -> {

            if (InputValidationHandler.inputValidation(getData().getName(), getData().getReminder())) {
                myRef.child(KEY).setValue(getData());
                startActivity(
                        new Intent(UpdateMeds.this, MedsPills.class)
                                .putExtra("UserName", user.getDisplayName()).putExtra("Id", user.getUid())
                );
            } else {
                InputValidationHandler.showDialog(UpdateMeds.this);
            }


        });

        up_custom_time.setOnClickListener(view -> {
            MaterialTimePicker picker;
            if (up_custom_time.getText().toString().contains("Custom")) {

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
                                .setHour(Integer.parseInt(up_custom_time.getText().toString().substring(0, 2)))
                                .setMinute(Integer.parseInt(up_custom_time.getText().toString().substring(3, 5)))
                                .build();
            }

            picker.show(getSupportFragmentManager(), "tag");

            picker.addOnPositiveButtonClickListener(view1 -> {
                up_custom_time.setText(TimeChange.timeTextView(picker.getHour(), picker.getMinute()));
                custom_time_value = TimeChange.timeToString(picker.getHour(), picker.getMinute());
            });
        });


        cancelBtn.setOnClickListener(view -> startActivity(
                new Intent(UpdateMeds.this, MedsPills.class)
                        .putExtra("UserName", user.getDisplayName()).putExtra("Id", user.getUid())
        ));


    }

    public void prefillData(MedsRecordHandler mrd) {
        String nameValue = mrd.getName();
        String noteValue = mrd.getDose();
        Boolean beforeFood = mrd.getBeforeFood();

        if (beforeFood) {
            radioGroup.check(R.id.up_radio_button_1);
        } else {
            radioGroup.check(R.id.up_radio_button_2);
        }

        name.getEditText().setText(nameValue);
        note.getEditText().setText(noteValue);

        for (Time.AlarmBundle i : mrd.getReminder()) {
            if (i.time.equals(Time.MORNING)) {
                materialButtonToggleGroup.check(R.id.up_morning);

            } else if (i.time.equals(Time.AFTERNOON)) {
                materialButtonToggleGroup.check(R.id.up_lunch);
            } else if (i.time.equals(Time.NIGHT)) {
                materialButtonToggleGroup1.check(R.id.up_night);
            } else {
                Button button = findViewById(R.id.up_custom_time);
                button.setText(i.time.substring(0, 2) + ":" + i.time.substring(2, 4));
                materialButtonToggleGroup1.check(R.id.up_custom_time);
            }
        }

    }

    public MedsRecordHandler getData() {

        String nameValue = name.getEditText().getText().toString();
        String noteValue = note.getEditText().getText().toString();
        boolean beforeFoodValue = radioGroup.getCheckedRadioButtonId() == R.id.up_radio_button_1;

        List<Integer> arr = materialButtonToggleGroup.getCheckedButtonIds();
        arr.addAll(materialButtonToggleGroup1.getCheckedButtonIds());

        ArrayList<Time.AlarmBundle> time = new ArrayList<>();
        for (Integer i : arr) {
            if (i == R.id.up_morning) {
                time.add(new Time.AlarmBundle(Time.MORNING));
            } else if (i == R.id.up_lunch) {
                time.add(new Time.AlarmBundle(Time.AFTERNOON));
            } else if (i == R.id.up_night) {
                time.add(new Time.AlarmBundle(Time.NIGHT));
            } else if (i == R.id.up_custom_time) {
                time.add(new Time.AlarmBundle(custom_time_value));
            }

        }

        return new MedsRecordHandler(
                nameValue,
                noteValue,
                beforeFoodValue,
                time
        );
    }

}


