package com.application.vozyk.ui.bipolar;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.application.vozyk.R;


public class bipolarActivity extends AppCompatActivity {

    private TextView mQuesView;
    private Button mChoiceA;
    private Button mChoiceB;
    private Button mChoiceC;
    private Button mChoiceD;
    private Button mChoiceE;
    private Button mChoiceF;
    private int mPoint = 0;
    private int mQuesNumber = 0;
    public String[] mQues;
    private String[][] mChoices;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bipolar);
        getSupportActionBar().hide();

        mQues =new String[] {
                getResources().getString(R.string.bip1),
                getResources().getString(R.string.bip2),
                getResources().getString(R.string.bip3),
                getResources().getString(R.string.bip4),
                getResources().getString(R.string.bip5),
                getResources().getString(R.string.bip6),
                getResources().getString(R.string.bip7),
                getResources().getString(R.string.bip8),
                getResources().getString(R.string.bip9),
                getResources().getString(R.string.bip10),
                getResources().getString(R.string.bip11),
                getResources().getString(R.string.bip12)
        };
        mChoices = new String[][] {
                {getResources().getString(R.string.not_at_all),getResources().getString(R.string.a_little),getResources().getString(R.string.somewhat),getResources().getString(R.string.moderately),getResources().getString(R.string.quite_a_lot),getResources().getString(R.string.very_much)},
                {getResources().getString(R.string.not_at_all),getResources().getString(R.string.a_little),getResources().getString(R.string.somewhat),getResources().getString(R.string.moderately),getResources().getString(R.string.quite_a_lot),getResources().getString(R.string.very_much)},
                {getResources().getString(R.string.not_at_all),getResources().getString(R.string.a_little),getResources().getString(R.string.somewhat),getResources().getString(R.string.moderately),getResources().getString(R.string.quite_a_lot),getResources().getString(R.string.very_much)},
                {getResources().getString(R.string.not_at_all),getResources().getString(R.string.a_little),getResources().getString(R.string.somewhat),getResources().getString(R.string.moderately),getResources().getString(R.string.quite_a_lot),getResources().getString(R.string.very_much)},
                {getResources().getString(R.string.not_at_all),getResources().getString(R.string.a_little),getResources().getString(R.string.somewhat),getResources().getString(R.string.moderately),getResources().getString(R.string.quite_a_lot),getResources().getString(R.string.very_much)},
                {getResources().getString(R.string.not_at_all),getResources().getString(R.string.a_little),getResources().getString(R.string.somewhat),getResources().getString(R.string.moderately),getResources().getString(R.string.quite_a_lot),getResources().getString(R.string.very_much)},
                {getResources().getString(R.string.not_at_all),getResources().getString(R.string.a_little),getResources().getString(R.string.somewhat),getResources().getString(R.string.moderately),getResources().getString(R.string.quite_a_lot),getResources().getString(R.string.very_much)},
                {getResources().getString(R.string.not_at_all),getResources().getString(R.string.a_little),getResources().getString(R.string.somewhat),getResources().getString(R.string.moderately),getResources().getString(R.string.quite_a_lot),getResources().getString(R.string.very_much)},
                {getResources().getString(R.string.not_at_all),getResources().getString(R.string.a_little),getResources().getString(R.string.somewhat),getResources().getString(R.string.moderately),getResources().getString(R.string.quite_a_lot),getResources().getString(R.string.very_much)},
                {getResources().getString(R.string.not_at_all),getResources().getString(R.string.a_little),getResources().getString(R.string.somewhat),getResources().getString(R.string.moderately),getResources().getString(R.string.quite_a_lot),getResources().getString(R.string.very_much)},
                {getResources().getString(R.string.not_at_all),getResources().getString(R.string.a_little),getResources().getString(R.string.somewhat),getResources().getString(R.string.moderately),getResources().getString(R.string.quite_a_lot),getResources().getString(R.string.very_much)},
                {getResources().getString(R.string.not_at_all),getResources().getString(R.string.a_little),getResources().getString(R.string.somewhat),getResources().getString(R.string.moderately),getResources().getString(R.string.quite_a_lot),getResources().getString(R.string.very_much)},
        };


        mQuesView = findViewById(R.id.questions);
        mChoiceA =  findViewById(R.id.choiceA);
        mChoiceB =  findViewById(R.id.choiceB);
        mChoiceC = findViewById(R.id.choiceC);
        mChoiceD =  findViewById(R.id.choiceD);
        mChoiceE =  findViewById(R.id.choiceE);
        mChoiceF =findViewById(R.id.choiceF);

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
            if (mQuesNumber ==getLength()){
                updateResult();
            } else {
                updateQuestion();
            }
        });

        mChoiceF.setOnClickListener(view -> {
            mPoint = mPoint + 5;
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
        mChoiceF.setText(getChoiceF(mQuesNumber));

        mQuesNumber++;
    }

    private void updateResult(){
        Intent i = new Intent(getApplicationContext(), bipolarResultActivity.class);
        Bundle b = new Bundle();
        b.putInt("points",mPoint);
        i.putExtras(b);
        bipolarActivity.this.finish();
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

    public String getChoiceF(int i){
        return mChoices[i][5];
    }

    public int getLength(){
        return mQues.length;
    }
}
