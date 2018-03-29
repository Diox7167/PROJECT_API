package com.example.ghislain.project_api.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ghislain.project_api.R;

/**
 * Created by GHISLAIN on 29/03/2018.
 */

public class ListAdapter extends ArrayAdapter<String> {

    private Integer[] tab_images_pour_la_liste = {
            R.drawable.wow_icon };

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater)
                getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.row_listviewapi, parent, false);

        TextView titleAPI = (TextView) rowView.findViewById(R.id.tvTitleAPI);
        TextView fournisseurAPI = (TextView) rowView.findViewById(R.id.tvNameFournisseur);
        ImageView avatarAPI = (ImageView) rowView.findViewById(R.id.imgAvatar);

        titleAPI.setText(getItem(position));
        fournisseurAPI.setText(getItem(position));

        if(convertView == null )
            avatarAPI.setImageResource(tab_images_pour_la_liste[position]);
        else
            rowView = (View)convertView;

        return rowView;
    }

    public ListAdapter(Context context, String[] values) {
        super(context, R.layout.row_listviewapi, values);
    }

}
