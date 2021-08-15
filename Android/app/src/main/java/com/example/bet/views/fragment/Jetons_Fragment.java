package com.example.bet.views.fragment;

import android.content.Context;
import android.os.Bundle;
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
import com.example.bet.adapter.PaginationAdapter_Jetons;
import com.example.bet.controleur.DataBaseHelper;
import com.example.bet.controleur.ItemClickSupport;
import com.example.bet.model.HistoriqueJetons;
import com.example.bet.model.HistoriqueJetons_Response;
import com.example.bet.model.Match_Response;
import com.example.bet.model.Regle;
import com.example.bet.model.Regle_Response;
import com.example.bet.model.Utilisateur;
import com.example.bet.service.Jeton_Service;
import com.example.bet.service.Match_Service;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Jetons_Fragment extends Fragment implements  MenuItem.OnActionExpandListener {
    PaginationAdapter_Jetons adapter;
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

    private Jeton_Service jeton_service;
    private Match_Service match_service;
private Utilisateur utilisateur;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_jetons_list, container, false);
        rv = (RecyclerView) view.findViewById(R.id.recycler_view);
        progressBar = (ProgressBar) view.findViewById(R.id.main_progress);
        adapter = new PaginationAdapter_Jetons(view.getContext());
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

        jeton_service = API.getClient().create(Jeton_Service.class);
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

        adapter = new PaginationAdapter_Jetons(view.getContext());

        linearLayoutManager = new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false);
        rv.setLayoutManager(linearLayoutManager);

        rv.setItemAnimator(new DefaultItemAnimator());

        rv.setAdapter(adapter);
        //init service and load data
        jeton_service = API.getClient().create(Jeton_Service.class);

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

        callGetAllHisto(utilisateur.getId()).enqueue(new Callback<HistoriqueJetons_Response>() {


            @Override
            public void onResponse(Call<HistoriqueJetons_Response> call, Response<HistoriqueJetons_Response> response) {

                List<HistoriqueJetons> results = fetchResultsJeton(response);
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
            public void onFailure(Call<HistoriqueJetons_Response> call, Throwable t) {
                t.printStackTrace();
                Log.e("EXCEPTION ATO ", t.getMessage());
            }
        });

    }


   private List<HistoriqueJetons> fetchResultsJeton(Response<HistoriqueJetons_Response> response) {
        System.out.println("Ito le response" + response);
        HistoriqueJetons_Response result = response.body();


        return result.getResults();
    }
    private List<Regle> fetchResultsRegle(Response<Regle_Response> response) {
        System.out.println("Ito le response" + response);
        Regle_Response result = response.body();


        return result.getResults();
    }

   private void loadNextPage() {

       callGetAllHisto(utilisateur.getId()).enqueue(new Callback<HistoriqueJetons_Response>() {


            @Override
            public void onResponse(Call<HistoriqueJetons_Response> call, Response<HistoriqueJetons_Response> response) {
                adapter.removeLoadingFooter();
                isLoading = false;

                List<HistoriqueJetons> results = fetchResultsJeton(response);
                adapter.addAll(results);

                if (currentPage != TOTAL_PAGES) adapter.addLoadingFooter();
                else isLastPage = true;
            }

            @Override
            public void onFailure(Call<HistoriqueJetons_Response> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }



    private Call<HistoriqueJetons_Response> callGetAllHisto (String idUtilisateur) {
        return jeton_service.getAllHistoriqueJetons(idUtilisateur);
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