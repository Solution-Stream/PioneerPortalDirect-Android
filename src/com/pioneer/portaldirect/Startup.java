package com.pioneer.portaldirect;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.pioneer.portaldirect.GlobalVariables;
import com.pioneer.sqlite.helper.DatabaseHelper;
import com.pioneer.sqlite.model.DropdownData;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;

public class Startup extends Activity{
	
	Context _context;
		
	public Startup(Context context) {
		_context = context;
	}
	
	public void loadDropdownData(){
		GetJSONData task = new GetJSONData();
    	task.execute("");
	}
	
	public class GetJSONData extends AsyncTask<String, Void, String>{
    	protected String doInBackground(String... params) {
    		DatabaseHelper db = DatabaseHelper.getInstance(_context);
    		db.deleteAllDropdownData();
    		
    		HttpParams httpParameters = new BasicHttpParams();
    		DefaultHttpClient httpclient = new DefaultHttpClient(httpParameters);
    		
    		int timeout = GlobalVariables.getInstance().getConnectionTimeout();
    		    		    		
    		HttpConnectionParams.setConnectionTimeout(httpclient.getParams(), timeout);
    		
    		String postURL = GlobalVariables.getInstance().getServerName() + "/users.svc/getdropdowndata/";
    		
    		HttpGet httppost = new HttpGet(postURL);
    		// Depends on your web service
    		httppost.setHeader("Content-type", "application/json");

    		InputStream inputStream = null;
    		String result = null;
    		try {
    		    HttpResponse response = httpclient.execute(httppost);           
    		    HttpEntity entity = response.getEntity();

    		    inputStream = entity.getContent();
    		    // json is UTF-8 by default
    		    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"), 8);
    		    StringBuilder sb = new StringBuilder();

    		    String line = null;
    		    while ((line = reader.readLine()) != null)
    		    {
    		        sb.append(line + "\n");
    		    }
    		    result = sb.toString();
    		} 
    		catch (ConnectTimeoutException e){
    			
    		}
    		catch (Exception e) { 
    		    String s = "";
    		}
    		finally {
    		    try{if(inputStream != null)inputStream.close();}catch(Exception squish){}
    		}
            return result;
        }

        protected void onProgressUpdate(Integer... progress) {
            //setProgressPercent(progress[0]);
        }

        protected void onPostExecute(String result) {
        	//TextView t = (TextView) findViewById(R.id.txtUsername);
		    //t.setText(result);
		    if(result == null){
		    	return;
		    }
		    DatabaseHelper db = DatabaseHelper.getInstance(_context);
		    JSONObject jObject = null;
			try {
				jObject = new JSONObject(result);
				//String responseString = jObject.getString("GetAnnualIncomeListResult");
				//String r = responseString + "f";
				JSONArray jArray = jObject.getJSONArray("GetDropdownDataResult");
				for (int i=0; i < jArray.length(); i++)
				{
				    try {
				        JSONObject oneObject = jArray.getJSONObject(i);
				        // Pulling items from the array
				        String code = oneObject.getString("Code");
				        String desc = oneObject.getString("Description");
				        String name = oneObject.getString("Name");
				        
				        DropdownData ai = new DropdownData();
				        ai.setCode(code);
				        ai.setDescription(desc);
				        ai.setName(name);
				        db.createDropdownData(ai);
				        
				    } catch (JSONException e) {
				        // Oops
				    }
				}
			} catch (JSONException e) {
				
			}
			finally{
				//db.closeDB();
				//db.close();
			}
		    
		    
        }

		
    }
	
}