package com.example.comicapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.comicapp.API.APIServices;
import com.example.comicapp.R;
import com.example.comicapp.adapter.ChapterAdapter;
import com.example.comicapp.object.Chapter;
import com.example.comicapp.object.Comic;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChapActivity extends AppCompatActivity {

    private ListView lvChap;
    private TextView txtComicName;
    private ImageView imgComic;
    
    private Button save;

    private Button unSave;
    private String idComic;
    private Comic comic;
    private ChapterAdapter chapterAdapter;

    private ArrayList<Chapter> mListChapter;

    private void init() {
        Bundle b = getIntent().getBundleExtra("data");
        comic = (Comic) b.getSerializable("comic");
        mListChapter = new ArrayList<>();
    }

    private void BindingView() {
        txtComicName = findViewById(R.id.tvComicNames);
        imgComic = findViewById(R.id.comicImages);
        lvChap = findViewById(R.id.lsvListChap);
        save = findViewById(R.id.btnSave);
        unSave = findViewById(R.id.btnUnSave);
    }

    private void SetUp() {
        txtComicName.setText(comic.getComicName());
        idComic = comic.getComicId();
        Glide.with(this).load(comic.getImageLink()).into(imgComic);
    }

    private void BindingAction() {
        lvChap.setOnItemClickListener(this::onitemsClick);
        save.setOnClickListener(this::onClickSave);
        unSave.setOnClickListener(this::onClickUnSave);
    }

    private void onClickUnSave(View view) {
        Toast.makeText(this, "Bỏ dõi thành công", Toast.LENGTH_SHORT).show();
        unSave.setVisibility(view.GONE);
        save.setVisibility(view.VISIBLE);
    }

    private void onClickSave(View view) {
        Toast.makeText(this, "Theo dõi thành công", Toast.LENGTH_SHORT).show();
        save.setVisibility(view.GONE);
        unSave.setVisibility(view.VISIBLE);
    }

    private void onitemsClick(AdapterView<?> adapterView, View view, int position, long id) {
        Chapter chapter = mListChapter.get(position);
        Bundle b = new Bundle();
        b.putSerializable("idChap", chapter);
        Intent intent = new Intent(ChapActivity.this, ReadComicActivity.class);
        intent.putExtra("data", b);
        startActivity(intent);
    }



    private void listComicApi() {
        Toast.makeText(this, "Loading Chap...", Toast.LENGTH_SHORT).show();
        APIServices.getChapterApiEndPoint().getAllChapterById(idComic).enqueue(new Callback<ArrayList<Chapter>>() {
            @Override
            public void onResponse(Call<ArrayList<Chapter>> call, Response<ArrayList<Chapter>> response) {
                mListChapter.clear();
                ArrayList<Chapter> allChapters = response.body();
                for (Chapter chapter : allChapters) {
                   if (chapter.getIdComic().equals(idComic)) {
                        mListChapter.add(chapter);
                    }
                }
                chapterAdapter = new ChapterAdapter(ChapActivity.this, 0, mListChapter);
                lvChap.setAdapter(chapterAdapter);
                ;
            }

            @Override
            public void onFailure(Call<ArrayList<Chapter>> call, Throwable t) {
                Toast.makeText(ChapActivity.this, "Load Chap Fail", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chap);
        init();
        BindingView();
        SetUp();
        BindingAction();
        listComicApi();
    }
}