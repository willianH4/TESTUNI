package com.unipay.uni;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.text.Html;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.unipay.uni.ui.adapters.ViewPagerFragmentAdapter;

import java.util.ArrayList;

public class Transfiere extends AppCompatActivity{

    private ViewPager2 myViewPager2;
    private ViewPagerFragmentAdapter myAdapter;

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
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfiere);

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

        TabLayout tabLayout = findViewById(R.id.tabLayoutMain);

        myViewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback(){
            @Override
            public void onPageSelected(int i) {
                // aqui codigo para cuando haya algun cambio en un tab
                super.onPageSelected(i);
            }
        });

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
}