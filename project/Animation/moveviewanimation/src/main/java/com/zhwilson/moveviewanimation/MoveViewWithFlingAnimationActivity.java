package com.zhwilson.moveviewanimation;

import android.os.Bundle;
import android.support.animation.DynamicAnimation;
import android.support.animation.FlingAnimation;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatTextView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;

public class MoveViewWithFlingAnimationActivity extends AppCompatActivity implements GestureDetector.OnGestureListener {
    AppCompatTextView view;
    GestureDetector gestureDetector;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_move_view_with_fling_animation);
        view = findViewById(R.id.view);
        gestureDetector = new GestureDetector(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
//        sampleFling();
    }

    private void sampleFling() {
        FlingAnimation flingAnimation = new FlingAnimation(view, DynamicAnimation.ROTATION);
        flingAnimation.setMinValue(0).setMaxValue(1500).setStartVelocity(1450).setFriction(0.6f).start();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (gestureDetector != null) return gestureDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        Log.e("zhwilson", "velocityX:" + velocityX + "---velocityY:" + velocityY);
//        boolean isTransX = Math.abs(velocityX) > Math.abs(velocityY);
//        FlingAnimation flingAnimation = new FlingAnimation(view, isTransX ? DynamicAnimation.TRANSLATION_X : DynamicAnimation.TRANSLATION_Y);
//        flingAnimation.setMinValue(0).setMaxValue(isTransX ? 800 : 1450).setStartVelocity(isTransX ? velocityX : velocityY).setFriction(1.1f).start();
        trans(true, velocityX);
        trans(false, velocityY);
        return true;
    }

    private void trans(boolean isTransX, float velocity) {
        FlingAnimation flingAnimation = new FlingAnimation(view, isTransX ? DynamicAnimation.TRANSLATION_X : DynamicAnimation.TRANSLATION_Y);
        flingAnimation.setMinValue(0).setMaxValue(isTransX ? 800 : 1450).setStartVelocity(velocity).setFriction(1.1f).start();
    }
}
