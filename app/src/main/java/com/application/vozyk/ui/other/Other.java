package com.application.vozyk.ui.other;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.application.vozyk.About_us;
import com.application.vozyk.Account;
import com.application.vozyk.Login;
import com.application.vozyk.R;
import com.application.vozyk.ui.ToDoList.ToDoListActivity;
import com.application.vozyk.ui.habits.Habits;
import com.application.vozyk.ui.notes.NotesListActivity;
import com.application.vozyk.ui.quiz.QuizActivity;
import com.firebase.ui.auth.AuthUI;

public class Other extends Fragment {
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_other, container, false);
        final Button habit_link=root.findViewById(R.id.habit_link);
        final Button account=root.findViewById(R.id.account);
        final Button settings=root.findViewById(R.id.settings);
        final Button about=root.findViewById(R.id.about);
        final Button logout=root.findViewById(R.id.logout);
        final Button quiz=root.findViewById(R.id.test);
        final Button toDo=root.findViewById(R.id.toDo);
        final Button notes=root.findViewById(R.id.notes);


        habit_link.setOnClickListener(v -> startActivity(new Intent(getContext(), Habits.class)));
        /*logout.setOnClickListener(v -> AuthUI.getInstance()
                .signOut(getContext())
                .addOnCompleteListener(task -> startActivity(new Intent(getContext(), Login.class))));*/

        logout.setOnClickListener(v -> {
            final AlertDialog.Builder b = new AlertDialog.Builder(getContext());
            b.setMessage("Are you sure you want to sign out?");
            b.setCancelable(true);
            b.setNegativeButton("Yes", (dialog, which) -> {
                dialog.cancel();
                AuthUI.getInstance()
               .signOut(getContext())
                        .addOnCompleteListener(task -> startActivity(new Intent(getContext(), Login.class)));
            });
            b.setPositiveButton("No", (dialog, which) -> {
            });
            AlertDialog a = b.create();
            a.show();
        });

        account.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), Account.class);
            startActivity(intent);
        });
        about.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), About_us.class);
            startActivity(intent);
        });

        about.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), About_us.class);
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