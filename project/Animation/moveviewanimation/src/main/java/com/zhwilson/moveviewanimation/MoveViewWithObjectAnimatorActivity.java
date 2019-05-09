package com.zhwilson.moveviewanimation;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

public class MoveViewWithObjectAnimatorActivity extends AppCompatActivity {
    AppCompatTextView view;
    float xTrans = 10;
    float yTrans = 10;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_move_view_with_objectanimator);
        view = findViewById(R.id.view);
        findViewById(R.id.translation_x).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(view, "translationX", xTrans);
                objectAnimator.setDuration(200);
                objectAnimator.start();
                xTrans += 10;
            }
        });

        findViewById(R.id.translation_y).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(view, "translationY", yTrans);
                objectAnimator.setDuration(200);
                objectAnimator.start();
                yTrans += 10;
            }
        });
    }
}
