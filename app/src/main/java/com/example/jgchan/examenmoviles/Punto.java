package com.example.jgchan.examenmoviles;

import java.util.ArrayList;

public class Punto {
	float x;
	float y;
	
	public Punto() {
		
	}
	
	public Punto(float x, float y) {
		super();
		this.x = x;
		this.y = y;
	}

	public float getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return String.format("%f,%f", this.x,this.y);
	}

	private float distancia(Punto puntoActual,Punto puntoProximo){
		float resultado;

		resultado=((float)Math.sqrt(((puntoProximo.y-puntoActual.y)*(puntoProximo.y-puntoActual.y)) + ((puntoProximo.x-puntoActual.x)*(puntoProximo.x-puntoActual.x))));

		return resultado;
	}

	public ArrayList<Punto> ordenarLineas(ArrayList<Figura> puntos) {

		ArrayList<Punto> figura = new ArrayList<>();
		ArrayList<Punto> reordenado = new ArrayList<>();
		Punto Actual= new Punto(0,0);

		for (Figura punto : puntos) {
			figura.add(new Punto(punto.getX(),punto.getY()));
		}


		for (Punto punto : figura) {
			if(Actual.y<=punto.y)
				Actual=punto;

		}

		reordenado.add(Actual);
		figura.remove(Actual);

		Punto Aux = new Punto(100000,100000);

		while(!figura.isEmpty()) {
			for (Punto punto : figura) {
				if((Actual.y==punto.y) || (Actual.x==punto.x)){
					Aux=punto;
					break;
				}

				if(distancia(Actual, punto)<=distancia(Actual, Aux)) {
					Aux=punto;
				}
			}

			reordenado.add(Aux);
			figura.remove(Aux);
			Actual=Aux;
			if(!figura.isEmpty())Aux=figura.get(0);

		}


		return reordenado;




	}



}
