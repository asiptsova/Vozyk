package com.application.vozyk.ui.lab;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
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

public class labAddActivity extends AppCompatActivity {


    private static TextView lab_date_view;
    private static final int[] arr = new int[3];
    private DatabaseReference mDatabase;
    private TextInputLayout lab_doctor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_add);
        lab_date_view = findViewById(R.id.lab_date_view);
        lab_doctor = findViewById(R.id.lab_doctor);
        Button lab_add_btn = findViewById(R.id.lab_add_btn);


        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();

        mDatabase = FirebaseDatabase.getInstance().getReference().child("LabTestRecord").child(Objects.requireNonNull(user.getUid()));
        AutoCompleteTextView textView = findViewById(R.id.lab_name_edit);
        lab_add_btn.setOnClickListener(view -> {
            String testName = textView.getText().toString();
            String docName = lab_doctor.getEditText().getText().toString();

            if (inputValidation(testName, docName)) {

                LabTestDataModel obj = new LabTestDataModel(arr[0], arr[1], arr[2], testName, docName);
                mDatabase.child(obj.getTestName()).setValue(obj);
                Toast.makeText(getApplicationContext(), getResources().getString(R.string.added), Toast.LENGTH_SHORT).show();
                startActivity(new Intent(labAddActivity.this, labActivity.class));
            } else {
                InputValidationHandler.showDialog(labAddActivity.this);
            }

        });
    }

    public boolean inputValidation(String testName, String docName) {
        if (testName.contains(".") || testName.contains("[") || testName.contains("]") || testName.contains("$") || testName.contains("#")) {
            return false;
        }
        if (docName.contains(".") || docName.contains("[") || docName.contains("]") || docName.contains("$") || docName.contains("#")) {
            return false;
        }

        return !testName.isEmpty() && !docName.isEmpty() && arr[0] != 0 && testName.length() < 25 && docName.length() < 40;
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
            lab_date_view.setText(result);
            arr[0] = day;
            arr[1] = month + 1;
            arr[2] = year;
        }

    }

}