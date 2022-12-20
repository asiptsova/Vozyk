package com.application.vozyk.ui.depression;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.content.Intent;

import com.application.vozyk.R;
import com.application.vozyk.ui.quiz.QuizActivity;


public class DepressionResult extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_depression_result);
        getSupportActionBar().hide();

        TextView result = findViewById(R.id.results);
        Button retry = findViewById(R.id.redo);
        ImageView back = findViewById(R.id.back);
        back.setOnClickListener(v -> startActivity(new Intent(this, QuizActivity.class)));
        Bundle b = getIntent().getExtras();
        int points = b.getInt("points");

        if (points >= 0 && points <= 4)
            result.setText(getResources().getString(R.string.dep1));
        else if (points >= 5 && points <= 9)
            result.setText(getResources().getString(R.string.dep2));
        else if (points >= 10 && points <= 14)
            result.setText(getResources().getString(R.string.dep3));
        else if (points >= 15 && points <= 19)
            result.setText(getResources().getString(R.string.dep4));
        else if (points >= 20 && points <= 27)
            result.setText(getResources().getString(R.string.dep5));

        retry.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), Depression.class)));
    }
}
