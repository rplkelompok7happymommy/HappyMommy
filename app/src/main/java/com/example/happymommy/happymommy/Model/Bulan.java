package com.example.happymommy.happymommy.Model;

/**
 * Created by M. Satria Wibawa on 01/05/2018.
 */

public class Bulan {
    private String Gejala;
    private String Makanan;
    private String Perkembangan;
    private String Tips;
    private String ImgGejala;
    private String ImgMakanan;
    private String ImgPerkembangan;
    private String ImgTips;

    public Bulan() {

    }

    public Bulan(String gejala, String makanan, String perkembangan, String tips, String imgGejala, String imgMakanan, String imgPerkembangan, String imgTips) {
        Gejala = gejala;
        Makanan = makanan;
        Perkembangan = perkembangan;
        Tips = tips;
        ImgGejala = imgGejala;
        ImgMakanan = imgMakanan;
        ImgPerkembangan = imgPerkembangan;
        ImgTips = imgTips;
    }

    public String getGejala() {
        return Gejala;
    }

    public void setGejala(String gejala) {
        Gejala = gejala;
    }

    public String getMakanan() {
        return Makanan;
    }

    public void setMakanan(String makanan) {
        Makanan = makanan;
    }

    public String getPerkembangan() {
        return Perkembangan;
    }

    public void setPerkembangan(String perkembangan) {
        Perkembangan = perkembangan;
    }

    public String getTips() {
        return Tips;
    }

    public void setTips(String tips) {
        Tips = tips;
    }

    public String getImgGejala() {
        return ImgGejala;
    }

    public void setImgGejala(String imgGejala) {
        ImgGejala = imgGejala;
    }

    public String getImgMakanan() {
        return ImgMakanan;
    }

    public void setImgMakanan(String imgMakanan) {
        ImgMakanan = imgMakanan;
    }

    public String getImgPerkembangan() {
        return ImgPerkembangan;
    }

    public void setImgPerkembangan(String imgPerkembangan) {
        ImgPerkembangan = imgPerkembangan;
    }

    public String getImgTips() {
        return ImgTips;
    }

    public void setImgTips(String imgTips) {
        ImgTips = imgTips;
    }
}
