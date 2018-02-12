package  admin.demo.ebnrdwan.demoadmin.modules.location;

import  admin.demo.ebnrdwan.demoadmin.lib.GPS;

import android.location.Location;

public interface ILocationResultListener {
	public void onLocationResult(Location location);
	public void onGsmLocationResult(GPS gps);
	public void onNoResult();
}
