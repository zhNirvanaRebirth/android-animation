package com.zhwilson.animation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnticipateOvershootInterpolator;
import android.view.animation.DecelerateInterpolator;

import com.zhwilson.animation.view.RotateClipView;

public class ClipAnimationActivity extends AppCompatActivity {
    RotateClipView clipView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clip_animation);
        clipView = findViewById(R.id.rotate_clip_view);
    }

    @Override
    protected void onStart() {
        super.onStart();
//        animation();
        clipRotateAnimation();
    }

//    private void animation() {
//        AnimatorSet animatorSet = new AnimatorSet();
//        ObjectAnimator startingAnimator = ObjectAnimator.ofFloat(clipView, "startAnimDegree", 0, -45f);
//        startingAnimator.setDuration(1000);
//        startingAnimator.addListener(new AnimatorListenerAdapter() {
//            @Override
//            public void onAnimationCancel(Animator animation) {
//                super.onAnimationCancel(animation);
//                clipView.setStatus(RotateClipView.Status.Starting);
//            }
//
//            @Override
//            public void onAnimationStart(Animator animation) {
//                super.onAnimationStart(animation);
//                clipView.setStatus(RotateClipView.Status.Starting);
//            }
//        });
//        ObjectAnimator rotateAnimator = ObjectAnimator.ofFloat(clipView, "rotateAnimDegree", 0f, 270f);
//        rotateAnimator.setDuration(1500);
//        rotateAnimator.setInterpolator(new DecelerateInterpolator());
//        rotateAnimator.addListener(new AnimatorListenerAdapter() {
//            @Override
//            public void onAnimationCancel(Animator animation) {
//                super.onAnimationCancel(animation);
//                clipView.setStatus(RotateClipView.Status.Starting);
//            }
//
//            @Override
//            public void onAnimationStart(Animator animation) {
//                super.onAnimationStart(animation);
//                clipView.setStatus(RotateClipView.Status.Rotation);
//            }
//        });
//        ObjectAnimator endingAnimator = ObjectAnimator.ofFloat(clipView, "endAnimDegree", 0, -45f);
//        endingAnimator.setDuration(1000);
//        endingAnimator.addListener(new AnimatorListenerAdapter() {
//            @Override
//            public void onAnimationCancel(Animator animation) {
//                super.onAnimationCancel(animation);
//                clipView.setStatus(RotateClipView.Status.Starting);
//            }
//
//            @Override
//            public void onAnimationStart(Animator animation) {
//                super.onAnimationStart(animation);
//                clipView.setStatus(RotateClipView.Status.Ending);
//            }
//        });
//        animatorSet.play(endingAnimator).after(1000).after(rotateAnimator);
//        animatorSet.play(rotateAnimator).after(1000).after(startingAnimator);
//        animatorSet.start();
//    }

    private void clipRotateAnimation(){
        ObjectAnimator rotateAnimator = ObjectAnimator.ofFloat(clipView, "rotateDegree", 0f, 270f);
        rotateAnimator.setDuration(3000);
        rotateAnimator.setInterpolator(new AnticipateOvershootInterpolator());
        rotateAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationCancel(Animator animation) {
                super.onAnimationCancel(animation);
                clipView.setStatus(RotateClipView.Status.Starting);
            }

            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                clipView.setStatus(RotateClipView.Status.Rotation);
            }
        });
        rotateAnimator.start();
    }
}
