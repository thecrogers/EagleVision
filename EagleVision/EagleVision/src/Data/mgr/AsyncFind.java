package Data.mgr;

import com.untar.eaglevision.MainActivity;

import android.os.AsyncTask;

public class AsyncFind extends AsyncTask<String,Void,Void> {

	@Override
	protected Void doInBackground(String... ID) {
		PoiOpenHelper db=new PoiOpenHelper(MainActivity.appContext);
		int id =Integer.parseInt(ID[0]);
		db.getPoi(id);
		return null;
	}

}
