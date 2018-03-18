package com.example.ghislain.project_api;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.example.ghislain.project_api.bdd.MasterBDD;
import com.example.ghislain.project_api.myrequest.MyRequest;

import java.util.Map;

/**
 * Created by GHISLAIN on 11/03/2018.
 */

public class RegisterActivity extends AppCompatActivity {
    private TextInputLayout til_pseudo, til_email, til_password, til_password2;
    private RequestQueue queue;
    private MyRequest request;

    private Button mValider = null;
    private ProgressBar pb_loader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        til_pseudo = (TextInputLayout) findViewById(R.id.til_pseudo);
        til_email = (TextInputLayout) findViewById(R.id.til_email);
        til_password = (TextInputLayout) findViewById(R.id.til_password);
        til_password2 = (TextInputLayout) findViewById(R.id.til_password2);

        pb_loader = (ProgressBar) findViewById(R.id.pbRegister);

        queue = VolleySingleton.getInstance(this).getRequestQueue();
        request = new MyRequest(this, queue);


        mValider = (Button) findViewById(R.id.btValider);
        mValider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                pb_loader.setVisibility(View.VISIBLE);

                //MasterBDD bdd = new MasterBDD();
                //boolean ok = bdd.userRegister("BERNARD", "Ghislain");

                //Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                //startActivity(intent);

                String pseudo = til_pseudo.getEditText().getText().toString().trim();
                String email = til_email.getEditText().getText().toString().trim();
                String password = til_password.getEditText().getText().toString().trim();
                String password2 = til_password2.getEditText().getText().toString().trim();

                if (pseudo.length() > 0 && email.length() > 0){
                    request.register(pseudo, email, password, password2, new MyRequest.RegisterCallback() {
                        @Override
                        public void onSuccess(String message) {
                            pb_loader.setVisibility(View.GONE);

                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            intent.putExtra("REGISTER", message);
                            startActivity(intent);
                            finish();

                        }

                        @Override
                        public void inputError(Map<String, String> errors) {
                            pb_loader.setVisibility(View.GONE);
                            if (errors.get("pseudo") != null){
                                til_pseudo.setError(errors.get("pseudo"));
                            }else{
                                til_pseudo.setErrorEnabled(false);
                            }
                            if (errors.get("pseudos") != null){
                                til_pseudo.setError(errors.get("pseudos"));
                            }else{
                                til_pseudo.setErrorEnabled(false);
                            }
                            if (errors.get("email") != null){
                                til_email.setError(errors.get("email"));
                            }else{
                                til_email.setErrorEnabled(false);
                            }
                            if (errors.get("password") != null){
                                til_password.setError(errors.get("password"));
                            }else{
                                til_password.setErrorEnabled(false);
                            }
                        }

                        @Override
                        public void onError(String messages) {
                            pb_loader.setVisibility(View.GONE);
                            Toast.makeText(getApplicationContext(), messages, Toast.LENGTH_SHORT).show();

                        }
                    });
                }else {
                    Toast.makeText(getApplicationContext(), "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}
