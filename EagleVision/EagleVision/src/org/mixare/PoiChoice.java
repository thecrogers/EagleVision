package org.mixare;

import junit.framework.Assert;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import org.mixare.MixView;

import com.untar.eaglevision.R;

   

public class PoiChoice extends Activity {
	 static String pid;
	 PoiChoice Poi;
    Context context =Poi;
	DataView Data;
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	    setContentView(R.layout.poi);
	    String name = getIntent().getStringExtra(pid);
	    TextView BuildingName = (TextView)findViewById(R.id.textView1);
	    BuildingName.setText(name);
	    ImageView BuildingPic  = (ImageView)findViewById(R.id.Building);
	    
	   // BuildingPic.setImageResource(getDrawable(context,name));
	    
	
	}
	public static int getDrawable(Context context, String name)
    {
        Assert.assertNotNull(context);
        Assert.assertNotNull(name);

        return context.getResources().getIdentifier(name,
                "drawable", context.getPackageName());
    }
	public void setPoiId(String id2) {
		pid=id2;
		
}
public String getPoiId()
{
	return pid;
}


}
