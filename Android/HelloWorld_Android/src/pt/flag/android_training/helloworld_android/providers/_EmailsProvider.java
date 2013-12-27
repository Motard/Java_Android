package pt.flag.android_training.helloworld_android.providers;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;

/**
 * e-mails content provider.
 * @author ricardosousa
 * */
public class _EmailsProvider extends ContentProvider
{
	// The authority that identifies this content provider in Android.
	public static final String AUTHORITY = "pt.flag.android_training.dummy_android.providers.emailsprovider";	
	// The content: scheme identifies the URI as a content URI pointing to an Android content provider.
	public static final Uri CONTENT_URI = Uri.parse(ContentResolver.SCHEME_CONTENT + "://" + AUTHORITY);	
	
	// Matcher for see if the type is one element or all elements.
	private static UriMatcher URIMATCHER = new UriMatcher(UriMatcher.NO_MATCH);
	private static final int EMAIL_ID  = 1;
	private static final int EMAIL_ALL = 2;
	
	/*
	 * For content URIs that point to a row or rows of table data, getType() should return a MIME 
	 * type in Android's vendor-specific MIME format: 
	 * Type part: 
	 * 	vnd
	 * Subtype part:
	 * 	If the URI pattern is for a single row: android.cursor.item/
	 * 	If the URI pattern is for more than one row: android.cursor.dir/
	 * Provider-specific part: 
	 * 	vnd.<name>.<type>
	 * 
	 * See: http://developer.android.com/guide/topics/providers/content-provider-creating.html
	 * */
	private static final String MIME_ALL = "vnd.android.cursor.dir/vnd.pt.flag.android_training.dummy_android.providers." + _EmailsContract.TABLE;
	private static final String MIME_ONE = "vnd.android.cursor.item/vnd.pt.flag.android_training.dummy_android.providers." + _EmailsContract.TABLE;
	
	// DB Helper instance for access to SQLite DB.
	private SQLiteOpenHelper _helper;
	
	static 
	{
		URIMATCHER.addURI(AUTHORITY, "EMAIL/#", EMAIL_ID);
		URIMATCHER.addURI(AUTHORITY, "EMAIL", EMAIL_ALL);
	}
	
	@Override
	public boolean onCreate() 
	{
		_helper = new EmailsHelper(getContext());
		return true;
	}
	
	@Override
	public String getType(Uri uri) 
	{
		return URIMATCHER.match(uri) == EMAIL_ALL ? MIME_ALL : MIME_ONE;
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) 
	{
		SQLiteDatabase db = _helper.getWritableDatabase();
		try
		{
			long row = db.insert(_EmailsContract.TABLE, null, values);
			return row == -1 ? null : ContentUris.withAppendedId(uri, row);
		} finally 
		{
			db.close();
		}
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) 
	{
		SQLiteDatabase db = _helper.getReadableDatabase();	
		return db.query(_EmailsContract.TABLE, projection, selection, selectionArgs, null, null, sortOrder);
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) 
	{
		SQLiteDatabase db = _helper.getWritableDatabase();
		try 
		{
			return db.update(_EmailsContract.TABLE, values, selection, selectionArgs); 
		} finally 
		{
			db.close();
		}
	}
	
	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) 
	{
		SQLiteDatabase db = _helper.getWritableDatabase();
		try 
		{
			return db.delete(_EmailsContract.TABLE, selection, selectionArgs);
		} finally
		{
			db.close();
		}
	}
	
	/**
	 * DB helper for e-mails. The DB is an SQLite Data Base.
	 * @author ricardosousa
	 * */
	private static class EmailsHelper extends SQLiteOpenHelper
	{
		private static final String DB_NAME = "contacts.db";
		private static final int DB_VERSION = 1;
		
		public EmailsHelper(Context context) 
		{
			// Don't need the cursor factory, so it's null.
			super(context, DB_NAME, null, DB_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase db) 
		{
			String columns = _EmailsContract._ID     + " INTEGER PRIMARY KEY AUTOINCREMENT, "
							 + _EmailsContract.EMAIL + " TEXT NOT NULL";
			
			String sql = "CREATE TABLE IF NOT EXISTS " + _EmailsContract.TABLE + " (" + columns + ")";
			db.execSQL(sql);
		}
		
		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) 
		{
			// For simplify, remove the table and create a new one.
			db.execSQL("DROP TABLE IF EXISTS " + _EmailsContract.TABLE);
			onCreate(db);
		}
	}
}
