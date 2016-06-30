package com.lianqi.union.customviewtest;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 *表面凹凸不平的view
 * @time 2016/6/30 11:14
 */
public class UnevenSurfaceView extends LinearLayout {
    //圆半径
    private int radius=20;
    //圆间距
    private int CircularPitch=18;
    private Paint mPaint;
    //圆数量
    private int circularNum;
    //边缘边距
    private int edgeSpace;

    public UnevenSurfaceView(Context context) {
        super(context);
    }

    public UnevenSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray =  context.getTheme().obtainStyledAttributes(attrs,R.styleable.UnevenSurfaceView,0,0);
        int indexCount = typedArray.getIndexCount();
        for (int i = 0; i <indexCount; i++) {
            int aIndex = typedArray.getIndex(i);
            switch (aIndex){
                case R.styleable.UnevenSurfaceView_radius:
                    this.radius=typedArray.getInt(i,10);
                    break;
                case R.styleable.UnevenSurfaceView_circularSpace:
                    this.CircularPitch=typedArray.getInt(i,6);
                    break;
            }
        }
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setDither(true);
        mPaint.setColor(Color.WHITE);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        circularNum=(getWidth()+CircularPitch)/(radius*2+CircularPitch);
        edgeSpace=(getWidth()-circularNum*radius*2-(circularNum-1)*CircularPitch)/2;
        for (int i = 0; i <circularNum ; i++) {
            float x=edgeSpace+radius+(radius*2+CircularPitch)*i;
            canvas.drawCircle(x,0,radius,mPaint);
            canvas.drawCircle(x,getHeight(),radius,mPaint);
        }
    }
}
