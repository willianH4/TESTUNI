package com.unipay.uni.utilidades;

import android.content.Intent;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class LeerCodigoQR extends AppCompatActivity {

    public void scanearQR() {
        IntentIntegrator integrator = new IntentIntegrator(LeerCodigoQR.this);
        integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE); //lee todos los tipos de codigos
        integrator.setPrompt("Lector - CQR");
        integrator.setCameraId(0);
        integrator.setOrientationLocked(false);
        integrator.setPrompt("Escanea tu codigo QR");
        integrator.setBeepEnabled(true);
        integrator.setBarcodeImageEnabled(true);
        integrator.initiateScan();
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);

        if (result != null) {
            if (result.getContents() == null) {
                Toast.makeText(this, "Lectura cancelada", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this, result.getContents(), Toast.LENGTH_SHORT).show();
            }
        }else{
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
