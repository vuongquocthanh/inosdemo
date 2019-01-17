package com.idocnet.inos.model;

public class EventType {
    private String tvEventType;

    private boolean cbEventType;

    public EventType(String tvEventType, boolean cbEventType) {
        this.tvEventType = tvEventType;
        this.cbEventType = cbEventType;
    }

    public String getTvEventType() {
        return tvEventType;
    }

    public void setTvEventType(String tvEventType) {
        this.tvEventType = tvEventType;
    }

    public boolean isCbEventType() {
        return cbEventType;
    }

    public void setCbEventType(boolean cbEventType) {
        this.cbEventType = cbEventType;
    }
}
