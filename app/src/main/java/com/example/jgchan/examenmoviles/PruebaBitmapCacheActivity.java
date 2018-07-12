package com.example.jgchan.examenmoviles;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static android.graphics.Bitmap.Config.ALPHA_8;
import static com.example.jgchan.examenmoviles.MainActivity.cacheOtro;

public class PruebaBitmapCacheActivity extends AppCompatActivity implements View.OnTouchListener, View.OnClickListener {

    RelativeLayout rel;
    Dibujar dib;
    ArrayList<Figura> listaFiguras;
    Figura figuraSeleccionada=null;
    String mensaje="";
    FloatingActionButton fabAgregar,fabDrag,fabEliminar,fabRedraw;

    boolean  drag= true, agregar= false, borrar= false;
    Punto obj = new Punto();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prueba_bitmap_cache);



        rel = (RelativeLayout)findViewById(R.id.canvas);


        listaFiguras = new ArrayList<Figura>();


        listaFiguras.add(new Figura(266.419952f,302.099426f,30, getResources().getColor(R.color.marcador)));
        listaFiguras.add(new Figura(129.623032f,335.376648f,30,  getResources().getColor(R.color.marcador)));
        listaFiguras.add(new Figura(262.428284f,193.542694f,30,  getResources().getColor(R.color.marcador)));



        dib = new Dibujar(this,rel,cacheOtro, listaFiguras,"1");


        dib.setOnTouchListener(this);
        dib.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                v.getX();
                return true;
            }
        });
        rel.addView(dib);

        fabAgregar=(FloatingActionButton)findViewById(R.id.fabAgregar);
        fabDrag=(FloatingActionButton)findViewById(R.id.fabDrag);
        fabEliminar=(FloatingActionButton)findViewById(R.id.fabEliminar);
        fabRedraw=(FloatingActionButton)findViewById(R.id.fabRedraw);

        fabAgregar.setOnClickListener(this);
        fabDrag.setOnClickListener(this);
        fabEliminar.setOnClickListener(this);
        fabRedraw.setOnClickListener(this);

        /*

        btnElm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                borrar=true;
            }
        });

        btnAgre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                borrar=false;
            }
        });


        btnFast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //borrar=(borrar)? false:true
                ArrayList<Punto> reorden = obj.ordenarLineas(listaFiguras);
                listaFiguras.clear();
                for (Punto punto:
                        reorden  ) {
                    Log.d("Ordenar ", "onClick: "+punto.toString());
                    listaFiguras.add(new Figura(punto.getX(),punto.getY(),30, getResources().getColor(R.color.marcador)));
                }
               dib.invalidate();
            }
        });
        */


    }

    private float distancia(float x1, float y1,float x2, float y2){
        float resultado;

        resultado=((float)Math.sqrt((y2-y1)*(y2-y1) + (x2-x1)*(x2-x1) ));

        return resultado;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        int accion = event.getAction();
        float x,y, distancia;
        x=event.getX();
        y=event.getY();

        if(accion==MotionEvent.ACTION_DOWN){

            mensaje="DOWN";

            if(figuraSeleccionada==null) {
                //Buscar si ha tocado una pelota
                for (Figura figura : listaFiguras) {
                    distancia = distancia(figura.getX(), figura.getY(), x, y);
                    if (distancia <= figura.getRadio()) {
                        figuraSeleccionada = figura;
                        break;
                    }
                }
            }

            if(borrar && listaFiguras.size()>3){
                listaFiguras.remove(figuraSeleccionada);
                dib.invalidate();
            }



        }

        if(accion==MotionEvent.ACTION_UP) {

            if(figuraSeleccionada!=null){
                figuraSeleccionada=null;
            }else if(agregar){

                    if(listaFiguras.size()<10){
                        listaFiguras.add(new Figura(x,y,30, getResources().getColor(R.color.marcador)));
                        dib.invalidate();
                    }
            }

            mensaje="UP";
        }



        if(accion==MotionEvent.ACTION_MOVE){
            mensaje="MOVE";
            if(drag){
                if(figuraSeleccionada!=null) {
                    if((figuraSeleccionada.getX()-30>0 && figuraSeleccionada.getX()+30<dib.getWidth()) && (figuraSeleccionada.getY()-30>0 && dib.alto>figuraSeleccionada.getY()+30)) {
                        figuraSeleccionada.setX(x);
                        figuraSeleccionada.setY(y);
                        dib.invalidate();
                    }else{

                        if(!(figuraSeleccionada.getX()+35<dib.getWidth())){
                            figuraSeleccionada.setX(figuraSeleccionada.getX()-30);
                            Log.d("Arrastra", "onTouch: Entro1");
                        }else
                        if(!(dib.alto>figuraSeleccionada.getY()+35)) {
                            Log.d("Arrastra", "onTouch: Entro2");
                            figuraSeleccionada.setY(figuraSeleccionada.getY()-35);
                        }
                        else
                        if(((figuraSeleccionada.getX()-50)-dib.ancho<0) && figuraSeleccionada.getY()>30){
                            Log.d("Arrastra", "onTouch: Entro3");
                            figuraSeleccionada.setX(35);
                        }
                        else
                        if(((figuraSeleccionada.getY()-30)-dib.alto)<0){
                            Log.d("Arrastra", "onTouch: Entro4");
                            figuraSeleccionada.setY(35);
                        }



                        figuraSeleccionada=null;
                    }


                }
            }

        }

        dib.invalidate();
        return true;
    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        cambiarColor(id);

        if(id!=R.id.fabRedraw){
            reset();
            v.setBackgroundTintList(getResources().getColorStateList(R.color.colorPrimary));
        }


        switch (id){
            case R.id.fabDrag:
                    drag=true;
                break;
            case R.id.fabAgregar:
                    agregar=true;
                break;
            case R.id.fabEliminar:
                    borrar=true;
                break;
            case R.id.fabRedraw:
                redibujar();
                drag=true;
                fabDrag.setBackgroundTintList(getResources().getColorStateList(R.color.colorPrimary));
                break;
        }




    }

    private void redibujar() {
        ArrayList<Punto> reorden = obj.ordenarLineas(listaFiguras);
        listaFiguras.clear();
        for (Punto punto:
                reorden  ) {
            Log.d("Ordenar ", "onClick: "+punto.toString());
            listaFiguras.add(new Figura(punto.getX(),punto.getY(),30, getResources().getColor(R.color.marcador)));
        }
        dib.invalidate();
    }

    private void reset() {
        drag= false;
        agregar= false;
        borrar= false;
    }

    private void cambiarColor(int id) {
        if(id!=R.id.fabAgregar)
            fabAgregar.setBackgroundTintList(getResources().getColorStateList(R.color.deshabilitado));
        if(id!=R.id.fabDrag)
            fabDrag.setBackgroundTintList(getResources().getColorStateList(R.color.deshabilitado));
        if(id!=R.id.fabEliminar)
            fabEliminar.setBackgroundTintList(getResources().getColorStateList(R.color.deshabilitado));
        if(id!=R.id.fabRedraw)
            fabRedraw.setBackgroundTintList(getResources().getColorStateList(R.color.deshabilitado));
    }
}
