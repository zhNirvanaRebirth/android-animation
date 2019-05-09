package com.zhwilson.moveviewanimation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageView;
import android.view.View;

public class ZoomViewAnimationActivity extends AppCompatActivity {
    AppCompatImageView smallView;
    AppCompatImageView bigView;

    Rect startBounds = new Rect();
    Rect endBounds = new Rect();
    Point offset = new Point();
    float scale = 1.0f;

    AnimatorSet currentAnimatorSet = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zoom_view);
        smallView = findViewById(R.id.small_img);
        bigView = findViewById(R.id.big_img);

        smallView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentAnimatorSet != null) currentAnimatorSet.cancel();

                smallView.getGlobalVisibleRect(startBounds);
                findViewById(R.id.container).getGlobalVisibleRect(endBounds, offset);
                startBounds.offset(-offset.x, -offset.y);
                endBounds.offset(-offset.x, -offset.y);

                if (endBounds.width()*1.0f / endBounds.height() > startBounds.width()*1.0f / startBounds.height()) {
                    scale = startBounds.height()*1.0f / endBounds.height();
                    float startWidth = endBounds.width() * scale;
                    float deltaWidth = (startWidth - startBounds.width()) / 2;
                    startBounds.left -= deltaWidth;
                    startBounds.right += deltaWidth;
                } else {
                    scale = startBounds.width()*1.0f / endBounds.width();
                    float startHeight = endBounds.height() * scale;
                    float deltaHeight = (startHeight - startBounds.height()) / 2;
                    startBounds.top -= deltaHeight;
                    startBounds.bottom += deltaHeight;
                }

                smallView.setAlpha(0f);
                bigView.setVisibility(View.VISIBLE);

                bigView.setPivotX(0f);
                bigView.setPivotY(0f);

                AnimatorSet set = new AnimatorSet();
                set.play(ObjectAnimator.ofFloat(bigView, View.X, startBounds.left, endBounds.left))
                        .with(ObjectAnimator.ofFloat(bigView, View.Y, startBounds.top, endBounds.top))
                        .with(ObjectAnimator.ofFloat(bigView, View.SCALE_X, scale, 1))
                        .with(ObjectAnimator.ofFloat(bigView, View.SCALE_Y, scale, 1));
                set.setDuration(getResources().getInteger(android.R.integer.config_shortAnimTime));
                set.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        currentAnimatorSet = null;
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {
                        super.onAnimationCancel(animation);
                        currentAnimatorSet = null;
                    }
                });
                set.start();
                currentAnimatorSet = set;
            }
        });

        bigView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentAnimatorSet != null) currentAnimatorSet.cancel();
                AnimatorSet set = new AnimatorSet();
                set.play(ObjectAnimator.ofFloat(bigView, View.X, startBounds.left))
                        .with(ObjectAnimator.ofFloat(bigView, View.Y, startBounds.top))
                        .with(ObjectAnimator.ofFloat(bigView, View.SCALE_X, scale))
                        .with(ObjectAnimator.ofFloat(bigView, View.SCALE_Y, scale));
                set.setDuration(getResources().getInteger(android.R.integer.config_longAnimTime));
                set.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        smallView.setAlpha(1f);
                        bigView.setVisibility(View.GONE);
                        currentAnimatorSet = null;
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {
                        super.onAnimationCancel(animation);
                        smallView.setAlpha(1f);
                        bigView.setVisibility(View.GONE);
                        currentAnimatorSet = null;
                    }
                });
                set.start();
                currentAnimatorSet = set;
            }
        });
    }

}
