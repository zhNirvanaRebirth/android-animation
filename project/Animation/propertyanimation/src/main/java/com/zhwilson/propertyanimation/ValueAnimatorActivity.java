package com.zhwilson.propertyanimation;

import android.animation.AnimatorInflater;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatTextView;
import android.transition.Explode;

public class ValueAnimatorActivity extends AppCompatActivity {
    AppCompatTextView valueAnimatorText;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setEnterTransition(new Explode());
        setContentView(R.layout.activity_value_animator);
        valueAnimatorText = findViewById(R.id.text);
    }

    @Override
    protected void onStart() {
        super.onStart();
        loadAnimatorInRes();
//        playAnimatorInCode();
    }

    private void loadAnimatorInRes() {
        ValueAnimator valueAnimator = (ValueAnimator) AnimatorInflater.loadAnimator(this, R.animator.value_animator);
        valueAnimator.setTarget(valueAnimatorText);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                valueAnimatorText.setBackgroundColor((Integer) animation.getAnimatedValue());
            }
        });
        valueAnimator.start();
    }

    private void playAnimatorInCode() {
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(12f, 16f);
        valueAnimator.setDuration(3000);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                valueAnimatorText.setTextSize((Float) animation.getAnimatedValue());
            }
        });
        valueAnimator.start();
    }
}
