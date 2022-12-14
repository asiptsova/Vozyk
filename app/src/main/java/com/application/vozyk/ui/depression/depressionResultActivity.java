package com.application.vozyk.ui.depression;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.content.Intent;

import com.application.vozyk.R;
import com.application.vozyk.ui.quiz.QuizActivity;


public class depressionResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_depression_result);
        getSupportActionBar().hide();

        TextView mResult =  findViewById(R.id.results);
        Button mRetry = findViewById(R.id.redo);
        ImageView back = findViewById(R.id.back);
        back.setOnClickListener(v -> startActivity(new Intent(this, QuizActivity.class)));
        Bundle b = getIntent().getExtras();
        int points = b.getInt("points");

        if (points >= 0 && points <= 4){
            mResult.setText("The level of your depression is None to Minimal");
        }
        if (points >= 5 && points <= 9){
            mResult.setText("The level of your depression is Mild");
        }
        if (points >= 10 && points <= 14){
            mResult.setText("The level of your depression is Moderate");
        }
        if (points >= 15 && points <= 19){
            mResult.setText("The level of your depression is Moderately severe");
        }
        if (points >= 20 && points <= 27){
            mResult.setText("The level of your depression is Severe");
        }

        mRetry.setOnClickListener(v -> {
            Intent i = new Intent(getApplicationContext(), depressionActivity.class);
            startActivity(i);
        });
    }
}
