package com.example.rezervasyon;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class profil extends AppCompatActivity implements View.OnClickListener  {
    kullanicilar user;
    Button buttonguncelle;
    EditText ad,soyad,sifre,email,ceptel;
    verikaynagi veritabani= new verikaynagi(this);
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);
        preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        editor = preferences.edit();
        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            user = (kullanicilar) bundle.get("gelenuser");
        }

        Toolbar toolbar2 = findViewById(R.id.toolbarprofil);
        setSupportActionBar(toolbar2);
        ad=findViewById(R.id.editadP);
        soyad=findViewById(R.id.editsoyadP);
        sifre=findViewById(R.id.editsifreP);
        email=findViewById(R.id.editemailP);
        ceptel=findViewById(R.id.editceptelP);
        buttonguncelle =  findViewById(R.id.butonPguncelle);
        buttonguncelle.setOnClickListener(this);
        ad.setText(user.getUsername());
        soyad.setText(user.getUsersurname());
        sifre.setText(user.getUserpassword());
        email.setText(user.getUsermail());
        ceptel.setText(user.getUserphone());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu2,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id=item.getItemId();
        if (id==R.id.cikis){
            Intent intent=new Intent(this,kayit.class);
            editor.putBoolean("Klogin", false);
            editor.putInt("Kid", 0);
            editor.commit();
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.butonPguncelle:

                veritabani.ac();
                veritabani.kaydiGuncelle(user.getUserid(),ad.getText().toString().trim(),soyad.getText().toString().trim(),email.getText().toString().trim(),sifre.getText().toString().trim(),ceptel.getText().toString().trim());
                veritabani.kapat();
                recreate();
                Toast.makeText(getApplicationContext(),"Bilgileriniz başarı ile güncellenmiştir.",Toast.LENGTH_LONG).show();

                break;
        }
    }
}