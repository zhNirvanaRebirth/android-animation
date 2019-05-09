package com.zhwilson.drawableanimation;

import android.graphics.drawable.Animatable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageView;

public class MorphAnimatedVectorDrawableActivity extends AppCompatActivity {
    AppCompatImageView vectorImg;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_morph_animated_vector_drawable);
        vectorImg = findViewById(R.id.vector_img);
    }

    @Override
    protected void onStart() {
        super.onStart();
        vectorImg.setImageResource(R.drawable.animated_directory_vector_drawable);
        Animatable animatable = (Animatable) vectorImg.getDrawable();
        animatable.start();
    }
}
