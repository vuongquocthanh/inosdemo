package com.idocnet.inos.model;

public class Day {
    public int day = 0;
    public int month = 0;
    public int year = 0;
    public int dayOfYear = 0;
    public int dayOfWeek = 0;
    public int monthOfYear = 0;
    public String type = "CUR";

    public Day(){}

    public Day(int dayOfWeek){
        this.dayOfWeek = dayOfWeek;
    }

}
