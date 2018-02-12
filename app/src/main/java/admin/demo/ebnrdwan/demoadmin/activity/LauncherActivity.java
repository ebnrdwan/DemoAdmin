package  admin.demo.ebnrdwan.demoadmin.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class LauncherActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
//		startActivity(new Intent(this, StartupFinalActivity.class));
        finish();

	}
}
