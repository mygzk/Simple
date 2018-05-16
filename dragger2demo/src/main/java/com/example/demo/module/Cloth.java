package com.example.demo.module;

/**
 * Created by guozhk on 2018/5/11.
 */

public class Cloth {

    private String color;

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return color + "布料";
    }
}
