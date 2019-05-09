package com.zhwilson.revealandhide;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class CardFlipAnimationActivity extends AppCompatActivity {
    private static final String FRAGMENT_TAG_ONE = "ONE";
    private static final String FRAGMENT_TAG_TWO = "TWO";
    FragmentManager fragmentManager;
    boolean flag = true;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_flip);
        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().add(R.id.view_content, CardFragment.getInstance(R.drawable.img_one), FRAGMENT_TAG_ONE).commit();
        findViewById(R.id.switch_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag) { // one -> two
                    flag = false;
                    fragmentSwitch(FRAGMENT_TAG_TWO);
                } else {
                    flag = true;
                    fragmentSwitch(FRAGMENT_TAG_ONE);
                }
            }
        });
    }

    private void fragmentSwitch(String tag) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        Fragment fragment = fragmentManager.findFragmentByTag(tag);
        if (fragment == null) {
            fragment = CardFragment.getInstance(FRAGMENT_TAG_ONE.equals(tag) ? R.drawable.img_one : R.drawable.img_three);
        }
        transaction.setCustomAnimations(R.animator.card_flip_left_in, R.animator.card_flip_left_out,
                R.animator.card_flip_right_in, R.animator.card_flip_right_out)
                .replace(R.id.view_content, fragment).commit();
    }
}
