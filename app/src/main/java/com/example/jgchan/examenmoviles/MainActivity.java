package com.example.jgchan.examenmoviles;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new Dibujar(this));
    }


    public class Dibujar extends View implements Runnable{
        Drawable imagenActual;
        Drawable [] imagenes;
        int centrado=100;
        int segundo=10;
        double giroMinutero=0, giroHora;
        int ancho=0, alto=0;
        int s=0,m=0,h=0;

        public Dibujar(Context context) {
            super(context);
            int[] nombres={
                    R.drawable.caratura1,
                    R.drawable.caratura2,
                    R.drawable.caratura3,
                    R.drawable.caratura4
            };

            imagenes = new Drawable[4];

            for(int i =0; i<imagenes.length;i++){
                //imagenes[i] = Drawable.createFromPath("../drawable"+nombres[i]);
                imagenes[i] = context.getResources().getDrawable(nombres[i]);
            }



            Thread hilo = new Thread(this);
            hilo.start();
        }


        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);

            //int ancho,alto; // figura
            this.ancho=canvas.getWidth(); //De la pantalla
            this.alto=canvas.getHeight(); //De la pantalla





            imagenes[0].setBounds((ancho/2)-imagenes[0].getIntrinsicWidth()+centrado,(alto/2)-imagenes[0].getIntrinsicWidth()+centrado, imagenes[0].getIntrinsicWidth()-centrado+(ancho/2),imagenes[0].getIntrinsicHeight()-centrado+(alto/2));
            imagenes[0].draw(canvas);

            imagenes[1].setBounds((ancho/2)-(imagenes[1].getIntrinsicWidth()-centrado),(alto/2)-(imagenes[1].getIntrinsicWidth()-centrado),imagenes[1].getIntrinsicWidth()+(ancho/2)-centrado,imagenes[1].getIntrinsicHeight()+(alto/2)-centrado);
           imagenes[1].draw(canvas);



            imagenes[3].setBounds((ancho/2)-(imagenes[3].getIntrinsicWidth()/2),(alto/2)-(imagenes[3].getIntrinsicWidth()/2),imagenes[3].getIntrinsicWidth()+(ancho/2)-(imagenes[3].getIntrinsicWidth()/2),imagenes[3].getIntrinsicHeight()+(alto/2)+(imagenes[3].getIntrinsicWidth()/2));
            canvas.rotate((float)((h*30)+(.5*m)-180),(ancho/2),(alto/2));
            imagenes[3].draw(canvas);


            imagenes[2].setBounds((ancho/2)-(imagenes[2].getIntrinsicWidth()/2),(alto/2)-(imagenes[2].getIntrinsicWidth()/2),imagenes[2].getIntrinsicWidth()+(ancho/2)-(imagenes[2].getIntrinsicWidth()/2),imagenes[2].getIntrinsicHeight()+(alto/2)+(imagenes[2].getIntrinsicWidth()/2));
            canvas.rotate((float)(6*m),(ancho/2),(alto/2));
            imagenes[2].draw(canvas);



        }

        @Override
        public void run() {

            while(true){

                 try {
                   s++;
                   if(s>59){
                      m++;
                      s=0;
                   }

                     if(m>59) {
                         h++;
                         m=0;
                     }

                     if(h>12){
                         h=0;
                     }

                    Thread.sleep(16);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                postInvalidate(); //Para Redibujar si se usan hilos
            }

        }
    }

}
