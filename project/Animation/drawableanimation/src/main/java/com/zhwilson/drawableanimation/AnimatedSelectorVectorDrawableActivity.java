package com.zhwilson.drawableanimation;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageView;
import android.view.View;

public class AnimatedSelectorVectorDrawableActivity extends AppCompatActivity {
    AppCompatImageView vectorImg;
    boolean selected = false;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animated_selector_vector_drawable);
        vectorImg = findViewById(R.id.vector_img);
        vectorImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selected = !selected;
                vectorImg.setSelected(selected);
            }
        });
    }
}
