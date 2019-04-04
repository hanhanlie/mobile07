package com.hanhan.mobile07.entity;

/**
 * @author Hanhan
 */

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class MainWeatherResponse implements Serializable{
    @SerializedName("temp")
    private double temperatur;
    @SerializedName("pressure")
    private double pressure;
    @SerializedName("humidity")
    private double humidity;
    @SerializedName("sea_level")
    private double seaLevel;

    public double getTemperatur() {
        return temperatur;
    }

    public double getPressure() {
        return pressure;
    }

    public double getHumidity() {
        return humidity;
    }

    public double getSeaLevel() {
        return seaLevel;
    }
}
