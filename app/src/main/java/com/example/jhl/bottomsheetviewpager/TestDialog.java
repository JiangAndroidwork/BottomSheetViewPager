package com.example.jhl.bottomsheetviewpager;

import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.example.jhl.bottomsheetviewpager.viewpagerofbottomsheet.ViewPagerBottomSheetDialogFragment;

import java.util.ArrayList;
import java.util.List;

public class TestDialog extends ViewPagerBottomSheetDialogFragment {

    private TabLayout tabLayout;
    private ViewPager viewpager;
    private List<String> titles = new ArrayList<>();
    private List<Fragment> fragments = new ArrayList<>();

    @Override
    protected int setLayoutId() {
        return R.layout.dialog_test;
    }

    @Override
    protected void initView() {
        tabLayout = mRootView.findViewById(R.id.tablayout);
        viewpager = mRootView.findViewById(R.id.viewpager);
        for (int i = 0; i < 4; i++) {
            titles.add("标题" + i);
            TestFragment testFragment = TestFragment.newInstance();
            fragments.add(testFragment);
        }
        // 注意要加上这句 让viewpager全部预加载
        viewpager.setOffscreenPageLimit(titles.size());
        viewpager.setAdapter(new ViewPagerAdapter(getChildFragmentManager()));

        tabLayout.setupWithViewPager(viewpager);
    }

    @Override
    protected void setListener() {
        super.setListener();
        viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                onPageChange(viewpager); // 不要忘了这句 用于更新viewpager的 子view
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

    private class ViewPagerAdapter extends FragmentStatePagerAdapter {

        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            return fragments.get(i);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return titles.get(position);
        }
    }
}
