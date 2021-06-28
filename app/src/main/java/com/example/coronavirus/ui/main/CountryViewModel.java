package com.example.coronavirus.ui.main;

import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.coronavirus.data.ApiClient;
import com.example.coronavirus.pojo.AllCountryCases;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CountryViewModel extends ViewModel {
    MutableLiveData<List<AllCountryCases>> casesMutableLiveData =new MutableLiveData<>();

    public void getAllCountriesCases(){
        if(ApiClient.getInstance()!=null) {
            ApiClient.getInstance().getAllCountryCases().enqueue(new Callback<List<AllCountryCases>>() {
                @Override
                public void onResponse(Call<List<AllCountryCases>> call, Response<List<AllCountryCases>> response) {
                    casesMutableLiveData.setValue(response.body());
                    if( response.body() != null)
                    Log.d("OnResponse",response.body().get(0).getCountry());
                    else
                        Log.d("OnReponse","null");
                }

                @Override
                public void onFailure(Call<List<AllCountryCases>> call, Throwable t) {
                    Log.d("onFailure", t.toString());

                }
            });
        }
    }

}
