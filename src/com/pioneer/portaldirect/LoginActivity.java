package com.pioneer.portaldirect;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.json.JSONException;
import org.json.JSONObject;

import com.pioneer.portaldirect.GlobalVariables;
import com.pioneer.sqlite.helper.DatabaseHelper;

public class LoginActivity extends Activity {

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login); 
             
        Startup st = new Startup(getApplicationContext());
        st.loadDropdownData();
        
        
    }    
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.login, menu);
 
        return super.onCreateOptionsMenu(menu);
    }
        
    
    public void Login_Click(View view){    	
    	GetJSONData task = new GetJSONData();
    	TextView username = (TextView) findViewById(R.id.txtUsername);
    	TextView password = (TextView) findViewById(R.id.txtPassword);
    	task.execute(username.getText().toString(),"/",password.getText().toString());
    	//task.execute("PA000868261","/","TestingQA10");
    	//Intent i = new Intent(LoginActivity.this, HomeActivity.class); startActivity(i);
    }
    
    public void ForgotPassword_Click(View view){    	
    	

		// Inserting tags in db
		//long ai_id = db.createAnnualIncome(ai1);
		

//		Log.d("Tag Count", "Tag Count: " + db.getAllAnnualIncomes().size());
//		
//		List<AnnualIncome> allTags = db.getAllAnnualIncomes();
//		for (AnnualIncome tag : allTags) {
//			Log.d("AnnualIncome", tag.getDescription());
//		}
    }
    
    public void GetAutoQuote_Click(View view){
    	DatabaseHelper db = DatabaseHelper.getInstance(getApplicationContext());
    	GlobalVariables gv = GlobalVariables.getInstance();
    	int quoteCount = db.getQuoteCount();
    	if(quoteCount > 0){
    		Intent i = new Intent(LoginActivity.this, QuoteListActivity.class); startActivity(i);
    	}
    	else
    	{
    		gv.setQuoteID("");
    		Intent i = new Intent(LoginActivity.this, QuoteTabActivity.class); startActivity(i);
    	}    	
    }
       
    
    public class GetJSONData extends AsyncTask<String, Void, String>{
    	protected String doInBackground(String... params) {
    		HttpParams httpParameters = new BasicHttpParams();
    		DefaultHttpClient httpclient = new DefaultHttpClient(httpParameters);
    		
    		int timeout = GlobalVariables.getInstance().getConnectionTimeout();
    		    		    		
    		HttpConnectionParams.setConnectionTimeout(httpclient.getParams(), timeout);
    		
    		String postURL = GlobalVariables.getInstance().getServerName() + "/users.svc/validateUser/";
    		postURL = postURL.concat(params[0]).concat(params[1]).concat(params[2]);
    		HttpPost httppost = new HttpPost(postURL);
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
    			showMessage("Cannot connect to server. Please try again later.");
    		}
    		catch (Exception e) { 
    		    String error = e.getMessage();
    		    TextView t = (TextView) findViewById(R.id.txtUsername);
    		    t.setText(error);
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
        	
		    JSONObject jObject = null;
			try {
				jObject = new JSONObject(result);
				String responseString = jObject.getString("ValidateUserDBPOSTResult");
				if(responseString.equals("true")){
					Intent i = new Intent(LoginActivity.this, HomeActivity.class); startActivity(i);
				}
				else if(responseString.equals("username")){
					AlertDialog.Builder builder1 = new AlertDialog.Builder(LoginActivity.this);
		            builder1.setMessage("Username is invalid");
		            builder1.setCancelable(true);
		            builder1.setPositiveButton("Ok",
		                    new DialogInterface.OnClickListener() {
		                public void onClick(DialogInterface dialog, int id) {
		                    dialog.cancel();
		                }
		            });
		            
		            AlertDialog alert11 = builder1.create();
		            alert11.show();
				}
				else
				{
					AlertDialog.Builder builder1 = new AlertDialog.Builder(LoginActivity.this);
		            builder1.setMessage("Login information is invalid");
		            builder1.setCancelable(true);
		            builder1.setPositiveButton("Ok",
		                    new DialogInterface.OnClickListener() {
		                public void onClick(DialogInterface dialog, int id) {
		                    dialog.cancel();
		                }
		            });
		            
		            AlertDialog alert11 = builder1.create();
		            alert11.show();
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    //ValidateUserDBPOSTResult
		    
        }

		
    }
    
    public void showMessage(final String message)
    {
        runOnUiThread(new Runnable() {
            public void run()
            {
            	//Toast.makeText(LoginActivity.this, message, Toast.LENGTH_SHORT).show();
            	AlertDialog.Builder builder1 = new AlertDialog.Builder(LoginActivity.this);
	            builder1.setMessage(message);
	            builder1.setCancelable(true);
	            builder1.setPositiveButton("Ok",
	                    new DialogInterface.OnClickListener() {
	                public void onClick(DialogInterface dialog, int id) {
	                    dialog.cancel();
	                }
	            });
	            
	            AlertDialog alert11 = builder1.create();
	            alert11.show();
            }
        });
    }
    
    
}
