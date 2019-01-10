package com.idocnet.inos.view.custome;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.support.v4.view.GestureDetectorCompat;
import android.text.Layout;
import android.text.SpannableStringBuilder;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.style.StyleSpan;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.idocnet.inos.model.EventRect;
import com.idocnet.inos.model.Event;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

public class WeekCustomView extends View {
    private GestureDetectorCompat mGestureDetector;
    private List<Event> events = new ArrayList<>();
    private List<EventRect> eventRects = new ArrayList<>();
    private List<EventRect> mEventRects = new ArrayList<>();
    private Context context;
    private Paint paintEvent, paintStroke, paintFill;
    private TextPaint paintText;
    private int mEventPadding = 8;
    private boolean dayView;
    private int weekOfYear;

    public WeekCustomView(Context context) {
        super(context);
    }

    public WeekCustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public WeekCustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public WeekCustomView(Context context, List<Event> events, boolean dayView, int weekOfYear){
        super(context);
        this.context = context;
        this.events = events;
        paintEvent = new Paint();
        paintStroke = new Paint();
        paintFill = new Paint();
        paintText = new TextPaint();
        this.dayView = dayView;
        this.weekOfYear = weekOfYear;

        mGestureDetector = new GestureDetectorCompat(context, mGestureListener);
    }

    private final GestureDetector.SimpleOnGestureListener mGestureListener = new GestureDetector.SimpleOnGestureListener(){
        @Override
        public boolean onDown(MotionEvent e) {
            Log.d("GestureDetector", "onDown");
            return true;
        }

        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
            Log.d("GestureDetector", "onSingleTapConfirmed");
            if (eventRects.size() > 0) {
                List<EventRect> reversedEventRects = eventRects;
                Collections.reverse(reversedEventRects);
                for (EventRect event : reversedEventRects) {
                    if (event.rect != null && e.getX() > event.rect.left && e.getX() < event.rect.right && e.getY() > event.rect.top && e.getY() < event.rect.bottom) {
                        Toast.makeText(context, event.event.title, Toast.LENGTH_LONG).show();
                        return true;
                    }
                }
            }
            return true;
        }

