package com.sinros.myexample;

/**
 * Created by Tanmay Ranjan on 19-Apr-18.
 */

public class Chapter {

    private String id;
    private String name;
    private String image;
    private String quantity;
    private String total;

    public Chapter(String id, String name, String image, String quantity, String total) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.quantity = quantity;
        this.total = total;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    public String getQuantity() {
        return quantity;
    }

    public String getTotal() {
        return total;
    }
}