package com.application.vozyk.ui.ptsd;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.content.Intent;

import com.application.vozyk.R;


public class ptsdActivity extends AppCompatActivity {

    private final ptsdQuestionModel mQues = new ptsdQuestionModel();
    private TextView mQuesView;
    private int mQuesNum=0;
    private int mScore=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ptsd_2);
        getSupportActionBar().hide();

        mQuesView =findViewById(R.id.quesP);
        Button yesB =findViewById(R.id.yesP);
        Button noB = findViewById(R.id.noP);

        updateQuestion();

        yesB.setOnClickListener(v -> {
            mScore = mScore + 1;
            if (mQuesNum == mQues.getLength()) {
                updateResult();
            } else {
                updateQuestion();
            }
        });

        noB.setOnClickListener(v -> {
            if (mQuesNum == mQues.getLength()){
                updateResult();
            } else {
                updateQuestion();
            }
        });


    }

    private void updateQuestion(){
        mQuesView.setText(mQues.getQuestion(mQuesNum));
        mQuesNum++;
    }

    private void updateResult(){
        Intent i = new Intent(getApplicationContext(), ptsdResultActivity.class);
        Bundle b = new Bundle();
        b.putInt("score",mScore);
        i.putExtras(b);
        ptsdActivity.this.finish();
        startActivity(i);
    }
}