        @Override
        public void onLongPress(MotionEvent e) {
            getTimePoint(e.getX(), e.getY());
            super.onLongPress(e);
        }
    };

    private void getTimePoint(float x, float y){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.WEEK_OF_YEAR, weekOfYear);

        float columnWidth = getWidth()/7;
        int dayOfWeek = (int)(x/columnWidth);
        calendar.add(Calendar.DAY_OF_WEEK, dayOfWeek-3);
        int hour = (int)(y/parsePX(60));
        int minute = (int)dpFromPx(y%parsePX(60));
        String time = hour+":"+minute+" "+calendar.get(Calendar.DAY_OF_MONTH)+"/"+(calendar.get(Calendar.MONTH)+1)+"/"+calendar.get(Calendar.YEAR);
        Toast.makeText(context, time, Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return mGestureDetector.onTouchEvent(event);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        drawLine(canvas);

        drawEvent(canvas);
    }

    private void drawEvent(Canvas canvas){
        for (Event event: events){
            String[] start = event.startTime.split(":");
            String[] end =  event.endTime.split(":");

            // left and right compute after
            float top = Float.valueOf(start[0]) * parsePX(60) + parsePX(Float.valueOf(start[1]));
            float left = dayView ? 0 : event.dayOfWeek*getWidth()/7;
            float bottom = Float.valueOf(end[0]) * parsePX(60) + parsePX(Float.valueOf(end[1]));
            float right = 0;

            eventRects.add(new EventRect(new RectF(left, top, right, bottom), event));
        }

        computePositionOfEvents(canvas, eventRects);
    }

    private void computePositionOfEvents(Canvas canvas, List<EventRect> eventRects){
        List<List<EventRect>> collisionGroups = new ArrayList<>();
        for (EventRect eventRect: eventRects){
            boolean isPlaced = false;

            outerLoop:
            for (List<EventRect> collisionGroup: collisionGroups){
                for (EventRect groupEventRect: collisionGroup){
                    if ((eventRect.event.dayOfWeek == groupEventRect.event.dayOfWeek || dayView) && isEventCollide(eventRect.rect, groupEventRect.rect)){
                        collisionGroup.add(eventRect);
                        isPlaced = true;
                        break outerLoop;
                    }
                }
            }

            if (!isPlaced){
                List<EventRect> newGroup = new ArrayList<>();
                newGroup.add(eventRect);
                collisionGroups.add(newGroup);
            }
        }

        for (List<EventRect> collisionGroup: collisionGroups){
            expandEventToMaxWidth(canvas, collisionGroup);
        }
    }

    private void expandEventToMaxWidth(Canvas canvas, List<EventRect> collisionGroup){
        List<List<EventRect>> columns = new ArrayList<>();
        columns.add(new ArrayList<>());
        for (EventRect eventRect: collisionGroup){
            boolean isPlaced = false;
            for (List<EventRect> column: columns){
                if (column.size() == 0){
                    column.add(eventRect);
                    isPlaced = true;
                }else if (!isEventCollide(eventRect.rect, column.get(column.size()-1).rect)){
                    column.add(eventRect);
                    isPlaced = true;
                    break;
                }
            }

            if (!isPlaced){
                List<EventRect> newColumn = new ArrayList<>();
                newColumn.add(eventRect);
                columns.add(newColumn);
            }
        }

        int maxRowCount = 0;
        for (List<EventRect> column: columns){
            maxRowCount = Math.max(maxRowCount, column.size());
        }

        for (int i = 0; i < maxRowCount; i++){
            int j = 0;
            for (List<EventRect> column: columns){
                if (column.size() >= i+1){
                    EventRect eventRect = column.get(i);
                    eventRect.rect.left += j * (dayView ? getWidth() : (getWidth()/7)) / columns.size();
                    eventRect.rect.right = 1f * (dayView ? getWidth() : (getWidth()/7)) / columns.size() + eventRect.rect.left;
                    mEventRects.add(eventRect);
                }
                j++;
            }
        }

        for (EventRect eventRect: mEventRects){
            drawEvent(canvas, eventRect);
        }
    }

    private boolean isEventCollide(RectF rectF1, RectF rectF2){
        return !(rectF1.top >= rectF2.bottom || rectF1.bottom <= rectF2.top);
    }

    private void drawEvent(Canvas canvas, EventRect eventRect){
        RectF rect = new RectF(eventRect.rect.left, eventRect.rect.top, eventRect.rect.right, eventRect.rect.bottom);
        int cornerRadius = 30;

        paintFill.setStyle(Paint.Style.FILL);
        paintFill.setColor(Color.parseColor("#CD5C5C"));
        canvas.drawRoundRect(rect, cornerRadius, cornerRadius, paintFill);

        paintText.setColor(Color.WHITE);
        paintText.setTextSize(30);
        drawEventTitle(eventRect.event.title, rect, canvas);

        paintStroke.setStyle(Paint.Style.STROKE);
        paintStroke.setColor(Color.parseColor("#008577"));
        paintStroke.setStrokeWidth(1);
        canvas.drawRoundRect(rect, cornerRadius, cornerRadius, paintStroke);
    }

    private void drawLine(Canvas canvas){
        float height = parsePX(60);
        paintEvent.setColor(Color.parseColor("#5d7a89"));
        for (int i = 0; i< 23; i++){
            canvas.drawLine(0, height, getWidth(), height, paintFill);
            height += parsePX(60);
        }
        if (dayView){
            canvas.drawLine(0, 0, 0, getHeight(), paintFill);

        }else {
            for (int i = 0; i < 7; i++) {
                canvas.drawLine(i * getWidth() / 7, 0, i * getWidth() / 7, getHeight(), paintEvent);
            }
        }
    }

    private float parsePX(float dp){
        final float scale = context.getResources().getDisplayMetrics().density;

        return dp * scale + 0.5f;
    }

    public float dpFromPx(float px) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return px / scale;
    }

    private void drawEventTitle(String title, RectF rectF, Canvas canvas){
        if (rectF.right - rectF.left - mEventPadding * 2 < 0 ) return;
        if (rectF.bottom - rectF.top - mEventPadding * 2 < 0) return;

        SpannableStringBuilder bob = new SpannableStringBuilder();
        if (title != null){
            bob.append(title);
            bob.setSpan(new StyleSpan(android.graphics.Typeface.BOLD), 0, bob.length(), 0);
            bob.append(' ');
        }

        int availableWidth = (int) rectF.right - (int) rectF.left - mEventPadding * 2;
        int availableHeight = (int) rectF.bottom - (int) rectF.top - mEventPadding * 2;

        StaticLayout textLayout = new StaticLayout(bob, paintText, availableWidth, Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, false);
        int lineHeight = textLayout.getHeight() / textLayout.getLineCount();
        if (availableHeight >= lineHeight) {
            // Calculate available number of line counts.
            int availableLineCount = availableHeight / lineHeight;
            do {
                // Ellipsize text to fit into event rect.
                textLayout = new StaticLayout(TextUtils.ellipsize(bob, paintText, availableLineCount * availableWidth, TextUtils.TruncateAt.END), paintText, availableWidth, Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, false);

                // Reduce line count.
                availableLineCount--;

                // Repeat until text is short enough.
            } while (textLayout.getHeight() > availableHeight);

            // Draw text.
            canvas.save();
            Log.d("TEXTLOG", "tLeft: "+rectF.left);
            canvas.translate(rectF.left + mEventPadding, rectF.top + mEventPadding);
            textLayout.draw(canvas);
            canvas.restore();
        }
    }

}

