package com.example.assignment4_client;



import java.io.File;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.ContentBody;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.util.EntityUtils;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


public class MainActivity extends ActionBarActivity implements OnClickListener {
	private String srcPath = "/storage/emulated/0/Download/subj-1.pdf";
	Button b1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.as);
        b1=(Button)findViewById(R.id.button1);
        
        b1.setOnClickListener(this);
        
    }
	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		if(arg0==b1){
			System.out.println("Clicked" );

			
			try{
			
			HttpClient httpclient = new DefaultHttpClient();
		    httpclient.getParams().setParameter(CoreProtocolPNames.PROTOCOL_VERSION, HttpVersion.HTTP_1_1);

		    HttpPost httppost = new HttpPost("http://192.168.0.3:8080/Assignment4_2011080/upload");
		    File file = new File("/storage/emulated/0/Download/lecture.pdf");

		    MultipartEntity mpEntity = new MultipartEntity();
		    ContentBody cbFile = new FileBody(file, "multipart/form-data");
		    mpEntity.addPart("file", cbFile);


		    httppost.setEntity(mpEntity);
		    System.out.println("executing request " + httppost.getRequestLine());
		    HttpResponse response = httpclient.execute(httppost);
		    HttpEntity resEntity = response.getEntity();

		    System.out.println(response.getStatusLine());
		    if (resEntity != null) {
		      System.out.println(EntityUtils.toString(resEntity));
		    }
		    if (resEntity != null) {
		      resEntity.consumeContent();
		    }

		    httpclient.getConnectionManager().shutdown();
			
			}
			
		    catch (Exception ex)
	        {
	            ex.printStackTrace() ;
	        }
			
			
			
		}
		
	}


    
}
