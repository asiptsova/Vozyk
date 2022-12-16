package com.application.vozyk.ui.login;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.application.vozyk.MainActivity;
import com.application.vozyk.R;
import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Locale;
import java.util.Objects;

public class Login extends AppCompatActivity {
    FirebaseAuth firebaseAuth;
    FirebaseAuth.AuthStateListener firebaseAuthListener;
    String temp = "Language";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).hide();
        for (String provider : AuthUI.SUPPORTED_PROVIDERS) {
            Log.v(this.getClass().getName(), provider);
        }
        setContentView(R.layout.activity_login);
        if (getIntent().getStringExtra("language") != null) {
            temp = getIntent().getStringExtra("language");
            if (temp.equals("English")) {
                Locale locale = new Locale("en");
                changeLocale(locale);
            }
            if (temp.equals("Belorussian")) {
                Locale locale = new Locale("be");
                changeLocale(locale);
            }
            if (temp.equals("Polish")) {
                Locale locale = new Locale("pl");
                changeLocale(locale);
            }
        }
        final EditText mEmail = findViewById(R.id.Email);
        final EditText mPassword = findViewById(R.id.password);
        final Button mLoginBtn = findViewById(R.id.loginBtn);
        final TextView mCreateBtn = findViewById(R.id.createText);
        final TextView forgotTextLink = findViewById(R.id.forgotPassword);
        final ImageView ShowHidePWD = findViewById(R.id.show_hide_pwd);
        final Spinner spinnerLanguage = findViewById(R.id.spinnerLanguage);
        ArrayAdapter<CharSequence> adapterLanguage = ArrayAdapter.createFromResource(this,
                R.array.language, R.layout.spinner_item);
        spinnerLanguage.setAdapter(adapterLanguage);
        adapterLanguage.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerLanguage.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                temp = parent.getItemAtPosition(pos).toString();
                if (temp.equals("English")) {
                    Locale locale = new Locale("en");
                    changeLocale(locale);
                } else if (temp.equals("Belorussian")) {
                    Locale locale = new Locale("be");
                    changeLocale(locale);
                } else {
                    Locale locale = new Locale("pl");
                    changeLocale(locale);
                }
            }

            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        ShowHidePWD.setOnClickListener(view -> {
            if (mPassword.getTransformationMethod().equals(HideReturnsTransformationMethod.getInstance()))
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
                mEmail.setError(getResources().getString(R.string.email_is_required));
                return;
            }
            if (TextUtils.isEmpty(password)) {
                mPassword.setError(getResources().getString(R.string.password_is_required));
                return;
            }
            if (password.length() < 6) {
                mPassword.setError(getResources().getString(R.string.password_contain_characters));
                return;
            }
            FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    Toast.makeText(Login.this, getResources().getString(R.string.logged_in_successfully),
                            Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                } else
                    Toast.makeText(Login.this, (getResources().getString(R.string.error))
                                    + Objects.requireNonNull(task.getException()).getMessage(),
                            Toast.LENGTH_SHORT).show();
            });
        });

        mCreateBtn.setOnClickListener(v ->
        {
            Intent intent = new Intent(getApplicationContext(), Registration.class);
            startActivity(intent);
        });
        forgotTextLink.setOnClickListener(v -> {
            final EditText resetMail = new EditText(v.getContext());
            final AlertDialog.Builder passwordResetDialog = new AlertDialog.Builder(v.getContext());
            passwordResetDialog.setTitle(getResources().getString(R.string.reset_password));
            passwordResetDialog.setMessage(getResources().getString(R.string.reset_link));
            passwordResetDialog.setView(resetMail);

            passwordResetDialog.setPositiveButton(getResources().getString(R.string.yes), (dialog, which) -> {
                String mail = resetMail.getText().toString();
                FirebaseAuth.getInstance().sendPasswordResetEmail(mail).addOnSuccessListener(aVoid ->
                        Toast.makeText(this, getResources().getString(R.string.link_sent),
                                Toast.LENGTH_SHORT).show()).addOnFailureListener(e ->
                        Toast.makeText(this, (getResources().getString(R.string.error_link)) + e.getMessage(),
                                Toast.LENGTH_SHORT).show());

            });

            passwordResetDialog.setNegativeButton(getResources().getString(R.string.no), (dialog, which) -> {
            });
            passwordResetDialog.create().show();
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseAuth.getInstance().addAuthStateListener(firebaseAuthListener);
    }

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

    @SuppressWarnings("deprecation")
    private void changeLocale(Locale locale) {
        Locale.setDefault(locale);
        Configuration configuration = new Configuration();
        configuration.setLocale(locale);
        getBaseContext().getResources()
                .updateConfiguration(configuration,
                        getBaseContext()
                                .getResources()
                                .getDisplayMetrics());
        setTitle(R.string.app_name);
        final EditText mEmail = findViewById(R.id.Email);
        mEmail.setHint(R.string.email);
        final EditText mPassword = findViewById(R.id.password);
        mPassword.setHint(R.string.password);
        final Button mRegisterBtn = findViewById(R.id.loginBtn);
        mRegisterBtn.setText(R.string.login);
        final TextView newAccount = findViewById(R.id.createText);
        newAccount.setText(R.string.create_a_new_account);
        final TextView login = findViewById(R.id.textView);
        login.setText(R.string.log_in_to_vo_yk);
        final TextView forgotPassword = findViewById(R.id.forgotPassword);
        forgotPassword.setText(R.string.forgot_password);

    }
}