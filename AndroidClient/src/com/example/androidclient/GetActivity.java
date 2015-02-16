package com.example.androidclient;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class GetActivity extends Activity implements
		android.view.View.OnClickListener {
	Button b1, b2;
	EditText e1;
	TextView t1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activityget);

		b1 = (Button) findViewById(R.id.button1);
	//	b2 = (Button) findViewById(R.id.button2);
		//e1 = (EditText) findViewById(R.id.editText1);
		t1 = (TextView) findViewById(R.id.textView1);
		b1.setOnClickListener(this);
		//b2.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if (v == b1) {
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
								t1.setText(responseStr);
								Log.d("Http Response:", responseStr.toString());
								// do something with response
							} else {
								
								t1.setText("Something went wrong");
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
/*
		if (v == b2) {
			//final String str=e1.getText().toString();
			
			if(e1.getText().toString().equals(null)){
				t1.setText("Please enter video name");
			}
			else {
			Thread thread = new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						// Your code goes here
						HttpClient httpClient = new DefaultHttpClient();
						String url = "http://192.168.1.3:8080/Assignment_3/VideoServlet/?file_name="+e1.getText().toString();
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
								t1.setText(responseStr);
								Log.d("Http Response:", responseStr.toString());
								// do something with response
							} else {
								t1.setText("Video not found on server");
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
		}
*/
	}

}
