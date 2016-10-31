package com.example.practica2;

import java.util.List;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

public class ImagenesPageAdapter extends PagerAdapter{

	private final List<Drawable> imagenes;
	private final Context context;
	
	
	
	public ImagenesPageAdapter(List<Drawable> imagenes, Context context) {
		super();
		this.imagenes = imagenes;
		this.context = context;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return imagenes.size();
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		// TODO Auto-generated method stub
		return arg0 == ((View)arg1);
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		((ViewPager) container).removeView((View) object);
	}

	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		LinearLayout layout = new LinearLayout(context);
		
		ImageView imagen = new ImageView(context);
		imagen.setImageDrawable(imagenes.get(position));
		
		layout.addView(imagen);
		
		container.addView(layout);
		container.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, 
				imagenes.get(position).getIntrinsicHeight()));

		
		return layout;
	}

}
