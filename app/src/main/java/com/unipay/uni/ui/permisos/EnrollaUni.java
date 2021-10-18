package com.unipay.uni.ui.permisos;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.unipay.uni.MainActivity;
import com.unipay.uni.R;
import com.unipay.uni.utilidades.ModalToastCustom;

public class EnrollaUni extends AppCompatActivity {

    AlertDialog.Builder dialogo;

    private Button btnContinuar;
    private ImageButton imgInfo;
    private CheckBox cbTerminos;
    private EditText etConfirmarTelefono;

    //instancia de la clase modal, contiene todos los metodos de llamado a las ventanas
    // tipo modals de la app
    ModalToastCustom modal = new ModalToastCustom();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enrolla_uni);

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


        //y esto para pantalla completa (oculta incluso la barra de estado)
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                regresar();
            }
        });

        btnContinuar = findViewById(R.id.btnContinuar);
        imgInfo = findViewById(R.id.imgInfo);
        cbTerminos = findViewById(R.id.cbTerminos);
        etConfirmarTelefono = findViewById(R.id.etConfirmarTelefono);

        imgInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(getApplicationContext(),"Test para imageButton",Toast.LENGTH_SHORT).show();
                modal.modalInformation(EnrollaUni.this);
            }
        });

        cbTerminos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validarCheckBox();
            }
        });
    }

    private void regresar() {
        Intent intent = new Intent(EnrollaUni.this, MainActivity.class);
        startActivity(intent);
    }

    private void validarTelefono() {
        if(etConfirmarTelefono.getText().toString().isEmpty()){
            Toast.makeText(getApplicationContext(),"Por favor ingresa tu numero de telefono",Toast.LENGTH_SHORT).show();
        }else if(etConfirmarTelefono.getText().toString().length() < 6) {
            Toast.makeText(getApplicationContext(), "numero de telefono no valido", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(getApplicationContext(), "Numero de telefono valido", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(EnrollaUni.this, OtorgarPermisos.class);
            startActivity(intent);
        }
    }

    private void validarCheckBox() {
        if (cbTerminos.isChecked() == true){
            btnContinuar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    validarTelefono();
                }
            });
        }else if(cbTerminos.isChecked() == false){
            btnContinuar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(getApplicationContext(),"Por favor acepta los terminos y " +
                            "condiciones",Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}