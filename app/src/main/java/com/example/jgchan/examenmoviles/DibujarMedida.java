package com.example.jgchan.examenmoviles;

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
import android.view.View;
import android.widget.RelativeLayout;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DibujarMedida extends View {
        Drawable imagenActual;
        Drawable [] imagenes;
        float ancho, alto;
        Context context;
    String PATH="";
    RelativeLayout rel;
    BitmapLruCache cache;
    Nivel medidor;



    private Resources mResources;
    Bitmap image=null;
    Bitmap img_medidor;
    ProgressDialog mensaje;

        public DibujarMedida(Context context, final RelativeLayout rel, BitmapLruCache cache, Nivel medidor , String id) {
            super(context);
            this.cache=cache;

            this.medidor=medidor;
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
            image = BitmapFactory.decodeResource(getResources(), R.drawable.bominus1);
            img_medidor = BitmapFactory.decodeResource(getResources(), R.drawable.medidor);

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

            Paint pincel = new Paint();

            pincel.setStyle(Paint.Style.FILL);

            canvas.drawBitmap(image,null,new RectF(0, 0, ancho, alto-50),null);



              pincel.setColor(getResources().getColor(R.color.llenado));
              canvas.drawRect(new RectF(130, 0+medidor.getNivelY(), ancho-130, alto-60),pincel);
            canvas.drawBitmap(img_medidor,null,new RectF(0, 0+medidor.getNivelY()-50, ancho, 100+medidor.getNivelY()-50),null);



         /*   imagenes[1].setBounds((ancho/2)-(imagenes[1].getIntrinsicWidth()-centrado),(alto/2)-(imagenes[1].getIntrinsicWidth()-centrado),imagenes[1].getIntrinsicWidth()+(ancho/2)-centrado,imagenes[1].getIntrinsicHeight()+(alto/2)-centrado);
           imagenes[1].draw(canvas);



            imagenes[3].setBounds((ancho/2)-(imagenes[3].getIntrinsicWidth()/2),(alto/2)-(imagenes[3].getIntrinsicWidth()/2),imagenes[3].getIntrinsicWidth()+(ancho/2)-(imagenes[3].getIntrinsicWidth()/2),imagenes[3].getIntrinsicHeight()+(alto/2)+(imagenes[3].getIntrinsicWidth()/2));
            canvas.rotate((float)((h*30)+(.5*m)-180),(ancho/2),(alto/2));
            imagenes[3].draw(canvas);


            imagenes[2].setBounds((ancho/2)-(imagenes[2].getIntrinsicWidth()/2),(alto/2)-(imagenes[2].getIntrinsicWidth()/2),imagenes[2].getIntrinsicWidth()+(ancho/2)-(imagenes[2].getIntrinsicWidth()/2),imagenes[2].getIntrinsicHeight()+(alto/2)+(imagenes[2].getIntrinsicWidth()/2));
            canvas.rotate((float)(6*m),(ancho/2),(alto/2));
            imagenes[2].draw(canvas);

        */




        }

    public void setImage(Bitmap image) {
        this.image = image;
    }






}