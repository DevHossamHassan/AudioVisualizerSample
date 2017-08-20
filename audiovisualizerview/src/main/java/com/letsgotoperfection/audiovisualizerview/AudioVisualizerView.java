package com.letsgotoperfection.audiovisualizerview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * @author hossam.
 */

public class AudioVisualizerView extends View {

    final static int WAVES = 50;
    Rect rect;
    Paint paint;
    Path path;
    float x1, x2, y1, y2;
    float w, h;
    int waveSize;
    float waveSlice;
    float xWaveStart;
    float xQuarterWaveSize;

    public AudioVisualizerView(Context context) {
        super(context);
        init(null);
    }

    public AudioVisualizerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public AudioVisualizerView(Context context, @Nullable AttributeSet attrs, int
            defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);

    }

    public AudioVisualizerView(Context context, @Nullable AttributeSet attrs, int
            defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);
    }

    private void init(@Nullable AttributeSet attrs) {


//         0
//        0.........
//         .........
//      h/2.........
//         .........
//         .........


        rect = new Rect();
        path = new Path();
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStyle(Paint.Style.STROKE);
        final float density = getResources().getDisplayMetrics().density;
        paint.setStrokeWidth(4);

        if (attrs == null) return;
        TypedArray attributes = getContext().obtainStyledAttributes(attrs, R.styleable
                .AudioVisualizerView);
        int wave1Color = attributes.getColor(R.styleable.AudioVisualizerView_Wave1_color,
                Color.GREEN);
        paint.setColor(Color.GREEN);


        attributes.recycle();
    }

    private void drawWave() {
        h = getHeight();
        w = getWidth();
        x1 = 0;
        y1 = h / 2;
        waveSize = (int) w / WAVES;
        waveSlice = (float) waveSize / 2f;
        path.reset();
        path.moveTo(x1, y1);
        //path.arcTo(new RectF(0, 0, waveSlice, y1), 0, 270);
        xQuarterWaveSize = waveSlice / 2f;
        xWaveStart = xQuarterWaveSize;
        path.lineTo(xWaveStart, 0);
        path.lineTo(waveSlice, y1);
        path.lineTo(waveSlice + xWaveStart, h);
        path.lineTo(waveSize, y1);
        xWaveStart = waveSize;
        for (int i = 0; i < WAVES; i++) {
            xWaveStart += xQuarterWaveSize;
            path.lineTo(xWaveStart, 0);
            xWaveStart += xQuarterWaveSize;
            path.lineTo(xWaveStart, h / 2);
            xWaveStart += xQuarterWaveSize;
            path.lineTo(xWaveStart, h);
            xWaveStart += xQuarterWaveSize;
            path.lineTo(xWaveStart, h / 2);
        }

    }

    @Override
    protected void onDraw(Canvas canvas) {
        drawWave();
        canvas.drawPath(path, paint);
        super.onDraw(canvas);
    }
}
