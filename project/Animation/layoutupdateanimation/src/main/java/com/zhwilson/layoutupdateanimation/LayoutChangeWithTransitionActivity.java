package com.zhwilson.layoutupdateanimation;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.transition.AutoTransition;
import android.transition.ChangeBounds;
import android.transition.Scene;
import android.transition.Transition;
import android.transition.TransitionListenerAdapter;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;

public class LayoutChangeWithTransitionActivity extends AppCompatActivity {
    FrameLayout container;
    Scene one;
    Scene two;
    boolean oneShow = true;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_change_with_transition);
        container = findViewById(R.id.container);
        one = Scene.getSceneForLayout(container, R.layout.layout_scene_one, this);
        two = Scene.getSceneForLayout(container, R.layout.layout_scene_two, this);
        one.setExitAction(new Runnable() {
            @Override
            public void run() {
                Log.e("zhwilson", "scene of one exit!");
            }
        });
        findViewById(R.id.transition).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (oneShow) {
                    oneShow = false;
                    Transition transition = new AutoTransition();
                    transition.addListener(new Transition.TransitionListener() {
                        @Override
                        public void onTransitionStart(Transition transition) {
                            Log.e("zhwilson", "scene of one start transition!");
                        }

                        @Override
                        public void onTransitionEnd(Transition transition) {

                        }

                        @Override
                        public void onTransitionCancel(Transition transition) {

                        }

                        @Override
                        public void onTransitionPause(Transition transition) {

                        }

                        @Override
                        public void onTransitionResume(Transition transition) {

                        }
                    });
                    TransitionManager.go(two, transition);
                } else {
                    oneShow = true;
                    TransitionManager.go(one, new AutoTransition());
                }
            }
        });
    }
}
