package com.application.vozyk.ui.ocd;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.application.vozyk.R;


public class Ocd extends AppCompatActivity {

    private TextView quesView;
    private Button choiceA, choiceB, choiceC, choiceD, choiceE;
    private int point = 0, number = 0;
    public String[] ques;
    private String[][] choices;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ocd);
        getSupportActionBar().hide();

        ques = new String[]{
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

        choices = new String[][]{
                {getResources().getString(R.string.not_at_all), getResources().getString(R.string.a_little), getResources().getString(R.string.moderately), getResources().getString(R.string.a_lot), getResources().getString(R.string.extremely)},
                {getResources().getString(R.string.not_at_all), getResources().getString(R.string.a_little), getResources().getString(R.string.moderately), getResources().getString(R.string.a_lot), getResources().getString(R.string.extremely)},
                {getResources().getString(R.string.not_at_all), getResources().getString(R.string.a_little), getResources().getString(R.string.moderately), getResources().getString(R.string.a_lot), getResources().getString(R.string.extremely)},
                {getResources().getString(R.string.not_at_all), getResources().getString(R.string.a_little), getResources().getString(R.string.moderately), getResources().getString(R.string.a_lot), getResources().getString(R.string.extremely)},
                {getResources().getString(R.string.not_at_all), getResources().getString(R.string.a_little), getResources().getString(R.string.moderately), getResources().getString(R.string.a_lot), getResources().getString(R.string.extremely)},
                {getResources().getString(R.string.not_at_all), getResources().getString(R.string.a_little), getResources().getString(R.string.moderately), getResources().getString(R.string.a_lot), getResources().getString(R.string.extremely)},
                {getResources().getString(R.string.not_at_all), getResources().getString(R.string.a_little), getResources().getString(R.string.moderately), getResources().getString(R.string.a_lot), getResources().getString(R.string.extremely)},
                {getResources().getString(R.string.not_at_all), getResources().getString(R.string.a_little), getResources().getString(R.string.moderately), getResources().getString(R.string.a_lot), getResources().getString(R.string.extremely)},
                {getResources().getString(R.string.not_at_all), getResources().getString(R.string.a_little), getResources().getString(R.string.moderately), getResources().getString(R.string.a_lot), getResources().getString(R.string.extremely)},
                {getResources().getString(R.string.not_at_all), getResources().getString(R.string.a_little), getResources().getString(R.string.moderately), getResources().getString(R.string.a_lot), getResources().getString(R.string.extremely)},
                {getResources().getString(R.string.not_at_all), getResources().getString(R.string.a_little), getResources().getString(R.string.moderately), getResources().getString(R.string.a_lot), getResources().getString(R.string.extremely)},
                {getResources().getString(R.string.not_at_all), getResources().getString(R.string.a_little), getResources().getString(R.string.moderately), getResources().getString(R.string.a_lot), getResources().getString(R.string.extremely)},
                {getResources().getString(R.string.not_at_all), getResources().getString(R.string.a_little), getResources().getString(R.string.moderately), getResources().getString(R.string.a_lot), getResources().getString(R.string.extremely)},
                {getResources().getString(R.string.not_at_all), getResources().getString(R.string.a_little), getResources().getString(R.string.moderately), getResources().getString(R.string.a_lot), getResources().getString(R.string.extremely)},
                {getResources().getString(R.string.not_at_all), getResources().getString(R.string.a_little), getResources().getString(R.string.moderately), getResources().getString(R.string.a_lot), getResources().getString(R.string.extremely)},
                {getResources().getString(R.string.not_at_all), getResources().getString(R.string.a_little), getResources().getString(R.string.moderately), getResources().getString(R.string.a_lot), getResources().getString(R.string.extremely)},
                {getResources().getString(R.string.not_at_all), getResources().getString(R.string.a_little), getResources().getString(R.string.moderately), getResources().getString(R.string.a_lot), getResources().getString(R.string.extremely)},
                {getResources().getString(R.string.not_at_all), getResources().getString(R.string.a_little), getResources().getString(R.string.moderately), getResources().getString(R.string.a_lot), getResources().getString(R.string.extremely)},


        };

        quesView = findViewById(R.id.questions);
        choiceA = findViewById(R.id.choiceA);
        choiceB = findViewById(R.id.choiceB);
        choiceC = findViewById(R.id.choiceC);
        choiceD = findViewById(R.id.choiceD);
        choiceE = findViewById(R.id.choiceE);

        updateQuestion();

        choiceA.setOnClickListener(view -> {
            if (number == getLength())
                updateResult();
            else
                updateQuestion();
        });

        choiceB.setOnClickListener(view -> {
            point = point + 1;
            if (number == getLength())
                updateResult();
            else
                updateQuestion();
        });

        choiceC.setOnClickListener(view -> {
            point = point + 2;
            if (number == getLength())
                updateResult();
            else
                updateQuestion();
        });

        choiceD.setOnClickListener(view -> {
            point = point + 3;
            if (number == getLength())
                updateResult();
            else
                updateQuestion();
        });

        choiceE.setOnClickListener(view -> {
            point = point + 4;
            if (number == getLength())
                updateResult();
            else
                updateQuestion();
        });

    }

    private void updateQuestion() {
        quesView.setText(getQuestion(number));
        choiceA.setText(getChoiceA(number));
        choiceB.setText(getChoiceB(number));
        choiceC.setText(getChoiceC(number));
        choiceD.setText(getChoiceD(number));
        choiceE.setText(getChoiceE(number));

        number++;
    }

    private void updateResult() {
        Intent i = new Intent(getApplicationContext(), OcdResult.class);
        Bundle b = new Bundle();
        b.putInt("points", point);
        i.putExtras(b);
        Ocd.this.finish();
        startActivity(i);
    }

    public String getQuestion(int i) {
        return ques[i];
    }

    public String getChoiceA(int i) {
        return choices[i][0];
    }

    public String getChoiceB(int i) {
        return choices[i][1];
    }

    public String getChoiceC(int i) {
        return choices[i][2];
    }

    public String getChoiceD(int i) {
        return choices[i][3];
    }

    public String getChoiceE(int i) {
        return choices[i][4];
    }

    public int getLength() {
        return ques.length;
    }
}
