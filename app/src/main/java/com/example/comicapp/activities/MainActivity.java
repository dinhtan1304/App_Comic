package com.example.comicapp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.comicapp.API.APIServices;
import com.example.comicapp.R;
import com.example.comicapp.adapter.ComicAdapter;
import com.example.comicapp.object.Comic;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private GridView gdvDSComic;
    private ComicAdapter comicAdapter;

    private ArrayList<Comic> mListComic;
    private ArrayList<Comic> filterList = new ArrayList<>();

    private void BindingView() {
        gdvDSComic = findViewById(R.id.gdvDSTruyen);
    }
    private void init(){
        mListComic = new ArrayList<>();
        comicAdapter = new ComicAdapter(this, 0, mListComic);
    }
    private void BindingAction() {
        gdvDSComic.setOnItemClickListener(this::onItemClick);
    }

    private void onItemClick(AdapterView<?> adapterView, View view, int i, long id) {
        Comic comic = mListComic.get(i);
        Bundle b = new Bundle();
        b.putSerializable("comic",comic);
        Intent intent = new Intent(MainActivity.this, ChapActivity.class);
        intent.putExtra("data", b);
        startActivity(intent);
    }

    private void setUp() {
        gdvDSComic.setAdapter(comicAdapter);
    }

    private void listComicApi() {
        Toast.makeText(this, "Load Comic", Toast.LENGTH_SHORT).show();
        APIServices.getComicApiEndPoint().getAllComics().enqueue(new Callback<ArrayList<Comic>>() {
            @Override
            public void onResponse(Call<ArrayList<Comic>> call, Response<ArrayList<Comic>> response) {
                mListComic = response.body();
                comicAdapter = new ComicAdapter(MainActivity.this,0,mListComic);
                gdvDSComic.setAdapter(comicAdapter);
            }

            @Override
            public void onFailure(Call<ArrayList<Comic>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Load Fail", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_comicapp,menu);
        MenuItem menuItem = menu.findItem(R.id.menuSearch);

        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterList(newText);
                return true;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }
    private void filterList(String text) {

        for (Comic c : mListComic) {
            if (c.getComicName().toLowerCase().contains(text.toLowerCase())) {
                filterList.add(c);
            }
        }
        if (filterList.isEmpty()) {
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
            comicAdapter = new ComicAdapter(MainActivity.this,0,mListComic);
        } else {
            comicAdapter = new ComicAdapter(MainActivity.this,0,filterList);
        }
        gdvDSComic.setAdapter(null);
        gdvDSComic.setAdapter(comicAdapter);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.menuLogout){
            Toast.makeText(this, "Log Out Success!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        BindingView();
        setUp();
        BindingAction();
        listComicApi();
    }
}