package com.example.ghislain.project_api;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by GHISLAIN on 27/03/2018.
 */

public class APILOLActivity extends Activity {
    private Button mRequestSummoner = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apilol);

        mRequestSummoner = (Button) findViewById(R.id.btSummoner);
        mRequestSummoner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(APILOLActivity.this, APILOLRequestSummonerActivity.class);
                startActivity(intent);
            }
        });


    }
}
