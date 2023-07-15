package com.example.comicapp.viewholder;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.comicapp.R;
import com.example.comicapp.object.ReadComic;

public class ReadComicViewHolder extends RecyclerView.ViewHolder {
    private ImageView imgReadComic;
    private Context context;
    private void bindingView(){
        imgReadComic = itemView.findViewById(R.id.imgReadComic);
    }
    public ReadComicViewHolder(@NonNull View itemView, Context context) {
        super(itemView);
        this.context = context;
        bindingView();
    }
    public void setImgReadComic(ReadComic rc){
        Glide.with(context).load(rc.getUrlImg()).into(imgReadComic);
    }
}
