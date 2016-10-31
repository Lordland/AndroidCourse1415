package com.example.practica4;

import java.util.LinkedList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class PisosDB extends SQLiteOpenHelper{

	public PisosDB(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("CREATE TABLE Pisos (id INTEGER, miniatura TEXT, titulo TEXT, descripcion TEXT, metros INTEGER, precio INTEGER)");
		db.execSQL("CREATE TABLE Imagenes (idPiso INTEGER, imagen TEXT)");

		db.execSQL("INSERT INTO Pisos (id, miniatura, titulo, descripcion , metros,  precio) VALUES (1,'piso1','Amplio piso, 3 dormitorios, 2 baños',' 3 dormitorios amplios, 2 baños, salon , terraza en el salon cocina con lavadero, , muchisima luz, calefaccion central incluida en los gastos de comunidad, plaza de garaje opcional',75,100000)");
		db.execSQL("INSERT INTO Pisos (id, miniatura, titulo, descripcion , metros,  precio) VALUES (2,'piso2','Piso exterior, muy luminoso. Dos dormitorios','Piso exterior, muy luminoso. Dos dormitorios, un baño.. Calle Gandía. Piso exterior, amplio y luminoso, 2 dormitorios, 1 baño. Amueblado, cocina equipada con electrodomésticos.',115,210000)");
		db.execSQL("INSERT INTO Pisos (id, miniatura, titulo, descripcion , metros,  precio) VALUES (3,'piso3','Amplio piso de 3 dorm. Y 2 baños. Plaza de garaje','Amplio piso de 3 dorm. Y 2 baños. Plaza de garaje. Calle Puerto de Canencia. Gran piso exterior, impecable, muy luminoso. Totalmente amueblado, 3 amplios dormitorios, baño y aseo.',115,210000)");

		db.execSQL("INSERT INTO Imagenes (idPiso, imagen) VALUES (1,'piso1_1')");
		db.execSQL("INSERT INTO Imagenes (idPiso, imagen) VALUES (1,'piso1_2')");
		db.execSQL("INSERT INTO Imagenes (idPiso, imagen) VALUES (1,'piso1_3')");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int newVersion, int previousVersion) {
		db.execSQL("DROP TABLE IF EXISTS Pisos");
		db.execSQL("DROP TABLE IF EXISTS Imagenes");
		
		onCreate(db);
	}
	
	
	public void subirPrecio(SQLiteDatabase db){
		db.execSQL("UPDATE Pisos SET precio = precio + 1000");
	}
	
	public List<Piso> getPisos(){
		SQLiteDatabase db = getWritableDatabase();
		
		List<Piso> listaPisos= new LinkedList<Piso>();
		if(db != null){
			Cursor c = db.rawQuery("SELECT * FROM Pisos", null);
			
			while(c.moveToNext()){
				
				Cursor ci = db.rawQuery("SELECT * FROM Imagenes WHERE idPiso = "+c.getInt(0), null);
				List<String> imagenes = new LinkedList<String>();
				
				while(ci.moveToNext()){
					imagenes.add(ci.getString(1));
				}
				
				listaPisos.add(new Piso(c.getInt(0), c.getString(1),c.getString(2), c.getString(3), 
						c.getInt(4),c.getInt(5), imagenes));
			}
		}
		return listaPisos;
	}


}
