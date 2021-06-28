package com.example.coronavirus.ui.details;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.coronavirus.R;
import com.example.coronavirus.data.ApiClient;
import com.example.coronavirus.pojo.AllCountryCases;
import com.example.coronavirus.pojo.CountryCasesDetails;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailsViewModel extends ViewModel {
    MutableLiveData<List<CountryCasesDetails>> casesDetailsMutableLiveData=new MutableLiveData<>();
    private String[] casesDetails ={"New Cases","New Deaths","Total Recovered",
            "Total Deaths","Active Cases","Critical Cases"};
    private int[] colors ={R.color.orangeColor,R.color.grayColor,R.color.greenColor,
                            R.color.pinkColor,R.color.orangeColor,R.color.grayColor};
    void getCountryCases(String country){
        ApiClient.getInstance().getCountryCases(country).enqueue(new Callback<AllCountryCases>() {
            @Override
            public void onResponse(Call<AllCountryCases> call, Response<AllCountryCases> response) {
                ArrayList<CountryCasesDetails> list=new ArrayList<>();
                AllCountryCases countryCases =  response.body();
                String[] casesNumbers ={countryCases.getTodayCases()+"",countryCases.getTodayDeaths()+"",
                        countryCases.getRecovered()+"",countryCases.getDeaths()+"",
                        countryCases.getActive()+"",countryCases.getCritical()+""};
                for(int i=0;i<6;i++){
                    list.add(new CountryCasesDetails(casesDetails[i],casesNumbers[i], colors[i]));
                }
                casesDetailsMutableLiveData.setValue(list);
            }

            @Override
            public void onFailure(Call<AllCountryCases> call, Throwable t) {

            }
        });
    }
}
