package com.example.comicapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.comicapp.API.APIServices;
import com.example.comicapp.R;
import com.example.comicapp.adapter.ReadComicAdapter;
import com.example.comicapp.object.Chapter;
import com.example.comicapp.object.ReadComic;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReadComicActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private Button btnPreChap;
    private Button btnNextChap;
    private TextView txtChap;
    private ArrayList<ReadComic> arrUrlImg;
    private String idChap;
    private String chapterName;
    private  String idNextChap;
    private  String idPreChap;
    private static Integer currentChapId;
    private Chapter chapter;
    private ReadComicAdapter readComicAdapter;
    private void init() {
        Bundle b = getIntent().getBundleExtra("data");
        chapter = (Chapter) b.getSerializable("idChap");
        arrUrlImg = new ArrayList<>();
    }

    private void setUp() {
        idChap = chapter.getId();
        chapterName = chapter.getChapterName();
        currentChapId = Integer.valueOf(idChap);
    }

    private void bindingView() {
        recyclerView = findViewById(R.id.rlcReadComic);
        btnPreChap = findViewById(R.id.btnPreChap);
        btnNextChap = findViewById(R.id.btnNextChap);
        txtChap = findViewById(R.id.txtChap);
    }
    private void bindingAction(){
        btnPreChap.setOnClickListener(this::onClickPreChap);
        btnNextChap.setOnClickListener(this::onClickNextChap);
        txtChap.setText(chapterName);
    }

    private void onClickNextChap(View view) {
        currentChapId++;
        idNextChap = String.valueOf(currentChapId);
        APIServices.getReadComicApiEndPoint().getAllImgById(idNextChap).enqueue(new Callback<ArrayList<ReadComic>>() {
            @Override
            public void onResponse(Call<ArrayList<ReadComic>> call, Response<ArrayList<ReadComic>> response) {
                arrUrlImg.clear();
                arrUrlImg = response.body();
                readComicAdapter = new ReadComicAdapter(arrUrlImg, ReadComicActivity.this);
                recyclerView.setAdapter(readComicAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(ReadComicActivity.this));
            }

            @Override
            public void onFailure(Call<ArrayList<ReadComic>> call, Throwable t) {
                Toast.makeText(ReadComicActivity.this, "Load Images Fail", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void onClickPreChap(View view) {
        currentChapId--;
        idPreChap = String.valueOf(currentChapId);
        APIServices.getReadComicApiEndPoint().getAllImgById(idPreChap).enqueue(new Callback<ArrayList<ReadComic>>() {
            @Override
            public void onResponse(Call<ArrayList<ReadComic>> call, Response<ArrayList<ReadComic>> response) {
                arrUrlImg.clear();
                arrUrlImg = response.body();
                readComicAdapter = new ReadComicAdapter(arrUrlImg, ReadComicActivity.this);
                recyclerView.setAdapter(readComicAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(ReadComicActivity.this));
            }

            @Override
            public void onFailure(Call<ArrayList<ReadComic>> call, Throwable t) {
                Toast.makeText(ReadComicActivity.this, "Load Images Fail", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void listImgApi() {
        APIServices.getReadComicApiEndPoint().getAllImgById(idChap).enqueue(new Callback<ArrayList<ReadComic>>() {
            @Override
            public void onResponse(Call<ArrayList<ReadComic>> call, Response<ArrayList<ReadComic>> response) {
                arrUrlImg.clear();
                arrUrlImg = response.body();
                readComicAdapter = new ReadComicAdapter(arrUrlImg, ReadComicActivity.this);
                recyclerView.setAdapter(readComicAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(ReadComicActivity.this));
            }

            @Override
            public void onFailure(Call<ArrayList<ReadComic>> call, Throwable t) {
                Toast.makeText(ReadComicActivity.this, "Load Images Fail", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_comic);
        init();
        setUp();
        bindingView();
        bindingAction();
        listImgApi();
    }
}