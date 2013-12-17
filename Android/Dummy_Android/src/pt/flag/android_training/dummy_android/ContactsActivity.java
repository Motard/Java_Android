package pt.flag.android_training.dummy_android;

import org.apache.http.protocol.HTTP;

import pt.flag.android_training.dummy_android.providers.ContactsContract;
import pt.flag.android_training.dummy_android.services.AddContactService;
import pt.flag.android_training.dummy_android.services.RemoveContactService;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.Context;
import android.content.CursorLoader;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.Loader;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.widget.CursorAdapter;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

/**
 * List Activity with e-mail contacts for select one to send the e-mail.
 * @author ricardosousa
 * */
public class ContactsActivity extends ListActivity
{
	private String _emailText;
	private CursorAdapter _adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		Log.d(MainActivity.TAG, "ContactsActivity: onCreate.");
		super.onCreate(savedInstanceState);
		/*
		 *  In this example, none layout is defined but you can define one ;-).
		 *  So, is not necessary to inflate the view.
		 */
				
		// Get the e-mail text from intent.
		_emailText = getIntent().getStringExtra(MainActivity.EMAIL_TEXT);
		
		_adapter = new ContactsAdapter();
		setListAdapter(_adapter);
		
		// Use LoaderManager.
		getLoaderManager().initLoader(0, null, new LoaderCallbacks<Cursor>() {
			@Override
			public Loader<Cursor> onCreateLoader(int id, Bundle args)
			{
				return new CursorLoader(ContactsActivity.this, pt.flag.android_training.dummy_android.providers.ContactsContract.CONTENT_URI, null, null, null, null);
			}

			@Override
			public void onLoadFinished(Loader<Cursor> loader, Cursor cursor)
			{
				// Register this cursor to "watch" the changes occurred in the specified URI.
				cursor.setNotificationUri(getContentResolver(), pt.flag.android_training.dummy_android.providers.ContactsContract.CONTENT_URI);
				_adapter.swapCursor(cursor);
			}

			@Override
			public void onLoaderReset(Loader<Cursor> loader) 
			{
				_adapter.swapCursor(null);
			}
		});
	}
	
	protected void onListItemClick(ListView l, View v, int position, long id)
	{
		super.onListItemClick(l, v, position, id);
		
		// Get the cursor.
		Cursor cursor = (Cursor)l.getItemAtPosition(position);
		
		// Verify the signature from preferences.
		SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
		String body = _emailText;
		if (prefs.getBoolean("signatureOn", false))
		{
			// Add the signature to body.
			body += "\n" + prefs.getString("signature", "");
		}
		
		/*
		 * Create an implicit intent to start an component that allows to 
		 * send an e-mail.
		 * */
		Intent intent = new Intent(Intent.ACTION_SEND);
		intent.setType(HTTP.PLAIN_TEXT_TYPE);
		intent.putExtra(Intent.EXTRA_EMAIL, new String[] {cursor.getString(cursor.getColumnIndex(ContactsContract.EMAIL))}); // recipients.
		intent.putExtra(Intent.EXTRA_SUBJECT, "Hello");
		intent.putExtra(Intent.EXTRA_TEXT, body);
		startActivity(intent);
	}
	
	/*
	 * This callback method is called only the first time the menu is 
	 * requested. In this method is necessary to inflate the menu.
	 * */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) 
	{
		Boolean superRes = super.onCreateOptionsMenu(menu);
		// Inflates the menu.
		getMenuInflater().inflate(R.menu.contacts, menu);
		return true && superRes;
	}
	
	/*
	 * Callback method that is called always an item is 
	 * selected in the options menu.
	 * */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) 
	{
		switch (item.getItemId())
		{
			// For add one more contact.
			case R.id.my_add_contact_menu_id:
				
				// Creation of one AlertDialog for the user writes the new e-mail to add to list.
				final EditText et = new EditText(this);				
				
				new AlertDialog.Builder(this)
				.setTitle("new e-mail")
				.setView(et)
				.setNegativeButton("cancel", null)
				.setPositiveButton("add", new OnClickListener() {		
					@Override
					public void onClick(DialogInterface dialog, int which)
					{
						// _contacts.add(et.getText().toString());
						// Refresh the list view.
						// _adapter.notifyDataSetChanged();
						
						// Start service to add new contact.
						Intent intent = new Intent(ContactsActivity.this, AddContactService.class);
						intent.putExtra(AddContactService.EMAIL, et.getText().toString());
						startService(intent);
					}
				})
				.create()
				.show();
				
				return true;
				
			// For open the preferences.
			case R.id.my_open_prefs_id:
				startActivity(new Intent(this, PrefsActivity.class));
				return true;
				
			default:
				return super.onOptionsItemSelected(item);
		}
	}
	
	/**
	 * Custom Array Adapter for contacts.
	 * 
	 * @author ricardosousa
	 * */
	private class ContactsAdapter extends CursorAdapter
	{
		private final Context _ctx;
		
		public ContactsAdapter() 
		{
			super(ContactsActivity.this, null, 0);
			_ctx = ContactsActivity.this;
		}
		
		@Override
		public boolean areAllItemsEnabled() 
		{
			return true;
		}
		
		@Override
		public boolean isEnabled(int position) 
		{
			return true;
		}

		@Override
		public void bindView(View v, Context ctx, final Cursor cursor) 
		{
			Holder h = (Holder)v.getTag(); 
			
			h.tv.setText(cursor.getString(cursor.getColumnIndex(pt.flag.android_training.dummy_android.providers.ContactsContract.EMAIL)));
			// Set the delete action in the btn.
			h.bt.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) 
				{
					// Start activity to remove.
					int id = cursor.getInt(cursor.getColumnIndex(ContactsContract._ID));
					Intent intent = new Intent(ContactsAdapter.this._ctx, RemoveContactService.class);
					intent.putExtra(RemoveContactService.POSITION, id);
					startService(intent);
				}
			});
		}

		@Override
		public View newView(Context arg0, Cursor arg1, ViewGroup arg2)
		{
			Holder h = new Holder(); 
			View v = getLayoutInflater().inflate(R.layout.contact_item, null); 
			h.tv = (TextView)v.findViewById(R.id.my_contact_item_id);
			h.bt = (Button)v.findViewById(R.id.my_contact_item_delete_id);
			v.setTag(h);
			return v;
		}
		
		/**
		 * Helper class for hold the views, so we gain 
		 * optimization because only inflates once the item view.
		 * */
		private class Holder
		{
			TextView tv;
			Button bt;
		}
	}
}
