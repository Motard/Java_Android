package pt.flag.android_training.dummy_android;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.widget.Toast;

public class ChangeConnectivityCapture extends BroadcastReceiver
{
	@Override
	public void onReceive(Context ctx, Intent intent) 
	{
		if (intent.getBooleanExtra(ConnectivityManager.EXTRA_NO_CONNECTIVITY, false))
			Toast.makeText(ctx, "no connectivity!", Toast.LENGTH_LONG).show();
	}
}