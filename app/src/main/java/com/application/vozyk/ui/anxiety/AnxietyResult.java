package com.application.vozyk.ui.anxiety;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Button;
import android.content.Intent;
import com.application.vozyk.R;
import com.application.vozyk.ui.quiz.QuizActivity;

public class AnxietyResult extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anxiety_results);
        getSupportActionBar().hide();

        TextView result =findViewById(R.id.result);
        Button retry =  findViewById(R.id.retry);
        ImageView back = findViewById(R.id.back);
        back.setOnClickListener(v -> startActivity(new Intent(this, QuizActivity.class)));
        Bundle b = getIntent().getExtras();
        int points = b.getInt("points");

        if (points >= 0 && points <= 9)
            result.setText(getResources().getString(R.string.level_anxiety_none_mild));
        if (points >= 10 && points <= 14)
            result.setText(getResources().getString(R.string.level_anxiety_none_moderate));
        if (points >= 15 && points <= 21)
            result.setText(getResources().getString(R.string.level_anxiety_none_severe));

        retry.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), Anxiety.class)));
    }
}
