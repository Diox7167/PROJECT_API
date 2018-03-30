package com.example.ghislain.project_api;

import android.content.Intent;
import android.os.Handler;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.example.ghislain.project_api.myrequest.MyRequest;

public class LoginActivity extends AppCompatActivity {

    private Button mConnexion = null;
    private Button mInscription = null;

    private TextInputLayout til_pseudo, til_password;
    private RequestQueue queue;
    private MyRequest request;
    private ProgressBar pb_loader;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Récupérer un Extra
        Intent intent = getIntent();
        if (intent.hasExtra("REGISTER")){
            Toast.makeText(this, intent.getStringExtra("REGISTER"), Toast.LENGTH_SHORT).show();
        }

        pb_loader = (ProgressBar) findViewById(R.id.pbLogin);

        queue = VolleySingleton.getInstance(this).getRequestQueue();
        request = new MyRequest(this, queue);
        handler = new Handler();

        til_pseudo = (TextInputLayout) findViewById(R.id.til_pseudo);
        til_password = (TextInputLayout) findViewById(R.id.til_password);


        mConnexion = (Button) findViewById(R.id.btConnexion);
        mConnexion.setOnClickListener(v -> {

            final String pseudo = til_pseudo.getEditText().getText().toString().trim();
            final String password = til_password.getEditText().getText().toString().trim();


            pb_loader.setVisibility(View.VISIBLE);

            if (pseudo.length() > 0 && password.length() > 0 ){
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        request.connection(pseudo, password, new MyRequest.LoginCallback() {
                            @Override
                            public void onSuccess(String id, String pseudo) {
                                pb_loader.setVisibility(View.GONE);
                                Intent intent1 = new Intent(getApplicationContext(), HomeActivity.class);
                                startActivity(intent1);
                                finish();
                            }

                            @Override
                            public void onError(String message) {
                                pb_loader.setVisibility(View.GONE);
                                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }, 1000);

            }else {
                Toast.makeText(getApplicationContext(), "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
            }


        });




        mInscription = (Button) findViewById(R.id.btInscription);
        mInscription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
}
