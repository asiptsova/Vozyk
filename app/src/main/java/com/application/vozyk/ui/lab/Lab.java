package com.application.vozyk.ui.lab;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.application.vozyk.MainActivity;
import com.application.vozyk.R;
import com.application.vozyk.ui.doctor.Doctor;
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

public class Lab extends AppCompatActivity {
    private ArrayList<LabTestDataModel> arrayList;
    private LabCustomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab);
        getSupportActionBar().hide();

        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();

        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference().child("LabTestRecord").child(Objects.requireNonNull(user.getUid()));
        ListView listview = findViewById(R.id.lv_lab);
        BottomAppBar bottomAppBar = findViewById(R.id.lab_bottomAppBar);

        arrayList = new ArrayList<>();
        adapter = new LabCustomAdapter(getApplicationContext(), arrayList);

        mDatabase.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                LabTestDataModel labTestDataModel = snapshot.getValue(LabTestDataModel.class);
                assert labTestDataModel != null;
                labTestDataModel.key = snapshot.getKey();
                arrayList.add(labTestDataModel);
                adapter.notifyDataSetChanged();
                emptyImage();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

                LabTestDataModel labTestDataModel = snapshot.getValue(LabTestDataModel.class);
                assert labTestDataModel != null;
                labTestDataModel.key = snapshot.getKey();

                for (int i = 0; i < arrayList.size(); i++) {
                    if (labTestDataModel.key.equals(arrayList.get(i).key))
                        arrayList.remove(i);
                }
                adapter.notifyDataSetChanged();
                emptyImage();
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        listview.setAdapter(adapter);

        bottomAppBar.setOnMenuItemClickListener(item -> {
            switch (item.getItemId()) {
                case R.id.nav_lab_medicine:
                    startActivity(new Intent(Lab.this, MedsPills.class));
                    break;
                case R.id.nav_lab_doc:
                    startActivity(new Intent(Lab.this, Doctor.class));
                    break;
                case R.id.nav_home:
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    break;
                default:
                    return false;
            }
            return true;
        });
        findViewById(R.id.lab_add_btn).setOnClickListener(view -> startActivity(new Intent(Lab.this, LabAdd.class)));

    }

    public void emptyImage() {
        if (adapter.isEmpty()) {
            findViewById(R.id.iv_lab_empty).setVisibility(View.VISIBLE);
            findViewById(R.id.tv_no_labs).setVisibility(View.VISIBLE);
        } else {
            findViewById(R.id.iv_lab_empty).setVisibility(View.INVISIBLE);
            findViewById(R.id.tv_no_labs).setVisibility(View.INVISIBLE);
        }
    }
}