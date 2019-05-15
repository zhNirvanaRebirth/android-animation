package com.zhwilson.animation.view;

import android.content.Context;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class StereoView extends View{
    private int width;
    private int height;
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint backPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    private Matrix matrix = new Matrix();
    private Camera camera = new Camera();
    private float[] values = new float[9];
    private float scale = 1.0f;
    private float rotateDegree = 0f;
    private float rectWidth = 700f;
    private float rectHeight = 160f;
    private RectF rectF = new RectF(0, 0, rectWidth, rectHeight);
    private int[] colors = {Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW};
    private int currentColorIndex = 0;
    public StereoView(Context context) {
        this(context, null);
    }

    public StereoView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public StereoView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        scale = context.getResources().getDisplayMetrics().density;
        backPaint.setColor(Color.GRAY);
        backPaint.setStyle(Paint.Style.STROKE);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = w;
        height = h;
    }

    public void setRectWidth(float rectWidth) {
        this.rectWidth = rectWidth;
        rectF.right = rectWidth;
    }

    public void setRectHeight(float rectHeight) {
        this.rectHeight = rectHeight;
        rectF.bottom = rectHeight;
    }

    public void setRotateDegree(float rotateDegree) {
        this.rotateDegree = -Math.abs(rotateDegree%360f);
        Log.e("zhwilson", "rotateDegree:" + rotateDegree);
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.save();
        canvas.translate(width*1.0f/2 - rectWidth/2, height*1.0f/2 - rectHeight/2);
        canvas.drawRect(rectF, backPaint);
        canvas.restore();

        float degree = 0f;
        if (rotateDegree >= -90) {
            currentColorIndex = 0;
            degree = rotateDegree;
        } else if (rotateDegree >= -180) {
            currentColorIndex = 1;
            degree = rotateDegree+90f;
        } else if (rotateDegree >= -270) {
            currentColorIndex = 2;
            degree = rotateDegree+180f;
        } else {
            currentColorIndex = 3;
            degree = rotateDegree+270f;
        }
        drawRect(canvas, colors[currentColorIndex], degree, false);
        drawRect(canvas, colors[(currentColorIndex+1)%colors.length], degree, true);
    }

    private void drawRect(Canvas canvas, int color, float degree, boolean isTopRect) {
        float delta = (float) (rectHeight*(1-Math.cos(Math.abs(degree)*Math.PI/180)));
        canvas.save();
        matrix.reset();
        camera.save();
        float rDegree = isTopRect ? (float) (Math.acos(delta/rectHeight)*180/Math.PI) : degree;
        camera.rotateX(rDegree);
        camera.getMatrix(matrix);
        camera.restore();

        matrix.getValues(values);
        values[6] = values[6]/scale;
        values[7] = values[7]/scale;
        matrix.setValues(values);

        /**
         * canvas变换与matrix变换搭配使用情况：
         * 主要是分析清楚移动、旋转与投影的先后顺序（这个顺序和代码的先后无关，这里说的无关不是说代码无序执行，代码依然是从上往下依次执行，但最终代码构造出的变换矩阵是可能不同的，因此会影响结果）
         *
         * canvas.concat(matrix) 之前的变换是投影之后
         * canvas.concat(matrix) 之后的canvas变换是matrix变换之前
         */
        canvas.translate(width*1.0f/2, height*1.0f/2 - rectHeight/2 + delta);
        canvas.concat(matrix);
        canvas.translate(-rectWidth/2, isTopRect ? -rectHeight : 0);
        paint.setColor(color);
        canvas.drawRect(rectF, paint);
        canvas.restore();
    }

    private void drawTopRect(Canvas canvas, int color, float degree) {
        float delta = (float) (rectHeight*(1-Math.cos(Math.abs(degree)*Math.PI/180)));
        canvas.save();
        matrix.reset();
        camera.save();
        float rDegree = (float) (Math.acos(delta/rectHeight)*180/Math.PI);
        camera.rotateX(rDegree);
        camera.getMatrix(matrix);
        camera.restore();

        matrix.getValues(values);
        values[6] = values[6]/scale;
        values[7] = values[7]/scale;
        matrix.setValues(values);

        /**
         * canvas变换与matrix变换搭配使用情况：
         * 主要是分析清楚移动、旋转与投影的先后顺序（这个顺序和代码的先后无关，这里说的无关不是说代码无序执行，代码依然是从上往下依次执行，但最终代码构造出的变换矩阵是可能不同的，因此会影响结果）
         *
         * canvas.concat(matrix) 之前的变换是投影之后
         * canvas.concat(matrix) 之后的canvas变换是matrix变换之前
         */
        canvas.translate(width*1.0f/2, height*1.0f/2 - rectHeight/2 + delta);
        canvas.concat(matrix);
        canvas.translate(-rectWidth/2, -rectHeight);
        paint.setColor(color);
        canvas.drawRect(rectF, paint);
        canvas.restore();
    }
}
