package pt.flag.android_training.helloworld_android;

import java.util.ArrayList;

import org.apache.http.protocol.HTTP;

import android.app.ListActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MailActivity extends ListActivity
{
	private ArrayList<String> _emails = new ArrayList<String>();
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
		
		_emails.add("ricardo.sousa@challenge.pt");
		_emails.add("lara.santos@challenge.pt");
		_emails.add("info@challenge.pt");
		
		// Create the adapter object to adapt the data model to this ListView.
		/*
		 * Usage of an default adapter.
		 * The "android.R.layout.simple_list_item_1" parameter indicates that is for use the 
		 * Android default layout in the ListView items.
		 * */
//		ListAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, _emails);
//		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.contact_item, R.id.my_contact_item_id, _emails);
		
		ListAdapter adapter = new ContactsAdapter();
		
		// Set adapter to the list.
		setListAdapter(adapter);
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) 
	{
		super.onListItemClick(l, v, position, id);
		
		// Get the e-mail string.
		// DONE: What if the item is not an TextView?!
		// String email = ((TextView)v).getText().toString();
		String email = _emails.get(position);
		
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
	
	private class ContactsAdapter extends ArrayAdapter<String>
	{
		public ContactsAdapter()
		{
			// The context is the current Activity (MailActivity instance).
			super(MailActivity.this, 0, _emails);
		}
		
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
	}
}









