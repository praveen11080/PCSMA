package com.example.androidclient;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class DeleteActivity extends Activity implements android.view.View.OnClickListener {
Button b1;
EditText e1;


@Override
protected void onCreate(Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	super.onCreate(savedInstanceState);
	setContentView(R.layout.delete);
	
	b1 = (Button) findViewById(R.id.button1);
	e1 = (EditText) findViewById(R.id.editText1);
	b1.setOnClickListener(this);
}


@Override
public void onClick(View arg0) {
	// TODO Auto-generated method stub
	if(arg0==b1){
		Thread thread = new Thread() {
		    @Override
		    public void run() {
		        HttpClient client = new DefaultHttpClient();
				 HttpDelete request = new HttpDelete("http://192.168.1.3:8080/Assignment_3/VideoServlet");


				 HttpResponse response;
				 try
				 {
				     response = client.execute(request);

				     Log.d("Response of Delete request", response.toString());
				 }
				 catch (ClientProtocolException e)
				 {

				     e.printStackTrace();
				 }
				 catch (IOException e)
				 {

				     e.printStackTrace();
				 }
		    }
		};

		thread.start();
	
				
		
	        
	
}
}
}
