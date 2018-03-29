package com.example.ghislain.project_api.ui;

/**
 * Created by GHISLAIN on 29/03/2018.
 */


import android.content.Context;
        import android.graphics.drawable.ColorDrawable;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ArrayAdapter;
        import android.widget.ImageView;
        import android.widget.TextView;

import com.example.ghislain.project_api.R;

import java.util.List;

public class ListeAPIAdapter extends ArrayAdapter<ListeAPI> {

    public ListeAPIAdapter(Context context, List<ListeAPI> API) {
        super(context, 0, API);
    }

    private Integer[] tab_images_pour_la_liste = {
            R.drawable.wow_icon
    };

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.row_listviewapi,parent, false);
        }

        TweetViewHolder viewHolder = (TweetViewHolder) convertView.getTag();
        if(viewHolder == null){
            viewHolder = new TweetViewHolder();
            viewHolder.nomAPI = (TextView) convertView.findViewById(R.id.tvTitleAPI);
            viewHolder.nomFournisseurAPI = (TextView) convertView.findViewById(R.id.tvNameFournisseur);
            viewHolder.avatarAPI = (ImageView) convertView.findViewById(R.id.imgAvatar);
            convertView.setTag(viewHolder);
        }

        //getItem(position) va récupérer l'item [position] de la List<Tweet> tweets
        ListeAPI api = getItem(position);
        viewHolder.nomAPI.setText(api.getPseudo());
        viewHolder.nomFournisseurAPI.setText(api.getText());
        viewHolder.avatarAPI.setImageDrawable(new ColorDrawable(api.getColor()));
        //viewHolder.avatarAPI.setImageResource(tab_images_pour_la_liste[position]);

        return convertView;
    }

    private class TweetViewHolder{
        public TextView nomAPI;
        public TextView nomFournisseurAPI;
        public ImageView avatarAPI;

    }
}