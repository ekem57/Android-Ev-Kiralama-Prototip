package com.example.rezervasyon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class giris extends AppCompatActivity implements View.OnClickListener {
    verikaynagi veritabani= new verikaynagi(this);
    Button girisbuton;
    EditText email,sifre;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giris);
        email=findViewById(R.id.girisemail);
        sifre=findViewById(R.id.girissifre);
        girisbuton =  findViewById(R.id.girissayfasindakibuton);
        girisbuton.setOnClickListener(this);
        preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());//preferences objesi
        editor = preferences.edit();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.girissayfasindakibuton:

                veritabani.ac_read();
                kullanicilar user = veritabani.queryUser(email.getText().toString(), sifre.getText().toString());

                if (user!=null){
                    Toast.makeText(getApplicationContext(),"Tebrikler Giriş İşleminiz Gerçekleşti",Toast.LENGTH_LONG).show();
                    Intent intent=new Intent(this,Anasayfa.class);
                    intent.putExtra("gelenuser",user);
                    editor.putBoolean("Klogin", true);
                    editor.putInt("Kid", user.getUserid());
                    editor.commit();//yapıl
                    startActivity(intent);
                }else{
                    Toast.makeText(getApplicationContext(),"Email Yada Şifre Hatalı",Toast.LENGTH_LONG).show();
                }

                break;
        }
    }
}