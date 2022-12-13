package com.application.vozyk.ui.notifications;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.application.vozyk.R;
import com.application.vozyk.ui.lab.labActivity;
import com.application.vozyk.ui.doctor.DoctorActivity;
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


public class NotificationsPills extends AppCompatActivity {
    private ArrayList<MedicineRecordHandler> arrayList;
    private CustomAdapterNew c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_notifications);
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();

        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference().child("MedicineRecord").child(Objects.requireNonNull(user.getUid())); //Takes the relative path of the user to get the instance of only that user not others.
        ListView listview = findViewById(R.id.listview);
        BottomAppBar bottomAppBar = findViewById(R.id.bottomAppBar);

        arrayList = new ArrayList<>();
        c = new CustomAdapterNew(getApplicationContext(), arrayList);

        mDatabase.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                MedicineRecordHandler medicineRecordHandler = snapshot.getValue(MedicineRecordHandler.class);
                medicineRecordHandler.key = snapshot.getKey();
                arrayList.add(medicineRecordHandler);
                emptyImage();
                c.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                c.notifyDataSetChanged();

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                MedicineRecordHandler medicineRecordHandler = snapshot.getValue(MedicineRecordHandler.class);
                medicineRecordHandler.key = snapshot.getKey();

                for (int i = 0; i < arrayList.size(); i++) {
                    if (medicineRecordHandler.key.equals(arrayList.get(i).key)) {
                        arrayList.remove(i);
                    }
                }
                emptyImage();
                c.notifyDataSetChanged();
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplicationContext(), "Check you internet connection", Toast.LENGTH_SHORT).show();
            }
        });

        listview.setAdapter(c);

        findViewById(R.id.add_btn).setOnClickListener(view -> startActivity(new Intent(NotificationsPills.this, HomeActivity.class).putExtra("Id", user.getUid())
                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK)
        ));
        bottomAppBar.setOnMenuItemClickListener(item -> {
            switch (item.getItemId()) {
                case R.id.nav_doctor:
                    startActivity(new Intent(getApplicationContext(), DoctorActivity.class));
                    break;
                case R.id.nav_lab:
                    startActivity(new Intent(getApplicationContext(), labActivity.class));
                    break;
                default:
                    return false;
            }
            return true;
        });
    }


    public void emptyImage() {
        if (c.isEmpty()) {
            findViewById(R.id.empty).setVisibility(View.VISIBLE);
            findViewById(R.id.emptyText).setVisibility(View.VISIBLE);
        } else {
            findViewById(R.id.empty).setVisibility(View.INVISIBLE);
            findViewById(R.id.emptyText).setVisibility(View.INVISIBLE);

        }
    }

}

