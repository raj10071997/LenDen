package com.game.dhanraj.lenden;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import Tabs.SlidingTabLayout;

public class MainActivity extends AppCompatActivity {

    private ViewPager mviewpager;
    private SlidingTabLayout mtabs;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mviewpager = (ViewPager)findViewById(R.id.viewpager);
        mtabs = (SlidingTabLayout)findViewById(R.id.tabs);
        mtabs.setDistributeEvenly(true);

        mviewpager.setAdapter(new pagerAdapter(getSupportFragmentManager()));

        mtabs.setViewPager(mviewpager);

    }



    class pagerAdapter extends FragmentPagerAdapter{
        int icons [] = {R.mipmap.ic_launcher1,R.mipmap.ic_launcher};

        private final String titles[] = {"My Debts","Credits","Events"};


        public pagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public CharSequence getPageTitle(int position) {

            return titles[position];
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new MainFragment();
                case 1:
                    return new PerfectFragment();
                case 2:
                    return new Events();
            }
            return null;
        }

        @Override
        public int getCount() {
            return titles.length;
        }
    }

}
