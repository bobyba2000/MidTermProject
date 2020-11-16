package com.example.midtermproject;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.Calendar;

public class MyStatisticViewAdapter extends RecyclerView.Adapter<MyStatisticViewAdapter.MyViewHolder> {

    private ArrayList<ArrayList<Float>> arrayListDistance;
    private ArrayList<ArrayList<String>> arrayListShortDay;
    private ArrayList<ArrayList<Calendar>> arrayListLongDay;

    private ArrayList<Float> listDistance;
    private ArrayList<String> listShortDay;
    private ArrayList<Calendar> listLongDay;
    private float mode = 7f;

    private Context context;

    public MyStatisticViewAdapter(Context context, ArrayList<ArrayList<Float>> arrayListDistance, ArrayList<ArrayList<String>> arrayListShortDay, ArrayList<ArrayList<Calendar>> arrayListLongDay, float mode) {
        this.arrayListDistance = arrayListDistance;
        this.arrayListShortDay = arrayListShortDay;
        this.arrayListLongDay = arrayListLongDay;
        this.mode = mode;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_statistic_view, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        listDistance = arrayListDistance.get(position);
        listLongDay = arrayListLongDay.get(position);
        listShortDay = arrayListShortDay.get(position);

        createBarChart(holder.barChart);
        initListView(holder.listView);
    }

    @Override
    public int getItemCount() {
        return arrayListLongDay.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        BarChart barChart;
        ListView listView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            barChart = (BarChart) itemView.findViewById(R.id.barChartStatistic);
            listView = (ListView) itemView.findViewById(R.id.listViewRunDetailForStatistic);
        }
    }

    private void initListView(ListView listView) {
        ArrayList<MyRunView> items = new ArrayList<>();
        for (int i = 0; i < listLongDay.size(); i++) {
            items.add(new MyRunView(listLongDay.get(i), listDistance.get(i)));
        }
        MyRunViewAdapter adapter = new MyRunViewAdapter(context, items);
        listView.setAdapter(adapter);
    }


    private void createBarChart(BarChart barChart) {
        setContextForBarChart(barChart);
        barChart.getDescription().setText("");
        barChart.animateY(2000);

        customizeXAxis(barChart);

        barChart.setScaleEnabled(false);
        barChart.setScaleMinima(listShortDay.size() / mode, 0.75f);
    }

    private void customizeXAxis(BarChart barChart) {
        XAxis x = barChart.getXAxis();
        x.setDrawGridLines(false);
        x.setPosition(XAxis.XAxisPosition.BOTTOM);
        x.setValueFormatter(new IndexAxisValueFormatter(listShortDay));
        x.setLabelCount(listShortDay.size());
        x.setTextSize(20);
    }

    private void setContextForBarChart(BarChart barChart) {
        ArrayList<BarEntry> distances = new ArrayList<>();
        for (int i = 0; i < listDistance.size(); i++)
            distances.add(new BarEntry(i, listDistance.get(i)));

        BarDataSet barDataSet = new BarDataSet(distances, "Quãng đường chạy");
        barDataSet.setColors(ColorTemplate.rgb("#039dfc"));
        barDataSet.setValueTextColor(Color.BLACK);
        barDataSet.setValueTextSize(16f);

        BarData barData = new BarData(barDataSet);
        barChart.setFitBars(true);
        barChart.setData(barData);
    }
}
