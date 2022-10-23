package com.application.vozyk;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class Pills extends AppCompatActivity {
    FloatingActionButton addButton;

    DatabaseHelper db;
    RecyclerView recyclerView;
    CustomAdapter customAdapter;
    ArrayList<String> medicationID, medicationName, medicationDosage, medicationTime, medicationDate;
    int myIntValue;
    AlertDialog.Builder builder1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pills);
        getSupportActionBar().setTitle("Vozyk");

        SharedPreferences sp = getSharedPreferences("your_prefs", Activity.MODE_PRIVATE);
        myIntValue = sp.getInt("userID", -1);

        medicationID = new ArrayList<>();
        medicationName = new ArrayList<>();
        medicationDosage = new ArrayList<>();
        medicationTime = new ArrayList<>();
        medicationDate = new ArrayList<>();

        builder1 = new AlertDialog.Builder(this);

        addButton = (FloatingActionButton) findViewById(R.id.add_button);
        db = new DatabaseHelper(this);
        recyclerView = findViewById(R.id.recyclerView);

        addButton.setOnClickListener(view -> {
            Intent addMed = new Intent(Pills.this, Add_new_pills.class);
            startActivityForResult(addMed, 2);
        });

        storeDataInArrays();
        customAdapter = new CustomAdapter(Pills.this, this, medicationID, medicationName, medicationDosage, medicationTime, medicationDate);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager((this)));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 || requestCode == 2) {
            recreate();
        }
    }

    public void storeDataInArrays(){
        Cursor cursor = db.readAllData(myIntValue);
        if(cursor.getCount() == 0){
            builder1.setMessage("Currently you have no medication! Please add some :)");
            builder1.setCancelable(true);
            builder1.setPositiveButton(
                    "Yes",
                    (dialog, id) -> dialog.cancel());
            AlertDialog alert11 = builder1.create();
            alert11.show();
        }else{
            while (cursor.moveToNext()){
                medicationID.add(cursor.getString(0));
                medicationName.add(cursor.getString(1));
                medicationDosage.add(cursor.getString(2));
                medicationDate.add(cursor.getString(3));
                medicationTime.add(cursor.getString(4));
            }
        }
    }
}