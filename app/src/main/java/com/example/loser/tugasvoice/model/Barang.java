package com.example.loser.tugasvoice.model;

public class Barang {

    String judul;
    String penulis;
    String tahun_terbit;
    String jumlah_bab;
    String keterangan;
    String keyBuku;
    String keyUser;

    String foto;
    public Barang(){

    }

    public Barang(String judul, String penulis, String tahun_terbit, String jumlah_bab, String keterangan, String foto, String keyBuku, String keyUser) {
        this.judul = judul;
        this.penulis = penulis;
        this.tahun_terbit = tahun_terbit;
        this.jumlah_bab = jumlah_bab;
        this.keterangan = keterangan;
        this.keyBuku = keyBuku;
        this.keyUser = keyUser;
        this.foto = foto;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getPenulis() {
        return penulis;
    }

    public void setPenulis(String penulis) {
        this.penulis = penulis;
    }

    public String getTahun_terbit() {
        return tahun_terbit;
    }

    public void setTahun_terbit(String tahun_terbit) {
        this.tahun_terbit = tahun_terbit;
    }

    public String getJumlah_bab() {
        return jumlah_bab;
    }

    public void setJumlah_bab(String jumlah_bab) {
        this.jumlah_bab = jumlah_bab;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getKeyBuku() {
        return keyBuku;
    }

    public void setKeyBuku(String keyBuku) {
        this.keyBuku = keyBuku;
    }

    public String getKeyUser() {
        return keyUser;
    }

    public void setKeyUser(String keyUser) {
        this.keyUser = keyUser;
    }


}
