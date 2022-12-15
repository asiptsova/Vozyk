package com.application.vozyk.ui.adhd;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.application.vozyk.R;


public class adhdActivity extends AppCompatActivity {

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
        setContentView(R.layout.activity_adhd);
        getSupportActionBar().hide();

        mQues =new String[] {
                getResources().getString(R.string.adhd1),
                getResources().getString(R.string.adhd2),
                getResources().getString(R.string.adhd3),
                getResources().getString(R.string.adhd4),
                getResources().getString(R.string.adhd5),
                getResources().getString(R.string.adhd6),
        };

        mChoices= new String[][]{
                {getResources().getString(R.string.never),getResources().getString(R.string.rarely), getResources().getString(R.string.sometimes),getResources().getString(R.string.often),getResources().getString(R.string.very_often)},
                {getResources().getString(R.string.never), getResources().getString(R.string.rarely), getResources().getString(R.string.sometimes),getResources().getString(R.string.often), getResources().getString(R.string.very_often)},
                {getResources().getString(R.string.never), getResources().getString(R.string.rarely), getResources().getString(R.string.sometimes), getResources().getString(R.string.often), getResources().getString(R.string.very_often)},
                {getResources().getString(R.string.never), getResources().getString(R.string.rarely), getResources().getString(R.string.sometimes), getResources().getString(R.string.often), getResources().getString(R.string.very_often)},
                {getResources().getString(R.string.never), getResources().getString(R.string.rarely), getResources().getString(R.string.sometimes),getResources().getString(R.string.often),getResources().getString(R.string.very_often)},
                {getResources().getString(R.string.never), getResources().getString(R.string.rarely), getResources().getString(R.string.sometimes),getResources().getString(R.string.often), getResources().getString(R.string.very_often)},
        };

        mQuesView = findViewById(R.id.questions);
        mChoiceA = findViewById(R.id.choiceA);
        mChoiceB = findViewById(R.id.choiceB);
        mChoiceC = findViewById(R.id.choiceC);
        mChoiceD = findViewById(R.id.choiceD);
        mChoiceE = findViewById(R.id.choiceE);

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
        Intent i = new Intent(getApplicationContext(), adhdResultActivity.class);
        Bundle b = new Bundle();
        b.putInt("points",mPoint);
        i.putExtras(b);
        com.application.vozyk.ui.adhd.adhdActivity.this.finish();
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
