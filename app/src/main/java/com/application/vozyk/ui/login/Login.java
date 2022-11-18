package com.application.vozyk.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.application.vozyk.MainActivity;
import com.application.vozyk.R;
import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.common.ConnectionResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class Login extends AppCompatActivity {
    FirebaseAuth firebaseAuth;
    FirebaseAuth.AuthStateListener firebaseAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).hide();
        for (String provider : AuthUI.SUPPORTED_PROVIDERS) {
            Log.v(this.getClass().getName(), provider);
        }
        setContentView(R.layout.login);

        final EditText mEmail = findViewById(R.id.Email);
        final EditText mPassword = findViewById(R.id.password);
        final Button mLoginBtn = findViewById(R.id.loginBtn);
        final TextView  mCreateBtn = findViewById(R.id.createText);
        final TextView  forgotTextLink = findViewById(R.id.forgotPassword);
        final ImageView ShowHidePWD=findViewById(R.id.show_hide_pwd);
        ShowHidePWD.setOnClickListener(view -> {
            if(mPassword.getTransformationMethod().equals(HideReturnsTransformationMethod.getInstance()))
                mPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
            else
                mPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());

        });

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuthListener = firebaseAuth -> {
            if (firebaseAuth.getCurrentUser() != null)
                goMainScreen();
        };
        mLoginBtn.setOnClickListener(v -> {
            String email = mEmail.getText().toString().trim();
            String password = mPassword.getText().toString().trim();
            if (TextUtils.isEmpty(email)) {
                mEmail.setError(String.valueOf(R.string.email_is_required));
                return;
            }
            if (TextUtils.isEmpty(password)) {
                mPassword.setError(String.valueOf(R.string.password_is_required));
                return;
            }
            if (password.length() < 6) {
                mPassword.setError(String.valueOf(R.string.password_contain_characters));
                return;
            }
            FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    Toast.makeText(Login.this, String.valueOf(R.string.logged_in_successfully),
                            Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                } else
                    Toast.makeText(Login.this, (R.string.error)
                                    + Objects.requireNonNull(task.getException()).getMessage(),
                            Toast.LENGTH_SHORT).show();
            });
        });

        mCreateBtn.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), Registration.class)));
        forgotTextLink.setOnClickListener(v -> {
            final EditText resetMail = new EditText(v.getContext());
            final AlertDialog.Builder passwordResetDialog = new AlertDialog.Builder(v.getContext());
            passwordResetDialog.setTitle(String.valueOf(R.string.reset_password));
            passwordResetDialog.setMessage(String.valueOf(R.string.reset_link));
            passwordResetDialog.setView(resetMail);

            passwordResetDialog.setPositiveButton(String.valueOf(R.string.yes), (dialog, which) -> {
                String mail = resetMail.getText().toString();
                FirebaseAuth.getInstance().sendPasswordResetEmail(mail).addOnSuccessListener(aVoid ->
                        Toast.makeText(this, String.valueOf(R.string.link_sent),
                                Toast.LENGTH_SHORT).show()).addOnFailureListener(e ->
                        Toast.makeText(this, (R.string.error_link) + e.getMessage(),
                                Toast.LENGTH_SHORT).show());

            });

            passwordResetDialog.setNegativeButton(String.valueOf(R.string.no), (dialog, which) -> {
            });
            passwordResetDialog.create().show();
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseAuth.getInstance().addAuthStateListener(firebaseAuthListener);
    }

    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {}
    private void goMainScreen() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
    @Override
    protected void onStop() {
        super.onStop();
        if (firebaseAuthListener != null) {
            FirebaseAuth.getInstance().removeAuthStateListener(firebaseAuthListener);
        }

    }
}