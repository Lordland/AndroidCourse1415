package com.example.inmobiliaria;

import android.app.Activity;
import android.os.Bundle;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class EmpresaActivity  extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		
		 LinearLayout layout = new LinearLayout(this);
	     layout.setOrientation(LinearLayout.VERTICAL);
	     layout.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
	        		LayoutParams.MATCH_PARENT));
	        
	     ImageView imageViewEmpresa = new ImageView(this);
	     imageViewEmpresa.setImageResource(R.drawable.logo_empresa);
	     layout.addView(imageViewEmpresa);
	     
	     TextView textViewEmpresa = new TextView(this);
	     textViewEmpresa.setText("Iniciamos nuestras actividades en el....");
	     layout.addView(textViewEmpresa);
	     
	     setContentView(layout);
	     
		
		super.onCreate(savedInstanceState);
	}
	
	

}
