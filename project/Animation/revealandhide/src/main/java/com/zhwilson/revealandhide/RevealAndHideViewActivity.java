package com.zhwilson.revealandhide;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class RevealAndHideViewActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reveal_and_hide_view);
        findViewById(R.id.crossfade).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RevealAndHideViewActivity.this, CrossfadeAnimationActivity.class));
            }
        });
        findViewById(R.id.cardflip).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RevealAndHideViewActivity.this, CardFlipAnimationActivity.class));
            }
        });
    }
}
