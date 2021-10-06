package com.unipay.uni;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;
import android.widget.Toast;

import com.shuhart.stepview.StepView;

import java.util.ArrayList;

public class Transfiere extends AppCompatActivity {

    ViewPager2 myViewPager2;
    ViewPagerFragmentAdapter myAdapter;
    private ArrayList<Fragment> arrayList = new ArrayList<>();
    StepView stepView;

    int stepIndex = 0;

    String[] stepsTexts = {"paso 1", "paso 2", "paso 3"};

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
        // set Orientation in your ViewPager2
        myViewPager2.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);

        myViewPager2.setAdapter(myAdapter);

        myViewPager2.setPageTransformer(new MarginPageTransformer(1500));

        stepView = findViewById(R.id.step_view);

        stepView.getState()
                .animationType(StepView.ANIMATION_ALL)
                .stepsNumber(3)
                .animationDuration(getResources().getInteger(android.R.integer.config_shortAnimTime))
                .commit();
        nextStep();
    }

    private void nextStep() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                stepIndex++;
                if (stepIndex < stepsTexts.length){
                    stepView.go(stepIndex, true);
                    nextStep();
                }
            }
        }, 4000);
    }

}