package com.application.vozyk.ui.login;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.application.vozyk.MainActivity;
import com.application.vozyk.R;
import com.google.android.material.progressindicator.CircularProgressIndicator;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Locale;
import java.util.regex.Pattern;

public class Registration extends AppCompatActivity {

    private DatabaseReference getUsersRef(String ref) {
        return FirebaseDatabase.getInstance().getReference("Users")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child(ref);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
              setContentView(R.layout.registration);
        final EditText mFullName = findViewById(R.id.fullName);
        final EditText mEmail = findViewById(R.id.Email);
        final EditText mPassword = findViewById(R.id.password);
        final EditText etConfirm = findViewById(R.id.etConfirmPassword);
        final Button mRegisterBtn = findViewById(R.id.register);
        final TextView mLoginBtn = findViewById(R.id.createText);
        final CircularProgressIndicator indicator =findViewById(R.id.progress_barCircle);
        final ImageView ShowHidePWD = findViewById(R.id.show_hide_pwd);
        Button by_btn =findViewById(R.id.by);
        by_btn.setOnClickListener(v -> {
            Locale locale = new Locale("be");
            changeLocale(locale);
        });
        Button en_btn =findViewById(R.id.eng);
        en_btn.setOnClickListener(v -> {
            Locale locale = new Locale("en");
            changeLocale(locale);
        });

        ShowHidePWD.setOnClickListener(view -> {
            if (mPassword.getTransformationMethod().equals(HideReturnsTransformationMethod.getInstance()))
                mPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
            else
                mPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
        });

        final ImageView ShowHidePWDConfirm = findViewById(R.id.show_hide_pwd_confirm);
        ShowHidePWDConfirm.setOnClickListener(view -> {
            if (etConfirm.getTransformationMethod().equals(HideReturnsTransformationMethod.getInstance()))
                etConfirm.setTransformationMethod(PasswordTransformationMethod.getInstance());
            else
                etConfirm.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
        });
        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        }
        mRegisterBtn.setOnClickListener(v -> {
            final String email = mEmail.getText().toString().trim();
            String password = mPassword.getText().toString();
            final String fullName = mFullName.getText().toString();
            String Confirm = etConfirm.getText().toString();
            if (TextUtils.isEmpty(email)) {
                mEmail.setError(String.valueOf(R.string.email_is_required));
                return;
            }
            if (!isValid(email)) {
                mEmail.setError(String.valueOf(R.string.email_is_not_real));
                return;
            }
            if (TextUtils.isEmpty(password)) {
                mPassword.setError(String.valueOf(R.string.password_is_required));
                return;
            }
            if (password.length() >= 8 && upperCase(password) && lowerCase(password)
                    && numberCase(password) && specialCase(password)) {
                System.out.println(R.string.password_accepted);
            } else {
                mPassword.setError(String.valueOf(R.string.password_contain));
                return;
            }
            if (TextUtils.isEmpty(Confirm)) {
                etConfirm.setError(String.valueOf(R.string.confirm_password_required));
                return;
            }
            if (!mPassword.getText().toString().equals(etConfirm.getText().toString())) {
                etConfirm.setError(String.valueOf(R.string.same_pass_conf));
                return;
            }
            indicator.show();
            indicator.setVisibility(View.VISIBLE);
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    FirebaseAuth.getInstance().getCurrentUser().sendEmailVerification()
                            .addOnSuccessListener(aVoid ->
                                    Toast.makeText(this, String.valueOf(R.string.verification_email_sent),
                                            Toast.LENGTH_SHORT).show()).addOnFailureListener(e ->
                                    Log.d("TAG", "onFailure: Email not sent " + e.getMessage()));
                    getUsersRef("name").setValue(fullName);
                    getUsersRef("email").setValue(email);
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                }
            });
        });
        mLoginBtn.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), Login.class)));
    }
    private boolean upperCase(String str) {
        char ch;
        for (int i = 0; i < str.length(); i++) {
            ch = str.charAt(i);
            if (Character.isUpperCase(ch))
                return true;
        }
        return false;
    }

    private boolean lowerCase(String str) {
        char ch;
        for (int i = 0; i < str.length(); i++) {
            ch = str.charAt(i);
            if (Character.isLowerCase(ch))
                return true;
        }
        return false;
    }

    private boolean numberCase(String str) {
        char ch;
        for (int i = 0; i < str.length(); i++) {
            ch = str.charAt(i);
            if (Character.isDigit(ch))
                return true;
        }
        return false;
    }

    private boolean specialCase(String str) {
        char ch;
        char ph;
        String specialChars = "/0*!@$^&*()\"{}_[]|\\?<>,.";
        for (int i = 0; i < str.length(); i++) {
            ch = str.charAt(i);
            for (int j = 0; j < specialChars.length(); j++) {
                ph = specialChars.charAt(j);
                if (ch == ph)
                    return true;
            }
        }
        return false;
    }
    public boolean isValid(String email) {
        String emailRegex =  "^([_a-zA-Z0-9-]+(\\.[_a-zA-Z0-9-]+)*@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*(\\.[a-zA-Z]{1,6}))?$";
        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }
    @SuppressWarnings("deprecation")
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
        final EditText mFullName = findViewById(R.id.fullName);
        mFullName.setHint(R.string.username);
        final EditText mEmail = findViewById(R.id.Email);
        mEmail.setHint(R.string.email);
        final EditText mPassword = findViewById(R.id.password);
        mPassword.setHint(R.string.password);
        final EditText etConfirm = findViewById(R.id.etConfirmPassword);
        etConfirm.setHint(R.string.confirm_password);
        final Button mRegisterBtn = findViewById(R.id.register);
        mRegisterBtn.setHint(R.string.register);
        final TextView alreadyRegister = findViewById(R.id.createText);
        alreadyRegister.setText(R.string.already_registered_login_here);
        final TextView createRegister = findViewById(R.id.textView);
        createRegister.setText(R.string.create_a_new_account);

    }
}