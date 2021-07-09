package com.example.loser.tugasvoice;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.example.loser.tugasvoice.adapter.AdapterBarang;
import com.example.loser.tugasvoice.model.Barang;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class LaporanActivity extends AppCompatActivity {
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
        getSupportActionBar().setTitle("MENU BARANG");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        FloatingActionButton Butt1 = (FloatingActionButton) findViewById(R.id.AddBuku);
        Log.d("awaw", "awawaw");

        context = this;
        Intent intent = getIntent();
        Bundle a = intent.getExtras();
        String p = (String) a.get("lab");


        rvBuku = (RecyclerView) findViewById(R.id.rvBuku);
        rvBuku.setHasFixedSize(true);
        rvBuku.setLayoutManager(new LinearLayoutManager(LaporanActivity.this));
        progressDialog = new ProgressDialog(LaporanActivity.this);
        progressDialog.setMessage("Memuat...");

        // Showing progress dialog.
        progressDialog.show();

        dataBuku(p);

        Butt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent int1 = new Intent(LaporanActivity.this, BarangActivity.class);
                startActivity(int1);
                Log.d("awaw", "awawaw");


            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_search, menu);
        MenuItem item = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(item);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchView.clearFocus();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //Filter as you type
             //   dataBuku(String lab);
                return false;
            }
        });
        // super.onCreateOptionsMenu(menu, inflater);
        return true;


    }


    private void dataBuku(String lab) {
        dbRef = FirebaseDatabase.getInstance().getReference().child("Barang").child(lab);

   //   Query query = dbRef.orderByChild("lab").equalTo("pcl");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                Log.println(Log.ASSERT, "datasnapshoot", snapshot.toString());
                barangList.clear();
                for (DataSnapshot templateSnapshot : snapshot.getChildren()) {
                    for (DataSnapshot snap : templateSnapshot.getChildren()) {

                        Barang dataBarangList = snap.getValue(Barang.class);
                        barangList.add(dataBarangList);
                    }

                    adapter = new AdapterBarang(context, barangList);

                    rvBuku.setAdapter(adapter);

                    // Hiding the progress dialog.
                    progressDialog.dismiss();
                }
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