package com.example.firebase;

public class ImageModel1 {

    private String name;
    private String info;

    public ImageModel1(String name,String info) {
        this.name = name;
        this.info = info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getInfo() {
        return info;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}