package org.atamertc.repository.entity;

public class Adres extends BaseEntity{
    int id;
    int musteriid;
    String il;
    String ilce;
    String mahalle;
    String adres;

    public Adres() {
    }

    public Adres(int id, int musteriid, String il, String ilce, String mahalle, String adres) {
        this.id = id;
        this.musteriid = musteriid;
        this.il = il;
        this.ilce = ilce;
        this.mahalle = mahalle;
        this.adres = adres;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMusteriid() {
        return musteriid;
    }

    public void setMusteriid(int musteriid) {
        this.musteriid = musteriid;
    }

    public String getIl() {
        return il;
    }

    public void setIl(String il) {
        this.il = il;
    }

    public String getIlce() {
        return ilce;
    }

    public void setIlce(String ilce) {
        this.ilce = ilce;
    }

    public String getMahalle() {
        return mahalle;
    }

    public void setMahalle(String mahalle) {
        this.mahalle = mahalle;
    }

    public String getAdres() {
        return adres;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }

    @Override
    public String toString() {
        return "Adres{" +
                "id=" + id +
                ", musteriid=" + musteriid +
                ", il='" + il + '\'' +
                ", ilce='" + ilce + '\'' +
                ", mahalle='" + mahalle + '\'' +
                ", adres='" + adres + '\'' +
                ", state=" + state +
                ", olusturmaTarihi=" + olusturmaTarihi +
                ", guncellemeTarihi=" + guncellemeTarihi +
                '}';
    }
}
