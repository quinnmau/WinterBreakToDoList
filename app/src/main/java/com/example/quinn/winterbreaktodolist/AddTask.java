package com.example.quinn.winterbreaktodolist;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

/**
 * Created by quinn on 12/23/2015.
 */
public class AddTask extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_task);
    }

    public void goBack(View view) {
        EditText currTask = (EditText) findViewById(R.id.inputTask);
        String theTask = currTask.getText().toString();
        EditText currDate = (EditText) findViewById(R.id.inputDate);
        String theDate = currDate.getText().toString();
        EditText currPriority = (EditText) findViewById(R.id.inputPriority);
        String thePriority = currPriority.getText().toString();
        EditText currDetails = (EditText) findViewById(R.id.inputDetails);
        String theDetails = currDetails.getText().toString();

        Intent goingBack = new Intent();
        goingBack.putExtra("task", theTask);
        goingBack.putExtra("date", theDate);
        goingBack.putExtra("priority", thePriority);
        goingBack.putExtra("details", theDetails);
        setResult(RESULT_OK, goingBack);
        finish();
    }

    public void cancel(View view) {
        Intent goingBack = new Intent();
        setResult(MainActivity.RESULT_CANCELED, goingBack);
        finish();
    }
}
