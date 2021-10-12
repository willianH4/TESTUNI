package com.unipay.uni.utilidades;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
//import android.support.v7.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.TextView;

import com.unipay.uni.R;

//Toco aplicar herencia de la clase AppCompatActivity para que no diera problemas el mètodo getLayoutInflater()
//public class modal_Toast_Custom extends AppCompatActivity{
public class ModalToastCustom extends AppCompatActivity{
    Dialog myDialog;
    androidx.appcompat.app.AlertDialog.Builder dialogo;
    AlertDialog.Builder dialogo1;

    public void modalInformation(final Context context) {
        myDialog = new Dialog(context);
        myDialog.setContentView(R.layout.custom_info);
        TextView tvAcept = myDialog.findViewById(R.id.tvAccept);

        tvAcept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
            }
        });

        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.show();
    }

//    public void modalConfirmacionInicio(final Context context) {
//        myDialog = new Dialog(context);
//        myDialog.setContentView(R.layout.custom_confirmacion);
//        Button btnAccept = myDialog.findViewById(R.id.btnAccept);
//
//        btnAccept.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                myDialog.dismiss();
//                /** Lanza la actividad siguiente despues de clickear el boton aceptar*/
//                Intent intent = new Intent(ModalToastCustom.this, Transfiere.class);
//                startActivity(intent);
//            }
//        });
//
//        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//        myDialog.show();
//    }
    //    private void mostrarModalInfo() {
//        String mensaje = "Elige tu cuenta podras enviar y recibir dinero a cualquier usuario de cualquier banco, " +
//                "que este registrado en la app UNIpay";
//        dialogo = new AlertDialog.Builder(EnrollaUni.this);
//        dialogo.setIcon(R.drawable.ic_info);
//        dialogo.setTitle("Información");
//        dialogo.setMessage(mensaje);
//        dialogo.setCancelable(false);
//        dialogo.setNegativeButton("Cerrar", new DialogInterface.OnClickListener() {
//            public void onClick(DialogInterface dialogo, int id) {
//                //Toast. makeText(getApplicationContext(), "Operacion Cancelada.", Toast. LENGTH LONG). show(),’
//            }
//        });
//        dialogo.show();
//    }

}

