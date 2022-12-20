package com.application.vozyk.ui.settings;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.application.vozyk.R;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ChangeEmail extends AppCompatActivity {


    private DatabaseReference getEmailRef() {
        return FirebaseDatabase.getInstance().getReference("Users")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("email");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_email);
        getSupportActionBar().hide();
        final EditText email = findViewById(R.id.current_email);
        final EditText new_email = findViewById(R.id.new_email);
        final EditText password = findViewById(R.id.password);
        getSupportActionBar().hide();
        Button save = findViewById(R.id.Save);
        save.setOnClickListener(v -> {
            if (FirebaseAuth.getInstance().getCurrentUser() != null) {
                AuthCredential credential = EmailAuthProvider
                        .getCredential(email.getText().toString().trim(), password.getText().toString().trim());
                FirebaseAuth.getInstance().getCurrentUser().reauthenticate(credential)
                        .addOnCompleteListener(task -> FirebaseAuth.getInstance().getCurrentUser().updateEmail(new_email.getText().toString().trim())
                                .addOnCompleteListener(task1 -> {
                                    if (task1.isSuccessful()) {
                                        Toast.makeText(ChangeEmail.this, getResources().getString(R.string.update), Toast.LENGTH_SHORT).show();
                                        getEmailRef().setValue(new_email.getText().toString().trim());
                                        startActivity(new Intent(ChangeEmail.this, Settings.class));
                                    } else {
                                        Toast.makeText(ChangeEmail.this, getResources().getString(R.string.error), Toast.LENGTH_SHORT).show();
                                    }
                                }));
            }
        });

    }
}

