package com.example.bet.views.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.budiyev.android.codescanner.CodeScanner;
import com.budiyev.android.codescanner.CodeScannerView;
import com.budiyev.android.codescanner.DecodeCallback;
import com.example.bet.API.API;
import com.example.bet.R;
import com.example.bet.controleur.DataBaseHelper;
import com.example.bet.controleur.NetworkUtils;
import com.example.bet.model.Jeton;
import com.example.bet.model.Utilisateur;
import com.example.bet.service.Jeton_Service;
import com.example.bet.service.Utilisateur_Service;
import com.google.zxing.Result;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Achat_Fragment extends Fragment  {
    Button acheter_Qr,achatBanque;
    TextView prixAchat,nomVendeur, solde,jetons,prix_banque;
    private Context mContext;
    private CodeScanner mCodeScanner;
    private Utilisateur_Service serviceUtil;
    private Jeton_Service serviceJetons;
    private Utilisateur vendeur,acheteur;
    private EditText nb_jetons, nb_jetons_achat;
    private  String id;
    private int prix;
    private float prixB;
    private DataBaseHelper database;
    private ProgressBar bar;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        final Activity activity = getActivity();
        View root = inflater.inflate(R.layout.fragment_achat, container, false);
        CodeScannerView scannerView = root.findViewById(R.id.scanner_view);
        database=new DataBaseHelper(getActivity());
        if(database.getUtilisateur()!=null){
            acheteur=database.getUtilisateur();
        }
        CheckUser();
        bar=(ProgressBar) root.findViewById(R.id.progressBar2);
        bar.setVisibility(View.GONE);
        solde=(TextView)root.findViewById(R.id.solde);
        jetons=(TextView)root.findViewById(R.id.jetons);
        acheter_Qr=(Button)root.findViewById(R.id.bouton_achat_qr);
        achatBanque=(Button)root.findViewById(R.id.bouton_achat_qr2);
        nomVendeur=(TextView)root.findViewById(R.id.nomVendeur);
        prixAchat=(TextView)root.findViewById(R.id.prix_vendeur);
        prix_banque=(TextView)root.findViewById(R.id.prix);
        nb_jetons=(EditText)root.findViewById(R.id.nb_qr_vente);
        nb_jetons_achat=(EditText)root.findViewById(R.id.nb_qr_vente2);

        nb_jetons.setVisibility(View.GONE);
        acheter_Qr.setVisibility(View.GONE);
        prixAchat.setVisibility(View.GONE);
        nomVendeur.setVisibility(View.GONE);

        solde.setText(String.valueOf(acheteur.getSolde()));
        jetons.setText(String.valueOf(acheteur.getJetons()));
      //  prix_banque.setText(String.valueOf(1000));
        getPrixJeton().enqueue(new Callback<List<Jeton>>() {
            @Override
            public void onResponse(Call<List<Jeton>> call, Response<List<Jeton>> response) {
                List<Jeton> jeton= fetchJeton(response);
                System.out.println("PRIX JETON "+jeton.get(0).getPrix());
                prix_banque.setText(String.valueOf(jeton.get(0).getPrix()));
                prixB=jeton.get(0).getPrix();
            }

            @Override
            public void onFailure(Call<List<Jeton>> call, Throwable t) {
                Log.e("Erreuuuur reeee", t.getMessage());
            }
        });
        achatBanque.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nb = nb_jetons_achat.getText().toString();

                if (TextUtils.isEmpty(nb)) {
                    //Toast.makeText(Login.this, "Veuillez saisir votre email/password", Toast.LENGTH_SHORT).show();
                    showError(nb_jetons_achat, "Nombre invalide");
                }
                else{
                    if(testMiseBanque(nb)){
                        bar.setVisibility(View.VISIBLE);
                        int nb2=Integer.parseInt(nb_jetons_achat.getText().toString());
                        int montantTotal=Integer.parseInt(nb_jetons_achat.getText().toString()) * (int)prixB;
                        callAjoutJeton(acheteur.getId(),nb2,montantTotal).enqueue(new Callback<ResponseBody>() {
                            @Override
                            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                Toast.makeText(activity, "Achat Réussie ", Toast.LENGTH_SHORT).show();
                                System.out.println("ETOOO "+response.body());
                                callGetUserById(acheteur.getId()).enqueue(new Callback<Utilisateur>() {
                                    @Override
                                    public void onResponse(Call<Utilisateur> call2, Response<Utilisateur> response2) {
                                        Utilisateur results = fetchResults(response2);
                                        System.out.println("BODY"+results.getJetons());
                                        if(results!=null){
                                            database.deleteUser();
                                            database.insertUtilisateur(results);
                                            acheteur=database.getUtilisateur();
                                            solde.setText(String.valueOf(acheteur.getSolde()));
                                            jetons.setText(String.valueOf(acheteur.getJetons()));
                                            bar.setVisibility(View.GONE);
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<Utilisateur> call2, Throwable t2) {

                                    }
                                });
                            }

                            @Override
                            public void onFailure(Call<ResponseBody> call, Throwable t) {
                                bar.setVisibility(View.GONE);
                                Log.e("ERRORRR tsy mety eee" , t.getMessage());
                                Toast.makeText(activity, "Erreur de la transaction ", Toast.LENGTH_SHORT).show();
                            }
                        });

                    }

                }

            }

        });
        acheter_Qr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(vendeur!=null){
                    String nb = nb_jetons.getText().toString();
                    if (TextUtils.isEmpty(nb)) {
                        //Toast.makeText(Login.this, "Veuillez saisir votre email/password", Toast.LENGTH_SHORT).show();
                        showError(nb_jetons, "Nombre invalide");
                    }
                    else{
                        if(testMise(nb)){
                            bar.setVisibility(View.VISIBLE);
                            int nb2=Integer.parseInt(nb_jetons.getText().toString());
                            int montantTotal=Integer.parseInt(nb_jetons.getText().toString()) * prix;
                            callAjoutJeton(acheteur.getId(),nb2,montantTotal).enqueue(new Callback<ResponseBody>() {
                                @Override
                                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                    Toast.makeText(activity, "Achat Réussie ", Toast.LENGTH_SHORT).show();
                                    System.out.println("ETOOO "+response.body());
                              callGetUserById(acheteur.getId()).enqueue(new Callback<Utilisateur>() {
                                        @Override
                                        public void onResponse(Call<Utilisateur> call2, Response<Utilisateur> response2) {
                                            Utilisateur results = fetchResults(response2);
                                            System.out.println("BODY"+results.getJetons());
                                            if(results!=null){
                                                database.deleteUser();
                                                database.insertUtilisateur(results);
                                                acheteur=database.getUtilisateur();
                                                solde.setText(String.valueOf(acheteur.getSolde()));
                                                jetons.setText(String.valueOf(acheteur.getJetons()));
                                            }
                                        }

                                        @Override
                                        public void onFailure(Call<Utilisateur> call2, Throwable t2) {

                                        }
                                    });
                                }

                                @Override
                                public void onFailure(Call<ResponseBody> call, Throwable t) {
                                    bar.setVisibility(View.GONE);
                                    Log.e("ERRORRR tsy mety eee" , t.getMessage());
                                    Toast.makeText(activity, "Erreur de la transaction ", Toast.LENGTH_SHORT).show();
                                }
                            });
                            callRemoveJeton(vendeur.getId(),nb2,montantTotal).enqueue(new Callback<ResponseBody>() {
                                @Override
                                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                    System.out.println("MESSAGE 2 "+response);
                                    bar.setVisibility(View.GONE);
                                }

                                @Override
                                public void onFailure(Call<ResponseBody> call, Throwable t) {
                                    bar.setVisibility(View.GONE);
                                    Toast.makeText(activity, "Erreur de la transaction 2 ", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }

                    }

                }
            }
        });
        mCodeScanner = new CodeScanner(activity, scannerView);
        mCodeScanner.setDecodeCallback(new DecodeCallback() {
            @Override
            public void onDecoded(@NonNull final Result result) {
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if(result.getText()!=null){
                            String[] split=result.getText().split(":");


                            if(split!=null){

                                id=split[0];
                                prix=Integer.parseInt(split[1]);
                                System.out.println("nom" +id);
                                callGetUserById(id).enqueue(new Callback<Utilisateur>() {
                                    @Override
                                    public void onResponse(Call<Utilisateur> call, Response<Utilisateur> response) {
                                        vendeur =fetchUtilisateur(response);
                                        if(vendeur!=null) {
                                            if (vendeur.getJetons() > 0) {
                                                nomVendeur.setText(vendeur.getNom());
                                                prixAchat.setText(String.valueOf(prix));
                                                prixAchat.setVisibility(View.VISIBLE);
                                                nomVendeur.setVisibility(View.VISIBLE);
                                                acheter_Qr.setVisibility(View.VISIBLE);
                                                nb_jetons.setVisibility(View.VISIBLE);
                                            }
                                            else
                                                Toast.makeText(activity, "Jeton du vendeur insuffisant", Toast.LENGTH_SHORT).show();
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<Utilisateur> call, Throwable t) {
                                        Toast.makeText(activity, "Utilisateur introuvable", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }

                           // Toast.makeText(activity, result.getText(), Toast.LENGTH_SHORT).show();


                        }



                    }
                });
            }
        });
        scannerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCodeScanner.startPreview();
            }
        });


        return root;
    }
    public void CheckUser(){
        if(NetworkUtils.networkStatus(getActivity())){
            callGetUserById(acheteur.getId()).enqueue(new Callback<Utilisateur>() {
                @Override
                public void onResponse(Call<Utilisateur> call2, Response<Utilisateur> response2) {
                    Utilisateur results = fetchResults(response2);
                    System.out.println("BODY"+results.getJetons());
                    if(results!=null){
                        database.deleteUser();
                        database.insertUtilisateur(results);
                        acheteur=database.getUtilisateur();
                        solde.setText(String.valueOf(acheteur.getSolde()));
                        jetons.setText(String.valueOf(acheteur.getJetons()));
                    }
                }

                @Override
                public void onFailure(Call<Utilisateur> call2, Throwable t2) {

                }
            });
        }
    }

    public boolean testMiseBanque(String miseB){
        int val= Integer.valueOf(miseB);
        if(acheteur.getSolde()-(prixB*val)>=0){
            return true;
        }
        showError(nb_jetons_achat, "Solde insuffisant");
        return false;
    }
    public boolean testMise(String mise){
        int val= Integer.valueOf(mise);
        System.out.println("MISE "+(vendeur.getJetons()-val));
        if(vendeur.getJetons()-val>=0){
            System.out.println("ACHETEUR "+((prix*val)-acheteur.getSolde()));
            if(acheteur.getSolde()-(prix*val)>=0)
            {
                return true;
             }else
                showError(nb_jetons, "Solde insuffisant");
        }
        showError(nb_jetons, "Jetons insuffisant");
        return false;
    }

    private Utilisateur fetchResults(retrofit2.Response<Utilisateur> response) {
        System.out.println("Ito le response" + response.body());
        Utilisateur utilisateur = null;

        if (response.body()!=null){

            utilisateur = response.body();
        }
        return utilisateur;
    }
    @Override
    public void onResume() {
        super.onResume();
        mCodeScanner.startPreview();
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
        if(NetworkUtils.networkStatus(getActivity())){
            serviceJetons = API.getClient().create(Jeton_Service.class);
            serviceUtil = API.getClient().create(Utilisateur_Service.class);
        }

    }
    @Override
    public void onPause() {
        mCodeScanner.releaseResources();
        super.onPause();
    }
    private void showError(EditText input, String s) {
        input.setError(s);
        input.requestFocus();
    }
    private Utilisateur fetchUtilisateur(retrofit2.Response<Utilisateur> response) {
        System.out.println("Ito le response" + response.body());
        Utilisateur utilisateur = null;

        if (response.body()!=null){

            utilisateur = response.body();
            System.out.println("NOMB" + utilisateur.getNom());
        }

        return utilisateur;
    }
    private List<Jeton> fetchJeton(retrofit2.Response<List<Jeton>> response) {
        System.out.println("Ito le response" + response.body());
        List<Jeton> jeton = null;

        if (response.body()!=null){

            jeton = response.body();
        }

        return jeton;
    }
    private Call<ResponseBody> callAjoutJeton(String id,int jetons,int montantTotal) {
        return serviceJetons.ajoutJeton(id,jetons,montantTotal);
    }
    private Call<ResponseBody> callRemoveJeton(String id,int jetons,int montantTotal) {
        return serviceJetons.retirerJeton(id,jetons,montantTotal);
    }
    private Call<List<Jeton>> getPrixJeton() {
        return serviceJetons.getPrixJeton();
    }
    private Call<Utilisateur> callGetUserById(String id) {
        return serviceUtil.getByID(id);
    }

}
