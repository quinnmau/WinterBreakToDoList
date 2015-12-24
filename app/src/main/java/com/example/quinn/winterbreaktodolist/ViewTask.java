package com.example.quinn.winterbreaktodolist;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

/**
 * Created by quinn on 12/22/2015.
 */
public class ViewTask extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_task);
        Intent passedString = getIntent();
        String taskFiller = passedString.getExtras().getString("clickedTask");
        TextView task = (TextView) findViewById(R.id.task_text_view);
        task.append(taskFiller);
        //append priority, due date etc.
    }


    public void goBack(View view) {
        finish();
    }
}
