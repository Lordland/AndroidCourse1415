package com.example.practica2;

import java.util.LinkedList;
import java.util.List;




import android.app.Fragment;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;

public class DetallesFragment extends Fragment{
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View rootView = inflater.inflate(R.layout.fragment_detalles, container, false);
		
		Piso piso = (Piso) getArguments().getSerializable("IDpiso");
		
		 TextView textTitulo = (TextView) rootView.findViewById(R.id.textViewTitulo);
		 textTitulo.setText(piso.getTitulo());
		 
		 LinearLayout layout = (LinearLayout) rootView.findViewById(R.id.linLayPager);
		 
		 if(piso.getImagenes().size() > 0){
				ViewPager galeria = new ViewPager(getActivity().getApplicationContext());
				List<Drawable> imagenesPiso = new LinkedList<Drawable>();
				
				for(String imagen : piso.getImagenes()){
					int idImagen = getActivity().getApplicationContext().getResources().getIdentifier(imagen, 
							"drawable", 
							getActivity().getApplicationContext().getPackageName());
					imagenesPiso.add(getActivity().getApplicationContext().getResources().getDrawable(idImagen));
				}
				
				galeria.setAdapter(new ImagenesPageAdapter(imagenesPiso,getActivity().getApplicationContext()));
				galeria.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, imagenesPiso.get(0).getMinimumHeight()));
				layout.addView(galeria);
		 }
		
		 

			TextView textDescripcion = (TextView) rootView.findViewById(R.id.textViewDescripcion);
			textDescripcion.setText(piso.getDescripcion());
			
			
			TextView textMetros = (TextView) rootView.findViewById(R.id.textViewMetros);
			textMetros.setText("Metros: "+ piso.getMetros());
			
			
			TextView textPrecio = (TextView) rootView.findViewById(R.id.textViewPrecio);
			textPrecio.setText("Euros: "+ piso.getPrecios());
			
		
		return rootView;
	}
	
	

}
