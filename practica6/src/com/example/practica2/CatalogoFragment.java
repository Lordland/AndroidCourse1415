package com.example.practica2;

import java.util.List;


import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

public class CatalogoFragment extends Fragment {

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View rootView = inflater.inflate(R.layout.fragment_catalogo, container,
				false);

		LinearLayout layout = (LinearLayout) rootView
				.findViewById(R.id.linLayCatalogo);

		PisosDB pisosDB = new PisosDB(getActivity().getApplicationContext(),
				"PisosDB", null, 21);
		List<Piso> pisos = pisosDB.getPisos();
		for (Piso piso : pisos)
			layout.addView(getMiniaturaPisos(piso));

		return rootView;

	}

	private LinearLayout getMiniaturaPisos(final Piso piso) {
		ImageView imageViewPiso = new ImageView(getActivity()
				.getApplicationContext());

		int imagePisoID = getActivity()
				.getApplicationContext()
				.getResources()
				.getIdentifier(piso.getMiniatura(), "drawable",
						getActivity().getApplicationContext().getPackageName());
		imageViewPiso.setImageResource(imagePisoID);

		Button buttonVerPiso = new Button(getActivity().getApplicationContext());
		buttonVerPiso.setText(piso.getTitulo());
		buttonVerPiso.setBackgroundColor(Color.TRANSPARENT);
		buttonVerPiso.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Bundle data = new Bundle();
				data.putSerializable("IDpiso", piso);
				Fragment fragment = new DetallesFragment();
				fragment.setArguments(data);
				FragmentTransaction ft = getFragmentManager()
						.beginTransaction();
				ft.replace(R.id.container, fragment).addToBackStack("fragment_catalogo").commit();
			}
		});

		LinearLayout layoutMiniaturaPiso = new LinearLayout(getActivity()
				.getApplicationContext());
		layoutMiniaturaPiso.setOrientation(LinearLayout.HORIZONTAL);
		layoutMiniaturaPiso.setLayoutParams(new LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		layoutMiniaturaPiso.setBackgroundColor(Color.CYAN);

		layoutMiniaturaPiso.addView(imageViewPiso);
		layoutMiniaturaPiso.addView(buttonVerPiso);

		return layoutMiniaturaPiso;

	}

}
