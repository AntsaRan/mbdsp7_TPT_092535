package com.example.bet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bet.controleur.NetworkUtils;

public class Login extends AppCompatActivity {
    private EditText mEmailField;
    private EditText mPasswordField;

    private Button mLoginBtn;
    private ProgressDialog progress;
    private TextView registerText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mEmailField = (EditText) findViewById(R.id.editTextTextEmailAddress3);
        mPasswordField = (EditText) findViewById(R.id.editTextTextPassword2);
        progress = new ProgressDialog(Login.this);
        registerText = (TextView) findViewById(R.id.textView3);
        mLoginBtn = (Button) findViewById(R.id.button2);
        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, MainActivity.class);
                //intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK| Intent.FLAG_ACTIVITY_NEW_TASK);

                startActivity(intent);
            }
        });
        registerText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, Register.class);
                //intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK| Intent.FLAG_ACTIVITY_NEW_TASK);

                startActivity(intent);

            }
        });
    }
    private void startSignIn() {
        String email = mEmailField.getText().toString();
        String password = mPasswordField.getText().toString();
        if (TextUtils.isEmpty(email)) {
            //Toast.makeText(Login.this, "Veuillez saisir votre email/password", Toast.LENGTH_SHORT).show();
            showError(mEmailField, "Email is not valid");
        }
        if (TextUtils.isEmpty(password)) {
            showError(mPasswordField, "Password is not valid");
        } else {
            progress.setTitle("Sign in");
            progress.setMessage("Please wait");
            progress.setCanceledOnTouchOutside(false);
            progress.show();
            if (NetworkUtils.networkStatus(Login.this)) {
               /* mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!task.isSuccessful()) {
                            Toast.makeText(Login.this, R.string.login_failed, Toast.LENGTH_SHORT).show();
                            progress.dismiss();
                        } else {

                            Intent intent = new Intent(Login.this, Home.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                            Notification.addNotification("DicoMovie","Welcome to DicoMovie",Login.this,Home.class);
                            startActivity(intent);
                        }
                    }
                });
                */
            }
            else{
                Toast.makeText(Login.this, "No Internet Connection", Toast.LENGTH_SHORT).show();
                progress.dismiss();
            }
        }
    }
    private void showError(EditText input, String s) {
        input.setError(s);
        input.requestFocus();
    }
}