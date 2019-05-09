package com.zhwilson.propertyanimation;

import android.animation.AnimatorInflater;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

public class StateListAnimatorActivity extends AppCompatActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_state_list_animator);
        findViewById(R.id.button2).setStateListAnimator(AnimatorInflater.loadStateListAnimator(this, R.animator.state_list_animator));
    }
}
