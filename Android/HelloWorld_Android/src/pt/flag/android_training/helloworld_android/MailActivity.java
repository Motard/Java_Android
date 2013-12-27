package pt.flag.android_training.helloworld_android;

import java.util.ArrayList;

import org.apache.http.protocol.HTTP;

import pt.flag.android_training.helloworld_android.providers.EmailsContract;
import pt.flag.android_training.helloworld_android.services.AddEmailService;
import pt.flag.android_training.helloworld_android.services.RemoveEmailService;

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
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class MailActivity extends ListActivity
{
	//private ArrayList<String> _emails = new ArrayList<String>();

	//private ArrayAdapter<String> _adapter;
	private CursorAdapter _adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		/*
		 *  In this example, none layout is defined but you can define one ;-).
		 *  So, is not necessary to inflate the view.
		 *  ListActivy have an pre-defined layout (the rows...).
		 */

		// Create an e-mail list.
		/*
                String[] emails = new String[] {"ricardo.sousa@challenge.pt",
                                                                                "lara.santos@challenge.pt",
                                                                                "info@challenge.pt"};
		 */

		//OLD //_emails.add("ricardo.sousa@challenge.pt");
		//OLD //_emails.add("lara.santos@challenge.pt");
		//OLD //_emails.add("info@challenge.pt");

		// Create the adapter object to adapt the data model to this ListView.
		/*
		 * Usage of an default adapter.
		 * The "android.R.layout.simple_list_item_1" parameter indicates that is for use the 
		 * Android default layout in the ListView items.
		 * */
		//                ListAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, _emails);
		//                ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.contact_item, R.id.my_contact_item_id, _emails);

		//OLD //_adapter = new ContactsAdapter();
		// Set adapter to the list.
		//OLD //setListAdapter(_adapter);
		
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
					//OLD //_adapter.add(editText.getText().toString());
					Intent intent = new Intent(MailActivity.this, AddEmailService.class);
					// TODO: Do not use hardcoded string "EXTRA_EMAIL"
					intent.putExtra("BATATE", editText.getText().toString());
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

		// Get the e-mail string.
		// DONE: What if the item is not an TextView?!
		// String email = ((TextView)v).getText().toString();
		//OLD //String email = _emails.get(position);
		
		// Get the cursor pointing to the correct email.
		Cursor cursor = (Cursor)l.getItemAtPosition(position);
		// Get the email from cursor.
		String email = cursor.getString(cursor.getColumnIndex(EmailsContract.EMAIL));
		
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

	private class ContactsAdapter extends CursorAdapter//extends ArrayAdapter<String>
	{
		/*
		public ContactsAdapter()
		{
			// The context is the current Activity (MailActivity instance).
			super(MailActivity.this, 0, _emails);
		}
		*/
		
		public ContactsAdapter(Cursor cursor)
		{
			super(MailActivity.this, cursor, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
		}
		
		/*
		@Override
		public View getView(final int position, View convertView, ViewGroup parent)
		{
			// final int pos = position;

			// TODO: should we use the view tag and create an holder?!
			convertView = getLayoutInflater().inflate(R.layout.contact_item, null);

			// Set the email in the row.
			((TextView)convertView.findViewById(R.id.my_contact_item_id)).setText(_emails.get(position));

			// Set onClick event for delete the email from list.
			convertView.findViewById(R.id.my_contact_item_delete_id).setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) 
				{
					// Delete the email on that position.
					// _emails.remove(pos);
					//_emails.remove(position);
					//ContactsAdapter.this.notifyDataSetChanged();
					// or...
					String email = MailActivity.this._emails.get(position);
					ContactsAdapter.this.remove(email);
				}
			});

			return convertView;
		}
		*/

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
		public void bindView(View v, final Context ctx, final Cursor cursor)
		{
			// Set text email.
			TextView tv = (TextView)v.findViewById(R.id.my_contact_item_id);
			tv.setText(cursor.getString(cursor.getColumnIndex(EmailsContract.EMAIL)));
			
			final int id = cursor.getInt(cursor.getColumnIndex(EmailsContract._ID));
			
			// Set the event when the delete button is clicked.
			v.findViewById(R.id.my_contact_item_delete_id).setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) 
				{
					Intent intent = new Intent(ctx, RemoveEmailService.class);
					intent.putExtra("POS", id);
					startService(intent);
				}
			});
		}

		@Override
		public View newView(Context ctx, Cursor cursor, ViewGroup vg) 
		{
			return getLayoutInflater().inflate(R.layout.contact_item, null);
		}
		
		@Override
		protected void onContentChanged()
		{
			super.onContentChanged();
			// If notified of changes asks to fetch e-mails again.
			new FetchEmailsAsyncTask().execute();
		}
	}
	
	private class FetchEmailsAsyncTask extends AsyncTask<Void, Void, Cursor>
	{
		// Run in alternative Thread.
		@SuppressWarnings("deprecation")
		@Override
		protected Cursor doInBackground(Void... params)
		{
			// Make the query. Start managing cursor and return it.
			Cursor cursor = getContentResolver().query(EmailsContract.CONTENT_URI, null, null, null, null);
			
			cursor.setNotificationUri(getContentResolver(), EmailsContract.CONTENT_URI);
			// Tells that the MailActivity controls the life-cycle of the cursor.
			startManagingCursor(cursor);
			return cursor;
		}
		
		// Run in UI Thread.
		@SuppressWarnings("deprecation")
		@Override
		protected void onPostExecute(Cursor newCursor)
		{
			// If is the first time, creates the adapter.
			if(_adapter == null)
			{
				_adapter = new ContactsAdapter(newCursor);
				// Set adapter in the list.
				MailActivity.this.setListAdapter(_adapter);
			}
			// If the adapter already exists, swap the cursor in adapter.
			else
			{
				// Stop using the old version of the emails in the cursor.
				stopManagingCursor(_adapter.getCursor());
				_adapter.changeCursor(newCursor);
			}
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
