package pt.flag.android_training.helloworld_android.services;

import pt.flag.android_training.helloworld_android.providers.EmailsContract;
import android.app.IntentService;
import android.content.ContentValues;
import android.content.Intent;

public class AddEmailService extends IntentService
{

	public AddEmailService() 
	{
		super("add_emails");
	}
	
	// Runs in alternative Thread.
	@Override
	protected void onHandleIntent(Intent intent) 
	{
		// TODO: Analyze intent and get the e-mail.
		//OLD & UGLY //String email = "ricardo.sousa@challenge.pt";
		String email = intent.getStringExtra("BATATE");
		// Insert in the DB.
		ContentValues values = new ContentValues();
		values.put(EmailsContract.EMAIL, email);
		getContentResolver().insert(EmailsContract.CONTENT_URI, values);
		
		// Notify the changes.
		getContentResolver().notifyChange(EmailsContract.CONTENT_URI, null);
	}
}