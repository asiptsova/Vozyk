package com.application.vozyk.ui.toDoList;

import android.app.DatePickerDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
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

    private Button add,completedButton,incompleteButton;
    private MyListAdapter adapter;
    private State currentState;
    private FirebaseFirestore db;
    private String uid;

    private final List<Task> incompleteList = new ArrayList<>();
    private final List<Task> completedList = new ArrayList<>();
    private DatePickerDialog.OnDateSetListener startDate;
    private DatePickerDialog.OnDateSetListener endDate;

    @Override
    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_todo);
        getSupportActionBar().hide();
        currentState = State.INCOMPLETE_TASKS;
        add = findViewById(R.id.addButton);
        completedButton = findViewById(R.id.bt_completed);
        incompleteButton = findViewById(R.id.bt_in_completed);
        Button deleteListButton = findViewById(R.id.delete_list_btn);

        db = FirebaseFirestore.getInstance();
        uid = FirebaseAuth.getInstance().getCurrentUser().getUid();

        adapter = new MyListAdapter();

        getAllCompletedTasksFromDB();

        getAllIncompleteTasks();

        ListView myListView = findViewById(R.id.myList);
        myListView.setAdapter(adapter);

        if (currentState == State.INCOMPLETE_TASKS)
            incompleteButton.setEnabled(false);
         else {
            completedButton.setEnabled(false);
            add.setEnabled(false);
        }

        myListView.setOnItemClickListener((parent, view, position, id) -> {
            final View infoTask = getLayoutInflater().inflate(R.layout.info_task_dialog, null);
            final TextView taskName = infoTask.findViewById(R.id.tv_task_name);
            final TextView startDate = infoTask.findViewById(R.id.tv_start_date);
            final TextView endDate = infoTask.findViewById(R.id.tv_end_date);

            if (currentState == State.INCOMPLETE_TASKS) {
                final Task task = incompleteList.get(position);
                taskName.setText(task.getTaskName());
                startDate.setText(task.getStartDate());
                endDate.setText(task.getEndDate());

                AlertDialog infoDialog = new AlertDialog.Builder(ToDoListActivity.this)
                        .setView(infoTask)
                        .setPositiveButton(getResources().getString(R.string.mark_completed), (dialog, which) -> {
                            task.setCompleted();
                            updateComplete(task.getTaskId());
                            completedList.add(task);
                            incompleteList.remove(task);
                            adapter.setData(incompleteList);
                        })
                        .setNegativeButton(getResources().getString(R.string.cancel_task), (dialog, which) -> {
                            removeTask(task.getTaskId());
                            incompleteList.remove(task);
                            adapter.setData(incompleteList);
                        })
                        .create();
                infoDialog.show();
            } else {
                final Task task = completedList.get(position);
                taskName.setText(task.getTaskName());
                startDate.setText(task.getStartDate());
                endDate.setText(task.getEndDate());
                AlertDialog infoDialog = new AlertDialog.Builder(ToDoListActivity.this)
                        .setView(infoTask)
                        .setNegativeButton(getResources().getString(R.string.cancel_task), (dialog, which) -> {
                            removeTask(task.getTaskId());
                            completedList.remove(task);
                            adapter.setData(completedList);
                        })
                        .create();
                infoDialog.show();
            }

        });

        add.setOnClickListener(v -> {
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
                        this.startDate,
                        year, month, day);
                d.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
                d.show();

            });
            finishDate.setOnClickListener(v1 -> {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog d = new DatePickerDialog(addTaskDialogView.getContext(),
                        endDate,
                        year, month, day);
                d.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
                d.show();

            });

            this.startDate = (view, year, month, dayOfMonth) -> {
                month = month + 1;
                startDate.setText(dayOfMonth + getResources().getString(R.string.slash) + month + getResources().getString(R.string.slash) + year);
            };
            endDate = (view, year, month, dayOfMonth) -> {
                month = month + 1;
                finishDate.setText(dayOfMonth + getResources().getString(R.string.slash) + month + getResources().getString(R.string.slash) + year);
            };

            AlertDialog dialog = new AlertDialog.Builder(ToDoListActivity.this)
                    .setView(addTaskDialogView)
                    .setPositiveButton(null, (dialog1, which) -> {
                        if (taskName.getText().toString().isEmpty()) {
                            Toast.makeText(ToDoListActivity.this, getResources().getString(R.string.task_name_error), Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(ToDoListActivity.this, getResources().getString(R.string.added_task), Toast.LENGTH_SHORT).show();
                            Task newTask = new Task(taskName.getText().toString(), startDate.getText().toString(), finishDate.getText().toString());
                            incompleteList.add(newTask);
                            addTaskToDatabase(taskName.getText().toString(), startDate.getText().toString(), finishDate.getText().toString(), newTask.getTaskId(), false);
                            adapter.setData(incompleteList);
                        }

                    })
                    .setPositiveButtonIcon(AppCompatResources.getDrawable(ToDoListActivity.this, R.drawable.complete_task))
                    .create();
            dialog.show();
        });
        completedButton.setOnClickListener(v -> {
            adapter.setData(completedList);
            completedButton.setEnabled(false);
            incompleteButton.setEnabled(true);
            add.setEnabled(false);
            currentState = State.COMPLETE_TASKS;
        });

        incompleteButton.setOnClickListener(v -> {
            adapter.setData(incompleteList);
            incompleteButton.setEnabled(false);
            completedButton.setEnabled(true);
            add.setEnabled(true);
            currentState = State.INCOMPLETE_TASKS;
        });

        deleteListButton.setOnClickListener(v -> {
            if (currentState == State.INCOMPLETE_TASKS) {
                for (Task t: incompleteList) {
                    removeTask(t.getTaskId());
                }
                incompleteList.clear();
                adapter.setData(incompleteList);
            } else {
                for (Task t:completedList) {
                    removeTask(t.getTaskId());
                }
                completedList.clear();
                adapter.setData(completedList);
            }
        });
    }

    class MyListAdapter extends BaseAdapter {
        final List<Task> taskList = new ArrayList<>();

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
                            t.setEndDate(finishDate);
                            t.setStartDate(startDate);
                            t.setTaskName(taskName);
                            t.setTaskId(document.getId());

                            if (complete)
                                completedList.add(t);
                        }
                        adapter.setData(completedList);
                    }
                });
    }

    public void getAllIncompleteTasks() {
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
                            t.setEndDate(finishDate);
                            t.setStartDate(startDate);
                            t.setTaskName(taskName);
                            t.setTaskId(document.getId());
                            if (!complete)
                                incompleteList.add(t);
                        }
                        adapter.setData(incompleteList);
                    }
                });
    }

    public void updateComplete(String taskID) {
        db.collection("users").document(this.uid).collection("taskLog").document(taskID).update("completed", true);
    }

    public void removeTask(String taskID) {
        db.collection("users").document(this.uid).collection("taskLog").document(taskID).delete();
    }
}