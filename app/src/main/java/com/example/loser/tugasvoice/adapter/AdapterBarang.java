package com.example.loser.tugasvoice.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.loser.tugasvoice.DetailBarang;
import com.example.loser.tugasvoice.model.Barang;
import com.example.loser.tugasvoice.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class AdapterBarang extends RecyclerView.Adapter<AdapterBarang.ViewHolder> implements View.OnClickListener {

    Context context;
    LayoutInflater mInflater;
    private List<Barang> barangList;
    private DatabaseReference dbRef;

    public AdapterBarang(Context context, List<Barang> barangList) {
        this.context = context;
        this.barangList = barangList;
        mInflater = LayoutInflater.from(context);
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        CircleImageView ivFoto;
        public TextView tvNamaBarang, tvJumlahBarang, tvStatus;

        public ViewHolder(View itemView) {
            super(itemView);
            ivFoto = (CircleImageView) itemView.findViewById(R.id.ivFoto);
            tvNamaBarang = (TextView) itemView.findViewById(R.id.tvJudulBuku);
            tvStatus = (TextView) itemView.findViewById(R.id.tvPenulis);
            tvJumlahBarang = (TextView) itemView.findViewById(R.id.tvJumlahBab);
        }
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cnt_barang, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        dbRef = FirebaseDatabase.getInstance().getReference();
        Barang barang = barangList.get(position);


        final String judul = barang.getJudul();
        final String penulis = barang.getPenulis();
        final String tahun_terbit = barang.getTahun_terbit();
        final String keterangan = barang.getKeterangan();
        final String foto = barang.getFoto();
        final String jmlBab = barang.getJumlah_bab();
        final String keyUser = barang.getKeyUser();
        final String keyBuku = barang.getKeyBuku();

        holder.tvNamaBarang.setText(judul);
        holder.tvStatus.setText("STATUS : "+penulis);
        holder.tvJumlahBarang.setText("JUMLAH  : " +jmlBab);
        Picasso.get()
                .load(foto)
                .into(holder.ivFoto);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailBarang.class);
                intent.putExtra("keyUser", keyUser);
                intent.putExtra("keyBuku", keyBuku);
                intent.putExtra("judul", judul);
                intent.putExtra("penulis", penulis);
                intent.putExtra("tahun_terbit", tahun_terbit);
                intent.putExtra("jumlah_bab", jmlBab);
                intent.putExtra("keterangan", keterangan);
                intent.putExtra("foto", foto);


                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return barangList.size();
    }

    @Override
    public void onClick(View v) {

    }

}