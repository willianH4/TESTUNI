package com.unipay.uni;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.unipay.uni.ui.permisos.OtorgarPermisos;

public class MainActivity extends Activity {

    private Button btnComenzar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnComenzar = findViewById(R.id.btnComenzar);

        btnComenzar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, OtorgarPermisos.class);
                startActivity(intent);
            }
        });
    }
}