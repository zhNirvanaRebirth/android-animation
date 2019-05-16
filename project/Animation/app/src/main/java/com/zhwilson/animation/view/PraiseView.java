package com.zhwilson.animation.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.ViewGroup;

import com.zhwilson.animation.R;

public class PraiseView extends ViewGroup {
    private static final int DEFAULT_DRAWABLE_POSITION = 1;
    private static final float DEFAULT_DRAWABLE_PADDING = 5f;
    private static final float DEFAULT_TEXT_SIZE = 14f;
    private static final int DEFAULT_TEXT_COLOR = Color.parseColor("#666666");
    private static final int MAX_PRAISE_COUNT = 9999;

    private int drawablePosition;
    private float drawablePadding;
    private float countTextSize;
    private int countTextColor;
    private Paint textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Bitmap imgPraised;
    private Bitmap imgPraisedShining;
    private Bitmap imgUnPraised;

    private int minWidth;
    private int minHeight;
    private int verOffset;
    private int horOffset;

    private int praiseCount = MAX_PRAISE_COUNT - 1;
    private boolean isPraised = false;
    private float[] signalTextWidths = new float[1];
    private float textWidth;
    private float signalTextHeight;
    private float textHeight;//这里是三行的高度

    private AppCompatImageView praiseIV;
    private AppCompatImageView shiningIV;
    private int imgLeft;
    private int imgTop;
    private float textX;
    private float textY;
    private Status status = Status.Normal;
    public PraiseView(Context context) {
        this(context, null);
    }

