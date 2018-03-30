package com.example.ghislain.project_api;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Created by GHISLAIN on 17/03/2018.
 */

public class HomeActivity extends Activity {

    public final static int CHOOSE_BUTTON_AJOUTAPI = 0;
    public final static String BUTTONS = "com.example.ghislain.project_api.Boutons";
    private Button mAPIWOW, mAPILOL = null;
    private Button mAjoutAPI = null;
    private ListView mListViewAPI;

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

        mAjoutAPI = (Button) findViewById(R.id.btAjouAPI);

        mAjoutAPI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, AjoutAPIActivity.class);
                // On associe l'identifiant à notre intent
                startActivityForResult(intent, CHOOSE_BUTTON_AJOUTAPI);
            }
        });


        mListViewAPI = (ListView) findViewById(R.id.lvAPI);
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // On vérifie tout d'abord à quel intent on fait référence ici à l'aide de notre identifiant
        if (requestCode == CHOOSE_BUTTON_AJOUTAPI) {
            // On vérifie aussi que l'opération s'est bien déroulée
            if (resultCode == RESULT_OK) {
                // On affiche le bouton qui a été choisi
                Toast.makeText(this, "L'API vient d'être ajoutée à vos favoris.", Toast.LENGTH_SHORT).show();
            }
        }
    }

















}
