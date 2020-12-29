package com.example.rezervasyon;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class mulkekle extends AppCompatActivity implements View.OnClickListener {
    final int REQUEST_CODE_GALLERY = 999;
    verikaynagi veritabani= new verikaynagi(this);
    Button resimsec;
    Button mulkekle;
    ImageView imageView;
    EditText baslik,adres,ucret,oda,ısıtma,metre;
    Spinner spinner,spinnerkat,spinnerbalkon,spinneril,spinnerilce,spinnertur;
    String[] tip = { "Ev", "Oda",};
    String[] katlar = { "1.Kat", "2.Kat","3.Kat","4.Kat"};
    String[] balkon = { "Var", "Yok",};
    String[] il = { "İstanbul", "Ankara","Konya"};
    String[] ilce = { "Esenyurt", "Mamak","Selçuklu"};
    String[] kiralamaturu = { "Günlük","Aylık"};
    byte[] byteArray;
    String encodeImage;
    Mulk mulk= new Mulk();
    kullanicilar gelenuser;
    int user_id;
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Bitmap originBitmap = null;
        Uri selectedImage = data.getData();

        InputStream imageStream;

        try
        {
            imageStream = getContentResolver().openInputStream(selectedImage);
            originBitmap = BitmapFactory.decodeStream(imageStream);
        }
        catch (FileNotFoundException ex)
        {
        }

        if(originBitmap !=null)
        {
            imageView.setImageBitmap(originBitmap);

            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            originBitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);

            byteArray = stream.toByteArray();

            encodeImage = Base64.encodeToString(byteArray, Base64.DEFAULT);

        }
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mulkekle);
        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
             user_id = bundle.getInt("userid");
            gelenuser = (kullanicilar) bundle.get("gelenuser");
        }

        resimsec =  findViewById(R.id.resimsec);
        resimsec.setOnClickListener(this);
        mulkekle =  findViewById(R.id.mulkekle);
        mulkekle.setOnClickListener(this);
        imageView=findViewById(R.id.imageView);
        baslik=findViewById(R.id.editbaslik);
        adres=findViewById(R.id.editadres);
        ucret=findViewById(R.id.editucret);
        oda=findViewById(R.id.editodasay);
        ısıtma=findViewById(R.id.editisitma);
        metre=findViewById(R.id.editmetre);
        spinner=findViewById(R.id.spinnermulk);


        spinnerlar();


    }



    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if(requestCode == REQUEST_CODE_GALLERY){
            if(grantResults.length >0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, REQUEST_CODE_GALLERY);
            }

            return;
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    public void ChooseImage()
    {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED) && !Environment.getExternalStorageState().equals(Environment.MEDIA_CHECKING))
        {
            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setType("image/*");
            startActivityForResult(intent, 1);
        }
        else
        {
            Toast.makeText(this, "Gerekli izinler verilmemiştir.", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.resimsec:
                ChooseImage();



                break;
            case  R.id.mulkekle:
                mulk.setUserid(user_id);
                mulk.setAdres(adres.getText().toString().trim());
                mulk.setBaslik(baslik.getText().toString().trim());
                mulk.setUcret(Integer.parseInt( ucret.getText().toString().trim()));
                mulk.setOdasayisi(oda.getText().toString().trim());
                mulk.setIsitma(ısıtma.getText().toString().trim());
                mulk.setMetre(metre.getText().toString().trim());
                mulk.setKat(spinnerkat.getSelectedItem().toString());
                mulk.setTip(spinner.getSelectedItem().toString());
                mulk.setBalkon(spinnerbalkon.getSelectedItem().toString());
                mulk.setIlce(spinnerilce.getSelectedItem().toString());
                mulk.setIl(spinneril.getSelectedItem().toString());
                mulk.setImage(byteArray);
                mulk.setKiralikzamani(spinnertur.getSelectedItem().toString());
                veritabani.ac();
                veritabani.mulk_ekle(mulk);
                veritabani.kapat();

                Toast.makeText(getApplicationContext(),"Mülk Eklendi.",Toast.LENGTH_LONG).show();
                Intent gonder=new Intent(this,Anasayfa.class);//yeni açaçağımız sayfa
                gonder.putExtra("veri1", (Bundle) null);
                gonder.putExtra("gelenuser",gelenuser);
                startActivity(gonder);

                break;
        }
    }

    private void spinnerlar(){

        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,tip);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(aa);

        spinnerkat=findViewById(R.id.spinnerkat);
        ArrayAdapter kat = new ArrayAdapter(this,android.R.layout.simple_spinner_item,katlar);
        kat.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerkat.setAdapter(kat);

        spinnerbalkon=findViewById(R.id.spinnerbalkon);
        ArrayAdapter balkonlu  = new ArrayAdapter(this,android.R.layout.simple_spinner_item,balkon);
        balkonlu.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerbalkon.setAdapter(balkonlu);

        spinneril=findViewById(R.id.spinneril);
        ArrayAdapter iller  = new ArrayAdapter(this,android.R.layout.simple_spinner_item,il);
        iller.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinneril.setAdapter(iller);

        spinnerilce=findViewById(R.id.spinnerilce);
        ArrayAdapter ilceler  = new ArrayAdapter(this,android.R.layout.simple_spinner_item,ilce);
        ilceler.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerilce.setAdapter(ilceler);


        spinnertur=findViewById(R.id.kiralikzamanispinner);
        ArrayAdapter tur  = new ArrayAdapter(this,android.R.layout.simple_spinner_item,kiralamaturu);
        ilceler.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnertur.setAdapter(tur);
    }
}