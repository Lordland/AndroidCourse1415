package com.example.inmobiliaria;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageButton;
import android.widget.LinearLayout;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        LinearLayout layout = new LinearLayout(this);
        layout.setPadding(20, 20, 20, 20);
        layout.setBackgroundResource(R.drawable.fondo);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
        		LayoutParams.MATCH_PARENT));
        
        ImageButton imageButtonEmpresa = new ImageButton(this);
        imageButtonEmpresa.setBackgroundResource(R.drawable.boton_empresa);
        imageButtonEmpresa.setOnClickListener(new View.OnClickListener() {
        	@Override
        	public void onClick(View v){
        		Intent intentEmpresaActivity = new Intent(MainActivity.this, EmpresaActivity.class);
        		startActivity(intentEmpresaActivity);
        	}
        });
        
        ImageButton imageButtonNoticias = new ImageButton(this);
        imageButtonNoticias.setBackgroundResource(R.drawable.boton_noticias);
        imageButtonNoticias.setOnClickListener(new View.OnClickListener() {
        	@Override
        	public void onClick(View v){
        		Intent intentNoticiasActivity = new Intent(MainActivity.this, NoticiasActivity.class);
        		startActivity(intentNoticiasActivity);
        	}
        });
        
        
        ImageButton imageButtonPisos = new ImageButton(this);
        imageButtonPisos.setBackgroundResource(R.drawable.boton_catalogo);
        imageButtonPisos.setOnClickListener(new View.OnClickListener() {
        	@Override
        	public void onClick(View v){
        		Intent intentPisosActivity = new Intent(MainActivity.this, PisoActivity.class);
        		startActivity(intentPisosActivity);
        	}
        });
        
        ImageButton imageButtonContacta = new ImageButton(this);
        imageButtonContacta.setBackgroundResource(R.drawable.boton_contacta);
        imageButtonContacta.setOnClickListener(new View.OnClickListener() {
        	@Override
        	public void onClick(View v){
        		Intent intentContactaActivity = new Intent(MainActivity.this, ContactaActivity.class);
        		startActivity(intentContactaActivity);
        	}
        });
        
        layout.addView(imageButtonEmpresa);
        layout.addView(imageButtonNoticias);
        layout.addView(imageButtonPisos);
        layout.addView(imageButtonContacta);
        
        setContentView(layout);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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
