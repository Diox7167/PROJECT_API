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

public class APIWOWRequestGuildeActivity extends Activity {
    private Button mConnexion = null;
    private TextInputLayout til_realm, til_guilde;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_requestguilde);

        til_realm = (TextInputLayout) findViewById(R.id.til_realm);
        til_guilde = (TextInputLayout) findViewById(R.id.til_guilde);

        mConnexion = (Button) findViewById(R.id.btValider);
        mConnexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String realm = til_realm.getEditText().getText().toString().trim();
                final String guilde = til_guilde.getEditText().getText().toString().trim();

                Intent intent = new Intent(APIWOWRequestGuildeActivity.this, APIWOWRespGuildeActivity.class);
                intent.putExtra("REALM", realm);
                intent.putExtra("GUILDE", guilde);
                startActivity(intent);
                finish();
            }
        });
    }
}
