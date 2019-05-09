package com.zhwilson.layoutupdateanimation;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class SlideViewPagerActivity extends AppCompatActivity {
    private ViewPager viewPager;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slide_viewpager);
        viewPager = findViewById(R.id.viewpager);
        viewPager.setAdapter(new SlideViewPagerAdapter(getSupportFragmentManager()));
        viewPager.setPageTransformer(true, new CustomPageTransformer());
    }


    public static class SlideFragment extends Fragment {
        private int imgResId;
        public static SlideFragment getInstance(int imgResId) {
            SlideFragment fragment = new SlideFragment();
            Bundle bundle = new Bundle();
            bundle.putInt("res_id", imgResId);
            fragment.setArguments(bundle);
            return fragment;
        }
        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.layout_content_of_fragment, container, false);
            imgResId = getArguments().getInt("res_id", -1);
            if (imgResId != -1) {
                AppCompatImageView imageView = view.findViewById(R.id.img);
                imageView.setImageResource(imgResId);
            }
            return view;
        }
    }

    public static class SlideViewPagerAdapter extends FragmentStatePagerAdapter {
        private int[] imgResIds = {R.drawable.beauty3, R.drawable.beauty1, R.drawable.beauty6};

        public SlideViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            return SlideFragment.getInstance(imgResIds[i]);
        }

        @Override
        public int getCount() {
            return imgResIds.length;
        }
    }

    public static class CustomPageTransformer implements ViewPager.PageTransformer {
        private static final float MIN_SCALE = 0.85f;
        private static final float MIN_ALPHA = 0.5f;
        @Override
        public void transformPage(@NonNull View view, float position) {  // -00  <-  -1  <-  0  ->  1  ->  00
            //totally on the left of the screen or on the right of the screen
            if (position < -1 || position > 1) view.setAlpha(0);
            else {
                int viewWidth = view.getWidth();
                int viewHeight = view.getHeight();

                float scale = Math.max(MIN_SCALE, 1 - Math.abs(position));
//                float horMargin = viewWidth*(1 - scale) / 2;
//                float verMargin = viewHeight*(1 - scale) / 2;
//                if (position < 0) {//left of the screen
//                    view.setTranslationX();
//                }
                view.setScaleX(scale);
                view.setScaleY(scale);
            }
        }
    }
}
