package com.example.comicapp.viewholder;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.comicapp.R;
import com.example.comicapp.object.Comic;

public class ComicViewHolder extends RecyclerView.ViewHolder {
    private String ComicId;
     private ImageView comicImage;
     private TextView tvComicName;
     private TextView tvChaptername;
    private Context context;


    private void BindingView(){
         comicImage = itemView.findViewById(R.id.ComicImage);
         tvComicName = itemView.findViewById(R.id.tvComicName);
         tvChaptername = itemView.findViewById(R.id.tvChapterName);
     }

     private void BindingAction(){
         comicImage.setOnClickListener(this::onComicImageClick);
     }

    private void onComicImageClick(View view) {
//        int position = getAdapterPosition();
////        if (position != RecyclerView.NO_POSITION) {
////            ComicId = getItemId(position);
////            Toast.makeText(context, "ID của mục: " + ComicId, Toast.LENGTH_SHORT).show();
////        }
////        Intent intent = new Intent(context, ChapActivity.class);
////
////        // Gửi dữ liệu cần thiết qua Intent (nếu có)
////        intent.putExtra("comicId", "ok");
////
////        // Khởi chạy hoạt động mới
////        context.startActivity(intent);
//        Toast.makeText(context, position, Toast.LENGTH_SHORT).show();
    }

    public void setComic(Comic c, Context context){
        ComicId = c.getComicId();
        tvComicName.setText(c.getComicName());
        tvChaptername.setText(c.getChapterName());
        Glide.with(context).load(c.getImageLink()).into(comicImage);
    }

    public ComicViewHolder(@NonNull View itemView, Context context) {
        super(itemView);
        this.context = context;
        BindingView();
        BindingAction();
    }

}
