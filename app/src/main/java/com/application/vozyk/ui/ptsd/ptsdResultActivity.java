package com.application.vozyk.ui.ptsd;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.content.Intent;

import com.application.vozyk.R;


public class ptsdResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ptsd_results_2);

        TextView mResults = findViewById(R.id.resultP);
        Button mRetry = findViewById(R.id.retryP);

        Bundle b = getIntent().getExtras();
        int score = b.getInt("score");

        if (score >= 3){
            mResults.setText("You are likely to be experiencing ptsd");
        } else {
            mResults.setText("You are not likely to be experiencing ptsd");
        }

        mRetry.setOnClickListener(v -> {
            Intent i = new Intent(getApplicationContext(), ptsd.class);
            startActivity(i);
        });
    }
}