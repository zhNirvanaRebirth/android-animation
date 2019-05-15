package com.zhwilson.animation;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.OvershootInterpolator;

import com.zhwilson.animation.view.StereoView;

public class RotateAnimationActivity extends AppCompatActivity {
    private StereoView stereoView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rotate_animation);
        stereoView = findViewById(R.id.view);
    }

    @Override
    protected void onStart() {
        super.onStart();
        ObjectAnimator rotateAnimator = ObjectAnimator.ofFloat(stereoView, "rotateDegree", 90f, -360f);
        rotateAnimator.setDuration(3000);
        rotateAnimator.setInterpolator(new OvershootInterpolator());
        rotateAnimator.start();
    }
}
