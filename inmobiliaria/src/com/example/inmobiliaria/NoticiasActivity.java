package com.example.inmobiliaria;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class NoticiasActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		ScrollView scroll = new ScrollView(this);
		
		LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
        		LayoutParams.MATCH_PARENT));
		
        String fecha = "15/01/15";
        String titulo = "Muere un desarrollador de android";
        String cuerpo = "Muere tras horas de trabajo sin parar";
        
        for(int i = 0; i < 20; i++)
        	layout.addView(getNoticia(fecha,titulo,cuerpo));
        
        scroll.addView(layout);
        setContentView(scroll);
	}
	
	
	private LinearLayout getNoticia(String fecha, String titulo, String cuerpo){
		
		TextView textViewFecha = new TextView(this);
		textViewFecha.setText(fecha);
		textViewFecha.setTextSize(10);
		
		TextView textViewTitulo = new TextView(this);
		textViewTitulo.setText(titulo);
		textViewTitulo.setTextSize(18);
		textViewTitulo.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, 
				LayoutParams.WRAP_CONTENT));
		textViewTitulo.setGravity(Gravity.CENTER);
		
		TextView textViewCuerpoNoticia = new TextView(this);
		textViewCuerpoNoticia.setText(cuerpo);
		
		LinearLayout layoutFechaYTitulo = new LinearLayout(this);
		layoutFechaYTitulo.setOrientation(LinearLayout.VERTICAL);
		layoutFechaYTitulo.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, 
				LayoutParams.WRAP_CONTENT));
		layoutFechaYTitulo.setBackgroundColor(Color.argb(255,55,72,232));
		
		layoutFechaYTitulo.addView(textViewFecha);
		layoutFechaYTitulo.addView(textViewTitulo);
		
		LinearLayout layoutPrincipal = new LinearLayout(this);
		layoutPrincipal.setOrientation(LinearLayout.VERTICAL);
		layoutFechaYTitulo.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, 
				LayoutParams.WRAP_CONTENT));
		layoutPrincipal.addView(layoutFechaYTitulo);
		
		layoutPrincipal.addView(textViewCuerpoNoticia);
		
		return layoutPrincipal;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.noticias, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
