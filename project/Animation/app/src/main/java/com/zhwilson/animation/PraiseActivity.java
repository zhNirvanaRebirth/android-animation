package com.zhwilson.animation;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.zhwilson.animation.view.PraiseView;

public class PraiseActivity extends AppCompatActivity {
    private PraiseView praiseView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_praise);
        praiseView = findViewById(R.id.praise_view);
        praiseView.setPraiseCount(759);
        praiseView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (praiseView.isPraised())
                    praiseView.unPraise();
                else praiseView.praise();
            }
        });
    }
}
