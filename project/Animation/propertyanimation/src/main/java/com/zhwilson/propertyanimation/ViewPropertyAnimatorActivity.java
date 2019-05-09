package com.zhwilson.propertyanimation;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatTextView;

public class ViewPropertyAnimatorActivity extends AppCompatActivity {
    AppCompatTextView viewPropertyAnimatorText;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_property_animator);
        viewPropertyAnimatorText = findViewById(R.id.text);
    }

    @Override
    protected void onStart() {
        super.onStart();
//        playWithAnimatorSet();
//        playWithObjectAnimator();
        playWithViewPropertyAnimator();
    }

    private void playWithAnimatorSet() {
        ObjectAnimator objectAnimator1 = ObjectAnimator.ofFloat(viewPropertyAnimatorText, "x", 50);
        ObjectAnimator objectAnimator2 = ObjectAnimator.ofFloat(viewPropertyAnimatorText, "y", 100);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(objectAnimator1, objectAnimator2);
        animatorSet.setDuration(3000);
        animatorSet.start();
    }

    private void playWithObjectAnimator() {
        PropertyValuesHolder propertyValuesHolder1 = PropertyValuesHolder.ofFloat("x", 100);
        PropertyValuesHolder propertyValuesHolder2 = PropertyValuesHolder.ofFloat("y", 50);
        ObjectAnimator.ofPropertyValuesHolder(viewPropertyAnimatorText, propertyValuesHolder1, propertyValuesHolder2).setDuration(3000).start();
    }

    private void playWithViewPropertyAnimator() {
        viewPropertyAnimatorText.animate().x(100).y(100).setDuration(3000).start();
    }
}
