package com.example.loser.tugasvoice;


import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
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

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class BarangActivity2 extends AppCompatActivity {
    private Button rekaman;
    private DatabaseReference dbRef;
    private FirebaseAuth auth;
    private StorageReference storage;
    private static final int SELECT_IMAGE = 2;
    String key;
    Uri uri = null;
    Uri uri2 = null;
    String key2, judul;
    CircleImageView ivFoto;
    ProgressDialog progressDialog;
    EditText tvJudul, tvPenerjemah, tvTahun, tvJumlahBab, tvKeterangan, tvLab;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barang2);
        storage = FirebaseStorage.getInstance().getReference();
        Log.d("Tess", "tesssssss");
        getSupportActionBar().setTitle("TAMBAH BARANG");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        ivFoto =(CircleImageView) findViewById(R.id.ivFoto);
        auth = FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();
        key = "6666";

        Intent intent = getIntent();
        Bundle a = intent.getExtras();
        String p = (String) a.get("kode");

        dbRef = FirebaseDatabase.getInstance().getReference("Barang");
        key2= p;
        progressDialog = new ProgressDialog(BarangActivity2.this);
        tvJudul= findViewById (R.id.tvNamaBarang);
        tvPenerjemah= findViewById (R.id.tvStatus);
        tvTahun= findViewById (R.id.tvTahun);
        tvJumlahBab= findViewById (R.id.tvJumlahBarang);
        tvKeterangan= findViewById (R.id.tvKeterangan);
        tvLab = findViewById(R.id.tvLab);

        rekaman = findViewById(R.id.btnKonfirmasi);
        rekaman.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                judul = tvJudul.getText().toString();
                String penerjemah = tvPenerjemah.getText().toString();
                String tahun = tvTahun.getText().toString();
                String jumlahBab = tvJumlahBab.getText().toString();
                String keterangan = tvKeterangan.getText().toString();
                String lab = tvLab.getText().toString();

                if (judul.isEmpty()) {
                    Toast.makeText(BarangActivity2.this, "Masukkan Judul Barang", Toast.LENGTH_SHORT).show();
                }else {

                    daftarbuku(judul,penerjemah,tahun,jumlahBab,keterangan,lab);

                }

            }
        });

        ivFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), SELECT_IMAGE);
            }
        });

    }

    private void daftarbuku(final String judul, final String penerjemah, final String tahun, final String jumlahBab, final String keterangan, final String lab) {
        progressDialog.show();
        progressDialog.setMessage("Upload Data "+ judul);
        final String device_token = FirebaseInstanceId.getInstance().getToken();
        StorageReference filepath = storage.child("Barang").child(judul);
        if (uri2 == null) {

            Map<String, Object> update_hashMap = new HashMap<String, Object>();
            update_hashMap.put("device_token", device_token);
            update_hashMap.put("penulis", penerjemah);
            update_hashMap.put("tahun_terbit", tahun);
            update_hashMap.put("judul", judul);
            update_hashMap.put("jumlah_bab", jumlahBab);
            update_hashMap.put("keyUser", key);
            update_hashMap.put("keyBuku", key2);
            update_hashMap.put("keterangan", keterangan);

            dbRef.child(lab).child(key).child(key2).updateChildren(update_hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {

                    if (task.isSuccessful()) {
                        Toast.makeText(BarangActivity2.this, "Uploading Data " + judul, Toast.LENGTH_LONG).show();
                        progressDialog.dismiss();
                    }

                }
            });
        } else {
            filepath.putFile(uri2).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                    if (task.isSuccessful()) {
                        final String download_url = task.getResult().getDownloadUrl().toString();
                        Map<String, Object> update_hashMap = new HashMap<>();
                        update_hashMap.put("penulis", penerjemah);
                        update_hashMap.put("tahun_terbit", tahun);
                        update_hashMap.put("judul", judul);
                        update_hashMap.put("jumlah_bab", jumlahBab);
                        update_hashMap.put("keyUser", key);
                        update_hashMap.put("keyBuku", key2);
                        update_hashMap.put("keterangan", keterangan);
                        update_hashMap.put("foto", download_url);
                        update_hashMap.put("device_token", device_token);
                        dbRef.child(lab).child(key).child(key2).updateChildren(update_hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {

                                if (task.isSuccessful()) {
                                    Toast.makeText(BarangActivity2.this, "Uploading Data " + judul, Toast.LENGTH_LONG).show();
                                    progressDialog.dismiss();
                                }

                            }
                        });
                    } else {
                        progressDialog.dismiss();
                        Toast.makeText(BarangActivity2.this, "Error / Koneksi Internet Anda Tidak Ada", Toast.LENGTH_LONG).show();


                    }

                }
            });
        }
    }



    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == SELECT_IMAGE) {
                uri2 = data.getData();
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri2);
                    ivFoto.setImageBitmap(bitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}