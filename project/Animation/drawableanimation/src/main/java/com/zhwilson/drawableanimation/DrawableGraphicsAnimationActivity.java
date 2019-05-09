package com.zhwilson.drawableanimation;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class DrawableGraphicsAnimationActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawable_graphics);
        findViewById(R.id.animation_drawable).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DrawableGraphicsAnimationActivity.this, AnimationDrawableActivity.class));
            }
        });
        findViewById(R.id.animated_vector_drawable).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DrawableGraphicsAnimationActivity.this, AnimatedVectorDrawableActivity.class));
            }
        });
        findViewById(R.id.morph_animated_vector_drawable).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DrawableGraphicsAnimationActivity.this, MorphAnimatedVectorDrawableActivity.class));
            }
        });
        findViewById(R.id.animated_selector_vector_drawable).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DrawableGraphicsAnimationActivity.this, AnimatedSelectorVectorDrawableActivity.class));
            }
        });
        findViewById(R.id.trim_path_vector_drawable).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DrawableGraphicsAnimationActivity.this, TrimPathVectorDrawableActivity.class));
            }
        });
    }
}
