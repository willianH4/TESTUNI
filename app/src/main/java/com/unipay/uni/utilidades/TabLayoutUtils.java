package com.unipay.uni.utilidades;

import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;

/** Clase para deshabilitar el click en las pestañas
 * de cada tab y que solo sean cambiables mediante el touch
 */

public class TabLayoutUtils {

    public static void enableTabs(TabLayout tabLayout, Boolean enable){
        ViewGroup viewGroup = getTabViewGroup(tabLayout);
        if (viewGroup != null) for (int childIndex = 0;
                                    childIndex < viewGroup.getChildCount();
                                    childIndex++) { View tabView = viewGroup.getChildAt(childIndex);
                                    if ( tabView != null) tabView.setEnabled(enable);
        }
    }

    public static View getTabView(TabLayout tabLayout, int position){
        View tabView = null; ViewGroup viewGroup = getTabViewGroup(tabLayout);
        if (viewGroup != null && viewGroup.getChildCount() > position) tabView = viewGroup.getChildAt(position);
        return tabView; }

        private static ViewGroup getTabViewGroup(TabLayout tabLayout){ ViewGroup viewGroup = null;
        if (tabLayout != null && tabLayout.getChildCount() > 0 ) {
            View view = tabLayout.getChildAt(0);
            if (view != null && view instanceof ViewGroup) viewGroup = (ViewGroup) view;
        } return viewGroup;
    }
}

