package com.application.vozyk;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.common.ConnectionResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class Login extends AppCompatActivity {
    FirebaseAuth firebaseAuth;
    FirebaseAuth.AuthStateListener firebaseAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).hide();
        for (String provider : AuthUI.SUPPORTED_PROVIDERS) {
            Log.v(this.getClass().getName(), provider);
        }
        setContentView(R.layout.login);

        final EditText mEmail = findViewById(R.id.Email);
        final EditText mPassword = findViewById(R.id.password);
        final Button mLoginBtn = findViewById(R.id.loginBtn);
        final TextView  mCreateBtn = findViewById(R.id.createText);
        final TextView  forgotTextLink = findViewById(R.id.forgotPassword);
        //   signInButton = findViewById(R.id.btn_googleLogin);
      //  final ImageView ShowHidePWD=findViewById(R.id.show_hide_pwd);
       /* ShowHidePWD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mPassword.getTransformationMethod().equals(HideReturnsTransformationMethod.getInstance()))
                {
                    mPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
                else
                {
                    mPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
            }
        });*/

//        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//                .requestIdToken("1008459252405-t0jbvgp83tsf7qjig4k4gef16u9spm0o.apps.googleusercontent.com")
//                .requestEmail()
//                .build();
//
//        googleApiClient = new GoogleApiClient.Builder(this)
//                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
//                .build();
//        signInButton.setOnClickListener(v -> {
//            Intent intent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
//            startActivityForResult(intent, SIGN_IN_CODE);
//        });


        firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuthListener = firebaseAuth -> {
            if (firebaseAuth.getCurrentUser() != null) {
                goMainScreen();
            }
        };

        mLoginBtn.setOnClickListener(v -> {

            String email = mEmail.getText().toString().trim();
            String password = mPassword.getText().toString().trim();

            if (TextUtils.isEmpty(email)) {
                mEmail.setError("Email is Required.");
                return;
            }

            if (TextUtils.isEmpty(password)) {
                mPassword.setError("Password is Required.");
                return;
            }

            if (password.length() < 6) {
                mPassword.setError("Password Must be >= 6 Characters");
                return;
            }
            FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    Toast.makeText(Login.this, "Logged in Successfully", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                } else {
                    Toast.makeText(Login.this, "Error ! " + Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();
                }

            });

        });

        mCreateBtn.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), Registration.class)));

        forgotTextLink.setOnClickListener(v -> {

            final EditText resetMail = new EditText(v.getContext());
            final AlertDialog.Builder passwordResetDialog = new AlertDialog.Builder(v.getContext());
            passwordResetDialog.setTitle("Reset Password ?");
            passwordResetDialog.setMessage("Enter Your Email To Received Reset Link.");
            passwordResetDialog.setView(resetMail);

            passwordResetDialog.setPositiveButton("Yes", (dialog, which) -> {
                String mail = resetMail.getText().toString();
                FirebaseAuth.getInstance().sendPasswordResetEmail(mail).addOnSuccessListener(aVoid -> Toast.makeText(this, "Reset Link Sent To Your Email.", Toast.LENGTH_SHORT).show()).addOnFailureListener(e -> Toast.makeText(this, "Error ! Reset Link is Not Sent" + e.getMessage(), Toast.LENGTH_SHORT).show());

            });

            passwordResetDialog.setNegativeButton("No", (dialog, which) -> {
            });
            passwordResetDialog.create().show();

        });


    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseAuth.getInstance().addAuthStateListener(firebaseAuthListener);
    }

    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if (requestCode == SIGN_IN_CODE) {
//            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
//
//        }
//    }

//    private void firebaseAuthWithGoogle(GoogleSignInAccount signInAccount) {
//
//        signInButton.setVisibility(View.GONE);
//
//        AuthCredential credential = GoogleAuthProvider.getCredential(signInAccount.getIdToken(), null);
//        FirebaseAuth.getInstance().signInWithCredential(credential).addOnCompleteListener(this, task -> {
//
//            signInButton.setVisibility(View.VISIBLE);
//
//            if (!task.isSuccessful()) {
//                Toast.makeText(getApplicationContext(), R.string.login_failed, Toast.LENGTH_SHORT).show();
//            }
//        });
//    }

    private void goMainScreen() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
    @Override
    protected void onStop() {
        super.onStop();

        if (firebaseAuthListener != null) {
            FirebaseAuth.getInstance().removeAuthStateListener(firebaseAuthListener);
        }

    }


}