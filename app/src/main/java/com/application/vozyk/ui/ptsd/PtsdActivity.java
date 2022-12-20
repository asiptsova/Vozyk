package com.application.vozyk.ui.ptsd;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.content.Intent;

import com.application.vozyk.R;


public class PtsdActivity extends AppCompatActivity {

    private TextView question;
    private int number =0,score =0;
    public  String[] ques;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ptsd_2);
        getSupportActionBar().hide();
        ques =new String[]{
                getResources().getString(R.string.ptsd1),
                getResources().getString(R.string.ptsd2),
                getResources().getString(R.string.ptsd3),
                getResources().getString(R.string.ptsd4),
                getResources().getString(R.string.ptsd5)
        };

        question =findViewById(R.id.quesP);
        Button yes =findViewById(R.id.yesP);
        Button no = findViewById(R.id.noP);

        updateQuestion();

        yes.setOnClickListener(v -> {
            score = score + 1;
            if (number == getLength())
                updateResult();
             else
                updateQuestion();
        });

        no.setOnClickListener(v -> {
            if (number == getLength())
                updateResult();
             else
                updateQuestion();
        });


    }

    private void updateQuestion(){
        question.setText(getQuestion(number));
        number++;
    }

    private void updateResult(){
        Intent i = new Intent(getApplicationContext(), PtsdResult.class);
        Bundle b = new Bundle();
        b.putInt("score", score);
        i.putExtras(b);
        PtsdActivity.this.finish();
        startActivity(i);
    }


    public String getQuestion(int i) {
        return ques[i];
    }

    public int getLength(){
        return ques.length;
    }
}
