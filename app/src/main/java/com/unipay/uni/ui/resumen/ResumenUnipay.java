package com.unipay.uni.ui.resumen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.unipay.uni.MainActivity;
import com.unipay.uni.R;
import com.unipay.uni.ui.transferencias.Transfiere;
import com.unipay.uni.ui.transferencias.DestinatarioFragment;
import com.unipay.uni.ui.usuario.DatosUsuario;
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

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_back_24));
        toolbar.setTitleTextColor(getResources().getColor(R.color.azulito));
        toolbar.setTitleMargin(0, 0, 0, 0);
        setSupportActionBar(toolbar);

        //y esto para pantalla completa (oculta incluso la barra de estado)
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);


        //y esto para pantalla completa (oculta incluso la barra de estado)
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                regresar();
            }
        });

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
                Intent intent = new Intent(ResumenUnipay.this, DatosUsuario.class);
                startActivity(intent);
            }
        });

        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.show();
    }

    private void regresar() {
        Intent intent = new Intent(ResumenUnipay.this, MainActivity.class);
        startActivity(intent);
    }

}