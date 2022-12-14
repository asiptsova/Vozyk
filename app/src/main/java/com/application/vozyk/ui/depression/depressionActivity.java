package com.application.vozyk.ui.depression;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.content.Intent;
import com.application.vozyk.R;

public class depressionActivity extends AppCompatActivity {

    private final depressionQuestionModel mQues = new depressionQuestionModel();
    private TextView mQuesView;
    private Button mChoice1;
    private Button mChoice2;
    private Button mChoice3;
    private Button mChoice4;
    private int mPoint = 0;
    private int mQuesNumber = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_depression);
        getSupportActionBar().hide();

        mQuesView =  findViewById(R.id.questions);
        mChoice1 =  findViewById(R.id.choiceA);
        mChoice2 =  findViewById(R.id.choiceB);
        mChoice3 = findViewById(R.id.choiceC);
        mChoice4 = findViewById(R.id.choiceD);

        updateQuestion();

        mChoice1.setOnClickListener(view -> {
            if (mQuesNumber == mQues.getLength()){
                updateResult();
            } else {
                updateQuestion();
            }
        });

        mChoice2.setOnClickListener(view -> {
            mPoint = mPoint + 1;
            if (mQuesNumber == mQues.getLength()){
                updateResult();
            } else {
                updateQuestion();
            }
        });

        mChoice3.setOnClickListener(view -> {
            mPoint = mPoint + 2;
            if (mQuesNumber == mQues.getLength()){
                updateResult();
            } else {
                updateQuestion();
            }
        });

        mChoice4.setOnClickListener(view -> {
            mPoint = mPoint + 3;
            if (mQuesNumber == mQues.getLength()){
                updateResult();
            } else {
                updateQuestion();
            }
        });

    }

    private void updateQuestion(){
        mQuesView.setText(mQues.getQuestion(mQuesNumber));
        mChoice1.setText(mQues.getChoice1(mQuesNumber));
        mChoice2.setText(mQues.getChoice2(mQuesNumber));
        mChoice3.setText(mQues.getChoice3(mQuesNumber));
        mChoice4.setText(mQues.getChoice4(mQuesNumber));

        mQuesNumber++;
    }

    private void updateResult(){
        Intent i = new Intent(getApplicationContext(), depressionResultActivity.class);
        Bundle b = new Bundle();
        b.putInt("points",mPoint);
        i.putExtras(b);
        depressionActivity.this.finish();
        startActivity(i);
    }
}
