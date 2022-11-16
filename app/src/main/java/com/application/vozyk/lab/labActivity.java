package com.application.vozyk.lab;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.application.vozyk.R;
import com.application.vozyk.ui.doctor.DoctorActivity;
import com.application.vozyk.ui.notifications.NotificationsPills;
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

public class labActivity extends AppCompatActivity {
    private ArrayList<LabTestDataModel> arrayList;
    private LabCustomAdapter c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab);


        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();

        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference().child("LabTestRecord").child(Objects.requireNonNull(user.getUid())); //Takes the relative path of the user to get the instance of only that user not others.
        ListView listview = findViewById(R.id.lab_listview);
        BottomAppBar bottomAppBar = findViewById(R.id.lab_bottomAppBar);

        arrayList = new ArrayList<>();

        c = new LabCustomAdapter(getApplicationContext(), arrayList);

        mDatabase.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                LabTestDataModel labTestDataModel = snapshot.getValue(LabTestDataModel.class);
                assert labTestDataModel != null;
                labTestDataModel.key = snapshot.getKey();
                arrayList.add(labTestDataModel);
                c.notifyDataSetChanged();
                emptyImage();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                c.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

                LabTestDataModel labTestDataModel = snapshot.getValue(LabTestDataModel.class);
                assert labTestDataModel != null;
                labTestDataModel.key = snapshot.getKey();

                for (int i = 0; i < arrayList.size(); i++) {
                    if (labTestDataModel.key.equals(arrayList.get(i).key)) {
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

        bottomAppBar.setOnMenuItemClickListener(item -> {
            switch (item.getItemId()) {
                case R.id.nav_lab_medicine:
                    startActivity(new Intent(labActivity.this, NotificationsPills.class));
                    break;
                case R.id.nav_lab_doc:
                    startActivity(new Intent(labActivity.this, DoctorActivity.class));
                    break;
                default:
                    return false;
            }
            return true;
        });
        findViewById(R.id.lab_add_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(labActivity.this, labAddActivity.class));
            }
        });

    }

    public void emptyImage() {
        if (c.isEmpty()) {
            findViewById(R.id.lab_empty).setVisibility(View.VISIBLE);
            findViewById(R.id.lab_emptyText).setVisibility(View.VISIBLE);
        } else {
            findViewById(R.id.lab_empty).setVisibility(View.INVISIBLE);
            findViewById(R.id.lab_emptyText).setVisibility(View.INVISIBLE);
        }
    }
}