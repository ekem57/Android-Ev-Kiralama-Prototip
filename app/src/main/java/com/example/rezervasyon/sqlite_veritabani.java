package com.example.rezervasyon;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class sqlite_veritabani  extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "rezervasyon_db";
    public static final int DATABASE_VERSION = 1;


    public static final String GIRIS_TABLE = "users";
    public static final String ROW_ID_GIRIS = "userid";
    public static final String PLAKA_GIRIS = "ad";
    public static final String GIRIS_SAATI_GIRIS = "soyad";
    public static final String GIRIS_TARIHI_GIRIS = "email";
    public static final String TOPLAM_TUTAR_GIRIS = "sifre";
    public static final String ICERIDEMI = "ceptel";



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

    public sqlite_veritabani(Context c){
        super(c, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + GIRIS_TABLE + "(" + ROW_ID_GIRIS + " INTEGER PRIMARY KEY, " + PLAKA_GIRIS + " TEXT NOT NULL, " + GIRIS_SAATI_GIRIS + " TEXT NOT NULL, " + GIRIS_TARIHI_GIRIS + " TEXT NOT NULL, " + TOPLAM_TUTAR_GIRIS + " TEXT NOT NULL,  " + ICERIDEMI + " TEXT NOT NULL   )");
        db.execSQL("CREATE TABLE " + TABLO_ADI + "(" + MULK_ID+ " INTEGER PRIMARY KEY, " + USER_ID + " INTEGER NOT NULL, " + BASLIK + " TEXT NOT NULL, " + TIP + " TEXT NOT NULL, " + ADRES + " TEXT NOT NULL,  " + UCRET + " INTEGER NOT NULL, " + ODA_SAYISI + " TEXT NOT NULL, " + KAT + " TEXT NOT NULL ," + ISITMA + " TEXT NOT NULL, " + BALKON + " TEXT NOT NULL,  " + METRESI + " TEXT NOT NULL,  " + RESIM + " BLOB NOT NULL," + IL + " TEXT NOT NULL," + ILCE + " TEXT NOT NULL," + TUR +" TEXT NOT NULL )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists users");
        db.execSQL("drop table if exists mulkler");
        onCreate(db);
    }


}
