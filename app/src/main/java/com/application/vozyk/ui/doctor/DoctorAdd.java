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

public class DoctorAdd extends AppCompatActivity {
    private static final int[] arr = new int[3];
    public static TextView date;
    private String name, reason;
    private TextInputLayout docName, docReason;
    private DatabaseReference database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_add);
        getSupportActionBar().hide();
        date = findViewById(R.id.tv_labDate);
        docName = findViewById(R.id.doctor_name);
        docReason = findViewById(R.id.reason);
        Button add = findViewById(R.id.add);
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();

        database = FirebaseDatabase.getInstance().getReference().child("AppointmentRecord").child(Objects.requireNonNull(user.getUid()));


        add.setOnClickListener(view -> {
            name = docName.getEditText().getText().toString();
            reason = docReason.getEditText().getText().toString();

            if (inputValidation(name, reason)) {
                DoctorDataModel obj = new DoctorDataModel(name, reason, arr[0], arr[1], arr[2]);
                database.child(obj.getName()).setValue(obj);
                Toast.makeText(getApplicationContext(), getResources().getString(R.string.added), Toast.LENGTH_SHORT).show();
                startActivity(new Intent(DoctorAdd.this, Doctor.class));
            } else
                InputValidationHandler.showDialog(DoctorAdd.this);
        });
    }

    public boolean inputValidation(String name, String reason) {
        if (name.contains(".") || name.contains("[") || name.contains("]") || name.contains("$") || name.contains("#"))
            return false;
        if (reason.contains(".") || reason.contains("[") || reason.contains("]") || reason.contains("$") || reason.contains("#"))
            return false;
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
            date.setText(result);
            arr[0] = day;
            arr[1] = month + 1;
            arr[2] = year;
        }
    }
}
