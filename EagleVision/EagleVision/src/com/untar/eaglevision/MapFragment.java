/* working code
  package com.untar.eaglevision;
 

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MapFragment extends Fragment {
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		return inflater.inflate(R.layout.map_fragment, container, false);
	}
}*/


//package com.isaiah.eaglevisionmaps;
  package com.untar.eaglevision;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
//import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
//import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;

public class POIMapFragment extends SupportMapFragment implements LocationListener {
	private GoogleMap mMap;
	int _position = 0;

//	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//		// Inflate the layout for this fragment
//		//setUpMapIfNeeded();
//		return inflater.inflate(R.layout.map_fragment, container, true);
//	   
//	}

	  
	    
//	    private void setUpMapIfNeeded() {
//	        // Do a null check to confirm that we have not already instantiated the map.
//	        if (mMap == null) {
//	        	// Try to obtain the map from the SupportMapFragment.
//	            mMap = ((SupportMapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();//<--another error generated with the getSupportFragmentManager changed to getFragmentManager also suggested getChildFragmentManager. Not sure which will work
//	            // Check if we were successful in obtaining the map.
//	            if (mMap != null) {
//	                setUpMap();
//	            }
//	        }
//	    }

	    public void setPoi(int position) {
	    	_position = position;
	    }
	    
//	    private void setUpMap() {
//	    	mMap.setMyLocationEnabled(true);
//	    	
//	    	LocationManager lm= (LocationManager) this.getActivity().getSystemService(MainActivity.appContext.LOCATION_SERVICE);
//	    	String provider= lm.getBestProvider(new Criteria(), true);
//	    	if(provider == null){
//	    		onProviderDisabled(provider);
//	    	}
//	    	 Location loc= lm.getLastKnownLocation(provider);
//	    	 if(loc != null){
//	    		 onLocationChanged(loc);
//	    	 }
//	    	mMap.addMarker(new MarkerOptions().position(new LatLng(0, 0)).title("Current Location"));
//	    	
//	    }

		@Override
		public void onLocationChanged(Location location) {
			POIOpenHelper db = new POIOpenHelper(MainActivity.appContext);
			POI poi = db.getPoi(1001 + _position);//getting building location from the POI list
			LatLng BuildingLocation= new LatLng(poi.getLat(), poi.getLng());
			db.close();
			//This is dummy text
			
			
			mMap.addMarker(new MarkerOptions().position(BuildingLocation).title("Building Location"));//pin dropped at location of BuildingLocation 
			mMap.moveCamera(CameraUpdateFactory.newLatLng(BuildingLocation));//current location
			mMap.animateCamera(CameraUpdateFactory.zoomTo(15));
			
		}

		@Override
		public void onProviderDisabled(String provider) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onProviderEnabled(String provider) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onStatusChanged(String provider, int status, Bundle extras) {
			// TODO Auto-generated method stub
			
		}
}

