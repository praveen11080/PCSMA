package com.example.androidclient;

import java.io.ByteArrayOutputStream;
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
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity implements android.view.View.OnClickListener {

	Button b1,b2,b3,b4;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		b1=(Button)findViewById(R.id.button1);
		b2=(Button)findViewById(R.id.button2);
		b3=(Button)findViewById(R.id.button3);
		b4=(Button)findViewById(R.id.button4);
		
		b1.setOnClickListener(this);
		b2.setOnClickListener(this);
		b3.setOnClickListener(this);
		b4.setOnClickListener(this);
		
		//Post();
		//Get();

	}
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(v==b1){
			Intent a=new Intent(getBaseContext(),GetActivity.class);
			startActivity(a);
			
		}
		
		if(v==b2){
			Intent a=new Intent(getBaseContext(),PostActivity.class);
			startActivity(a);
		}
		
		if(v==b3){
			Intent a=new Intent(getBaseContext(),DeleteActivity.class);
			startActivity(a);
			
		}
		
		if(v==b4){
			Intent a=new Intent(getBaseContext(),PutActivity.class);
			startActivity(a);
		}
	}

	
	void Get() {

		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					// Your code goes here
					HttpClient httpClient = new DefaultHttpClient();
					String url = "http://192.168.1.3:8080/Assignment_3/VideoServlet";
					HttpGet httpGet = new HttpGet(url);
					try {
						HttpResponse response = httpClient.execute(httpGet);
						StatusLine statusLine = response.getStatusLine();
						if (statusLine.getStatusCode() == HttpStatus.SC_OK) {
							HttpEntity entity = response.getEntity();
							ByteArrayOutputStream out = new ByteArrayOutputStream();
							entity.writeTo(out);
							out.close();
							String responseStr = out.toString();
							Log.d("Http Response:", responseStr.toString());
							// do something with response
						} else {
							// handle bad response
						}
					} catch (ClientProtocolException e) {
						// handle exception
					} catch (IOException e) {
						// handle exception
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

		thread.start();

	}

	void Post() {

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
							"Praveen"));
					nameValuePair.add(new BasicNameValuePair("file_size",
							"2000"));
					nameValuePair
							.add(new BasicNameValuePair("duration", "200"));
					nameValuePair.add(new BasicNameValuePair("type", "avl"));
					nameValuePair.add(new BasicNameValuePair("url",
							"Google.com/yes/hes.com"));

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

						// writing response to log
						HttpEntity entity = response.getEntity();
						final String responseText = EntityUtils
								.toString(entity);
						Log.d("pagal:", responseText);
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

/*
 * 
 * // Creating HTTP client HttpClient httpClient = new DefaultHttpClient(); //
 * Creating HTTP Post HttpPost httpPost = new HttpPost(
 * "http://192.168.1.3:8080/Assignment_3/VideoServlet");
 * 
 * // Building post parameters
 * 
 * // key and value pair
 * 
 * 
 * List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>();
 * nameValuePair.add(new BasicNameValuePair("file_name", "Praveen"));
 * nameValuePair.add(new BasicNameValuePair("file_size", "2000"));
 * nameValuePair.add(new BasicNameValuePair("duration", "200"));
 * nameValuePair.add(new BasicNameValuePair("type", "avl"));
 * nameValuePair.add(new BasicNameValuePair("url", "Google.com/yes/hes.com"));
 * 
 * 
 * 
 * // Url Encoding the POST parameters try { httpPost.setEntity(new
 * UrlEncodedFormEntity(nameValuePair)); } catch (UnsupportedEncodingException
 * e) { // writing error to Log e.printStackTrace(); }
 * 
 * // Making HTTP Request try { HttpResponse response =
 * httpClient.execute(httpPost);
 * 
 * // writing response to log Log.d("Http Response:", response.toString()); }
 * catch (ClientProtocolException e) { // writing exception to log
 * e.printStackTrace(); } catch (IOException e) { // writing exception to log
 * e.printStackTrace();
 * 
 * }
 */