package com.example.david.findberry;

import android.content.SharedPreferences;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    SectionsPagerAdapter mSectionsPagerAdapter;
    SharedPreferences prefs;//defino mi objeto
    SharedPreferences.Editor editor; //requiero un editor

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    ViewPager mViewPager;

    final int icons[] = new int[]{
            R.drawable.ic_home_black_24dp,
            R.drawable.ic_directions_run_black_24dp,
            R.drawable.ic_shopping_cart_black_24dp,
            R.drawable.ic_place_black_24dp,
            R.drawable.ic_settings_black_24dp
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        prefs = getSharedPreferences("preferences",MODE_PRIVATE);//nombre y modo
        editor = prefs.edit(); //asi enlazo mis preferencias  y tengo acceso a dicha informacion

        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);


        tabLayout.getTabAt(0).setIcon(icons[0]);
        tabLayout.getTabAt(1).setIcon(icons[1]);
        tabLayout.getTabAt(2).setIcon(icons[2]);
        tabLayout.getTabAt(3).setIcon(icons[3]);
        tabLayout.getTabAt(4).setIcon(icons[4]);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "No tienes nuevos mensajes", Snackbar.LENGTH_SHORT)
                        .setAction("Action", null).show();
            }
        });

    }


    private class SectionsPagerAdapter extends FragmentPagerAdapter {

        private SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch(position){
                case 0:
                    RequestFragment tab0 = new RequestFragment();
                    return tab0;
                case 1:
                    DeliveryFragment tab1 = new DeliveryFragment();
                    return tab1;
                case 2:
                    OrdersFragment tab2 = new OrdersFragment();
                    return tab2;
                case 3:
                    MapFragment tab3 = new MapFragment();
                    return tab3;
                case 4:
                    SettingsFragment tab4 = new SettingsFragment();
                    return tab4;
                default: return null;
            }
        }

        @Override
        public int getCount() {
            return 5;
        }

    }
}
