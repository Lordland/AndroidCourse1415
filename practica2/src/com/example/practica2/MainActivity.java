package com.example.practica2;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;
import android.os.Build;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}

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

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {

		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = null;

			rootView = inflater.inflate(R.layout.fragment_main, container,
					false);

			ImageButton contacta = (ImageButton) rootView
					.findViewById(R.id.imageButtonContacta);
			ImageButton empresa = (ImageButton) rootView
					.findViewById(R.id.imageButtonEmpresa);
			ImageButton catalogo = (ImageButton) rootView
					.findViewById(R.id.imageButtonCatalogo);
			ImageButton noticias = (ImageButton) rootView
					.findViewById(R.id.imageButtonNoticias);

			empresa.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					FragmentTransaction ft = getFragmentManager()
							.beginTransaction();
					ft.replace(R.id.container, new EmpresaFragment()).addToBackStack("fragment_main").commit();
				}
			});
			noticias.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					FragmentTransaction ft = getFragmentManager()
							.beginTransaction();
					ft.replace(R.id.container, new NoticiasFragment()).addToBackStack("fragment_main").commit();
				}
			});
			contacta.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					FragmentTransaction ft = getFragmentManager()
							.beginTransaction();
					ft.replace(R.id.container, new ContactaFragment()).addToBackStack("fragment_main").commit();
				}
			});
			catalogo.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					Fragment catalogo = new CatalogoFragment();
					FragmentTransaction ft = getFragmentManager()
							.beginTransaction();
					ft.replace(R.id.container, catalogo).addToBackStack("fragment_main").commit();
				}
			});

			return rootView;
		}
	}
}
