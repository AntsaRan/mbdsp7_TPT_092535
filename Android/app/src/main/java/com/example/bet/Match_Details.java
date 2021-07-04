package com.example.bet;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bet.modele.Match;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

public class Match_Details extends AppCompatActivity {

    TextView score1,score2,nomTeam1,nomTeam2,etat,lieu,corner1,corner2,possession1,possession2;
    ImageView img1,img2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match__details);
        Match match = (Match) getIntent().getExtras().getParcelable("match");
        score1=(TextView)findViewById(R.id.score1);
        score2=(TextView)findViewById(R.id.score2);
        nomTeam1=(TextView)findViewById(R.id.nomEquipe1);
        nomTeam2=(TextView)findViewById(R.id.nomEquipe2);
        corner1=(TextView)findViewById(R.id.corner1);
        corner2=(TextView)findViewById(R.id.corner2);
        possession1=(TextView)findViewById(R.id.possession1);
        possession2=(TextView)findViewById(R.id.possession2);
        lieu=(TextView)findViewById(R.id.etat);
        etat=(TextView)findViewById(R.id.lieu);
        //Set Text
        score1.setText(String.valueOf(match.getScoreEquipe1()));
        score2.setText(String.valueOf(match.getScoreEquipe2()));
        nomTeam1.setText(match.getEquipe1().getNom());
        nomTeam2.setText(match.getEquipe2().getNom());
        corner1.setText(String.valueOf(match.getCornerEquipe1()));
        corner2.setText(String.valueOf(match.getCornerEquipe2()));
        possession1.setText(String.valueOf(match.getPossessionEquipe1())+"%");
        possession2.setText(String.valueOf(match.getPossessionEquipe2())+"%");
        lieu.setText(match.getEtat());
        etat.setText(match.getLieu());

       /* Picasso.get()
                .load(post_URL+intent.getStringExtra("bannerpath"))
                .into(banner);
        Picasso.get()
                .load(back_URL+intent.getStringExtra("profilepath"))
                .into(profil);

        Log.e("MATCH ITOOOO", "ALLO : " + match.getLieu());
        */
    }
}