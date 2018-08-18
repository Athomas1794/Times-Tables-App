package com.example.atom.timetables;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SeekBar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //Function to update to array ListView
    public void updateNumListView(int seekbarNum, ArrayList<String> listToUpdate){

        int numToAdd = 0;
        int listLength = listToUpdate.size();

        for(int i = 0; i<listLength; i++){
            numToAdd = (seekbarNum*(i+1));
            Log.i("Debug numbers", "Attempting to set num");
            listToUpdate.set(i, Integer.toString(numToAdd) );
            Log.i("ArrayList number", listToUpdate.get(i));
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Get seekbar and list view
        SeekBar numSeekBar = (SeekBar) findViewById(R.id.numSeekBar);
        final ListView numListView = (ListView) findViewById(R.id.numListView);

        int max = 18; //Max multiplier
        numSeekBar.setMax(max); //Set max seekbar length
        numSeekBar.setProgress(0); //Set initial at zero, will add 2 for times table

        //Create array to hold times table
        final ArrayList<String> numArrayList = new ArrayList<String>(max);


        //Add numbers to array list
        //Note: Must add numbers before using "Set" function or else crash
        int currentNum = 2;
        int numToAdd = 0;
        for(int i = 0; i<max-1; i++){
            numToAdd = (currentNum*(i+1));
            Log.i("Debug numbers", "Attempting to set num");
            numArrayList.add(Integer.toString(numToAdd) );
            Log.i("ArrayList number", numArrayList.get(i));
        }

        //Display ListView
        final ArrayAdapter<String> numArrayAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, numArrayList);
        numListView.setAdapter(numArrayAdapter);


        //Track changes in seekbar and update listView
        numSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                updateNumListView(i+2, numArrayList);

                numArrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });






    }
}
