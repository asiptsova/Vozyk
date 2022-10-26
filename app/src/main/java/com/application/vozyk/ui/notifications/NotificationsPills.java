package com.application.vozyk.ui.notifications;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.application.vozyk.Add_new_pills;
import com.application.vozyk.CustomAdapter;
import com.application.vozyk.DatabaseHelper;
import com.application.vozyk.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class NotificationsPills extends Fragment {

    FloatingActionButton addButton;

    DatabaseHelper db;
    RecyclerView recyclerView;
    CustomAdapter customAdapter;
    ArrayList<String> medicationID, medicationName, medicationDosage, medicationTime, medicationDate;
    int myIntValue;
    AlertDialog.Builder builder1;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_notifications, container, false);
        SharedPreferences sp = getContext().getSharedPreferences("your_prefs", Activity.MODE_PRIVATE);
        myIntValue = sp.getInt("userID", -1);

        medicationID = new ArrayList<>();
        medicationName = new ArrayList<>();
        medicationDosage = new ArrayList<>();
        medicationTime = new ArrayList<>();
        medicationDate = new ArrayList<>();

        builder1 = new AlertDialog.Builder(getContext());

        addButton = (FloatingActionButton) root.findViewById(R.id.add_button);
        db = new DatabaseHelper(getActivity());
        recyclerView = root.findViewById(R.id.recyclerView);

        addButton.setOnClickListener(view -> {
            Intent addMed = new Intent(getActivity(), Add_new_pills.class);
            startActivityForResult(addMed, 2);
        });

        storeDataInArrays();
        customAdapter = new CustomAdapter(getActivity(), getActivity(), medicationID, medicationName, medicationDosage, medicationTime, medicationDate);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager((getContext())));

        return root;
    }

    /*@Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 || requestCode == 2) {
            recreate();
        }
    }*/

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