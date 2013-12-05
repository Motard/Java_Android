package pt.flag.android_training.dummy_android;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.protocol.HTTP;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
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
	private static final List<String> _contacts = new ArrayList<String>();
	
	private String _emailText;
	private ArrayAdapter<String> _adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		Log.d(MainActivity.TAG, "ContactsActivity: onCreate.");
		super.onCreate(savedInstanceState);
		/*
		 *  In this example, none layout is defined but you can define one ;-).
		 *  So, is not necessary to inflate the view.
		 */
		
		// Set the contacts.
		_contacts.add("ricardo.sousa@challenge.pt");
		_contacts.add("lara.santos@challenge.pt");
		
		/*
		 * The interface ListAdapter defines the necessary things to adapt one data model 
		 * to the ListView.
		 * The "android.R.layout.simple_list_item_1" parameter indicates that is for use the 
		 * Android default layout in the ListView items.
		 * */
		// _adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, _contacts);
		
		// Usage of the custom adapter.
		_adapter = new ContactsAdapter();
		setListAdapter(_adapter);
		
		// Get the e-mail text from intent.
		_emailText = getIntent().getStringExtra(MainActivity.EMAIL_TEXT);
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id)
	{
		super.onListItemClick(l, v, position, id);
		
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
		intent.putExtra(Intent.EXTRA_EMAIL, new String[] {_contacts.get(position)}); // recipients.
		intent.putExtra(Intent.EXTRA_SUBJECT, "Hello");
		intent.putExtra(Intent.EXTRA_TEXT, body);
		startActivity(intent);
		
		// Show an toast only for show that the startActivity is asynchronous operation.
		// Toast.makeText(this, "Email send to " + _contacts.get(position), Toast.LENGTH_LONG).show();
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
						
						// Or use add method that refresh the view also.
						_adapter.add(et.getText().toString());
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
	private class ContactsAdapter extends ArrayAdapter<String>
	{
		public ContactsAdapter() 
		{
			super(ContactsActivity.this, 0, ContactsActivity._contacts);
		}

		@Override
		public View getView(final int position, View convertView, ViewGroup parent) 
		{
			View v = convertView; 
			Holder h;
			if ( v== null) 
			{ 
				v = getLayoutInflater().inflate(R.layout.contact_item, null); 
				
				h = new Holder();
				h.tv = (TextView)v.findViewById(R.id.my_contact_item_id);
				h.bt = (Button)v.findViewById(R.id.my_contact_item_delete_id);
				// Set the view tag with the holder object for optimization.
				v.setTag(h);
			} 
			else 
				h = (Holder)v.getTag(); 
			
			h.tv.setText(_contacts.get(position));
			// Set the delete action in the btn.
			h.bt.setOnClickListener(new View.OnClickListener() 
			{
				@Override
				public void onClick(View v) 
				{
					ContactsAdapter.this.remove(ContactsActivity._contacts.get(position));
				}
			});
			return v;
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
