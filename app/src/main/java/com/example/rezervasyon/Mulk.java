package com.example.rezervasyon;

import android.os.Parcel;
import android.os.Parcelable;

public class Mulk implements Parcelable {

    private int mulkid;
    private int userid;
    private String baslik;
    private String tip;
    private String adres;
    private int ucret;
    private String odasayisi;
    private String kat;
    private String isitma;
    private String balkon;
    private String metre;
    private byte[] image;
    private String il;
    private String ilce;
    private String kiralikzamani;

    public int getMulkid() {
        return mulkid;
    }

    public void setMulkid(int mulkid) {
        this.mulkid = mulkid;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getBaslik() {
        return baslik;
    }

    public void setBaslik(String baslik) {
        this.baslik = baslik;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public String getAdres() {
        return adres;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }

    public int getUcret() {
        return ucret;
    }

    public void setUcret(int ucret) {
        this.ucret = ucret;
    }

    public String getOdasayisi() {
        return odasayisi;
    }

    public void setOdasayisi(String odasayisi) {
        this.odasayisi = odasayisi;
    }

    public String getKat() {
        return kat;
    }

    public void setKat(String kat) {
        this.kat = kat;
    }

    public String getIsitma() {
        return isitma;
    }

    public void setIsitma(String isitma) {
        this.isitma = isitma;
    }

    public String getBalkon() {
        return balkon;
    }

    public void setBalkon(String balkon) {
        this.balkon = balkon;
    }

    public String getMetre() {
        return metre;
    }

    public void setMetre(String metre) {
        this.metre = metre;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
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

    public String getKiralikzamani() {
        return kiralikzamani;
    }

    public void setKiralikzamani(String kiralikzamani) {
        this.kiralikzamani = kiralikzamani;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.mulkid);
        dest.writeInt(this.userid);
        dest.writeString(this.baslik);
        dest.writeString(this.tip);
        dest.writeString(this.adres);
        dest.writeInt(this.ucret);
        dest.writeString(this.odasayisi);
        dest.writeString(this.kat);
        dest.writeString(this.ilce);
        dest.writeString(this.balkon);
        dest.writeString(this.metre);
        dest.writeByteArray(this.image);
        dest.writeString(this.il);
        dest.writeString(this.isitma);
        dest.writeString(this.kiralikzamani);
    }

    public Mulk() {
    }

    protected Mulk(Parcel in) {
        this.mulkid = in.readInt();
        this.userid = in.readInt();
        this.baslik = in.readString();
        this.tip = in.readString();
        this.adres = in.readString();
        this.ucret = in.readInt();
        this.odasayisi = in.readString();
        this.kat = in.readString();
        this.ilce = in.readString();
        this.balkon = in.readString();
        this.metre = in.readString();
        this.image = in.createByteArray();
        this.il = in.readString();
        this.isitma = in.readString();
        this.kiralikzamani = in.readString();
    }

    public static final Creator<Mulk> CREATOR = new Creator<Mulk>() {
        @Override
        public Mulk createFromParcel(Parcel source) {
            return new Mulk(source);
        }

        @Override
        public Mulk[] newArray(int size) {
            return new Mulk[size];
        }
    };
}
