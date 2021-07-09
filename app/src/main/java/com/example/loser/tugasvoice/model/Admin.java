package com.example.loser.tugasvoice.model;

public class Admin {
    private String nama;
    private String email;
    private String key;
    private String foto;
    private String pass;
    public Admin() {

    }
    public Admin(String nama, String email, String key, String foto, String pass) {
        this.nama = nama;
        this.email = email;
        this.key = key;
        this.foto = foto;
        this.pass = pass;
    }
    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
