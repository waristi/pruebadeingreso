package co.com.ceiba.mobile.pruebadeingreso.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;

import java.io.Serializable;


public class Geo implements Serializable {
    @ColumnInfo(name = "lat")
    private String lat;
    @ColumnInfo(name = "lng")
    private String lng;

    public Geo(String lat, String lng) {
        this.lat = lat;
        this.lng = lng;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }
}
