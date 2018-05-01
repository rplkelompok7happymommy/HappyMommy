package com.example.happymommy.happymommy.Model;

/**
 * Created by M. Satria Wibawa on 28/04/2018.
 */

public class CommentModel {
    private String Idcomment;
    private String Username;
    private String Isi;
    private String IdRumahSakit;
    private long timestamp;

    public CommentModel() {

    }

    public CommentModel(String idcomment, String username, String isi, String idRumahSakit, long timestamp) {
        Idcomment = idcomment;
        Username = username;
        Isi = isi;
        IdRumahSakit = idRumahSakit;
        this.timestamp = timestamp;
    }

    public String getIdcomment() {
        return Idcomment;
    }

    public void setIdcomment(String idcomment) {
        Idcomment = idcomment;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getIsi() {
        return Isi;
    }

    public void setIsi(String isi) {
        Isi = isi;
    }

    public String getIdRumahSakit() {
        return IdRumahSakit;
    }

    public void setIdRumahSakit(String idRumahSakit) {
        IdRumahSakit = idRumahSakit;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
