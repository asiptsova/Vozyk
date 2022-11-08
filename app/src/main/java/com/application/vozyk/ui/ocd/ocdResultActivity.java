package com.application.vozyk.ui.ocd;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.application.vozyk.R;

public class ocdResultActivity extends AppCompatActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ocd_result);

        TextView mResult = findViewById(R.id.results);
        Button mRetry = findViewById(R.id.redo);

        Bundle b = getIntent().getExtras();
        int points = b.getInt("points");

        if (points >= 0 && points < 21){
            mResult.setText("Your symptoms are not suggestive of OCD");
        }
        if (points >= 21){
            mResult.setText("Your symptoms are suggestive of OCD");
        }

        mRetry.setOnClickListener(v -> {
            Intent i = new Intent(getApplicationContext(), ocdActivity.class);
            startActivity(i);
        });
    }
}
