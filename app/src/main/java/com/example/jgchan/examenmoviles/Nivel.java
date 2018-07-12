package com.example.jgchan.examenmoviles;

/**
 * Created by jgchan on 9/07/18.
 */

public class Nivel {

    float nivelY;
    float topeMin;
    float tomeMax;

    public Nivel(float nivelY, float topeMin, float tomeMax) {
        this.nivelY = nivelY;
        this.topeMin = topeMin;
        this.tomeMax = tomeMax;
    }

    public float getNivelY() {
        return nivelY;
    }

    public void setNivelY(float nivelY) {
        this.nivelY = nivelY;
    }

    public float getTopeMin() {
        return topeMin;
    }

    public void setTopeMin(float topeMin) {
        this.topeMin = topeMin;
    }

    public float getTomeMax() {
        return tomeMax;
    }

    public void setTomeMax(float tomeMax) {
        this.tomeMax = tomeMax;
    }
}
