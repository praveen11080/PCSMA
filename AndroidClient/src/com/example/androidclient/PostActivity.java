package com.example.androidclient;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class PostActivity extends Activity implements android.view.View.OnClickListener{
	Button b1;
	EditText e1,e2,e3,e4,e5;
	TextView t6;
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.post);
		b1=(Button)findViewById(R.id.button1);
		e1=(EditText)findViewById(R.id.editText1);
		e2=(EditText)findViewById(R.id.editText2);
		e3=(EditText)findViewById(R.id.editText3);
		e4=(EditText)findViewById(R.id.editText4);
		e5=(EditText)findViewById(R.id.editText5);
		t6=(TextView)findViewById(R.id.textView6);
		
		b1.setOnClickListener(this);
		
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(v==b1){
			Thread thread = new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						// Your code goes here
						HttpClient httpClient = new DefaultHttpClient();
						// Creating HTTP Post
						HttpPost httpPost = new HttpPost(
								"http://192.168.1.3:8080/Assignment_3/VideoServlet");

						// Building post parameters

						// key and value pair

						List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>(
								5);
						nameValuePair.add(new BasicNameValuePair("file_name",
								e1.getText().toString()));
						nameValuePair.add(new BasicNameValuePair("file_size",
								e2.getText().toString()));
						nameValuePair
								.add(new BasicNameValuePair("duration", e3.getText().toString()));
						nameValuePair.add(new BasicNameValuePair("type", e4.getText().toString()));
						nameValuePair.add(new BasicNameValuePair("url",
								e5.getText().toString()));

						// Url Encoding the POST parameters
						try {
							httpPost.setEntity(new UrlEncodedFormEntity(
									nameValuePair));
						} catch (UnsupportedEncodingException e) {
							// writing error to Log
							e.printStackTrace();
						}

						// Making HTTP Request
						try {
							HttpResponse response = httpClient.execute(httpPost);
							StatusLine statusLine = response.getStatusLine();
							if (statusLine.getStatusCode() == HttpStatus.SC_OK) {
							// writing response to log
							HttpEntity entity = response.getEntity();
							final String responseText = EntityUtils
									.toString(entity);
							t6.setText(responseText);
							Log.d("pagal:", responseText);
							}
							else{
								t6.setText("Required [Name,Size,Duration,Type,Url]");
							}
							
						} catch (ClientProtocolException e) {
							// writing exception to log
							e.printStackTrace();
						} catch (IOException e) {
							// writing exception to log
							e.printStackTrace();

						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});

			thread.start();
		}
	}

}
