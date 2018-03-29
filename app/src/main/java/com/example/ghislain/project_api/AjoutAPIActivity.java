package com.example.ghislain.project_api;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

/**
 * Created by GHISLAIN on 29/03/2018.
 */

public class AjoutAPIActivity extends ListActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String[] values = new String[] {
                "World of Warcraft",
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.row_listviewapi, values);
        setListAdapter(adapter);
    }
}
