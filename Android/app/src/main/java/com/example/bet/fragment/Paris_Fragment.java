package com.example.bet.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bet.API.API;
import com.example.bet.R;
import com.example.bet.adapter.PaginationAdapter_Paris;
import com.example.bet.controleur.DataBaseHelper;
import com.example.bet.controleur.ItemClickSupport;
import com.example.bet.controleur.PaginationScrollListener;
import com.example.bet.modele.Equipe;
import com.example.bet.modele.Equipe_Response;
import com.example.bet.modele.Match;
import com.example.bet.modele.Match_Response;
import com.example.bet.modele.Paris_Match;
import com.example.bet.modele.Paris_Response;
import com.example.bet.modele.Regle;
import com.example.bet.modele.Regle_Response;
import com.example.bet.modele.Utilisateur;
import com.example.bet.service.Equipe_Service;
import com.example.bet.service.Match_Service;
import com.example.bet.service.Paris_Service;
import com.example.bet.service.Regle_Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Paris_Fragment extends Fragment implements  MenuItem.OnActionExpandListener {
    PaginationAdapter_Paris adapter;
    LinearLayoutManager linearLayoutManager;
    private Context mContext;
    RecyclerView rv;
    ProgressBar progressBar;
    View view;
    FrameLayout frameLayout;
    private DataBaseHelper database;
    private static final int PAGE_START = 1;
    private boolean isLoading = false;
    private boolean isLastPage = false;
    // limiting to 5 for this tutorial, since total pages in actual API is very large. Feel free to modify.
    private int TOTAL_PAGES = 1;
    private int currentPage = PAGE_START;

    private Paris_Service paris_service;
    private Regle_Service regle_service;
    private Match_Service match_service;
private Utilisateur utilisateur;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_paris_list, container, false);
        rv = (RecyclerView) view.findViewById(R.id.recycler_view);
        progressBar = (ProgressBar) view.findViewById(R.id.main_progress);
        adapter = new PaginationAdapter_Paris(view.getContext());
        database=new DataBaseHelper(getActivity());
        setHasOptionsMenu(true);
        // rv.setLayoutManager(new GridLayoutManager(this, 2));
        //linearLayoutManager = new GridLayoutManager(this, 2);
        if(database.getUtilisateur()!=null){
          utilisateur=database.getUtilisateur();

        }
        linearLayoutManager = new LinearLayoutManager(view.getContext());
        rv.setLayoutManager(linearLayoutManager);

        rv.setItemAnimator(new DefaultItemAnimator());

        rv.setAdapter(adapter);
        configureOnClickRecyclerView();
       /* rv.addOnScrollListener(new PaginationScrollListener(linearLayoutManager) {
            @Override
            protected void loadMoreItems() {
                if (currentPage < TOTAL_PAGES) {
                    isLoading = true;
                    currentPage += 1;
                    Log.e("LOAD MORE ITEMS ", Integer.toString(currentPage));
                    Toast.makeText(mContext, "MORE ITEMS", Toast.LENGTH_SHORT).show();
                    // mocking network delay for API call
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            loadNextPage();
                        }
                    }, 3000);
                }
            }

            @Override
            public int getTotalPageCount() {
                return TOTAL_PAGES;
            }

            @Override
            public boolean isLastPage() {
                return isLastPage;
            }

            @Override
            public boolean isLoading() {
                return isLoading;
            }
        });

        */
        return view;
    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;

        paris_service = API.getClient().create(Paris_Service.class);
        regle_service = API.getClient().create(Regle_Service.class);
        match_service = API.getClient().create(Match_Service.class);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        loadFirstPage();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        loadFirstPage();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

    }

    private void refreshList() {

        adapter = new PaginationAdapter_Paris(view.getContext());

        linearLayoutManager = new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false);
        rv.setLayoutManager(linearLayoutManager);

        rv.setItemAnimator(new DefaultItemAnimator());

        rv.setAdapter(adapter);
        //init service and load data
        paris_service = API.getClient().create(Paris_Service.class);

        loadFirstPage();
    }

    private void configureOnClickRecyclerView() {
        ItemClickSupport.addTo(rv, R.layout.fragment_favorite)
                .setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
                    @Override
                    public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                        Log.e("TAG", "Position : " + position);


/*
                        Equipe equipe = (Equipe) adapter.getItem(position);
                        Intent intent = new Intent(view.getContext(), Equipe_Fragment.class);
                        intent.putExtra("id", equipe.getId().toString());
                        intent.putExtra("title", equipe.getTitle());
                        intent.putExtra("bannerpath", equipe.getBackdropPath());
                        intent.putExtra("overview", equipe.getOverview());
                        intent.putExtra("release", equipe.getReleaseDate());
                        intent.putExtra("profilepath", equipe.getPosterPath());
                        for (int cmpt = 0; cmpt < equipe.getGenreIds().size(); cmpt++) {
                            intent.putExtra("genre" + cmpt, equipe.getGenreIds().get(cmpt).toString());
                        }
                        intent.putExtra("taille", String.valueOf(equipe.getGenreIds().size()));
                        startActivity(intent);

 */
                    }
                });
    }

    private void loadFirstPage() {

        callGetAllParis(utilisateur.getId()).enqueue(new Callback<Paris_Response>() {


            @Override
            public void onResponse(Call<Paris_Response> call, retrofit2.Response<Paris_Response> response) {

                List<Paris_Match> results = fetchResults(response);
               if(results!=null) {
                   adapter.clear();
                   progressBar.setVisibility(View.GONE);

                   adapter.addAll(results);
                   if (currentPage <= TOTAL_PAGES) adapter.addLoadingFooter();
                   else isLastPage = true;

                   rv.setItemAnimator(new DefaultItemAnimator());

                   rv.setAdapter(adapter);

               }else{
                   progressBar.setVisibility(View.GONE);
                   Toast.makeText(mContext, "Aucun resultat ", Toast.LENGTH_SHORT).show();
               }
            }


            @Override
            public void onFailure(Call<Paris_Response> call, Throwable t) {
                t.printStackTrace();
                Log.e("EXCEPTION ATO ", t.getMessage());
            }
        });

    }


    private List<Paris_Match> fetchResults(retrofit2.Response<Paris_Response> response) {
        System.out.println("Ito le response" + response);
        Paris_Response result = response.body();

        List<Paris_Match> list=null;
        if(response.body()!=null){
            list=new ArrayList<>();
            for(int i=0;i<result.getResults().size();i++){
                final Paris_Match paris=new Paris_Match();
                paris.setDateParis(result.getResults().get(i).getDateParis().toString());
                paris.setId(result.getResults().get(i).getId());
                paris.setMise(result.getResults().get(i).getMise());
                /////
                callGetMatchById(result.getResults().get(i).getIdMatch()).enqueue(new Callback<Match_Response>() {
                    @Override
                    public void onResponse(Call<Match_Response> call, Response<Match_Response> response) {
                        List<Match> results = fetchResultsMatch(response);
                        paris.setMatch(results.get(0));
                        System.out.println("MATCH NALAINA"+paris.getMatch().getLieu());
                    }

                    @Override
                    public void onFailure(Call<Match_Response> call, Throwable t) {

                    }
                });

                callGetRegleById(result.getResults().get(i).getMatchRegle()).enqueue(new Callback<Regle_Response>() {
                    @Override
                    public void onResponse(Call<Regle_Response> call, Response<Regle_Response> response) {
                        List<Regle> results = fetchResultsRegle(response);
                        paris.setRegle(results.get(0));
                        System.out.println("MATCH NALAINA"+paris.getRegle().getDefinition());

                    }

                    @Override
                    public void onFailure(Call<Regle_Response> call, Throwable t) {

                    }
                });
                /////
                list.add(paris);
            }
        }
        System.out.println("ZAY VOA VITA ");
        return list;
    }
    private List<Match> fetchResultsMatch(retrofit2.Response<Match_Response> response) {
        System.out.println("Ito le response" + response);
        Match_Response result = response.body();


        return result.getResults();
    }
    private List<Regle> fetchResultsRegle(retrofit2.Response<Regle_Response> response) {
        System.out.println("Ito le response" + response);
        Regle_Response result = response.body();


        return result.getResults();
    }

   private void loadNextPage() {

       callGetAllParis(utilisateur.getId()).enqueue(new Callback<Paris_Response>() {


            @Override
            public void onResponse(Call<Paris_Response> call, retrofit2.Response<Paris_Response> response) {
                adapter.removeLoadingFooter();
                isLoading = false;

                List<Paris_Match> results = fetchResults(response);
                adapter.addAll(results);

                if (currentPage != TOTAL_PAGES) adapter.addLoadingFooter();
                else isLastPage = true;
            }

            @Override
            public void onFailure(Call<Paris_Response> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }



    private Call<Paris_Response> callGetAllParis(String idUtilisateur) {
        return paris_service.getAllParisByUser(idUtilisateur);
    }
    private Call<Regle_Response> callGetRegleById(String idRegle) {
        return regle_service.getByID(idRegle);
    }
    private Call<Match_Response> callGetMatchById(String idMatch) {
        return match_service.getByID(idMatch);
    }
    @Override
    public boolean onMenuItemActionExpand(MenuItem menuItem) {
        return false;
    }

    @Override
    public boolean onMenuItemActionCollapse(MenuItem menuItem) {
        return false;
    }







    @Override
    public void onResume() {
        super.onResume();
    }
}