package com.idocnet.inos.model;

public class Message {
    private int avatar;
    private String message;
    private String time;

    public Message(int avatar, String message, String time) {
        this.avatar = avatar;
        this.message = message;
        this.time = time;
    }

    public int getAvatar() {
        return avatar;
    }

    public void setAvatar(int avatar) {
        this.avatar = avatar;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
