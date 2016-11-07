package Data.mgr;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class PoiOpenHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 2;
    private static final String Poi_TABLE = "Poi";
    private static final String Type_int =" INTEGER";
    private static final String DATABASE_NAME= "PoiData";
    private static final String Type_float =" REAL";
    private static final String Type_text =" TEXT";
    private static final String Poi_TABLE_CREATE =
                "CREATE TABLE " + Poi_TABLE + " (" +
    "id " + Type_int+ "PRIMARY KEY, "+ 
    "title" + Type_text + "," +
    "lat" + Type_float+"," +
    "lng"+Type_float+","+
    "elevation"+Type_int+","+
    "photo"+Type_text+","+
    "prio"+" INTEGER"+
    ")";

    public PoiOpenHelper (Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Poi_TABLE_CREATE);
    }

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + Poi_TABLE);
		onCreate(db);
	}
	
	
	
	
	
	// Adding new poi
	public void addPoi(poi POI) {
		SQLiteDatabase db = this.getWritableDatabase();
		 
	    ContentValues values = new ContentValues();
	    values.put("id", POI.getID()); //ID #
	    values.put("title", POI.getTitle()); //Title
	    values.put("lat", POI.getLat()); //Latitude
	    values.put("lng", POI.getLng()); //Longitude
	    values.put("elevation", POI.getElevation()); //Elevatoin
	    values.put("photo",POI.getPhoto());
	    db.insert(Poi_TABLE,null,values);
	    db.close();
	}
	 
	// Getting single poi
	public poi getPoi(int id) {
		 SQLiteDatabase db = this.getReadableDatabase();
		 
		    Cursor cursor = db.query(Poi_TABLE, new String[] { "id",
		            "title", "lat","lng","elevation","photo" }, "id" + "=?",
		            new String[] { String.valueOf(id) }, null, null, null);
		    if (cursor != null)
		        cursor.moveToFirst();
		 
		    poi POI = new poi(Integer.parseInt(cursor.getString(0)),
		            cursor.getString(1), Double.parseDouble(cursor.getString(2)),
		            Double.parseDouble(cursor.getString(3)),
		            Integer.parseInt(cursor.getString(4)),cursor.getString(5));
		    // return poi
		    return POI;
	}
	public List<String> getAllTitles() {
		List<String> contactList = new ArrayList<String>();
	    // Select All Query
	    String selectQuery = "SELECT  * FROM " + Poi_TABLE;
	 
	    SQLiteDatabase db = this.getWritableDatabase();
	    Cursor cursor = db.rawQuery(selectQuery, null);
	 
	    // looping through all rows and adding to list
	    if (cursor.moveToFirst()) {
	        do {
	            
	            contactList.add(cursor.getString(1));
	        } while (cursor.moveToNext());
	    }
	 
	    // return Poi list
	    return contactList;
	}
	
	public List<poi> getAllLocation() {
		List<poi> contactList = new ArrayList<poi>();
	    // Select All Query
	    String selectQuery = "SELECT  * FROM " + Poi_TABLE;
	 
	    SQLiteDatabase db = this.getWritableDatabase();
	    Cursor cursor = db.rawQuery(selectQuery, null);
	 
	    // looping through all rows and adding to list
	    if (cursor.moveToFirst()) {
	        do {
	            poi POI = new poi();
	            POI.setTitle(cursor.getString(1));
	            POI.setLat(Double.parseDouble(cursor.getString(2)));
	            POI.setLng(Double.parseDouble(cursor.getString(3)));
	            
	            // Adding contact to list
	            contactList.add(POI);
	        } while (cursor.moveToNext());
	    }
	 
	    // return Poi list
	    return contactList;
	}
    public void dropTable() {
    	SQLiteDatabase db = this.getWritableDatabase();
    	db.execSQL("DROP TABLE IF EXISTS " + Poi_TABLE);
    	 db.execSQL("DROP TABLE IF EXISTS " + Poi_TABLE);
        onCreate(db);
}

	// Getting All POI's
	public List<poi> getAllPoi() {
		List<poi> contactList = new ArrayList<poi>();
	    // Select All Query
	    String selectQuery = "SELECT  * FROM " + Poi_TABLE;
	 
	    SQLiteDatabase db = this.getWritableDatabase();
	    Cursor cursor = db.rawQuery(selectQuery, null);
	 
	    // looping through all rows and adding to list
	    if (cursor.moveToFirst()) {
	        do {
	            poi POI = new poi();
	            POI.setID(Integer.parseInt(cursor.getString(0)));
	            POI.setTitle(cursor.getString(1));
	            POI.setLat(Double.parseDouble(cursor.getString(2)));
	            POI.setLng(Double.parseDouble(cursor.getString(3)));
	            POI.setElevation(Integer.parseInt(cursor.getString(4)));
	            POI.setPhoto(cursor.getString(5));
	            // Adding contact to list
	            contactList.add(POI);
	        } while (cursor.moveToNext());
	    }
	 
	    // return Poi list
	    return contactList;
	}
	 
	// Getting Poi Count
	public int getPoiCount() {
		 String countQuery = "SELECT  * FROM " + Poi_TABLE;
	        SQLiteDatabase db = this.getReadableDatabase();
	        Cursor cursor = db.rawQuery(countQuery, null);
	        cursor.close();
	 
	        // return count
	        return cursor.getCount();
	}
	// Updating single Poi
	public int updatePoi(poi POI) {
		SQLiteDatabase db = this.getWritableDatabase();
		 
	    ContentValues values = new ContentValues();
	    values.put("id", POI.getID()); //ID #
	    values.put("title", POI.getTitle()); //Title
	    values.put("lat", POI.getLat()); //Latitude
	    values.put("lng", POI.getLng()); //Longitude
	    values.put("elevation", POI.getElevation()); //Elevatoin
	    values.put("photo", POI.getPhoto());
	 
	    // updating row
	    return db.update(Poi_TABLE, values, "id" + " = ?",
	            new String[] { String.valueOf(POI.getID()) });
	}
	 
	// Deleting single Poi
	public void deletePoi(poi POI) {
		SQLiteDatabase db = this.getWritableDatabase();
	    db.delete(Poi_TABLE, "id" + " = ?",
	            new String[] { String.valueOf(POI.getID()) });
	    db.close();
	}

}
