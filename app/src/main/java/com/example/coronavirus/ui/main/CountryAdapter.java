package com.example.coronavirus.ui.main;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coronavirus.R;
import com.example.coronavirus.pojo.AllCountryCases;
import com.example.coronavirus.ui.details.DetailsActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.MyHolder> implements Filterable {


   private ArrayList<AllCountryCases> allCountryCasesList=new ArrayList<>();
    private ArrayList<AllCountryCases> allCountryCasesCopyList=new ArrayList<>();
    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.country_cases_item,parent,false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, final int position) {
        Picasso.get().load(allCountryCasesList.get(position).getCountryInfo().getFlag()).into(holder.countryFlag);
        holder.countryNameText.setText(allCountryCasesList.get(position).getCountry()+"");
        holder.countryTotalCasesText.setText(allCountryCasesList.get(position).getCases()+"");
        holder.countryNewCasesText.setText(allCountryCasesList.get(position).getTodayCases()+"");

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(view.getContext(), DetailsActivity.class);
                intent.putExtra("countryName",allCountryCasesList.get(position).getCountry()+"");
                intent.putExtra("totalCases",allCountryCasesList.get(position).getCases()+"");
                view.getContext().startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        if(allCountryCasesList.size()!=0)
            return allCountryCasesList.size();
        else
            return 0;
    }

    public void setList(ArrayList<AllCountryCases> list) {
        this.allCountryCasesList = list;
        allCountryCasesCopyList=list;
        notifyDataSetChanged();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    allCountryCasesList=allCountryCasesCopyList ;
                } else {
                    ArrayList<AllCountryCases> filteredList = new ArrayList<>();
                    for (AllCountryCases row : allCountryCasesCopyList) {
                        if (row.getCountry().toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(row);
                        }
                    }

                    allCountryCasesList = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = allCountryCasesList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                allCountryCasesList = (ArrayList<AllCountryCases>) filterResults.values;

                // refresh the list with filtered data
                notifyDataSetChanged();
            }
        };
    }

    public class MyHolder extends RecyclerView.ViewHolder{

        ImageView countryFlag;
        TextView countryNameText;
        TextView countryTotalCasesText;
        TextView countryNewCasesText;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            countryFlag=itemView.findViewById(R.id.country_flag_recycle_item);
            countryNameText = itemView.findViewById(R.id.country_name_recycle_item);
            countryTotalCasesText = itemView.findViewById(R.id.country_total_cases_recycle_item);
            countryNewCasesText = itemView.findViewById(R.id.country_new_cases_recycle_item);

        }
    }
}
