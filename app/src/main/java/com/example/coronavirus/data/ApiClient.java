package com.example.coronavirus.data;

import com.example.coronavirus.pojo.AllCountryCases;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private static final String BASE_URL = "https://disease.sh/v3/covid-19/";
    private static ApiClient instance;
    private ApiInterface apiInterface;

    private ApiClient(){
        Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        apiInterface = retrofit.create(ApiInterface.class);
    }

    public static ApiClient getInstance(){
        if(instance==null){
            instance = new ApiClient();
        }
        return instance;
    }

    public Call<List<AllCountryCases>> getAllCountryCases(){
        return apiInterface.getAllCountriesInfo();
    }
    public Call<AllCountryCases> getCountryCases(String country){
        return apiInterface.getCountryInfo(country);
    }
}
