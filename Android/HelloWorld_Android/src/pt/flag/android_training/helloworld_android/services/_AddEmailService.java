package pt.flag.android_training.helloworld_android.services;

import pt.flag.android_training.helloworld_android.providers._EmailsContract;
import android.app.IntentService;
import android.content.ContentValues;
import android.content.Intent;

public class _AddEmailService extends IntentService
{
	public final static String EMAIL = "email";
	
	public _AddEmailService() 
	{
		super("AddContact");
	}

	@Override
	protected void onHandleIntent(Intent intent) 
	{
		ContentValues values = new ContentValues();
		values.put(_EmailsContract.EMAIL, intent.getStringExtra(EMAIL));
		getContentResolver().insert(_EmailsContract.CONTENT_URI, values);
		
		// notify the changes.
		getContentResolver().notifyChange(_EmailsContract.CONTENT_URI, null);
	}
}