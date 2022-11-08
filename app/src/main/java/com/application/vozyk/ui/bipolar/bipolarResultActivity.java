package com.application.vozyk.ui.bipolar;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.application.vozyk.R;


public class bipolarResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_bipolar_result);

        TextView mResult = findViewById(R.id.results);
        Button mRetry =  findViewById(R.id.redo);

        Bundle b = getIntent().getExtras();
        int points = b.getInt("points");

        if (points >= 0 && points <= 15){
            mResult.setText("Your symptoms are not suggestive of bipolar disorder");
        }

        if (points >= 16 && points <= 24){
            mResult.setText("Your symptoms are suggestive of major depression or bipolar disorder");
        }

        if (points >= 25){
            mResult.setText("Your symptoms are suggestive of bipolar disorder");
        }

        mRetry.setOnClickListener(v -> {
            Intent i = new Intent(getApplicationContext(), bipolarActivity.class);
            startActivity(i);
        });
    }
}
