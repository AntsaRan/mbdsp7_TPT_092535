package com.example.bet;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;

import com.example.bet.fragment.Achat;
import com.example.bet.fragment.Achat_Fragment;
import com.example.bet.fragment.Calendrier_Fragment;
import com.example.bet.fragment.Equipe_Fragment;
import com.example.bet.fragment.ListeMatchs_Fragment;
import com.example.bet.fragment.Profil_Fragment;
import com.example.bet.fragment.Vente_Fragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.GravityCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity  implements NavigationView.OnNavigationItemSelectedListener{

    private AppBarConfiguration mAppBarConfiguration;
    private DrawerLayout drawer;
    NavigationView navigationView;
    FloatingActionButton panierButton;
    ConstraintLayout panier;
    Boolean isAllFabsVisible;
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
       panierButton=findViewById(R.id.fab);
       panier=findViewById(R.id.constraint);
       panier.setVisibility(View.GONE);
       panierButton.setOnClickListener(new View.OnClickListener() {
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
       navigationView.setNavigationItemSelectedListener(this);
       ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
       drawer.addDrawerListener(toggle);
       toggle.syncState();
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
              /* case R.id.nav_calendrier:
                mAuth.signOut();
                Intent intent = new Intent(Home.this, Login.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                break;

              */
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

}