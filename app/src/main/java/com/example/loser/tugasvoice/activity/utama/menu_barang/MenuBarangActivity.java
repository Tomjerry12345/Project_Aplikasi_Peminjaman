package com.example.loser.tugasvoice.activity.utama.menu_barang;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.loser.tugasvoice.LaporanActivity;
import com.example.loser.tugasvoice.R;

public class MenuBarangActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_menu_barang);

        Button Butt1=(Button)findViewById(R.id.il);
        Button Butt2=(Button)findViewById(R.id.ml);
        Button Butt3=(Button)findViewById(R.id.ipl);
        Button Butt4=(Button)findViewById(R.id.pcl);


        Butt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuBarangActivity.this, LaporanActivity.class);
                intent.putExtra("lab", "instalasi listrik");
                startActivity(intent);
            }
        });

        Butt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuBarangActivity.this, LaporanActivity.class);
                intent.putExtra("lab", "mesin listrik");
                startActivity(intent);
            }
        });
        Butt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuBarangActivity.this, LaporanActivity.class);
                intent.putExtra("lab", "instrumen dan pengukuran listrik");
                startActivity(intent);
            }
        });

        Butt4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuBarangActivity.this, LaporanActivity.class);
                intent.putExtra("lab", "pcl");
                startActivity(intent);
            }
        });


    }
}