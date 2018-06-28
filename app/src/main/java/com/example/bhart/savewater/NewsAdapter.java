package com.example.bhart.savewater;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {

    private Context context;
    private List<news_list> newsLists;

    public NewsAdapter(Context context, List<news_list> newsLists) {
        this.context = context;
        this.newsLists = newsLists;
    }

    @NonNull
    @Override
    public NewsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.news_list,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsAdapter.ViewHolder holder, int position) {

        news_list listItem = newsLists.get(position);
        holder.heading.setText(listItem.getmHeading());
        holder.desc.setText(listItem.getmDesc());


        Picasso.with(context).load(listItem.getImageUrl()).into(holder.image);

    }

    @Override
    public int getItemCount() {
        return newsLists.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView heading;
        public TextView desc;
        public ImageView image;
        public ViewHolder(View itemView) {
                super(itemView);
                heading = (TextView)itemView.findViewById(R.id.news_topic);
                desc = (TextView)itemView.findViewById(R.id.news_details);
                image = (ImageView)itemView.findViewById(R.id.imageView);
        }

    }

}
