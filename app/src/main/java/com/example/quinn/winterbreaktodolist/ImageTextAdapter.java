package com.example.quinn.winterbreaktodolist;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

class ImageTextAdapter extends ArrayAdapter<String>  {
    private Context theContext;

    public ImageTextAdapter(Context context, List<String> tasks) {
        super(context, R.layout.row_layout_2, tasks);
        theContext = context;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater theInflater = LayoutInflater.from(getContext());
        View theView = theInflater.inflate(R.layout.row_layout_2,
                parent, false);
        String task = getItem(position);
        TextView theTextView = (TextView) theView.findViewById(R.id.aTask);
        theTextView.setText(task);
        theView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "you clicked the " + position + " index", Toast.LENGTH_SHORT).show();
                getViewTask(v, theContext);
            }
        });
        ImageButton theImageButton = (ImageButton) theView.findViewById(R.id.taskComplete);
        theImageButton.setImageResource(R.drawable.check);
        theImageButton.setTag(position);
        return theView;
    }

    public void getViewTask(View view, Context context) {
        Intent viewTask = new Intent(theContext, ViewTask.class);
        final int result = 1;
        viewTask.putExtra("clickedTask", "go to walmart");
        theContext.startActivity(viewTask);
    }

}