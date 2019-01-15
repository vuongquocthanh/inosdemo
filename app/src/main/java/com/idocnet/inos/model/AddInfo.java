package com.idocnet.inos.model;

public class AddInfo {
    private int imgIconInfo;
    private String infoName;

    public AddInfo(int imgIconInfo, String infoName) {
        this.imgIconInfo = imgIconInfo;
        this.infoName = infoName;
    }

    public int getImgIconInfo() {
        return imgIconInfo;
    }

    public void setImgIconInfo(int imgIconInfo) {
        this.imgIconInfo = imgIconInfo;
    }

    public String getInfoName() {
        return infoName;
    }

    public void setInfoName(String infoName) {
        this.infoName = infoName;
    }
}
