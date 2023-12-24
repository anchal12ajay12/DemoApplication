package com.AnchalAjay.demoapp.Activity;

import android.os.Bundle;

import com.AnchalAjay.demoapp.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.core.view.GravityCompat;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Handler;
import android.view.View;
import android.widget.Toast;

import com.AnchalAjay.demoapp.ui.main.SectionsPagerAdapter;

public class MainActivity extends AppCompatActivity {

    boolean doubleBackToExitPressedOnce = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);

        findViewById(R.id.iv_close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                   onBackPressed();
                }
            }
        );



    }

    @Override
    public void onBackPressed() {
        // if (doubleBackToExitPressedOnce) {
        //     finishAffinity();
        // }
        // this.doubleBackToExitPressedOnce = true;
        // Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();
        // new Handler().postDelayed(() -> doubleBackToExitPressedOnce = false, 2000);
    }
}
