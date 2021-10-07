package com.unipay.uni;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.text.Html;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.ArrayList;

public class Transfiere extends AppCompatActivity{

    private LinearLayout linearLayout;
    private TextView[] puntosSlide;

    ViewPager2 myViewPager2;
    ViewPagerFragmentAdapter myAdapter;
    private ArrayList<Fragment> arrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfiere);

        myViewPager2 = findViewById(R.id.pager);

        // add Fragments in your ViewPagerFragmentAdapter class
        arrayList.add(new DestinatarioFragment());
        arrayList.add(new TransferirFragment());
        arrayList.add(new ResumenTransferenciaFragment());

        myAdapter = new ViewPagerFragmentAdapter(getSupportFragmentManager(), getLifecycle());
//         set Orientation in your ViewPager2
        myViewPager2.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);

        myViewPager2.setAdapter(myAdapter);

        myViewPager2.setPageTransformer(new MarginPageTransformer(1500));

        linearLayout = findViewById(R.id.linearPuntos);
        agregarIndicadorPuntos(0);

        myViewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback(){
            @Override
            public void onPageSelected(int i) {
                agregarIndicadorPuntos(i);
                super.onPageSelected(i);
            }
        });
    }

    private ViewPagerFragmentAdapter getMyAdapter() {
        return myAdapter;
    }

    private void agregarIndicadorPuntos(int pos) {
            puntosSlide = new TextView[3];
            linearLayout.removeAllViews();

            for (int i = 0; i < puntosSlide.length; i++) {
                puntosSlide[i] = new TextView(this);
                puntosSlide[i].setText(Html.fromHtml("&#8226;"));
                puntosSlide[i].setTextSize(100);
                puntosSlide[i].setTextColor(getResources().getColor(R.color.white));
                linearLayout.addView(puntosSlide[i]);
            }
            if (puntosSlide.length > 0) {
                puntosSlide[pos].setTextColor(getResources().getColor(R.color.celeste));
            }
    }
}