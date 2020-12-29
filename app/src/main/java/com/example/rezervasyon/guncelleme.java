package com.example.rezervasyon;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Parcelable;
import android.util.Base64;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class guncelleme extends AppCompatActivity implements View.OnClickListener {

    final int REQUEST_CODE_GALLERY = 999;
    Mulk mulk;
    ImageView imageView;
    Button buton,butongucelle;
    EditText baslik,adres,ucret,oda,ısıtma,metre;
    Spinner spinnertip,spinnerkat,spinnerbalkon,spinneril,spinnerilce,spinnertur;
    verikaynagi veritabani= new verikaynagi(this);
    kullanicilar gelenuser;
    String[] tip = { "Ev", "Oda",};
    String[] katlar = { "1.Kat", "2.Kat","3.Kat","4.Kat"};
    String[] balkon = { "Var", "Yok",};
    String[] il = { "İstanbul", "Ankara","Konya"};
    String[] ilce = { "Esenyurt", "Mamak","Selçuklu"};
    String[] kiralamatur = {  "Günlük","Aylık"};
    int balkonposition=0;
    int turposition=0;
    int mulkposition=0;
    int  ilposition=0;
    int  ilceposition=0;
    byte[] byteArray=null;
    String encodeImage;
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
        setContentView(R.layout.activity_guncelleme);
        imageView=findViewById(R.id.imageView);

        spinnerlar();
        baslik=findViewById(R.id.editbaslik2);
        adres=findViewById(R.id.editadres2);
        ucret=findViewById(R.id.editucret2);
        oda=findViewById(R.id.editodasay2);
        ısıtma=findViewById(R.id.editisitma2);
        buton=findViewById(R.id.mulkguncelle);
        buton.setOnClickListener(this);
        butongucelle=findViewById(R.id.resimguncelle);
        butongucelle.setOnClickListener(this);
        metre=findViewById(R.id.editmetre2);
        imageView=findViewById(R.id.imageView2);
        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            mulk = (Mulk) bundle.get("gelenmulk");
            gelenuser = (kullanicilar) bundle.get("gelenuser");
        }
        System.out.println("mulk bilgileri "+ mulk.getKiralikzamani().toString());
        metre.setText(mulk.getMetre());
        baslik.setText(mulk.getBaslik());
        adres.setText(mulk.getAdres());
        ucret.setText(String.valueOf(mulk.getUcret()));
        oda.setText(mulk.getOdasayisi());
        ısıtma.setText(mulk.getIsitma());

        byteArray=mulk.getImage();
        ByteArrayInputStream imageStream = new ByteArrayInputStream(mulk.getImage());
        Bitmap theImage= BitmapFactory.decodeStream(imageStream);
        String a=mulk.getKat().substring(0,1);
        spinnerkat.setSelection(Integer.parseInt(a)-1);
        if (mulk.getBalkon().equals("Var")){
            balkonposition=0;
        }else{
            balkonposition=1;
        }
        if (mulk.getKiralikzamani().equals("Günlük")){
            turposition=0;
        }else{
            turposition=1;
        }
        if (mulk.getTip().equals("Ev")){
            mulkposition=0;
        }else{
            mulkposition=1;
        }
        if (mulk.getIl().equals("İstanbul")){
            ilposition=0;

        }else if(mulk.getIl().equals("Ankara")){
            ilposition=1;
        }else{
            ilposition=2;
        }
        if (mulk.getIlce().equals("Esenyurt")){
            ilceposition=0;
        }else if(mulk.getIlce().equals("Mamak")){
            ilceposition=1;
        }else{
            ilceposition=2;
        }
        spinnerbalkon.setSelection(balkonposition);
        spinnertip.setSelection(mulkposition);
        spinneril.setSelection(ilposition);
        spinnerilce.setSelection(ilceposition);
        spinnertur.setSelection(turposition);

        imageView.setImageBitmap(theImage);


    }

    private void spinnerlar(){
        spinnertip=findViewById(R.id.spinnermulk2);
        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,tip);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnertip.setAdapter(aa);

        spinnerkat=findViewById(R.id.spinnerkat2);
        ArrayAdapter kat = new ArrayAdapter(this,android.R.layout.simple_spinner_item,katlar);
        kat.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerkat.setAdapter(kat);

        spinnerbalkon=findViewById(R.id.spinnerbalkon2);
        ArrayAdapter balkonlu  = new ArrayAdapter(this,android.R.layout.simple_spinner_item,balkon);
        balkonlu.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerbalkon.setAdapter(balkonlu);

        spinneril=findViewById(R.id.spinneril2);
        ArrayAdapter iller  = new ArrayAdapter(this,android.R.layout.simple_spinner_item,il);
        iller.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinneril.setAdapter(iller);

        spinnerilce=findViewById(R.id.spinnerilce2);
        ArrayAdapter ilceler  = new ArrayAdapter(this,android.R.layout.simple_spinner_item,ilce);
        ilceler.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerilce.setAdapter(ilceler);

        spinnertur=findViewById(R.id.kiralamaturguncelleme);
        ArrayAdapter tur  = new ArrayAdapter(this,android.R.layout.simple_spinner_item,kiralamatur);
        ilceler.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnertur.setAdapter(tur);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.mulkguncelle:
                if (byteArray.equals(mulk.getImage())){
                    veritabani.ac();
                    veritabani.MulkUpdate(mulk.getMulkid(),baslik.getText().toString().trim(),spinnertip.getSelectedItem().toString().trim(),
                            adres.getText().toString().trim(),Integer.parseInt(ucret.getText().toString()),oda.getText().toString().trim()
                            ,spinnerkat.getSelectedItem().toString().trim(),ısıtma.getText().toString().trim()
                            ,spinnerbalkon.getSelectedItem().toString().trim(),metre.getText().toString().trim(),
                            spinneril.getSelectedItem().toString().trim(),spinnerilce.getSelectedItem().toString().trim(),mulk.getImage(),spinnertur.getSelectedItem().toString());
                    veritabani.kapat();
                    Toast.makeText(getApplicationContext(),"Mülkünüz Güncellenmiştir.",Toast.LENGTH_LONG).show();
                    Intent gonder=new Intent(this,Anasayfa.class);//yeni açaçağımız sayfa
                    gonder.putExtra("veri1", (Bundle) null);
                    gonder.putExtra("veri2",spinnerkat.getSelectedItem().toString());
                    gonder.putExtra("veri3",spinnerbalkon.getSelectedItem().toString());
                    gonder.putExtra("veri4",spinneril.getSelectedItem().toString());
                    gonder.putExtra("veri5",spinnerilce.getSelectedItem().toString());
                    gonder.putExtra("gelenuser",gelenuser);
                    recreate();
                    startActivity(gonder);
                }else{
                    veritabani.ac();
                    veritabani.MulkUpdate(mulk.getMulkid(),baslik.getText().toString().trim(),spinnertip.getSelectedItem().toString().trim(),
                            adres.getText().toString().trim(),Integer.parseInt(ucret.getText().toString()),oda.getText().toString().trim()
                            ,spinnerkat.getSelectedItem().toString().trim(),ısıtma.getText().toString().trim()
                            ,spinnerbalkon.getSelectedItem().toString().trim(),metre.getText().toString().trim(),
                            spinneril.getSelectedItem().toString().trim(),spinnerilce.getSelectedItem().toString().trim(),byteArray,spinnertur.getSelectedItem().toString());
                    veritabani.kapat();
                    Toast.makeText(getApplicationContext(),"Mülkünüz Güncellenmiştir.",Toast.LENGTH_LONG).show();
                    Toast.makeText(getApplicationContext(),spinnertur.getSelectedItem().toString(),Toast.LENGTH_LONG).show();
                    Intent gonder=new Intent(this,Anasayfa.class);//yeni açaçağımız sayfa
                    gonder.putExtra("veri1", (Bundle) null);
                    gonder.putExtra("veri2",spinnerkat.getSelectedItem().toString());
                    gonder.putExtra("veri3",spinnerbalkon.getSelectedItem().toString());
                    gonder.putExtra("veri4",spinneril.getSelectedItem().toString());
                    gonder.putExtra("veri5",spinnerilce.getSelectedItem().toString());
                    gonder.putExtra("gelenuser",gelenuser);
                    startActivity(gonder);

                }

                break;
            case R.id.resimguncelle:
                ChooseImage();
                break;
        }
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
            Toast.makeText(this, "No activity found to perform this task", Toast.LENGTH_SHORT).show();
        }
    }
}




