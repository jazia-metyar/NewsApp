package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DatabaseReference;
import com.squareup.picasso.Picasso;

import java.util.LinkedList;
import java.util.List;

public class PostListAdapter extends
        RecyclerView.Adapter<PostListAdapter.WordViewHolder> {

    //Préparer la structure qui contiendra les éléments de notre liste
    private final List<Post> mWordList;
    private final LayoutInflater mInflater;

    private TextView date,body,title,category;
    private ImageView imageView;

    class WordViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {
        private TextView date,body,title,category;
        private ImageView imageView;
        private DatabaseReference ref;
        //public final RelativeLayout wordItemView;
        final PostListAdapter mAdapter;


        public WordViewHolder(View itemView, PostListAdapter adapter)
        {
            super(itemView);
           // wordItemView = itemView.findViewById(R.id.relative1);
            title=itemView.findViewById(R.id.textView9);
            category=itemView.findViewById(R.id.textView10);
            date=itemView.findViewById(R.id.textView11);
            body=itemView.findViewById(R.id.textView12);
            imageView=(ImageView)itemView.findViewById(R.id.imageView);
            this.mAdapter = adapter;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int mPosition = getLayoutPosition();

            Post element = mWordList.get(mPosition);
            mWordList.set(mPosition, element);
            mAdapter.notifyDataSetChanged();
        }
    }

    public PostListAdapter(Context context, List<Post> wordList) {
        mInflater = LayoutInflater.from(context);
        this.mWordList = wordList;

    }

    //Appelée au moment de la création du ViewHolder qui affichera
    //les éléments chargés à partir de l'Adapter
    @Override
    public PostListAdapter.WordViewHolder onCreateViewHolder(ViewGroup parent,
                                                          int viewType) {
        // Inflater un view avec le layout déjà créé
        View mItemView = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.wordlist_item, parent, false);
        return new WordViewHolder(mItemView, this);
    }

    //Elle est appelé à chaque fois qu'une vue doit être chargé.
    //On récupére alors la position du nouvel élément à afficher
    //et on le charge dans le TextView
    @Override
    public void onBindViewHolder(PostListAdapter.WordViewHolder holder,
                                 int position) {
        // Récupérer l'élément qui doit etre affiché et chargé dans le ViewHolder
        Post mCurrent = mWordList.get(position);
        // Ajouter l'élément au ViewHolder
        holder.title.setText(mCurrent.getTitle());
        holder.category.setText(mCurrent.getCat());
        holder.date.setText(mCurrent.getDate());
        if(mCurrent.getBody().length()>20)
            holder.body.setText(mCurrent.getBody().substring(0,20)+"....");
        else
            holder.body.setText(mCurrent.getBody());

        String link=mCurrent.getPhotos();
                     Picasso.get().load(link)
                        .into(holder.imageView);
    }

    //Retourne le nombre d'éléments de notre liste
    @Override
    public int getItemCount() {
        return mWordList.size();
    }
}
