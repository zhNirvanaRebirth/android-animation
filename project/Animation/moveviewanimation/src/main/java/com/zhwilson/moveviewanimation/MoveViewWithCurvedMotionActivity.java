package com.zhwilson.moveviewanimation;

import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.graphics.Path;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.view.animation.PathInterpolator;

public class MoveViewWithCurvedMotionActivity extends AppCompatActivity {
    AppCompatTextView view;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_move_view_with_curved_motion);
        view = findViewById(R.id.view);
    }

    @Override
    protected void onStart() {
        super.onStart();
        moveWithCustomPath();
    }

    private void moveWithPathInterpolator() {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
            Path path = new Path();
            path.moveTo(0f, 0f);
            path.quadTo(0f, 2.0f, 1.0f, 1.0f);
            PathInterpolator pathInterpolator = new PathInterpolator(path);
            ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(view, "translationY", 500);
            objectAnimator.setInterpolator(pathInterpolator);
            objectAnimator.setDuration(3000);
            objectAnimator.start();
        }
    }

    private void moveWithCustomPath() {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
            Path path = new Path();
            path.arcTo(0f, 0f, 1000f, 1000f, 270f, -180f, true);
            ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(view, View.X, View.Y, path);
            objectAnimator.setDuration(3000);
            objectAnimator.start();
        }
    }
}
