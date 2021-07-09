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

import com.example.loser.tugasvoice.activity.utama.pengembalian.FormPengembalianActivity;
import com.itextpdf.text.DocumentException;
import com.squareup.picasso.Picasso;

import java.io.FileNotFoundException;
import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;

public class DetailBarangPinjman extends AppCompatActivity {
    TextView tvJudul, tvJmlbab, tvTahun, tvPenulis, tvKeterangan,title2;
    CircleImageView imgFoto;
    Context context;
    Button pinjam;
    Button download_pinjam, pengen;
    String fto;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_pinjam);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        context = this;

        download_pinjam = findViewById(R.id.download_pinjam);
        pinjam = findViewById(R.id.pinjam);
        pengen = findViewById(R.id.pengen);
        tvJudul = (TextView)findViewById(R.id.tvJudul2);
        tvJmlbab =(TextView)findViewById(R.id.tvJmlBab);
        tvTahun =(TextView)findViewById(R.id.tvTahun);
        tvPenulis = (TextView)findViewById(R.id.tvPenulis);
        tvKeterangan = (TextView)findViewById(R.id.tvKeterangan);
        imgFoto = (CircleImageView)findViewById(R.id.imgFoto);

        showDetailBuku();


        pinjam.setVisibility(View.GONE);
        pengen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loginIntent = new Intent(DetailBarangPinjman.this, FormPengembalianActivity.class);
                loginIntent.putExtra("fto",fto);
                startActivity(loginIntent);

            }
        });
    }


    private void showDetailBuku() {
       final String keyU = getIntent().getStringExtra("keyUser");
       final String keyB = getIntent().getStringExtra("keyBuku");
       final String jdl = getIntent().getStringExtra("judul");
       final String pnls = getIntent().getStringExtra("penulis");
       final String ttb = getIntent().getStringExtra("tahun_terbit");
       final String jbab = getIntent().getStringExtra("jumlah_bab");
      final String ket = getIntent().getStringExtra("keterangan");
        fto = getIntent().getStringExtra("foto");
        Log.d("wwkwkw", keyB  +"LOl"+ keyU);
       getSupportActionBar().setTitle(jdl);

        tvJudul.setText(jdl);
        tvJmlbab.setText(jbab);
        tvTahun.setText(ttb);
        tvPenulis.setText(pnls);
        tvKeterangan.setText(ket);
        Picasso.get()
                .load(fto)
                .into(imgFoto);

        download_pinjam.setOnClickListener(new View.OnClickListener() {
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
                    String a = PDFpinjam.PDFpinjam(context,fto, jdl, jbab, ttb, pnls, ket);
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



    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}

