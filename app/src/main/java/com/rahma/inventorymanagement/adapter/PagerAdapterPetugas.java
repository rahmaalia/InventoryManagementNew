package com.rahma.inventorymanagement.adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.rahma.inventorymanagement.fragment.BarangFragmentPetugas;
import com.rahma.inventorymanagement.fragment.DipinjamFragmentPetugas;
import com.rahma.inventorymanagement.fragment.PermintaanFragmentPetugas;

public class PagerAdapterPetugas extends FragmentPagerAdapter{

    private int numOfTabs;

    public PagerAdapterPetugas(FragmentManager fm, int numOfTabs){
        super(fm);
        this.numOfTabs = numOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new PermintaanFragmentPetugas();
            case 1:
                return new DipinjamFragmentPetugas();
            case 2:
                return new BarangFragmentPetugas();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numOfTabs;
    }
}
