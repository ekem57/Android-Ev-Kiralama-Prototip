package com.example.rezervasyon;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;

public class verikaynagi {

    public static final String TABLO = "users";         //vt tablo adı
    public static final String ID = "userid";                 //tablodaki id, plaka ve diger satırlarımız
    public static final String AD = "ad";
    public static final String SOYAD = "soyad";
    public static final String EMAIL = "email";
    public static final String SIFRE = "sifre";
    public static final String CEPTEL = "ceptel";


    public static final String TABLO_ADI = "mulkler";
    public static final String MULK_ID = "mulkid";
    public static final String USER_ID = "userid";
    public static final String BASLIK = "baslik";
    public static final String TIP = "tip";
    public static final String ADRES = "adres";
    public static final String UCRET = "ucret";
    public static final String ODA_SAYISI = "oda_sayisi";
    public static final String KAT = "kat";
    public static final String ISITMA = "isitma";
    public static final String BALKON = "balkon";
    public static final String METRESI = "metre";
    public static final String RESIM = "resim";
    public static final String IL = "il";
    public static final String ILCE = "ilce";
    public static final String TUR = "tur";
    SQLiteDatabase db;
    sqlite_veritabani mydb;
    kullanicilar user= new kullanicilar(0,null,null,null,null,null);
    Mulk m=new Mulk();

    public verikaynagi(Context c) {
        mydb = new sqlite_veritabani(c);
    }


    public void ac() {
        db = mydb.getWritableDatabase();
    }

    public void ac_read() {
        db = mydb.getReadableDatabase();
    }

    public void kapat() {
        mydb.close();
    }

    public void user_ekle(kullanicilar user) {

        ContentValues veriler = new ContentValues();
        veriler.put(AD, user.getUsername());
        veriler.put(SOYAD, user.getUsersurname());
        veriler.put(EMAIL, user.getUsermail());
        veriler.put(SIFRE, user.getUserpassword());
        veriler.put(CEPTEL, user.getUserphone());
        db.insert(TABLO, null, veriler);
        //  db.insertOrThrow(TABLO,null,veriler);

    }

