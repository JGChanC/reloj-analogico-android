package com.example.jgchan.examenmoviles;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Target;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class Dibujar extends View {
        Drawable imagenActual;
        Drawable [] imagenes;
        int centrado=100;
        int segundo=10;
        double giroMinutero=0, giroHora;
        int ancho=0, alto=0;
        int s=0,m=0,h=0;
        Context context;
    String PATH="";
    RelativeLayout rel;
    BitmapLruCache cache;
    ArrayList<Figura> listaFiguras;
    Figura figuraSeleccionada=null;


    private Resources mResources;
    Bitmap image=null;
    ProgressDialog mensaje;

        public Dibujar(Context context, final RelativeLayout rel, BitmapLruCache cache,ArrayList<Figura> listaFiguras , String id) {
            super(context);
            this.cache=cache;

            this.listaFiguras=listaFiguras;
            this.rel = rel;
            PATH=String.format("http://drink-app.tk/usuario/%s/foto_perfil", id);
            int[] nombres={
                    R.drawable.boprueba,
                    R.drawable.bominus1,
                    R.drawable.bominus2,
                    R.drawable.caratura1,
                    R.drawable.caratura2,
                    R.drawable.caratura3,
                    R.drawable.caratura4
            };

            this.context = context;


            mResources = getResources();

            imagenes = new Drawable[4];

            for(int i =0; i<imagenes.length;i++){
                //imagenes[i] = Drawable.createFromPath("../drawable"+nombres[i]);
                imagenes[i] = context.getResources().getDrawable(R.drawable.bominus1);
            }


            mensaje = new ProgressDialog(context);
            mensaje.setMessage("Actualizando datos, por favor espere...");
            image = BitmapFactory.decodeResource(getResources(), R.drawable.hennesy);

           // Thread hilo = new Thread(this);
            //hilo.start();

          /*  if (this.cache.get(String.valueOf(id))!=null) {
                image = this.cache.get(String.valueOf(id));
                setImage(image);
                Log.d("HayImagen", "Dibujar: ");
            }else{
                Log.d("Ejecuto", "Dibujar: "+id);
                new MiTarea().execute();
            }

          */



            Picasso.with(context)
                    .load(PATH)
                    .into(new com.squareup.picasso.Target() {
                        @Override
                        public void onBitmapLoaded(Bitmap imagen, Picasso.LoadedFrom from) {
                          //  setImage(imagen);
                            postInvalidate();
                            rel.postInvalidate();
                        }

                        @Override
                        public void onBitmapFailed(Drawable errorDrawable) {

                        }

                        @Override
                        public void onPrepareLoad(Drawable placeHolderDrawable) {

                        }
                    });

        }

        public void retornarColor(){
            for (Figura punto:
                 listaFiguras) {
                punto.setColor(getResources().getColor(R.color.marcador));
            }
        }


        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);



          /* Bitmap image = BitmapFactory.decodeResource(
                    mResources,
                    R.drawable.bominus1
            ); */

            //int ancho,alto; // figura
            this.ancho=canvas.getWidth(); //De la pantalla
            this.alto=canvas.getHeight(); //De la pantalla



            //imagenes[2].setBounds(0,0, ancho,alto);
            //imagenes[2].draw(canvas);

            if(image!=null)
              canvas.drawBitmap(image,null,new RectF(0, 0, ancho, alto-50),null);



         /*   imagenes[1].setBounds((ancho/2)-(imagenes[1].getIntrinsicWidth()-centrado),(alto/2)-(imagenes[1].getIntrinsicWidth()-centrado),imagenes[1].getIntrinsicWidth()+(ancho/2)-centrado,imagenes[1].getIntrinsicHeight()+(alto/2)-centrado);
           imagenes[1].draw(canvas);



            imagenes[3].setBounds((ancho/2)-(imagenes[3].getIntrinsicWidth()/2),(alto/2)-(imagenes[3].getIntrinsicWidth()/2),imagenes[3].getIntrinsicWidth()+(ancho/2)-(imagenes[3].getIntrinsicWidth()/2),imagenes[3].getIntrinsicHeight()+(alto/2)+(imagenes[3].getIntrinsicWidth()/2));
            canvas.rotate((float)((h*30)+(.5*m)-180),(ancho/2),(alto/2));
            imagenes[3].draw(canvas);


            imagenes[2].setBounds((ancho/2)-(imagenes[2].getIntrinsicWidth()/2),(alto/2)-(imagenes[2].getIntrinsicWidth()/2),imagenes[2].getIntrinsicWidth()+(ancho/2)-(imagenes[2].getIntrinsicWidth()/2),imagenes[2].getIntrinsicHeight()+(alto/2)+(imagenes[2].getIntrinsicWidth()/2));
            canvas.rotate((float)(6*m),(ancho/2),(alto/2));
            imagenes[2].draw(canvas);

        */  Paint pincel = new Paint();

            pincel.setStyle(Paint.Style.FILL);

            retornarColor();



            if(listaFiguras.size()<10){
                listaFiguras.get(0).setColor(getResources().getColor(R.color.resalto));
                listaFiguras.get(listaFiguras.size()-1).setColor(getResources().getColor(R.color.resalto));
            }


            for (Figura figura: listaFiguras) {
                pincel.setColor(figura.getColor());
                canvas.drawCircle(figura.getX(),figura.getY(),figura.getRadio(),pincel);
            }



            Path path = new Path();

           //   path.moveTo(listaFiguras.get(0).getX(),listaFiguras.get(0).getY()  -(ancho/2)); // Top

            pincel.setColor(Color.RED);
            pincel.setStyle(Paint.Style.STROKE);
            pincel.setStrokeWidth(5);

           // path.addCircle(ancho/2,alto/2, 150, Path.Direction.CW);

            path.moveTo(listaFiguras.get(0).getX(),listaFiguras.get(0).getY());
           for (int i=0;i<listaFiguras.size();i++){
               path.lineTo(listaFiguras.get(i).getX(),listaFiguras.get(i).getY());
           }
            path.lineTo(listaFiguras.get(listaFiguras.size()-1).getX(),listaFiguras.get(listaFiguras.size()-1).getY());
            path.close();

            canvas.drawPath(path,pincel);

        }

    public void setImage(Bitmap image) {
        this.image = image;
    }






}