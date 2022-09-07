package com.example.cust_search;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.widget.SearchView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ExampleAdapter adapter;
    private List<ExampleItem> exampleList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fillExampleList();
        setUpRecyclerView();
    }

    private void fillExampleList() {
        exampleList = new ArrayList<>();
        exampleList.add(new ExampleItem(R.drawable.chrome, "Google", "sunder pitchi"));
        exampleList.add(new ExampleItem(R.drawable.facebook, "FaceBook", "Mark bhai"));
        exampleList.add(new ExampleItem(R.drawable.instagram, "Instagram", "Mark bhai"));
        exampleList.add(new ExampleItem(R.drawable.gmail, "Gmail", "Google"));
        exampleList.add(new ExampleItem(R.drawable.playstore, "Play store", "Google"));
        exampleList.add(new ExampleItem(R.drawable.youtube, "YouTube", "Google"));
        exampleList.add(new ExampleItem(R.drawable.whatsapp, "whatsapp", "mark bhai"));
        exampleList.add(new ExampleItem(R.drawable.pinterest, "Pinterest", "By pinterest"));
        exampleList.add(new ExampleItem(R.drawable.twitter, "Twiter", "By Twiiter"));
        
    }

    private void setUpRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        adapter = new ExampleAdapter(exampleList);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.example_menu, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();

        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });
        return true;
    }
}