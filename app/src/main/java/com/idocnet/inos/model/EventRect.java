package com.idocnet.inos.model;

import android.graphics.RectF;

public class EventRect {
    public RectF rect;
    public Event event;

    public EventRect(RectF rect, Event event){
        this.rect = rect;
        this.event = event;
    }
}
