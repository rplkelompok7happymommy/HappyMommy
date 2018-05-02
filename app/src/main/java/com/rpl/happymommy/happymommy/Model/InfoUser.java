package com.rpl.happymommy.happymommy.Model;

/**
 * Created by M. Satria Wibawa on 24/03/2018.
 */

public class InfoUser {

    private String Nama;
    private String Alamat;
    private String Nohp;

    public InfoUser(){

    }

    public InfoUser(String nama, String alamat, String nohp) {
        Nama = nama;
        Alamat = alamat;
        Nohp = nohp;
    }

    public String getNama() {
        return Nama;
    }

    public void setNama(String nama) {
        Nama = nama;
    }

    public String getAlamat() {
        return Alamat;
    }

    public void setAlamat(String alamat) {
        Alamat = alamat;
    }

    public String getNohp() {
        return Nohp;
    }

    public void setNohp(String nohp) {
        Nohp = nohp;
    }
}

