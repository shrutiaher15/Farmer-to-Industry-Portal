package com.example.firebase;

public class Model {
    ///String name;
    String crop;
    String quantity;
    String lat;
    String lon;

    public String getCrop() {
        return crop;
    }

    public void setCrop(String crop) {
        this.crop = crop;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }



    public Model(String crop, String quantity, String lat, String lon) {
        this.crop = crop;
        this.quantity = quantity;
        this.lat = lat;
        this.lon = lon;
    }
    public Model(){}

}
