package pt.flag.android_training.dummy_android;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.Toast;

/**
 * First Activity of the application.
 * @author ricardosousa
 * */
public class MainActivity extends Activity
{
	public final static String TAG = "tag";
	public final static String EMAIL_TEXT = "pt.flag.android_training.dummy_android.MainActivity.EMAIL_TEXT";
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		Log.d(MainActivity.TAG, "MainActivity: onCreate.");	
		super.onCreate(savedInstanceState);
		// Inflate the view.
		setContentView(R.layout.activity_main);
		
		// Example of add an button, or other view, programmatically.
		// Button b = new Button(this);
		// addContentView(b, new LayoutParams(100, 100));
		// LinearLayout l = (LinearLayout)findViewById(R.id.my_linearLayout);
		// l.addView(b, 0);
		 
		// Get the button view to add the click listener. Anonymous class usage.
		findViewById(R.id.my_button_open_contacts_id).setOnClickListener(new OnClickListener() {	
			@Override
			public void onClick(View v) 
			{
				/*
                 * First, create the Intent.
                 * An Intent is an object that provides runtime binding between separate 
                 * components (such as two activities). The Intent represents an app’s 
                 * "intent to do something." You can use intents for a wide variety of tasks,
                 * but most often they’re used to start another activity. 
                 *  
                 * This is an explicit intent, because the Intent specifies the exact application
                 * component to which the intent should be given. 
                 * */
				Intent intent = new Intent(MainActivity.this, ContactsActivity.class);
				// Get the email text.
				String text = ((EditText)MainActivity.this.findViewById(R.id.my_editText_id)).getText().toString();
				if (text.equals(""))
				{
					Toast.makeText(MainActivity.this, "Empty text!", Toast.LENGTH_LONG).show();
					return;
				}
				
				// Put the text in the intent.
				intent.putExtra(MainActivity.EMAIL_TEXT, text);
				// Start the new activity using the explicit intent created previously.
				startActivity(intent);
			}
		});
		
		// Get the button view for open the Dummy Activity.
		findViewById(R.id.my_button_open_dummy_activity_id).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) 
			{
				startActivity(new Intent(MainActivity.this, DummyActivity.class));
			}
		});
	}
}