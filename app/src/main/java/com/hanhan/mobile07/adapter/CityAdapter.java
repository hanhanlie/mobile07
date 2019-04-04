package com.hanhan.mobile07.adapter;

/**
 * @author Hanhan
 */

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.hanhan.mobile07.R;
import com.hanhan.mobile07.entity.City;
import com.hanhan.mobile07.entity.WeatherResponse;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CityAdapter extends RecyclerView.Adapter<CityAdapter.CityViewHolder>{
    private ArrayList<City> cities;
    private DataClickListener listener;

    public ArrayList<City> getCities() {
        if (cities == null) {
            cities = new ArrayList<>();
        }
        return cities;
    }

    public void setCities(ArrayList<City> cities) {
        this.getCities().clear();
        this.getCities().addAll(cities);
        notifyDataSetChanged();
    }

    public void setListener(DataClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public CityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row, parent, false);
        return new CityViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CityViewHolder holder, int i) {
        final City city = getCities().get(i);
        holder.txtId.setText(String.valueOf(city.getId()));
        holder.txtName.setText(city.getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                listener.onCityClickedListener(city);
            }
        });
    }

    @Override
    public int getItemCount() {
        return getCities().size();
    }

    class CityViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.tv_id)
        TextView txtId;
        @BindView(R.id.tv_name)
        TextView txtName;

        public CityViewHolder(@NonNull View itemView){
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


    public interface DataClickListener{
        void onCityClickedListener(City city);
    }
}
