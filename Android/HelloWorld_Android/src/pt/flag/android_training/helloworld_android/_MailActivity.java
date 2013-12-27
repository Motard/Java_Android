package pt.flag.android_training.helloworld_android;

import org.apache.http.protocol.HTTP;

import pt.flag.android_training.helloworld_android.providers._EmailsContract;
import pt.flag.android_training.helloworld_android.services._AddEmailService;
import pt.flag.android_training.helloworld_android.services._RemoveEmailService;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.widget.CursorAdapter;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class _MailActivity extends ListActivity
{
	private CursorAdapter _adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		/*
		 *  In this example, none layout is defined but you can define one ;-).
		 *  So, is not necessary to inflate the view.
		 *  ListActivity have an pre-defined layout (the rows...).
		 */
		
		// Query e-mails the first time.
		new FetchEmailsAsyncTask().execute();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) 
	{
		boolean resSuper = super.onCreateOptionsMenu(menu);
		getMenuInflater().inflate(R.menu.contacts, menu);
		return resSuper && true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		switch (item.getItemId()) 
		{
			// Add new contact.
			case R.id.my_add_contact_menu_id:
				
				final EditText editText = new EditText(this);
				
				// Create an Dialog.
				new AlertDialog.Builder(this).setTitle("new e-mail")
											 .setNegativeButton("cancel", null)
											 .setPositiveButton("add", new OnClickListener() {	
												@Override
												public void onClick(DialogInterface dialog, int which) 
												{
													// Start service to add new contact.
													Intent intent = new Intent(_MailActivity.this, _AddEmailService.class);
													intent.putExtra(_EmailsContract.EMAIL, editText.getText().toString());
													startService(intent);
												}
											 })
											 .setView(editText) // Returns a Builder.
											 .create() // Returns an AlertDialog.
											 .show();
				return true;
			
			// Open preferences Activity.
			case R.id.my_open_prefs_id:
				startActivity(new Intent(this, PrefsActivity.class));
				return true;
				
			default: 
				return super.onOptionsItemSelected(item);
		}
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) 
	{
		super.onListItemClick(l, v, position, id);
		
		// Get the cursor.
		Cursor cursor = (Cursor)l.getItemAtPosition(position);
				
				
		// Get the e-mail string.
		// DONE: What if the item is not an TextView?!
		// String email = ((TextView)v).getText().toString();
		
		String email = cursor.getString(cursor.getColumnIndex(_EmailsContract.EMAIL));
		
		// Get the e-mail body.
		// TODO: name of the extra key is hard coded! :-S
		String body = getIntent().getStringExtra("my_body");
		
		// Get the signature.
		SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
		// Concatenates the signature with the body.
		body += "\n" + prefs.getString("signature", "");
		
		/*
		 * Create an implicit intent to start an component that allows to 
		 * send an e-mail.
		 * */
		Intent intent = new Intent(Intent.ACTION_SEND);
		intent.setType(HTTP.PLAIN_TEXT_TYPE);
		
		// Extras.
		intent.putExtra(Intent.EXTRA_EMAIL, new String[] {email});
		// intent.putExtra(Intent.EXTRA_SUBJECT, "Hi, please read this email!");
		intent.putExtra(Intent.EXTRA_TEXT, body);
		
		// Send the intent...
		// this.startActivity(intent);
		// Always choose.
		startActivity(Intent.createChooser(intent, "Choose"));
	}
	
	/**
	 * Async task for fetch the e-mails. 
	 * 
	 * @author ricardosousa
	 * */
	private class FetchEmailsAsyncTask extends AsyncTask<Void, Void, Cursor>
    {
        @SuppressWarnings("deprecation")
		@Override
        protected Cursor doInBackground(Void... params)
        {
    		Cursor cursor = getContentResolver().query(_EmailsContract.CONTENT_URI, null, null, null, null);
    		cursor.setNotificationUri(getContentResolver(), _EmailsContract.CONTENT_URI);
    		startManagingCursor(cursor);
    		return cursor;
        }

        @SuppressWarnings("deprecation")
		@Override
        protected void onPostExecute(final Cursor newCursor)
        {
        	// Lazy load creation/set for adapter.
            if(_adapter == null)
            {
                // First load.
            	_adapter = new EmailsAdapter(newCursor);
            	_MailActivity.this.setListAdapter(_adapter);
            }
            else
            {
            	// Stop the old cursor, and change to the new cursor.
                stopManagingCursor(_adapter.getCursor());
                _adapter.changeCursor(newCursor);
            }
        }
    };
	
	/**
	 * Custom cursor adapter, for querying the data.
	 * @author ricardosousa
	 * */
	private class EmailsAdapter extends CursorAdapter
	{
		public EmailsAdapter(Cursor cursor)
		{
			super(_MailActivity.this, cursor, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
		}
		
		@Override
		public boolean areAllItemsEnabled() { return true; }
		@Override
		public boolean isEnabled(int position) { return true; }

		@Override
		public void bindView(View v, final Context ctx, final Cursor cursor) 
		{
			// TODO: call findViewById every time bindView is called?!?!?! Hum...
			
			// Set the e-mail in the text view.
			((TextView)v.findViewById(R.id.my_contact_item_id)).setText(cursor.getString(cursor.getColumnIndex(_EmailsContract.EMAIL)));
			
			// Set the event when the delete button is clicked.
			v.findViewById(R.id.my_contact_item_delete_id).setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) 
				{
					// Start activity to remove.
					int id = cursor.getInt(cursor.getColumnIndex(_EmailsContract._ID));
					Intent intent = new Intent(ctx, _RemoveEmailService.class);
					intent.putExtra(_RemoveEmailService.POSITION, id);
					startService(intent);
				}
			});
		}

		@Override
		public View newView(Context ctx, Cursor cursor, ViewGroup vg) 
		{
			return getLayoutInflater().inflate(R.layout.contact_item, null);
		}
		
		/*
		 * Overrides onContentChanged for re-querying the data.
		 * */
		@Override
		protected void onContentChanged() 
		{
			super.onContentChanged();
			// Re-query the data.
			new FetchEmailsAsyncTask().execute();
		}
	}
}









