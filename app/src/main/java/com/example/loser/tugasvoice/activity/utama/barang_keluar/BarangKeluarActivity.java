package com.example.loser.tugasvoice.activity.utama.barang_keluar;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;


import com.example.loser.tugasvoice.R;
import com.example.loser.tugasvoice.adapter.AdapterPinjaman;
import com.example.loser.tugasvoice.model.Barang;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class BarangKeluarActivity extends AppCompatActivity {
    RecyclerView rvBuku;
    RecyclerView.Adapter adapter;
    DatabaseReference dbRef, dbRef2;
    List<Barang> barangList = new ArrayList<>();
    ProgressDialog progressDialog;
    private FirebaseAuth auth;
    Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_list);
        getSupportActionBar().setTitle("DAFTAR PINJAMAN");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        FloatingActionButton Butt1 = (FloatingActionButton) findViewById(R.id.AddBuku);
        Log.d("awaw", "awawaw");

        Butt1.setVisibility(View.GONE);
        Butt1.setEnabled(true);
        context = this;


        rvBuku = (RecyclerView) findViewById(R.id.rvBuku);
        rvBuku.setHasFixedSize(true);
        rvBuku.setLayoutManager(new LinearLayoutManager(BarangKeluarActivity.this));
        progressDialog = new ProgressDialog(BarangKeluarActivity.this);
        progressDialog.setMessage("Memuat...");

        // Showing progress dialog.
        progressDialog.show();

        dataBuku();


    }

    private void dataBuku() {
        dbRef = FirebaseDatabase.getInstance().getReference("Pinjaman");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                barangList.clear();
                for (DataSnapshot templateSnapshot : snapshot.getChildren()) {


                    Barang dataBarangList = templateSnapshot.getValue(Barang.class);
                    barangList.add(dataBarangList);
                }

                adapter = new AdapterPinjaman(context, barangList);

                rvBuku.setAdapter(adapter);

                // Hiding the progress dialog.
                progressDialog.dismiss();
            }


            @Override
            public void onCancelled(DatabaseError databaseError) {

                // Hiding the progress dialog.
                progressDialog.dismiss();

            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }


}