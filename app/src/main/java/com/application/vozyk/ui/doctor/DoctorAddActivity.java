package com.application.vozyk.ui.doctor;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import com.application.vozyk.R;
import com.application.vozyk.ui.meds.InputValidationHandler;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import java.util.Calendar;
import java.util.Objects;

public class DoctorAddActivity extends AppCompatActivity {
    private static final int[] arr = new int[3];
    public static TextView date_view;
    private String name;
    private String reason;
    private TextInputLayout doctor_name;
    private TextInputLayout doctor_reason;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_add);
        date_view = findViewById(R.id.lab_date_view);
        doctor_name = findViewById(R.id.doctor_name);
        doctor_reason = findViewById(R.id.doctor_reason);
        Button doctor_add_btn = findViewById(R.id.doctor_add_btn);

        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();

        mDatabase = FirebaseDatabase.getInstance().getReference().child("AppointmentRecord").child(Objects.requireNonNull(user.getUid()));


        doctor_add_btn.setOnClickListener(view -> {
            name = doctor_name.getEditText().getText().toString();
            reason = doctor_reason.getEditText().getText().toString();

            if (inputValidation(name, reason)) {
                DoctorDataModel obj = new DoctorDataModel(name, reason, arr[0], arr[1], arr[2]);
                mDatabase.child(obj.getName()).setValue(obj);
                Toast.makeText(getApplicationContext(), "Added", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(DoctorAddActivity.this, DoctorActivity.class));
            } else {
                InputValidationHandler.showDialog(DoctorAddActivity.this);
            }
        });


    }

    public boolean inputValidation(String name, String reason) {
        if (name.contains(".") || name.contains("[") || name.contains("]") || name.contains("$") || name.contains("#")) {
            return false;
        }
        if (reason.contains(".") || reason.contains("[") || reason.contains("]") || reason.contains("$") || reason.contains("#")) {
            return false;
        }

        return !name.isEmpty() && !reason.isEmpty() && arr[0] != 0 && name.length() < 15 && reason.length() < 40;
    }

    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    public static class DatePickerFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {

        @NonNull
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            c.set(Calendar.DAY_OF_MONTH, c.get(Calendar.DAY_OF_MONTH) + 1);
            int day = c.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), this, year, month, day);
            datePickerDialog.getDatePicker().setMinDate(c.getTimeInMillis());
            return datePickerDialog;
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
            String result = day + "/" + (month + 1) + "/" + year;
            date_view.setText(result);
            arr[0] = day;
            arr[1] = month + 1;
            arr[2] = year;
        }

    }
}
