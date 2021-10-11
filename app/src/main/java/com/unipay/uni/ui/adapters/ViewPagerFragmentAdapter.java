package com.unipay.uni.ui.adapters;

import android.widget.Toast;

import java.util.ArrayList;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.unipay.uni.DestinatarioFragment;
import com.unipay.uni.ResumenTransferenciaFragment;
import com.unipay.uni.TransferirFragment;

public class ViewPagerFragmentAdapter extends FragmentStateAdapter {

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
