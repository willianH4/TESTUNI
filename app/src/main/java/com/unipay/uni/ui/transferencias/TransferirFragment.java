package com.unipay.uni.ui.transferencias;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.unipay.uni.R;

public class TransferirFragment extends Fragment {

//    NavController navController;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_transferir, container, false);
    }

//    @Override
//    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
//        navController = Navigation.findNavController(view);
//        super.onViewCreated(view, savedInstanceState);
//    }
}