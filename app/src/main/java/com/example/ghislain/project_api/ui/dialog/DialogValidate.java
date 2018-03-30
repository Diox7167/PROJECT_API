package com.example.ghislain.project_api.ui.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ghislain.project_api.AjoutAPIActivity;
import com.example.ghislain.project_api.HomeActivity;
import com.example.ghislain.project_api.R;
import com.example.ghislain.project_api.ui.CustomAPIAdapter;
import com.example.ghislain.project_api.ui.modele.ItemAPI;

import java.util.ArrayList;

import static android.widget.Toast.LENGTH_SHORT;

/**
 * Created by GHISLAIN on 30/03/2018.
 */

public class DialogValidate extends Dialog {

    private Button mAnnuler, mConfirmer;
    private TextView text;
    private AjoutAPIActivity activity;

    private String content;

    public DialogValidate(AjoutAPIActivity activity, String content) {
        super(activity, R.style.Dialog);
        this.activity = activity;
        this.content = content;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_validate);

        TextView text = (TextView) findViewById(R.id.tvTitleDialog);
        Button mConfirmer = (Button) findViewById(R.id.btConfirmer);
        Button mAnnuler = (Button) findViewById(R.id.btAnnuler);



        text.setText(content);

        mConfirmer.setOnClickListener(view -> {
            dismiss();
            activity.startActivity(new Intent(activity, HomeActivity.class));

            activity.finish();

            Toast.makeText(activity, "L'API " + activity.getSelectedItem().getNomApi() + " vient d'être ajoutée à vos favoris.", LENGTH_SHORT).show();


        });

        mAnnuler.setOnClickListener(view -> {
            dismiss();
        });





    }
}
