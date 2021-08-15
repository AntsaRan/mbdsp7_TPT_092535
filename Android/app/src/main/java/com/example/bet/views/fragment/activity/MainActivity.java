package com.example.bet.views.fragment.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.example.bet.API.API;
import com.example.bet.R;
import com.example.bet.controleur.DataBaseHelper;
import com.example.bet.views.fragment.Achat_Fragment;
import com.example.bet.views.fragment.Calendrier_Fragment;
import com.example.bet.views.fragment.Equipe_Fragment;
import com.example.bet.views.fragment.Jetons_Fragment;
import com.example.bet.views.fragment.ListeMatchs_Fragment;
import com.example.bet.views.fragment.Paris_Fragment;
import com.example.bet.views.fragment.Profil_Fragment;
import com.example.bet.views.fragment.Vente_Fragment;
import com.example.bet.model.RequestDevice;
import com.example.bet.model.Utilisateur;
import com.example.bet.service.Device_Service;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.FirebaseApp;
import com.google.firebase.messaging.FirebaseMessaging;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.GravityCompat;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;

public class MainActivity extends AppCompatActivity  implements NavigationView.OnNavigationItemSelectedListener{

    private AppBarConfiguration mAppBarConfiguration;
    private DrawerLayout drawer;
    NavigationView navigationView;
    FloatingActionButton panierButton;
    ConstraintLayout panier;
    Boolean isAllFabsVisible;
    SharedPreferences pref;
    DataBaseHelper database;
    Device_Service service;
    Utilisateur user;
   /* @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
         drawer = findViewById(R.id.drawer_layout);
         navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home,R.id.nav_liste_matchs,R.id.nav_profil,R.id.nav_achat,R.id.nav_calendrier, R.id.nav_gallery, R.id.nav_slideshow)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    */

   ///////////TEST////////////////////
   @Override
   protected void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_main);
       isAllFabsVisible = false;
       //ToolBar process
       Toolbar toolbar = findViewById(R.id.toolbar);
       setSupportActionBar(toolbar);
       drawer = findViewById(R.id.drawer_layout);
       navigationView = findViewById(R.id.nav_view);
      // panierButton=findViewById(R.id.fab);
       panier=findViewById(R.id.constraint);
       panier.setVisibility(View.GONE);
       /*panierButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               if(!isAllFabsVisible){
                   panier.setVisibility(View.VISIBLE);
                   isAllFabsVisible=true;
               }else{
                   panier.setVisibility(View.GONE);
                   isAllFabsVisible=false;
               }
           }
       });
        */
       service = API.getClient().create(Device_Service.class);
       GetNotification();
       database=new DataBaseHelper(this);
       if(database.getUtilisateur()!=null){
           user=database.getUtilisateur();
       }
       navigationView.setNavigationItemSelectedListener(this);
       ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
       drawer.addDrawerListener(toggle);
       toggle.syncState();
       pref = getSharedPreferences("user_details",MODE_PRIVATE);
       if (savedInstanceState == null) {
           getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, new Profil_Fragment()).commit();
           navigationView.setCheckedItem(R.id.nav_profil);

       }


   }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        switch (menuItem.getItemId()) {


            case R.id.nav_profil:
                getSupportFragmentManager().clearFragmentResultListener(null);
                getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, new Profil_Fragment()).commit();

                break;

            case R.id.nav_achat:
                getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, new Achat_Fragment()).commit();
                //Toast.makeText(Home.this, "Share", Toast.LENGTH_SHORT).show();
              /* Intent intent1 = new Intent(MainActivity.this, Achat.class);
                startActivity(intent1);

               */
                break;
            case R.id.nav_equipe:
                getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, new Equipe_Fragment()).commit();
                //Toast.makeText(Home.this, "Share", Toast.LENGTH_SHORT).show();
              /* Intent intent1 = new Intent(MainActivity.this, Achat.class);
                startActivity(intent1);

               */
                break;
            case R.id.nav_liste_matchs:
                getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, new ListeMatchs_Fragment()).addToBackStack(null).commit();

                break;
            case R.id.nav_historique_jetons:
                getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, new Jetons_Fragment()).addToBackStack(null).commit();
                break;

            case R.id.nav_historique_paris:
                getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, new Paris_Fragment()).addToBackStack(null).commit();

                break;
            case R.id.nav_vente:
                getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, new Vente_Fragment()).commit();
                //Toast.makeText(Home.this, "Share", Toast.LENGTH_SHORT).show();
              /* Intent intent1 = new Intent(MainActivity.this, Achat.class);
                startActivity(intent1);

               */
                break;
            case R.id.nav_calendrier:
                getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, new Calendrier_Fragment()).commit();
                //Toast.makeText(Home.this, "Share", Toast.LENGTH_SHORT).show();
              /* Intent intent1 = new Intent(MainActivity.this, Achat.class);
                startActivity(intent1);

               */
                break;
              case R.id.nav_logout:
                  database.deleteUser();
                  Intent intent = new Intent(MainActivity.this, Login.class);
                  intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                  startActivity(intent);
                  break;

        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

  /*  @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
       // getMenuInflater().inflate(R.menu.menu_search, menu);
        return true;
    }
*/
/*    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

 */
  public void GetNotification(){
      FirebaseApp.initializeApp(this);

      FirebaseMessaging.getInstance().getToken()
              .addOnCompleteListener(new OnCompleteListener<String>() {
                  @Override
                  public void onComplete(@NonNull Task<String> task) {
                      if (!task.isSuccessful()) {
                          Log.w(TAG, "Fetching FCM registration token failed", task.getException());
                          return;
                      }

                      // Get new FCM registration token
                      String token = task.getResult();

                      // Log and toast
                      String msg = token;
                      insertDevice(user.getId(),token).enqueue(new Callback<String>() {
                          @Override
                          public void onResponse(Call<String> call, Response<String> response) {
                              //Do nothing
                              Log.d("INSERTION ITO", response.body());
                          }

                          @Override
                          public void onFailure(Call<String> call, Throwable t) {

                          }
                      });
                      Log.d("token", msg);
                 }


              });
  }
    private Call<String> insertDevice(String idUtilisateur, String token) {
        RequestDevice device=new RequestDevice(idUtilisateur,token);
        return service.insererDevice(device);
    }
}