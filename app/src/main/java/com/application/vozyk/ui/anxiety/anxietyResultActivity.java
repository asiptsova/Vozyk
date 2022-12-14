package com.application.vozyk.ui.anxiety;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Button;
import android.content.Intent;
import com.application.vozyk.R;
import com.application.vozyk.ui.quiz.QuizActivity;

public class anxietyResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anxiety_results);
        getSupportActionBar().hide();

        TextView mResult =findViewById(R.id.result);
        Button mRetry =  findViewById(R.id.retry);
        ImageView back = findViewById(R.id.back);
        back.setOnClickListener(v -> startActivity(new Intent(this, QuizActivity.class)));
        Bundle b = getIntent().getExtras();
        int points = b.getInt("points");

        if (points >= 0 && points <= 9){
            mResult.setText("The level of your anxiety is none to mild");
        }
        if (points >= 10 && points <= 14){
            mResult.setText("The level of your anxiety is moderate");
        }
        if (points >= 15 && points <= 21){
            mResult.setText("The level of your anxiety is severe");
        }

        mRetry.setOnClickListener(v -> {
            Intent i = new Intent(getApplicationContext(), anxietyActivity.class);
            startActivity(i);
        });
    }
}
