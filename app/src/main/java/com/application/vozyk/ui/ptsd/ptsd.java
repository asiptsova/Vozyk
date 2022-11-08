package com.application.vozyk.ui.ptsd;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.content.Intent;

import com.application.vozyk.R;


public class ptsd extends AppCompatActivity {


    private int score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ptsd_1);

        Button yes_b =  findViewById(R.id.yes);
        Button no_b = findViewById(R.id.no);
        score = 0;

        yes_b.setOnClickListener(v -> {
            Intent i = new Intent(getApplicationContext(), ptsdActivity.class);
            startActivity(i);
        });

        no_b.setOnClickListener(v -> {
            Intent i = new Intent(getApplicationContext(), ptsdResultActivity.class);
            Bundle b = new Bundle();
            b.putInt("score",score);
            i.putExtras(b);
            ptsd.this.finish();
            startActivity(i);
        });

    }
}
