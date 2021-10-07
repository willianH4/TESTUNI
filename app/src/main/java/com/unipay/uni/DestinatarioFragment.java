package com.unipay.uni;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;


public class DestinatarioFragment extends Fragment {

    private FloatingActionButton fabMenuQR;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_destinatario, container, false);

        fabMenuQR = view.findViewById(R.id.fabMenuQR);
        View leerQr, generarQr, generarQrMonto;

        leerQr = view.findViewById(R.id.leerQr);
        generarQr = view.findViewById(R.id.generarQr);
        generarQrMonto = view.findViewById(R.id.generarQrMonto);

//        fabMenuQR.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Here's a Snackbar", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

    return view;
    }
}