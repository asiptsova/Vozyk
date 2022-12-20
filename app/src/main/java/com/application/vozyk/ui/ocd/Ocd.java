package com.application.vozyk.ui.ocd;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.application.vozyk.R;


public class Ocd extends AppCompatActivity {

    private TextView mQuesView;
    private Button mChoiceA;
    private Button mChoiceB;
    private Button mChoiceC;
    private Button mChoiceD;
    private Button mChoiceE;
    private int mPoint = 0;
    private int mQuesNumber = 0;
    public  String[] mQues;


    private  String[][] mChoices;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ocd);
        getSupportActionBar().hide();

        mQues =new String[] {
                getResources().getString(R.string.ocd1),
                getResources().getString(R.string.ocd2),
                getResources().getString(R.string.ocd3),
                getResources().getString(R.string.ocd4),
                getResources().getString(R.string.ocd5),
                getResources().getString(R.string.ocd6),
                getResources().getString(R.string.ocd7),
                getResources().getString(R.string.ocd8),
                getResources().getString(R.string.ocd9),
                getResources().getString(R.string.ocd10),
                getResources().getString(R.string.ocd11),
                getResources().getString(R.string.ocd12),
                getResources().getString(R.string.ocd13),
                getResources().getString(R.string.ocd14),
                getResources().getString(R.string.ocd15),
                getResources().getString(R.string.ocd16),
                getResources().getString(R.string.ocd17),
                getResources().getString(R.string.ocd18),
        };

        mChoices = new String[][] {
                {getResources().getString(R.string.not_at_all),getResources().getString(R.string.a_little), getResources().getString(R.string.moderately), getResources().getString(R.string.a_lot),getResources().getString(R.string.extremely)},
                {getResources().getString(R.string.not_at_all),getResources().getString(R.string.a_little), getResources().getString(R.string.moderately), getResources().getString(R.string.a_lot),getResources().getString(R.string.extremely)},
                {getResources().getString(R.string.not_at_all),getResources().getString(R.string.a_little), getResources().getString(R.string.moderately), getResources().getString(R.string.a_lot),getResources().getString(R.string.extremely)},
                {getResources().getString(R.string.not_at_all),getResources().getString(R.string.a_little), getResources().getString(R.string.moderately), getResources().getString(R.string.a_lot),getResources().getString(R.string.extremely)},
                {getResources().getString(R.string.not_at_all),getResources().getString(R.string.a_little), getResources().getString(R.string.moderately), getResources().getString(R.string.a_lot),getResources().getString(R.string.extremely)},
                {getResources().getString(R.string.not_at_all),getResources().getString(R.string.a_little), getResources().getString(R.string.moderately), getResources().getString(R.string.a_lot),getResources().getString(R.string.extremely)},
                {getResources().getString(R.string.not_at_all),getResources().getString(R.string.a_little), getResources().getString(R.string.moderately), getResources().getString(R.string.a_lot),getResources().getString(R.string.extremely)},
                {getResources().getString(R.string.not_at_all),getResources().getString(R.string.a_little), getResources().getString(R.string.moderately), getResources().getString(R.string.a_lot),getResources().getString(R.string.extremely)},
                {getResources().getString(R.string.not_at_all),getResources().getString(R.string.a_little), getResources().getString(R.string.moderately), getResources().getString(R.string.a_lot),getResources().getString(R.string.extremely)},
                {getResources().getString(R.string.not_at_all),getResources().getString(R.string.a_little), getResources().getString(R.string.moderately), getResources().getString(R.string.a_lot),getResources().getString(R.string.extremely)},
                {getResources().getString(R.string.not_at_all),getResources().getString(R.string.a_little), getResources().getString(R.string.moderately), getResources().getString(R.string.a_lot),getResources().getString(R.string.extremely)},
                {getResources().getString(R.string.not_at_all),getResources().getString(R.string.a_little), getResources().getString(R.string.moderately), getResources().getString(R.string.a_lot),getResources().getString(R.string.extremely)},
                {getResources().getString(R.string.not_at_all),getResources().getString(R.string.a_little), getResources().getString(R.string.moderately), getResources().getString(R.string.a_lot),getResources().getString(R.string.extremely)},
                {getResources().getString(R.string.not_at_all),getResources().getString(R.string.a_little), getResources().getString(R.string.moderately), getResources().getString(R.string.a_lot),getResources().getString(R.string.extremely)},
                {getResources().getString(R.string.not_at_all),getResources().getString(R.string.a_little), getResources().getString(R.string.moderately), getResources().getString(R.string.a_lot),getResources().getString(R.string.extremely)},
                {getResources().getString(R.string.not_at_all),getResources().getString(R.string.a_little), getResources().getString(R.string.moderately), getResources().getString(R.string.a_lot),getResources().getString(R.string.extremely)},
                {getResources().getString(R.string.not_at_all),getResources().getString(R.string.a_little), getResources().getString(R.string.moderately), getResources().getString(R.string.a_lot),getResources().getString(R.string.extremely)},
                {getResources().getString(R.string.not_at_all),getResources().getString(R.string.a_little), getResources().getString(R.string.moderately), getResources().getString(R.string.a_lot),getResources().getString(R.string.extremely)},


        };

        mQuesView =  findViewById(R.id.questions);
        mChoiceA =  findViewById(R.id.choiceA);
        mChoiceB =  findViewById(R.id.choiceB);
        mChoiceC = findViewById(R.id.choiceC);
        mChoiceD = findViewById(R.id.choiceD);
        mChoiceE =  findViewById(R.id.choiceE);

        updateQuestion();

        mChoiceA.setOnClickListener(view -> {
            if (mQuesNumber == getLength()){
                updateResult();
            } else {
                updateQuestion();
            }
        });

        mChoiceB.setOnClickListener(view -> {
            mPoint = mPoint + 1;
            if (mQuesNumber == getLength()){
                updateResult();
            } else {
                updateQuestion();
            }
        });

        mChoiceC.setOnClickListener(view -> {
            mPoint = mPoint + 2;
            if (mQuesNumber == getLength()){
                updateResult();
            } else {
                updateQuestion();
            }
        });

        mChoiceD.setOnClickListener(view -> {
            mPoint = mPoint + 3;
            if (mQuesNumber == getLength()){
                updateResult();
            } else {
                updateQuestion();
            }
        });

        mChoiceE.setOnClickListener(view -> {
            mPoint = mPoint + 4;
            if (mQuesNumber == getLength()){
                updateResult();
            } else {
                updateQuestion();
            }
        });

    }

    private void updateQuestion(){
        mQuesView.setText(getQuestion(mQuesNumber));
        mChoiceA.setText(getChoiceA(mQuesNumber));
        mChoiceB.setText(getChoiceB(mQuesNumber));
        mChoiceC.setText(getChoiceC(mQuesNumber));
        mChoiceD.setText(getChoiceD(mQuesNumber));
        mChoiceE.setText(getChoiceE(mQuesNumber));

        mQuesNumber++;
    }

    private void updateResult(){
        Intent i = new Intent(getApplicationContext(), OcdResult.class);
        Bundle b = new Bundle();
        b.putInt("points",mPoint);
        i.putExtras(b);
        Ocd.this.finish();
        startActivity(i);
    }
    public String getQuestion(int i){
        return mQues[i];
    }

    public String getChoiceA(int i){
        return mChoices[i][0];
    }

    public String getChoiceB(int i){
        return mChoices[i][1];
    }

    public String getChoiceC(int i){
        return mChoices[i][2];
    }

    public String getChoiceD(int i){
        return mChoices[i][3];
    }

    public String getChoiceE(int i){
        return mChoices[i][4];
    }

    public int getLength(){
        return mQues.length;
    }
}
