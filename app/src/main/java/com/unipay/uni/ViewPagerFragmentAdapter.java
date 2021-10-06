package com.unipay.uni;

import android.widget.Toast;

import java.util.ArrayList;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class ViewPagerFragmentAdapter extends FragmentStateAdapter {

    int elementos = 3;
    private ArrayList<Fragment> arrayList = new ArrayList<>();

    public ViewPagerFragmentAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new DestinatarioFragment();
            case 1:
                return new TransferirFragment();
            case 2:
                return new ResumenTransferenciaFragment();
        }
        return null;
    }

    @Override
    public int getItemCount() {
        return 3;
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }
}