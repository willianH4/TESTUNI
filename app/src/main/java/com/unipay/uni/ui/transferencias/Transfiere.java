package com.unipay.uni.ui.transferencias;

import static androidx.appcompat.widget.Toolbar.*;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.unipay.uni.MainActivity;
import com.unipay.uni.R;
import com.unipay.uni.ui.adapters.ViewPagerFragmentAdapter;
import com.unipay.uni.ui.transferencias.DestinatarioFragment;
import com.unipay.uni.utilidades.TabLayoutUtils;

import java.util.ArrayList;

public class Transfiere extends AppCompatActivity{

    private ViewPager2 myViewPager2;
    private ViewPagerFragmentAdapter myAdapter;
    private  TabLayout tabLayout;
    private FloatingActionMenu menu_fab;
    private FloatingActionButton leerQr, qrSinMonto, qrMonto;

    /* arreglo para el texto
     * o encabezados de cada tab
     * */
   private String[] texto = new String[]{
            "Origen y contacto",
            "Transferir",
            "Resumen",
    };

    private ArrayList<Fragment> arrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        DestinatarioFragment contactos = new DestinatarioFragment();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfiere);

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

        toolbar.setOnMenuItemClickListener(new OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.user) {
                    Toast.makeText(getApplicationContext(), "Click", Toast.LENGTH_SHORT).show();
                }
                return true;
            }
        });

        tabLayout = findViewById(R.id.tabLayoutMain);
        myViewPager2 = findViewById(R.id.pager);
        myAdapter = new ViewPagerFragmentAdapter(getSupportFragmentManager(), getLifecycle());

        // add Fragments in your ViewPagerFragmentAdapter class
        arrayList.add(new DestinatarioFragment());
        arrayList.add(new TransferirFragment());
        arrayList.add(new ResumenTransferenciaFragment());

        myAdapter = new ViewPagerFragmentAdapter(getSupportFragmentManager(), getLifecycle());
//         set Orientation in your ViewPager2
        myViewPager2.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
        myViewPager2.setAdapter(myAdapter);
        myViewPager2.setPageTransformer(new MarginPageTransformer(1500));

        myViewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback(){
            @Override
            public void onPageSelected(int i) {
                // aqui codigo para cuando haya algun cambio en un tab
                super.onPageSelected(i);
            }
        });

        generarTab();
        // Deshabilita evento click de los tab
        deshabilitarTab();

        // No funcional
        menu_fab = findViewById(R.id.menu_fab);
        leerQr = findViewById(R.id.leerQr);
        qrSinMonto = findViewById(R.id.qrSinMonto);
        qrMonto = findViewById(R.id.qrMonto);
    }

    private void deshabilitarTab() {
        tabLayout = tabLayout.findViewById(R.id.tabLayoutMain);
        TabLayoutUtils.enableTabs( tabLayout, false );
    }

    private void generarTab() {
        new TabLayoutMediator(tabLayout, myViewPager2,
                new TabLayoutMediator.TabConfigurationStrategy(){
                    @Override
                    public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                        tab.setText(texto[position]);
                    }
                }).attach();
    }

    private ViewPagerFragmentAdapter getMyAdapter() {
        return myAdapter;
    }

    private void scanearQR() {
        IntentIntegrator integrator = new IntentIntegrator(Transfiere.this);
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

    private void regresar() {
        Intent intent = new Intent(Transfiere.this, MainActivity.class);
        startActivity(intent);
    }

}