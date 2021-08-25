package com.example.demo.view;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.demo.MasterDetailFragment;
import com.example.demo.R;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Fragment newFragment = new MasterDetailFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fl_main, newFragment, "DetailFragment")
                .commit();

    }
}