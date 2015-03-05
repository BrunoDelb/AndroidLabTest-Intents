package com.mma.androidlabtest;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class Main extends Activity {
			 
	static final int CODE_REQUEST = 1;
	TextView tv_output;

	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		setContentView(R.layout.main);
		tv_output = (TextView)findViewById(R.id.tv_output);
		Button btnChooseOneContact = (Button)findViewById(R.id.btnChooseOneContact);
		btnChooseOneContact.setOnClickListener(new OnClickListener() {
			public void onClick(View view) {
				Intent intent = new Intent(Intent.ACTION_PICK, Uri.parse("content://contacts/"));
				startActivityForResult(intent, CODE_REQUEST);  
			}     
		});
	}

	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == CODE_REQUEST) {
			if (resultCode == Activity.RESULT_OK) {
				Cursor cursor = managedQuery(data.getData(), null, null, null, null);
				cursor.moveToFirst();
				tv_output.setText(cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.Contacts.DISPLAY_NAME)));
			}
		}
	}
}
