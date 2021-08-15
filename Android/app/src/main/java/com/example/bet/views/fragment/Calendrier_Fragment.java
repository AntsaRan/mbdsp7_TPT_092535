package com.example.bet.views.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bet.API.API;
import com.example.bet.views.fragment.activity.Match_Details;
import com.example.bet.R;
import com.example.bet.adapter.PaginationAdapter_Match;
import com.example.bet.controleur.DataBaseHelper;
import com.example.bet.controleur.ItemClickSupport;
import com.example.bet.controleur.NetworkUtils;
import com.example.bet.controleur.PaginationScrollListener;
import com.example.bet.model.Match;
import com.example.bet.model.Match_Response;
import com.example.bet.service.Match_Service;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class Calendrier_Fragment extends Fragment {


    CalendarView calendarView;
    TextView text;

    PaginationAdapter_Match adapter;
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
    private int TOTAL_PAGES = 1;
    private int currentPage = PAGE_START;

    private Match_Service matchService;
    private DataBaseHelper database;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         view=inflater.inflate(R.layout.fragment_calendrier, container,false);
        calendarView=view.findViewById(R.id.calendarView);

        text = view.findViewById(R.id.date);
       final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
        //String selectedDate = sdf.format(new Date(calendarView.getDate()));
        //  Toast.makeText(getApplicationContext(), ""+dayOfMonth, 0).show();// TODO Auto-generated method stub

        text.setText(sdf.format(calendarView.getDate()));
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {

            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month,
                                            int dayOfMonth) {

                //String selectedDate = sdf.format(new Date(calendarView.getDate()));
              //  Toast.makeText(getApplicationContext(), ""+dayOfMonth, 0).show();// TODO Auto-generated method stub
               GregorianCalendar greg=new GregorianCalendar(year,month,dayOfMonth);
                progressBar.setVisibility(View.VISIBLE);
               // text.setText(dayOfMonth+"/"+month+"/"+year);
                text.setText(sdf.format(greg.getTime()));
                loadFirstPage();

            }
        });
        ////////////////////////
        rv = (RecyclerView) view.findViewById(R.id.main_recycler);
        progressBar = (ProgressBar) view.findViewById(R.id.main_progress);
        adapter = new PaginationAdapter_Match(view.getContext());
        setHasOptionsMenu(true);
        // rv.setLayoutManager(new GridLayoutManager(this, 2));
        //linearLayoutManager = new GridLayoutManager(this, 2);
        database=new DataBaseHelper(getActivity());
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
                //Toast.makeText(mContext, "MORE ITEMS", Toast.LENGTH_SHORT).show();
                // mocking network delay for API call
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //      loadNextPage();
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



        return view;
    }

    @Override
    public void onPause() {
        super.onPause();
    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
        if(NetworkUtils.networkStatus(getActivity())){
            matchService = API.getClient().create(Match_Service.class);
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if(!NetworkUtils.networkStatus(getActivity())){
            if(database.getOfflineMatch()!=null) {
                adapter.clear();
                progressBar.setVisibility(View.GONE);
                adapter.addAll(database.getOfflineMatch());
                if (currentPage <= TOTAL_PAGES) adapter.addLoadingFooter();
                else isLastPage = true;
                rv.setItemAnimator(new DefaultItemAnimator());
                rv.setAdapter(adapter);
            }
        }else {
            loadFirstPage();
        }
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

    }

    private void refreshList() {

        adapter = new PaginationAdapter_Match(view.getContext());

        linearLayoutManager = new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false);
        rv.setLayoutManager(linearLayoutManager);

        rv.setItemAnimator(new DefaultItemAnimator());

        rv.setAdapter(adapter);
        //init service and load data
        matchService = API.getClient().create(Match_Service.class);

        loadFirstPage();
    }

    private void configureOnClickRecyclerView() {
        ItemClickSupport.addTo(rv, R.layout.fragment_favorite)
                .setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
                    @Override
                    public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                        Log.e("TAG", "Position : " + position);



                        Match match = (Match) adapter.getItem(position);
                        Intent intent = new Intent(view.getContext(), Match_Details.class);
                       /* intent.putExtra("id", match.getId().toString());
                        intent.putExtra("equipe1", match.getEquipe1());
                        intent.putExtra("equipe2", match.getEquipe2());
                        intent.putExtra("date", match.getDate());
                        intent.putExtra("lieu", match.getLieu());
                        intent.putExtra("etat", match.getEtat());
                        intent.putExtra("scoreEquipe1", match.getScoreEquipe1());
                        intent.putExtra("scoreEquipe2", match.getScoreEquipe2());

                        */
                        intent.putExtra("match",match);
                     /*   for (int cmpt = 0; cmpt < match.getGenreIds().size(); cmpt++) {
                            intent.putExtra("genre" + cmpt, match.getGenreIds().get(cmpt).toString());
                        }
                        intent.putExtra("taille", String.valueOf(match.getGenreIds().size()));

                      */
                        startActivity(intent);


                    }
                });
    }

    private void loadFirstPage() {

        callToGetMatchByDate(text.getText().toString()).enqueue(new Callback<Match_Response>() {


            @Override
            public void onResponse(Call<Match_Response> call, retrofit2.Response<Match_Response> response) {

                if(response!=null){
                List<Match> results = fetchResults(response);
                if(results!=null){
                adapter.clear();
                progressBar.setVisibility(View.GONE);
                adapter.addAll(results);
                if (currentPage <= TOTAL_PAGES) adapter.addLoadingFooter();
                else isLastPage = true;
                rv.setItemAnimator(new DefaultItemAnimator());

                rv.setAdapter(adapter);
                    rv.setVisibility(View.VISIBLE);
                }else{
                    rv.setVisibility(View.GONE);
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(mContext, "Aucun resultat ", Toast.LENGTH_SHORT).show();
                }
                }
            }


            @Override
            public void onFailure(Call<Match_Response> call, Throwable t) {
                t.printStackTrace();
                Log.e("EXCEPTION ATO ", t.getMessage());
            }
        });

    }



    private List<Match> fetchResults(retrofit2.Response<Match_Response> response) {
        System.out.println("Ito le response"+ response);
        Match_Response searchResult = new Match_Response();

        if (response.body() != null)
            searchResult = response.body();
        return searchResult.getResults();
    }

    private List<Match> fetchSearchResults(retrofit2.Response<Match_Response> response) {
        Match_Response searchResult = new Match_Response();
        if (response.body() != null)
            searchResult = response.body();

        return searchResult.getResults();
    }

    private void loadNextPage() {

        callToGetMatchByDate(text.getText().toString()).enqueue(new Callback<Match_Response>() {


            @Override
            public void onResponse(Call<Match_Response> call, retrofit2.Response<Match_Response> response) {
                adapter.removeLoadingFooter();
                isLoading = false;
                if(response!=null){
                List<Match> results = fetchResults(response);
                adapter.addAll(results);

                if (currentPage != TOTAL_PAGES) adapter.addLoadingFooter();
                else isLastPage = true;
                }else{
                    adapter.clear();
                    rv.setItemAnimator(new DefaultItemAnimator());
                    rv.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<Match_Response> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    private Call<Match_Response> callToGetMatchByDate(String date) {
        return matchService.getAllMatchesByDate(date);
    }





    @Override
    public void onResume() {
        super.onResume();
    }
}
