package pt.flag.android_training.helloworld_android.providers;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Class that defines the contract of the e-mails table. 
 * Contains the name of the Table, the columns and the content URI 
 * for access via content resolver in Android.
 * @author ricardosousa
 * */
public class _EmailsContract 
{
	// Table name.
	public static final String TABLE = "EMAILS";
	
	// Content URI for this particular subset of provided data from e-mails provider.
	public static final Uri CONTENT_URI = Uri.withAppendedPath(_EmailsProvider.CONTENT_URI, TABLE);
	
	// Columns names.
	public static final String _ID 	 = BaseColumns._ID,
							   EMAIL = "email";
}