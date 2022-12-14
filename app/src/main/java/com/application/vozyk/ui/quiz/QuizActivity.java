package com.application.vozyk.ui.quiz;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.application.vozyk.R;
import com.application.vozyk.ui.adhd.adhdActivity;
import com.application.vozyk.ui.anxiety.anxietyActivity;
import com.application.vozyk.ui.bipolar.bipolarActivity;
import com.application.vozyk.ui.depression.depressionActivity;
import com.application.vozyk.ui.ocd.ocdActivity;
import com.application.vozyk.ui.ptsd.ptsdActivity;


public class QuizActivity extends AppCompatActivity {

    Button adhd;
    Button anxiety;
    Button depression;
    Button ocd;
    Button ptsd;
    Button bipolar;
    LinearLayout linearLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_quiz);
        getSupportActionBar().hide();
        linearLayout = findViewById(R.id.quiz_layout);
        linearLayout.getBackground().setAlpha(80);

        adhd = findViewById(R.id.button_adhd);
        adhd.getBackground().setAlpha(180);

        anxiety = findViewById(R.id.button_anxiety);
        anxiety.getBackground().setAlpha(180);

        depression = findViewById(R.id.button_depression);
        depression.getBackground().setAlpha(180);

        ocd = findViewById(R.id.button_ocd);
        ocd.getBackground().setAlpha(180);

        ptsd = findViewById(R.id.button_ptsd);
        ptsd.getBackground().setAlpha(180);

        bipolar = findViewById(R.id.button_bipolar);
        bipolar.getBackground().setAlpha(180);
    }

    public void adhd_test(View view){
        Intent i_adhd = new Intent(this, adhdActivity.class);
        startActivity(i_adhd);
    }

    public void anxiety_test(View view){
        Intent i_anxiety = new Intent(this, anxietyActivity.class);
        startActivity(i_anxiety);
    }

    public void bipolar_test(View view){
        Intent i_bipolar = new Intent(this, bipolarActivity.class);
        startActivity(i_bipolar);
    }

    public void depression_test(View view){
        Intent i_dep = new Intent(this, depressionActivity.class);
        startActivity(i_dep);
    }

    public void ocd_test(View view){
        Intent i_ocd = new Intent(this, ocdActivity.class);
        startActivity(i_ocd);
    }

    public void ptsd_test(View view){
        Intent i_ptsd = new Intent(this, ptsdActivity.class);
        startActivity(i_ptsd);
    }

}
