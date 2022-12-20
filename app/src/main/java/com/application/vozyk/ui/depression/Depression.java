package com.application.vozyk.ui.depression;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.content.Intent;

import com.application.vozyk.R;

public class Depression extends AppCompatActivity {

    private TextView quesView;
    private Button choice1, choice2, choice3, choice4;
    private int point = 0, number = 0;
    private String[][] choices;
    public String[] ques;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_depression);
        getSupportActionBar().hide();

        ques = new String[]{
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

        choices = new String[][]{
                {getResources().getString(R.string.not_at_all_sure), getResources().getString(R.string.several_days), getResources().getString(R.string.over_half_the_days), getResources().getString(R.string.nearly_every_day)},
                {getResources().getString(R.string.not_at_all_sure), getResources().getString(R.string.several_days), getResources().getString(R.string.over_half_the_days), getResources().getString(R.string.nearly_every_day)},
                {getResources().getString(R.string.not_at_all_sure), getResources().getString(R.string.several_days), getResources().getString(R.string.over_half_the_days), getResources().getString(R.string.nearly_every_day)},
                {getResources().getString(R.string.not_at_all_sure), getResources().getString(R.string.several_days), getResources().getString(R.string.over_half_the_days), getResources().getString(R.string.nearly_every_day)},
                {getResources().getString(R.string.not_at_all_sure), getResources().getString(R.string.several_days), getResources().getString(R.string.over_half_the_days), getResources().getString(R.string.nearly_every_day)},
                {getResources().getString(R.string.not_at_all_sure), getResources().getString(R.string.several_days), getResources().getString(R.string.over_half_the_days), getResources().getString(R.string.nearly_every_day)},
                {getResources().getString(R.string.not_at_all_sure), getResources().getString(R.string.several_days), getResources().getString(R.string.over_half_the_days), getResources().getString(R.string.nearly_every_day)},
                {getResources().getString(R.string.not_at_all_sure), getResources().getString(R.string.several_days), getResources().getString(R.string.over_half_the_days), getResources().getString(R.string.nearly_every_day)},
                {getResources().getString(R.string.not_at_all_sure), getResources().getString(R.string.several_days), getResources().getString(R.string.over_half_the_days), getResources().getString(R.string.nearly_every_day)}
        };
        quesView = findViewById(R.id.questions);
        choice1 = findViewById(R.id.choiceA);
        choice2 = findViewById(R.id.choiceB);
        choice3 = findViewById(R.id.choiceC);
        choice4 = findViewById(R.id.choiceD);

        updateQuestion();

        choice1.setOnClickListener(view -> {
            if (number == getLength())
                updateResult();
            else
                updateQuestion();
        });

        choice2.setOnClickListener(view -> {
            point = point + 1;
            if (number == getLength())
                updateResult();
            else
                updateQuestion();
        });

        choice3.setOnClickListener(view -> {
            point = point + 2;
            if (number == getLength())
                updateResult();
            else
                updateQuestion();
        });

        choice4.setOnClickListener(view -> {
            point = point + 3;
            if (number == getLength())
                updateResult();
            else
                updateQuestion();
        });

    }

    private void updateQuestion() {
        quesView.setText(getQuestion(number));
        choice1.setText(getChoice1(number));
        choice2.setText(getChoice2(number));
        choice3.setText(getChoice3(number));
        choice4.setText(getChoice4(number));

        number++;
    }

    private void updateResult() {
        Intent i = new Intent(getApplicationContext(), DepressionResult.class);
        Bundle b = new Bundle();
        b.putInt("points", point);
        i.putExtras(b);
        Depression.this.finish();
        startActivity(i);
    }

    public String getQuestion(int i) {
        return ques[i];
    }

    public String getChoice1(int i) {
        return choices[i][0];
    }

    public String getChoice2(int i) {
        return choices[i][1];
    }

    public String getChoice3(int i) {
        return choices[i][2];
    }

    public String getChoice4(int i) {
        return choices[i][3];
    }

    public int getLength() {
        return ques.length;
    }
}
