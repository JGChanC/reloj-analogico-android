package com.example.jgchan.examenmoviles;

public class Figura {

    private float x,y;
    private float radio; //Radio de interacción
    private int color;

    public Figura(float x, float y, float radio, int color) {
        this.x = x;
        this.y = y;
        this.radio = radio;
        this.color = color;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getRadio() {
        return radio;
    }

    public void setRadio(float radio) {
        this.radio = radio;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
}
