package Data.mgr;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.untar.eaglevision.MainActivity;
 
import android.util.Log;

public class DataGeter 
{

	 
 
    private static String convertStreamToString(InputStream is) {
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
    }
 
    /* This is a test function which will connects to a given
     * rest service and prints it's response to Android Log with
     * labels "Praeda".
     */
    public static void connect(String url)
    {
     	PoiOpenHelper db= new PoiOpenHelper(MainActivity.appContext);
        HttpClient httpclient = new DefaultHttpClient();
        db.dropTable();

        // Prepare a request object
        HttpGet httpget = new HttpGet(url); 
        httpget.addHeader("accept","application/json");
        // Execute the request
        HttpResponse response;
        try {
            response = httpclient.execute(httpget);
            // Examine the response status
            Log.i("Connect",response.getStatusLine().toString());
 
            // Get hold of the response entity
            HttpEntity entity = response.getEntity();
            // If the response does not enclose an entity, there is no need
            // to worry about connection release
 
            if (entity != null) {
 
                // A Simple JSON Response Read
                InputStream instream = entity.getContent();
                String result= convertStreamToString(instream);
                Log.i("Results:",result);
 
                // A Simple JSONObject Creation
                /*JSONObject json=new JSONObject(result);
                Log.i("Connect","<jsonobject>\n"+json.toString()+"\n</jsonobject>");*/
                JSONObject json=new JSONObject(result);
                json.getJSONArray("results");
                JSONArray datArray=json.getJSONArray("results");
                
                // A Simple JSONObject Parsing
                /*JSONArray nameArray=json.names();
                JSONArray valArray=json.getJSONArray("results");*/
                JSONObject json_data; 
                for(int i=0;i<datArray.length();i++)
                {
                 json_data = datArray.getJSONObject(i);
                 String Id =json_data.getString("id");
                 String Lat=json_data.getString("lat");
                 String Lng=json_data.getString("lng");
                 int elevation =json_data.getInt("elevation");
                 String title =json_data.getString("title");
                 String photo = json_data.getString("webpage");
               /*  Log.i("Data","<jsonname"+i+">\n"+nameArray.getString(i)+"\n</jsonname"+i+">\n"
                         +"<jsonvalue"+i+">\n"+valArray.getString(i)+"\n</jsonvalue"+i+">");*/
                 int id = Integer.parseInt(Id);
                 double lat=Double.parseDouble(Lat);
                 double lng=Double.parseDouble(Lng);
                 
                 //need to convert data to proper cast. 
                 db.addPoi(new poi(id,title,lat,lng,elevation,photo));
                
                 }
                Log.d("Reading: ", "Reading all contacts.."); 
                List<poi> POI = db.getAllPoi();
                for (poi pi : POI) {
                    String log = "Id: "+pi.getID()+" ,Title: " + 
                pi.getTitle() + " ,Lat: " + pi.getLat()+" ,Lng: "+ pi.getLng() +
                " ,Elevation: "+pi.getElevation()+" ,Photo: "+pi.getPhoto();
                        // Writing Contacts to log
                Log.d("Name: ", log); 
                 
                 
                 
                }
                    
                
 
                /* A Simple JSONObject Value Pushing
                json.put("sample key", "sample value");
                Log.i("Data","<jsonobject>\n"+json.toString()+"\n</jsonobject>");
 */
                // Closing the input stream will trigger connection release
                instream.close();
                db.close();
            }
 
 
        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
 
}





