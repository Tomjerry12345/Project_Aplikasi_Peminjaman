package com.example.loser.tugasvoice;


import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class PinajamActivity extends AppCompatActivity {
    private Button rekaman;
    private DatabaseReference dbRef;
    private StorageReference storage;

    String urlImage;

    String key2, judul;
    CircleImageView ivFoto;
    ProgressDialog progressDialog;
    EditText tvJudul, tvPenerjemah, tvTahun, tvJumlahBab, tvbatas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_pinjam);
        Log.d("Tess", "tesssssss");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        urlImage = getIntent().getStringExtra("img");
        final String jdl = getIntent().getStringExtra("jdl");
        getSupportActionBar().setTitle(jdl);

        ivFoto = (CircleImageView) findViewById(R.id.ivFoto);

        if (urlImage != null) {
            Picasso.get().load(urlImage).into(ivFoto);
        } else {
            ivFoto.setVisibility(View.GONE);
        }
        dbRef = FirebaseDatabase.getInstance().getReference("Pinjaman");
        key2 = dbRef.push().getKey();
        progressDialog = new ProgressDialog(PinajamActivity.this);
        tvJudul = findViewById(R.id.tvNamaBarang);
        tvPenerjemah = findViewById(R.id.tvStatus);
        tvTahun = findViewById(R.id.tvTahun);
        tvJumlahBab = findViewById(R.id.tvJumlahBarang);
        tvbatas = findViewById(R.id.tvJmlBab);


        rekaman = findViewById(R.id.btnKonfirmasi);
        rekaman.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                judul = tvJudul.getText().toString();
                String penerjemah = tvPenerjemah.getText().toString();
                String tahun = tvTahun.getText().toString();
                String jumlahBab = tvJumlahBab.getText().toString();
                String batas = tvbatas.getText().toString();

                if (judul.isEmpty()) {
                    Toast.makeText(PinajamActivity.this, "Masukkan Judul Barang", Toast.LENGTH_SHORT).show();
                } else {

                    daftarbuku(judul, penerjemah, tahun, jumlahBab, batas);

                }

            }
        });


    }

    private void daftarbuku(final String judul, final String penerjemah, final String tahun, final String jumlahBab, String batas) {
        progressDialog.show();
        progressDialog.setMessage("Upload Data " + judul);
        final String device_token = FirebaseInstanceId.getInstance().getToken();

        Map<String, Object> update_hashMap = new HashMap<String, Object>();
        update_hashMap.put("device_token", device_token);
        update_hashMap.put("judul", judul);
        update_hashMap.put("penulis", penerjemah);
        update_hashMap.put("tahun_terbit", tahun);
        update_hashMap.put("jumlah_bab", jumlahBab);
        update_hashMap.put("foto", urlImage);
        update_hashMap.put("keyBuku", key2);
        update_hashMap.put("keterangan", batas);



        dbRef.child(key2).updateChildren(update_hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                if (task.isSuccessful()) {
                    Toast.makeText(PinajamActivity.this, "Uploading Data " + judul, Toast.LENGTH_LONG).show();
                    progressDialog.dismiss();
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