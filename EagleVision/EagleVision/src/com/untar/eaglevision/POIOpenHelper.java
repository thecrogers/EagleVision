package com.untar.eaglevision;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class POIOpenHelper extends SQLiteOpenHelper {

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

    POIOpenHelper (Context context) {
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
        public void addPOI(POI poi) {
                SQLiteDatabase db = this.getWritableDatabase();
                 
            ContentValues values = new ContentValues();
            values.put("id", poi.getID()); //ID #
            values.put("title", poi.getTitle()); //Title
            values.put("lat", poi.getLat()); //Latitude
            values.put("lng", poi.getLng()); //Longitude
            values.put("elevation", poi.getElevation()); //Elevatoin
            values.put("photo",poi.getPhoto());
            db.insert(Poi_TABLE,null,values);
            db.close();
        }
         
        // Getting single poi
        public POI getPoi(int id) {
                 SQLiteDatabase db = this.getReadableDatabase();
                 
                    Cursor cursor = db.query(Poi_TABLE, new String[] { "id",
                            "title", "lat","lng","elevation","photo" }, "id" + "=?",
                            new String[] { String.valueOf(id) }, null, null, null);
                    if (cursor != null)
                        cursor.moveToFirst();
                 
                    POI poi = new POI(Integer.parseInt(cursor.getString(0)),
                            cursor.getString(1), Double.parseDouble(cursor.getString(2)),
                            Double.parseDouble(cursor.getString(3)),
                            Integer.parseInt(cursor.getString(4)),cursor.getString(5));
                    // return poi
                    return poi;
        }
         
        // Getting All POI's
        public List<POI> getAllPoi() {
                List<POI> contactList = new ArrayList<POI>();
            // Select All Query
            String selectQuery = "SELECT  * FROM " + Poi_TABLE;
         
            SQLiteDatabase db = this.getWritableDatabase();
            Cursor cursor = db.rawQuery(selectQuery, null);
         
            // looping through all rows and adding to list
            if (cursor.moveToFirst()) {
                do {
                    POI poi = new POI();
                    poi.setID(Integer.parseInt(cursor.getString(0)));
                    poi.setTitle(cursor.getString(1));
                    poi.setLat(Double.parseDouble(cursor.getString(2)));
                    poi.setLng(Double.parseDouble(cursor.getString(3)));
                    poi.setElevation(Integer.parseInt(cursor.getString(4)));
                    poi.setPhoto(cursor.getString(5));
                    // Adding contact to list
                    contactList.add(poi);
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
        public int updatePoi(POI poi) {
                SQLiteDatabase db = this.getWritableDatabase();
                 
            ContentValues values = new ContentValues();
            values.put("id", poi.getID()); //ID #
            values.put("title", poi.getTitle()); //Title
            values.put("lat", poi.getLat()); //Latitude
            values.put("lng", poi.getLng()); //Longitude
            values.put("elevation", poi.getElevation()); //Elevatoin
            values.put("photo", poi.getPhoto());
         
            // updating row
            return db.update(Poi_TABLE, values, "id" + " = ?",
                    new String[] { String.valueOf(poi.getID()) });
        }
         
        // Deleting single Poi
        public void deletePoi(POI poi) {
                SQLiteDatabase db = this.getWritableDatabase();
            db.delete(Poi_TABLE, "id" + " = ?",
                    new String[] { String.valueOf(poi.getID()) });
            db.close();
        }

}
