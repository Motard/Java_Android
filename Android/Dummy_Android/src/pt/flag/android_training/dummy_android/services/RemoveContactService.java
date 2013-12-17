package pt.flag.android_training.dummy_android.services;

import pt.flag.android_training.dummy_android.providers.ContactsContract;
import android.app.IntentService;
import android.content.Intent;

public class RemoveContactService extends IntentService
{
	public final static String POSITION = "pos";
	
	public RemoveContactService() 
	{
		super("RemoveContact");
	}

	@Override
	protected void onHandleIntent(Intent intent) 
	{
		getContentResolver().delete(ContactsContract.CONTENT_URI, ContactsContract._ID + "=?", new String[] {intent.getIntExtra(POSITION, 0) + ""});
		// notify the changes.
		getContentResolver().notifyChange(ContactsContract.CONTENT_URI, null);
	}
}
