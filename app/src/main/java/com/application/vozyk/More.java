package com.application.vozyk;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.firebase.ui.auth.AuthUI;


public class More extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more);
        final Button account=findViewById(R.id.account);
        final Button pills=findViewById(R.id.pills);
        final Button about=findViewById(R.id.about_us);
        final Button logout=findViewById(R.id.logout);
        final Button settings=findViewById(R.id.settings);


        logout.setOnClickListener(v -> AuthUI.getInstance()
                .signOut(More.this)
                .addOnCompleteListener(task -> {
                    startActivity(new Intent(More.this, Login.class));
                    finish();
                }));
        account.setOnClickListener(v -> {
            Intent intent = new Intent(More.this, Account.class);
            startActivity(intent);

        });
        pills.setOnClickListener(v -> {
            Intent intent = new Intent(More.this, Pills.class);
            startActivity(intent);

        });
        about.setOnClickListener(v -> {
            Intent intent = new Intent(More.this, About_us.class);
            startActivity(intent);

        });
        settings.setOnClickListener(v -> {
            Intent intent = new Intent(More.this, Settings.class);
            startActivity(intent);

        });
    }
}