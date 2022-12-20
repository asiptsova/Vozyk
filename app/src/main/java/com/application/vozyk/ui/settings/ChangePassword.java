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

public class ChangePassword extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_change_password);
        final EditText email = findViewById(R.id.current_email);
        final EditText current_password = findViewById(R.id.current_password);
        final EditText new_password = findViewById(R.id.password_new);
        final EditText new_password_repeat = findViewById(R.id.password_new_repeat);
        Button save = findViewById(R.id.Save_password);
        save.setOnClickListener(v -> {
            if (FirebaseAuth.getInstance().getCurrentUser() != null) {
                AuthCredential credential = EmailAuthProvider.getCredential(email.getText().toString().trim(), current_password.getText().toString().trim());
                FirebaseAuth.getInstance().getCurrentUser().reauthenticate(credential)
                        .addOnCompleteListener(task -> {
                            if (task.isSuccessful()) {
                                if (new_password.getText().toString().trim().equals(new_password_repeat.getText().toString().trim())) {
                                    FirebaseAuth.getInstance().getCurrentUser().updatePassword(new_password.getText().toString().trim()).addOnCompleteListener(task1 -> {
                                        if (task1.isSuccessful()) {
                                            Toast.makeText(ChangePassword.this, getResources().getString(R.string.update), Toast.LENGTH_SHORT).show();
                                            startActivity(new Intent(ChangePassword.this, Settings.class));
                                        } else {
                                            Toast.makeText(ChangePassword.this, getResources().getString(R.string.error), Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                }
                            }
                        });
            }
        });
    }

}