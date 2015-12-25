package com.thoughtworks.myapplication.domain;

import com.google.gson.annotations.SerializedName;

public class PM25 {
    @SerializedName("position_name")
    private String positionName;

    @SerializedName("quality")
    private String quality;

    @SerializedName("area")
    private String area;

    @SerializedName("time_point")
    private String time;

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
