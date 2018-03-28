package com.example.ghislain.project_api;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.ghislain.project_api.api.MasterAPI;

/**
 * Created by GHISLAIN on 27/03/2018.
 */

public class APIWOWRespGuildeActivity extends Activity {
    String apiResult = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_respguilde);

        final TextView tvTitle = (TextView)findViewById(R.id.tvTitle);
        final TextView tvResponse = (TextView)findViewById(R.id.tvResponse);

        final String respRealm;
        final String respGuilde;


        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                respRealm= null;
                respGuilde= null;
            } else {
                respRealm= extras.getString("REALM");
                respGuilde= extras.getString("GUILDE");

                //tvRealm.setText(respRealm);
                //tvGuilde.setText(respGuilde);



                new Thread(new Runnable() {
                    public void run() {
                        MasterAPI ma = new MasterAPI();
                        if(ma.WOWRequestGuild(respRealm, respGuilde)) {
                            apiResult = ma.getLastResponseContent();

                        }
                        runOnUiThread(new Runnable() {
                            public void run() {
                                tvTitle.setText("Réponse : ");
                                tvResponse.setText(apiResult);
                                //Log.d("APP", apiResult);
                            }
                        });
                    }
                }).start();

            }
        } else {
            respRealm= (String) savedInstanceState.getSerializable("REALM");
            respGuilde= (String) savedInstanceState.getSerializable("GUILDE");
        }
    }
}
