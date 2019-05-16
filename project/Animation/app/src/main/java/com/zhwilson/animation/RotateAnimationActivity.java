package com.zhwilson.animation;

import android.animation.ObjectAnimator;
import android.graphics.Path;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.PathInterpolator;

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
        ObjectAnimator rotateAnimator = ObjectAnimator.ofFloat(stereoView, "rotateDegree", -270f, -360f);
        rotateAnimator.setDuration(1500);
        Path path = new Path();
        path.moveTo(0, 0);
        path.quadTo(0.8f, 1, 0.8f, 1.4f);
        path.quadTo(1, 1.4f, 1, 1);
        rotateAnimator.setInterpolator(new PathInterpolator(path));
        rotateAnimator.start();
    }
}
