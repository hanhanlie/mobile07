package com.hanhan.mobile07.entity;

/**
 * @author Hanhan
 */

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class WeatherResponse implements Serializable{
    @SerializedName("name")
    private String name;
    @SerializedName("main")
    private MainWeatherResponse main;

    public String getName() {
        return name;
    }

    public MainWeatherResponse getMain() {
        return main;
    }
}
