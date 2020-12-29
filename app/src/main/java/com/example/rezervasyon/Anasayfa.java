package com.example.rezervasyon;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class Anasayfa extends AppCompatActivity implements  View.OnClickListener {
     Button anasayfabuton;
     FloatingActionButton actionButton;
     SwipeMenuListView listView;
     kullanicilar gelenuser;
     verikaynagi vk=new verikaynagi(this);
     String veri1,veri2,veri3,veri4,veri5,veri6;
    List<Mulk> m= new ArrayList<Mulk>();
    boolean deneme=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        gelenuser = intent.getParcelableExtra("gelenuser");

        veri1= (String) getIntent().getExtras().get("veri1");
        veri2= (String) getIntent().getExtras().get("veri2");
        veri3= (String) getIntent().getExtras().get("veri3");
        veri4= (String) getIntent().getExtras().get("veri4");
        veri5= (String) getIntent().getExtras().get("veri5");
        veri6= (String) getIntent().getExtras().get("veri6");


        setContentView(R.layout.activity_anasayfa);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        actionButton=findViewById(R.id.floatbuton);

        listView = (SwipeMenuListView) findViewById(R.id.mulklist2);

        if (veri1==null){
            vk.ac_read();
            System.out.println("gelen id"+gelenuser.getUserid());
            m=vk.listele(String.valueOf(gelenuser.getUserid()));
            System.out.println("ggelen  liste"+m.size());
            vk.kapat();
        }else{
            vk.ac();
             m= vk.querymulk(Integer.toString(gelenuser.getUserid()),veri1,veri2,veri3,veri4,veri5);
            vk.kapat();

        }


        ozeladaptor adaptor=new ozeladaptor(this,m);
        listView.setAdapter(adaptor);

        SwipeMenuCreator creator = new SwipeMenuCreator() {

            @Override
            public void create(SwipeMenu menu) {

                SwipeMenuItem openItem = new SwipeMenuItem(getApplicationContext());
                openItem.setBackground(new ColorDrawable(Color.rgb(0xC9, 0xC9, 0xCE)));
                openItem.setWidth(280);
                openItem.setTitle("Güncelle");
                openItem.setTitleSize(18);
                openItem.setTitleColor(Color.BLACK);
                menu.addMenuItem(openItem);

                SwipeMenuItem deleteItem = new SwipeMenuItem(getApplicationContext());
                deleteItem.setBackground(new ColorDrawable(Color.rgb(0xF9, 0x3F, 0x25)));
                deleteItem.setWidth(280);
                deleteItem.setTitle("Sil");
                deleteItem.setIcon(R.drawable.ic_baseline_delete_24);
                openItem.setTitleSize(18);
                menu.addMenuItem(deleteItem);



            }
        };
        listView.setMenuCreator(creator);

        listView.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                switch (index) {
                    case 0:
                        Mulk gelenmulk=m.get(position);
                        Intent intent=new Intent(Anasayfa.this,guncelleme.class);
                        intent.putExtra("gelenmulk",  gelenmulk);
                        intent.putExtra("gelenuser",  gelenuser);
                        startActivity(intent);
                        recreate();
                        break;
                    case 1:

                     int silinecek_id = m.get(position).getMulkid();
                     vk.ac();
                     vk.kaydiSil(silinecek_id);
                     vk.kapat();
                     recreate();

                        break;
                }
                // false : close the menu; true : not close the menu
                return false;
            }
        });


        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent i;
                i = new Intent(Anasayfa.this, mulkekle.class);
                i.putExtra("userid",gelenuser.getUserid());
                i.putExtra("gelenuser",  gelenuser);
                startActivity(i);


            }
        });




    }

    protected void onResume(){
        vk.ac_read();
        super.onResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id=item.getItemId();
        if (id==R.id.profil_icon){
            vk.ac_read();
            kullanicilar user = vk.queryid(String.valueOf(gelenuser.getUserid()));


            if (user!=null){

                Intent intent2=new Intent(this,profil.class);
                intent2.putExtra("gelenuser",user);
                startActivity(intent2);
            }


        }
        else{


            Intent intent=new Intent(this,filtre.class);
            intent.putExtra("gelenuser",gelenuser);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);

    }

    @Override
    public void onClick(View v) {

    }
}