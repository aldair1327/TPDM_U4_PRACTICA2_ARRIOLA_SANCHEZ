package com.example.aldair.u4_practica2_arriola_sanchez;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.media.Image;

public class Moscas {
    Bitmap imagen;
    int x, y, x1, x2,y1,y2;

    public Moscas(Lienzo este,int imagen, int posx, int posy){
        this.imagen = BitmapFactory.decodeResource(este.getResources(),imagen);
        x = posx;
        y = posy;

    }

    public void mover(int dedox,int dedoy){
        x = dedox ;
        y = dedoy ;
    }

    public void pintar(Canvas c, Paint p,String n){
        if(n.equals("jefe"))
            c.drawBitmap(imagen,x,y,p);
        else
            c.drawBitmap(imagen,x,y,p);
    }

    public boolean estaEnArea(int dedox, int dedoy) {
        int x2 = x + imagen.getWidth();
        int y2 = y + imagen.getHeight();

        if (dedox >= x && dedox <= x2 ) {
            if (dedoy >= y && dedoy <= y2) {
                return true;
            }

        }
        return false;
    }



}
