package com.unipay.uni;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class EnrollaUni extends Activity {

    private Button btnContinuar;
    private ImageButton imgInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enrolla_uni);

        btnContinuar = findViewById(R.id.btnContinuar);
        imgInfo = findViewById(R.id.imgInfo);

        imgInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"Test para imageButton",Toast.LENGTH_SHORT).show();
            }
        });

        btnContinuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"Test para boton continuar",Toast.LENGTH_SHORT).show();
            }
        });

    }
}