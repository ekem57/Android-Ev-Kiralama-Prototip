package com.example.rezervasyon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.List;

public class filtre extends AppCompatActivity implements View.OnClickListener {
    Spinner spinnermulk,spinnerkat,spinnerbalkon,spinneril,spinnerilce;
    Button filtre,filtrekaldirbuton;
    verikaynagi veritabani= new verikaynagi(this);
    String[] tip = {"Ev", "Oda",};
    String[] katlar = { "1.Kat", "2.Kat","3.Kat","4.Kat"};
    String[] balkon = { "Var", "Yok",};
    String[] il = { "Ankara","Konya","İstanbul"};
    String[] ilce = {"Mamak","Selçuklu","Esenyurt",};


    kullanicilar gelenuser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filtre);
        spinnerlar();
        filtre =  findViewById(R.id.filtrele);
        filtrekaldirbuton =  findViewById(R.id.filtrekaldir);
        filtre.setOnClickListener(this);
        filtrekaldirbuton.setOnClickListener(this);
        Intent intent = getIntent();
        gelenuser = intent.getParcelableExtra("gelenuser");
    }

    private void spinnerlar(){
        spinnermulk=findViewById(R.id.filtermulk);
        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,tip);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnermulk.setAdapter(aa);

        spinnerkat=findViewById(R.id.filterkat);
        ArrayAdapter kat = new ArrayAdapter(this,android.R.layout.simple_spinner_item,katlar);
        kat.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerkat.setAdapter(kat);

        spinnerbalkon=findViewById(R.id.filterbalkon);
        ArrayAdapter balkonlu  = new ArrayAdapter(this,android.R.layout.simple_spinner_item,balkon);
        balkonlu.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerbalkon.setAdapter(balkonlu);

        spinneril=findViewById(R.id.filteril);
        ArrayAdapter iller  = new ArrayAdapter(this,android.R.layout.simple_spinner_item,il);
        iller.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinneril.setAdapter(iller);

        spinnerilce=findViewById(R.id.filterilce);
        ArrayAdapter ilceler  = new ArrayAdapter(this,android.R.layout.simple_spinner_item,ilce);
        ilceler.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerilce.setAdapter(ilceler);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.filtrele:

                veritabani.ac();
              List<Mulk> mulkList= veritabani.querymulk(Integer.toString(gelenuser.getUserid()),spinnermulk.getSelectedItem().toString(),spinnerkat.getSelectedItem().toString(),spinnerbalkon.getSelectedItem().toString(),spinneril.getSelectedItem().toString(),spinnerilce.getSelectedItem().toString());
              System.out.println(mulkList.size()+" bu filtredeki arama");
                veritabani.kapat();
                Intent gonder=new Intent(this,Anasayfa.class);//yeni açaçağımız sayfa
                gonder.putExtra("veri1",spinnermulk.getSelectedItem().toString());
                gonder.putExtra("veri2",spinnerkat.getSelectedItem().toString());
                gonder.putExtra("veri3",spinnerbalkon.getSelectedItem().toString());
                gonder.putExtra("veri4",spinneril.getSelectedItem().toString());
                gonder.putExtra("veri5",spinnerilce.getSelectedItem().toString());
                gonder.putExtra("gelenuser",gelenuser);
                startActivity(gonder);

                System.out.println("Mulk listesi "+mulkList.size());
                break;
            case R.id.filtrekaldir:


                Intent gonder2=new Intent(this,Anasayfa.class);//yeni açaçağımız sayfa
                gonder2.putExtra("veri1", (Parcelable[]) null);
                gonder2.putExtra("veri2",spinnerkat.getSelectedItem().toString());
                gonder2.putExtra("veri3",spinnerbalkon.getSelectedItem().toString());
                gonder2.putExtra("veri4",spinneril.getSelectedItem().toString());
                gonder2.putExtra("veri5",spinnerilce.getSelectedItem().toString());
                gonder2.putExtra("gelenuser",gelenuser);
                startActivity(gonder2);


                break;
        }
    }
}


