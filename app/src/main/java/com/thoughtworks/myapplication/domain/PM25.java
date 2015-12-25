package com.thoughtworks.myapplication.domain;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class PM25 implements Serializable {
    @SerializedName("position_name")
    private String positionName;

    @SerializedName("quality")
    private String quality;

    @SerializedName("area")
    private String area;

    @SerializedName("time_point")
    private String time;

    @SerializedName("station_code")
    private String stationCode;

    public String getStationCode(){return stationCode;}
    public void setStationCode(String stationCode){ this.stationCode = stationCode;}

    public String getArea(){ return area ;}
    public void setArea(String area){ this.area = area; }

    public String getTime(){return time;}
    public void setTime(String time){this.time = time;}

    public String getPositionName() {
        return positionName;
    }
    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public String getQuality() {
        return quality;
    }
    public void setQuality(String quality) {
        this.quality = quality;
    }
}
