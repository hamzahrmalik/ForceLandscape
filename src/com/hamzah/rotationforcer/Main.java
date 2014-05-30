package com.hamzah.rotationforcer; 

import static de.robv.android.xposed.XposedHelpers.findAndHookMethod;
import android.app.Activity;
import android.content.pm.ActivityInfo;
import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.IXposedHookZygoteInit;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XSharedPreferences;
import de.robv.android.xposed.callbacks.XC_LoadPackage.LoadPackageParam;
 
public class Main implements IXposedHookLoadPackage, IXposedHookZygoteInit{
	
	XSharedPreferences pref;

	@Override
	public void handleLoadPackage(LoadPackageParam lpparam) throws Throwable {
		pref.reload();
		final boolean landscape = pref.getBoolean("on", false);
		findAndHookMethod(Activity.class, "onResume", new XC_MethodHook(){
			@Override
			protected void afterHookedMethod(MethodHookParam param)
					throws Throwable {
				Activity activity = (Activity) param.thisObject;
				
				if(landscape)
					activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
			}
		});
	}

	@Override
	public void initZygote(StartupParam startupParam) throws Throwable {
		pref = new XSharedPreferences("com.hamzah.rotationforcer", "pref");
	} 
}