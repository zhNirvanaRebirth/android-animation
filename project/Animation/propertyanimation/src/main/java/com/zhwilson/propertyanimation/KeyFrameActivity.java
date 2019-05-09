package com.zhwilson.propertyanimation;

import android.animation.Keyframe;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatTextView;

public class KeyFrameActivity extends AppCompatActivity {
    AppCompatTextView keyframeText;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keyframe);
        keyframeText = findViewById(R.id.text);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Keyframe keyframe1 = Keyframe.ofFloat(0, 0);
        Keyframe keyframe2 = Keyframe.ofFloat(0.5f, 360f);
        Keyframe keyframe3 = Keyframe.ofFloat(1.0f, 0);
        PropertyValuesHolder propertyValuesHolder = PropertyValuesHolder.ofKeyframe("rotation", keyframe1, keyframe2, keyframe3);
        ObjectAnimator objectAnimator = new ObjectAnimator();
        objectAnimator.setValues(propertyValuesHolder);
        objectAnimator.setDuration(3000);
        objectAnimator.setTarget(keyframeText);
        objectAnimator.start();
    }
}
