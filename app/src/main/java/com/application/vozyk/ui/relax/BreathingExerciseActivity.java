package com.application.vozyk.ui.relax;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.application.vozyk.R;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.os.CountDownTimer;

public class BreathingExerciseActivity extends AppCompatActivity {

    private final int[] timers = new int[4];
    private final String[] instructionList = new String[]{"Inhale...", "Hold...", "Exhale..."};
    private int instructionCounter = 0;
    TextView instructionText;
    TextView timerText;
    TextView breathText;
    private CountDownTimer timer1, timer2, timer3;
    int counter;
    int breathsCounter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_breathing_exercise);

        LinearLayout linearLayout = findViewById(R.id.breath_layout2);
        linearLayout.getBackground().setAlpha(50);
        timers[0] = getIntent().getIntExtra("inhale", 5);
        timers[1] = getIntent().getIntExtra("hold", 5);
        timers[2] = getIntent().getIntExtra("exhale", 5);
        timers[3] = getIntent().getIntExtra("breaths", 2);
        instructionText = findViewById(R.id.instruction);
        timerText = findViewById(R.id.timer);
        breathText = findViewById(R.id.breathNumber);
        counter=timers[instructionCounter];
        instructionText.setText(instructionList[instructionCounter]);
        breathText.setText("Breath "+(breathsCounter+1)+" out of "+timers[3]);

        timer1 = new CountDownTimer(timers[instructionCounter]* 1000L, 1000) {
            public void onTick(long millisUntilFinished) {
                timerText.setText(String.valueOf(counter));
                counter--;
            }

            public void onFinish() {

                instructionCounter++;
                counter=timers[instructionCounter];
                instructionText.setText(instructionList[instructionCounter]);

                timer2 = new CountDownTimer(timers[instructionCounter]* 1000L, 1000) {

                    public void onTick(long millisUntilFinished) {
                        timerText.setText(String.valueOf(counter));
                        counter--;
                    }
                    public void onFinish() {

                        instructionCounter++;
                        counter=timers[instructionCounter];
                        instructionText.setText(instructionList[instructionCounter]);

                        timer3 = new CountDownTimer(timers[instructionCounter]* 1000L, 1000) {

                            public void onTick(long millisUntilFinished) {
                                timerText.setText(String.valueOf(counter));
                                counter--;
                            }
                            public  void onFinish(){
                                instructionCounter=0;
                                counter=timers[instructionCounter];
                                breathsCounter++;

                                if (breathsCounter < timers[3]) {
                                    breathText.setText("Breath "+(breathsCounter+1)+" out of "+timers[3]);
                                    instructionText.setText(instructionList[instructionCounter]);
                                    timer1.cancel();
                                    timer1.start();
                                } else if (breathsCounter == timers[3]) {
                                    timerText.setText("");
                                    instructionText.setText("Good job ~");
                                }
                            }
                        };
                        timer3.start();
                    }
                };
                timer2.start();
            }
        };

        timer1.start();
    }
}


