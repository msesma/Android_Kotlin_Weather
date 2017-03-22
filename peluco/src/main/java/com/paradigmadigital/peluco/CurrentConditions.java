package com.paradigmadigital.peluco;

import android.graphics.Bitmap;

public class CurrentConditions {
    private final Bitmap icon;
    private final String temp;
    private final String city;

    public CurrentConditions(Bitmap icon, String temp, String city) {
        this.icon = icon;
        this.temp = temp;
        this.city = city;
    }

    public Bitmap getIcon() {
        return icon;
    }

    public String getTemp() {
        return temp;
    }

    public String getCity() {
        return city;
    }
}
