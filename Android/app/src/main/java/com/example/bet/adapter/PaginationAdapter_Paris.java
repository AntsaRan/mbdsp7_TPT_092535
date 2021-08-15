package com.example.bet.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bet.R;
import com.example.bet.model.Paris_Match;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class PaginationAdapter_Paris extends RecyclerView.Adapter<RecyclerView.ViewHolder>  {

    private static final int ITEM = 0;
    private static final int LOADING = 1;
    private static final String BASE_URL_IMG = "https://image.tmdb.org/t/p/w185";

    private List<Paris_Match> matchResults;
    private List<Paris_Match> matchResultsALL;
    private List<Paris_Match> tmpMatch;
    private Context context;

    private boolean isLoadingAdded = false;

    public PaginationAdapter_Paris(Context context) {
        this.context = context;
        matchResults = new ArrayList<>();
        matchResultsALL = matchResults;
        tmpMatch = new ArrayList<>(matchResults);
    }

    public List<Paris_Match> getMovies() {
        return matchResults;
    }

    public void setMovies(List<Paris_Match> equipeResults) {
        this.matchResults = equipeResults;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        switch (viewType) {
            case ITEM:
                viewHolder = getViewHolder(parent, inflater);
                break;
            case LOADING:
                View v2 = inflater.inflate(R.layout.fragment_progress, parent, false);
                viewHolder = new PaginationAdapter_Paris.LoadingVH(v2);
                break;
        }
        return viewHolder;
    }

    @NonNull
    private RecyclerView.ViewHolder getViewHolder(ViewGroup parent, LayoutInflater inflater) {
        RecyclerView.ViewHolder viewHolder;
        View v1 = inflater.inflate(R.layout.fragment_paris_card_list, parent, false);

        viewHolder = new ParisVH(v1);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        Paris_Match result = matchResults.get(position); // Movie
        if(result!=null){
            switch (getItemViewType(position)) {
                case ITEM:
                    final PaginationAdapter_Paris.ParisVH matchVH = (PaginationAdapter_Paris.ParisVH) holder;


                    matchVH.idParis.setText(result.getId());
                    matchVH.mise.setText(String.valueOf(result.getMise()));
                    matchVH.idParis.setText(result.getId());
                    if(result.getMatch()!=null && result.getRegle()!=null){
                        matchVH.match.setText(result.getMatch().getEquipe1().getNom()+" VS "+ result.getMatch().getEquipe2().getNom());
                        matchVH.type.setText(result.getRegle().getDefinition());
                    }

                    SimpleDateFormat sdf = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss");
                    matchVH.date.setText(result.getDateParis());

                    //MBOLA ASIANA MATCH SY MATCH REGLE

                    break;

                case LOADING:
//                Do nothing
                    break;
            }
        }

    }

    @Override
    public int getItemCount() {
        return matchResults == null ? 0 : matchResults.size();
    }

    @Override
    public int getItemViewType(int position) {
        return (position == matchResults.size() - 1 && isLoadingAdded) ? LOADING : ITEM;
    }

    public void add(Paris_Match r) {
        matchResults.add(r);
        notifyItemInserted(matchResults.size() - 1);
    }

    public void addAll(List<Paris_Match> moveResults) {
        for (Paris_Match result : moveResults) {
            add(result);
        }

    }

    public void remove(Paris_Match r) {
        int position = matchResults.indexOf(r);
        if (position > -1) {
            matchResults.remove(position);
            notifyItemRemoved(position);
        }
    }

    public void clear() {
        isLoadingAdded = false;
        while (getItemCount() > 0) {
            remove(getItem(0));
        }
    }

    public boolean isEmpty() {
        return getItemCount() == 0;
    }


    public void addLoadingFooter() {
        isLoadingAdded = true;
        add(new Paris_Match());
    }

    public void removeLoadingFooter() {
        isLoadingAdded = false;

        int position = matchResults.size() - 1;
        Paris_Match result = getItem(position);

        if (result != null) {
            matchResults.remove(position);
            notifyItemRemoved(position);
        }
    }

    public Paris_Match getItem(int position) {
        return matchResults.get(position);
    }


    protected class ParisVH extends RecyclerView.ViewHolder {
        private TextView idParis;
        private TextView mise;
        private TextView date;
        private TextView match;
        private TextView type;
        public ParisVH(View itemView) {
            super(itemView);

            idParis = (TextView) itemView.findViewById(R.id.id_paris);

            mise = (TextView) itemView.findViewById(R.id.mise);
            date = (TextView) itemView.findViewById(R.id.date);
            match=(TextView) itemView.findViewById(R.id.match);
            type=(TextView) itemView.findViewById(R.id.type);
        }
    }


    protected class LoadingVH extends RecyclerView.ViewHolder {

        public LoadingVH(View itemView) {
            super(itemView);
        }
    }


}