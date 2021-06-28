package com.example.coronavirus.data;

import com.example.coronavirus.pojo.AllCountryCases;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiInterface {
    @GET("countries")
    public Call<List<AllCountryCases>> getAllCountriesInfo();

    @GET("countries/{country}")
    public Call<AllCountryCases> getCountryInfo(@Path("country") String country);
}
