package com.example.imageurlexample;

import java.io.InputStream;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
//COMMENT

public class MainActivity extends Activity {
    //This is a test comments.
	// Set your Image URL into a string
    String URL = "http://i1.ytimg.com/u/22TOQWJue006Lp6DB5QhDA/channels4_tablet_banner_low.jpg?v=52283c8a";
    ImageView image;
    Button button;
    ProgressDialog mProgressDialog;
 
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Get the layout from image.xml
        setContentView(R.layout.activity_main);
 
        // Locate the ImageView in activity_main.xml
        image = (ImageView) findViewById(R.id.image);
 
        // Locate the Button in activity_main.xml
        button = (Button) findViewById(R.id.button);
 
        // Capture button click
        button.setOnClickListener(new OnClickListener() {
            public void onClick(View arg0) {
 
                // Execute DownloadImage AsyncTask
                new DownloadImage().execute(URL);

                //Testing comment
            }
        });
    }
 
    // DownloadImage AsyncTask
    private class DownloadImage extends AsyncTask<String, Void, Bitmap> {
 
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Create a progressdialog
            mProgressDialog = new ProgressDialog(MainActivity.this);
            // Set progressdialog title
            mProgressDialog.setTitle("Download Image Tutorial");
            // Set progressdialog message
            mProgressDialog.setMessage("Loading...");
            mProgressDialog.setIndeterminate(false);
            // Show progressdialog
            mProgressDialog.show();
        }
 
        @Override
        protected Bitmap doInBackground(String... URL) {
 
            String imageURL = URL[0];
 
            Bitmap bitmap = null;
            try {
                // Download Image from URL
                InputStream input = new java.net.URL(imageURL).openStream();
                // Decode Bitmap
                bitmap = BitmapFactory.decodeStream(input);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return bitmap;
        }
 
        @Override
        protected void onPostExecute(Bitmap result) {
            // Set the bitmap into ImageView
            image.setImageBitmap(result);
            // Close progressdialog
            mProgressDialog.dismiss();
        }
    }

}
