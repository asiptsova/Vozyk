package com.application.vozyk.ui.about;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.application.vozyk.R;

public class About_us extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        getSupportActionBar().hide();
    }
}