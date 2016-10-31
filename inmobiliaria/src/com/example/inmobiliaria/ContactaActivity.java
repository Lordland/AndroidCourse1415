package com.example.inmobiliaria;

import java.util.LinkedList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.InputType;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

public class ContactaActivity extends Activity {
	
	private Handler mostrarNotificacion = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			Toast.makeText(getApplicationContext(), msg.obj.toString(), 
					Toast.LENGTH_LONG).show();
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		LinearLayout layout = new LinearLayout(this);
		layout.setOrientation(LinearLayout.VERTICAL);
		layout.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT));

		final EditText editTextNombre = new EditText(this);
		editTextNombre.setHint("Nombre");
		editTextNombre.setTextSize(22);
		layout.addView(editTextNombre);

		final EditText editTextTelefono = new EditText(this);
		editTextTelefono.setHint("Telefono");
		editTextTelefono.setInputType(InputType.TYPE_CLASS_PHONE);
		editTextTelefono.setTextSize(22);
		layout.addView(editTextTelefono);

		List<String> lista = new LinkedList<String>();
		lista.add("Mañanas");
		lista.add("Tardes");
		lista.add("Noches");

		ArrayAdapter adapter = new ArrayAdapter(this,
				android.R.layout.simple_spinner_dropdown_item, lista);

		final Spinner spinner = new Spinner(this);
		spinner.setAdapter(adapter);
		layout.addView(spinner);

		Button botonEnviar = new Button(this);
		botonEnviar.setTextSize(25);
		botonEnviar.setText("Enviar");
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
		layout.addView(botonEnviar);
		setContentView(layout);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.contacta, menu);
		return true;
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
