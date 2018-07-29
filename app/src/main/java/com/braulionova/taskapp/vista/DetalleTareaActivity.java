package com.braulionova.taskapp.vista;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.braulionova.taskapp.R;

public class DetalleTareaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_tarea);

        try{
            Toast toast = Toast.makeText(getApplicationContext(), "Detalle Tarea.", Toast.LENGTH_LONG);
            toast.show();
        }
        catch (Exception ex)
        {
            Toast toast = Toast.makeText(getApplicationContext(), "Error: " + ex.toString(), Toast.LENGTH_LONG);
            toast.show();
        }
    }
}
