package pt.flag.android_training.dummy_android.services;

import pt.flag.android_training.dummy_android.providers.ContactsContract;
import android.app.IntentService;
import android.content.ContentValues;
import android.content.Intent;

public class AddContactService extends IntentService
{
	public final static String EMAIL = "email";
	
	public AddContactService() 
	{
		super("AddContact");
	}

	@Override
	protected void onHandleIntent(Intent intent) 
	{
		ContentValues values = new ContentValues();
		values.put(ContactsContract.EMAIL, intent.getStringExtra(EMAIL));
		getContentResolver().insert(ContactsContract.CONTENT_URI, values);
		
		// notify the changes.
		getContentResolver().notifyChange(ContactsContract.CONTENT_URI, null);
	}
}