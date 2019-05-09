package com.zhwilson.layoutupdateanimation;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.transition.Scene;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.transition.TransitionValues;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

public class LayoutChangeWithCustomTransitionActivity extends AppCompatActivity {
    FrameLayout container;
    Scene one;
    Scene two;
    boolean showOne = true;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_change_with_custom_transition);
        container = findViewById(R.id.container);
        one = Scene.getSceneForLayout(container, R.layout.layout_scene_for_custom_transition_one, this);
        two = Scene.getSceneForLayout(container, R.layout.layout_scene_for_custom_transition_two, this);
        findViewById(R.id.change).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Scene ending = showOne ? two : one;
                TransitionManager.go(ending, new CustomTransition());
                showOne = !showOne;
            }
        });
    }

    public class CustomTransition extends Transition {
        private static final String TRANSITION_KEY = "com.zhwilson.layoutupdateanimation:CustomTransition:textSize";
        @Override
        public void captureStartValues(TransitionValues transitionValues) {
            if (transitionValues.view instanceof TextView) {
                TextView view = (TextView) transitionValues.view;
                transitionValues.values.put(TRANSITION_KEY, view.getTextSize());
            }
        }

        @Override
        public void captureEndValues(TransitionValues transitionValues) {
            if (transitionValues.view instanceof TextView) {
                TextView view = (TextView) transitionValues.view;
                transitionValues.values.put(TRANSITION_KEY, view.getTextSize());
            }
        }

        @Override
        public Animator createAnimator(ViewGroup sceneRoot, TransitionValues startValues, TransitionValues endValues) {
            if (startValues.view instanceof TextView) {
                final TextView view = (TextView) startValues.view;
                ValueAnimator valueAnimator = ValueAnimator.ofFloat((Float) startValues.values.get(TRANSITION_KEY), (Float) endValues.values.get(TRANSITION_KEY));
                valueAnimator.setDuration(5000);
                valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        view.setTextSize((Float) animation.getAnimatedValue());
                    }
                });
                return valueAnimator;
            } else return null;
        }
    }
}
