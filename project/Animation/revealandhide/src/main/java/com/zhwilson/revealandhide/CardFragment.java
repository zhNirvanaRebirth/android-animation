package com.zhwilson.revealandhide;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatImageView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class CardFragment extends Fragment {
    private Context context;
    private int imgId;

    public static CardFragment getInstance(int id){
        CardFragment fragment = new CardFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("id", id);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        AppCompatImageView imageView = new AppCompatImageView(context);
        imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        imgId = getArguments().getInt("id", R.drawable.img_two);
        imageView.setImageResource(imgId);
        return imageView;
    }
}
