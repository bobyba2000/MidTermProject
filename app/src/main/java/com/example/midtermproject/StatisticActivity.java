package com.example.midtermproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;

public class StatisticActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private ArrayList<ArrayList<Float>> arrayListDistance = new ArrayList<>();
    private ArrayList<ArrayList<String>> arrayListShortDay = new ArrayList<>();
    private ArrayList<ArrayList<Calendar>> arrayListLongDay = new ArrayList<>();

    SnapHelper snapHelper = new PagerSnapHelper();

    float WEEK_MODE = 7f;
    float MONTH_MODE = 4f;
    float YEAR_MODE = 12f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistic);

        Intent intent = getIntent();

        initDataForRecyclerView();
        //initSpinner();
        initRecyclerViewForMePlease();
    }

//    private void initSpinner() {
//        Spinner spinner = (Spinner)findViewById(R.id.dropdownList);
//        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.spinner_item, android.R.layout.simple_spinner_dropdown_item);
//        spinner.setAdapter(adapter);
//        spinner.setOnItemClickListener(this);
//    }

    private void initDataForRecyclerView() {
        for (int i = 0; i < 7; i++) {
            ArrayList<Float> listDistance = new ArrayList<>();
            ArrayList<String> listShortDay = new ArrayList<>();
            ArrayList<Calendar> listLongDay = new ArrayList<>();

            for (int j = 0; j < 7; j++) {
                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd");
                calendar.add(Calendar.DAY_OF_YEAR, i * 7 + j);
                listShortDay.add(simpleDateFormat.format(calendar.getTime()));
                listLongDay.add(calendar);
                listDistance.add((float) (new Random().nextInt(10) + 5.0));
            }

            arrayListDistance.add(listDistance);
            arrayListLongDay.add(listLongDay);
            arrayListShortDay.add(listShortDay);
        }
    }

    private void initRecyclerViewForMePlease() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false );
        RecyclerView recyclerView = findViewById(R.id.recyclerViewDetail);

        recyclerView.setLayoutManager(layoutManager);

        MyStatisticViewAdapter myStatisticViewAdapter = new MyStatisticViewAdapter(this, arrayListDistance, arrayListShortDay, arrayListLongDay, WEEK_MODE);
        recyclerView.setAdapter(myStatisticViewAdapter);
        snapHelper.attachToRecyclerView(recyclerView);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}