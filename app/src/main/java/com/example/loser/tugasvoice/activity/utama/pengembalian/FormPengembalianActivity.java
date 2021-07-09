package com.example.loser.tugasvoice.activity.utama.pengembalian;

import android.content.Intent;
import android.support.design.internal.BottomNavigationItemView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.loser.tugasvoice.R;
import com.squareup.picasso.Picasso;

public class FormPengembalianActivity extends AppCompatActivity {
    Button upload;
    ImageView foto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_pengenmbalian);
        Intent home = getIntent();
        Bundle l = home.getExtras();
        String k =(String) l.get("fto");

        foto = findViewById(R.id.ivFoto);
        upload = findViewById(R.id.upload);
        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        Picasso.get()
                .load(k)
                .into(foto);

    }
}