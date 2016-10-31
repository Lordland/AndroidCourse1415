package com.example.inmobiliaria;

import java.util.LinkedList;
import java.util.List;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup.LayoutParams;
import android.widget.Gallery;
import android.widget.LinearLayout;
import android.widget.TextView;

public class DetallesPisoActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		Piso piso = (Piso) getIntent().getExtras().getSerializable("IDpiso");
		
		LinearLayout layout = new LinearLayout(this);
		layout.setOrientation(LinearLayout.VERTICAL);
		layout.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
        		LayoutParams.MATCH_PARENT));
		
		TextView textTitulo = new TextView(this);
		textTitulo.setText(piso.getTitulo());
		layout.addView(textTitulo);
		
		if(piso.getImagenes().size() > 0){
			ViewPager galeria = new ViewPager(this);
			List<Drawable> imagenesPiso = new LinkedList<Drawable>();
			
			for(String imagen : piso.getImagenes()){
				int idImagen = getApplicationContext().getResources().getIdentifier(imagen, 
						"drawable", 
						getApplicationContext().getPackageName());
				imagenesPiso.add(getApplicationContext().getResources().getDrawable(idImagen));
			}
			
			galeria.setAdapter(new ImagenesPageAdapter(imagenesPiso,getApplicationContext()));
			layout.addView(galeria);
		}
		
		TextView textDescripcion = new TextView(this);
		textDescripcion.setText(piso.getDescripcion());
		layout.addView(textDescripcion);
		
		TextView textMetros = new TextView(this);
		textMetros.setText("Metros: "+ piso.getMetros());
		layout.addView(textMetros);
		
		TextView textPrecio = new TextView(this);
		textPrecio.setText("Euros: "+ piso.getPrecios());
		layout.addView(textPrecio);
		
		
		
		
		setContentView(layout);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.detalles_piso_activity, menu);
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
