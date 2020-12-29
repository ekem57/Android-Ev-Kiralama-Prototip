package com.example.rezervasyon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class kayit extends AppCompatActivity implements View.OnClickListener {
    Button buttonkayit;
    EditText ad,soyad,sifre,sifre2,email,ceptel;
    TextView yonlendir;
    kullanicilar user= new kullanicilar();
    verikaynagi veritabani= new verikaynagi(this);
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kayit);
        preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());//preferences objesi
        editor = preferences.edit();
        ad=findViewById(R.id.editad);
        yonlendir=findViewById(R.id.yonlendirme);
        soyad=findViewById(R.id.editsoyad);
        sifre=findViewById(R.id.editsifre);
        sifre2=findViewById(R.id.editsifreT);
        email=findViewById(R.id.editemail);
        ceptel=findViewById(R.id.editceptel);
        buttonkayit =  findViewById(R.id.butonkayit2);
        buttonkayit.setOnClickListener(this);
        yonlendir.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.butonkayit2:
                if (emptyValidation()){
                    user.setUsername(ad.getText().toString().trim());
                    user.setUsersurname(soyad.getText().toString().trim());
                    user.setUsermail(email.getText().toString().trim());
                    user.setUserpassword(sifre.getText().toString().trim());
                    user.setUserphone(ceptel.getText().toString().trim());

                    veritabani.ac();

                    boolean varmi= veritabani.uservarmi(email.getText().toString().trim());
                    System.out.println(varmi);
                    if (varmi){
                        Toast.makeText(getApplicationContext(),"Daha önce bu email adresi kullanılmıştır.",Toast.LENGTH_LONG).show();

                    }else{
                        veritabani.user_ekle(user);
                        Toast.makeText(getApplicationContext(),"Tebrikler Kayıt İşleminiz Gerçekleşti",Toast.LENGTH_LONG).show();
                        Intent intent=new Intent(this,giris.class);
                        editor.putBoolean("Klogin", true);
                        editor.putInt("Kid", 0);
                        editor.commit();
                        startActivity(intent);
                    }
                    veritabani.kapat();
                }else{
                    Toast.makeText(getApplicationContext(),"Lütfen tüm alanları doldurunuz.",Toast.LENGTH_LONG).show();
                }

                break;
            case R.id.yonlendirme:
                Intent intent=new Intent(this,giris.class);
                startActivity(intent);

                break;

        }
    }

    private boolean emptyValidation() {
        if (TextUtils.isEmpty(ad.getText().toString()) || TextUtils.isEmpty(soyad.getText().toString()) || TextUtils.isEmpty(sifre.getText().toString())  || TextUtils.isEmpty(sifre2.getText().toString()) || TextUtils.isEmpty(email.getText().toString()) || TextUtils.isEmpty(ceptel.getText().toString())  ) {
            return false;
        }else {
            return true;
        }
    }
}