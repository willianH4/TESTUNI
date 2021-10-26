package com.unipay.uni.ui.permisos;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import com.unipay.uni.MainActivity;
import com.unipay.uni.R;
import com.unipay.uni.ui.transferencias.Transfiere;

public class OtorgarPermisos extends AppCompatActivity {

    private Button btnAceptar;
    int REQUEST_CODE = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otorgar_permisos);

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

        btnAceptar = findViewById(R.id.btnAceptar);

        btnAceptar.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {
                verificarPermiso();
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void verificarPermiso() {
        int permisoContactos = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS);

        if (permisoContactos == PackageManager.PERMISSION_GRANTED){
            Toast.makeText(this, "Permiso para acceder a contactos concedido!", Toast.LENGTH_SHORT).show();
            verTransacciones();
        }else {
            requestPermissions(new String[]{Manifest.permission.READ_CONTACTS}, REQUEST_CODE);
        }
    }

    private void verTransacciones(){
        Intent intent = new Intent(OtorgarPermisos.this, Transfiere.class);
        startActivity(intent);
    }

    private void regresar() {
        Intent intent = new Intent(OtorgarPermisos.this, MainActivity.class);
        startActivity(intent);
    }
}