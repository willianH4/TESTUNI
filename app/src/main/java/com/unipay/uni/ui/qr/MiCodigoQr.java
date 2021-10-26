package com.unipay.uni.ui.qr;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.icu.util.Calendar;
import android.media.MediaScannerConnection;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;
import com.unipay.uni.R;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class MiCodigoQr extends AppCompatActivity {

    private static final int CODIGO_PERMISO_ESCRIBIR_ALMACENAMIENTO = 1;
    private Bitmap bitmap;
    private ImageView codigo;
    private Button btnExportar;
    private boolean tienePermisoParaEscribir = false; // Para los permisos en tiempo de ejecución

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mi_codigo_qr);

        btnExportar = findViewById(R.id.btnExportarSinMonto);
        codigo = findViewById(R.id.imvCodigoSinMonto);

        verificarYPedirPermisos();
        btnExportar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(getApplicationContext(), "Desde exportar", Toast.LENGTH_LONG).show();
                generarCodigoQR("Hola mundo");
            }
        });
    }

    private void generarCodigoQR(String contenidoDeQr) {
        MultiFormatWriter writer = new MultiFormatWriter();
        try {
            BitMatrix matrix = writer.encode(contenidoDeQr, BarcodeFormat.QR_CODE, 400, 400);
            BarcodeEncoder encoder = new BarcodeEncoder();

            bitmap = encoder.createBitmap(matrix);

            Toast.makeText(getApplicationContext(), "Codigo gerenerado exitosamente", Toast.LENGTH_SHORT).show();

            codigo.setImageBitmap(bitmap);

            InputMethodManager manager = (InputMethodManager) getSystemService(
                    Context.INPUT_METHOD_SERVICE
            );

        } catch (WriterException e) {
            e.printStackTrace();
        }
    }

    private void verificarYPedirPermisos() {
        if (
                ContextCompat.checkSelfPermission(
                        MiCodigoQr.this,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        == PackageManager.PERMISSION_GRANTED
        ) {
            // En caso de que haya dado permisos ponemos la bandera en true
            tienePermisoParaEscribir = true;
        } else {
            // Si no, entonces pedimos permisos
            ActivityCompat.requestPermissions(MiCodigoQr.this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    CODIGO_PERMISO_ESCRIBIR_ALMACENAMIENTO);
        }
    }

    private void noTienePermiso() {
        Toast.makeText(MiCodigoQr.this, "No has dado permiso para escribir", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case CODIGO_PERMISO_ESCRIBIR_ALMACENAMIENTO:
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(getApplicationContext(), "Permisos concedidos", Toast.LENGTH_LONG).show();
                    tienePermisoParaEscribir = true;
                } else {
                    noTienePermiso();
                    Toast.makeText(getApplicationContext(), "Acepte los permisos de escritura", Toast.LENGTH_LONG).show();
                }
        }
    }

    public String saveImage(Bitmap myBitmap) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        myBitmap.compress(Bitmap.CompressFormat.PNG, 90, bytes);
        File fileUni = new File(
                Environment.getExternalStorageDirectory() + "/Pictures/CodigosQR/");
        // have the object build the directory structure, if needed.

        if (!fileUni.getParentFile().exists())
            fileUni.getParentFile().mkdirs();
        if (!fileUni.exists()) {
            try {
                fileUni.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (!fileUni.exists()) {
            Log.d("directorio", "" + fileUni.mkdirs());
            fileUni.mkdirs();
        }

        try {
            File f = null;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                f = new File(fileUni, Calendar.getInstance()
                        .getTimeInMillis() + ".png");
            }
            Log.i("Archivo", ""+ f.getAbsoluteFile());
            f.createNewFile();   //give read write permission
            FileOutputStream fo = new FileOutputStream(f);
            fo.write(bytes.toByteArray());
            MediaScannerConnection.scanFile(this,
                    new String[]{f.getPath()},
                    new String[]{"image/png"}, null);
            fo.close();
            Log.d("TAG", "File Saved::--->" + f.getAbsolutePath());
            Toast.makeText(getApplicationContext(), "Codigo QR guardado en la ruta: "+f.getAbsolutePath(), Toast
                    .LENGTH_LONG).show();

            return f.getAbsolutePath();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return "";

    }
}