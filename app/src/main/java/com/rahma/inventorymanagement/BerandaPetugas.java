package com.rahma.inventorymanagement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import com.rahma.inventorymanagement.adapter.PagerAdapterPetugas;

public class BerandaPetugas extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beranda_petugas);

        getSupportActionBar().setElevation(0);

        TabLayout tabLayout = findViewById(R.id.tabBarPetugas);
        TabItem tabDipinjam = findViewById(R.id.tabDipinjamPetugas);
        TabItem tabBarang = findViewById(R.id.tabBarangPetugas);
        final ViewPager viewPager = findViewById(R.id.viewPagerPetugas);
        PagerAdapterPetugas pagerAdapter = new PagerAdapterPetugas(getSupportFragmentManager(), tabLayout.getTabCount());

        viewPager.setAdapter(pagerAdapter);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.profilMenu:
                startActivity(new Intent(BerandaPetugas.this,ProfilPetugas.class));
                return true;
            case R.id.riwayatMenu:
                startActivity(new Intent(BerandaPetugas.this, HistoryPetugas.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }

    }
}