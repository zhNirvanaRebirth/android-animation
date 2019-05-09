package com.zhwilson.propertyanimation;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Pair;
import android.view.View;
import android.widget.Button;

public class PropertyAnimationActivity extends AppCompatActivity {
    View shareElement;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property_animation);
        findViewById(R.id.value_animator).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PropertyAnimationActivity.this, ValueAnimatorActivity.class), ActivityOptions.makeSceneTransitionAnimation(PropertyAnimationActivity.this).toBundle());
            }
        });
        findViewById(R.id.object_animator).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PropertyAnimationActivity.this, ObjectAnimatorActivity.class), ActivityOptions.makeSceneTransitionAnimation(PropertyAnimationActivity.this).toBundle());
            }
        });
        findViewById(R.id.animator_set).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PropertyAnimationActivity.this, AnimatorSetActivity.class), ActivityOptions.makeSceneTransitionAnimation(PropertyAnimationActivity.this).toBundle());
            }
        });
        findViewById(R.id.state_list_animator).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PropertyAnimationActivity.this, StateListAnimatorActivity.class));
            }
        });
        findViewById(R.id.animated_state_list_drawable).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PropertyAnimationActivity.this, AnimatedStateListDrawableActivity.class));
            }
        });
        findViewById(R.id.keyframe).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PropertyAnimationActivity.this, KeyFrameActivity.class));
            }
        });
        findViewById(R.id.view_property_animator).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PropertyAnimationActivity.this, ViewPropertyAnimatorActivity.class));
            }
        });
        findViewById(R.id.circular_reveal).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PropertyAnimationActivity.this, CircularRevealActivity.class));
            }
        });
        shareElement = findViewById(R.id.share_element);
        shareElement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PropertyAnimationActivity.this, ShareElementActivity.class),
                        ActivityOptions.makeSceneTransitionAnimation(PropertyAnimationActivity.this,
                                Pair.create(shareElement, "share_btn")).toBundle());
            }
        });
    }

}
