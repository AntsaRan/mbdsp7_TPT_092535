package com.example.bet.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bet.R;
import com.example.bet.modele.HistoriqueJetons;
import com.example.bet.modele.Paris_Match;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class PaginationAdapter_Jetons extends RecyclerView.Adapter<RecyclerView.ViewHolder>  {

    private static final int ITEM = 0;
    private static final int LOADING = 1;
    private static final String BASE_URL_IMG = "https://image.tmdb.org/t/p/w185";

    private List<HistoriqueJetons> matchResults;
    private List<HistoriqueJetons> matchResultsALL;
    private List<HistoriqueJetons> tmpMatch;
    private Context context;

    private boolean isLoadingAdded = false;

    public PaginationAdapter_Jetons(Context context) {
        this.context = context;
        matchResults = new ArrayList<>();
        matchResultsALL = matchResults;
        tmpMatch = new ArrayList<>(matchResults);
    }

    public List<HistoriqueJetons> getMovies() {
        return matchResults;
    }

    public void setMovies(List<HistoriqueJetons> equipeResults) {
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
                viewHolder = new PaginationAdapter_Jetons.LoadingVH(v2);
                break;
        }
        return viewHolder;
    }

    @NonNull
    private RecyclerView.ViewHolder getViewHolder(ViewGroup parent, LayoutInflater inflater) {
        RecyclerView.ViewHolder viewHolder;
        View v1 = inflater.inflate(R.layout.fragment_jetons_card_list, parent, false);

        viewHolder = new ParisVH(v1);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        HistoriqueJetons result = matchResults.get(position); // Movie
        if(result!=null){
            switch (getItemViewType(position)) {
                case ITEM:
                    final PaginationAdapter_Jetons.ParisVH matchVH = (PaginationAdapter_Jetons.ParisVH) holder;


                    matchVH.montant.setText(String.valueOf(result.getMontant()));
                    matchVH.type.setText(result.getIdTransaction());

                     matchVH.date.setText(result.getDateTransaction());

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

    public void add(HistoriqueJetons r) {
        matchResults.add(r);
        notifyItemInserted(matchResults.size() - 1);
    }

    public void addAll(List<HistoriqueJetons> moveResults) {
        for (HistoriqueJetons result : moveResults) {
            add(result);
        }

    }

    public void remove(HistoriqueJetons r) {
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
        add(new HistoriqueJetons());
    }

    public void removeLoadingFooter() {
        isLoadingAdded = false;

        int position = matchResults.size() - 1;
        HistoriqueJetons result = getItem(position);

        if (result != null) {
            matchResults.remove(position);
            notifyItemRemoved(position);
        }
    }

    public HistoriqueJetons getItem(int position) {
        return matchResults.get(position);
    }


    protected class ParisVH extends RecyclerView.ViewHolder {

        private TextView montant;
        private TextView date;
        private TextView type;
        public ParisVH(View itemView) {
            super(itemView);



            montant = (TextView) itemView.findViewById(R.id.montant);
            date = (TextView) itemView.findViewById(R.id.date);
            type=(TextView) itemView.findViewById(R.id.type);
        }
    }


    protected class LoadingVH extends RecyclerView.ViewHolder {

        public LoadingVH(View itemView) {
            super(itemView);
        }
    }


}