    public void mulk_ekle( Mulk m) {
System.out.println("mulk ekledeyim"+ m.getKiralikzamani());
        ContentValues veriler2 = new ContentValues();
        veriler2.put(USER_ID, m.getUserid());
        veriler2.put(BASLIK, m.getBaslik());
        veriler2.put(TIP, m.getTip());
        veriler2.put(ADRES, m.getAdres());
        veriler2.put(UCRET, m.getUcret());
        veriler2.put(ODA_SAYISI, m.getOdasayisi());
        veriler2.put(KAT, m.getKat());
        veriler2.put(ISITMA, m.getIsitma());
        veriler2.put(BALKON, m.getBalkon());
        veriler2.put(METRESI, m.getMetre());
        veriler2.put(RESIM,m.getImage());
        veriler2.put(IL, m.getIl());
        veriler2.put(ILCE, m.getIlce());
        veriler2.put(TUR, m.getKiralikzamani());

        db.insert(TABLO_ADI, null, veriler2);
        //  db.insertOrThrow(TABLO,null,veriler);

    }
    public List<Mulk> listele(String user_id){
        String kolonlar[]={MULK_ID,USER_ID,BASLIK,TIP,ADRES,UCRET,ODA_SAYISI,KAT,ISITMA,BALKON,METRESI,RESIM,IL,ILCE,TUR};
        List<Mulk> liste= new ArrayList<Mulk>();

        String filtreARGUMANT[] ={user_id};
        Cursor c= db.query(TABLO_ADI,kolonlar,USER_ID + "=?",filtreARGUMANT,null,null,null);
        c.moveToFirst();
        if (c.getCount() >= 1){
        byte[] resim1 = c.getBlob(11);
        int Mid1=c.getInt(0);
        int Uid1=c.getInt(1);
        String baslik1=c.getString(2);
        String tip1 =c.getString(3);
        String adres1 =c.getString(4);
        int ucret1 =c.getInt(5);
        String oda1 =c.getString(6);
        String kat1 =c.getString(7);
        String isitma1 =c.getString(8);
        String balkon1 =c.getString(9);
        String metresi1 =c.getString(10);
        String il1 =c.getString(12);
        String ilce1 =c.getString(13);
        String tur1 =c.getString(14);

        Mulk m1=new Mulk();
        m1.setMulkid(Mid1);
        m1.setUserid(Uid1);
        m1.setBaslik(baslik1);
        m1.setTip(tip1);
        m1.setAdres(adres1);
        m1.setUcret(ucret1);
        m1.setOdasayisi(oda1);
        m1.setKat(kat1);
        m1.setIsitma(isitma1);
        m1.setBalkon(balkon1);
        m1.setMetre(metresi1);
        m1.setImage(resim1);
        m1.setIl(il1);
        m1.setIlce(ilce1);
        m1.setKiralikzamani(tur1);
        liste.add(m1);}
        while (c.moveToNext()){

            byte[] resim = c.getBlob(11);
            int Mid=c.getInt(0);
            int Uid=c.getInt(1);
            String baslik=c.getString(2);
            String tip =c.getString(3);
            String adres =c.getString(4);
            int ucret =c.getInt(5);
            String oda =c.getString(6);
            String kat =c.getString(7);
            String isitma =c.getString(8);
            String balkon =c.getString(9);
            String metresi =c.getString(10);
            String il =c.getString(12);
            String ilce =c.getString(13);
            String tur =c.getString(14);

            Mulk m=new Mulk();
            m.setMulkid(Mid);
            m.setUserid(Uid);
            m.setBaslik(baslik);
            m.setTip(tip);
            m.setAdres(adres);
            m.setUcret(ucret);
            m.setOdasayisi(oda);
            m.setKat(kat);
            m.setIsitma(isitma);
            m.setBalkon(balkon);
            m.setMetre(metresi);
            m.setImage(resim);
            m.setIl(il);
            m.setIlce(ilce);
            m.setKiralikzamani(tur);
            liste.add(m);
            System.out.println(ilce);
        }

        return liste;
    }
    public void kaydiSil(int silinecekID) {
        db.delete(TABLO_ADI, MULK_ID + "=" + silinecekID, null);
    }
    public kullanicilar queryUser(String email, String password) {


       kullanicilar user = null;

        Cursor cursor = db.query(TABLO, new String[]{
                        ID,AD,SOYAD,EMAIL, SIFRE,CEPTEL}, EMAIL + "=? and " + SIFRE + "=?",
                new String[]{email, password}, null, null, null, "1");
        if (cursor != null)
            cursor.moveToFirst();
        if (cursor != null && cursor.getCount() > 0) {
            user = new kullanicilar(cursor.getInt(0),cursor.getString(1), cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5));
        }
        cursor.close();
        // return user
        return user;
    }
    public kullanicilar queryid(String id) {


        kullanicilar user = null;

        Cursor cursor = db.query(TABLO, new String[]{
                        ID,AD,SOYAD,EMAIL, SIFRE,CEPTEL}, ID + "=?",
                new String[]{id}, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        if (cursor != null && cursor.getCount() > 0) {
            user = new kullanicilar(cursor.getInt(0),cursor.getString(1), cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5));
        }
        cursor.close();
        // return user
        return user;
    }
    public boolean uservarmi(String email) {
        String[] columns = {EMAIL};


        String selection = EMAIL + "=?";
        String[] selectionArgs = {email};

        Cursor cursor = db.query(TABLO, columns, selection, selectionArgs, null, null, null);
        int count = cursor.getCount();

        cursor.close();

        if (count > 0) {
            return true;
        } else {
            return false;
        }
    }
    public void kaydiGuncelle(int guncellenecekID, String ad, String soyad,String email,String pass,String cep) {
        // TODO Auto-generated method stub
        ContentValues cvGuncelle = new ContentValues();
        cvGuncelle.put(AD, ad);
        cvGuncelle.put(SOYAD ,soyad);
        cvGuncelle.put(EMAIL, email);
        cvGuncelle.put(SIFRE ,pass);
        cvGuncelle.put(CEPTEL, cep);

        db.update(TABLO, cvGuncelle, ID + "="
                + guncellenecekID, null);

    }
    public void MulkUpdate(int guncellenecekID, String baslik, String tip,String adres,int ucret,String odasayisi,String kat,String isitma,String balkon,String metresi,String il,String ilce,byte[] resim,String tur) {
        // TODO Auto-generated method stub
        System.out.println("guncellemedeki tur "+tur);
        ContentValues cvGuncelle = new ContentValues();
        cvGuncelle.put(BASLIK, baslik);
        cvGuncelle.put(TIP ,tip);
        cvGuncelle.put(ADRES, adres);
        cvGuncelle.put(UCRET ,ucret);
        cvGuncelle.put(ODA_SAYISI, odasayisi);
        cvGuncelle.put(KAT, kat);
        cvGuncelle.put(ISITMA, isitma);
        cvGuncelle.put(BALKON, balkon);
        cvGuncelle.put(METRESI, metresi);
        cvGuncelle.put(IL, il);
        cvGuncelle.put(ILCE, ilce);
        cvGuncelle.put(RESIM, resim);
        cvGuncelle.put(TUR, tur);

        db.update(TABLO_ADI, cvGuncelle, MULK_ID + "="
                + guncellenecekID, null);

    }




    public List<Mulk> querymulk(String userid,String tip, String kat,String balkon,String il,String ilce) {

        List<Mulk> liste= new ArrayList<Mulk>();
        Mulk m = null;
        byte[] resim;
        byte[] resim1;
        String istenilen[] = {MULK_ID,USER_ID,BASLIK,TIP,ADRES,UCRET,ODA_SAYISI,KAT,ISITMA,BALKON,METRESI,RESIM,IL,ILCE,TUR};
        String filtreARGUMANT[] ={userid,tip,kat,balkon,il,ilce};
         Cursor c = db.query(TABLO_ADI, istenilen, USER_ID + "=? and "+ TIP + "=? and " + KAT + "=? and " + BALKON + "=? and " + IL + "=? and "+ ILCE + "=?",
                filtreARGUMANT, null, null, null);
        c.moveToFirst();
        if (c.getCount() >= 1){
            int Mid1=c.getInt(0);
            int Uid1=c.getInt(1);
            String baslik1=c.getString(2);
            String Mtip1 =c.getString(3);
            String adres1 =c.getString(4);
            int ucret1 =c.getInt(5);
            String oda1 =c.getString(6);
            String Mkat1 =c.getString(7);
            String isitma1 =c.getString(8);
            String Mbalkon1 =c.getString(9);
            String metresi1 =c.getString(10);
            resim1 = c.getBlob(11);
            String Mil1 =c.getString(12);
            String Milce1 =c.getString(13);
            String Mtur1 =c.getString(14);


            Mulk mu1=new Mulk();
            mu1.setMulkid(Mid1);
            mu1.setUserid(Uid1);
            mu1.setBaslik(baslik1);
            mu1.setTip(Mtip1);
            mu1.setAdres(adres1);
            mu1.setUcret(ucret1);
            mu1.setOdasayisi(oda1);
            mu1.setKat(Mkat1);
            mu1.setIsitma(isitma1);
            mu1.setBalkon(Mbalkon1);
            mu1.setMetre(metresi1);
            mu1.setImage(resim1);
            mu1.setIl(Mil1);
            mu1.setIlce(Milce1);
            mu1.setKiralikzamani(Mtur1);
            liste.add(mu1);
        }
        while (c.moveToNext()){
            int Mid=c.getInt(0);
            int Uid=c.getInt(1);
            String baslik=c.getString(2);
            String Mtip =c.getString(3);
            String adres =c.getString(4);
            int ucret =c.getInt(5);
            String oda =c.getString(6);
            String Mkat =c.getString(7);
            String isitma =c.getString(8);
            String Mbalkon =c.getString(9);
            String metresi =c.getString(10);
            resim = c.getBlob(11);
            String Mil =c.getString(12);
            String Milce =c.getString(13);
            String Mtur =c.getString(14);


            Mulk mu=new Mulk();
            mu.setMulkid(Mid);
            mu.setUserid(Uid);
            mu.setBaslik(baslik);
            mu.setTip(Mtip);
            mu.setAdres(adres);
            mu.setUcret(ucret);
            mu.setOdasayisi(oda);
            mu.setKat(Mkat);
            mu.setIsitma(isitma);
            mu.setBalkon(Mbalkon);
            mu.setMetre(metresi);
            mu.setImage(resim);
            mu.setIl(Mil);
            mu.setIlce(Milce);
            mu.setKiralikzamani(Mtur);
            liste.add(mu);
        }
        System.out.println("verikaynağı dönen "+liste.size());
        c.close();
        return liste;
    }



}
