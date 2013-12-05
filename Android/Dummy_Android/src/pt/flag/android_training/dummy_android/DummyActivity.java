package pt.flag.android_training.dummy_android;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

/**
 * Dummy Activity. Only for show some code examples like the 
 * back button callback.
 * 
 * @author ricardosousa
 * */
public class DummyActivity extends Activity
{
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dummy);
		
		findViewById(R.id.my_button_back_from_dummy_activity_id).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) 
			{
				// Simulates the "go back" action.
				DummyActivity.this.onBackPressed();
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
}
