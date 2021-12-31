package com.tomiguntur.ProjectAkhir.News;

public class News {

    private String nama, desSingkat, deskripsi;
    private int id_gambar;


    public News(String nama, String desSingkat, String deskripsi, int id_gambar) {
        this.nama = nama;
        this.desSingkat = desSingkat;
        this.deskripsi = deskripsi;
        this.id_gambar = id_gambar;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getDesSingkat() {
        return desSingkat;
    }

    public void setDesSingkat(String desSingkat) {
        this.desSingkat = desSingkat;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public int getId_gambar() {
        return id_gambar;
    }

    public void setId_gambar(int id_gambar) {
        this.id_gambar = id_gambar;
    }
}
