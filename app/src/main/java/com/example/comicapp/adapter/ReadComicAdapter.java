package com.example.comicapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.comicapp.R;
import com.example.comicapp.object.ReadComic;
import com.example.comicapp.viewholder.ReadComicViewHolder;

import java.util.ArrayList;

public class ReadComicAdapter extends RecyclerView.Adapter<ReadComicViewHolder> {

    private ArrayList<ReadComic> list;
    private Context context;
    private LayoutInflater inflater;

    public ReadComicAdapter(ArrayList<ReadComic> list, Context context) {
        this.list = list;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ReadComicViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.item_readcomic, parent, false);
        return new ReadComicViewHolder(v, context);
    }

    @Override
    public void onBindViewHolder(@NonNull ReadComicViewHolder holder, int position) {
        ReadComic rc = list.get(position);
        holder.setImgReadComic(rc);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
