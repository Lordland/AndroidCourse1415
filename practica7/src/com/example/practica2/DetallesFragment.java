package com.example.practica2;

import java.util.LinkedList;
import java.util.List;






import java.util.Locale;

import android.app.Fragment;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class DetallesFragment extends Fragment implements OnInitListener{
	TextToSpeech tts;
	
	
	
	@Override
	public void onStart() {
		tts = new TextToSpeech(getActivity().getApplicationContext(),this);
		super.onStart();
	}

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
		
		 

			final TextView textDescripcion = (TextView) rootView.findViewById(R.id.textViewDescripcion);
			textDescripcion.setText(piso.getDescripcion());
			
			
			TextView textMetros = (TextView) rootView.findViewById(R.id.textViewMetros);
			textMetros.setText("Metros: "+ piso.getMetros());
			
			
			TextView textPrecio = (TextView) rootView.findViewById(R.id.textViewPrecio);
			textPrecio.setText("Euros: "+ piso.getPrecios());
			
			Button botonHablar = (Button) rootView.findViewById(R.id.buttonLeer);
			botonHablar.setOnClickListener(new View.OnClickListener(){

				@Override
				public void onClick(View v) {
					String texto = textDescripcion.getText().toString();
					tts.speak(texto, TextToSpeech.QUEUE_FLUSH, null);
				}
				
			});
		
		return rootView;
	}

	@Override
	public void onInit(int status) {
		if(status == TextToSpeech.SUCCESS){
			
			int result = tts.setLanguage(new Locale ("es"));
			if(result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED){
				Log.e("Error", "idioma error");
			}
			else{
				Toast.makeText(getActivity().getApplicationContext(), "TTS Listo", Toast.LENGTH_SHORT).show();
			}
		}
		else{
			Log.e("Error", "TTS error");
		}
		
	}
	
	

}
