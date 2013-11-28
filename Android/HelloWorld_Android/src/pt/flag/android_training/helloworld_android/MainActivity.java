package pt.flag.android_training.helloworld_android;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.app.Activity;

public class MainActivity extends Activity 
{
	private String _text = "B";
	
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
	}
	
	// OnClick method.
	public void kumekie(View v)
	{
		String currText = _tv.getText().toString();
		
		// Set text view text.
		_tv.setText(_text);
		// Set button text.
		((Button)v).setText(_text);
		
		// Change the current text for next time.
		_text = currText;
	}
}