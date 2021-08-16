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

import com.example.bet.model.Equipe;
import com.example.bet.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by delaroy on 12/5/17.
 */

public class PaginationAdapter_Equipe extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements Filterable {

    private static final int ITEM = 0;
    private static final int LOADING = 1;
    private static final String BASE_URL_IMG = "https://image.tmdb.org/t/p/w185";

    private List<Equipe> equipeResults;
    private List<Equipe> equipeResultsALL;
    private List<Equipe> tmpEquipe;
    private Context context;

    private boolean isLoadingAdded = false;

    public PaginationAdapter_Equipe(Context context) {
        this.context = context;
        equipeResults = new ArrayList<>();
        equipeResultsALL = equipeResults;
        tmpEquipe = new ArrayList<>(equipeResults);
    }

    public List<Equipe> getMovies() {
        return equipeResults;
    }

    public void setMovies(List<Equipe> equipeResults) {
        this.equipeResults = equipeResults;
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
        View v1 = inflater.inflate(R.layout.fragment_card, parent, false);

        viewHolder = new EquipeVH(v1);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        Equipe result = equipeResults.get(position); // Movie
        if(result!=null){
        switch (getItemViewType(position)) {
            case ITEM:
                final EquipeVH movieVH = (EquipeVH) holder;

                movieVH.mEquipeNom.setText(result.getNom());

                System.out.println("LOGO "+result.getLogo());

                Picasso.get().load(result.getLogo())
                        .placeholder(R.drawable.image_placeholder)
                        .into(movieVH.mPosterImg);
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
        return equipeResults == null ? 0 : equipeResults.size();
    }

    @Override
    public int getItemViewType(int position) {
        return (position == equipeResults.size() - 1 && isLoadingAdded) ? LOADING : ITEM;
    }

    public void add(Equipe r) {
        equipeResults.add(r);
        notifyItemInserted(equipeResults.size() - 1);
    }

    public void addAll(List<Equipe> moveResults) {
        for (Equipe result : moveResults) {
            add(result);
        }

    }

    public void remove(Equipe r) {
        int position = equipeResults.indexOf(r);
        if (position > -1) {
            equipeResults.remove(position);
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
        add(new Equipe());
    }

    public void removeLoadingFooter() {
        isLoadingAdded = false;

        int position = equipeResults.size() - 1;
        Equipe result = getItem(position);

        if (result != null) {
            equipeResults.remove(position);
            notifyItemRemoved(position);
        }
    }

    public Equipe getItem(int position) {
        return equipeResults.get(position);
    }

    @Override
    public Filter getFilter() {
        return filter;
    }
    Filter filter=new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
           List<Equipe> filteredList = new ArrayList<>();
           if(equipeResults.size()==0)
               equipeResults = new ArrayList<>(tmpEquipe);
           equipeResultsALL = new ArrayList<>(equipeResults);
           if(charSequence.toString().isEmpty()){
                filteredList.addAll(tmpEquipe);
           }else{
               Log.e("MOVIE","TAILLE"+ equipeResultsALL.size());
               if(!equipeResultsALL.isEmpty()) {
                   for (int i = 0; i < equipeResultsALL.size() - 1; i++) {
                       if (equipeResultsALL.get(i) != null) {
                           if (equipeResultsALL.get(i).getNom().toLowerCase().contains(charSequence.toString().toLowerCase())) {
                               filteredList.add(equipeResultsALL.get(i));
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
        if(equipeResultsALL.size()>=0)
            tmpEquipe.addAll(equipeResults);
        equipeResults.clear();
        if(filterResults.values!=null){
            equipeResults.addAll((Collection<?extends Equipe>)filterResults.values);
            notifyDataSetChanged();
        }
    }
    };
    protected class EquipeVH extends RecyclerView.ViewHolder {
        private TextView mEquipeNom;
        private ImageView mPosterImg;
        private ProgressBar mProgress;

        public EquipeVH(View itemView) {
            super(itemView);

            mEquipeNom = (TextView) itemView.findViewById(R.id.nomEquipe1);
            mPosterImg = (ImageView) itemView.findViewById(R.id.movie_poster);
            mProgress = (ProgressBar) itemView.findViewById(R.id.movie_progress);
        }
    }


    protected class LoadingVH extends RecyclerView.ViewHolder {

        public LoadingVH(View itemView) {
            super(itemView);
        }
    }


}