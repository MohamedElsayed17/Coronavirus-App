package com.example.coronavirus.ui.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.coronavirus.R;
import com.example.coronavirus.networking.NetworkModel;
import com.example.coronavirus.pojo.AllCountryCases;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    CountryViewModel countryViewModel;
    RecyclerView countriesCasesRecycleView;
    SearchView searchView;
    CountryAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        initViews();
        String connection= NetworkModel.getConnectivityStatusString(this);
        if(connection==null || connection.equals("No internet is available")){
            ImageView imageView = findViewById(R.id.offline_image);
            imageView.setVisibility(View.VISIBLE);
            Toast.makeText(this, "No internet connection", Toast.LENGTH_SHORT).show();
            return;
        }

        countryViewModel.getAllCountriesCases();

        Log.d("MainActivity","main activity");
        countryViewModel.casesMutableLiveData.observe(this, new Observer<List<AllCountryCases>>() {
            @Override
            public void onChanged(List<AllCountryCases> allCountryCases) {
                adapter.setList(new ArrayList<AllCountryCases>(allCountryCases));
            }
        });
        search();

    }

    private void initViews() {
        if(getActionBar()!=null)
            getActionBar().hide();
        if(getSupportActionBar()!=null)
            getSupportActionBar().hide();
        searchView = (SearchView) findViewById(R.id.search_view);
        countryViewModel = ViewModelProviders.of(this).get(CountryViewModel.class);
        countriesCasesRecycleView = findViewById(R.id.countries_recycle_view);
        countriesCasesRecycleView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new CountryAdapter();
        countriesCasesRecycleView.setAdapter(adapter);

    }


    void search(){
        searchView.getBackground().setColorFilter(ContextCompat.getColor(this, R.color.blueColor) , PorterDuff.Mode.SCREEN);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                adapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });

    }
}