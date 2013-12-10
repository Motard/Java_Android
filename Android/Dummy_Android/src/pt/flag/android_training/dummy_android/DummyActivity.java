package pt.flag.android_training.dummy_android;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

/**
 * Dummy Activity. Only for show some code examples like the 
 * back button callback.
 * 
 * @author ricardosousa
 * */
public class DummyActivity extends Activity
{
	private static final String COUNT_KEY = "count";
	private int _btnTextCont;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dummy);
		final Button b = (Button)findViewById(R.id.my_button_back_from_dummy_activity_id);
		if(savedInstanceState != null)
			_btnTextCont = savedInstanceState.getInt(COUNT_KEY);
			
		b.setText(_btnTextCont + "");
		b.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) 
			{
				// Simulates the "go back" action.
				// DummyActivity.this.onBackPressed();
				b.setText(++_btnTextCont + "");
			}
		});
	}
	
	// Overrides onBackPressed only for show that is possible.
	@Override
	public void onBackPressed() 
	{
		super.onBackPressed();
		Toast.makeText(this, "back action!", Toast.LENGTH_LONG).show();
	}
	
	@Override
	protected void onSaveInstanceState(Bundle outState)
	{
		Log.d(MainActivity.TAG, "DummyActivity: onSaveInstanceState");
		super.onSaveInstanceState(outState);
		// Save the state.
		outState.putInt(COUNT_KEY, _btnTextCont);
	}
	
	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState)
	{
		Log.d(MainActivity.TAG, "DummyActivity: onRestoreInstanceState");
		super.onRestoreInstanceState(savedInstanceState);
		//_btnTextCont = savedInstanceState.getInt(COUNT_KEY);
	}
}
