package com.example.ghislain.project_api;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.widget.Button;

/**
 * Created by GHISLAIN on 27/03/2018.
 */

public class APILOLRequestSummonerActivity extends Activity {
    private Button mRequeteSummoner = null;
    private TextInputLayout til_username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_requestsummoner);

        til_username = (TextInputLayout) findViewById(R.id.til_usernme);

        mRequeteSummoner = (Button) findViewById(R.id.btValider);
        mRequeteSummoner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String usernameSummoner = til_username.getEditText().getText().toString().trim();

                Intent intent = new Intent(getApplicationContext(), APILOLRespSummonerActivity.class);
                intent.putExtra("SUMMONER", usernameSummoner);
                startActivity(intent);
                finish();
            }
        });
    }
}
