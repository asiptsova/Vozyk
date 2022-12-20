package com.application.vozyk.ui.anxiety;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.content.Intent;
import com.application.vozyk.R;


public class Anxiety extends AppCompatActivity {

    private TextView mQuesView;
    private Button mChoice1;
    private Button mChoice2;
    private Button mChoice3;
    private Button mChoice4;
    private int mPoint = 0;
    private int mQuesNumber = 0;

    public String[] mQues;

    private String[][] mChoices;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_anxiety);
        getSupportActionBar().hide();
        mQues = new String[]{
                getResources().getString(R.string.anxiexty1),
                getResources().getString(R.string.anxiexty2),
                getResources().getString(R.string.anxiexty3),
                getResources().getString(R.string.anxiexty4),
                getResources().getString(R.string.anxiexty5),
                getResources().getString(R.string.anxiexty6),
                getResources().getString(R.string.anxiexty7),
        };
        mChoices = new String[][] {
                {getResources().getString(R.string.not_at_all_sure),getResources().getString(R.string.several_days),getResources().getString(R.string.over_half_the_days),getResources().getString(R.string.nearly_every_day)},
                {getResources().getString(R.string.not_at_all_sure),getResources().getString(R.string.several_days),getResources().getString(R.string.over_half_the_days),getResources().getString(R.string.nearly_every_day)},
                {getResources().getString(R.string.not_at_all_sure),getResources().getString(R.string.several_days),getResources().getString(R.string.over_half_the_days),getResources().getString(R.string.nearly_every_day)},
                {getResources().getString(R.string.not_at_all_sure),getResources().getString(R.string.several_days),getResources().getString(R.string.over_half_the_days),getResources().getString(R.string.nearly_every_day)},
                {getResources().getString(R.string.not_at_all_sure),getResources().getString(R.string.several_days),getResources().getString(R.string.over_half_the_days),getResources().getString(R.string.nearly_every_day)},
                {getResources().getString(R.string.not_at_all_sure),getResources().getString(R.string.several_days),getResources().getString(R.string.over_half_the_days),getResources().getString(R.string.nearly_every_day)},
                {getResources().getString(R.string.not_at_all_sure),getResources().getString(R.string.several_days),getResources().getString(R.string.over_half_the_days),getResources().getString(R.string.nearly_every_day)}
        };
        mQuesView = findViewById(R.id.question);
        mChoice1 = findViewById(R.id.choice1);
        mChoice2 =  findViewById(R.id.choice2);
        mChoice3 =  findViewById(R.id.choice3);
        mChoice4 =  findViewById(R.id.choice4);

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
        Intent i = new Intent(getApplicationContext(), AnxietyResult.class);
        Bundle b = new Bundle();
        b.putInt("points",mPoint);
        i.putExtras(b);
        Anxiety.this.finish();
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
