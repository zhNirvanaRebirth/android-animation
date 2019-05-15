package com.zhwilson.animation.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.zhwilson.animation.R;

/**
 *
 *
 * questions:
 *              没有考虑图片大于屏幕的情况
 */
public class RotateClipView extends View {
    private Bitmap img;
    private int screenWidth;
    private int screenHeight;
    private int width;
    private int height;
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Matrix matrix = new Matrix();
    private Camera camera = new Camera();
    private RectF clipRectFOne = new RectF();
    private RectF clipRectFTwo = new RectF();
    private float[] values = new float[9];

    float scale = 1.0f;
    private Status status;
    private float rotateDegree = 0f;

    public RotateClipView(Context context) {
        this(context, null);
    }

    public RotateClipView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RotateClipView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        img = BitmapFactory.decodeResource(context.getResources(), R.drawable.beauty_img);
//        img = BitmapFactory.decodeResource(context.getResources(), R.drawable.maps);
//        img = BitmapFactory.decodeResource(context.getResources(), R.drawable.beauty6);//不是长宽相同的图片会有问题：解决办法，增加clipRect的size
        // 获取手机像素密度 （即dp与px的比例）
        scale = context.getResources().getDisplayMetrics().density;
        screenWidth = getResources().getDisplayMetrics().widthPixels;
        screenHeight = getResources().getDisplayMetrics().heightPixels;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(measure(widthMeasureSpec, true), measure(heightMeasureSpec, false));
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = w;
        height = h;
        initClipRectF();
    }

    private void initClipRectF() {//前提是坐标中心移动到了view中心
        clipRectFOne.left = -width*1.0f/2;
//        clipRectFOne.left = -width*1.0f/2 - 100;
        clipRectFOne.top = -height*1.0f/2;
        clipRectFOne.right = 0f;
        clipRectFOne.bottom = height*1.0f/2;

        clipRectFTwo.left = 0f;
        clipRectFTwo.top = -height*1.0f/2;
        clipRectFTwo.right = width*1.0f/2;
//        clipRectFTwo.right = width*1.0f/2 + 100;
        clipRectFTwo.bottom = height*1.0f/2;
    }

    @Override
    protected int getSuggestedMinimumWidth() {
        return img == null ? 0 : diagonalLength();
    }

    @Override
    protected int getSuggestedMinimumHeight() {
        return img == null ? 0 : diagonalLength();
    }

    private int diagonalLength() {
        return (int) Math.sqrt(Math.pow(img.getWidth(), 2) + Math.pow(img.getHeight(), 2));
    }

    private int measure(int measureSpec, boolean isWidth) {
        int result;
        int size = MeasureSpec.getSize(measureSpec);
        int mode = MeasureSpec.getMode(measureSpec);
        if (MeasureSpec.EXACTLY == mode) result = size;
        else {
            if (isWidth) result = Math.min(getSuggestedMinimumWidth(), size);
            else result = Math.min(getSuggestedMinimumHeight(), size);
        }
        return result;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(width*1.0f/2, height*1.0f/2);//当前坐标原点：view中心

        canvas.save();
        matrix.reset();
        camera.save();
        camera.rotateY(-45f);
        camera.getMatrix(matrix);
        camera.restore();
        //修正
        matrix.getValues(values);
        values[6] = values[6]/scale;
        values[7] = values[7]/scale;
        matrix.setValues(values);
        canvas.rotate(-rotateDegree);
        canvas.concat(matrix);
        canvas.clipRect(clipRectFTwo);
        canvas.rotate(rotateDegree);
        canvas.drawBitmap(img, -img.getWidth()*1.0f/2, -img.getHeight()*1.0f/2, paint);
        canvas.restore();

        canvas.save();
        canvas.rotate(-rotateDegree);
        canvas.clipRect(clipRectFOne);
        canvas.rotate(rotateDegree);
        canvas.drawBitmap(img, -img.getWidth()*1.0f/2, -img.getHeight()*1.0f/2, paint);
        canvas.restore();
    }

    public void setRotateDegree(float rotateDegree) {
        this.rotateDegree = rotateDegree;
        invalidate();
    }

    public enum Status {
        Starting, Rotation, Ending
    }
}
