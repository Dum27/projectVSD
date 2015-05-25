package com.ielts.mcpp.ielts.utils;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by taras on 22.05.2015.
 */
public class PercentView extends View {

    public PercentView (Context context, int progressColor) {
        super(context);
        this.primary = progressColor;
        init();
    }
    public PercentView (Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }
    public PercentView (Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }
    private void init() {

        paint = new Paint();
        paint.setColor(primary);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);

        bgpaint = new Paint();
        bgpaint.setColor(Color.GRAY);
        bgpaint.setAntiAlias(true);
        bgpaint.setStyle(Paint.Style.FILL);

        white = new Paint();
        white.setColor(Color.WHITE);
        white.setAntiAlias(true);
        white.setStyle(Paint.Style.FILL);

        rect = new RectF();
        rectWhite = new RectF();
    }
    Paint paint;
    Paint bgpaint;
    int primary;
    Paint white;
    RectF rect;
    RectF rectWhite;

    float percentage = 0;


    public void setProgressColor(int color){
        primary = color;
        init();
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //draw background circle anyway
        int left = 0;
        int width = getWidth();
        int top = 0;
        rect.set(left, top, left+width, top + width);
        rectWhite.set(left+20, top+20, left+width-20, top + width-20);
        canvas.drawArc(rect, -90, 360, true, bgpaint);
        if(percentage!=0) {
            canvas.drawArc(rect, -90, (360*percentage), true, paint);
        }
        canvas.drawArc(rectWhite, -90, 360, true, white);

    }
    public void setPercentage(float percentage) {
        this.percentage = percentage / 100;
        invalidate();
    }
}