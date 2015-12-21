package com.example.quinn.winterbreaktodolist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

class ImageTextAdapter extends ArrayAdapter<String> {

    public ImageTextAdapter(Context context, List<String> tasks) {
        super(context, R.layout.row_layout_2, tasks);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater theInflater = LayoutInflater.from(getContext());
        View theView = theInflater.inflate(R.layout.row_layout_2,
                parent, false);
        String task = getItem(position);
        TextView theTextView = (TextView) theView.findViewById(R.id.aTask);
        theTextView.setText(task);
        ImageButton theImageButton = (ImageButton) theView.findViewById(R.id.taskComplete);
        theImageButton.setImageResource(R.drawable.check);
        return theView;
    }
}