package com.example.midtermproject;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class MyRunViewAdapter extends ArrayAdapter<MyRunView> {
    public MyRunViewAdapter(Context context, ArrayList<MyRunView> items) {
        super(context, 0, items);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        convertView = createView();
        MyRunView item = getItem(position);
        bindData(item, convertView);

        return convertView;
    }

    private void bindData(MyRunView item, View convertView) {
        ItemViewManager.bindPlease(getContext(), item, convertView);
    }

    private View createView() {
        View res = ItemViewManager.createItemView(this.getContext());
        return res;
    }
}
