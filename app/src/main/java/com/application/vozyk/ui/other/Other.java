package com.application.vozyk.ui.other;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import com.application.vozyk.ui.about.AboutUs;
import com.application.vozyk.ui.account.Account;
import com.application.vozyk.R;
import com.application.vozyk.ui.notes.NotesListActivity;
import com.application.vozyk.ui.quiz.QuizActivity;
import com.application.vozyk.ui.ToDoList.ToDoListActivity;

public class Other extends Fragment {
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_other, container, false);
        final View account=root.findViewById(R.id.account);
        final TextView about=root.findViewById(R.id.about);
        final View quiz=root.findViewById(R.id.test);
        final View toDo=root.findViewById(R.id.toDo);
        final View notes=root.findViewById(R.id.notes);


        account.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), Account.class);
            startActivity(intent);
        });
        about.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), AboutUs.class);
            startActivity(intent);
        });

        about.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), AboutUs.class);
            startActivity(intent);
        });
        quiz.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), QuizActivity.class);
            startActivity(intent);
        });
        toDo.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), ToDoListActivity.class);
            startActivity(intent);
        });
        notes.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), NotesListActivity.class);
            startActivity(intent);
        });

        return root;
    }
}