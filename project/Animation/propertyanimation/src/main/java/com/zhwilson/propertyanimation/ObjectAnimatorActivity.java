package com.zhwilson.propertyanimation;

import android.animation.AnimatorInflater;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatTextView;
import android.transition.Slide;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AnticipateOvershootInterpolator;
import android.view.animation.OvershootInterpolator;

public class ObjectAnimatorActivity extends AppCompatActivity{
    AppCompatTextView objectAnimatorText;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setEnterTransition(new Slide());
        setContentView(R.layout.activity_object_animator);
        objectAnimatorText = findViewById(R.id.text);
    }

    @Override
    protected void onStart() {
        super.onStart();
//        loadAnimatorInRes();
        playAnimatorInCode();
    }

    private void loadAnimatorInRes() {
        ObjectAnimator objectAnimator = (ObjectAnimator) AnimatorInflater.loadAnimator(this, R.animator.object_animator);
        objectAnimator.setInterpolator(new AnticipateOvershootInterpolator());
        objectAnimator.setTarget(objectAnimatorText);
        objectAnimator.start();
    }

    private void playAnimatorInCode() {
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(objectAnimatorText, "translationY", 0, 800);
        objectAnimator.setInterpolator(new OvershootInterpolator());
        objectAnimator.setDuration(3000);
        objectAnimator.start();
    }
}
