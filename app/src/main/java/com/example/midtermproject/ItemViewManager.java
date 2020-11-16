package com.example.midtermproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import java.util.Calendar;

public class ItemViewManager {

    private static final String TAG = "ItemViewManager";
    public static View createItemView(Context context) {
        return LayoutInflater.from(context).inflate(R.layout.my_run_view, null);
    }


    public static void bindPlease(Context context, MyRunView item, View convertView) {
        TextView textView = (TextView) convertView.findViewById(R.id.textViewDay);
        String date = "Ngày " + String.valueOf(item.date.get(Calendar.DATE));
        String month = " Tháng " + String.valueOf(item.date.get(Calendar.MONTH));

        textView.setText(date + month);

        textView = (TextView) convertView.findViewById(R.id.textViewDistance);
        textView.setText(String.valueOf(item.distance) + " km");
    }
}
