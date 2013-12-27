package pt.flag.android_training.helloworld_android.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.widget.Toast;

public class ConnectivityCapture extends BroadcastReceiver
{
	@Override
	public void onReceive(Context context, Intent intent) 
	{
		if(intent.getBooleanExtra(ConnectivityManager.EXTRA_NO_CONNECTIVITY, false))
			Toast.makeText(context, "No connectivity", Toast.LENGTH_LONG).show();
	}
}