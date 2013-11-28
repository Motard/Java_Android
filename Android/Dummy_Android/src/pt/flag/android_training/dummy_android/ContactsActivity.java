package pt.flag.android_training.dummy_android;

import org.apache.http.protocol.HTTP;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

/**
 * List Activity with e-mail contacts for select one to send the e-mail.
 * @author ricardosousa
 * */
public class ContactsActivity extends ListActivity
{
	private static final String[] _contacts = {"ricardo.sousa@challenge.pt", 
											   "lara.santos@challenge.pt"};
	
	private String _emailText;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		Log.d(MainActivity.TAG, "ContactsActivity: onCreate.");
		super.onCreate(savedInstanceState);
		/*
		 *  In this example, none layout is defined but you can define one ;-).
		 *  So, is not necessary to inflate the view.
		 */
		
		/*
		 * The interface ListAdapter defines the necessary things to adapt one data model 
		 * to the ListView.
		 * The "android.R.layout.simple_list_item_1" parameter indicates that is for use the 
		 * Android default layout in the ListView items.
		 * */
		ListAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, _contacts);
		setListAdapter(adapter);
		
		// Get the e-mail text from intent.
		_emailText = getIntent().getStringExtra(MainActivity.EMAIL_TEXT);
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id)
	{
		super.onListItemClick(l, v, position, id);
		
		/*
		 * Create an implicit intent to start an component that allows to 
		 * send an e-mail.
		 * */
		Intent intent = new Intent(Intent.ACTION_SEND);
		intent.setType(HTTP.PLAIN_TEXT_TYPE);
		intent.putExtra(Intent.EXTRA_EMAIL, new String[] {_contacts[position]}); // recipients.
		intent.putExtra(Intent.EXTRA_SUBJECT, "Hello");
		intent.putExtra(Intent.EXTRA_TEXT, _emailText);
		startActivity(intent);
	}
}
