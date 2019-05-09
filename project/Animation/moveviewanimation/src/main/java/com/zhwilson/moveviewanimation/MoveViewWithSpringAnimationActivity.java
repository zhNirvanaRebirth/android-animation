package com.zhwilson.moveviewanimation;

import android.os.Bundle;
import android.support.animation.DynamicAnimation;
import android.support.animation.SpringAnimation;
import android.support.animation.SpringForce;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatTextView;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;

public class MoveViewWithSpringAnimationActivity extends AppCompatActivity {
    AppCompatTextView dragView;
    AppCompatTextView firstView;
    AppCompatTextView secondView;

    SpringAnimation firstXAnimation;
    SpringAnimation firstYAnimation;
    SpringAnimation secondXAnimation;
    SpringAnimation secondYAnimation;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_move_view_with_spring_animation);
        dragView = findViewById(R.id.drag_view);
        firstView = findViewById(R.id.first_view);
        secondView = findViewById(R.id.second_view);

        firstXAnimation = new SpringAnimation(firstView, DynamicAnimation.TRANSLATION_X);
        firstYAnimation = new SpringAnimation(firstView, DynamicAnimation.TRANSLATION_Y);
        secondXAnimation = new SpringAnimation(secondView, DynamicAnimation.TRANSLATION_X);
        secondYAnimation = new SpringAnimation(secondView, DynamicAnimation.TRANSLATION_Y);

        firstXAnimation.addUpdateListener(new DynamicAnimation.OnAnimationUpdateListener() {
            @Override
            public void onAnimationUpdate(DynamicAnimation dynamicAnimation, float v, float v1) {
                secondXAnimation.animateToFinalPosition(v);
            }
        });

        firstYAnimation.addUpdateListener(new DynamicAnimation.OnAnimationUpdateListener() {
            @Override
            public void onAnimationUpdate(DynamicAnimation dynamicAnimation, float v, float v1) {
                secondYAnimation.animateToFinalPosition(v);
            }
        });

        findViewById(R.id.container).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_MOVE:
                        dragView.animate().x(event.getX()).y(event.getY()).setDuration(0).start();
                        firstXAnimation.animateToFinalPosition(event.getX() - firstView.getLeft());
                        firstYAnimation.animateToFinalPosition(event.getY());
                        break;
                }
                return true;
            }
        });

        findViewById(R.id.drop).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SpringAnimation springAnimation = new SpringAnimation(dragView, DynamicAnimation.Y, 1000);
                springAnimation.getSpring().setDampingRatio(SpringForce.DAMPING_RATIO_HIGH_BOUNCY).setStiffness(SpringForce.STIFFNESS_VERY_LOW);
                springAnimation.start();
            }
        });
    }
}
