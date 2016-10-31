package com.example.practica4;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Service;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.IBinder;

public class SubirPrecioService extends Service {

	 private Timer mTimer = null; 
	
	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public void onCreate() {
		this.mTimer = new Timer();
		  this.mTimer.scheduleAtFixedRate(
		    new TimerTask(){
		     @Override
		     public void run() {
		    	 PisosDB pisosDB = new PisosDB(getApplicationContext(),
		 				"PisosDB", null, 21);
		    	 subirPrecio(pisosDB);
		     }      
		    }
		    , 0, 1000 * 60);
		super.onCreate();
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		PisosDB pisosDB = new PisosDB(getApplicationContext(),
				"PisosDB", null, 21);
		subirPrecio(pisosDB);
		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}
	
	public void subirPrecio(PisosDB pisosDB){
		SQLiteDatabase db = pisosDB.getWritableDatabase();
		pisosDB.subirPrecio(db);
	}
	
	

}
