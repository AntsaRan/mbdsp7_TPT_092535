package com.example.bet;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.bet.API.API;
import com.example.bet.controleur.NetworkUtils;
import com.example.bet.modele.RequestUtilisateur;
import com.example.bet.modele.Utilisateur;
import com.example.bet.service.Utilisateur_Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import androidmads.library.qrgenearator.QRGEncoder;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Register extends AppCompatActivity {
    private static final String TAG = "Qr";
    private EditText editText;
    private ImageView imageView;
    private QRGEncoder qrgEncoder;
    private Bitmap bitmapResult;
    private EditText nom,prenom,pseudo;
    private EditText email1;
    private EditText password;
    private EditText password2;
    private Button btn;
    private ProgressDialog progress;
    private  EditText dateNaissance;
    private Calendar myCalendar;
    Utilisateur_Service service;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        nom=findViewById(R.id.nom);
        prenom=findViewById(R.id.prenom);
        pseudo=findViewById(R.id.pseudo);
        password2=findViewById(R.id.password2);
        email1=findViewById(R.id.mail);
        myCalendar = Calendar.getInstance();
        btn=findViewById(R.id.valider);
        progress = new ProgressDialog(Register.this);
        dateNaissance= (EditText) findViewById(R.id.date);
        service = API.getClient().create(Utilisateur_Service.class);
        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };

        dateNaissance.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN) {
                    new DatePickerDialog(Register.this, date, myCalendar
                            .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                            myCalendar.get(Calendar.DAY_OF_MONTH)).show();
                }
                return true;
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String _nom = nom.getText().toString();
                String _prenom = prenom.getText().toString();
                String _date = dateNaissance.getText().toString();
                String _pseudo = pseudo.getText().toString();
                String _mail = email1.getText().toString();
                String _pwd = password2.getText().toString();
                if (TextUtils.isEmpty(_nom)) {
                    showError(nom, "Nom invalide");
                }
                if (TextUtils.isEmpty(_prenom)) {
                    showError(prenom, "Prenom invalide");
                }
                if (TextUtils.isEmpty(_date)) {
                    showError(dateNaissance, "Date de naissance invalide");
                }
                if (TextUtils.isEmpty(_pseudo)) {
                    showError(pseudo, "Pseudo invalide");
                }
                if (TextUtils.isEmpty(_pwd)) {
                    showError(password2, "Mot de passe invalide");
                }else
                {
                    progress.setTitle("Inscription");
                    progress.setMessage("En cours....");
                    progress.setCanceledOnTouchOutside(false);
                    progress.show();
                    if (NetworkUtils.networkStatus(Register.this)) {
                        callToInscription(_mail,_date,_pseudo,_prenom,_nom,_pwd).enqueue(new Callback<ResponseBody>() {
                            @Override
                            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                                    progress.setMessage("Reussie....");
                                Intent intent = new Intent(Register.this, Login.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);

                                startActivity(intent);
                            }

                            @Override
                            public void onFailure(Call<ResponseBody> call, Throwable t) {
                                Log.e("Exception ato",t.getMessage());
                                Toast.makeText(Register.this, R.string.login_failed, Toast.LENGTH_SHORT).show();
                                progress.dismiss();
                            }
                        });


                    }else{

                        Toast.makeText(Register.this, "No Internet Connection", Toast.LENGTH_SHORT).show();
                        progress.dismiss();

                    }

                }

            }
        });




    }
    private void showError(EditText input, String s) {
        input.setError(s);
        input.requestFocus();
    }
    private Call<ResponseBody> callToInscription(String mail,String date,String pseudo,String prenom,String nom, String pwd) {
        RequestUtilisateur util=new RequestUtilisateur(nom,prenom,date,pseudo,pwd,1000f,mail);
        System.out.println("UTILISATEUR"+util.getNom()+"|"+util.getDateNaissance()+"|"+util.getJetons());
        return service.inscription(util);
    }


    private void updateLabel() {
        String myFormat = "yyyy-MM-dd"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat,Locale.FRENCH);

        dateNaissance.setText(sdf.format(myCalendar.getTime()));
    }
}