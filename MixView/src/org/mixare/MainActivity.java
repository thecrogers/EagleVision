package org.mixare;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Window;


public class MainActivity extends FragmentActivity {
	
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    requestWindowFeature(Window.FEATURE_ACTION_BAR);
	    setContentView(R.layout.main_activity);
	    
	    MixView fragment = new MixView();
        
        getSupportFragmentManager().beginTransaction().add(R.id.startActivity_container, fragment).commit();
	    
       
      

	    
	}
	 @Override
 	public void onResume() {
 		super.onResume();
     	
     }
	 
	  @Override
      public void onPause()
      {
    	  super.onPause();
    	  
      }
}
