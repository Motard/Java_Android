package pt.flag.android_training.helloworld_android;

import org.apache.http.protocol.HTTP;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;

public class MainActivity extends Activity 
{
	private String _text = "B";	
	private int _color = Color.BLUE;	
	private TextView _tv;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		_tv = (TextView)findViewById(R.id.textView1);
		// _tv.setText("A");
		_tv.setTextSize(50);
		
		((TextView)findViewById(R.id.textView2)).setText(_tv.getText());
		
		findViewById(R.id.my_button_open_prefs_id).setOnClickListener(new View.OnClickListener() {	
			@Override
			public void onClick(View v) 
			{
				// Explicit intent to launch preferences.
				startActivity(new Intent(MainActivity.this, PrefsActivity.class));
			}
		});
	}
	
	// OnClick method.
	public void kumekie(View v)
	{
		String currText = _tv.getText().toString();
		int currTextColor = _tv.getCurrentTextColor();
		
		// Set text view text.
		_tv.setText(_text);
		// Set button text.
		((Button)v).setText(_text);
		
		// Set text view text color.
		_tv.setTextColor(_color);
		
		// Change the current text color for next time.
		_color = currTextColor;
		// Change the current text for next time.
		_text = currText;
	}
	
	// OnLCick method for toast.
	public void showToast(View v)
	{
		Toast.makeText(this, "This is a Toast", Toast.LENGTH_LONG).show();
	}
	
	// OnLCick method for send an email.
	public void sendEmail(View v)
	{
		/*
		 * Create an implicit intent to start an component that allows to 
		 * send an e-mail.
		 * */
		Intent intent = new Intent(Intent.ACTION_SEND);
		intent.setType(HTTP.PLAIN_TEXT_TYPE);
		
		// Extras.
//		String[] emails = new String[] {"sousa.ricardo10@gmail.com"};
		intent.putExtra(Intent.EXTRA_EMAIL, /*emails*/ new String[] {"sousa.ricardo10@gmail.com"});
		intent.putExtra(Intent.EXTRA_SUBJECT, "Hi, please read this email!");
		intent.putExtra(Intent.EXTRA_TEXT, _tv.getText());
		
		// Send the intent...
//		this.startActivity(intent);
		// Always choose.
		startActivity(Intent.createChooser(intent, "Choose"));
	}
	
	// OnClick method for open the second activity.
	public void openSecondActivity(View v)
	{
		/*
		 * Create an explicit intent to start the second
		 *  activity.
		 * */
		startActivity(new Intent(this, SecondActivity.class));
	}
	
	// OnCLick method for open list activity with the e-mail addresses.
	public void sendEmailTo(View v)
	{
		// First, create an explicit intent to open the list activity with the e-mail addresses. 
		Intent intent = new Intent(this, MailActivity.class);
		
		// Set intent extra with the e-mail body String.
		// Step 1: Get the e-mail body from EditText element.
		EditText et = (EditText)findViewById(R.id.my_editText_id);
		String body = et.getText().toString();
		// Step 2: Set as extra.
		intent.putExtra("my_body", body);
		
		// Send the intent :-)
		startActivity(intent);
	}
}