package com.application.vozyk.ui.ocd;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.application.vozyk.R;


public class ocdActivity extends AppCompatActivity {

    private final ocdQuestionModel mQues = new ocdQuestionModel();
    private TextView mQuesView;
    private Button mChoiceA;
    private Button mChoiceB;
    private Button mChoiceC;
    private Button mChoiceD;
    private Button mChoiceE;
    private int mPoint = 0;
    private int mQuesNumber = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ocd);

        mQuesView =  findViewById(R.id.questions);
        mChoiceA =  findViewById(R.id.choiceA);
        mChoiceB =  findViewById(R.id.choiceB);
        mChoiceC = findViewById(R.id.choiceC);
        mChoiceD = findViewById(R.id.choiceD);
        mChoiceE =  findViewById(R.id.choiceE);

        updateQuestion();

        mChoiceA.setOnClickListener(view -> {
            if (mQuesNumber == mQues.getLength()){
                updateResult();
            } else {
                updateQuestion();
            }
        });

        mChoiceB.setOnClickListener(view -> {
            mPoint = mPoint + 1;
            if (mQuesNumber == mQues.getLength()){
                updateResult();
            } else {
                updateQuestion();
            }
        });

        mChoiceC.setOnClickListener(view -> {
            mPoint = mPoint + 2;
            if (mQuesNumber == mQues.getLength()){
                updateResult();
            } else {
                updateQuestion();
            }
        });

        mChoiceD.setOnClickListener(view -> {
            mPoint = mPoint + 3;
            if (mQuesNumber == mQues.getLength()){
                updateResult();
            } else {
                updateQuestion();
            }
        });

        mChoiceE.setOnClickListener(view -> {
            mPoint = mPoint + 4;
            if (mQuesNumber == mQues.getLength()){
                updateResult();
            } else {
                updateQuestion();
            }
        });

    }

    private void updateQuestion(){
        mQuesView.setText(mQues.getQuestion(mQuesNumber));
        mChoiceA.setText(mQues.getChoiceA(mQuesNumber));
        mChoiceB.setText(mQues.getChoiceB(mQuesNumber));
        mChoiceC.setText(mQues.getChoiceC(mQuesNumber));
        mChoiceD.setText(mQues.getChoiceD(mQuesNumber));
        mChoiceE.setText(mQues.getChoiceE(mQuesNumber));

        mQuesNumber++;
    }

    private void updateResult(){
        Intent i = new Intent(getApplicationContext(), ocdResultActivity.class);
        Bundle b = new Bundle();
        b.putInt("points",mPoint);
        i.putExtras(b);
        ocdActivity.this.finish();
        startActivity(i);
    }
}