    public PraiseView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PraiseView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.Praise, defStyleAttr, 0);
        drawablePosition = typedArray.getInt(R.styleable.Praise_DrawablePosition, DEFAULT_DRAWABLE_POSITION);
        drawablePadding = typedArray.getDimension(R.styleable.Praise_DrawablePadding, TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, DEFAULT_DRAWABLE_PADDING, context.getResources().getDisplayMetrics()));
        countTextSize = typedArray.getDimension(R.styleable.Praise_PraiseCountTextSize, TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, DEFAULT_TEXT_SIZE, context.getResources().getDisplayMetrics()));
        countTextColor = typedArray.getColor(R.styleable.Praise_PraiseCountTextColor, DEFAULT_TEXT_COLOR);
        typedArray.recycle();

        textPaint.setColor(countTextColor);
        textPaint.setTextSize(countTextSize);
        textPaint.getTextWidths("0", signalTextWidths);
        textWidth = textPaint.measureText(String.valueOf(MAX_PRAISE_COUNT));
        signalTextHeight = textPaint.getFontSpacing();
        textHeight = signalTextHeight*3;
        initBitmap(context);

        initChildren(context);
    }

    private void initChildren(Context context) {
        shiningIV = new AppCompatImageView(context);
        shiningIV.setImageBitmap(imgPraisedShining);
        addView(shiningIV);
        praiseIV = new AppCompatImageView(context);
        praiseIV.setImageBitmap(imgPraised);
        addView(praiseIV);
    }

    private void initBitmap(Context context) {
        imgPraised = BitmapFactory.decodeResource(context.getResources(), R.mipmap.jike_like_selected);
        imgPraisedShining = BitmapFactory.decodeResource(context.getResources(), R.mipmap.jike_like_selected_shining);
        imgUnPraised = BitmapFactory.decodeResource(context.getResources(), R.mipmap.jike_like_unselected);
    }

    @Override
    protected int getSuggestedMinimumWidth() {
        Log.e("zhwilson", "get min width");
        float width;
        if (drawablePosition%2 != 0) { //left or right
            width = getPaddingLeft() + getPaddingRight() + drawablePadding + textWidth + imgPraised.getWidth();
        } else {//top or bottom
            width = getPaddingLeft() + getPaddingRight() + Math.max(textWidth, imgPraised.getWidth());
        }
        return (int) width;
    }

    @Override
    protected int getSuggestedMinimumHeight() {
        Log.e("zhwilson", "get min height");
        float height;
        if (drawablePosition%2 != 0) {//left or right
            height = getPaddingTop() + getPaddingBottom() + Math.max(imgUnPraised.getHeight() + imgPraisedShining.getHeight(), textHeight);
        } else {//top or bottom
            height = getPaddingTop() + getPaddingBottom() + drawablePadding + textHeight + imgUnPraised.getHeight() + imgPraisedShining.getHeight();
        }
        return (int) height;
    }

    private int measure(int measureSpec, boolean isWidth){
        int result;
        int size = MeasureSpec.getSize(measureSpec);
        int mode = MeasureSpec.getMode(measureSpec);
        if (MeasureSpec.EXACTLY == mode) result = size;
        else {
            if (isWidth) result = Math.min(size, minWidth);
            else result = Math.min(size, minHeight);
        }
        return result;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        minWidth = getSuggestedMinimumWidth();
        minHeight = getSuggestedMinimumHeight();
        setMeasuredDimension(measure(widthMeasureSpec, true), measure(heightMeasureSpec, false));
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        horOffset = (w - minWidth)/2;
        verOffset = (h - minHeight)/2;
        calImgPosition();
        calTextPosition();
    }

    private void calImgPosition() {
        switch (drawablePosition) {
            case 1://left
                imgLeft = horOffset + getPaddingLeft();
                imgTop = verOffset + (minHeight - imgPraisedShining.getHeight() - imgPraised.getHeight())/2;
                break;
            case 2://top
                imgLeft = horOffset + (minWidth - imgPraised.getWidth())/2;
                imgTop = verOffset + getPaddingTop();
                break;
            case 3://right
                imgLeft = (int) (horOffset + getPaddingLeft() + textWidth + drawablePadding);
                imgTop = verOffset + (minHeight - imgPraisedShining.getHeight() - imgPraised.getHeight())/2;
                break;
            case 4://bottom
                imgLeft = horOffset + (minWidth - imgPraised.getWidth())/2;
                imgTop = (int) (verOffset + getPaddingTop() + textHeight + drawablePadding);
                break;
        }
    }

    private void calTextPosition() {
        switch (drawablePosition) {
            case 1://left
                textX = horOffset + getPaddingLeft() + imgPraised.getWidth() + drawablePadding;
                textY = verOffset + (minHeight - textHeight)/2;
                break;
            case 2://top
                textX = horOffset + getPaddingLeft();
                textY = verOffset + minHeight - getPaddingBottom() - textHeight;
                break;
            case 3://right
                textX = horOffset + getPaddingLeft();
                textY = verOffset + (minHeight - textHeight)/2;
                break;
            case 4://bottom
                textX = horOffset + getPaddingLeft();
                textY = verOffset + getPaddingTop();
                break;
        }
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        Log.e("zhwilson", "childCount:" + getChildCount());
        shiningIV.layout(imgLeft, imgTop, imgLeft + imgPraisedShining.getWidth(), imgTop + imgPraisedShining.getHeight());
        praiseIV.layout(imgLeft, imgTop + imgPraisedShining.getHeight(), imgLeft + imgPraised.getWidth(), imgTop + imgPraisedShining.getHeight() + imgPraised.getHeight());
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        Log.e("zhwilson", "draw child");
        if (status == Status.Normal) {
            canvas.drawText(String.valueOf(praiseCount), textX, textY + signalTextHeight*2, textPaint);
            return;
        }
        if (status == Status.Praising) {
            //current
            canvas.drawText(String.valueOf(praiseCount), textX, textY + signalTextHeight*2, textPaint);
            //next
            canvas.drawText(String.valueOf(1111), textX, textY + textHeight, textPaint);
            return;
        }
        if (status == Status.UnPraising) {
            //pre
            canvas.drawText("7", textX + signalTextWidths[0]*3, textY + signalTextHeight, textPaint);
            //current
            canvas.drawText(String.valueOf(praiseCount), textX, textY + signalTextHeight*2, textPaint);
            return;
        }
    }

    public void setPraiseCount(int praiseCount) {
        this.praiseCount = praiseCount;
    }

    public void setPraised(boolean praised) {
        isPraised = praised;
    }

    private void praise() {//点赞
        //TODO
    }

    private void unPraise() {//取消点赞
        //TODO
    }

    private enum Status {
        Praising, Normal, UnPraising
    }
}
