package com.application.vozyk.ui.settings;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.application.vozyk.R;
import com.application.vozyk.ui.login.Login;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;


public class DeleteAccount extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_acount);
        getSupportActionBar().hide();
        final EditText email = findViewById(R.id.email);
        final EditText password = findViewById(R.id.pass);
        Button submit = findViewById(R.id.submit);
        submit.setOnClickListener(v -> {
            AuthCredential credential = EmailAuthProvider
                    .getCredential(email.getText().toString().trim(), password.getText().toString().trim());
            if (FirebaseAuth.getInstance().getCurrentUser() != null) {
                FirebaseAuth.getInstance().getCurrentUser().reauthenticate(credential)
                        .addOnCompleteListener(task -> FirebaseAuth.getInstance().getCurrentUser().delete()
                                .addOnCompleteListener(task1 -> {
                                    if (task1.isSuccessful()) {
                                        startActivity(new Intent(DeleteAccount.this, Login.class));
                                        Toast.makeText(DeleteAccount.this, getResources().getString(R.string.delete_user_successfully), Toast.LENGTH_LONG).show();
                                    }
                                }));
            }
        });

    }

    private void deleteUser(String email, String password) {
    }
}