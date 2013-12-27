package pt.flag.android_training.helloworld_android.services;

import pt.flag.android_training.helloworld_android.providers.EmailsContract;
import android.app.IntentService;
import android.content.Intent;

public class RemoveEmailService extends IntentService
{

	public RemoveEmailService() 
	{
		super("removeEmail");
	}

	@Override
	protected void onHandleIntent(Intent intent) 
	{
		int id = intent.getIntExtra("POS", 0);
		String[] args = new String[]{id+""};
																// where _ID = id
		getContentResolver().delete(EmailsContract.CONTENT_URI, EmailsContract._ID + "=?", args);
		
		// Notify changes.
		getContentResolver().notifyChange(EmailsContract.CONTENT_URI, null);
	}
}
