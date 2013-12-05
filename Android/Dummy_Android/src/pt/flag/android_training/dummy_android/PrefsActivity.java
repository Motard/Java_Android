package pt.flag.android_training.dummy_android;

import android.os.Bundle;
import android.preference.PreferenceActivity;

/**
 * Application preferences activity for show and 
 * handle the user preferences.
 * 
 * @author ricardosousa
 * */
public class PrefsActivity extends PreferenceActivity
{
	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.user_prefs);
	}
}