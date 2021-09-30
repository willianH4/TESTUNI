package com.unipay.uni.ui;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.unipay.uni.R;

public class OtorgarPermisos extends AppCompatActivity {

    private Button btnAceptar;
    int REQUEST_CODE = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otorgar_permisos);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

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
        }else {
            requestPermissions(new String[]{Manifest.permission.READ_CONTACTS}, REQUEST_CODE);
        }
    }
}