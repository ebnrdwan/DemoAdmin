package  admin.demo.ebnrdwan.demoadmin.services;

import  admin.demo.ebnrdwan.demoadmin.Debug;

import android.content.Context;
import android.database.ContentObserver;
import android.os.Handler;

public class CallContentObserver extends ContentObserver {

	public CallContentObserver(Handler handler, Context context) {
		super(handler);
	}
	
	@Override
	public void onChange(boolean selfChange) {
        super.onChange(selfChange);
        Debug.i("[CallContentObserver] onChange");
        
        if (AppService.sThreadManager != null) {
			AppService.sThreadManager.onCallChange();
		}
	}
}
