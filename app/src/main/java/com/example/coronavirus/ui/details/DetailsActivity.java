package com.example.coronavirus.ui.details;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.widget.GridView;
import android.widget.TextView;

import com.example.coronavirus.R;
import com.example.coronavirus.pojo.CountryCasesDetails;

import java.util.ArrayList;
import java.util.List;

public class DetailsActivity extends AppCompatActivity {

    TextView countryNameText;
    TextView totalCasesText;
    DetailsViewModel viewModel;
    GridView gridView;
    GridAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        initViews();
        String country = getIntent().getStringExtra("countryName");
        String totalCases = getIntent().getStringExtra("totalCases");

        countryNameText.setText("Covid-19 cases ("+country+")");
        totalCasesText.setText(totalCases);

        viewModel.getCountryCases(country);

        viewModel.casesDetailsMutableLiveData.observe(this, new Observer<List<CountryCasesDetails>>() {
            @Override
            public void onChanged(List<CountryCasesDetails> countryCasesDetails) {
                adapter.setList((ArrayList<CountryCasesDetails>) countryCasesDetails);
            }
        });


    }

    private void initViews() {
        if(getActionBar()!=null)
            getActionBar().hide();
        if(getSupportActionBar()!=null)
            getSupportActionBar().hide();
        countryNameText=findViewById(R.id.country_name_details);
        totalCasesText=findViewById(R.id.total_cases_details);
        viewModel = ViewModelProviders.of(this).get(DetailsViewModel.class);
        gridView=findViewById(R.id.grid_view);
        adapter  = new GridAdapter();
        gridView.setAdapter(adapter);
    }
}