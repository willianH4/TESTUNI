package com.unipay.uni.ui.principal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.unipay.uni.MainActivity;
import com.unipay.uni.R;
import com.unipay.uni.ui.EnrollaUni;
import com.unipay.uni.ui.OtorgarPermisos;

public class CambiarAlias extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cambiar_alias);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_back_24));
        toolbar.setTitleTextColor(getResources().getColor(R.color.azulito));
        toolbar.setTitleMargin(0, 0, 0, 0);
//        toolbar.setSubtitle("Tarea CRUD SQLite");
//        toolbar.setSubtitleTextColor(getResources().getColor(R.color.mycolor));
//        toolbar.setTitle("Willian Hernandez");
        setSupportActionBar(toolbar);

        //y esto para pantalla completa (oculta incluso la barra de estado)
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                regresar();
            }
        });
    }

    private void regresar() {
        Intent intent = new Intent(CambiarAlias.this, MainActivity.class);
        startActivity(intent);
    }
}