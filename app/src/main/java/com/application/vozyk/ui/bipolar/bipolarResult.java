package com.application.vozyk.ui.bipolar;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.application.vozyk.R;
import com.application.vozyk.ui.quiz.QuizActivity;


public class bipolarResult extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_bipolar_result);
        getSupportActionBar().hide();

        TextView mResult = findViewById(R.id.results);
        Button mRetry =  findViewById(R.id.redo);
        ImageView back = findViewById(R.id.back);
        back.setOnClickListener(v -> startActivity(new Intent(this, QuizActivity.class)));
        Bundle b = getIntent().getExtras();
        int points = b.getInt("points");

        if (points >= 0 && points <= 15){
            mResult.setText(getResources().getString(R.string.symptoms_bip_no));
        }

        if (points >= 16 && points <= 24){
            mResult.setText(getResources().getString(R.string.symptoms_bip_maybe));
        }

        if (points >= 25){
            mResult.setText(getResources().getString(R.string.symptoms_bip_yes));
        }

        mRetry.setOnClickListener(v -> {
            Intent i = new Intent(getApplicationContext(), Bipolar.class);
            startActivity(i);
        });
    }
}
