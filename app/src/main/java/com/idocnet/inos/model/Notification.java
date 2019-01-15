package com.idocnet.inos.model;

public class Notification {
    private String nameNotification;
    private boolean check;

    public Notification(String nameNotification, boolean check) {
        this.nameNotification = nameNotification;
        this.check = check;
    }

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

    public String getNameNotification() {
        return nameNotification;
    }

    public void setNameNotification(String nameNotification) {
        this.nameNotification = nameNotification;
    }
}
