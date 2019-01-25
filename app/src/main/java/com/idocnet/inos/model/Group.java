package com.idocnet.inos.model;

public class Group {
    private int avatar;
    private String name;
    private boolean check;

    public Group(int avatar, String name, boolean check) {
        this.avatar = avatar;
        this.name = name;
        this.check = check;
    }

    public int getAvatar() {
        return avatar;
    }

    public void setAvatar(int avatar) {
        this.avatar = avatar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }
}
