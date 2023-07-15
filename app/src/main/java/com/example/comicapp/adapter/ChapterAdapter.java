package com.example.comicapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.comicapp.R;
import com.example.comicapp.object.Chapter;
import com.example.comicapp.object.Comic;
import com.example.comicapp.viewholder.ChapterViewHolder;
import com.example.comicapp.viewholder.ComicViewHolder;

import java.util.ArrayList;
import java.util.List;

public class ChapterAdapter extends ArrayAdapter<Chapter> {
    private Context context;
    private ArrayList<Chapter> chapterList;

    public ChapterAdapter(@NonNull Context context, int resource, @NonNull List<Chapter> objects) {
        super(context, resource, objects);
        this.context = context;
        this.chapterList = new ArrayList<>(objects);
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView==null){
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=inflater.inflate(R.layout.item_chapter,null);
        }
        if (chapterList.size()>0){
            TextView tvChapName,tvDatePost;
            tvChapName = convertView.findViewById(R.id.tvChapName);
            tvDatePost = convertView.findViewById(R.id.tvDatePost);

            Chapter chapter = chapterList.get(position);
            tvChapName.setText(chapter.getChapterName());
            tvDatePost.setText(chapter.getDatePost());
        }
        return  convertView;
    }
}
