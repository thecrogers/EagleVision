package com.untar.eaglevision;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.json.JSONArray;
import org.json.JSONObject;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.HttpEntity;

import Data.mgr.PoiOpenHelper;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class POIListFragment extends ListFragment {
	OnPOISelectedListener _listener;
	
	POIListFragment(OnPOISelectedListener listener) {
		_listener = listener;
	}
    /*private static String convertStreamToString(InputStream is) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
 
        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }*/
    
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		/*try {
			POIOpenHelper POIdb = new POIOpenHelper(getActivity());
		
			HttpClient httpClient = new DefaultHttpClient();
	        HttpGet httpGet = new HttpGet("http://eaglevision.net23.net/test.json"); 
	        httpGet.addHeader("accept", "application/json");
	        HttpEntity httpEntity = httpClient.execute(httpGet).getEntity();
	        
	        if (httpEntity != null) {
	
	        	InputStream webStream = httpEntity.getContent();
				String rawDataString = convertStreamToString(webStream);
				webStream.close();
				
				JSONArray webData = (new JSONObject(rawDataString)).getJSONArray("results");
				JSONObject currentData;
				
				for (int i = 0; i < webData.length(); i++) {
					currentData = webData.getJSONObject(i);
					POIdb.addPOI(new POI(
						currentData.getInt("id"),
						currentData.getString("title"),
						currentData.getDouble("lat"),
						currentData.getDouble("lng"),
						currentData.getInt("elevation"),
						currentData.getString("webpage")
					));
				}

				setListAdapter(new ArrayAdapter<POI>(getActivity(), R.layout.list_fragment, POIdb.getAllPoi()));
		        POIdb.close();
	        }
		} catch (Exception e) {Log.e("MYSELF", e.toString());}*/
		PoiOpenHelper db= new PoiOpenHelper(MainActivity.appContext);
		setListAdapter(new ArrayAdapter<String>(getActivity(), R.layout.list_fragment, db.getAllTitles()));
		return super.onCreateView(inflater, container, savedInstanceState);
	}
	
	public void onListItemClick (ListView l, View v, int position, long id) {
		_listener.onPOISelected(position);
		/*Intent intent = new Intent(getActivity(), POIDetailsFragment.class);
		startActivity(intent);*/
	}
}
