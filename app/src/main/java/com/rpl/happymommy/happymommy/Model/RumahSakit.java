package com.rpl.happymommy.happymommy.Model;

/**
 * Created by M. Satria Wibawa on 28/04/2018.
 */

public class RumahSakit {
    private String Address;
    private String Deskripsi;
    private String Hours;
    private String ImgLokasi;
    private String ImgMaps;
    private String Phone;

    public RumahSakit (){

    }

    public RumahSakit(String address, String deskripsi, String hours, String imgLokasi, String imgMaps, String phone) {
        Address = address;
        Deskripsi = deskripsi;
        Hours = hours;
        ImgLokasi = imgLokasi;
        ImgMaps = imgMaps;
        Phone = phone;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getDeskripsi() {
        return Deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        Deskripsi = deskripsi;
    }

    public String getHours() {
        return Hours;
    }

    public void setHours(String hours) {
        Hours = hours;
    }

    public String getImgLokasi() {
        return ImgLokasi;
    }

    public void setImgLokasi(String imgLokasi) {
        ImgLokasi = imgLokasi;
    }

    public String getImgMaps() {
        return ImgMaps;
    }

    public void setImgMaps(String imgMaps) {
        ImgMaps = imgMaps;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }
}
