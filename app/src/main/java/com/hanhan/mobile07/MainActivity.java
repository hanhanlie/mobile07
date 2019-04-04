package com.hanhan.mobile07;

/**
 * @author Hanhan
 */

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.hanhan.mobile07.adapter.CityAdapter;
import com.hanhan.mobile07.entity.City;
import com.hanhan.mobile07.entity.MainWeatherResponse;
import com.hanhan.mobile07.entity.WeatherResponse;
import com.hanhan.mobile07.fragment.MainFragment;
import com.hanhan.mobile07.R;


import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements CityAdapter.DataClickListener{

    @BindView(R.id.fl_container)
    FrameLayout container;

    private MainFragment mainFragment;
    ArrayList<City> cities;
    WeatherResponse weatherResponse;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        cities = new ArrayList<>();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.fl_container,getMainFragment());
        transaction.commit();
        populateCityData();
    }

    public MainFragment getMainFragment() {
        if(mainFragment == null){
            mainFragment = new MainFragment();
        }
        return mainFragment;
    }

    private void populateCityData(){
        try {
            InputStream inputStream = getAssets().open("city.list.json");
            JsonReader reader = new JsonReader(new InputStreamReader(inputStream));
            Gson gson = new Gson();

            City[] arrCity = gson.fromJson(reader, City[].class);
            cities.addAll(Arrays.asList(arrCity));
            getMainFragment().getCityAdapter().setCities(cities);
            getMainFragment().getCityAdapter().setListener(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadCityWeatherData(City city){
        Uri uri = Uri.parse("https://api.openweathermap.org/data/2.5/weather").buildUpon().appendQueryParameter("id", String.valueOf(city.getId())).appendQueryParameter("appid", "f122036f79f9c361ed3f06cb59053417").build();
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest request = new StringRequest(Request.Method.GET, uri.toString(), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                JSONObject object = new JSONObject();
                Gson gson = new Gson();
                weatherResponse = gson.fromJson(response, WeatherResponse.class);
                intent = new Intent(MainActivity.this,DetailActivity.class);
                intent.putExtra("data",weatherResponse);
                startActivity(intent);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "error",Toast.LENGTH_SHORT).show();
            }

        });
        queue.add(request);
    }


    @Override
    public void onCityClickedListener(City city) {
        loadCityWeatherData(city);
    }
}
