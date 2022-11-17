package com.application.vozyk.ui.notifications;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;


public class AlarmRefreshService extends Service {

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        Log.d("AlarmRefreshService", "AlarmRefreshService Started");
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference().child("MedicineRecord").child(Objects.requireNonNull(user.getUid()));
        mDatabase.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                refreshData(snapshot);

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                refreshData(snapshot);
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                deleteData(snapshot);
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public void refreshData(DataSnapshot snapshot) {

        MedicineRecordHandler mrd = snapshot.getValue(MedicineRecordHandler.class);
        AlarmManagerHandler.initAlarm(mrd, getApplicationContext());
    }

    public void deleteData(DataSnapshot snapshot) {
        MedicineRecordHandler mrd = snapshot.getValue(MedicineRecordHandler.class);
        AlarmManagerHandler.cancelAlarm(getApplicationContext(), mrd);
    }


}
