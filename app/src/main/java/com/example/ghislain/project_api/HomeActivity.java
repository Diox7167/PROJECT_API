package com.example.ghislain.project_api;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by GHISLAIN on 17/03/2018.
 */

public class HomeActivity extends Activity{

    private Button mAPIWOW, mAPILOL = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mAPIWOW = (Button) findViewById(R.id.btApiWow);
        mAPIWOW.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), APIWOWActivity.class);
                startActivity(intent);
                finish();
            }
        });


        mAPILOL = (Button) findViewById(R.id.btApiLol);
        mAPILOL.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), APILOLActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
