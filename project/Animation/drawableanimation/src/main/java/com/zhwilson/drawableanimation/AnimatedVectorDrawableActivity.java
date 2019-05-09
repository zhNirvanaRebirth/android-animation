package com.zhwilson.drawableanimation;

import android.graphics.drawable.Animatable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageView;

public class AnimatedVectorDrawableActivity extends AppCompatActivity {
    AppCompatImageView animatedVectorDrawableImg;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animated_vector_drawable);
        animatedVectorDrawableImg = findViewById(R.id.img);
    }

    @Override
    protected void onStart() {
        super.onStart();
        animatedVectorDrawableImg.setImageResource(R.drawable.animated_vector_drawable);
        Animatable animatedVectorDrawable = (Animatable) animatedVectorDrawableImg.getDrawable();
        animatedVectorDrawable.start();
    }
}
