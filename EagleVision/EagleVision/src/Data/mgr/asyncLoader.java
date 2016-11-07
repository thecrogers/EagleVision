package Data.mgr;

import android.os.AsyncTask;
import android.widget.Toast;

public class asyncLoader extends AsyncTask<Void,Void,Void> {
	String SERVER_URL ="http://eaglevision.net23.net/test.json";
	 protected void onPreExecute() {
		   // update the UI immediately after the task is executed
		   super.onPreExecute();
		    
		    /*Toast.makeText(asyncLoader.this,
		            "DownLoading POI", Toast.LENGTH_SHORT).show();*/
	 }
	@Override
	protected Void doInBackground(Void... arg0) {
		
		DataGeter.connect(SERVER_URL);
		return null;
	}

}
