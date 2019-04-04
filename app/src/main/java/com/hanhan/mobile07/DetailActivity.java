package com.hanhan.mobile07;

/**
 * @author Hanhan
 */

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.hanhan.mobile07.entity.City;
import com.hanhan.mobile07.entity.WeatherResponse;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity {

    @BindView(R.id.txtTemp)
    TextView temp;
    @BindView(R.id.txtPressure)
    TextView pressure;
    @BindView(R.id.txtHumidity)
    TextView humidity;
    @BindView(R.id.txtSeaLevel)
    TextView seaLevel;

    private WeatherResponse weatherResponse = new WeatherResponse();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);

        weatherResponse = (WeatherResponse) getIntent().getSerializableExtra("data");

        temp.setText(String.valueOf(weatherResponse.getMain().getTemperatur()));
        pressure.setText(String.valueOf(weatherResponse.getMain().getPressure()));
        humidity.setText(String.valueOf(weatherResponse.getMain().getHumidity()));
        seaLevel.setText(String.valueOf(weatherResponse.getMain().getSeaLevel()));
    }
}
