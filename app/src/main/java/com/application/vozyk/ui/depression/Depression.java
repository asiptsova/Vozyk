package com.application.vozyk.ui.depression;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.content.Intent;
import com.application.vozyk.R;

public class Depression extends AppCompatActivity {

    private TextView mQuesView;
    private Button mChoice1;
    private Button mChoice2;
    private Button mChoice3;
    private Button mChoice4;
    private int mPoint = 0;
    private int mQuesNumber = 0;
    private String[][] mChoices;
    public String[] mQues;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_depression);
        getSupportActionBar().hide();

        mQues = new  String[]{
                getResources().getString(R.string.depression1),
                getResources().getString(R.string.depression2),
                getResources().getString(R.string.depression3),
                getResources().getString(R.string.depression4),
                getResources().getString(R.string.depression5),
                getResources().getString(R.string.depression6),
                getResources().getString(R.string.depression7),
                getResources().getString(R.string.depression8),
                getResources().getString(R.string.depression9),
        };


        mChoices = new String[][] {
                {getResources().getString(R.string.not_at_all_sure),getResources().getString(R.string.several_days),getResources().getString(R.string.over_half_the_days),getResources().getString(R.string.nearly_every_day)},
                {getResources().getString(R.string.not_at_all_sure),getResources().getString(R.string.several_days),getResources().getString(R.string.over_half_the_days),getResources().getString(R.string.nearly_every_day)},
                {getResources().getString(R.string.not_at_all_sure),getResources().getString(R.string.several_days),getResources().getString(R.string.over_half_the_days),getResources().getString(R.string.nearly_every_day)},
                {getResources().getString(R.string.not_at_all_sure),getResources().getString(R.string.several_days),getResources().getString(R.string.over_half_the_days),getResources().getString(R.string.nearly_every_day)},
                {getResources().getString(R.string.not_at_all_sure),getResources().getString(R.string.several_days),getResources().getString(R.string.over_half_the_days),getResources().getString(R.string.nearly_every_day)},
                {getResources().getString(R.string.not_at_all_sure),getResources().getString(R.string.several_days),getResources().getString(R.string.over_half_the_days),getResources().getString(R.string.nearly_every_day)},
                {getResources().getString(R.string.not_at_all_sure),getResources().getString(R.string.several_days),getResources().getString(R.string.over_half_the_days),getResources().getString(R.string.nearly_every_day)},
                {getResources().getString(R.string.not_at_all_sure),getResources().getString(R.string.several_days),getResources().getString(R.string.over_half_the_days),getResources().getString(R.string.nearly_every_day)},
                {getResources().getString(R.string.not_at_all_sure),getResources().getString(R.string.several_days),getResources().getString(R.string.over_half_the_days),getResources().getString(R.string.nearly_every_day)}
        };
        mQuesView =  findViewById(R.id.questions);
        mChoice1 =  findViewById(R.id.choiceA);
        mChoice2 =  findViewById(R.id.choiceB);
        mChoice3 = findViewById(R.id.choiceC);
        mChoice4 = findViewById(R.id.choiceD);

        updateQuestion();

        mChoice1.setOnClickListener(view -> {
            if (mQuesNumber == getLength()){
                updateResult();
            } else {
                updateQuestion();
            }
        });

        mChoice2.setOnClickListener(view -> {
            mPoint = mPoint + 1;
            if (mQuesNumber == getLength()){
                updateResult();
            } else {
                updateQuestion();
            }
        });

        mChoice3.setOnClickListener(view -> {
            mPoint = mPoint + 2;
            if (mQuesNumber == getLength()){
                updateResult();
            } else {
                updateQuestion();
            }
        });

        mChoice4.setOnClickListener(view -> {
            mPoint = mPoint + 3;
            if (mQuesNumber == getLength()){
                updateResult();
            } else {
                updateQuestion();
            }
        });

    }

    private void updateQuestion(){
        mQuesView.setText(getQuestion(mQuesNumber));
        mChoice1.setText(getChoice1(mQuesNumber));
        mChoice2.setText(getChoice2(mQuesNumber));
        mChoice3.setText(getChoice3(mQuesNumber));
        mChoice4.setText(getChoice4(mQuesNumber));

        mQuesNumber++;
    }

    private void updateResult(){
        Intent i = new Intent(getApplicationContext(), DepressionResult.class);
        Bundle b = new Bundle();
        b.putInt("points",mPoint);
        i.putExtras(b);
        Depression.this.finish();
        startActivity(i);
    }
    public String getQuestion(int i){
        return mQues[i];
    }

    public String getChoice1(int i){
        return mChoices[i][0];
    }

    public String getChoice2(int i){
        return mChoices[i][1];
    }

    public String getChoice3(int i){
        return mChoices[i][2];
    }

    public String getChoice4(int i){
        return mChoices[i][3];
    }

    public int getLength(){
        return mQues.length;
    }
}
