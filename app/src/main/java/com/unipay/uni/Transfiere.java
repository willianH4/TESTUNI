package com.unipay.uni;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import com.shuhart.stepview.StepView;

import java.util.ArrayList;

public class Transfiere extends AppCompatActivity {

    StepView stepView;

    int stepIndex = 0;

    String[] stepsTexts = {"paso 1", "paso 2", "paso 3"};

    String[] descriptionTexts = {
      "Procesando pago",
      "Transferir",
      "Transferencia realizada exitosamente"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfiere);

        stepView = findViewById(R.id.step_view);

        stepView.getState()
                .animationType(StepView.ANIMATION_ALL)
                .stepsNumber(3)
                .animationDuration(getResources().getInteger(android.R.integer.config_shortAnimTime))
                .commit();
        nextStep();

        stepView.setOnStepClickListener(new StepView.OnStepClickListener() {
            @Override
            public void onStepClick(int step) {

            }
        });

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
        }, 3000);
    }

}