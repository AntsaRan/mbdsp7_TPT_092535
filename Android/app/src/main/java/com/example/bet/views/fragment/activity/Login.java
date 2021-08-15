package com.example.bet.views.fragment.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bet.API.API;
import com.example.bet.R;
import com.example.bet.controleur.DataBaseHelper;
import com.example.bet.controleur.NetworkUtils;
import com.example.bet.model.Utilisateur;
import com.example.bet.service.Utilisateur_Service;

import retrofit2.Call;
import retrofit2.Callback;

public class Login extends AppCompatActivity {
    private EditText mEmailField;
    private EditText mPasswordField;
    Utilisateur_Service service;
    private Button mLoginBtn;
    SharedPreferences pref;
    private ProgressDialog progress;
    private TextView registerText;

    private DataBaseHelper database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mEmailField = (EditText) findViewById(R.id.editTextTextEmailAddress3);
        mPasswordField = (EditText) findViewById(R.id.editTextTextPassword2);
        progress = new ProgressDialog(Login.this);
        registerText = (TextView) findViewById(R.id.textView3);
        mLoginBtn = (Button) findViewById(R.id.button2);
        service = API.getClient().create(Utilisateur_Service.class);
        pref = getSharedPreferences("user_details",MODE_PRIVATE);
        database=new DataBaseHelper(this);

        if(database.getUtilisateur()!=null){
            Utilisateur user=database.getUtilisateur();
            System.out.println("UTILISATEUR "+user.getNom());
            Intent intent = new Intent(Login.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
       /* if(pref.contains("user") ){
            Gson gson = new Gson();
            String json = pref.getString("user", "");
            Utilisateur utilisateur = gson.fromJson(json, Utilisateur.class);
            System.out.println("UTILISATEUR "+utilisateur.getNom());
            Intent intent = new Intent(Login.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
*/
        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // Intent intent = new Intent(Login.this, MainActivity.class);
                //intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK| Intent.FLAG_ACTIVITY_NEW_TASK);

                startSignIn();
                //startActivity(intent);
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

                callToSignIn(email,password).enqueue(new Callback<Utilisateur>() {

                    @Override
                    public void onResponse(Call<Utilisateur> call, retrofit2.Response<Utilisateur> response) {

                        Utilisateur results = fetchResults(response);
                       // System.out.println("BODY"+results.getDateNaissance());
                        if(results!=null){
                           /* SharedPreferences.Editor editor = pref.edit();
                            Gson gson = new Gson();
                            String json = gson.toJson(results);
                            editor.putString("user", json);
                            editor.commit();

                            System.out.println("RESULTS ID "+results.getDateNaissance());
                            */
                           database.insertUtilisateur(results);

                            Intent intent = new Intent(Login.this, MainActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);

                            startActivity(intent);
                        }else{
                            //System.out.println("RESULTS ID "+results.getId());
                            Toast.makeText(Login.this, R.string.login_failed, Toast.LENGTH_SHORT).show();
                            progress.dismiss();
                        }

                       // bar.setVisibility(View.GONE);


                    }


                    @Override
                    public void onFailure(Call<Utilisateur> call, Throwable t) {
                        t.printStackTrace();
                        Log.e("EXCEPTION ATO ", t.getMessage());
                        progress.dismiss();
                        Toast.makeText(Login.this, R.string.login_failed, Toast.LENGTH_SHORT).show();
                    }
                });

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
    private Utilisateur fetchResults(retrofit2.Response<Utilisateur> response) {
        System.out.println("Ito le response" + response.body());
        Utilisateur utilisateur = null;

        if (response.body()!=null){

            utilisateur = response.body();
        }


        //ASORINA AVEO

        return utilisateur;
    }
    private Call<Utilisateur> callToSignIn(String mail, String pwd) {
        return service.authentification(mail,pwd);
    }
    private void showError(EditText input, String s) {
        input.setError(s);
        input.requestFocus();
    }
}