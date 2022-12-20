package com.application.vozyk.ui.ptsd;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.content.Intent;

import com.application.vozyk.R;
import com.application.vozyk.ui.quiz.QuizActivity;


public class PtsdResult extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ptsd_results_2);
        getSupportActionBar().hide();

        TextView results = findViewById(R.id.resultP);
        Button retry = findViewById(R.id.retryP);
        ImageView back = findViewById(R.id.back);
        back.setOnClickListener(v -> startActivity(new Intent(this, QuizActivity.class)));
        int score = getIntent().getExtras().getInt("score");

        if (score >= 3)
            results.setText(getResources().getString(R.string.symptoms_ptsd_yes));
         else
            results.setText(getResources().getString(R.string.symptoms_ptsd_no));

        retry.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), Ptsd.class)));
    }
}
