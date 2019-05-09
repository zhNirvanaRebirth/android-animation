package com.zhwilson.revealandhide;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageView;
import android.view.View;

public class CrossfadeAnimationActivity extends AppCompatActivity {
    AppCompatImageView imgOne;
    AppCompatImageView imgTwo;
    boolean flag = true;
    private int shortAnimationDuration;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crossfade_animation);
        imgOne = findViewById(R.id.img_one);
        imgTwo = findViewById(R.id.img_two);
        imgTwo.setVisibility(View.GONE);
        shortAnimationDuration = getResources().getInteger(android.R.integer.config_shortAnimTime);
        findViewById(R.id.switch_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag) { //one -> two
                    flag = false;
                    fade(imgOne, imgTwo);
                } else { //two -> one
                    flag = true;
                    fade(imgTwo, imgOne);
                }
                imgOne.animate().start();
                imgTwo.animate().start();
            }
        });
    }

    private void fade(final View from, View to) {
        to.setVisibility(View.VISIBLE);
        to.setAlpha(0f);
        to.animate().alpha(1).setDuration(shortAnimationDuration).setListener(null);
        from.animate().alpha(0).setDuration(shortAnimationDuration).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                from.setVisibility(View.GONE);
            }
        });
    }
}
