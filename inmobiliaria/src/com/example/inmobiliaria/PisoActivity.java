package com.example.inmobiliaria;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout.LayoutParams;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;

public class PisoActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		ScrollView scroll = new ScrollView(this);
		
		LinearLayout layout = new LinearLayout(this);
		layout.setOrientation(LinearLayout.VERTICAL);
		layout.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
        		LayoutParams.MATCH_PARENT));
		
		PisosDB pisosDB = new PisosDB(this,"PisosDB",null,21);
		List<Piso> pisos = pisosDB.getPisos();
		for(Piso piso : pisos)
			layout.addView(getMiniaturaPisos(piso));
		
		scroll.addView(layout);
		setContentView(scroll);
	}

	private LinearLayout getMiniaturaPisos(final Piso piso){
		ImageView imageViewPiso = new ImageView(this);
		
		int imagePisoID = getApplicationContext().getResources().getIdentifier(piso.getMiniatura(), 
				"drawable", 
				getApplicationContext().getPackageName());
		imageViewPiso.setImageResource(imagePisoID);
		
		Button buttonVerPiso = new Button(this);
		buttonVerPiso.setText(piso.getTitulo());
		buttonVerPiso.setBackgroundColor(Color.TRANSPARENT);
		buttonVerPiso.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intentDetallesPiso = new Intent(PisoActivity.this, DetallesPisoActivity.class);
				intentDetallesPiso.putExtra("IDpiso", piso);
				startActivity(intentDetallesPiso);
			}
		});
		
		LinearLayout layoutMiniaturaPiso = new LinearLayout(this);
		layoutMiniaturaPiso.setOrientation(LinearLayout.HORIZONTAL);
		layoutMiniaturaPiso.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
        		LayoutParams.MATCH_PARENT));
		layoutMiniaturaPiso.setBackgroundColor(Color.CYAN);
		
		layoutMiniaturaPiso.addView(imageViewPiso);
		layoutMiniaturaPiso.addView(buttonVerPiso);
		
		return layoutMiniaturaPiso;
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.piso, menu);
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
