package com.zhwilson.animation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.zhwilson.animation.view.RotateClipView;
import com.zhwilson.drawableanimation.DrawableGraphicsAnimationActivity;
import com.zhwilson.layoutupdateanimation.LayoutUpdateActivity;
import com.zhwilson.moveviewanimation.MoveViewAnimationActivity;
import com.zhwilson.propertyanimation.PropertyAnimationActivity;
import com.zhwilson.revealandhide.RevealAndHideViewActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.property_animator).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, PropertyAnimationActivity.class));
            }
        });
        findViewById(R.id.drawable_graphics_animation).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, DrawableGraphicsAnimationActivity.class));
            }
        });
        findViewById(R.id.reveal_and_hide_view_animation).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, RevealAndHideViewActivity.class));
            }
        });
        findViewById(R.id.move_view_animation).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, MoveViewAnimationActivity.class));
            }
        });
        findViewById(R.id.layout_update_animation).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, LayoutUpdateActivity.class));
            }
        });
        findViewById(R.id.practice_clip_animation).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ClipAnimationActivity.class));
            }
        });
        findViewById(R.id.rotate_animation).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, RotateAnimationActivity.class));
            }
        });
        findViewById(R.id.praise_animation).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, PraiseActivity.class));
            }
        });
    }
}
