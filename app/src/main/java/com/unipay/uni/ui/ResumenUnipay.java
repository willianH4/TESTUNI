package com.unipay.uni.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.unipay.uni.R;

public class ResumenUnipay extends AppCompatActivity {

    private EditText etEmail;
    private Button btnAceptar;

    //instancia de la clase modal, contiene todos los metodos de llamado a las ventanas
    // tipo modals de la app
    ModalToastCustom modal = new ModalToastCustom();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resumen_unipay);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        etEmail = findViewById(R.id.etEmail);
        btnAceptar = findViewById(R.id.btnAceptar);

        btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                modal.modalConfirmacionInicio(ResumenUnipay.this);
            }
        });
    }
}