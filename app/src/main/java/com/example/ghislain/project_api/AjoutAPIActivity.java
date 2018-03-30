package com.example.ghislain.project_api;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.ghislain.project_api.ui.CustomAPIAdapter;
import com.example.ghislain.project_api.ui.modele.ItemAPI;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by GHISLAIN on 29/03/2018.
 */

public class AjoutAPIActivity extends Activity {

    private CustomAPIAdapter adapter;
    private List<ItemAPI> apis;
    private ItemAPI selectedItem;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajoutapi);

        apis = new ArrayList<>();

        apis.add(new ItemAPI("World of Warcraft", "Blizzard", R.drawable.wow_icon));
        apis.add(new ItemAPI("Starcraft 2", "Blizzard", R.drawable.sc2_icon));
        apis.add(new ItemAPI("Diablo 3", "Blizzard", R.drawable.d3_icon));
        apis.add(new ItemAPI("Battle.net", "Blizzard", R.drawable.bnet_icon));

        apis.add(new ItemAPI("League of Legends", "Riot Games", R.drawable.lol_icon));

        adapter = new CustomAPIAdapter(this, apis);

        ListView listeAPI = ( ListView) findViewById(R.id.lvListeAPI);
        listeAPI.setAdapter(adapter);




    }

    public void setSelectedItem(ItemAPI selectedItem) {
        this.selectedItem = selectedItem;
    }

    public ItemAPI getSelectedItem() {
        return selectedItem;
    }
}
