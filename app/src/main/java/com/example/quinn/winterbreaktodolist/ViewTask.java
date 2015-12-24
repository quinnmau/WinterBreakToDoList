package com.example.quinn.winterbreaktodolist;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

/**
 * Created by quinn on 12/22/2015.
 */
public class ViewTask extends Activity {
    private SQLiteDatabase retrievalDB = MainActivity.toDoListDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_task);
        Intent passedString = getIntent();
        String taskFiller = passedString.getExtras().getString("clickedTask");

        Cursor cursor = retrievalDB.rawQuery("SELECT * FROM list2 WHERE task = '" + taskFiller + "'", null);
        cursor.moveToFirst();
        String priority = cursor.getString(cursor.getColumnIndex("priority"));
        String dayDue = cursor.getString(cursor.getColumnIndex("due"));
        String details = cursor.getString(cursor.getColumnIndex("details"));

        TextView task = (TextView) findViewById(R.id.task_text_view);
        task.append(taskFiller);
        TextView priorityView = (TextView) findViewById(R.id.priority_text_view);
        priorityView.append(priority);
        TextView dueView = (TextView) findViewById(R.id.dueby_text_view);
        dueView.append(dayDue);
        TextView detailsView = (TextView) findViewById(R.id.details_text_view);
        detailsView.append(details);
        cursor.close();
    }


    public void goBack(View view) {
        finish();
    }
}
