package com.example.bet;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.bet.API.API;
import com.example.bet.modele.Equipe;
import com.example.bet.modele.Equipe_Response;
import com.example.bet.modele.Match;
import com.example.bet.modele.MatchRegle;
import com.example.bet.modele.MatchRegle_Response;
import com.example.bet.service.Equipe_Service;
import com.example.bet.service.MatchRegle_Service;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class Match_Details extends AppCompatActivity {

    TextView score1, score2, nomTeam1, nomTeam2, etat, lieu, corner1, corner2, possession1, possession2;
    ImageView img1, img2;
    ProgressBar bar;
    MatchRegle_Service service;
    String id;

    /////
    TextView quote1, quote2, quote3, quote4, quote5, quote6, quote7, quote8, quote9;
    TextView titre1, titre2, titre3;
    Button button1, button2, button3, button4, button5, button6, button7, button8, button9;
    ImageView background1, background2, background3, background_mini1, background_mini2, background_mini3;

    List<TextView> listQuotes;
    List<Button> listButton;
    List<TextView> listTitre;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match__details);
        Match match = (Match) getIntent().getExtras().getParcelable("match");
        score1 = (TextView) findViewById(R.id.score1);
        score2 = (TextView) findViewById(R.id.score2);
        nomTeam1 = (TextView) findViewById(R.id.nomEquipe1);
        nomTeam2 = (TextView) findViewById(R.id.nomEquipe2);
        corner1 = (TextView) findViewById(R.id.corner1);
        corner2 = (TextView) findViewById(R.id.corner2);
        possession1 = (TextView) findViewById(R.id.possession1);
        possession2 = (TextView) findViewById(R.id.possession2);
        lieu = (TextView) findViewById(R.id.etat);
        etat = (TextView) findViewById(R.id.lieu);
        //Set Text
        score1.setText(String.valueOf(match.getScoreEquipe1()));
        score2.setText(String.valueOf(match.getScoreEquipe2()));
        nomTeam1.setText(match.getEquipe1().getNom());
        nomTeam2.setText(match.getEquipe2().getNom());
        corner1.setText(String.valueOf(match.getCornerEquipe1()));
        corner2.setText(String.valueOf(match.getCornerEquipe2()));
        possession1.setText(String.valueOf(match.getPossessionEquipe1()) + "%");
        possession2.setText(String.valueOf(match.getPossessionEquipe2()) + "%");
        lieu.setText(match.getEtat());
        etat.setText(match.getLieu());
        ////---------------------///////

        quote1 = findViewById(R.id.quote1);
        quote2 = findViewById(R.id.quote2);
        quote3 = findViewById(R.id.quote3);
        quote4 = findViewById(R.id.quote4);
        quote5 = findViewById(R.id.quote5);
        quote6 = findViewById(R.id.quote6);
        quote7 = findViewById(R.id.quote7);
        quote8 = findViewById(R.id.quote8);
        quote9 = findViewById(R.id.quote9);

        titre1 = findViewById(R.id.titre1);
        titre2 = findViewById(R.id.titre2);
        titre3 = findViewById(R.id.titre3);

        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);
        button5 = findViewById(R.id.button5);
        button6 = findViewById(R.id.button6);
        button7 = findViewById(R.id.button7);
        button8 = findViewById(R.id.button8);
        button9 = findViewById(R.id.button9);

        background1 = findViewById(R.id.background1);
        background2 = findViewById(R.id.background2);
        background3 = findViewById(R.id.background3);

        background_mini1 = findViewById(R.id.background_mini1);
        background_mini2 = findViewById(R.id.background_mini2);
        background_mini3 = findViewById(R.id.background_mini3);

        titre1=findViewById(R.id.titre1);
        titre2=findViewById(R.id.titre2);
        titre3=findViewById(R.id.titre3);
        bar=findViewById(R.id.progressBar);
        ////---------------------///////
        id = match.getId();
        service = API.getClient().create(MatchRegle_Service.class);
        System.out.println("ID DE MATCH"+id);
        Initialise();
        DisableButton();
        loadFirstPage();
       /* Picasso.get()
                .load(post_URL+intent.getStringExtra("bannerpath"))
                .into(banner);
        Picasso.get()
                .load(back_URL+intent.getStringExtra("profilepath"))
                .into(profil);

        Log.e("MATCH ITOOOO", "ALLO : " + match.getLieu());
        */
    }

    @Override
    protected void onStart() {
        super.onStart();
        service = API.getClient().create(MatchRegle_Service.class);
    }

    private void loadFirstPage() {

        callToGetRegles(id).enqueue(new Callback<MatchRegle_Response>() {


            @Override
            public void onResponse(Call<MatchRegle_Response> call, retrofit2.Response<MatchRegle_Response> response) {

                List<MatchRegle> results = fetchResults(response);
                for(int i=0;i<results.get(0).getRegles().size();i++){
                    listQuotes.get(i).setText(String.valueOf(results.get(0).getRegles().get(i).getCote()));
                    listButton.get(i).setText(String.valueOf(results.get(0).getRegles().get(i).getRegle().getDefinition()));


                }
                EnableButton();
                System.out.println("RESULTS "+results.get(0).getRegles().size());
                bar.setVisibility(View.GONE);


            }


            @Override
            public void onFailure(Call<MatchRegle_Response> call, Throwable t) {
                t.printStackTrace();
                Log.e("EXCEPTION ATO ", t.getMessage());
            }
        });

    }

    private List<MatchRegle> fetchResults(retrofit2.Response<MatchRegle_Response> response) {
        System.out.println("Ito le response" + response.message());
        MatchRegle_Response allRegles = response.body();
        return allRegles.getResults();
    }


    private Call<MatchRegle_Response> callToGetRegles(String _id) {
        return service.getByID(_id);
    }
    private Call<MatchRegle_Response> callToGetReglesAll( ) {
        return service.getAllMatchesRegle();
    }


    //Fonctions pour initialisation
    public void Initialise() {
        listButton = new ArrayList<Button>();
        listQuotes = new ArrayList<>();
        listTitre = new ArrayList<>();

        listTitre.add(titre1);
        listTitre.add(titre2);
        listTitre.add(titre3);

        listQuotes.add(quote1);
        listQuotes.add(quote2);
        listQuotes.add(quote3);
        listQuotes.add(quote4);
        listQuotes.add(quote5);
        listQuotes.add(quote6);
        listQuotes.add(quote7);
        listQuotes.add(quote8);
        listQuotes.add(quote9);

        listButton.add(button1);
        listButton.add(button2);
        listButton.add(button3);
        listButton.add(button4);
        listButton.add(button5);
        listButton.add(button6);
        listButton.add(button7);
        listButton.add(button8);
        listButton.add(button9);
    }

    public void DisableButton() {
        if (!listButton.isEmpty()) {
            for (int i = 0; i<listButton.size();i++) {
                listButton.get(i).setVisibility(View.GONE);
            }
        }
    }
    public void EnableButton() {
        if (!listButton.isEmpty()) {
            for (int i = 0; i<listButton.size();i++) {
                listButton.get(i).setVisibility(View.VISIBLE);
            }
        }
    }
}
