package com.example.practica4;

import com.example.practica2.R;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;

public class NoticiasFragment extends Fragment {
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View rootView = null;
		
		rootView = inflater.inflate(R.layout.fragment_noticias, container, false);
		
		LinearLayout layout = (LinearLayout) rootView.findViewById(R.id.linLayNoticias);
		
		String fecha = "15/01/15";
        String titulo = "Muere un desarrollador de android";
        String cuerpo = "Muere tras horas de trabajo sin parar";
		
		for(int i = 0; i < 20; i++)
        	layout.addView(getNoticia(fecha,titulo,cuerpo));
		
		return rootView;
	}
	
	
private LinearLayout getNoticia(String fecha, String titulo, String cuerpo){
		
		TextView textViewFecha = new TextView(getActivity().getApplicationContext());
		textViewFecha.setText(fecha);
		textViewFecha.setTextSize(10);
		
		TextView textViewTitulo = new TextView(getActivity().getApplicationContext());
		textViewTitulo.setText(titulo);
		textViewTitulo.setTextSize(18);
		textViewTitulo.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, 
				LayoutParams.WRAP_CONTENT));
		textViewTitulo.setGravity(Gravity.CENTER);
		
		TextView textViewCuerpoNoticia = new TextView(getActivity().getApplicationContext());
		textViewCuerpoNoticia.setText(cuerpo);
		
		LinearLayout layoutFechaYTitulo = new LinearLayout(getActivity().getApplicationContext());
		layoutFechaYTitulo.setOrientation(LinearLayout.VERTICAL);
		layoutFechaYTitulo.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, 
				LayoutParams.WRAP_CONTENT));
		layoutFechaYTitulo.setBackgroundColor(Color.argb(255,55,72,232));
		
		layoutFechaYTitulo.addView(textViewFecha);
		layoutFechaYTitulo.addView(textViewTitulo);
		
		LinearLayout layoutPrincipal = new LinearLayout(getActivity().getApplicationContext());
		layoutPrincipal.setOrientation(LinearLayout.VERTICAL);
		layoutFechaYTitulo.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, 
				LayoutParams.WRAP_CONTENT));
		layoutPrincipal.addView(layoutFechaYTitulo);
		
		layoutPrincipal.addView(textViewCuerpoNoticia);
		
		return layoutPrincipal;
	}


}
