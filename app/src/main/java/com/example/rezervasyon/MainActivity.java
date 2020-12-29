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

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    Button buttongiris,buttonkayit;
    kullanicilar user=new kullanicilar();
    SharedPreferences preferences;//preferences referansı
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttongiris =  findViewById(R.id.butongiris1);
        buttonkayit =  findViewById(R.id.butonkayit1);
        preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        editor = preferences.edit();
        verikaynagi veritabani= new verikaynagi(this);
        if(preferences.getBoolean("Klogin",false)){

          int id=  preferences.getInt("Kid",0);

            veritabani.ac_read();
            kullanicilar user = veritabani.queryid(String.valueOf(id));


            if (user!=null){

                Intent intent=new Intent(this,Anasayfa.class);
                intent.putExtra("gelenuser",user);
                editor.putBoolean("Klogin", true);
                editor.putInt("Kid", user.getUserid());
                editor.commit();//yapıl
                startActivity(intent);
            }

        }else{

            Intent intocan = new Intent(MainActivity.this, kayit.class);
            startActivity(intocan);
        }
        veritabani.kapat();
        buttongiris.setOnClickListener(this);
        final SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor1 = sharedPref.edit();
        editor1.putInt("id",0);
        editor1.commit();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.butongiris1:
                Intent intocan = new Intent(MainActivity.this, kayit.class);
                startActivity(intocan);
                break;
            case R.id.butonkayit1:
                Toast.makeText(getApplicationContext(),"Kayıt Oluşturuldu",Toast.LENGTH_LONG).show();
                break;
        }
    }
}
