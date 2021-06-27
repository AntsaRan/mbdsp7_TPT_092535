package com.example.bet.fragment;

import androidx.fragment.app.Fragment;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.bet.R;
import com.example.bet.adapter.PaginationAdapter_Equipe;
import com.example.bet.service.Equipe_Service;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class Equipe_Fragment extends Fragment{
    PaginationAdapter_Equipe adapter;
    LinearLayoutManager linearLayoutManager;
    private Context mContext;
    RecyclerView rv;
    ProgressBar progressBar;
    View view;
    FrameLayout frameLayout;
    private static final int PAGE_START = 1;
    private boolean isLoading = false;
    private boolean isLastPage = false;
    // limiting to 5 for this tutorial, since total pages in actual API is very large. Feel free to modify.
    private int TOTAL_PAGES = 500;
    private int currentPage = PAGE_START;

    private Equipe_Service movieService;
/*
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_favorite, container, false);
        rv = (RecyclerView) view.findViewById(R.id.main_recycler);
        progressBar = (ProgressBar) view.findViewById(R.id.main_progress);
        adapter = new PaginationAdapter(view.getContext());
        frameLayout = (FrameLayout) view.findViewById(R.id.frameLayout);
        setHasOptionsMenu(true);
        // rv.setLayoutManager(new GridLayoutManager(this, 2));
        //linearLayoutManager = new GridLayoutManager(this, 2);

        linearLayoutManager = new LinearLayoutManager(view.getContext());
        rv.setLayoutManager(linearLayoutManager);

        rv.setItemAnimator(new DefaultItemAnimator());

        rv.setAdapter(adapter);
        configureOnClickRecyclerView();
        rv.addOnScrollListener(new PaginationScrollListener(linearLayoutManager) {
            @Override
            protected void loadMoreItems() {

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
        Load_Setting();
        return view;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {

        inflater.inflate(R.menu.menu_search, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setOnQueryTextListener(this);
        searchView.setQueryHint("Search");

        super.onCreateOptionsMenu(menu, inflater);

        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;

        movieService = API.getClient().create(Service.class);
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

        adapter = new PaginationAdapter(view.getContext());

        linearLayoutManager = new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false);
        rv.setLayoutManager(linearLayoutManager);

        rv.setItemAnimator(new DefaultItemAnimator());

        rv.setAdapter(adapter);
        //init service and load data
        movieService = API.getClient().create(Service.class);

        loadFirstPage();
    }

    private void configureOnClickRecyclerView() {
        ItemClickSupport.addTo(rv, R.layout.fragment_favorite)
                .setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
                    @Override
                    public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                        Log.e("TAG", "Position : " + position);

                        Movie movie = (Movie) adapter.getItem(position);
                        Intent intent = new Intent(view.getContext(), MovieDetails.class);
                        intent.putExtra("id", movie.getId().toString());
                        intent.putExtra("title", movie.getTitle());
                        intent.putExtra("bannerpath", movie.getBackdropPath());
                        intent.putExtra("overview", movie.getOverview());
                        intent.putExtra("release", movie.getReleaseDate());
                        intent.putExtra("profilepath", movie.getPosterPath());
                        for (int cmpt = 0; cmpt < movie.getGenreIds().size(); cmpt++) {
                            intent.putExtra("genre" + cmpt, movie.getGenreIds().get(cmpt).toString());
                        }
                        intent.putExtra("taille", String.valueOf(movie.getGenreIds().size()));
                        startActivity(intent);
                    }
                });
    }

    private void loadFirstPage() {

        callTopRatedMoviesApi().enqueue(new Callback<MoviesResponse>() {


            @Override
            public void onResponse(Call<MoviesResponse> call, retrofit2.Response<MoviesResponse> response) {
                List<Movie> results = fetchResults(response);
                adapter.clear();
                progressBar.setVisibility(View.GONE);

                adapter.addAll(results);
                if (currentPage <= TOTAL_PAGES) adapter.addLoadingFooter();
                else isLastPage = true;

                rv.setItemAnimator(new DefaultItemAnimator());

                rv.setAdapter(adapter);
            }


            @Override
            public void onFailure(Call<MoviesResponse> call, Throwable t) {
                t.printStackTrace();
                Log.e("EXCEPTION ATO ", t.getMessage());
            }
        });

    }

    private void searchMovies(String name) {

        callSearchMoviesApi(name).enqueue(new Callback<MoviesResponse>() {


            @Override
            public void onResponse(Call<MoviesResponse> call, retrofit2.Response<MoviesResponse> response) {
                List<Movie> results = fetchSearchResults(response);
                if (results != null) {
                    adapter.clear();
                    progressBar.setVisibility(View.GONE);

                    adapter.addAll(results);
                    if (currentPage <= TOTAL_PAGES) adapter.addLoadingFooter();
                    else isLastPage = true;

                    rv.setItemAnimator(new DefaultItemAnimator());
                    rv.setAdapter(adapter);
                }
            }


            @Override
            public void onFailure(Call<MoviesResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });

    }

    /**
     * @param response extracts List<{@link Movie>} from response
     * @return
     */
   /* private List<Movie> fetchResults(retrofit2.Response<MoviesResponse> response) {
        MoviesResponse topRatedMovies = response.body();
        return topRatedMovies.getResults();
    }

    private List<Movie> fetchSearchResults(retrofit2.Response<MoviesResponse> response) {
        MoviesResponse searchResult = new MoviesResponse();
        if (response.body() != null)
            searchResult = response.body();

        return searchResult.getResults();
    }

    private void loadNextPage() {

        callTopRatedMoviesApi().enqueue(new Callback<MoviesResponse>() {


            @Override
            public void onResponse(Call<MoviesResponse> call, retrofit2.Response<MoviesResponse> response) {
                adapter.removeLoadingFooter();
                isLoading = false;

                List<Movie> results = fetchResults(response);
                adapter.addAll(results);

                if (currentPage != TOTAL_PAGES) adapter.addLoadingFooter();
                else isLastPage = true;
            }

            @Override
            public void onFailure(Call<MoviesResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    private Call<MoviesResponse> callTopRatedMoviesApi() {
        return movieService.getTopRatedMovies(
                API.API_TOKEN,
                currentPage
        );
    }

    private Call<MoviesResponse> callSearchMoviesApi(String name) {
        return movieService.searchMovies(
                API.API_TOKEN, name,
                1
        );
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
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {

        if (s != "")
            searchMovies(s);
        else
            loadFirstPage();
        adapter.getFilter().filter(s);
        return false;
    }

    private void Load_Setting() {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(view.getContext());
        boolean chk_night = sp.getBoolean("NIGHT", false);
        if (chk_night) {

            frameLayout.setBackgroundColor(Color.parseColor("#222222"));
        } else {


            frameLayout.setBackgroundColor(Color.parseColor("#FFFFFF"));
        }

    }

    @Override
    public void onResume() {
        Load_Setting();
        super.onResume();
    }

    */

}

