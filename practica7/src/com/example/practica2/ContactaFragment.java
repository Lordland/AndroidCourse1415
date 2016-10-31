package com.example.practica2;

import java.util.LinkedList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class ContactaFragment extends Fragment{

	
	private Handler mostrarNotificacion = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			Toast.makeText(getActivity(), msg.obj.toString(), 
					Toast.LENGTH_LONG).show();
		}
	};
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = null;
		// TODO Auto-generated method stub
		rootView = inflater.inflate(R.layout.fragment_contacta, container, false);
		
		final EditText editTextNombre = (EditText) rootView.findViewById(R.id.editTextNombre);
		editTextNombre.setHint("Nombre");
		editTextNombre.setTextSize(22);
		
		final EditText editTextTelefono = (EditText) rootView.findViewById(R.id.editTextTelefono);
		editTextTelefono.setHint("Telefono");
		editTextTelefono.setInputType(InputType.TYPE_CLASS_PHONE);
		editTextTelefono.setTextSize(22);
		
		List<String> lista = new LinkedList<String>();
		lista.add("Mañanas");
		lista.add("Tardes");
		lista.add("Noches");

		ArrayAdapter adapter = new ArrayAdapter(getActivity().getApplicationContext(),
				android.R.layout.simple_spinner_dropdown_item, lista);

		final Spinner spinner = (Spinner) rootView.findViewById(R.id.spinnerTurnos);
		spinner.setAdapter(adapter);
		
		Button botonEnviar = (Button) rootView.findViewById(R.id.buttonEnviar);
		botonEnviar.setTextSize(25);
		botonEnviar.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				String url = "http://www.mderg.com/inm/servicio.php";
				String parametros = editTextNombre.getText() + ";"
						+ editTextTelefono.getText() + ";"
						+ spinner.getSelectedItem().toString();

				new Thread(new PeticionServicioWeb(url, parametros)).start();

			}
		});
		
		
		
		return rootView;
	}
	
	private class PeticionServicioWeb implements Runnable {
		private String url;
		private String info;

		public PeticionServicioWeb(String url, String info) {
			this.url = url;
			this.info = info;
		}

		@Override
		public void run() {
			try {
				HttpClient httpClient = new DefaultHttpClient();
				HttpPost post = new HttpPost(url);

				post.setHeader("Content-type", "application/json");
				StringEntity contenido = new StringEntity(info);
				post.setEntity(contenido);
				
				HttpResponse httpResponse = httpClient.execute(post);
				String respuesta = EntityUtils.toString(httpResponse
						.getEntity());

				if (respuesta.equals("YES")) {
					Message msg = new Message();
					msg.obj = "Información enviada";
					mostrarNotificacion.sendMessage(msg);
				}
			} catch (Exception e) {
				Log.v("Excepción", "Es:" + e.toString());
			}
		}
	}

}
