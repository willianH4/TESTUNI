package com.unipay.uni.ui;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.unipay.uni.MainActivity;
import com.unipay.uni.R;

public class EnrollaUni extends Activity {

    AlertDialog.Builder dialogo;

    private Button btnContinuar;
    private ImageButton imgInfo;
    private CheckBox cbTerminos;

    //instancia de la clase modal, contiene todos los metodos de llamado a las ventanas
    // tipo modals de la app
    ModalToastCustom modal = new ModalToastCustom();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enrolla_uni);

        btnContinuar = findViewById(R.id.btnContinuar);
        imgInfo = findViewById(R.id.imgInfo);
        cbTerminos = findViewById(R.id.cbTerminos);

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
                if (cbTerminos.isChecked() == true){
                    btnContinuar.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(EnrollaUni.this, OtorgarPermisos.class);
                            startActivity(intent);
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
        });
    }

}