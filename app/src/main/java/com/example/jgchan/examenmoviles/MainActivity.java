package com.example.jgchan.examenmoviles;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    String PATH="http://drink-app.tk/usuario/1/foto_perfil";
    ImageView im;
    Bitmap bi;
    ProgressDialog mensaje;
    Dibujar dib;
    Button btn;
    RelativeLayout canvas;
    BitmapCache cache;
    static BitmapLruCache cacheOtro;
    ArrayList<Figura> listaFiguras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //dib=new Dibujar(this);


        setContentView(R.layout.activity_main);
        canvas = (RelativeLayout)findViewById(R.id.canvas);
        btn = (Button)findViewById(R.id.btnEnviar);

        cacheOtro = new BitmapLruCache();
        Bitmap A =  BitmapFactory.decodeResource(getResources(), R.drawable.bominus1);
        cacheOtro.put("1", A);

        Bitmap B =  BitmapFactory.decodeResource(getResources(), R.drawable.bominus2);
        cacheOtro.put("2", B);

        listaFiguras = new ArrayList<Figura>();

        listaFiguras.add(new Figura(100,50,20, Color.YELLOW));
        listaFiguras.add(new Figura(400,50,40, Color.RED));
        listaFiguras.add(new Figura(300,350,50, Color.BLUE));

        dib = new Dibujar(this,canvas,cacheOtro,listaFiguras,"1");
        canvas.addView(dib);




        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(MainActivity.this,PruebaBitmapCacheActivity.class);
                startActivity(i);
            }
        });
        //setContentView(new Dibujar(this));
       // im=(ImageView)findViewById(R.id.imgPrueba);
        //downloadFile(PATH);


      //  getBitmapFromURL(PATH);

      /*  try {
           bi = Picasso.with(this).load(PATH).get();
        } catch (IOException e) {
            e.printStackTrace();
        }

       // im.setImageBitmap(bi);

        /*Picasso.with(this).load(PATH).into(new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
               //dib.setBitmap(bitmap);
                im.setImageBitmap(bitmap);
                im.postInvalidate();
            }

            @Override
            public void onBitmapFailed(Drawable errorDrawable) {
                Log.d(TAG, "onBitmapFailed: ");
            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {
            }
        });*/
    }


     public static Bitmap getBitmapFromURL(String src) {
        try {
            URL url = new URL(src);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            return myBitmap;
        } catch (IOException e) {
            // Log exception
            return null;
        }
    }

    Bitmap bmImg;
    void downloadFile(String fileUrl){
        URL myFileUrl =null;
        try {
            myFileUrl= new URL(fileUrl);
        } catch (MalformedURLException e) {
// TODO Auto-generated catch block
            e.printStackTrace();
        }
        try {
            HttpURLConnection conn= (HttpURLConnection)myFileUrl.openConnection();
            conn.setDoInput(true);
            conn.connect();
            int length = conn.getContentLength();
            int[] bitmapData =new int[length];
            byte[] bitmapData2 =new byte[length];
            InputStream is = conn.getInputStream();

            bmImg = BitmapFactory.decodeStream(is);
            im.setImageBitmap(bmImg);
            Log.d("Descarga", "downloadFile: Termino");

        } catch (IOException e) {
// TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    public void otro(View v){
        Intent i = new Intent(MainActivity.this,DeslizarActivity.class);
        startActivity(i);
    }

}




