package com.example.jerry.notetaker;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.EnumMap;
import java.util.HashSet;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    static ArrayList<String> notes = new ArrayList<>();
    static ArrayList<Emotion> emotionsArrayList = new ArrayList<>();
    static ArrayAdapter arrayAdapter;
    Button addEmotionButton;
    Button emotionSummaryButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*saveButton = (Button) findViewById(R.id.addEmotionButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData();
            }
        });
*/
        // One time check
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        if(!prefs.getBoolean("firstTime", false)) {
            Joy joy = new Joy();
            joy.setDate(new Date(System.currentTimeMillis()));
            Fear fear = new Fear();
            fear.setDate(new Date(System.currentTimeMillis()));
            Fear feara = new Fear();
            feara.setDate(new Date(System.currentTimeMillis()));
            Fear fearb = new Fear();
            fearb.setDate(new Date(System.currentTimeMillis()));

            feara.setComment("Fear A");
            fearb.setComment("Hello Guys, fear B");

            emotionsArrayList.add(fear);
            emotionsArrayList.add(joy);
            emotionsArrayList.add(feara);
            emotionsArrayList.add(fearb);
            SharedPreferences.Editor editor = prefs.edit();
            editor.putBoolean("firstTime", true);
            editor.commit();
        }


        // Buttons for adding in a new emotion and getting a summary report
        addEmotionButton = findViewById(R.id.addEmotionButton);
        addEmotionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAddActivity();
            }
        });

        emotionSummaryButton = findViewById(R.id.emotionSummaryButton);


        ListView listview = findViewById(R.id.listView);
        /*notes.add("Example Note");
        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, notes);
        listview.setAdapter(arrayAdapter);
*/

        // The custom layout for the adapter, shows the emotion as well as the time
        EmotionAdapter adapter = new EmotionAdapter(this, R.layout.adapter_view_layout, emotionsArrayList);
        listview.setAdapter(adapter);

        // When you click on an item in the list
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), NoteActivity.class);
                intent.putExtra("emotionId", position);
                startActivity(intent);
            }
        });

        // Return true so that we can long click
/*        listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {

                new AlertDialog.Builder(MainActivity.this).setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle("Confirm")
                        .setMessage("Would you like to delete this emotion?")

                        // If they choose to delete the entry
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                notes.remove(position);
                                arrayAdapter.notifyDataSetChanged();

                                SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("com.example.jerry.notetaker", Context.MODE_PRIVATE);
                                HashSet<String> set = new HashSet<>(MainActivity.notes);
                                sharedPreferences.edit().putStringSet("notes", set).apply();
                            }
                        })
                        // If they choose not to delete, set listener to null
                        .setNegativeButton("No", null)
                        .show();

                return true;
            }
        });*/

    }

    public void openAddActivity(){
        Intent intent = new Intent(this, NoteActivity.class);
        startActivity(intent);
    }

    // For the menu
/*    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_note_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }*/

/*    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);

        if (item.getItemId() == R.id.add_note){
            Intent intent = new Intent(getApplicationContext(), NoteActivity.class);
            startActivity(intent);

            return true;
        }
        return false;
    }*/
}
