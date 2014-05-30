package com.hamzah.rotationforcer;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.widget.Toast;

public class ToggleRotation extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_toggle_rotation);
		
		String toggle = "";
		boolean on = false;
		
		@SuppressWarnings("deprecation")
		SharedPreferences pref = getSharedPreferences("pref", Context.MODE_WORLD_READABLE);
		on = pref.getBoolean("on", false);
		Editor editor = pref.edit();
		editor.putBoolean("on", !on);
		editor.apply();
		
		toggle = (on? "Disabled":"Enabled");
		Toast.makeText(this, toggle + " force landcape", Toast.LENGTH_SHORT).show();
		
		finish();
	}
}
