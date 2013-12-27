package pt.flag.android_training.helloworld_android.services;

import pt.flag.android_training.helloworld_android.providers._EmailsContract;
import android.app.IntentService;
import android.content.Intent;

public class _RemoveEmailService extends IntentService
{
	public final static String POSITION = "pos";
	
	public _RemoveEmailService() 
	{
		super("RemoveContact");
	}

	@Override
	protected void onHandleIntent(Intent intent) 
	{
		getContentResolver().delete(_EmailsContract.CONTENT_URI, _EmailsContract._ID + "=?", new String[] {intent.getIntExtra(POSITION, 0) + ""});
		// notify the changes.
		getContentResolver().notifyChange(_EmailsContract.CONTENT_URI, null);
	}
}