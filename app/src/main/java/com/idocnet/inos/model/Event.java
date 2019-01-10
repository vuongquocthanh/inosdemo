package com.idocnet.inos.model;

public class Event {
    public String title;
    public String startTime;
    public String endTime;
    public int dayOfWeek;

    public Event(String title, String startTime, String endTime, int dayOfWeek){
        this.title = title;
        this.startTime = startTime;
        this.endTime = endTime;
        this.dayOfWeek = dayOfWeek;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public int getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(int day) {
        this.dayOfWeek = day;
    }
}
