package com.application.vozyk.ui.ptsd;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.content.Intent;

import com.application.vozyk.R;


public class PtsdActivity extends AppCompatActivity {

    private TextView mQuesView;
    private int mQuesNum=0;
    private int mScore=0;
    public  String[] mQues;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ptsd_2);
        getSupportActionBar().hide();
        mQues=new String[]{
                getResources().getString(R.string.ptsd1),
                getResources().getString(R.string.ptsd2),
                getResources().getString(R.string.ptsd3),
                getResources().getString(R.string.ptsd4),
                getResources().getString(R.string.ptsd5)
        };

        mQuesView =findViewById(R.id.quesP);
        Button yesB =findViewById(R.id.yesP);
        Button noB = findViewById(R.id.noP);

        updateQuestion();

        yesB.setOnClickListener(v -> {
            mScore = mScore + 1;
            if (mQuesNum == getLength()) {
                updateResult();
            } else {
                updateQuestion();
            }
        });

        noB.setOnClickListener(v -> {
            if (mQuesNum == getLength()){
                updateResult();
            } else {
                updateQuestion();
            }
        });


    }

    private void updateQuestion(){
        mQuesView.setText(getQuestion(mQuesNum));
        mQuesNum++;
    }

    private void updateResult(){
        Intent i = new Intent(getApplicationContext(), PtsdResult.class);
        Bundle b = new Bundle();
        b.putInt("score",mScore);
        i.putExtras(b);
        PtsdActivity.this.finish();
        startActivity(i);
    }


    public String getQuestion(int i) {
        return mQues[i];
    }

    public int getLength(){
        return mQues.length;
    }
}
