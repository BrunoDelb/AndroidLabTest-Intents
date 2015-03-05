package com.mma.androidlabtest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Main  extends Activity {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Button btnChooseContact = (Button)findViewById (R.id.btnChooseContact);
        btnChooseContact.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
		        Intent intent = new Intent();
		        intent.setAction(Intent.ACTION_PICK);
		        intent.setData(ContactsContract.Contacts.CONTENT_URI);
				startActivity(intent);
			}
		});
    }
}
