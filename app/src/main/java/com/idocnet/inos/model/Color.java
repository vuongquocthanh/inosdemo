package com.idocnet.inos.model;

public class Color {
    private int imgColor;
    private String tvColor;
    private boolean cbColor;

    public Color(int imgColor, String tvColor, boolean cbColor) {
        this.imgColor = imgColor;
        this.tvColor = tvColor;
        this.cbColor = cbColor;
    }

    public int getImgColor() {
        return imgColor;
    }

    public void setImgColor(int imgColor) {
        this.imgColor = imgColor;
    }

    public String getTvColor() {
        return tvColor;
    }

    public void setTvColor(String tvColor) {
        this.tvColor = tvColor;
    }

    public boolean isCbColor() {
        return cbColor;
    }

    public void setCbColor(boolean cbColor) {
        this.cbColor = cbColor;
    }
}
