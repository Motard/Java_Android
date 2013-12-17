package pt.flag.android_training.dummy_android.providers;

import android.net.Uri;
import android.provider.BaseColumns;

public class ContactsContract 
{
	// Table name.
		public static final String TABLE = "CONTACTS";
		
		// Content URI.
		public static final Uri CONTENT_URI = Uri.withAppendedPath(ContactsProvider.CONTENT_URI, TABLE);
		
		// Columns names.
		public static final String _ID 	 = BaseColumns._ID,
								   EMAIL = "email";
}
