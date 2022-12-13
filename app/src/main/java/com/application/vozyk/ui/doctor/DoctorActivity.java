package com.application.vozyk.ui.doctor;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.application.vozyk.R;
import com.application.vozyk.ui.lab.labActivity;
import com.application.vozyk.ui.meds.MedsPills;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import java.util.ArrayList;
import java.util.Objects;

public class DoctorActivity extends AppCompatActivity {

    private ArrayList<DoctorDataModel> arrayList;
    private DocCustomAdapter c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor);

        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();

        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference().child("AppointmentRecord").child(Objects.requireNonNull(user.getUid())); //Takes the relative path of the user to get the instance of only that user not others.
        ListView listview = findViewById(R.id.doctor_listview);
        BottomAppBar bottomAppBar = findViewById(R.id.doctor_bottomAppBar);

        arrayList = new ArrayList<>();
        c = new DocCustomAdapter(getApplicationContext(), arrayList);

        mDatabase.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                DoctorDataModel doctorDataModel = snapshot.getValue(DoctorDataModel.class);
                assert doctorDataModel != null;
                doctorDataModel.key = snapshot.getKey();

                arrayList.add(doctorDataModel);
                c.notifyDataSetChanged();
                emptyImage();

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                c.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                DoctorDataModel doctorDataModel = snapshot.getValue(DoctorDataModel.class);
                assert doctorDataModel != null;
                doctorDataModel.key = snapshot.getKey();

                for (int i = 0; i < arrayList.size(); i++) {
                    assert doctorDataModel.key != null;
                    if (doctorDataModel.key.equals(arrayList.get(i).key)) {
                        arrayList.remove(i);
                    }
                }

                c.notifyDataSetChanged();
                emptyImage();
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        listview.setAdapter(c);

        findViewById(R.id.doctor_add_btn).setOnClickListener(view -> startActivity(new Intent(DoctorActivity.this, DoctorAddActivity.class)));

        bottomAppBar.setOnMenuItemClickListener(item -> {
            switch (item.getItemId()) {
                case R.id.nav_medicine:
                    try {
                        startActivity(new Intent(DoctorActivity.this, MedsPills.class));
                    }
                    catch (Exception ignored){}
                    break;
                case R.id.nav_lab:
                     startActivity(new Intent(DoctorActivity.this, labActivity.class));
                    break;
                default:
                    return false;
            }
            return true;
        });


    }

    public void emptyImage() {
        if (c.isEmpty()) {
            findViewById(R.id.doctor_empty).setVisibility(View.VISIBLE);
            findViewById(R.id.doctor_emptyText).setVisibility(View.VISIBLE);
        } else {
            findViewById(R.id.doctor_empty).setVisibility(View.INVISIBLE);
            findViewById(R.id.doctor_emptyText).setVisibility(View.INVISIBLE);
        }
    }


}