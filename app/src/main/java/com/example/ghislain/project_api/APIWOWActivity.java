package com.example.ghislain.project_api;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by GHISLAIN on 27/03/2018.
 */

public class APIWOWActivity extends Activity {
    private Button mRequestGuilde, mRequestBossList = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apiwow);

        mRequestGuilde = (Button) findViewById(R.id.btGuilde);
        mRequestGuilde.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), APIWOWRequestGuildeActivity.class);
                startActivity(intent);
                finish();
            }
        });

        mRequestBossList = (Button) findViewById(R.id.btBossList);
        mRequestBossList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), APIWOWRespBossList.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
