package com.example.midtermproject;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startNewActivity();
    }

    private void startNewActivity() {
        Intent intent = new Intent(this, StatisticActivity.class);

        startActivity(intent);
    }
}