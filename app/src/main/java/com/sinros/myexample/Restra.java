package com.sinros.myexample;

/**
 * Created by Tanmay Ranjan on 19-Apr-18.
 */

public class Restra {
    private int id;
    private String name;

    private String price;
    private String time;
    private String rate;
    private String image;

    public Restra(int id, String name, String price, String time, String rate, String image) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.time = time;
        this.rate = rate;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public String getTime() {
        return time;
    }

    public String getRate() {
        return rate;
    }

    public String getImage() {
        return image;
    }
}