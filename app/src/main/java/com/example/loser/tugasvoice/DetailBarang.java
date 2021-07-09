package com.example.loser.tugasvoice;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.loser.tugasvoice.utils.PDFcreator;
import com.itextpdf.text.DocumentException;
import com.squareup.picasso.Picasso;

import java.io.FileNotFoundException;
import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;

public class DetailBarang extends AppCompatActivity {
    TextView tvJudul, tvJmlbab, tvTahun, tvPenulis, tvKeterangan,title2;
    CircleImageView imgFoto;
    Context context;
    Button pinjam;
    Button download;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_barang);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        context = this;

        download = findViewById(R.id.download);
        pinjam = findViewById(R.id.pinjam);
        tvJudul = (TextView)findViewById(R.id.tvJudul2);
        tvJmlbab =(TextView)findViewById(R.id.tvJmlBab);
        tvTahun =(TextView)findViewById(R.id.tvTahun);
        tvPenulis = (TextView)findViewById(R.id.tvPenulis);
        tvKeterangan = (TextView)findViewById(R.id.tvKeterangan);
        imgFoto = (CircleImageView)findViewById(R.id.imgFoto);

        showDetailBuku();




    }


    private void showDetailBuku() {
       final String keyU = getIntent().getStringExtra("keyUser");
       final String keyB = getIntent().getStringExtra("keyBuku");
       final String jdl = getIntent().getStringExtra("judul");
       final String pnls = getIntent().getStringExtra("penulis");
       final String ttb = getIntent().getStringExtra("tahun_terbit");
       final String jbab = getIntent().getStringExtra("jumlah_bab");
       final String ket = getIntent().getStringExtra("keterangan");
       final String fto = getIntent().getStringExtra("foto");
        Log.d("wwkwkw", keyB  +"LOl"+ keyU);
       getSupportActionBar().setTitle("DETAIL BARANG");

        tvJudul.setText(jdl);
        tvJmlbab.setText(jbab);
        tvTahun.setText(ttb);
        tvPenulis.setText(pnls);
        tvKeterangan.setText(ket);
        Picasso.get()
                .load(fto)
                .into(imgFoto);

        Button Butt1=(Button)findViewById(R.id.edit);
        Butt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailBarang.this, BarangActivity2.class);
                intent.putExtra("kode", keyB);
                        startActivity(intent);
            }
        });

        download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                int SDK_INT = android.os.Build.VERSION.SDK_INT;
                if (SDK_INT > 8)
                {
                    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                            .permitAll().build();
                    StrictMode.setThreadPolicy(policy);
                    //your codes here

                }
                try {
                    String a = PDFcreator.PDFcreator(context,fto, jdl, jbab, ttb, pnls, ket);
                    Toast.makeText(context, a, Toast.LENGTH_LONG).show();

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (DocumentException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

    if (keyB != null){
       // pinjam.setVisibility(View.VISIBLE);
        pinjam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, PinajamActivity.class);
                intent.putExtra("img", fto);
                intent.putExtra("jdl", jdl);
                intent.putExtra("keyB", keyB);
                startActivity(intent);
            }
        });
    }else {
        pinjam.setVisibility(View.GONE);
    }

    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}

