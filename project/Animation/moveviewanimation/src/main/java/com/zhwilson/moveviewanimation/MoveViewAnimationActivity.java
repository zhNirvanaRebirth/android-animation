package com.zhwilson.moveviewanimation;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MoveViewAnimationActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_move_view);
        findViewById(R.id.translation_xy).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MoveViewAnimationActivity.this, MoveViewWithObjectAnimatorActivity.class));
            }
        });
        findViewById(R.id.curved_motion).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MoveViewAnimationActivity.this, MoveViewWithCurvedMotionActivity.class));
            }
        });
        findViewById(R.id.fling_animation).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MoveViewAnimationActivity.this, MoveViewWithFlingAnimationActivity.class));
            }
        });
        findViewById(R.id.spring_animation).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MoveViewAnimationActivity.this, MoveViewWithSpringAnimationActivity.class));
            }
        });
        findViewById(R.id.zoom_animation).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MoveViewAnimationActivity.this, ZoomViewAnimationActivity.class));
            }
        });
    }
}
