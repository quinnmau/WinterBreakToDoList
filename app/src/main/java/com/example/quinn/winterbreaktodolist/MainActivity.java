package com.example.quinn.winterbreaktodolist;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.Image;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private List<String> taskList;
    private ArrayAdapter<String> listAdapter;
    private int clickedPosition;
    SQLiteDatabase toDoListDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        taskList = new ArrayList<String>();
        listAdapter = new ImageTextAdapter(this, taskList);
        ListView toDoList = (ListView) findViewById(R.id.toDoList);
        toDoList.setAdapter(listAdapter);
        createDB();
        Cursor cursor = toDoListDB.rawQuery("SELECT * FROM list", null);
        int taskColumn = cursor.getColumnIndex("task");
        cursor.moveToFirst();
        if(cursor != null && (cursor.getCount() > 0)) {
            do{
                String task = cursor.getString(taskColumn);
                taskList.add(task);
            }while(cursor.moveToNext());
        }
        final EditText input = (EditText) findViewById(R.id.addTaskText);
        input.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                input.setCursorVisible(true);
            }
        });
        input.setCursorVisible(false);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        input.setOnEditorActionListener(new EditText.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE){
                    input.setCursorVisible(false);
                    hideKeyboard(v);
                    return true;
                }
                return false;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //adds a task to the to do list and saves it to the database
    public void addTask(View view) {
        EditText newTask = (EditText) findViewById(R.id.addTaskText);
        String currentText = newTask.getText().toString();
        taskList.add(0, currentText);
        toDoListDB.execSQL("INSERT INTO list (task) VALUES ('" +
                currentText + "');");
        listAdapter.notifyDataSetChanged();
        EditText input = (EditText) findViewById(R.id.addTaskText);
        input.setText("");
        //hideKeyboard(input);
    }

    //removes a task from the to do list and saves change in database
    public void completeTask(View view) {
        int position = (Integer) view.getTag();
        String currentText = taskList.get(position);
        taskList.remove(position);
        toDoListDB.execSQL("DELETE FROM list WHERE task = '" +
                currentText + "';");
        listAdapter.notifyDataSetChanged();
        Toast.makeText(this, "Task Complete!", Toast.LENGTH_LONG).show();
    }

    //creates database to store all tasks that will be remembered next time
    //the application is opened
    public void createDB() {
        toDoListDB = openOrCreateDatabase("ToDoList", MODE_PRIVATE, null);
        toDoListDB.execSQL("CREATE TABLE IF NOT EXISTS list " +
                "(task VARCHAR);");
    }

    //method to make keyboard disappear upon button click
    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager =(InputMethodManager)getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}