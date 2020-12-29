package com.example.rezervasyon;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.ByteArrayInputStream;
import java.util.List;

public class ozeladaptor extends BaseAdapter {

    LayoutInflater layoutInflater;
    List<Mulk> list;
    Activity activity;
    public ozeladaptor(Activity activity,List<Mulk>mulkList){
        layoutInflater=(LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        list=mulkList;
        this.activity=activity;
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View satirview;
        satirview=layoutInflater.inflate(R.layout.satir,null);
        ImageView iv=(ImageView)satirview.findViewById(R.id.satirimageview);
        TextView baslik=(TextView)satirview.findViewById(R.id.baslik);
        TextView tip=(TextView)satirview.findViewById(R.id.tip);
        TextView oda=(TextView)satirview.findViewById(R.id.satirodasayisi);
        TextView metre=(TextView)satirview.findViewById(R.id.metrekare);
        TextView kat=(TextView)satirview.findViewById(R.id.kat);
        TextView isitma=(TextView)satirview.findViewById(R.id.isitma);
        TextView balkon=(TextView)satirview.findViewById(R.id.balkonlumu);
        TextView adres=(TextView)satirview.findViewById(R.id.adres);
        TextView il=(TextView)satirview.findViewById(R.id.iL);
        TextView ilceview=(TextView)satirview.findViewById(R.id.satirilce);
        TextView fiyatview=(TextView)satirview.findViewById(R.id.parasi);
        TextView kiralamaturuview=(TextView)satirview.findViewById(R.id.tur);
        Mulk m=list.get(position);
        ByteArrayInputStream imageStream = new ByteArrayInputStream(m.getImage());
        Bitmap theImage= BitmapFactory.decodeStream(imageStream);

        iv.setImageBitmap(theImage);
        baslik.setText(m.getBaslik());
        tip.setText(m.getTip());
        oda.setText(m.getOdasayisi());
        metre.setText(m.getMetre());
        kat.setText(m.getKat());
        isitma.setText(m.getIsitma());
        balkon.setText(m.getBalkon());
        adres.setText(m.getAdres());
        ilceview.setText(m.getIlce());
        il.setText(m.getIl());
        kiralamaturuview.setText(m.getKiralikzamani());
        fiyatview.setText( String.valueOf(m.getUcret()));



        return satirview;
    }
}
