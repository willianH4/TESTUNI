package com.unipay.uni.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.unipay.uni.R;
import com.unipay.uni.Transfiere;
import com.unipay.uni.utilidades.ModalToastCustom;

public class ResumenUnipay extends AppCompatActivity {

    private EditText etEmail;
    private Button btnAceptar;
    Dialog myDialog;

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
                modalConfirmacionInicio(ResumenUnipay.this);
            }
        });
    }

    public void modalConfirmacionInicio(final Context context) {
        myDialog = new Dialog(context);
        myDialog.setContentView(R.layout.custom_confirmacion);
        Button btnAccept = myDialog.findViewById(R.id.btnAccept);

        btnAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
                /** Lanza la actividad siguiente despues de clickear el boton aceptar*/
                Intent intent = new Intent(ResumenUnipay.this, Transfiere.class);
                startActivity(intent);
            }
        });

        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.show();
    }

}