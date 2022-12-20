package com.application.vozyk.ui.settings;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.application.vozyk.R;
import com.application.vozyk.ui.login.Login;
import com.firebase.ui.auth.AuthUI;

public class Settings extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        getSupportActionBar().hide();
        View editLanguage = findViewById(R.id.cl_edit_language);
        View changePassword = findViewById(R.id.cl_change_password);
        View changeEmail = findViewById(R.id.cl_change_email);
        View logout = findViewById(R.id.cl_logout);
        View deleteAccount = findViewById(R.id.cl_delete_account);

        logout.setOnClickListener(v -> {
            final AlertDialog.Builder b = new AlertDialog.Builder(this);
            b.setMessage(getResources().getString(R.string.exit));
            b.setCancelable(true);
            b.setNegativeButton(getResources().getString(R.string.yes), (dialog, which) -> {
                dialog.cancel();
                AuthUI.getInstance()
                        .signOut(this)
                        .addOnCompleteListener(task -> startActivity(new Intent(this, Login.class)));
            });
            b.setPositiveButton(getResources().getString(R.string.no), (dialog, which) -> {
            });
            AlertDialog a = b.create();
            a.show();
        });
        deleteAccount.setOnClickListener(v -> startActivity(new Intent(this, DeleteAccount.class)));
        changePassword.setOnClickListener(v -> startActivity(new Intent(this, ChangePassword.class)));
        changeEmail.setOnClickListener(v -> startActivity(new Intent(this, ChangeEmail.class)));
        editLanguage.setOnClickListener(v -> startActivity(new Intent(this, ChangeLanguage.class)));
    }
}
