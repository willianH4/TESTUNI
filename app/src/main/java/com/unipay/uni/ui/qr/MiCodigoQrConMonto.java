package com.unipay.uni.ui.qr;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.media.MediaScannerConnection;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
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
import java.util.Calendar;

public class MiCodigoQrConMonto extends AppCompatActivity implements View.OnClickListener {

    private static final int CODIGO_PERMISO_ESCRIBIR_ALMACENAMIENTO = 1;
    private static final int ALTURA_CODIGO = 500, ANCHURA_CODIGO = 500;
    private EditText etMonto;
    private Button btnGenerar, btnGuardar;
    private ImageView imvCodigoQR;
    private Bitmap bitmap;

    private boolean tienePermisoParaEscribir = false; // Para los permisos en tiempo de ejecución

    private String obtenerTextoParaCodigo() {
        etMonto.setError(null);
        String posibleTexto = etMonto.getText().toString();
        if (posibleTexto.isEmpty()) {
            etMonto.setError("Escribe el Monto del código QR");
            etMonto.requestFocus();
        }
        return posibleTexto;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mi_codigo_qr_con_monto);

        etMonto = findViewById(R.id.etMonto);
        btnGenerar = findViewById(R.id.btnGenerar);
        imvCodigoQR = findViewById(R.id.imvCodigoQr);
        btnGuardar = findViewById(R.id.btnExportar);

        // llamado al metodo para solicitar los permisos de escritura
        verificarYPedirPermisos();
        btnGenerar.setOnClickListener(this);
        btnGuardar.setOnClickListener(this);
    }


    private void generarCodigoQR() {
//        String monto = etMonto.getText().toString();
        String telefono = "7878-8989";
        String contenidoDeQr = obtenerTextoParaCodigo() + "#" + telefono;

        MultiFormatWriter writer = new MultiFormatWriter();
        try {
            BitMatrix matrix = writer.encode(contenidoDeQr, BarcodeFormat.QR_CODE, 400, 400);
            BarcodeEncoder encoder = new BarcodeEncoder();

            bitmap = encoder.createBitmap(matrix);

            Toast.makeText(getApplicationContext(), "Codigo gerenerado exitosamente", Toast.LENGTH_SHORT).show();

            imvCodigoQR.setImageBitmap(bitmap);

            InputMethodManager manager = (InputMethodManager) getSystemService(
                    Context.INPUT_METHOD_SERVICE
            );

            // ocultar teclado
            manager.hideSoftInputFromWindow(etMonto.getApplicationWindowToken(),
                    0
            );
        } catch (WriterException e) {
            e.printStackTrace();
        }
    }

    private void verificarYPedirPermisos() {
        if (
            ContextCompat.checkSelfPermission(
            MiCodigoQrConMonto.this,
            Manifest.permission.WRITE_EXTERNAL_STORAGE)
            == PackageManager.PERMISSION_GRANTED
        ) {
            // En caso de que haya dado permisos ponemos la bandera en true
            tienePermisoParaEscribir = true;
        } else {
            // Si no, entonces pedimos permisos
            ActivityCompat.requestPermissions(MiCodigoQrConMonto.this,
            new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
            CODIGO_PERMISO_ESCRIBIR_ALMACENAMIENTO);
        }
//        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.R){
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
//                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){
//                    saveImage(bitmap);
//                }else {
//                    ActivityCompat.requestPermissions(
//                            this,
//                            new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
//                            CODIGO_PERMISO_ESCRIBIR_ALMACENAMIENTO
//                    );
//                }
//            }else {
//                saveImage(bitmap);
//            }
//        }else {
//            saveImage(bitmap);
//        }
    }

    private void noTienePermiso() {
        Toast.makeText(MiCodigoQrConMonto.this, "No has dado permiso para escribir", Toast.LENGTH_SHORT).show();
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

    /**
     * Metodo para exportar la imagen en formato PNG
     **/
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
            File f = new File(fileUni, Calendar.getInstance()
                    .getTimeInMillis() + ".png");
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

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnGenerar:
                generarCodigoQR();
                break;
            case R.id.btnExportar:
                saveImage(bitmap);
                break;
        }
    }
}