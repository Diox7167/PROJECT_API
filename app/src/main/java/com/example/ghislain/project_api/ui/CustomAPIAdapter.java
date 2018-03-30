package com.example.ghislain.project_api.ui;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ghislain.project_api.AjoutAPIActivity;
import com.example.ghislain.project_api.HomeActivity;
import com.example.ghislain.project_api.R;
import com.example.ghislain.project_api.ui.dialog.DialogValidate;
import com.example.ghislain.project_api.ui.modele.ItemAPI;

import java.util.List;

import static android.widget.Toast.LENGTH_SHORT;

/**
 * Created by GHISLAIN on 30/03/2018.
 */

public class CustomAPIAdapter extends BaseAdapter {

    private AjoutAPIActivity activity; //context
    private List<ItemAPI> items; //data source of the list adapter

    public CustomAPIAdapter(AjoutAPIActivity activity, List<ItemAPI> items) {
        //super(activity);
        this.activity = activity;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position); //returns list item at the specified position
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        // inflate the layout for each list row
        if (convertView == null) {
            convertView = LayoutInflater.from(activity).
                    inflate(R.layout.row_listviewapi, viewGroup, false);
        }

        // get current item to be displayed
        final ItemAPI currentItem = (ItemAPI) getItem(position);

        // get the TextView for item name and item description
        TextView textViewItemNameAPI = (TextView)
                convertView.findViewById(R.id.tvTitleAPI);
        TextView textViewItemFournisseurAPI = (TextView)
                convertView.findViewById(R.id.tvNameFournisseur);
        ImageView imageLogoAPI = (ImageView) convertView.findViewById(R.id.imgAvatar);

        //sets the text for item name and item description from the current item object
        textViewItemNameAPI.setText(currentItem.getNomApi());
        textViewItemFournisseurAPI.setText(currentItem.getFournisseurAPI());
        imageLogoAPI.setImageResource(currentItem.getLogoAPI());

        convertView.setOnClickListener(view -> {
        //TODO INSERT FAVORIS BDD
        //activity.startActivity(new Intent(activity, HomeActivity.class));

            activity.setSelectedItem(currentItem);

        new DialogValidate(activity, "Voulez vous ajouter cette API à vos favoris ?").show();


    });

        // returns the view for the current row
        return convertView;
    }
}
