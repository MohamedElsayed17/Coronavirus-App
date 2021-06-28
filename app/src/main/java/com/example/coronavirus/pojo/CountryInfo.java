package com.example.coronavirus.pojo;

public class CountryInfo {
    private int _id;

    private String iso2;

    private String iso3;

    private float lat;

    private float lang;

    private String flag;

    public void set_id(int _id){
        this._id = _id;
    }
    public int get_id(){
        return this._id;
    }
    public void setIso2(String iso2){
        this.iso2 = iso2;
    }
    public String getIso2(){
        return this.iso2;
    }
    public void setIso3(String iso3){
        this.iso3 = iso3;
    }
    public String getIso3(){
        return this.iso3;
    }
    public void setLat(float lat){
        this.lat = lat;
    }
    public float getLat(){
        return this.lat;
    }
    public void setLong(float lang){
        this.lang = lang;
    }
    public float getLong(){
        return this.lang;
    }
    public void setFlag(String flag){
        this.flag = flag;
    }
    public String getFlag(){
        return this.flag;
    }
}
