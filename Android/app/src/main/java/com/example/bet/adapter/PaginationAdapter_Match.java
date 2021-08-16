package com.example.bet.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.Adapter;

import com.example.bet.R;
import com.example.bet.model.Match;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by delaroy on 12/5/17.
 */

public class PaginationAdapter_Match extends Adapter<RecyclerView.ViewHolder> implements Filterable {

    private static final int ITEM = 0;
    private static final int LOADING = 1;
    private static final String BASE_URL_IMG = "https://image.tmdb.org/t/p/w185";

    private List<Match> matchResults;
    private List<Match> matchResultsALL;
    private List<Match> tmpMatch;
    private Context context;

    private boolean isLoadingAdded = false;

    public PaginationAdapter_Match(Context context) {
        this.context = context;
        matchResults = new ArrayList<>();
        matchResultsALL = matchResults;
        tmpMatch = new ArrayList<>(matchResults);
    }

    public List<Match> getMovies() {
        return matchResults;
    }

    public void setMovies(List<Match> equipeResults) {
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
                viewHolder = new LoadingVH(v2);
                break;
        }
        return viewHolder;
    }

    @NonNull
    private RecyclerView.ViewHolder getViewHolder(ViewGroup parent, LayoutInflater inflater) {
        RecyclerView.ViewHolder viewHolder;
        View v1 = inflater.inflate(R.layout.fragment_card_match, parent, false);

        viewHolder = new MatchVH(v1);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        Match result = matchResults.get(position); // Movie
        if(result!=null){
            switch (getItemViewType(position)) {
                case ITEM:
                    final MatchVH matchVH = (MatchVH) holder;

                    matchVH.mEquipeNom1.setText(result.getEquipe1().getNom());
                    matchVH.mEquipeNom2.setText(result.getEquipe2().getNom());

                    matchVH.score1.setText(String.valueOf(result.getScoreEquipe1()));
                    matchVH.score2.setText(String.valueOf(result.getScoreEquipe2()));

                    SimpleDateFormat sdf = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss");
                    matchVH.date.setText(sdf.format(result.getDate()));

                   // System.out.println("LOGO "+result.getLogo());

                    Picasso.get().load( result.getEquipe1().getLogo())
                            .placeholder(R.drawable.ic_launcher_foreground)
                            .into(matchVH.mPosterImg1);
                    Picasso.get().load(result.getEquipe2().getLogo())
                            .placeholder(R.drawable.ic_launcher_foreground)
                            .into(matchVH.mPosterImg2);
                    /**
                     * Using Glide to handle image loading.
                     * Learn more about Glide here:
                     *
                     */
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

    public void add(Match r) {
        matchResults.add(r);
        notifyItemInserted(matchResults.size() - 1);
    }

    public void addAll(List<Match> moveResults) {
        for (Match result : moveResults) {
            add(result);
        }

    }

    public void remove(Match r) {
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
        add(new Match());
    }

    public void removeLoadingFooter() {
        isLoadingAdded = false;

        int position = matchResults.size() - 1;
        Match result = getItem(position);

        if (result != null) {
            matchResults.remove(position);
            notifyItemRemoved(position);
        }
    }

    public Match getItem(int position) {
        return matchResults.get(position);
    }

    @Override
    public Filter getFilter() {
        return filter;
    }
    Filter filter=new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
           List<Match> filteredList = new ArrayList<>();
            if(matchResults.size()==0)
                matchResults = new ArrayList<>(tmpMatch);
            matchResultsALL = new ArrayList<>(matchResults);
            if(charSequence.toString().isEmpty()){
                filteredList.addAll(tmpMatch);
            }else{
                Log.e("MOVIE","TAILLE"+ matchResultsALL.size());
                if(!matchResultsALL.isEmpty()) {
                    for (int i = 0; i < matchResultsALL.size() - 1; i++) {
                        if (matchResultsALL.get(i) != null) {
                            if (matchResultsALL.get(i).getEquipe1().getNom().toLowerCase().contains(charSequence.toString().toLowerCase())) {
                                filteredList.add(matchResultsALL.get(i));
                            }
                        }
                    }
                }

            }
            FilterResults filterResults = new FilterResults();
            filterResults.values = filteredList;
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            if(matchResultsALL.size()>=0)
                tmpMatch.addAll(matchResults);
            matchResults.clear();
            if(filterResults.values!=null){
                matchResults.addAll((Collection<?extends Match>)filterResults.values);
                notifyDataSetChanged();
            }
        }
    };
    protected class MatchVH extends RecyclerView.ViewHolder {
        private TextView mEquipeNom1;
        private TextView mEquipeNom2;
        private TextView score1;
        private TextView score2;
        private TextView date;
        private ImageView mPosterImg1;
        private ImageView mPosterImg2;
        private ProgressBar mProgress1;
        private ProgressBar mProgress2;
        public MatchVH(View itemView) {
            super(itemView);

            mEquipeNom1 = (TextView) itemView.findViewById(R.id.nomEquipe1);
            mPosterImg1 = (ImageView) itemView.findViewById(R.id.movie_poster);
            mProgress1 = (ProgressBar) itemView.findViewById(R.id.movie_progress);

            mEquipeNom2 = (TextView) itemView.findViewById(R.id.nomEquipe2);
            mPosterImg2 = (ImageView) itemView.findViewById(R.id.movie_poster2);
            mProgress2 = (ProgressBar) itemView.findViewById(R.id.movie_progress2);
            date = (TextView) itemView.findViewById(R.id.date);
            score1 = (TextView) itemView.findViewById(R.id.score1);
            score2 = (TextView) itemView.findViewById(R.id.score2);
        }
    }


    protected class LoadingVH extends RecyclerView.ViewHolder {

        public LoadingVH(View itemView) {
            super(itemView);
        }
    }


}