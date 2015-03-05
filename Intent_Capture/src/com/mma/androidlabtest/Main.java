package com.mma.androidlabtest;

import java.io.File;
import java.io.FileInputStream;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class Main extends Activity {
    
    private static final int CODE_REQUEST = 1;
    ImageView ivPreview;
    File outputFile;
    
    public void onCreate (Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.main);
        ivPreview = (ImageView)findViewById (R.id.imageview);
        outputFile = new File (Environment.getExternalStorageDirectory(), "capture.jpg");
		
        Button btnCapture = (Button)findViewById (R.id.btn_capture);
        btnCapture.setOnClickListener (new View.OnClickListener() {
			public void onClick (View view) {
				Intent intent = new Intent  (MediaStore.ACTION_IMAGE_CAPTURE);
				intent.putExtra (MediaStore.EXTRA_OUTPUT, Uri.fromFile (outputFile));
				startActivityForResult (intent, CODE_REQUEST);
			}
		});
    }
    
    protected void onActivityResult (int requestCode, int resultCode, Intent data) {
        if (requestCode == CODE_REQUEST) {
        	if (resultCode == Activity.RESULT_OK) {
	            try {
	                FileInputStream fileInputStream = new FileInputStream(outputFile);
	                BitmapFactory.Options options = new BitmapFactory.Options();
	                options.inSampleSize = 2;
	                Bitmap bitmap = BitmapFactory.decodeStream (fileInputStream, null, options);
	                ivPreview.setImageBitmap (bitmap);
	            } catch (Exception e) {
					Toast.makeText (getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
	            }
        	}
        }
    }
}
