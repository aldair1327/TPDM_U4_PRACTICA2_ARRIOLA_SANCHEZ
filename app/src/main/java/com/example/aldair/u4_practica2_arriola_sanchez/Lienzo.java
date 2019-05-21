package com.example.aldair.u4_practica2_arriola_sanchez;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

public class Lienzo extends View {
    Thread hilo;

    Moscas mosca, moscardon, puntero;

    int tiempo = 60;
    int tiempojefe = 10;
    int puntos = 0;
    int vidasjefe = 6;


    public Lienzo(Context context) {
        super(context);
        mosca = new Moscas(this, R.drawable.mosca, 0, 0);
        moscardon = new Moscas(this, R.drawable.moscardon, 0, 0);
        puntero = null;
        hilo = new Thread() {

            public void run() {
                while (true) {
                    if (puntos >= 30 && vidasjefe == 0)
                        return;
                    if (puntos <= 30) {
                        tiempo--;

                    } else {
                        tiempojefe--;

                    }
                    try {
                        sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    postInvalidate();
                }
            }
        };
        hilo.start();
    }

    protected void onDraw(Canvas c) {
        Paint p = new Paint();
        p.setColor(Color.BLACK);
        p.setTextSize(50);

        if (vidasjefe == 0) {
            p.setColor(Color.BLUE);
            c.drawText("Felicidades has ganado!", 300, 960, p);

            return;
        } else if (tiempo <= 0 || tiempojefe <= 0) {
            p.setColor(Color.RED);
            c.drawText("Se acabo el tiempo :(", 300, 900, p);
            c.drawText("Suerte para la proxima", 300, 960, p);
            return;
        }


        if (puntos <= 30) {
            mosca.pintar(c, p, "normal");
            c.drawText("Puntuación: " + puntos + "  Tiempo: " + tiempo, 60, 60, p);

        } else {
            moscardon.pintar(c, p, "jefe");
            c.drawText("Vidas Jefe: " + vidasjefe + "  Tiempo: " + tiempojefe, 60, 60, p);
        }
    }

    public boolean onTouchEvent(MotionEvent me) {
        //el evento ontouchevent permite detectar los toques
        //de uno o mas dedos que se hacen en el area de dibujo
        /*me.getAction() = accion: presiono,soltar,mover
                       pos x pos y*/

        int accion = me.getAction();
        int posx = (int) me.getX();
        int posy = (int) me.getY();


        if (vidasjefe != 0) {
            switch (accion) {
                case MotionEvent.ACTION_DOWN:
                    //presiono
                    if (mosca.estaEnArea(posx, posy) && puntos <= 30) {
                        mosca.mover((int) (Math.random() * (getWidth() - mosca.imagen.getWidth())),
                                (int) (Math.random() * (getHeight() - mosca.imagen.getHeight())));

                        puntos++;
                    }
                    if (moscardon.estaEnArea(posx, posy) && puntos >= 30 && vidasjefe >= 0) {
                        moscardon.mover((int) (Math.random() * (getWidth() - moscardon.imagen.getWidth())),
                                (int) (Math.random() * (getHeight() - moscardon.imagen.getHeight())));
                        vidasjefe--;
                        puntos++;
                    }

                    break;
                case MotionEvent.ACTION_MOVE:
                    break;

                case MotionEvent.ACTION_UP:

                    break;

            }
        }
            invalidate();
            return true;


    }
}
