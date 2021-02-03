package co.com.ceiba.mobile.pruebadeingreso.model;

import android.arch.persistence.room.ColumnInfo;

import java.io.Serializable;


public class Company implements Serializable {
    @ColumnInfo(name = "company")
    private String name;
    @ColumnInfo(name = "catchPhrase")
    private String catchPhrase;
    @ColumnInfo(name = "bs")
    private String bs;

    public Company(String name, String catchPhrase, String bs) {
        this.name = name;
        this.catchPhrase = catchPhrase;
        this.bs = bs;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCatchPhrase() {
        return catchPhrase;
    }

    public void setCatchPhrase(String catchPhrase) {
        this.catchPhrase = catchPhrase;
    }

    public String getBs() {
        return bs;
    }

    public void setBs(String bs) {
        this.bs = bs;
    }
}
