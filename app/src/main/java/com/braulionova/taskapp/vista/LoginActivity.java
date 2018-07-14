package com.braulionova.taskapp.vista;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.braulionova.taskapp.R;

public class LoginActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button btnRegistrarse = (Button) findViewById(R.id.btnRegistrarse);

        //on click
        btnRegistrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //intent
                Intent registrarseIntent = new Intent(LoginActivity.this, RegistroUsuarioActivity.class);
                startActivity(registrarseIntent);

            }
        });
    }
}
