package com.ferg.awfulapp;

import android.app.Activity;
import android.content.pm.ActivityInfo;

import com.ferg.awfulapp.preferences.AwfulPreferences;

/**
 * Responsible for setting activity preferences which need to affect every activity in the app.
 * Standard activities do not need to deal with this class manually and can simply extend
 * {@link AwfulActivity} instead. Things that would prefer ListActivities or something should
 * follow {@link AwfulActivity}'s example and call one of this class's lifecycle methods along with
 * their own.
 *  
 */
public class ActivityConfigurator implements AwfulPreferences.AwfulPreferenceUpdate {
	private Activity mActivity;
	private AwfulPreferences mPrefs;
	
	public ActivityConfigurator(Activity activity) {
		mActivity = activity;
		mPrefs = AwfulPreferences.getInstance(activity,this);
	}
	
	public void onCreate() {}
	
	public void onStart() {	}
	
	public void onResume() {setOrientation();}
	
	public void onPause() {	}
	
	public void onStop() {}
	
	public void onDestroy() {
		mPrefs.unregisterCallback(this);
	}
	
	private void setOrientation() {
		String orientationStr = mPrefs.orientation;
		int orientation = ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED;
		if(orientationStr.equals("portrait")) {
			orientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;
		} else if(orientationStr.equals("landscape")) {
			orientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE;
		} else if(orientationStr.equals("sensor")) {
			orientation = ActivityInfo.SCREEN_ORIENTATION_SENSOR;
		}
		mActivity.setRequestedOrientation(orientation);
	}

	@Override
	public void onPreferenceChange(AwfulPreferences prefs, String key) {
		setOrientation();
	}
}
