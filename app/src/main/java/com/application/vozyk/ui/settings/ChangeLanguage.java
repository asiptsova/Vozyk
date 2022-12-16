package com.application.vozyk.ui.settings;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.application.vozyk.MainActivity;
import com.application.vozyk.R;
import com.application.vozyk.ui.notes.NotesListActivity;

import java.util.Locale;

public class ChangeLanguage extends AppCompatActivity {
    int position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_language);
        getSupportActionBar().hide();
        final RadioGroup languageGroup = findViewById(R.id.languageGroup);
        languageGroup.setOnCheckedChangeListener((group, checkedId) -> {
            position = languageGroup.indexOfChild(findViewById(checkedId));
            if (position == 0)
            {
                Locale locale = new Locale("en");
                changeLocale(locale);
            }
            else if (position == 1)
            {
                Locale locale = new Locale("be");
                changeLocale(locale);
            }
            else
            {
                Locale locale = new Locale("pl");
                changeLocale(locale);
            }
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });

    }
    private void changeLocale(Locale locale)
    {
        Locale.setDefault(locale);
        Configuration configuration = new Configuration();
        configuration.setLocale(locale);
        getBaseContext().getResources()
                .updateConfiguration(configuration,
                        getBaseContext()
                                .getResources()
                                .getDisplayMetrics());
        setTitle(R.string.app_name);
        final RadioButton english = findViewById(R.id.englishRadio);
        english.setText(R.string.english);
        final RadioButton belorussian = findViewById(R.id.belorussianRadio);
        belorussian.setText(R.string.belorussian);
        final RadioButton polish = findViewById(R.id.polishRadio);
        polish.setText(R.string.polish);
        final TextView chooseLanguage = findViewById(R.id.tv_change_language);
        chooseLanguage.setText(R.string.change_language);
    }
}