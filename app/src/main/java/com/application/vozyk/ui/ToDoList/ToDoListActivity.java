package com.application.vozyk.ui.ToDoList;

import android.app.DatePickerDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;
import com.application.vozyk.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

enum State {
    INCOMPLETE_TASKS,
    COMPLETE_TASKS
}

public class ToDoListActivity extends AppCompatActivity {

    private Button addButton;
    private Button completedButton;
    private Button incompleteButton;
    private MyListAdapter mylistadapter;

    private State currentState;
    private FirebaseFirestore db;
    private String uid;

    private final List<Task> incompletedList = new ArrayList<>();
    private final List<Task> completedList = new ArrayList<>();
    private DatePickerDialog.OnDateSetListener dateSetListener;
    private DatePickerDialog.OnDateSetListener enddateSetListener;

    @Override
    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_to_do_list);

        this.currentState = State.INCOMPLETE_TASKS;
        this.addButton = findViewById(R.id.addButton);
        this.completedButton = findViewById(R.id.viewCompletedButton);
        this.incompleteButton = findViewById(R.id.viewIncompletedButton);
        Button deleteListButton = findViewById(R.id.delete_list_btn);

        this.db = FirebaseFirestore.getInstance();
        this.uid = FirebaseAuth.getInstance().getCurrentUser().getUid();

        this.mylistadapter = new MyListAdapter();

        this.getAllCompletedTasksFromDB();

        this.getAllIncompletedTasksFromDB();

        ListView myListView = findViewById(R.id.myList);
        myListView.setAdapter(this.mylistadapter);

        if (this.currentState == State.INCOMPLETE_TASKS) {
            this.incompleteButton.setEnabled(false);
        } else {
            this.completedButton.setEnabled(false);
            this.addButton.setEnabled(false);
        }

        myListView.setOnItemClickListener((parent, view, position, id) -> {
            final View infoTaskDialogView = getLayoutInflater().inflate(R.layout.info_task_dialog, null);
            final TextView taskLabel = infoTaskDialogView.findViewById(R.id.taskLabel);
            final TextView startDateLabel = infoTaskDialogView.findViewById(R.id.startDateLabel);
            final TextView endDateLabel = infoTaskDialogView.findViewById(R.id.endDateLabel);

            if (currentState == State.INCOMPLETE_TASKS) {
                final Task task = incompletedList.get(position);
                taskLabel.setText(task.getTaskName());
                startDateLabel.setText(task.getStart_date());
                endDateLabel.setText(task.getEnd_date());

                AlertDialog infoDialog = new AlertDialog.Builder(ToDoListActivity.this)
                        .setView(infoTaskDialogView)
                        .setPositiveButton("Mark As Completed", (dialog, which) -> {
                            task.setCompleted();
                            updateCompleteInDb(task.getTaskId());
                            completedList.add(task);
                            incompletedList.remove(task);
                            mylistadapter.setData(incompletedList);
                        })
                        .setNegativeButton("Cancel Task", (dialog, which) -> {
                            removeTaskFromDb(task.getTaskId());
                            incompletedList.remove(task);
                            mylistadapter.setData(incompletedList);
                        })
                        .create();
                infoDialog.show();
            } else {
                final Task task = completedList.get(position);
                taskLabel.setText(task.getTaskName());
                startDateLabel.setText(task.getStart_date());
                endDateLabel.setText(task.getEnd_date());
                AlertDialog infoDialog = new AlertDialog.Builder(ToDoListActivity.this)
                        .setView(infoTaskDialogView)
                        .setNegativeButton("Cancel Task", (dialog, which) -> {
                            removeTaskFromDb(task.getTaskId());
                            completedList.remove(task);
                            mylistadapter.setData(completedList);
                        })
                        .create();
                infoDialog.show();
            }

        });

        addButton.setOnClickListener(v -> {
            final View addTaskDialogView = getLayoutInflater().inflate(R.layout.add_new_task_dialog, null);
            final EditText taskName = addTaskDialogView.findViewById(R.id.task_name);
            final TextView startDate = addTaskDialogView.findViewById(R.id.start_date);
            final TextView finishDate = addTaskDialogView.findViewById(R.id.end_date);

            startDate.setOnClickListener(v12 -> {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog d = new DatePickerDialog(addTaskDialogView.getContext(),
                        dateSetListener,
                        year, month, day);
                d.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
                d.show();

            });
            finishDate.setOnClickListener(v1 -> {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog d = new DatePickerDialog(addTaskDialogView.getContext(),
                        enddateSetListener,
                        year, month, day);
                d.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
                d.show();

            });

            dateSetListener = (view, year, month, dayOfMonth) -> {
                month = month + 1;
                startDate.setText(dayOfMonth + "/" + month + "/" + year);
                Log.d("task added", "date:" + dayOfMonth + "/" + month + "/" + year);
            };
            enddateSetListener = (view, year, month, dayOfMonth) -> {
                month = month + 1;
                finishDate.setText(dayOfMonth + "/" + month + "/" + year);
                Log.d("task added", "date:" + dayOfMonth + "/" + month + "/" + year);
            };

            AlertDialog dialog = new AlertDialog.Builder(ToDoListActivity.this)
                    .setView(addTaskDialogView)
                    .setPositiveButton(null, (dialog1, which) -> {
                        if (taskName.getText().toString().isEmpty()) {
                            Toast.makeText(ToDoListActivity.this, "ERROR: Please specify task name", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(ToDoListActivity.this, "Successfully Added Task", Toast.LENGTH_SHORT).show();
                            Task newTask = new Task(taskName.getText().toString(), startDate.getText().toString(), finishDate.getText().toString());
                            incompletedList.add(newTask);
                            //add to database
                            addTaskToDatabase(taskName.getText().toString(), startDate.getText().toString(), finishDate.getText().toString(), newTask.getTaskId(), false);
                            mylistadapter.setData(incompletedList);
                        }

                    })
                    .setPositiveButtonIcon(AppCompatResources.getDrawable(ToDoListActivity.this, R.drawable.complete_task))
                    .create();
            dialog.show();
        });

        completedButton.setOnClickListener(v -> {
            mylistadapter.setData(completedList);
            completedButton.setEnabled(false);
            incompleteButton.setEnabled(true);
            addButton.setEnabled(false);
            currentState = State.COMPLETE_TASKS;
        });

        incompleteButton.setOnClickListener(v -> {
            mylistadapter.setData(incompletedList);
            incompleteButton.setEnabled(false);
            completedButton.setEnabled(true);
            addButton.setEnabled(true);
            currentState = State.INCOMPLETE_TASKS;
        });

        deleteListButton.setOnClickListener(v -> {
            if (currentState == State.INCOMPLETE_TASKS) {
                for (Task t:incompletedList) {
                    removeTaskFromDb(t.getTaskId());
                }
                incompletedList.clear();
                mylistadapter.setData(incompletedList);
            } else {
                for (Task t:completedList) {
                    removeTaskFromDb(t.getTaskId());
                }
                completedList.clear();
                mylistadapter.setData(completedList);
            }
        });
    }

    class MyListAdapter extends BaseAdapter {
        List<Task> taskList = new ArrayList<>();

        public void setData(List<Task> mList) {
            taskList.clear();
            taskList.addAll(mList);
            notifyDataSetChanged();
        }

        @Override
        public int getCount() {
            return taskList.size();
        }

        @Override
        public Object getItem(int position) {
            return taskList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            LayoutInflater inflateLayout = (LayoutInflater) ToDoListActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View taskRow = inflateLayout.inflate(R.layout.task, parent, false);
            TextView taskObject = taskRow.findViewById(R.id.taskItem);
            taskObject.setText(taskList.get(position).getTaskName());
            return taskRow;
        }
    }

    public void addTaskToDatabase(String taskName, String startDate, String finishDate, String taskId, boolean completed) {
        Map<String, Object> newTaskForUser = new HashMap<>();

        newTaskForUser.put("taskname", taskName);
        newTaskForUser.put("startDate", startDate);
        newTaskForUser.put("finishDate", finishDate);
        newTaskForUser.put("completed", completed);

        db.collection("users").document(uid).collection("taskLog").document(taskId).set(newTaskForUser);
    }

    public void getAllCompletedTasksFromDB() {

        db.collection("users").document(uid).collection("taskLog")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document: task.getResult()) {
                            Map<String, Object> taskItem = document.getData();
                            String taskName = (String)taskItem.get("taskname");
                            String startDate = (String)taskItem.get("startDate");
                            String finishDate = (String)taskItem.get("finishDate");
                            boolean complete = (boolean)taskItem.get("completed");

                            Task t = new Task();
                            t.setCompleted();
                            t.setEnd_date(finishDate);
                            t.setStart_date(startDate);
                            t.setTaskName(taskName);
                            t.setTaskId(document.getId());

                            if (complete) {
                                completedList.add(t);
                            }
                        }
                        mylistadapter.setData(completedList);
                    }
                });
    }

    public void getAllIncompletedTasksFromDB() {
        db.collection("users").document(uid).collection("taskLog")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document: task.getResult()) {
                            Map<String, Object> taskItem = document.getData();
                            String taskName = (String)taskItem.get("taskname");
                            String startDate = (String)taskItem.get("startDate");
                            String finishDate = (String)taskItem.get("finishDate");
                            boolean complete = (boolean)taskItem.get("completed");


                            Task t = new Task();
                            t.setCompleted();
                            t.setEnd_date(finishDate);
                            t.setStart_date(startDate);
                            t.setTaskName(taskName);
                            t.setTaskId(document.getId());

                            if (!complete) {
                                incompletedList.add(t);
                            }

                        }
                        mylistadapter.setData(incompletedList);
                    }
                });
    }

    public void updateCompleteInDb(String taskID) {
        db.collection("users").document(this.uid).collection("taskLog").document(taskID).update("completed", true);
    }

    public void removeTaskFromDb(String taskID) {
        db.collection("users").document(this.uid).collection("taskLog").document(taskID).delete();
    }
}