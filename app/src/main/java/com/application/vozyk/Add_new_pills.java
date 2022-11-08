package com.application.vozyk;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import com.application.vozyk.ui.notifications.NotificationsPills;

public class Add_new_pills extends AppCompatActivity {

    EditText medicationName, medicationDosage;
    TimePicker medicationTime;
    DatePicker medicationDate;
    Button submit, cancel;
    DatabaseHelper DB;
    AlertDialog.Builder builder1;
    int myIntValue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_pills);
        SharedPreferences sp = getSharedPreferences("your_prefs", Activity.MODE_PRIVATE);
        myIntValue = sp.getInt("userID", -1);


        medicationName = (EditText) findViewById(R.id.medicationName);
        medicationDosage = (EditText) findViewById(R.id.medicationDosage);
        medicationDate = (DatePicker) findViewById(R.id.datePicker);
        medicationTime = (TimePicker) findViewById(R.id.timePicker1);

        medicationTime.setIs24HourView(true);

        builder1 = new AlertDialog.Builder(this);
        submit = (Button) findViewById(R.id.submit);
        cancel = (Button) findViewById(R.id.cancel);

        DB = new DatabaseHelper(this);

        submit.setOnClickListener(view -> submitDetails());
        cancel.setOnClickListener(v -> {
            Intent intent = new Intent(this, NotificationsPills.class);
            startActivity(intent);

        });

    }

    public void submitDetails(){
        String userMedicationName = medicationName.getText().toString();
        String userMedicationDosage = medicationDosage.getText().toString();


        int userDate1 = medicationDate.getMonth()+1;
        String monthConverted = ""+userDate1;
        if(userDate1 <10){
            monthConverted = "0"+monthConverted;
        }
        String userDate = medicationDate.getDayOfMonth() + "/" + monthConverted + "/" + medicationDate.getYear();
        String userTime = medicationTime.getCurrentHour() + " : " + medicationTime.getCurrentMinute();

        if (userMedicationName.equals("") || userMedicationDosage.equals("")) {
            builder1.setMessage("Please fill in all fields!");
            builder1.setCancelable(true);
            builder1.setPositiveButton(
                    "Yes",
                    (dialog, id) -> dialog.cancel());
            AlertDialog alert11 = builder1.create();
            alert11.show();

        }else {
            DB.insertMedication(userMedicationName, userMedicationDosage, userDate, userTime, myIntValue);
            Intent intent = new Intent(this, NotificationsPills.class);
            startActivity(intent);
        }
    }
    }
