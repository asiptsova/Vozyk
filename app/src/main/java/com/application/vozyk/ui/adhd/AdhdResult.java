package com.application.vozyk.ui.adhd;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.application.vozyk.R;
import com.application.vozyk.ui.quiz.QuizActivity;

public class AdhdResult extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adhd_result);
        getSupportActionBar().hide();


        TextView result = findViewById(R.id.results);
        Button retry =findViewById(R.id.redo);
        ImageView  back = findViewById(R.id.back);
        back.setOnClickListener(v -> startActivity(new Intent(this, QuizActivity.class)));
        Bundle b = getIntent().getExtras();
        int points = b.getInt("points");

        if (points >= 0 && points < 4)
            result.setText(getResources().getString(R.string.symptoms_ADHD_no));
        else if (points >= 4)
            result.setText(getResources().getString(R.string.symptoms_ADHD_no));
        retry.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), Adhd.class)));
    }
}
