package com.zhwilson.drawableanimation;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageView;

public class AnimationDrawableActivity extends AppCompatActivity {
    AppCompatImageView animationDrawableImg;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation_drawable);
        animationDrawableImg = findViewById(R.id.img);
    }

    @Override
    protected void onStart() {
        super.onStart();
        animationDrawableImg.setImageResource(R.drawable.animation_drawable);
        AnimationDrawable animationDrawable = (AnimationDrawable) animationDrawableImg.getDrawable();
        animationDrawable.start();
    }
}
