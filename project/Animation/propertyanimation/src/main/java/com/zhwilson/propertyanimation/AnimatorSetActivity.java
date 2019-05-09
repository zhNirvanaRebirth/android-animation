package com.zhwilson.propertyanimation;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatTextView;
import android.transition.Fade;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;

public class AnimatorSetActivity extends AppCompatActivity {
    AppCompatTextView animatorSetText;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setEnterTransition(new Fade());
        getWindow().setExitTransition(new Fade());
        setContentView(R.layout.activity_animator_set);
        animatorSetText = findViewById(R.id.text);
    }

    @Override
    protected void onStart() {
        super.onStart();
//        loadAnimatorInRes();
        playAnimatorInCode();
    }

    private void loadAnimatorInRes() {
        AnimatorSet animatorSet = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.animator_set);
        animatorSet.setTarget(animatorSetText);
        animatorSet.start();
    }

    private void playAnimatorInCode() {
        ObjectAnimator objectAnimatorX = ObjectAnimator.ofFloat(animatorSetText, "translationX", 0, 800);
        objectAnimatorX.setDuration(3000);
        objectAnimatorX.setInterpolator(new AccelerateInterpolator());

        ObjectAnimator objectAnimatorY = ObjectAnimator.ofFloat(animatorSetText, "translationY", 0, 1200);
        objectAnimatorY.setDuration(3000);
        objectAnimatorY.setInterpolator(new DecelerateInterpolator());

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(objectAnimatorY).with(objectAnimatorX);
        animatorSet.start();
    }
}
