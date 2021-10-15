package com.codes.bilikjelantah.Model;

public class User {
    String email,nama,password,id;
    double saldo;
    int jumlah_liter,checkpoint;

    public User(String id,String email, String nama, String password, double saldo, int jumlah_liter,int checkpoint) {
        this.id = id;
        this.email = email;
        this.nama = nama;
        this.password = password;
        this.saldo = saldo;
        this.jumlah_liter = jumlah_liter;
        this.checkpoint = checkpoint;
    }
    public User(){}

    public int getCheckpoint() {
        return checkpoint;
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getNama() {
        return nama;
    }

    public String getPassword() {
        return password;
    }

    public double getSaldo() {
        return saldo;
    }

    public int getJumlah_liter() {
        return jumlah_liter;
    }
}
