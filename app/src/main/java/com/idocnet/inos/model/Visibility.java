package com.idocnet.inos.model;

public class Visibility {

    private String tvVisibility;

    private boolean cbVisibility;

    public Visibility(String tvVisibility, boolean cbVisibility) {
        this.tvVisibility = tvVisibility;
        this.cbVisibility = cbVisibility;
    }

    public String getTvVisibility() {
        return tvVisibility;
    }

    public void setTvVisibility(String tvVisibility) {
        this.tvVisibility = tvVisibility;
    }

    public boolean isCbVisibility() {
        return cbVisibility;
    }

    public void setCbVisibility(boolean cbVisibility) {
        this.cbVisibility = cbVisibility;
    }
}
