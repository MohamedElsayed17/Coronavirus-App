package com.example.coronavirus.ui.details;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.example.coronavirus.R;
import com.example.coronavirus.pojo.AllCountryCases;
import com.example.coronavirus.pojo.CountryCasesDetails;

import java.util.ArrayList;

public class GridAdapter extends BaseAdapter {
    private ArrayList<CountryCasesDetails> countryCasesArrayList=new ArrayList<>();

    public void setList(ArrayList<CountryCasesDetails> countryCasesArrayList) {
        this.countryCasesArrayList = countryCasesArrayList;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return countryCasesArrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return countryCasesArrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view==null)
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.country_details_item,viewGroup,false);
        TextView countryDetailsText = view.findViewById(R.id.country_details_text);
        TextView numberOfCasesText = view.findViewById(R.id.country_details_number_text);

        CountryCasesDetails countryCasesDetails = (CountryCasesDetails) getItem(i);
        countryDetailsText.setText(countryCasesDetails.getDetailName());
        numberOfCasesText.setText(countryCasesDetails.getDetailNumber());
        if(Build.VERSION.SDK_INT>=23)
            numberOfCasesText.setTextColor(ContextCompat.getColor(viewGroup.getContext(), countryCasesDetails.getColor()));
        else
            numberOfCasesText.setTextColor(viewGroup.getContext().getResources().getColor(countryCasesDetails.getColor()));
        return view;
    }
}
