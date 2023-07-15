package com.example.comicapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.example.comicapp.R;
import com.example.comicapp.object.Comic;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Callback;

public class ComicAdapter extends ArrayAdapter<Comic> {
    private Context context;
    private ArrayList<Comic> comicList;

    public ComicAdapter(@NonNull Context context, int resource, @NonNull List<Comic> objects) {
        super(context,resource,objects);
        this.context= context;
        this.comicList= new ArrayList<>(objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_comic, null);
        }
        if (comicList.size()>0){
            Comic comic = this.comicList.get(position);
            TextView comicName = convertView.findViewById(R.id.tvComicName);
            TextView chapName = convertView.findViewById(R.id.tvChapterName);
            RoundedImageView imgComic = convertView.findViewById(R.id.ComicImage);
            comicName.setText(comic.getComicName());
            chapName.setText(comic.getChapterName());
            Glide.with(this.context).load(comic.getImageLink()).into(imgComic);
        }
        return convertView;
    }
}
