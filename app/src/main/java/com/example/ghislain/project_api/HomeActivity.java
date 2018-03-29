package com.example.ghislain.project_api;

import android.app.ListActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.ghislain.project_api.ui.ListeAPI;
import com.example.ghislain.project_api.ui.ListeAPIAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by GHISLAIN on 17/03/2018.
 */

public class HomeActivity extends ListActivity {

    private Button mAPIWOW, mAPILOL = null;
    private ListView mListViewAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        /*mAPIWOW = (Button) findViewById(R.id.btApiWow);
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
*/
        mListViewAPI = (ListView) findViewById(R.id.lvAPI);
        //afficherListeNoms();
        afficherListeAPI();
    }



    private List<ListeAPI> genererListe(){
        List<ListeAPI> liste = new ArrayList<ListeAPI>();
        liste.add(new ListeAPI(Color.BLACK, "World of Warcraft", "Blizzard"));
        liste.add(new ListeAPI(Color.BLUE, "Starcraft 2", "Blizzard"));
        liste.add(new ListeAPI(Color.GREEN, "Diablo 3 ", "Blizzard"));
        liste.add(new ListeAPI(Color.GRAY, "Battle.net", "Blizzard"));
        liste.add(new ListeAPI(Color.RED, "League of Legends", "Riot"));
        return liste;
    }

    private void afficherListeAPI(){
        List<ListeAPI> liste = genererListe();

        ListeAPIAdapter adapter = new ListeAPIAdapter(HomeActivity.this, liste);
        mListViewAPI.setAdapter(adapter);
    }


    protected void onListItemClick(ListView l, View v, int position, long id) {
        Toast.makeText(this, "Position : " + position, Toast.LENGTH_LONG).show();
    }


}
