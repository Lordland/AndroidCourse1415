package com.example.practica8;

import java.io.IOException;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity {
	Button enviar;
	EditText campo1;
	EditText campo2;
	TextView resultado;
	Handler mHandler = new Handler();
	
	
	
	class HiloPeticionSoap extends Thread {

		@Override
		public void run() {
			final String NAMESPACE = "http://www.parasoft.com/wsdl/calculator/" ;
			final String URL = "http://ws1.parasoft.com/glue/calculator" ;
			final String METHOD_NAME = "add";
			final String SOAP_ACTION = "add";
			
			Float cosa1 = Float.parseFloat(campo1.getText().toString());
			Float cosa2 = Float.parseFloat(campo2.getText().toString());
			
			SoapObject peticion = new SoapObject(NAMESPACE, METHOD_NAME);
			peticion.addProperty("x", cosa1);
			peticion.addProperty("y", cosa2);
			SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
			
			envelope.dotNet = true;
			envelope.setOutputSoapObject(peticion);
			
			HttpTransportSE transporte = new HttpTransportSE(URL);
			try {
				transporte.call(SOAP_ACTION, envelope);
				SoapPrimitive resultadoxml = (SoapPrimitive) envelope.getResponse();
				final String suma = resultadoxml.toString();
				mHandler.post(new Runnable() {

					@Override
					public void run() {
						resultado.setText(suma);
						
					}
					
				});
				
			} catch (IOException e) {
				e.printStackTrace();
			} catch (XmlPullParserException e) {
				e.printStackTrace();
			}
			
			
			
			
		}
		
	}

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		enviar = (Button) findViewById(R.id.button1);
		campo1 = (EditText) findViewById(R.id.editTextSuma1);
		campo2 = (EditText) findViewById(R.id.editTextSuma2);
		resultado = (TextView) findViewById(R.id.textViewResultado);
		
		enviar.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				new HiloPeticionSoap().start();
				
			}
			
		});
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
