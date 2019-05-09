package com.zhwilson.layoutupdateanimation;

import android.animation.LayoutTransition;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageView;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

/**
 * animateLayoutChanges:只对view的显示与消失产生动画（若view的size为wrap，从有内容到没内容，不会产生动画，因为view只是没有内容了，size=0，但并没有消失）
 */

public class AutoLayoutUpdateAnimationActivity extends AppCompatActivity implements View.OnClickListener{
    LinearLayout container;

    AppCompatImageView imgOne;
    AppCompatImageView imgTwo;
    AppCompatImageView imgThree;
    AppCompatImageView imgFour;
    AppCompatImageView imgFive;
    AppCompatImageView imgSix;

    Button imgOneOP;
    Button imgTwoOP;
    Button imgThreeOP;
    Button imgFourOP;
    Button imgFiveOP;
    Button imgSixOP;
    Button containerOP;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto_layout_update);

        container = findViewById(R.id.container);
        container.setLayoutTransition(new LayoutTransition());

        imgOne = findViewById(R.id.img_one);
        imgTwo = findViewById(R.id.img_two);
        imgThree = findViewById(R.id.img_three);
        imgFour = findViewById(R.id.img_four);
        imgFive = findViewById(R.id.img_five);
        imgSix = findViewById(R.id.img_six);

        imgOneOP = findViewById(R.id.img_one_op);
        imgTwoOP = findViewById(R.id.img_two_op);
        imgThreeOP = findViewById(R.id.img_three_op);
        imgFourOP = findViewById(R.id.img_four_op);
        imgFiveOP = findViewById(R.id.img_five_op);
        imgSixOP = findViewById(R.id.img_six_op);
        containerOP = findViewById(R.id.container_op);
        imgOneOP.setOnClickListener(this);
        imgTwoOP.setOnClickListener(this);
        imgThreeOP.setOnClickListener(this);
        imgFourOP.setOnClickListener(this);
        imgFiveOP.setOnClickListener(this);
        imgSixOP.setOnClickListener(this);
        containerOP.setOnClickListener(this);

        setBtnText(imgOne, imgOneOP);
        setBtnText(imgTwo, imgTwoOP);
        setBtnText(imgThree, imgThreeOP);
        setBtnText(imgFour, imgFourOP);
        setBtnText(imgFive, imgFiveOP);
        setBtnText(imgSix, imgSixOP);
        setBtnText(container, containerOP);
    }

    private void setBtnText(View imgView, Button btnView){
        btnView.setText(imgView.getVisibility() == View.VISIBLE ? "Hide" : "Show");
    }

    private void setViewVisibility(View imgView) {
        imgView.setVisibility(imgView.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE);
    }

    @Override
    public void onClick(View v) {
        int vid = v.getId();
        if (vid == R.id.img_one_op){
            setViewVisibility(imgOne);
            setBtnText(imgOne, imgOneOP);
            return;
        }
        if (vid == R.id.img_two_op){
            setViewVisibility(imgTwo);
            setBtnText(imgTwo, imgTwoOP);
            return;
        }
        if (vid == R.id.img_three_op){
            setViewVisibility(imgThree);
            setBtnText(imgThree, imgThreeOP);
            return;
        }
        if (vid == R.id.img_four_op){
            setViewVisibility(imgFour);
            setBtnText(imgFour, imgFourOP);
            return;
        }
        if (vid == R.id.img_five_op){
            setViewVisibility(imgFive);
            setBtnText(imgFive, imgFiveOP);
            return;
        }
        if (vid == R.id.img_six_op){
            setViewVisibility(imgSix);
            setBtnText(imgSix, imgSixOP);
            return;
        }
        if (vid == R.id.container_op) {
            setViewVisibility(container);
            setBtnText(container, containerOP);
        }
    }
}
