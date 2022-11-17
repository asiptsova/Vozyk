package com.application.vozyk.ui.habits;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import com.application.vozyk.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.SetOptions;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

public class Habits extends AppCompatActivity implements Observer, OnCompleteListener<QuerySnapshot> {

    private static final String TAG = "HabitTrackerActivity";
    private final ArrayList<Habit> habitList = new ArrayList<>();
    private FirebaseFirestore db;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_habits);

        RecyclerView recyclerView = findViewById(R.id.habitlist);

        db = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();

        Button newHabit = findViewById(R.id.new_habit);
        newHabit.setOnClickListener(v -> {
            Intent i = new Intent(getApplicationContext(), NewHabitActivity.class);
            startActivity(i);
        });
        db.collection("users").document(mAuth.getCurrentUser().getUid()).collection("habits").get().addOnCompleteListener(this);
    }

    @Override
    public void onComplete(@NonNull Task<QuerySnapshot> task) {
        if (task.isSuccessful()) {
            for (QueryDocumentSnapshot document : task.getResult()) {
                Log.d(TAG, document.getId() + "=>" + document.getData());
                Calendar endDate = Calendar.getInstance();
                Calendar startDate = Calendar.getInstance();
                Date date1 = null;
                Date date2 = null;
                try {
                    date1 = new SimpleDateFormat("MM/dd/yyyy").parse(document.getString("Start Date"));
                    date2 = new SimpleDateFormat("MM/dd/yyyy").parse(document.getString("End Date"));

                } catch (ParseException e) {
                    e.printStackTrace();
                }
                assert date1 != null;
                startDate.setTime(date1);
                assert date2 != null;
                endDate.setTime(date2);
                Habit habit = new Habit(document.getId(), document.getString("Habit Name"), startDate, endDate, document.getString("Frequency"), Integer.parseInt(document.getString("Progress")));
                habit.addObserver(this);
                habitList.add(habit);
            }
            initRecyclerView();
        } else {
            Log.d(TAG, "Error getting documents: ", task.getException());
        }
    }

    private void initRecyclerView() {
        Log.d(TAG, "Initialize recycler view");
        RecyclerView habitListView = findViewById(R.id.habitlist);
        HabitAdapter adapter = new HabitAdapter(this, habitList);
        habitListView.setAdapter(adapter);
        habitListView.setLayoutManager(new LinearLayoutManager(this));
        adapter.notifyDataSetChanged();
    }

    @Override
    public void update(Observable o, Object arg) {
        Habit h = (Habit) arg;
        Map<String, String> progressUpdate = new HashMap<>();
        progressUpdate.put("Progress", Integer.toString(h.getProgress()));
        db.collection("users").document(mAuth.getCurrentUser().getUid()).collection("habits").document(h.getHabitId()).set(progressUpdate, SetOptions.merge());
    }
}
