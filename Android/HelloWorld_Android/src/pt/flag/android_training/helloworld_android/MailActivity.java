package pt.flag.android_training.helloworld_android;

import java.util.ArrayList;

import org.apache.http.protocol.HTTP;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MailActivity extends ListActivity
{
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
		// TODO: do i need reference to e-mail list?!
		ArrayList<String> emails = new ArrayList<String>();
		emails.add("ricardo.sousa@challenge.pt");
		emails.add("lara.santos@challenge.pt");
		emails.add("info@challenge.pt");
		
		// Create the adapter object to adapt the data model to this ListView.
		/*
		 * Usage of an default adapter.
		 * The "android.R.layout.simple_list_item_1" parameter indicates that is for use the 
		 * Android default layout in the ListView items.
		 * */
		ListAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, emails);
		
		// Set adapter to the list.
		setListAdapter(adapter);
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) 
	{
		super.onListItemClick(l, v, position, id);
		
		// Get the e-mail string.
		// TODO: What if the item is not an TextView?!
		String email = ((TextView)v).getText().toString();
		
		// Get the e-mail body.
		// TODO: name of the extra key is hard coded! :-S
		String body = getIntent().getStringExtra("my_body");
		
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
}















